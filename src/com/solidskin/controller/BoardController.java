  /*
   * Copyright SmartWellness,All rights reserved.
   * (http://www.smartwellness.com)
   *
   * This software is the confidential and proprietary information
   * of SmartWellness Corp. ("Confidential Information").
   */

package com.solidskin.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.solidskin.config.Configuration;
import com.solidskin.service.BoardService;
import com.solidskin.util.FileUpload;
import com.solidskin.util.LangUtil;
import com.solidskin.util.Notice;
import com.solidskin.util.PageMgr;


/**
 * @���ϸ�          : BoardController.java
 * @�ۼ���          : 2014. 12. 3.
 * @�ۼ���          : Administrator
 * @����			  :
 */

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	/**
	 * @Method Name  : noticeList
	 * @�ۼ���   : 2014. 12. 4. 
	 * @�ۼ���   : dino
	 * @�����̷�  :
	 * @Method ���� : �Խ��� ���� > �������� ����Ʈ
	 * @return
	 */
	@RequestMapping(value = "/board/noticeList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String noticeList(HttpServletRequest req, ModelMap map, HttpSession session) {
		
		//������ ������
		int page_size = new Integer(LangUtil.nvl(req.getParameter("page_size"), "10"));
		
		//���� ������
		int page_no = new Integer(LangUtil.nvl(req.getParameter("page_no"), "1"));
		
		int include_count = (page_no - 1) * page_size;
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		
		paramMap.put("include_count", Integer.toString(include_count));
		paramMap.put("page_size", Integer.toString(page_size));
		paramMap.put("searchType", LangUtil.nvl(req.getParameter("searchType"), ""));
		paramMap.put("searchKeyword", LangUtil.nvl(req.getParameter("searchKeyword"), ""));
		
		//��ü ����
		int total_cnt = boardService.selectNoticeListCnt(paramMap); 
		
		List<Notice> noticeList = boardService.selectNoticeList(paramMap);
		
		PageMgr pageMgr = new PageMgr(total_cnt, page_size, page_no, "/board/noticeList.do?page_size="+page_size);		

		map.addAttribute("total_cnt", total_cnt);
		map.addAttribute("pageList", pageMgr.getPageList());
		map.addAttribute("noticeList", noticeList);
		
		/*	ISMS 2016.08.01 ����� - ���ε� ���͸� ó�� 
		 *  ����Ȯ���� ȭ��Ʈ����Ʈ üũ�Ͽ� ���ε� ���н� ���ϰ�
		 * */
		if(req.getParameter("resultCd") != null){
			map.addAttribute("resultCd", "error");	
		}
		
		return "/board/notice_list";
	}
	
	/**
	 * @Method Name  : insertNotice
	 * @�ۼ���   : 2014. 12. 4. 
	 * @�ۼ���   : dino
	 * @�����̷�  :
	 * @Method ���� : �Խ��� > �������� ��� 
	 * @param model
	 * @param multipartRequest
	 * @return
	 */
	@RequestMapping(value = "/board/insertNotice.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String insertNotice(Model model, HttpServletRequest req, MultipartRequest multipartRequest) {
		
		MultipartFile file = multipartRequest.getFile("uploadFile");   //�信�� form���� �Ѿ�� �� name�� ������ �̸��Դϴ�.

		HashMap<String, String> paramMap = new HashMap<String, String>();
		
		String replaceName = "";
		
		try {
			Calendar cal = Calendar.getInstance();
			
			String fileName = LangUtil.nvl(file.getOriginalFilename(), "");
			
			/*	ISMS 2016.08.01 ����� - ���ε� ���͸� ó�� 
			 *  ����Ȯ���� ȭ��Ʈ����Ʈ üũ�Ͽ� ���ε� 
			 * */		
			if(!"".equals(fileName)){
				String fileOrgNameExt = fileName.substring(fileName.lastIndexOf("."));
				fileOrgNameExt = fileOrgNameExt.toLowerCase();

				if(fileOrgNameExt.equals(".jpg")){
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					replaceName = cal.getTimeInMillis() + fileType;  //���� �̸��� �ߺ��� ���� ���ؼ� �̸��� �缳���մϴ�.
					  
					String path = Configuration.conf.get("ourhome.admin.upload.path");
					
					FileUpload.fileUpload(file, path, replaceName);
				} else {
					return "redirect:/board/noticeList.do?resultCd=error";
				}
			}
			
			paramMap.put("type", req.getParameter("type"));
			paramMap.put("subject", LangUtil.removeXSS(req.getParameter("subject").toString(), true));
			paramMap.put("contents", LangUtil.removeXSS(req.getParameter("contents").toString(), true));
			paramMap.put("img", replaceName);
			
			boardService.insertNotice(paramMap);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/board/noticeList.do";
	}		
	
	/**
	 * @Method Name  : noticeView
	 * @�ۼ���   : 2014. 12. 4. 
	 * @�ۼ���   : dino
	 * @�����̷�  :
	 * @Method ���� : �Խ��� > �������� ����
	 * @return
	 * @throws SQLException 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/board/noticeView.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String noticeView(HttpServletRequest req, ModelMap map) throws SQLException, IOException {
		
		int noticeSeq = new Integer(req.getParameter("noticeSeq"));
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap = boardService.selectNoticeView(noticeSeq);
		
		/*
		// �ش� map���� CLOB�� ��ü�� ����ϰ�
		CLOB clob = (CLOB) paramMap.get("CONTENTS");

		// reader�� ����
		Reader reader = clob.getCharacterStream();

		StringBuffer out = new StringBuffer();
		char[] buff = new char[1024];
		int nchars = 0;

		// ��Ʈ�� ���ۿ� append ��Ų��
		while ((nchars = reader.read(buff)) > 0) {
			out.append(buff, 0, nchars);
		}

		// String���·� ���Ҵ�.
		paramMap.put("CONTENTS", out.toString());
		*/
		map.addAttribute("noticeInfo", paramMap);
		
		return "/board/notice_read";
	}	
	
	/**
	 * @Method Name  : modifyNotice
	 * @�ۼ���   : 2014. 12. 4. 
	 * @�ۼ���   : dino
	 * @�����̷�  :
	 * @Method ���� : �Խ��� > �������� > ����
	 * @param req
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/board/modifyNotice.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyNotice(Model model, HttpServletRequest req, MultipartRequest multipartRequest) {
		
		String noticeSeq = req.getParameter("noticeSeq");
		
		MultipartFile file = multipartRequest.getFile("uploadFile");   //�信�� form���� �Ѿ�� �� name�� ������ �̸��Դϴ�.
		
		Calendar cal = Calendar.getInstance();
		
		String fileName = LangUtil.nvl(file.getOriginalFilename(), "");
		
		try {
			HashMap<String, String> paramMap = new HashMap<String, String>();
			String path = Configuration.conf.get("ourhome.admin.upload.path");
			
			if(!"".equals(LangUtil.nvl(fileName, ""))){
				
				/*	ISMS 2016.08.01 ����� - ���ε� ���͸� ó�� 
				 *  ����Ȯ���� ȭ��Ʈ����Ʈ üũ�Ͽ� ���ε� 
				 *	*/	
				String replaceName = "";
				String fileOrgNameExt = fileName.substring(fileName.lastIndexOf("."));
				fileOrgNameExt = fileOrgNameExt.toLowerCase();

				if(fileOrgNameExt.equals(".jpg")){
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					replaceName = cal.getTimeInMillis() + fileType;  //���� �̸��� �ߺ��� ���� ���ؼ� �̸��� �缳���մϴ�.
					  
					FileUpload.fileUpload(file, path, replaceName);		
					FileUpload.fileDelete(path, req.getParameter("orgFileNm"));
					paramMap.put("img", replaceName);	
					
				} else {
					return "redirect:/board/noticeList.do?resultCd=error";
				}
				
			}else{
				if("Y".equals(req.getParameter("imgDelYn"))){
					FileUpload.fileDelete(path, req.getParameter("orgFileNm"));
					paramMap.put("img", "");					
				}else{
					paramMap.put("img", req.getParameter("orgFileNm"));					
				}
			}
			paramMap.put("contents", LangUtil.removeXSS(req.getParameter("contents").toString(), true));		
			paramMap.put("noticeSeq", noticeSeq);					
			paramMap.put("type", req.getParameter("type"));
			paramMap.put("subject", LangUtil.removeXSS(req.getParameter("subject").toString(), true));
			
			boardService.updateNotice(paramMap);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/board/noticeView.do?noticeSeq="+noticeSeq;
	}	
	
	/**
	 * @Method Name  : deleteNotice
	 * @�ۼ���   : 2014. 12. 5. 
	 * @�ۼ���   : dino
	 * @�����̷�  :
	 * @Method ���� : �Խ��� > �������� > ����
	 * @param req
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/board/deleteNotice.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteNotice(HttpServletRequest req, ModelMap map) {
		
		int noticeSeq = new Integer(req.getParameter("noticeSeq"));
		
		boardService.deleteNotice(noticeSeq);
		
		return "redirect:/board/noticeList.do";
	}		
}
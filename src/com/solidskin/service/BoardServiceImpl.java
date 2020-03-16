  /*
   * Copyright SmartWellness,All rights reserved.
   * (http://www.smartwellness.com)
   *
   * This software is the confidential and proprietary information
   * of SmartWellness Corp. ("Confidential Information").
   */

package com.solidskin.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solidskin.dao.BoardDao;
import com.solidskin.util.Notice;



/**
 * @파일명          : BoardServiceImpl.java
 * @작성일          : 2014. 12. 4.
 * @작성자          : dino
 * @설명			  :
 */

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao boardDao;
	/**
	 * @location   : kr.co.ourhome.app.board.service.BoardService.selectNoticeList
	 * @writeDay   : 2014. 12. 4. 오전 1:57:17
	 * @overridden : @see kr.co.ourhome.app.board.service.BoardService#selectNoticeList(java.util.HashMap)
	 * @Todo       :
	 */
	
	@Override
	public List<Notice> selectNoticeList(HashMap<String, String> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.selectNoticeList(paramMap);
	}

	/**
	 * @location   : kr.co.ourhome.app.board.service.BoardService.selectNoticeCnt
	 * @writeDay   : 2014. 12. 4. 오전 1:57:17
	 * @overridden : @see kr.co.ourhome.app.board.service.BoardService#selectNoticeCnt(java.util.HashMap)
	 * @Todo       :
	 */
	
	@Override
	public int selectNoticeListCnt(HashMap<String, String> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.selectNoticeListCnt(paramMap);
	}

	/**
	 * @location   : kr.co.ourhome.app.board.service.BoardService.insertNotice
	 * @writeDay   : 2014. 12. 4. 오전 1:57:17
	 * @overridden : @see kr.co.ourhome.app.board.service.BoardService#insertNotice(java.util.HashMap)
	 * @Todo       :
	 */
	
	@Override
	public void insertNotice(HashMap<String, String> paramMap) {
		// TODO Auto-generated method stub
		boardDao.insertNotice(paramMap);
	}

	/**
	 * @location   : kr.co.ourhome.app.board.service.BoardService.selectNoticeView
	 * @writeDay   : 2014. 12. 4. 오후 4:25:00
	 * @overridden : @see kr.co.ourhome.app.board.service.BoardService#selectNoticeView(int)
	 * @Todo       :
	 */
	
	@Override
	public HashMap<String, Object> selectNoticeView(int noticeSeq) {
		// TODO Auto-generated method stub
		return boardDao.selectNoticeView(noticeSeq);
	}

	/**
	 * @location   : kr.co.ourhome.app.board.service.BoardService.deleteNotice
	 * @writeDay   : 2014. 12. 5. 오후 9:52:38
	 * @overridden : @see kr.co.ourhome.app.board.service.BoardService#deleteNotice(int)
	 * @Todo       :
	 */
	
	@Override
	public void deleteNotice(int noticeSeq) {
		// TODO Auto-generated method stub
		boardDao.deleteNotice(noticeSeq);
	}

	/**
	 * @location   : kr.co.ourhome.app.board.service.BoardService.updateNotice
	 * @writeDay   : 2014. 12. 5. 오후 10:42:28
	 * @overridden : @see kr.co.ourhome.app.board.service.BoardService#updateNotice(java.util.HashMap)
	 * @Todo       :
	 */
	
	@Override
	public void updateNotice(HashMap<String, String> paramMap) {
		// TODO Auto-generated method stub
		boardDao.updateNotice(paramMap);
	}
	
}

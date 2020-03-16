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

import org.springframework.transaction.annotation.Transactional;

import com.solidskin.util.Notice;




/**
 * @?åå?ùºÎ™?          : BoardService.java
 * @?ûë?Ñ±?ùº          : 2014. 12. 4.
 * @?ûë?Ñ±?ûê          : Administrator
 * @?Ñ§Î™?			  :
 */

@Transactional
public interface BoardService {

	public List<Notice> selectNoticeList(HashMap<String, String> paramMap);
	
	public int selectNoticeListCnt(HashMap<String, String> paramMap);
	
	public void insertNotice(HashMap<String, String> paramMap);

	public HashMap<String, Object> selectNoticeView(int noticeSeq);	
	
	public void deleteNotice(int noticeSeq);
	
	public void updateNotice(HashMap<String, String> paramMap);

}
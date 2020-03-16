  /*
   * Copyright SmartWellness,All rights reserved.
   * (http://www.smartwellness.com)
   *
   * This software is the confidential and proprietary information
   * of SmartWellness Corp. ("Confidential Information").
   */

package com.solidskin.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.solidskin.util.Notice;


/**
 * @?��?���?          : BoardDao.java
 * @?��?��?��          : 2014. 12. 4.
 * @?��?��?��          : dino
 * @?���?			  :
 */

@Repository("boardDao")
public interface BoardDao {

	public List<Notice> selectNoticeList(HashMap<String, String> paramMap);
	
	public int selectNoticeListCnt(HashMap<String, String> paramMap);
	
	public void insertNotice(HashMap<String, String> paramMap);
	
	public HashMap<String, Object> selectNoticeView(int noticeSeq);
	
	public void deleteNotice(int noticeSeq);
	
	public void updateNotice(HashMap<String, String> paramMap);
	
}
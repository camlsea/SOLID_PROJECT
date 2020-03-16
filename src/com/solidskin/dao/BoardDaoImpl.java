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

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solidskin.util.Notice;


/**
 * @파일명          : BoardDaoImpl.java
 * @작성일          : 2014. 12. 4.
 * @작성자          : dino
 * @설명			  :
 */

@Repository("boardDao")
public class BoardDaoImpl implements BoardDao {

	@Autowired
    private SqlSession sql;
	
	/**
	 * @location   : kr.co.ourhome.app.board.dao.BoardDao.selectNoticeList
	 * @writeDay   : 2014. 12. 4. 오전 1:51:00
	 * @overridden : @see kr.co.ourhome.app.board.dao.BoardDao#selectNoticeList(java.util.HashMap)
	 * @Todo       :
	 */
	
	@Override
	public List<Notice> selectNoticeList(HashMap<String, String> paramMap) {
		// TODO Auto-generated method stub
		return sql.selectList("notice.selectNoticeList",paramMap);
	}

	/**
	 * @location   : kr.co.ourhome.app.board.dao.BoardDao.selectNoticeCnt
	 * @writeDay   : 2014. 12. 4. 오전 1:53:48
	 * @overridden : @see kr.co.ourhome.app.board.dao.BoardDao#selectNoticeCnt(java.util.HashMap)
	 * @Todo       :
	 */
	
	@Override
	public int selectNoticeListCnt(HashMap<String, String> paramMap) {
		// TODO Auto-generated method stub
		return sql.selectOne("notice.selectNoticeListCnt", paramMap);
	}

	/**
	 * @location   : kr.co.ourhome.app.board.dao.BoardDao.insertNotice
	 * @writeDay   : 2014. 12. 4. 오전 1:53:48
	 * @overridden : @see kr.co.ourhome.app.board.dao.BoardDao#insertNotice(java.util.HashMap)
	 * @Todo       :
	 */
	
	@Override
	public void insertNotice(HashMap<String, String> paramMap) {
		// TODO Auto-generated method stub
		sql.insert("notice.insertNotice",paramMap);
	}

	/**
	 * @location   : kr.co.ourhome.app.board.dao.BoardDao.selectNoticeView
	 * @writeDay   : 2014. 12. 4. 오후 4:24:06
	 * @overridden : @see kr.co.ourhome.app.board.dao.BoardDao#selectNoticeView(int)
	 * @Todo       :
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> selectNoticeView(int noticeSeq) {
		// TODO Auto-generated method stub
		return (HashMap<String, Object>)sql.selectOne("notice.selectNoticeView", noticeSeq);
	}

	/**
	 * @location   : kr.co.ourhome.app.board.dao.BoardDao.deleteNotice
	 * @writeDay   : 2014. 12. 5. 오후 9:52:01
	 * @overridden : @see kr.co.ourhome.app.board.dao.BoardDao#deleteNotice(int)
	 * @Todo       :
	 */
	
	@Override
	public void deleteNotice(int noticeSeq) {
		// TODO Auto-generated method stub
		sql.delete("notice.deleteNotice",noticeSeq);
	}

	/**
	 * @location   : kr.co.ourhome.app.board.dao.BoardDao.updateNotice
	 * @writeDay   : 2014. 12. 5. 오후 10:39:35
	 * @overridden : @see kr.co.ourhome.app.board.dao.BoardDao#updateNotice(java.util.HashMap)
	 * @Todo       :
	 */
	
	@Override
	public void updateNotice(HashMap<String, String> paramMap) {
		// TODO Auto-generated method stub
		sql.update("notice.updateNotice",paramMap);
	}
}
  /*
   * Copyright SmartWellness,All rights reserved.
   * (http://www.smartwellness.com)
   *
   * This software is the confidential and proprietary information
   * of SmartWellness Corp. ("Confidential Information").
   */

package com.solidskin.util;

import org.apache.ibatis.type.Alias;

/**
 * @파일명          : Board.java
 * @작성일          : 2014. 12. 4.
 * @작성자          : Administrator
 * @설명			  :
 */

@Alias("notice")
public class Notice {

	private int rnum;
	
	private int noticeSeq;
	
	private String type;
	
	private String subject;
	
	private String img;
	
	private String contents;
	
	private String regDt;

	private int hitCnt;
	
	/**
	 * @location : kr.co.ourhome.app.board.Board.getRegDt
	 * @writeDay : 2014. 12. 4. 오후 4:02:10
	 * @return   : regDt
	 * @Todo     :
	 */
	public String getRegDt() {
		return regDt;
	}

	/**
	 * @param regDt the regDt to set
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	/**
	 * @location : kr.co.ourhome.app.board.Board.getHitCnt
	 * @writeDay : 2014. 12. 4. 오후 3:53:17
	 * @return   : hitCnt
	 * @Todo     :
	 */
	public int getHitCnt() {
		return hitCnt;
	}

	/**
	 * @param hitCnt the hitCnt to set
	 */
	public void setHitCnt(int hitCnt) {
		this.hitCnt = hitCnt;
	}

	/**
	 * @location : kr.co.ourhome.app.board.Board.getRnum
	 * @writeDay : 2014. 12. 4. 오전 2:32:54
	 * @return   : rnum
	 * @Todo     :
	 */
	public int getRnum() {
		return rnum;
	}

	/**
	 * @param rnum the rnum to set
	 */
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	/**
	 * @location : kr.co.ourhome.app.board.Board.getNoticeSeq
	 * @writeDay : 2014. 12. 4. 오전 1:24:39
	 * @return   : noticeSeq
	 * @Todo     :
	 */
	public int getNoticeSeq() {
		return noticeSeq;
	}

	/**
	 * @param noticeSeq the noticeSeq to set
	 */
	public void setNoticeSeq(int noticeSeq) {
		this.noticeSeq = noticeSeq;
	}

	/**
	 * @location : kr.co.ourhome.app.board.Board.getType
	 * @writeDay : 2014. 12. 4. 오전 1:24:39
	 * @return   : type
	 * @Todo     :
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @location : kr.co.ourhome.app.board.Board.getSubject
	 * @writeDay : 2014. 12. 4. 오전 1:24:39
	 * @return   : subject
	 * @Todo     :
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @location : kr.co.ourhome.app.board.Board.getImg
	 * @writeDay : 2014. 12. 4. 오전 1:24:39
	 * @return   : img
	 * @Todo     :
	 */
	public String getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * @location : kr.co.ourhome.app.board.Board.getContents
	 * @writeDay : 2014. 12. 4. 오전 1:24:39
	 * @return   : contents
	 * @Todo     :
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

}

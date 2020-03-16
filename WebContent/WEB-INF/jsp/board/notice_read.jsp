<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.solidskin.config.Configuration" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<!DOCTYPE html >
<html lang="ko">
<head>
	<title></title>
	<link href='http://fonts.googleapis.com/css?family=Cabin+Condensed' rel='stylesheet' type='text/css'>
	<link href="/images/common/favicon.ico" rel="shortcut icon" />
	<link href="/css/reset.css" rel="stylesheet" />
	<link href="/css/board.css" rel="stylesheet" />
	<link href="/css/layout.css" rel="stylesheet" />
	<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/design.js"></script>
</head>
<body>

<div id="wrap" class="admin-wrap">
	<!-- header -->

	
	<div id="container" class="admin-container">
		<!-- leftmenu -->


		<!-- contents -->
		<div id="contents" class="admin-content">
			<!-- title-section -->
			<div class="title-section">
				<h2 class="tit1">공지사항</h2>
				<p class="para">아워홈 호스피탈리티가 전하는 다양한 소식입니다.</p>
			</div>
			<!-- //title-section -->

			<!-- page Contents -->
			<div class="article">
				<!-- board view -->
				<div class="board-view">
					<div class="view-header">
						<h3 class="title-sec"><b class="title">${noticeInfo.SUBJECT}</b></h3>
						<ul class="info-sec"> 
							<li><b>등록일 :</b> ${noticeInfo.REGDT}</li>
							<li><b>조회수 :</b> ${noticeInfo.HITCNT}</li>
						</ul>
					</div>
					<div class="view-body">
						<div class="view-body-content">
							<c:if test="${noticeInfo.IMG ne null}">
							<div class="img-area">
								<%-- <img src="<%=Configuration.conf.get("ourhome.admin.upload.url")%>${noticeInfo.IMG}" alt="공지사항 이미지" />			 --%>					
							</div>
							</c:if>
							${fn:replace(noticeInfo.CONTENTS, newLineChar, '<br/>')}
						</div>
					</div>
					<div class="other-contents">
						<dl class="prev">
							<dt>이전글</dt>
							<dd>
								<p class="text">
								<c:choose>
									<c:when test="${noticeInfo.PRENOTICESEQ == '0'}">이전글이 없습니다.</c:when>
									<c:otherwise><a href="./noticeView.do?noticeSeq=${noticeInfo.PRENOTICESEQ}">${noticeInfo.PRESUBJECT}</a></c:otherwise>
								</c:choose>
								</p>
							</dd>
						</dl>
						<dl class="next">						
							<dt>다음글 </dt>
							<dd>
								<p class="text">
								<c:choose>
									<c:when test="${noticeInfo.NEXTNOTICESEQ == '0'}">이전글이 없습니다.</c:when>
									<c:otherwise><a href="./noticeView.do?noticeSeq=${noticeInfo.NEXTNOTICESEQ}">${noticeInfo.NEXTSUBJECT}</a></c:otherwise>
								</c:choose>								
								</p>
								<!--  <span class="info"> 2014.11.11 / 601</span> -->
							</dd>
						</dl>
					</div>
				</div>
				<!-- //board view -->
				<!-- btn-area -->
				<div class="btn-area">
					<div class="left">
						<a href="./noticeForm.do?mode=modify&noticeSeq=${noticeInfo.NOTICESEQ}" class="btn-b-type2"><span>수정</span></a>
						<a href="./deleteNotice.do?noticeSeq=${noticeInfo.NOTICESEQ}" class="btn-b-type2"><span>삭제</span></a>
					</div>
					<div class="right">
						<a href="./noticeList.do" class="btn-b-type3"><span>목록</span></a>
					</div>
				</div>
				<!-- //btn-area -->
				
			</div>
			<!-- //page Contents -->
		</div>
	</div>

	<!-- footer -->
</div>

</body>
</html>

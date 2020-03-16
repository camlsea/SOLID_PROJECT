<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

	
	<script type="text/javascript">
	function fnc_search() {
		var searchKeyword = $("input[name='searchKeyword']").val();
		if(1 > searchKeyword.length) {
			alert("검색어를 입력하세요.");
			$("input[name='searchKeyword']").focus();
			return;
		}
	
		$("#actionForm").submit();
	}
	</script>			
</head>
<body>


<div id="wrap" class="wrap">
	<!-- header -->
	<!-- //header -->
	
	<!-- container -->
		<!-- 페이지맵 -->

		<!-- //페이지 맵 -->
		
		<!-- 컨텐츠 wrap -->
		<div class="contents-wrap">
			<!-- leftmenu -->


			<!-- contents -->
			<div id="contents" class="contents">
				<!-- title-section -->
				<div class="title-section">
					<h2 class="tit1">이름</h2>
					<p class="para">소식입니다.</p>
				</div>
				<!-- //title-section -->
				<!-- 본문 -->
				<div class="aticle">
           		<form id="actionForm" action="./noticeList.do" method="post">
					<!-- 검색 -->
					<div class="search-sec1 mgt66">
						<div class="total">TOTAL <strong class="num">${total_cnt}</strong></div>
						<div class="select-group">
							<select class="input-select" name="searchType">
								<option value="subject">제목</option>
								<option value="contents">제목+내용</option>
							</select>
							<input type="text" name="searchKeyword" class="text-input" title="검색어 입력" />
							<a href="javascript:fnc_search();" class="btn-search"><span>검색</span></a>
						</div>
					</div>
				</form>
					<!-- 게시판 -->
					<table class="board-list">
						<caption>공지사항</caption>
						<colgroup>
							<col style="width:10%;" />
							<col style="width:auto;" />
							<col style="width:15%;" />
							<!--<col style="width:15%;" />-->
						</colgroup>
						<thead>
							<tr>
								<th>번호</th>
								<th>내용</th>
								<th>등록일</th>
								<!-- <th>조회</th> -->
							</tr>
						</thead>
						<tbody>
						<c:if test="${total_cnt == 0}">
							<tr>
								<td colspan="4">공지사항 리스트가 존재하지 않습니다.</td>
							</tr>					
						</c:if>								
						<c:forEach var="noticeList" items="${noticeList}" varStatus="status">						
							<tr>
								<td>${noticeList.rnum}</td>
								<td class="text-left"><a href="./noticeView.do?noticeSeq=${noticeList.noticeSeq}">${noticeList.subject}</a></td>
								<td>${noticeList.regDt}</td>
								<!-- <td>${noticeList.hitCnt}</td> -->
							</tr>
						</c:forEach>
						</tbody>
					</table>
					
					<!-- 게시판 하단 -->
					<div class="board-bottom-area">
						<!-- 버튼 -->
						<div class="btn-area-right">
						<!-- 
							<a href="./noticeForm.do?mode=write" class="btn-b-type1"><span>등록</span></a>
						 -->
						</div>

						<!-- 페이지 숫자 -->
						<div class="page-num">
							<div class="inner">
							<c:if test="${total_cnt > 0}">
								${pageList}
							</c:if>
							</div>
						</div>
					</div>
					<!-- //게시판 하단 끝 -->
				
				</div>
				<!-- 본문 끝 -->
			</div>
			<!-- //contents -->
		</div>
		<!-- //컨텐츠 wrap -->
	<!-- //container -->

	<!-- footer -->
</div>

</body>
</html>
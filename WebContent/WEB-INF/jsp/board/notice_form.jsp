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

	function fnc_insert() {
		var subject = $("input[name='subject']").val();
		var type = $("input:radio[name='rdo']:checked").val();
		var uploadFile = $("input[name='uploadFile']").val();
		var upFileNm = $("#up_filenm").text();
		var contents = $("textarea#contents").val();
		var mode = $("input[name='mode']").val();
		
		if(type == undefined) {
			alert("분류를 선택하세요.");
			return;
		}
		
		if(1 > subject.length) {
			alert("제목을 입력하세요.");
			$("input[name='subject']").focus();
			return;
		}

		/*
		if(1 > upFileNm.length) {
			alert("이미지를 등록하세요.");
			return;
		}
		*/
		if(1 > contents.length) {
			alert("내용을 입력하세요.");
			return;
		}
		contents = contents.replace("\r\n","<br>");
		$("textarea#contents").val(contents);
		
		$("input[name='type']").val(type);

		if(mode == 'write'){
			$("#actionForm").attr("action", "./insertNotice.do");
		}else if(mode == 'modify'){
			$("#actionForm").attr("action", "./modifyNotice.do");			
		}
		$("#actionForm").submit();
	}

	function fnc_checkExt(file){
		
		if(file.value != ''){
			var img = file.value;
			var ext = img.substring(img.lastIndexOf(".") + 1);
			
			if(ext.toLowerCase() != "jpg"){
				alert("jpg 이미지 파일만 업로드 할 수 있습니다.");
				$("input[name='uploadFile']").val('');
				$("#up_filenm").text('');
				return;
			}
			
			$("#up_filenm").text(img);
			$("#up_btn_file_del").append("<a href=\"javascript:fnc_deleteFile();\" class=\"btn-file-del\">파일삭제</a>");
		}
		
	}	
	function fnc_deleteFile(){
		$("input[name='uploadFile']").val('');
		$("#up_filenm").text("");
		$("#up_btn_file_del").html("");
		$("input[name='imgDelYn']").val('Y');	
	}
	
	</script>	
</head>
<body>
<div id="wrap" class="admin-wrap">
	<!-- top -->

	
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
                <form id="actionForm" method="post" enctype="multipart/form-data" action="fileUpload">	
                <input type="hidden" name="mode" value="${mode}">
                <input type="hidden" name="type" value="">                
                <input type="hidden" name="noticeSeq" value="${noticeSeq}">
                <input type="hidden" name="orgFileNm" value="${noticeInfo.IMG}">
				<input type="hidden" name="imgDelYn" value="N">                
				<!-- board view -->
				<table class="board-write">
					<caption>공지사항 입력</caption>
					<colgroup>
						<col style="width:182px;" />
						<col style="width:auto;" />
					</colgroup>
					<tbody>
						<tr>
							<th>분류</th>
							<td>
								<label class="radio-label"><input type="radio" name="rdo" class="input-radio" value="G" <c:if test="${noticeInfo.TYPE == 'G'}">checked='checked'</c:if>/> <span>일반</span></label>
								<label class="radio-label"><input type="radio" name="rdo" class="input-radio" value="T" <c:if test="${noticeInfo.TYPE == 'T'}">checked='checked'</c:if>/> <span>TOP 공지</span></label>
							</td>
						</tr>
						<tr>
							<th>제목</th>
							<td>
								<label class="input-label-full"><input type="text" name="subject" class="input-text" title="제목" maxlength="100" value="${noticeInfo.SUBJECT}"/></label>
							</td>
						</tr>
						<tr>
							<th>이미지 첨부</th>
							<td>
								<div class="file-add-wrap">
									<label class="file-label">
										<input type="file" id="uploadFile" name="uploadFile" class="input-file" value="" onchange="javascript:fnc_checkExt(this);"/>
										<b class="btn-b-file"><span>찾아보기</span></b>
									</label>
									<span class="sub-text">* 실제 이미지 사이즈 적용, 파일은 jpg만 사용합니다.</span>
								</div>
								<div class="add-file-box">
									<div class="file-unit">
										<span id="up_filenm" class="name"><a href="/download.jsp?filename=${noticeInfo.IMG}">${noticeInfo.IMG}</a></span>
										<span id="up_btn_file_del">
										<c:if test="${noticeInfo.IMG ne null}">
												<a href="javascript:fnc_deleteFile();" class="btn-file-del">파일삭제</a>										
										</c:if>
										</span>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
								<textarea class="input-textarea" id="contents" name="contents" maxLength="1300" onkeyup="return textarea_maxlength(this);">${noticeInfo.CONTENTS}</textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</form>
				<!-- //board view -->
				<!-- btn-area -->
				<div class="btn-area">
					<div class="right">
						<a href="./noticeList.do" class="btn-b-type3"><span>목록</span></a>
						<a href="javascript:fnc_insert();" class="btn-b-type1"><span>등록</span></a>
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

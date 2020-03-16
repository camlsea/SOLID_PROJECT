package com.solidskin.util;

/**
 * @class PageMgr.java
 * @author jklee
 * @since 2014. 12. 03
 * @description : 
 */
public class PageMgr {
	
	private String pageNavi;
	
	/** 현재 페이지 */
	private int currentPage;

	/** 총 데이터 카운트 */
	private int totalCount;

	/** 한 페이지당 보여질 데이터 사이즈 */
	private int pageSize;

	/** 페이징 한 블럭당 보여질 페이지 넘버 수 */
	private int blockSize = 10;
	
	/** 이전 페이지 */
	private String prevPage;

	/** 다음 페이지 */
	private String nextPage;

	/** 이전 10페이지 넘버 */
	private String jumpPrevPage;

	/** 다음 10페이지 넘버 */
	private String jumpNextPage;
	
	/** 총 페이지 수 */
	private String pageCount;
	
	private String urlPath;
	
	private String brandno;
	
	public PageMgr(int total_count, int page_size, int page_num, String path) {
		this.currentPage = page_num;
		this.totalCount = total_count;
		this.pageSize = page_size;
		this.urlPath = path;
		maker();
	}
	
	private void maker() {
		pageNavi = "";
		
		// 총 페이지 수
		int pageCount = (int) Math.ceil((double)totalCount / pageSize);
		
		// 시작 Row와 끝 Row
		int startRow = (currentPage - 1) * pageSize;
		int endRow = startRow + pageSize - 1;
		endRow = endRow < totalCount ? endRow : totalCount - 1;

		// 네비게이션의 시작 번호와 끝번호
		int startPageNum = (int) (Math.floor((currentPage - 1) / blockSize) * blockSize + 1);
		int endPageNum = startPageNum + blockSize - 1;
		endPageNum = endPageNum > pageCount ? pageCount : endPageNum;
		
		// 이전 10개
		int prevPage = blockSize < startPageNum ? startPageNum - 1 : -1;
		int nextPage = pageCount > endPageNum ? endPageNum + 1 : -1;

		// 맨 처음, 맨 끝
		int jumpPrevPage = 1;
		int jumpNextPage = pageCount;

		this.prevPage = prevPage > -1 ? Integer.toString(prevPage) : null;
		this.nextPage = nextPage > -1 ? Integer.toString(nextPage) : null;
		this.pageCount = pageCount >= endPageNum ? Integer.toString(pageCount) : null;
		this.jumpPrevPage = jumpPrevPage > -1 ? Integer.toString(jumpPrevPage) : null;
		this.jumpNextPage = jumpNextPage > -1 ? Integer.toString(jumpNextPage) : null;
		
		String pageNum = "";
		
		// 맨 처음으로 가기 버튼 셋팅
		if (currentPage != 1) {
			pageNavi = pageNavi + "\n<a class=\"first\" href=\"javascript:void(0);\" onclick=\"location='"+urlPath+"&page_no=1';\"><u>처음으로 이동</u></a>";
		} else {
			pageNavi = pageNavi + "\n<a class=\"first\" href=\"javascript:void(0);\"><u>처음으로 이동</u></a>";
		}
		
		// 이전 10개 버튼 셋팅
		if (this.prevPage != null) {
			pageNavi = pageNavi + "<a class=\"prev\" href=\"javascript:void(0);\" onclick=\"location='"+urlPath+"&page_no="+this.prevPage+"';\"><u>이전으로 이동</u></a>\n";
		} else {
			pageNavi = pageNavi + "<a class=\"prev\" href=\"javascript:void(0);\"><u>이전으로 이동</u></a>\n";
		}

		// 페이지 넘버 셋팅
		for (int pageNo = startPageNum; pageNo <= endPageNum; pageNo++) {
			if (currentPage == pageNo) {
				pageNum = pageNum + "<span class=\"current\" href=\"javascript:void(0);\">"+pageNo+"</span>\n";
			} else {
				pageNum  = pageNum + "<a href=\"javascript:void(0);\" onclick=\"location='"+urlPath+"&page_no="+pageNo+"';\">"+pageNo+"</a>\n";
				//System.out.println(pageNum);
			}
		}
		pageNavi += pageNum;

		// 다음 10개 버튼 셋팅
		if (this.nextPage != null) {
			pageNavi = pageNavi + "<a class=\"next\" href=\"javascript:void(0);\" onclick=\"location='"+urlPath+"&page_no="+this.nextPage+"';\"><u>다음으로 이동</u></a>\n";
		} else {
			pageNavi = pageNavi + "<a class=\"next\" href=\"javascript:void(0);\"><u>다음으로 이동</u></a>\n";
		}

		// 맨 뒤로 가기 버튼 셋팅
		if (pageCount != currentPage) {
			if(pageCount != 0) {
				if (this.nextPage != null) {
					pageNavi = pageNavi + "<a class=\"last\" href=\"javascript:void(0);\" onclick=\"location='"+urlPath+"&page_no="+this.nextPage+"';\"><u>끝으로 이동</u></a>\n";					
				}else{
					pageNavi = pageNavi + "<a class=\"last\" href=\"javascript:void(0);\" onclick=\"location='"+urlPath+"&page_no="+this.pageCount+"';\"><u>끝으로 이동</u></a>\n";
				}
			} else {
				pageNavi = pageNavi + "<a class=\"last\" href=\"javascript:void(0);\"><u>끝으로 이동</u></a>\n";
			}
		} else {
			pageNavi = pageNavi + "<a class=\"last\" href=\"javascript:void(0);\"><u>끝으로 이동</u></a>\n";
		}
		
	}
	
	public String getPageList() {
		return pageNavi;
	}

}
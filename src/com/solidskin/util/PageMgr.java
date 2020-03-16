package com.solidskin.util;

/**
 * @class PageMgr.java
 * @author jklee
 * @since 2014. 12. 03
 * @description : 
 */
public class PageMgr {
	
	private String pageNavi;
	
	/** ���� ������ */
	private int currentPage;

	/** �� ������ ī��Ʈ */
	private int totalCount;

	/** �� �������� ������ ������ ������ */
	private int pageSize;

	/** ����¡ �� ���� ������ ������ �ѹ� �� */
	private int blockSize = 10;
	
	/** ���� ������ */
	private String prevPage;

	/** ���� ������ */
	private String nextPage;

	/** ���� 10������ �ѹ� */
	private String jumpPrevPage;

	/** ���� 10������ �ѹ� */
	private String jumpNextPage;
	
	/** �� ������ �� */
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
		
		// �� ������ ��
		int pageCount = (int) Math.ceil((double)totalCount / pageSize);
		
		// ���� Row�� �� Row
		int startRow = (currentPage - 1) * pageSize;
		int endRow = startRow + pageSize - 1;
		endRow = endRow < totalCount ? endRow : totalCount - 1;

		// �׺���̼��� ���� ��ȣ�� ����ȣ
		int startPageNum = (int) (Math.floor((currentPage - 1) / blockSize) * blockSize + 1);
		int endPageNum = startPageNum + blockSize - 1;
		endPageNum = endPageNum > pageCount ? pageCount : endPageNum;
		
		// ���� 10��
		int prevPage = blockSize < startPageNum ? startPageNum - 1 : -1;
		int nextPage = pageCount > endPageNum ? endPageNum + 1 : -1;

		// �� ó��, �� ��
		int jumpPrevPage = 1;
		int jumpNextPage = pageCount;

		this.prevPage = prevPage > -1 ? Integer.toString(prevPage) : null;
		this.nextPage = nextPage > -1 ? Integer.toString(nextPage) : null;
		this.pageCount = pageCount >= endPageNum ? Integer.toString(pageCount) : null;
		this.jumpPrevPage = jumpPrevPage > -1 ? Integer.toString(jumpPrevPage) : null;
		this.jumpNextPage = jumpNextPage > -1 ? Integer.toString(jumpNextPage) : null;
		
		String pageNum = "";
		
		// �� ó������ ���� ��ư ����
		if (currentPage != 1) {
			pageNavi = pageNavi + "\n<a class=\"first\" href=\"javascript:void(0);\" onclick=\"location='"+urlPath+"&page_no=1';\"><u>ó������ �̵�</u></a>";
		} else {
			pageNavi = pageNavi + "\n<a class=\"first\" href=\"javascript:void(0);\"><u>ó������ �̵�</u></a>";
		}
		
		// ���� 10�� ��ư ����
		if (this.prevPage != null) {
			pageNavi = pageNavi + "<a class=\"prev\" href=\"javascript:void(0);\" onclick=\"location='"+urlPath+"&page_no="+this.prevPage+"';\"><u>�������� �̵�</u></a>\n";
		} else {
			pageNavi = pageNavi + "<a class=\"prev\" href=\"javascript:void(0);\"><u>�������� �̵�</u></a>\n";
		}

		// ������ �ѹ� ����
		for (int pageNo = startPageNum; pageNo <= endPageNum; pageNo++) {
			if (currentPage == pageNo) {
				pageNum = pageNum + "<span class=\"current\" href=\"javascript:void(0);\">"+pageNo+"</span>\n";
			} else {
				pageNum  = pageNum + "<a href=\"javascript:void(0);\" onclick=\"location='"+urlPath+"&page_no="+pageNo+"';\">"+pageNo+"</a>\n";
				//System.out.println(pageNum);
			}
		}
		pageNavi += pageNum;

		// ���� 10�� ��ư ����
		if (this.nextPage != null) {
			pageNavi = pageNavi + "<a class=\"next\" href=\"javascript:void(0);\" onclick=\"location='"+urlPath+"&page_no="+this.nextPage+"';\"><u>�������� �̵�</u></a>\n";
		} else {
			pageNavi = pageNavi + "<a class=\"next\" href=\"javascript:void(0);\"><u>�������� �̵�</u></a>\n";
		}

		// �� �ڷ� ���� ��ư ����
		if (pageCount != currentPage) {
			if(pageCount != 0) {
				if (this.nextPage != null) {
					pageNavi = pageNavi + "<a class=\"last\" href=\"javascript:void(0);\" onclick=\"location='"+urlPath+"&page_no="+this.nextPage+"';\"><u>������ �̵�</u></a>\n";					
				}else{
					pageNavi = pageNavi + "<a class=\"last\" href=\"javascript:void(0);\" onclick=\"location='"+urlPath+"&page_no="+this.pageCount+"';\"><u>������ �̵�</u></a>\n";
				}
			} else {
				pageNavi = pageNavi + "<a class=\"last\" href=\"javascript:void(0);\"><u>������ �̵�</u></a>\n";
			}
		} else {
			pageNavi = pageNavi + "<a class=\"last\" href=\"javascript:void(0);\"><u>������ �̵�</u></a>\n";
		}
		
	}
	
	public String getPageList() {
		return pageNavi;
	}

}
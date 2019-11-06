package com.pqc.utils;

public class PageTool {

	private int pageSize;//总页数
	private int totalCount;//总数据量
	private int currentPage;//当前页码
	private int lastPage;//上一页
	private int nextPage;//下一页
	private int pageCount;//每页数据量
	private int startIndex;//起始下标
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	public PageTool(int totalCount, String currentPage) {
		super();
		this.totalCount = totalCount;//初始化总数据量
		pageCount=5;//初始化每页数据量
		//初始化当前页码
		initialCurrentPage(currentPage);
		//初始化总页数
		initialPageSize();
		//初始化上一页
		initialLastPage();
		//初始化下一页
		initivalNextPage();
		//初始化起始下标
		initivalStartIndex();
		
	}
	private void initivalStartIndex() {
		startIndex = (currentPage-1)*pageCount;
	}
	private void initivalNextPage() {
		if(currentPage==pageSize) {
			nextPage=pageSize;
		}else {
			nextPage=currentPage+1;
		}
	}
	private void initialLastPage() {
		if(currentPage==1) {
			lastPage=1;
		}else {
			lastPage=currentPage-1;
		}
	}
	private void initialPageSize() {
		pageSize=totalCount/pageCount+(totalCount%pageCount==0 ? 0 : 1 );
	}
	private void initialCurrentPage(String currentPage) {
		/*
	 	因为当前页码是从前端页面传过来的，那么刚进入user_list.jsp时，没法传入当前页码值，那么当前页码就是第一页
	 	其余情况下，该是第几页就是第几页
	 */
		if(currentPage == null || "".equals(currentPage)) {
			this.currentPage=1;
			
		}else {
			this.currentPage=Integer.valueOf(currentPage);
		}
		
	}
	
	
}







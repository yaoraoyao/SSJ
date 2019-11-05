package cn.itsource.ssj.query;


public class BaseQuery {

	//当前显示第几页 默认显示第一页【主要用来接收前端传递的请求参数】
	private Integer pageNo = 1;
	
	//每页显示多少行，默认10行【主要用来接收前端传递的请求参数】
	private Integer pageSize = 10;
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getBeginIndex(){
		return (pageNo-1)*pageSize;
	}
}

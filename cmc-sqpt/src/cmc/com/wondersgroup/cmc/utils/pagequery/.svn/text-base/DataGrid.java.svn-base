package com.wondersgroup.cmc.utils.pagequery;

import java.util.Collection;

/**
 * 
 * @author jacky
 * @version $Revision$ 2013-12-11
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class DataGrid<R> {
	private Collection<R> data;     // 当前数据结果
	private int total = 0;  // 总条数, 默认为0
	private int pageSize = 10;   // 每页记录数
	private int pageIndex = 0;   // 当前页码，从0开始，0为第一页
	
	public Collection<R> getData() {
		return data;
	}
	
	public void setData(Collection<R> data) {
		this.data = data;
	}
	
	public int getTotal() {
		return this.total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPageIndex() {
		return pageIndex;
	}
	
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
}

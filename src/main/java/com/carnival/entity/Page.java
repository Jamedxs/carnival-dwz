package com.carnival.entity;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.carnival.entity.Page.OrderDirection;

/**
 * @author dengxusheng
 * @create 2017年3月5日 下午9:17:30
 */
@SuppressWarnings("all")
@Scope("prototype")
public class Page {

	// 排序方式
	public enum OrderDirection {
		asc, desc
	}

	private String orderField;// 排序字段
	private OrderDirection orderDirection = OrderDirection.desc;// 排序方式

	private int pageNo = 1;// 页码，默认是第一页
	private int pageSize = 20;// 每页显示的记录数，默认是15
	private int totalRecord = 0;// 总记录数
	private int totalPage;// 总页数
	private List results;// 对应的当前页记录
	private HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
			.getRequestAttributes()).getRequest();
	private Map<String, String> params = new HashMap<String, String>();// 其他的参数我们把它分装成一个Map对象

	public Page() {
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			String notInclude = "_,pageNum,orderField,orderDirection,numPerPage,totalCount";// 参数，排除分页等参数
			if (notInclude.indexOf(paraName) == -1) {
				params.put(paraName, request.getParameter(paraName).trim());
			}
		}
		if (request.getParameter("pageNum") != null) {
			this.setOrderField(request.getParameter("orderField"));
			this.setOrderDirection(request.getParameter("orderDirection")
					.equals("desc") ? OrderDirection.desc : OrderDirection.asc);
			this.setPageNo(Integer.parseInt(request.getParameter("pageNum")));
			this.setPageSize(Integer.parseInt(request
					.getParameter("numPerPage")));
			this.setTotalRecord(Integer.parseInt(request
					.getParameter("totalRecord")));
		}
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		// 在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
		int totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize
				: totalRecord / pageSize + 1;
		this.setTotalPage(totalPage);
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List getResults() {
		return results;
	}

	public void setResults(List results) {
		this.results = results;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest().setAttribute("params", params);
		this.params = params;
	}

	public String getOrderField() {

		return orderField;
	}

	public void setOrderField(String orderField) {

		this.orderField = orderField;
	}

	public OrderDirection getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(OrderDirection orderDirection) {
		this.orderDirection = orderDirection;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [pageNo=").append(pageNo).append(", pageSize=")
				.append(pageSize).append(", results=").append(results)
				.append(", totalPage=").append(totalPage)
				.append(", totalRecord=").append(totalRecord).append("]");
		return builder.toString();
	}

}

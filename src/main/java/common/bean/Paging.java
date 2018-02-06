package common.bean;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import common.constant.GlobalConstant;
import common.util.JavaBeanUtils;

/**
 * 封装分页数据
 * 
 * @author bianj
 * @version 1.0.0 2017-07-15
 */
public class Paging extends ToStringBean {
	private static final long serialVersionUID = -1501666908248005445L;

	/** 分页大小 */
	private Integer pageSize = GlobalConstant.DEFAULT_PAGE_SIZE;

	/** 当前页 */
	private Integer page = 1;

	/** 总记录数 */
	private Long total = 0l;

	/** 排序字段 */
	private String sort;

	/** 排序方式，只能是 ASC 或者 DESC */
	private String order;

	/** 查询参数 */
	private Map<String, ?> parameters;

	/** 当前这一页的记录集 */
	private List<?> rows;

	/**
	 * 根据分页大小和当前页码，获取起始行
	 * 
	 * @return 查询起始行
	 */
	public Integer getBeginCount() {
		int beginCount = 0;
		if (page > 0 && pageSize > 0) {
			beginCount = pageSize * (page - 1);
		}
		return beginCount;
	}

	/**
	 * 获取分页大小
	 * 
	 * @return 分页大小
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * 设置分页大小
	 * 
	 * @param pageSize
	 *            分页大小
	 */
	public void setPageSize(Integer pageSize) {
		if (pageSize != null && pageSize > 0) {
			this.pageSize = pageSize;
		}
	}

	/** 获取当前页 */
	public Integer getPage() {
		return page;
	}

	/**
	 * 设置当前页
	 * 
	 * @param page
	 *            页码
	 */
	public void setPage(Integer page) {
		if (page != null && page > 0) {
			this.page = page;
		}
	}

	/**
	 * 获取总记录数
	 * 
	 * @return 总记录数
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * 设置总记录数
	 * 
	 * @param total
	 *            总记录数
	 */
	public void setTotal(Long total) {
		if (total != null && total > 0) {
			this.total = total;
		}
	}

	/**
	 * 获取排序字段
	 * 
	 * @return 排序字段
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * 设置排序字段
	 * 
	 * @param sort
	 *            排序字段
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * 获取排序方式
	 * 
	 * @return 排序方式
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 设置排序方式
	 * 
	 * @param order
	 *            排序方式
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * 获取查询参数
	 * 
	 * @return 查询参数 Map
	 */
	public Map<String, ?> getParameters() {
		return parameters;
	}

	/**
	 * 设置查询参数
	 * 
	 * @param parameters
	 *            查询参数 Map
	 */
	public void setParameters(Map<String, ?> parameters) {
		this.parameters = parameters;
	}

	public void setStringParameters(Object javaBean) {
		this.parameters = JavaBeanUtils.beanToStringMap(javaBean);
	}

	public void setObjectParameters(Object javaBean) {
		this.parameters = JavaBeanUtils.beanToObjectMap(javaBean);
	}

	/**
	 * 获取当前页数据
	 * 
	 * @return 当前页数据
	 */
	public List<?> getRows() {
		return rows;
	}

	/**
	 * 设置当前页数据
	 * 
	 * @param rows
	 *            当前页数据
	 */
	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	/**
	 * 清空当前页数据
	 */
	public void clearRows() {
		if (CollectionUtils.isNotEmpty(rows)) {
			rows.clear();
		}
	}
}

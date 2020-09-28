package com.hy.demo.common.base.util;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class QueryPage implements Serializable {
	
	// 定义默认每页中显示记录数
	public static final long DEFAULT_PAGE_SIZE = 10;
	
	// 每页记录数
	@Builder.Default
	private long pageSize = DEFAULT_PAGE_SIZE;

	// 总页数
	@Builder.Default
	private long pageCount = 1;

	// 总记录数
	@Builder.Default
	private long recordCount = 0;

	// 目标页
	@Builder.Default
	private long targetPage = 1;


	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
		
		// 总页数
		var pageCount = recordCount / pageSize;
		if (recordCount % pageSize > 0) {
			pageCount++;
		}
		this.pageCount = pageCount;
	}

	public void setTargetPage(long targetPage) {
		if (targetPage <= 0) {
			targetPage = 1;
		}
		this.targetPage = targetPage;
	}

	public long getOffset() {
		return (targetPage - 1) * pageSize;
	}
	
	public long getLimit() {
		return pageSize;
	}
}
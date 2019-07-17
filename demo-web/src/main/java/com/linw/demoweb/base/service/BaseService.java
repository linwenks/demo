package com.linw.demoweb.base.service;

import java.util.List;

import com.linw.demoweb.base.util.QueryPage;

public interface BaseService<BO> {
	
	// 增加
	public final static String INSERT = "insert";
	
	// 增加 批量
	public final static String INSERT_BATCH = "insert_batch";
	
	// 增加 选择参数
	public final static String INSERT_SEL = "insert_sel";
	
	// 增加 选择参数 批量
	public final static String INSERT_SEL_BATCH = "insert_sel_batch";
	
	
	// 修改
	public final static String UPDATE = "update";
	
	// 修改 选择参数
	public final static String UPDATE_SEl = "update_sel";
	
	// 修改 根据主键
	public final static String UPDATE_PK = "update_pk";
	
	// 修改 根据主键 选择参数 
	public final static String UPDATE_PK_SEl = "update_pk_sel";
	
	// 修改 根据主键 控制版本
	public final static String UPDATE_PK_VER = "update_pk_ver";
	
	// 修改 根据主键 选择参数 控制版本
	public final static String UPDATE_PK_SEl_VER = "update_pk_sel_ver";
	
	
	// 逻辑删除 根据主键 
	public final static String DELETE_PK_LOGIC = "delete_pk_logic";
	
	// 逻辑删除 根据主键 控制版本
	public final static String DELETE_PK_VER_LOGIC = "delete_pk_ver_logic";
	
	
	// 删除
	public final static String DELETE = "delete";
	
	// 删除 根据主键 
	public final static String DELETE_PK = "delete_pk";
	
	// 删除 根据主键 控制版本
	public final static String DELETE_PK_VER = "delete_pk_ver";
	
	Object exe(String cmd, Object obj);
	
	/**
	 * 查询 by id
	 * @param id
	 * @return
	 */
	BO selectById(Long id);
	
	/**
	 * 查询 one
	 * @param bo
	 * @return
	 */
	BO selectOne(BO bo);
	
	/**
	 * 查询 数量
	 * @param bo
	 * @return
	 */
	Long selectCount(BO bo);
	
	/**
	 * 查询 list
	 * @param queryPage
	 * @param bo
	 * @return
	 */
	List<BO> selectList(QueryPage queryPage, BO bo);
}
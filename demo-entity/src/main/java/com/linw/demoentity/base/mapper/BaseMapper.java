package com.linw.demoentity.base.mapper;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseMapper<T, E> {

	public long countByExample(E example);

	public int deleteByExample(E example);

	public int deleteByPrimaryKey(Long id);

	public int insert(T record);

	public int insertSelective(T record);

	public List<T> selectByExample(E example);

	public T selectByPrimaryKey(Long id);

	public int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

	public int updateByExample(@Param("record") T record, @Param("example") E example);

	public int updateByPrimaryKeySelective(T record);

	public int updateByPrimaryKey(T record);

	public int insertBatch(@Param("recordColl") Collection<T> recordColl);

	public int insertBatchSel(@Param("recordColl") Collection<T> recordColl);

	public T selectByExampleForOne(E example);

	public int updateByPrimaryKeySelectiveVer(T record);

	public int updateByPrimaryKeyVer(T record);
}
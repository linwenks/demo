package com.linw.demoentity.base.mapper;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.repository.CrudRepository;

public interface BaseMapper<DO, C> extends CrudRepository<DO, Long> {

	long countByExample(C example);

	int deleteByExample(C example);

	int deleteByPrimaryKey(Long id);

	int insert(DO record);

	int insertSelective(DO record);

	List<DO> selectByExample(C example);

	DO selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") DO record, @Param("example") C example);

	int updateByExample(@Param("record") DO record, @Param("example") C example);

	int updateByPrimaryKeySelective(DO record);

	int updateByPrimaryKey(DO record);

	int insertBatch(@Param("recordColl") Collection<DO> recordColl);

	int insertBatchSel(@Param("recordColl") Collection<DO> recordColl);

	DO selectByExampleForOne(C example);

	int updateByPrimaryKeySelectiveVer(DO record);

	int updateByPrimaryKeyVer(DO record);
}
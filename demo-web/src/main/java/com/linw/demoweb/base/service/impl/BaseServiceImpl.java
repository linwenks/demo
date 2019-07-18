package com.linw.demoweb.base.service.impl;

import static com.linw.demoweb.base.constant.MsgEnum.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.linw.demoentity.base.entity.BaseEntity;
import com.linw.demoentity.base.mapper.BaseMapper;
import com.linw.demoweb.base.exception.AppException;
import com.linw.demoweb.base.service.BaseService;
import com.linw.demoweb.base.util.QueryPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

@Slf4j
@SuppressWarnings("unchecked")
public abstract class BaseServiceImpl<DO extends BaseEntity, BO, C> implements BaseService<BO> {
	
	private static final String orderBy = " id desc ";

	private String simpleName;
	
	private Class<DO> classDO;
	private Class<BO> classBO;
	private Class<C> classCriteria;
	
	public BaseServiceImpl() {
		simpleName = this.getClass().getSimpleName();
		
		var types = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();

		classDO = (Class<DO>) types[0];
		classBO = (Class<BO>) types[1];
		classCriteria = (Class<C>) types[2];
	}
	
	public abstract BaseMapper<DO, C> mapper();
	
	private BO castBO(DO obj) {
		if (obj != null) {
			try {
				BO bo = classBO.getDeclaredConstructor().newInstance();
				BeanUtils.copyProperties(obj, bo);
				return bo;
			} catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
				log.error(" {} error ", simpleName, e);
			}
		}
		return null;
	}

	/**
	 * 查询 by id
	 * @param id
	 * @return
	 */
	@Override
	public BO selectById(Long id) {
		DO bo = null;
		try {
			bo = mapper().selectByPrimaryKey(id);
		} catch (Exception e) {
			log.error(" {} error ", simpleName, e);
		}
		return castBO(bo);
	}
	
	@Override
	public BO selectOne(BO bean) {
		DO bo = null;
		try {
			C criteria = classCriteria.getDeclaredConstructor().newInstance();
			Object criteriaDetail = classCriteria.getMethod("createCriteria", (Class<?>[]) null).invoke(criteria, (Object[]) null);
			where(criteriaDetail, bean);
			bo = mapper().selectByExampleForOne(criteria);
		} catch (Exception e) {
			log.error(" {} error ", simpleName, e);
		}
		return castBO(bo);
	}
	
	@Override
	public Long selectCount(BO bean) {
		var count = 0L;
		try {
			C criteria = classCriteria.getDeclaredConstructor().newInstance();
			Object criteriaDetail = classCriteria.getMethod("createCriteria", (Class<?>[]) null).invoke(criteria, (Object[]) null);
			where(criteriaDetail, bean);
			count = mapper().countByExample(criteria);
		} catch (Exception e) {
			log.error(" {} error ", simpleName, e);
		}
		return count;
	}
	
	@Override
	public List<BO> selectList(QueryPage queryPage, BO bo) {
		List<DO> listDO = null;
		List<BO> listBO = null;
		
		Integer limitStart = null;
		Integer limitEnd = null;
		if (queryPage != null) {
			limitStart = queryPage.getLimitStart();
			limitEnd = queryPage.getLimitEnd();
		}
		try {
			C criteria = classCriteria.getDeclaredConstructor().newInstance();
			String orderByTmp = orderBy;
			if (bo != null) {
				Object orderBy = bo.getClass().getMethod("getOrderBy", (Class<?>[]) null).invoke(bo, (Object[]) null);
				if (orderBy != null) {
					orderByTmp = orderBy.toString();
				}
			}
			classCriteria.getMethod("setOrderByClause", new Class[]{String.class}).invoke(criteria, orderByTmp);
			if (queryPage != null) {
				classCriteria.getMethod("setLimitStart", new Class[]{Integer.class}).invoke(criteria, limitStart);
				classCriteria.getMethod("setLimitEnd", new Class[]{Integer.class}).invoke(criteria, limitEnd);
			}
			Object criteriaDetail = classCriteria.getMethod("createCriteria", (Class<?>[]) null).invoke(criteria, (Object[]) null);
			where(criteriaDetail, bo);
			listDO = mapper().selectByExample(criteria);
		} catch (Exception e) {
			log.error(" {} error ", simpleName, e);
		}
		
		if (!CollectionUtils.isEmpty(listDO)) {
			if (queryPage != null) {
				long recordCount = this.selectCount(bo);
				queryPage.setRecordCount(recordCount);
			}
			listBO = listDO.stream().map(this::castBO).collect(Collectors.toList());
		}
		return listBO;
	}
	
	protected void where(Object criteria, BO bo) {
	}
	
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case INSERT: return insert((BO) obj);
			case INSERT_BATCH: return insertBatch((Collection<BO>) obj);
			case INSERT_SEL: return insertSel((BO) obj);
			case INSERT_SEL_BATCH: return insertBatchSel((Collection<BO>) obj);
			case UPDATE_PK: return updatePk((BO) obj);
			case UPDATE_PK_SEl: return updatePkSel((BO) obj);
			case UPDATE_PK_VER: return updatePkVer((BO) obj);
			case UPDATE_PK_SEl_VER: return updatePkSelVer((BO) obj);
			case DELETE: return deleteMyBatis((BO) obj);
			case DELETE_PK: return deletePk((Long) obj);
			default: break;
			}
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			AppException.toThrow(e);
		}
		return null;
	}
	
	private int insert(BO bo) {
		var row = 0;
		try {
			row = mapper().insert((DO) bo);
		} catch (Exception e) {
			log.error(" {} error ", simpleName, e);
		}
		if (row == 0) AppException.toThrow(MSG_00010);
		return row;
	}

	private int insertSel(BO bo) {
		var row = 0;
		try {
			row = mapper().insertSelective((DO) bo);
		} catch (Exception e) {
			log.error(" {} error ", simpleName, e);
		}
		if (row == 0) AppException.toThrow(MSG_00010);
		return row;
	}

	private int insertBatch(Collection<BO> boColl) {
		var row = 0;
		try {
			row = mapper().insertBatch((Collection<DO>) boColl);
		} catch (Exception e) {
			log.error(" {} error ", simpleName, e);
		}
		if (row == 0) AppException.toThrow(MSG_00010);
		return row;
	}

	private int insertBatchSel(Collection<BO> boColl) {
		var row = 0;
		try {
			row = mapper().insertBatchSel((Collection<DO>) boColl);
		} catch (Exception e) {
			log.error(" {} error ", simpleName, e);
		}
		if (row == 0) AppException.toThrow(MSG_00010);
		return row;
	}

	private int updatePk(BO bo) {
		if (bo == null) AppException.toThrow(MSG_00003);
		var row = 0;
		try {
			row = mapper().updateByPrimaryKey((DO) bo);
		} catch (Exception e) {
			log.error(" {} error ", simpleName, e);
		}
		if (row == 0) AppException.toThrow(MSG_00010);
		return row;
	}

	private int updatePkSel(BO bo) {
		if (bo == null) AppException.toThrow(MSG_00003);
		var row = 0;
		try {
			row = mapper().updateByPrimaryKeySelective((DO) bo);
		} catch (Exception e) {
			log.error(" {} error ", simpleName, e);
		}
		if (row == 0) AppException.toThrow(MSG_00010);
		return row;
	}

	private int updatePkVer(BO bo) {
		if (bo == null) AppException.toThrow(MSG_00003);
		var row = 0;
		try {
			row = mapper().updateByPrimaryKeyVer((DO) bo);
		} catch (Exception e) {
			log.error(" {} error ", simpleName, e);
		}
		if (row == 0) AppException.toThrow(MSG_00010);
		return row;
	}

	private int updatePkSelVer(BO bo) {
		if (bo == null) AppException.toThrow(MSG_00003);
		var row = 0;
		try {
			row = mapper().updateByPrimaryKeySelectiveVer((DO) bo);
		} catch (Exception e) {
			log.error(" {} error ", simpleName, e);
		}
		if (row == 0) AppException.toThrow(MSG_00010);
		return row;
	}

	private int deleteMyBatis(BO bo) {
		if (bo == null) AppException.toThrow(MSG_00003);
		var row = 0;
		try {
			C criteria = classCriteria.getDeclaredConstructor().newInstance();
			Object criteriaDetail = classCriteria.getMethod("createCriteria", (Class<?>[]) null).invoke(criteria, (Object[]) null);
			where(criteriaDetail, bo);
			row = mapper().deleteByExample(criteria);
		} catch (Exception e) {
			log.error(" {} error ", simpleName, e);
		}
		if (row == 0) AppException.toThrow(MSG_00010);
		return row;
	}

	private int deletePk(Long id) {
		if (id == null) AppException.toThrow(MSG_00003);
		var row = 0;
		try {
			row = mapper().deleteByPrimaryKey(id);
		} catch (Exception e) {
			log.error(" {} error ", simpleName, e);
		}
		if (row == 0) AppException.toThrow(MSG_00010);
		return row;
	}
}
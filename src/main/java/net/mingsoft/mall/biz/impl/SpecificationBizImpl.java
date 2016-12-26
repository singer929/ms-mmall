package net.mingsoft.mall.biz.impl;

import java.util.List;

import org.aspectj.weaver.ast.Var;
import org.hamcrest.core.IsNot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.util.PageUtil;

import net.mingsoft.mall.biz.ISpecificationBiz;

import net.mingsoft.mall.dao.ISpecificationDao;
import net.mingsoft.mall.entity.SpecificationEntity;

/**
 * 
 * 
 * <p>
 * <b>铭飞MS平台</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 * 
 * <p>
 * Company:景德镇铭飞科技有限公司
 * </p>
 * 
 * @author 王敏
 * 
 * @version 300-001-001
 * 
 *          <p>
 *          版权所有 铭飞科技
 *          </p>
 * 
 *          <p>
 *          Comments:商品规格管理业务处理层实现类 || 继承IBaseBiz业务处理层||实现ISpecificationsBiz业务层接口
 *          </p>
 * 
 *          <p>
 *          Create Date:2014-7-14
 *          </p>
 * 
 *          <p>
 *          Modification history:
 *          </p>
 */
@Service("SpecificationBizImpl")
public class SpecificationBizImpl extends BaseBizImpl implements ISpecificationBiz{
	
	/**
	 * 商品规格数据层Dao
	 */
	@Autowired
	private ISpecificationDao specDao;
	
	@Override
	protected IBaseDao getDao() {
		return specDao;
	}

	@Override
	public List<SpecificationEntity> queryPageByAppId(int appId, PageUtil page) {
		
		return specDao.queryByAppId(appId, 0, 0);
	}

	/**
	 * 根据appId查询规格总数
	 * @param appId 应用ID
	 * @return 规格总数
	 */
	@Override
	public int countByAppId(int appId) {
		int count = specDao.countByAppId(appId);
		return count;
	}

	@Override
	public List<SpecificationEntity> queryByProductId(int productId) {
		
		List<SpecificationEntity> list = specDao.queryByProductId(productId);
		return list;
	}
	
	@Override
	public void saveSpecificationEntities (List<SpecificationEntity> list) {
		List<SpecificationEntity> dbList = specDao.queryAll();
		
		for (SpecificationEntity se : list){
			boolean isInDb = false;
			for (SpecificationEntity dbSe : dbList){
				if (dbSe.getName().equals(se.getName())){
					isInDb = true;
					break;
				}
			}
			if (isInDb) continue;
			// 不在DB中 插入数据
			specDao.saveEntity(se);
		}
	}
}

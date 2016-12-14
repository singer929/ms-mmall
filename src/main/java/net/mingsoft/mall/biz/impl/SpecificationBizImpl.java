package net.mingsoft.mall.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.util.PageUtil;

import net.mingsoft.mall.biz.ISpecificationBiz;
import net.mingsoft.mall.biz.ISpecificationsBiz;
import net.mingsoft.mall.dao.ISpecificationDao;
import net.mingsoft.mall.dao.ISpecificationsDao;
import net.mingsoft.mall.entity.SpecificationEntity;
import net.mingsoft.mall.entity.SpecificationsEntity;

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
 *          Comments:商品规格管理业务处理层实现类 || 继承IBaseBiz业务处理层||实现ISpecificationBiz业务层接口
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
@Service("specificationBizImpl")
public class SpecificationBizImpl extends BaseBizImpl implements ISpecificationBiz{
	
	private ISpecificationDao specDao;

	@Override
	protected IBaseDao getDao() {
		
		return specDao;
	}

	@Override
	public List<SpecificationEntity> queryPageByAppId(int appId, PageUtil page) {
		
		List<SpecificationEntity> list = specDao.queryByAppId(appId, 0, 0);		// TODO begin end 临时的
		return list;
	}

	@Override
	public int countByAppId(int appId) {
		
		int count = 
		return 0;
	}
	
	

}

package net.mingsoft.mall.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.util.PageUtil;

import net.mingsoft.mall.biz.IPromotionBiz;
import net.mingsoft.mall.dao.IPromotionDao;
import net.mingsoft.mall.entity.PromotionEntity;

/**
 * 
 *  <p>
 * <b>限时促销</b>
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 * 
 * <p>
 * Company:景德镇铭飞科技有限公司
 * </p>
 * 
 * @author 郭鹏辉
 * 
 * @version 140-000-000
 * 
 *          <p>
 *          版权所有 铭飞科技
 *          </p>
 *
 *         @ClassName: PromotionBizImpl
 *
 *          <p>
 *          Comments:  限时抢购业务逻辑层实现类 || 继承BasicBizImpl类 || 实现IPromotionBiz接口
 *          </p>
 * 
 *          <p>
 *          CreatrDate:Jun 1, 2015 3:54:05 PM
 *          </p>
 * 
 *          <p>
 *          Modification history:
 *          </p>
 */
@Service("promotionBizImpl")
public class PromotionBizImpl extends BaseBizImpl implements IPromotionBiz {

	/**
	 * 限时抢购持久化层
	 */
	@Autowired
	private IPromotionDao promotionDao;

	
	/**
	 * 获取IBaseDao的持久化层
	 * 
	 * @return 返回持promotionDao的久化对象
	 */
	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return promotionDao;
	}

    
    @Override
	public void deletes(String[] ids,Integer appId,Integer modelId){
		// TODO Auto-generated method stub
    	promotionDao.deletes(ids, appId, modelId);
	}
	@Override
	public List queryAllByAppId(int appId,Integer modelId){
		// TODO Auto-generated method stub
		return promotionDao.queryAllByAppId(appId, modelId);
	}
	
	@Override
	public List queryByPageAppid(int appId,Integer modelId,PageUtil page, String orderBy,boolean order){
		// TODO Auto-generated method stub
		return promotionDao.queryByPageAppid(appId, modelId, page, orderBy, order);
	}
	
	@Override
	public int queryCountByAppId(int appId,Integer modelId,String orderBy ){
		// TODO Auto-generated method stub
		return promotionDao.queryCountByAppId(appId, modelId,orderBy);
	}


	@Override
	public List queryPromotionProduct(int appId, PageUtil page) {
		// TODO Auto-generated method stub
		if (page!=null) {
			return promotionDao.queryPromotionProduct(appId,page.getPageSize()*page.getPageNo(),page.getPageSize());
		} 
		return promotionDao.queryPromotionProduct(appId,null,null);
	}


	@Override
	public PromotionEntity getByProductId(int appId, int productId) {
		// TODO Auto-generated method stub
		return promotionDao.getByProductId(appId,productId);
	}
	
	
}

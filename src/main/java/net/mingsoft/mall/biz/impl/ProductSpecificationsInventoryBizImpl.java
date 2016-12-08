package net.mingsoft.mall.biz.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.dao.IModelDao;
import com.mingsoft.util.PageUtil;

import net.mingsoft.mall.biz.IProductSpecificationsInventoryBiz;
import net.mingsoft.mall.constant.ModelCode;
import net.mingsoft.mall.dao.IProductSpecificationsInventoryDao;
import net.mingsoft.mall.entity.ProductSpecificationsInventoryEntity;


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
 * @author 姓名 史爱华
 * 
 * @version 300-001-001
 * 
 *          <p>
 *          版权所有 铭飞科技
 *          </p>
 * 
 *          <p>
 *          Comments:商品规格库存业务处理层实现类 || 继承BaseBizImpl业务处理层||实现IProductSpecificationsInventoryBiz业务层接口
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
@Service("ProductSpecificationsInventoryBizImpl")
public class ProductSpecificationsInventoryBizImpl extends BaseBizImpl implements IProductSpecificationsInventoryBiz{
	
	/**
	 * 产品库存数据处理层
	 */
	@Autowired
	private IProductSpecificationsInventoryDao productSpecificationsInventoryDao;
	
	/**
	 * 模块编码
	 */
	@Autowired
	private IModelDao modelDao;

	
	/**
	 * 更具产品ID删除产品信息
	 * @param productIds 产品ID
	 */
	public void deleteEntityByProductId(int[] productIds){
		this.productSpecificationsInventoryDao.deleteEntityByProductId(productIds);
	}
	
	/**
	 * 
	 * @return
	 */
	public IProductSpecificationsInventoryDao getProductSpecificationsInventoryDao() {
		return productSpecificationsInventoryDao;
	}

	/**
	 * 
	 * @param productSpecificationsInventoryDao
	 */
	@Autowired
	public void setProductSpecificationsInventoryDao(
			IProductSpecificationsInventoryDao productSpecificationsInventoryDao) {
		this.productSpecificationsInventoryDao = productSpecificationsInventoryDao;
	}


	
	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return productSpecificationsInventoryDao;
	}

	@Override
	public void updateSort(
			List<ProductSpecificationsInventoryEntity> productSpecificationsInventory) {
		// TODO Auto-generated method stub
		for(int i = 0;i<productSpecificationsInventory.size();i++){
			this.productSpecificationsInventoryDao.updateSort(productSpecificationsInventory.get(i));
		}
	}

	@Override
	public int getProducntSpecificationCountByColumnId(int appId,
			int modelId, Integer categoryId,Map whereMap) {
		// TODO Auto-generated method stub
		if(categoryId==0){
			categoryId = null;
		}
		return productSpecificationsInventoryDao.getProducntSpecificationCountByColumnId(appId, modelId, categoryId,whereMap);
	}

	@Override
	public List<ProductSpecificationsInventoryEntity> queryProducntSpecificationByColumnId(
			Integer appId, Integer modelId, Integer categoryId, PageUtil page,Map whereMap) {
		// TODO Auto-generated method stub
		if(categoryId==0){
			categoryId = null;
		}
		return productSpecificationsInventoryDao.queryProducntSpecificationByColumnId(appId, modelId, categoryId, page,whereMap);
	}



	@Override
	public List<ProductSpecificationsInventoryEntity> queryProducntSpecificationByProductId(
			Integer appId,  Integer productId) {
		// TODO Auto-generated method stub
		return this.productSpecificationsInventoryDao.queryProducntSpecificationByProductId(appId, modelDao.getEntityByModelCode(ModelCode.MALL_PRODUCT.toString()).getModelId(), productId);
	}
	
	
	
	
	
}

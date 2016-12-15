package net.mingsoft.mall.biz.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.dao.IModelDao;
import com.mingsoft.util.PageUtil;

import net.mingsoft.mall.biz.IProductSpecDetailBiz;
import net.mingsoft.mall.biz.IProductSpecificationsInventoryBiz;
import net.mingsoft.mall.constant.ModelCode;
import net.mingsoft.mall.dao.IProductSpecDetailDao;
import net.mingsoft.mall.dao.IProductSpecificationsInventoryDao;
import net.mingsoft.mall.entity.ProductSpecDetailEntity;
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
 * @author 王敏
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
@Service("ProductSpecDetailBizImpl")
public class ProductSpecDetailBizImpl extends BaseBizImpl implements IProductSpecDetailBiz{
	
	/**
	 * 产品库存数据处理层
	 */
	@Autowired
	private IProductSpecDetailDao detailDao;
	
	/**
	 * 模块编码
	 */
	@Autowired
	private IModelDao modelDao;

	
	/**
	 * 根据产品ID 删除产品信息
	 * @param productIds 产品ID数组
	 */
	@Override
	public void deleteByProductIds(int[] productIds){
		this.detailDao.deleteEntityByProductIds(productIds);
	}

	@Override
	protected IBaseDao getDao() {
		return detailDao;
	}

	/**
	 * 批量更新排序
	 */
	@Override
	public void updateSort(List<ProductSpecDetailEntity> list) {
		
	}

	/**
	 * 根据商品ID, 来查询它所拥有个规格
	 */
	@Override
	public List<ProductSpecDetailEntity> queryDetailsByProductId(int productId) {
		
		return null;
	}

	/**
	 * 保存某商品的规格明细数据
	 * @param productId		商品id
	 * @param list			数据集合
	 */
	@Override
	public void saveEntitiesByProductId(int productId, List<ProductSpecDetailEntity> list) {
		
		// 删除原来数据
		detailDao.deleteEntityByProductIds(new int[]{productId});
		// 保存新数据
		detailDao.saveBatch(list);
	}
}

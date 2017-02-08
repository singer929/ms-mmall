package net.mingsoft.mall.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;

import net.mingsoft.mall.bean.ProductSaveData;
import net.mingsoft.mall.biz.IProductSpecificationBiz;
import net.mingsoft.mall.dao.IProductDao;
import net.mingsoft.mall.dao.IProductSpecificationDao;
import net.mingsoft.mall.dao.IProductSpecificationDetailDao;
import net.mingsoft.mall.dao.ISpecificationDao;
import net.mingsoft.mall.entity.ProductEntity;
import net.mingsoft.mall.entity.ProductSpecificationDetailEntity;
import net.mingsoft.mall.entity.ProductSpecificationEntity;
import net.mingsoft.mall.entity.SpecificationEntity;

/**
 * 
 * 
 * <p>
 * <b>铭飞科技-商城</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 * 
 * @author 成卫雄 QQ:330216230
 * 
 *         <p>
 *         Comments:产品关联规格业务层
 *         </p>
 * 
 *         <p>
 *         Create Date:2014-12-13
 *         </p>
 * 
 *         <p>
 *         Modification history:
 *         </p>
 */
@Service("ProductSpecificationBizImpl")
public class ProductSpecificationBizImpl extends BaseBizImpl implements IProductSpecificationBiz {
	/**
	 * 产品关联规格 持久层
	 */
	@Autowired
	private IProductSpecificationDao productSpecDao;

	/**
	 * 产品关联规格商品详情 持久层
	 */
	@Autowired
	private IProductSpecificationDetailDao detailDao;
	
	/**
	 * 规格数据 持久层
	 */
	@Autowired
	private ISpecificationDao specDao;
	
	/**
	 * 商品数据层
	 */
	@Autowired
	private IProductDao productDao;

	/**
	 * 根据产品ID删除该产品对应的规格
	 * 
	 * @param productId
	 *            产品Id
	 */
	public void deleteEntityByProductIds(int[] productIds) {
		this.productSpecDao.deleteEntityByProductIds(productIds);
	}

	public IProductSpecificationDao getProductSpecificationsDao() {
		return productSpecDao;
	}

	@Autowired
	public void setProductSpecificationsDao(IProductSpecificationDao productSpecificationsDao) {
		this.productSpecDao = productSpecificationsDao;
	}

	@Override
	protected IBaseDao getDao() {
		return productSpecDao;
	}

	/**
	 * 功能暂不需要 未实现
	 */
	@Override
	@Deprecated
	public List<ProductSpecificationEntity> queryListByPsId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 保存产品规格数据(包括规格, 产品规格, 产品规格明细)
	 * @param productId
	 * @param data
	 * @param appId
	 */
	@Override
	public void saveProductSpecification(int productId, ProductSaveData data, int appId){
		
		saveSpecs(data.getSpecList(), appId);
		saveProductSpecs(productId, data.getProductSpecList());
		saveProductSpecDetails(productId, data.getDetailList());
	}
	
	/**
	 * 保存规格数据, 有添加则添加, 否则不做处理
	 * @param list
	 */
	private void saveSpecs (List<SpecificationEntity> list, int appId) {
		
		List<SpecificationEntity> dbList = specDao.queryAll();
		for (SpecificationEntity se : list){
			
			se.setAppId(appId);
			
			boolean isInDb = false;
			for (SpecificationEntity dbSe : dbList){
				if (dbSe.getName().equals(se.getName())){
					isInDb = true;
					break;
				}
			}
			if (isInDb) continue;
			// 不在DB中则插入数据
			specDao.saveEntity(se); 
		}
	}
	
	/**
	 * 保存商品规格数据
	 * @param productId
	 * @param list
	 */
	private void saveProductSpecs(int productId, List<ProductSpecificationEntity> list) {
		
		// 清空当前productId的规格数据
		productSpecDao.deleteEntityByProductIds(new int[]{productId});
		// 将productId 赋值给实体
		for (ProductSpecificationEntity ps : list){
			ps.setProductId(productId);
		}
		if (list != null && list.size() > 0){
			// 添加新数据
			productSpecDao.saveBatch(list);
		}
	}
	
	/**
	 * 保存产品规格明细数据
	 * @param productId
	 * @param list
	 */
	public void saveProductSpecDetails(int productId, List<ProductSpecificationDetailEntity> list) {
		
		// 删除原来数据
		detailDao.deleteEntityByProductIds(new int[]{productId});
		
		for (ProductSpecificationDetailEntity pse : list){
			pse.setProductId(productId);
		}
		
		if (list != null && list.size() > 0){
			// 保存新数据
			detailDao.saveBatch(list);
		}
	}
	
	/**
	 * 根据产品ID 获取客户端需要的产品规格信息 json 数据字符串
	 * @param productId
	 * @return
	 */
	@Override
	public String getDataStrByProductId(int productId) {
		
		//List<SpecificationEntity> specList = specDao.queryByProductId(productId);
		List<SpecificationEntity> specList = specDao.queryAll();
		List<ProductSpecificationEntity> productSpecList = productSpecDao.queryByProductId(productId);
		List<ProductSpecificationDetailEntity> detailList =  detailDao.queryEntitiesByProductId(productId);
		
		// 构建发给前段的数据
		ProductSpecData data = new ProductSpecData();
		data.setProductSpecDetails(detailList);
		data.setProductSpecs(productSpecList);
		data.setSpecs(specList);
		
		String str = JSON.toJSONString(data);
		LOG.debug("前端获取的JSON:" +  str);
		
		return str;
	}

	@Override
	public List<ProductEntity> queryBySpecValues(List<ProductSpecificationEntity> list) {
		
		// 先查询所有id
		List<Integer> ids = productSpecDao.queryByProductSpec(new ProductSpecificationEntity());
		
		for (int i = 0; i < list.size(); i ++){
			ProductSpecificationEntity productSpec = list.get(i);
			if (i == 0){
				ids = productSpecDao.queryByProductSpec(productSpec);
			}
			else{
				List<Integer> tmpIds = productSpecDao.queryByProductSpec(productSpec);
				for (Integer id : ids){
					
					Boolean find = false;
					for (Integer tmpId: tmpIds){
						if (id == tmpId) {
							find = true;
							break;
						}
					}
					
					// 如果在新查询的id中没找到现有的产品id, 则移除现有队列
					if (!find){
						ids.remove(id);
					}
				}
			}
			
			if (ids.size() == 0) break;
		}
		
		// 根据id查找商城数据
		List<ProductEntity> productList = null;
		if (ids.size() == 0){
			productList = new ArrayList<ProductEntity>();
		}
		else{
			productList = productDao.queryByBasicIds(1, null, ids, null, null, false, null);
		}
		
		return productList;
	}
}

/**
 * 产品规格数据结构 将输出给前段获取页面
 */
class ProductSpecData {
	private List<SpecificationEntity> specs;
	private List<ProductSpecificationEntity> productSpecs;
	private List<ProductSpecificationDetailEntity> productSpecDetails;
	
	public List<SpecificationEntity> getSpecs() {
		return specs;
	}
	public void setSpecs(List<SpecificationEntity> specs) {
		this.specs = specs;
	}
	public List<ProductSpecificationEntity> getProductSpecs() {
		return productSpecs;
	}
	public void setProductSpecs(List<ProductSpecificationEntity> productSpecs) {
		this.productSpecs = productSpecs;
	}
	public List<ProductSpecificationDetailEntity> getProductSpecDetails() {
		return productSpecDetails;
	}
	public void setProductSpecDetails(List<ProductSpecificationDetailEntity> productSpecDetails) {
		this.productSpecDetails = productSpecDetails;
	}
}

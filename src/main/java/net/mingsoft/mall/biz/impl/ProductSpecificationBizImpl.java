package net.mingsoft.mall.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;

import net.mingsoft.mall.biz.IProductSpecificationBiz;
import net.mingsoft.mall.dao.IProductSpecificationDao;
import net.mingsoft.mall.dao.IProductSpecificationDetailDao;
import net.mingsoft.mall.dao.ISpecificationDao;
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
	 * 保存商品的规格数据 (先删除然后再更新)
	 * @param productId 商品Id
	 * @param list 需要保存的商品规格数据列表
 	 * @return 结果是否成功
	 */
	@Override
	public Boolean saveEntitiesByProductId(int productId, List<ProductSpecificationEntity> list) {
		
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
		
		return true;
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

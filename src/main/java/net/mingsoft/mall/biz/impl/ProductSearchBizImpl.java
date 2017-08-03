package net.mingsoft.mall.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;

import net.mingsoft.mall.biz.IProductSearchBiz;
import net.mingsoft.mall.dao.IProductSearchDao;
import net.mingsoft.mall.dao.IProductSpecificationDao;
import net.mingsoft.mall.dao.IProductSpecificationDetailDao;
import net.mingsoft.mall.entity.ProductSpecificationDetailEntity;
import net.mingsoft.mall.entity.ProductSpecificationEntity;
import net.mingsoft.mall.search.mapping.ProductMapping;

@Service("ProductSearchBizImpl")
public class ProductSearchBizImpl extends BaseBizImpl implements IProductSearchBiz {

	/**
	 * 产品关联规格 持久层
	 */
	@Autowired
	private IProductSpecificationDao productSpecificationDao;

	/**
	 * 产品关联规格商品详情 持久层
	 */
	@Autowired
	private IProductSpecificationDetailDao productSpecificationDetailDao;
	
	/**
	 * 产品dao处理层
	 */
	@Autowired
	private IProductSearchDao productSearchDao;
	
	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductMapping> queryForSearchMapping() {
		// TODO Auto-generated method stub
		List<ProductMapping> productMapping =  productSearchDao.queryForSearchMapping(null);
		for(int i = 0 ; i < productMapping.size(); i++){
			int productId = Integer.parseInt(productMapping.get(i).getId());
			List<ProductSpecificationEntity> productSpecList = productSpecificationDao.queryByProductId(productId);
			productMapping.get(i).setProductSpecs(JSON.toJSONString(productSpecList));
			List<ProductSpecificationDetailEntity> detailList =  productSpecificationDetailDao.queryEntitiesByProductId(productId);
			productMapping.get(i).setProductSpecDetails(JSON.toJSONString(detailList));
		}
		return productMapping;
	}
	
	
}

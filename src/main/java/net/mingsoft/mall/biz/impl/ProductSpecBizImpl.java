package net.mingsoft.mall.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.util.StringUtil;

import net.mingsoft.mall.biz.IProductSpecBiz;
import net.mingsoft.mall.dao.IProductSpecDao;
import net.mingsoft.mall.dao.IProductSpecDetailDao;
import net.mingsoft.mall.entity.ProductSpecEntity;
import net.mingsoft.mall.entity.ProductSpecificationsEntity;

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
@Service("ProductSpecBizImpl")
public class ProductSpecBizImpl extends BaseBizImpl implements IProductSpecBiz {
	/**
	 * 产品关联规格持久化层
	 */
	private IProductSpecDao productSpecDao;

	/**
	 * 产品关联规格商品详情持久化层
	 */
	@Autowired
	private IProductSpecDetailDao detailDao;

	/**
	 * 关联查询商品规格已经该规格对应的商品详情</br> 将规格进行关联
	 * 
	 * @param productId
	 *            商品ID
	 * @return 商品规格列表
	 */
//	public List<ProductSpecEntity> queryListJsonByProduct(int productId) {
//		
//		List<ProductSpecEntity> psList = this.productSpecDao.queryListJsonByProduct(productId);
//		if (psList == null || psList.size() == 0) {
//			return null;
//		}
//		
//		return null;
//	}

	/**
	 * 新增商品规格详细信息
	 * 
	 * @param ProductSpecificationsJson
	 *            商品规格JSON值
	 * @param productId
	 *            商品ID
	 * @return true 成功
	 */
	public Boolean saveProductSpecificationsEntity(String productSpecificationsJson, int productId) {
		if (!StringUtil.isBlank(productSpecificationsJson)) {
			// 将JSON字符串转换为数组
			List<ProductSpecificationsEntity> listProductSpecifications = JSONArray.parseArray(productSpecificationsJson, ProductSpecificationsEntity.class);
			if (listProductSpecifications == null || listProductSpecifications.size() == 0) {
				return false;
			}
			return saveOrUpdateProductSpecifications(listProductSpecifications, 0, productId, null);
		} else {
			return true;
		}
	}
	
	/**
	 * 递归实现商品规格关联的数据添加和对应规格的商品详情的添加
	 * 
	 * @param list规格列表，由前端传入
	 * @param productSpecificationsFatherId
	 *            父ID
	 * @param productId
	 *            商品ID
	 * @param removeIds
	 *            即将移出的规格id
	 * @return true 成功
	 */
	private Boolean saveOrUpdateProductSpecifications(List<ProductSpecificationsEntity> list, int productSpecificationsFatherId, int productId,List<Integer> remeroveIds) {
		
//		for (int i = 0; i < list.size(); i++) {
//			
//			ProductSpecificationsEntity ps = list.get(i);
//			ps.setProductId(productId);
//			// 持久化产品规格关联
//			ps.setProductSpecificationsFatherId(productSpecificationsFatherId);
//			
//			//查询数据库是否存在
//			ProductSpecificationsEntity pse = productSpecDao.getByProductIdAndspecificationsIdAndspecificationsField(productId, ps.getSpecificationsField(), ps.getSpecificationsId(), productSpecificationsFatherId);
//			
//			// 查询表中是否已经存在商品规格。如果存在就进行更新操作，否则新增
//			if (pse != null) { 
//				ps.setProductSpecificationsId(pse.getProductSpecificationsId());
//				this.productSpecDao.updateEntity(ps);
//				if (ps.getProductSpecificationsInventory() != null) {
//					ps.getProductSpecificationsInventory().setProductSpecificationsId(pse.getProductSpecificationsId());
//					this.productSpecificationsInventoryDao.updateEntity(ps.getProductSpecificationsInventory());
//				}
//			} 
//			else {
//				this.productSpecDao.saveEntity(ps);
//				//查询
//				ProductSpecificationsInventoryEntity productIn = (ProductSpecificationsInventoryEntity) productSpecificationsInventoryDao.getEntity(ps.getProductSpecificationsFatherId());
//				if(productIn!=null){
//					productSpecificationsInventoryDao.deleteEntity(productIn.getProductSpecificationsId());
//				}
//				if (ps.getProductSpecificationsInventory() != null) {
//					ps.getProductSpecificationsInventory().setProductSpecificationsId(ps.getProductSpecificationsId());
//					ps.getProductSpecificationsInventory().setProductId(productId);// 商品ID
//					this.productSpecificationsInventoryDao.saveEntity(ps.getProductSpecificationsInventory());
//				}
//			}
//			
//			//更新时要记录当前使用的规格信息编号，通过这些id排错不使用的id并且删除
//			if (remeroveIds!=null) {
//				remeroveIds.add(ps.getProductSpecificationsId());	
//			}
//		
//			LOG.debug(ps.getProductSpecificationsId());
//	
//			if (ps.getChildProductSpecifications() != null && ps.getChildProductSpecifications().size() != 0) {
//				saveOrUpdateProductSpecifications(ps.getChildProductSpecifications(), ps.getProductSpecificationsId(), productId,remeroveIds);// 递归获取子类
//			}
//		}
		return true;
	}

	/**
	 * 根据产品ID删除该产品对应的规格
	 * 
	 * @param productId
	 *            产品Id
	 */
	public void deleteEntityByProductIds(int[] productIds) {
		this.productSpecDao.deleteEntityByProductIds(productIds);
	}

	/**
	 * 更新
	 * 
	 * @param ProductSpecificationsJson
	 *            商品规格JSON数据
	 * @param productId
	 *            商品ID
	 * @return true 成功
	 */
	public Boolean updateEntityByProduct(String productSpecificationsJson, int productId) {
		// 根据商品ID删除商品对应的规格和库存信息
		// this.deleteEntityByProductId(productId);
		// this.productSpecificationsInventoryDao.deleteEntityByProductId(productId);
		if (!StringUtil.isBlank(productSpecificationsJson)) {
			
			return this.updateProductSpecificationsEntity(productSpecificationsJson, productId);
		} else {
			return true;
		}
	}
	
	public Boolean updateProductSpecificationsEntity(String productSpecificationsJson, int productId) {
		if (!StringUtil.isBlank(productSpecificationsJson)) {
			// 将JSON字符串转换为数组
			List<ProductSpecificationsEntity> listProductSpecifications = JSONArray.parseArray(productSpecificationsJson, ProductSpecificationsEntity.class);
			if (listProductSpecifications == null || listProductSpecifications.size() == 0) {
				productSpecDao.deleteBatch(null,productId); //删除无效的规格编号
				return false;
			}
			List<Integer> ids = new ArrayList<Integer>();//删除的ids,更新规格时要记录当前商品正在使用的规格，并移出不使用的规格编号
			boolean temp = saveOrUpdateProductSpecifications(listProductSpecifications, 0, productId,ids);
			productSpecDao.deleteBatch(ids,productId); //删除无效的规格编号
			return temp;
		} else {
			return true;
		}
	}

	public IProductSpecDao getProductSpecificationsDao() {
		return productSpecDao;
	}

	@Autowired
	public void setProductSpecificationsDao(IProductSpecDao productSpecificationsDao) {
		this.productSpecDao = productSpecificationsDao;
	}

	@Override
	protected IBaseDao getDao() {
		return productSpecDao;
	}

	@Override
	public List<ProductSpecEntity> queryListByPsId(int id) {
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
	public Boolean saveEntitiesByProductId(int productId, List<ProductSpecEntity> list) {
		
		// 清空当前productId的规格数据
		productSpecDao.deleteEntityByProductIds(new int[]{productId});
		
		// 添加新数据
		productSpecDao.saveBatch(list);
		
		return true;
	}
}

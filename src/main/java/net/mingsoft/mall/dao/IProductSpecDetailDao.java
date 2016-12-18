package net.mingsoft.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;

import net.mingsoft.mall.entity.ProductSpecDetailEntity;

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
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments:产品规格明细 数据层接口
 * </p>
 *  
 * <p>
 * Create Date:2014-11-20
 * </p>
 *
 * <p>
 * </p>
 */
public interface IProductSpecDetailDao extends IBaseDao{

	/**
	 * 更具产品ID删除产品信息
	 * @param productIds 产品ID
	 */
	public void deleteEntityByProductIds(@Param("productIds")int[] productIds);
	
	/**
	 * 批量进行更新操作
	 * @param data
	 */
	public void updateSort(ProductSpecDetailEntity data);
	
	/**
	 * 根据分类id和模块id查询商品规格产品的总数
	 * @param appId 应用id
	 * @param modelId 模块id
	 * @param categoryId 分类id
	 * @return 商品规格产品的总数
	 */
	//public int getProducntSpecificationCountByColumnId(@Param("appId")int appId, @Param("modelId")int modelId, @Param("categoryId")Integer categoryId, @Param("whereMap")Map whereMap);
	
	/**
	 * 根据分类id和模块id查询商品规格产品列表
	 * @param appId 应用id
	 * @param modelId 模块id
	 * @param categoryId 分类id
	 * @param page 分页
	 * @return 商品规格库存列表
	 */
	//public List<ProductSpecDetailEntity> queryProducntSpecificationByColumnId(@Param("appId")int appId,@Param("modelId")int modelId,@Param("categoryId")Integer categoryId,@Param("page")PageUtil page,@Param("whereMap")Map whereMap);
	
	/**
	 * 根据商品id查询商品规格库存列表信息
	 * @param appId 应用id
	 * @param productId 产品id
	 * @return 商品规格库存列表
	 */
	public List<ProductSpecDetailEntity> queryByAppAndProductId(@Param("appId")int appId, @Param("productId")int productId);
	
	/**
	 * 根据商品id查询商品规格库存列表信息
	 * @param productId 产品id
	 * @return 商品规格库存列表
	 */
	public List<ProductSpecDetailEntity> queryEntitiesByProductId(@Param("productId") int productId);
}

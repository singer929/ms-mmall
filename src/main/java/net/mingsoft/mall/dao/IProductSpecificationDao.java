package net.mingsoft.mall.dao;

import java.util.List;

import javax.ws.rs.DELETE;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONArray;
import com.mingsoft.base.dao.IBaseDao;

import net.mingsoft.mall.entity.ProductEntity;
import net.mingsoft.mall.entity.ProductSpecificationEntity;
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
 * Comments:产品规格数据层接口
 * </p>
 *  
 * <p>
 * Create Date:2014-11-20
 * </p>
 *
 * <p>
 * </p>
 */
public interface IProductSpecificationDao extends IBaseDao {
	
	
	/**
	 * 依据规格名称删除产品规格数据(例如规格删除之后需要清理产品规格数据)
	 * @param specName
	 */
	public void deleteBySpecificationName(@Param("specName") String specName);
	
	/**
	 * 根据产品ID删除该产品对应的规格
	 * @param productId 产品Id
	 */
	public void deleteEntityByProductIds(@Param("productIds")int[] productIds);
	
	
	/**
	 * 根据商品 获取所有的数据
	 * @param productId
	 * @return
	 */
	public List<ProductSpecificationEntity> queryByProductId(@Param("productId")int productId);
	
	/**
	 * 根据产品的规格值获取符合条件的 产品id
	 * @param specName
	 */
	public List<Integer> queryByProductSpec(ProductSpecificationEntity productSpec);
}

package net.mingsoft.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;

import net.mingsoft.mall.entity.ProductSpecificationsEntity;
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
 * @author 姓名：史爱华
 * 
 * @version 300-001-001
 * 
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments:产品规格关联数据层接口，继承IBaseDao
 * </p>
 *  
 * <p>
 * Create Date:2014-11-20
 * </p>
 *
 * <p>
 * </p>
 */
public interface IProductSpecificationsDao extends IBaseDao{

	/**
	 * 关联查询商品规格已经该规格对应的商品详情
	 * @param productId 商品ID
	 * @return 商品规格列表
	 */
	public List<ProductSpecificationsEntity> queryListJsonByProduct(@Param("productId")Integer productId); 
	
	/**
	 * 根据产品ID删除该产品对应的规格
	 * @param productId 产品Id
	 */
	public void deleteEntityByProductId(@Param("productIds")int[] productIds);
	
	/**
	 * 根据商品规格编号读取商品规格详情
	 * @param productSpecificationsId 商品规格编号
	 * @return 规格详情
	 */
	public ProductSpecificationsEntity getByProductSpecificationsId(@Param("productSpecificationsId")Integer productSpecificationsId);
	
	/**
	 * 根据商品编号、规格编号、规格值，查询商品绑定的规格数据
	 * @param productId 商品id
	 * @param specificationsField 规格值
	 * @param specificationsId 规格id
	 */
	ProductSpecificationsEntity getByProductIdAndspecificationsIdAndspecificationsField(@Param("productId")int productId,@Param("specificationsField")String specificationsField,@Param("specificationsId")int specificationsId,@Param("productSpecificationsFatherId")int productSpecificationsFatherId);
	
	/**
	 * 根据编号批量删除
	 * @param ids 编号集合
	 * @param productId商品编号
	 */
	void deleteBatch(@Param("ids")List ids,@Param("productId")int productId);
}

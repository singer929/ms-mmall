package net.mingsoft.mall.biz;

import java.util.List;

import com.mingsoft.base.biz.IBaseBiz;

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
 *          <p>
 *          版权所有 铭飞科技
 *          </p>
 * 
 *          <p>
 *          Comments:商品规格库存管理业务处理层 接口|| 继承IBaseBiz业务处理层
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
public interface IProductSpecDetailBiz extends IBaseBiz{
	
	/**
	 * 更具产品ID删除产品信息
	 * @param productIds 产品ID
	 */
	public void deleteByProductIds(int[] productIds);
	
	
	/**
	 * 更新排序
	 * @param list 商品规格数据明细
	 */
	public void updateSort(List<ProductSpecDetailEntity> list);
	
	/**
	 * 保存某商品的规格明细数据
	 * @param productId		商品id
	 * @param list			数据集合
	 */
	public void saveEntitiesByProductId(int productId, List<ProductSpecDetailEntity> list);
	
	
	/**
	 * 根据分类id和模块id查询商品规格产品的总数
	 * @param appId 应用id
	 * @param modelId 模块id
	 * @param categoryId 分类id
	 * @return 规格商品总数
	 */
	//public int getProducntSpecificationCountByColumnId(int appId, int  modelId, Integer categoryId, Map whereMap);
	
	/**
	 * 根据分类id和模块id查询商品规格产品列表
	 * @param appId 应用id
	 * @param modelId 模块id
	 * @param categoryId 分类id
	 * @param page 分页
	 * @return 商品规格产品列表
	 */
	//public List<ProductSpecificationsInventoryEntity> queryProducntSpecificationByColumnId(Integer appId,Integer modelId,Integer categoryId,PageUtil page,Map whereMap);
	
	/**
	 * 根据商品id 查询商品规明细列表信息
	 * @param productId 产品id
	 * @return 商品规格产品列表
	 */
	public List<ProductSpecDetailEntity> queryDetailsByProductId(int productId);
}

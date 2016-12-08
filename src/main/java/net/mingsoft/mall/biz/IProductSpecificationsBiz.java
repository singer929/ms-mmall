package net.mingsoft.mall.biz;

import java.util.List;

import com.mingsoft.base.biz.IBaseBiz;

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
 * @author 姓名 史爱华
 * 
 * @version 300-001-001
 * 
 *          <p>
 *          版权所有 铭飞科技
 *          </p>
 * 
 *          <p>
 *          Comments:商品规格关联管理业务处理层 接口|| 继承IBaseBiz业务处理层
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
public interface IProductSpecificationsBiz extends IBaseBiz{
	
	/**
	 * 关联查询商品规格已经该规格对应的商品详情
	 * @param productId 商品ID
	 * @return 商品规格列表
	 */
	public List<ProductSpecificationsEntity> queryListJsonByProduct(Integer productId);
	
	/**
	 * 新增商品规格详细信息
	 * @param ProductSpecificationsJson 商品规格JSON值
	 * @param productId 商品ID
 	 * @return true 成功
	 */
	public Boolean saveProductSpecificationsEntity(String productSpecificationsJson,int productId);
	
	/**
	 * 根据产品ID删除该产品对应的规格
	 * @param productIds 产品Id
	 */
	public void deleteEntityByProductId(int[] productIds);
	
	/**
	 * 更新
	 * @param ProductSpecificationsJson 商品规格JSON数据
	 * @param productId 商品ID
	 * @return true 成功
	 */
	public Boolean updateEntityByProduct(String productSpecificationsJson,int productId);
	
	
	/**
	 * 根据产品规格关联id查询所有的子产品规格关联列表
	 * @param ProductSpecificationsId 产品规格关联id
	 * @return  产品规格关联列表
	 */
	public List<ProductSpecificationsEntity> queryListByProductSpecificationsId(int productSpecificationsId);
}

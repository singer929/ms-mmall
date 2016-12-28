package net.mingsoft.mall.biz;

import java.util.List;

import com.mingsoft.base.biz.IBaseBiz;

import net.mingsoft.mall.bean.ProductSaveData;
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
public interface IProductSpecificationBiz extends IBaseBiz{
	
	/**
	 * 根据产品ID 获取客户端需要的产品规格信息 json 数据字符串
	 * @param productId
	 * @return
	 */
	public String getDataStrByProductId(int productId);
	
	/**
	 * 根据产品ID删除该产品对应的规格
	 * @param productIds 产品Id
	 */
	public void deleteEntityByProductIds(int[] productIds);
	
	/**
	 * 根据产品规格关联id查询所有的子产品规格关联列表
	 * @param ProductSpecificationsId 产品规格关联id
	 * @return  产品规格关联列表
	 */
	public List<ProductSpecificationEntity> queryListByPsId(int id);
	
	/**
	 * 保存产品规格数据
	 * @param productId		产品ID
	 * @param data			产品规格数据集合
	 * @param appId			appId
	 */
	public void saveProductSpecification(int productId, ProductSaveData data, int appId);
}

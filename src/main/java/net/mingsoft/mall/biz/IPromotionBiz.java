package net.mingsoft.mall.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.util.PageUtil;

import net.mingsoft.mall.entity.PromotionEntity;

/**
 * 
 *  <p>
 * <b>限时促销</b>
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 * 
 * <p>
 * Company:景德镇铭飞科技有限公司
 * </p>
 * 
 * @author 郭鹏辉
 * 
 * @version 060-401-00
 * 
 *          <p>
 *          版权所有 铭飞科技
 *          </p>
 *
 *         @ClassName: IPromotionBiz
 *
 *          <p>
 *          Comments:  限时抢购业务逻辑层 || 继承IBaseBiz类
 *          </p>
 * 
 *          <p>
 *          CreatrDate:Jun 1, 2015 3:53:06 PM
 *          </p>
 * 
 *          <p>
 *          Modification history:
 *          </p>
 *
 */
public interface IPromotionBiz extends IBaseBiz{

	/**
	 * 删除多个
	 * @param ids  多个实体id集合
	 * @param appId 站点id(可选)
	 * @param modelId 模块id(可选)
	 */
	void deletes(String[] ids,Integer appId,Integer modelId);
	
	/**
	 * 查询所有
	 * @param appId 站点id
	 * @param modelId 模块id(可选)
	 * @return 返回查询的实体集合 null没有数据
	 */
	List queryAllByAppId(int appId,Integer modelId);
	
	/**
	 * 分页查询
	 * @param appId 站点id
	 * @param modelId 模块id(可选)
	 * @param page PageUtil对象,分页实体
	 * @param orderBy 排序字段
	 * @param order 排序方式true:asc false:desc
	 * @return 返回查询的实体集合 null没有数据
	 */
	List queryByPageAppid(int appId,Integer modelId,PageUtil page, String orderBy,boolean order);
	
	/**
	 * 根据站点id查询数据表中记录集合总数</br>
	 * 可配合分页使用</br>
	 * @param appId 站点id
	 * @param modelId 模块id(可选)
	 * @param orderBy 关键字
	 * @return 返回记录总数
	 */
	int queryCountByAppId(int appId,Integer modelId,String orderBy);
	
	/**
	 * 查询促销商品
	 * @param appId 应用编号
	 * @param page 分页
	 * @return 返回查询的实体集合 null没有数据
	 */
	List queryPromotionProduct(int appId,PageUtil page);
	
	/**
	 * 获取商品对应的促销信息
	 * @param appId 应用编号
	 * @param productId 商品编号
	 * @return 促销信息，没有返回null
	 */
	PromotionEntity getByProductId(int appId,int productId);

}

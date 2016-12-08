package net.mingsoft.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;
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
 * @version 140-000-000
 * 
 *          <p>
 *          版权所有 铭飞科技
 *          </p>
 *
 *         @ClassName: IPromotionDao
 *
 *          <p>
 *          Comments:  限时抢购持久化层 || 继承IBaseDao类
 *          </p>
 * 
 *          <p>
 *          CreatrDate:Jun 1, 2015 3:57:47 PM
 *          </p>
 * 
 *          <p>
 *          Modification history:
 *          </p>
 */
public interface IPromotionDao extends IBaseDao {
	/**
	 * 删除多个
	 * @param ids  多个实体id集合
	 * @param appId 站点id
	 * @param modelId 模块id(可选)
	 */
	void deletes(@Param("ids")String[] ids,@Param("appId")Integer appId,@Param("modelId")Integer modelId);
	
	/**
	 * 查询所有
	 * @param appId 站点id
	 * @param modelId 模块id(可选)
	 * @return 返回查询的实体集合 null没有数据
	 */
	List queryAllByAppId(@Param("appId")int appId,@Param("modelId")Integer modelId);
	/**
	 * 分页查询
	 * @param appId 站点id
	 * @param modelId 模块id(可选)
	 * @param page PageUtil对象,分页实体
	 * @param orderBy 排序字段
	 * @param order 排序方式true:asc false:desc
	 * @return 返回查询的实体集合 null没有数据
	 */
	List queryByPageAppid(@Param("appId")int appId,@Param("modelId")Integer modelId,@Param("page")PageUtil page, @Param("orderBy")String orderBy,@Param("order")boolean order);
	
	/**
	 * 查询数据表中记录集合总数</br>
	 * 可配合分页使用</br>
	 * @param appId 站点id
	 * @param modelId 模块id(可选)
	 * @param orderBy 关键字(可选)
	 * @return 返回查询的集合总数
	 */
	int queryCountByAppId(@Param("appId")int appId,@Param("modelId")Integer modelId,@Param("modelId")String orderBy);

	/**
	 * 查询促销商品
	 * @param appId 应用编号
	 * @param start 开始
	 * @param end 结束
	 * @return 返回查询的实体集合 null没有数据
	 */
	List queryPromotionProduct(@Param("appId")int appId, @Param("start")Integer start,@Param("end")Integer end);

	/**
	 * 获取商品对应的促销信息
	 * @param appId 应用编号
	 * @param productId 商品编号
	 * @return 促销信息，没有返回null
	 */
	PromotionEntity getByProductId(@Param("appId")int appId, @Param("productId")int productId);

}

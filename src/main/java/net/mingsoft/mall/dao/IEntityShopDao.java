package net.mingsoft.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;

import net.mingsoft.mall.entity.EntityShopEntity;


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
 * Comments:商品实体店持久化层，继承IBaseDao
 * </p>
 *  
 * <p>
 * Create Date:2015-02-03
 * </p>
 *
 * <p>
 * </p>
 */
public interface IEntityShopDao  extends IBaseDao {
	
	/**
	 * 根据应用id获取总数
	 * @param appId 应用id
	 * @return 门店总数
	 */
	public int getCountByAppId(@Param("appId") Integer appId);
	
	/**
	 * 根据appId实现商品实体店的分页查询
	 * @param appId  应用id
	 * @param pageNo ：当前页码
	 * @param pageSize：查询的数量
	 * @param orderBy：依据的排序方式
	 * @param order：是否为升序
	 * @return 商品实体店列表
	 */
	public List<EntityShopEntity> queryPageByAppId(@Param("appId")Integer appId,@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);
	/**
	 * 根据appId实现商品实体店的分页查询
	 * @param appId 应用id
	 * @param pageNo ：当前页码
	 * @param pageSize：查询的数量
	 * @param orderBy：依据的排序方式
	 * @param order：是否为升序
	 * @return 商品实体店列表
	 */
	public List<EntityShopEntity> queryListByProvinceId(@Param("appId")Integer appId,@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize,@Param("provinceId")Integer provinceId);
	
}

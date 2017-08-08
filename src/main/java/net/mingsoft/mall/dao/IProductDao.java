package net.mingsoft.mall.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.util.PageUtil;

import net.mingsoft.mall.entity.ProductEntity;

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
 *          <p>
 *          版权所有 铭飞科技
 *          </p>
 * 
 *          <p>
 *          Comments:产品实体类，继承BaseEntity
 *          </p>
 * 
 *          <p>
 *          Create Date:2014-11-20
 *          </p>
 *
 *          <p>
 *          </p>
 */
public interface IProductDao extends IBaseDao {



	/**
	 * 根据栏目id和产品更新时间获取产品列表
	 * 
	 * @param basicCategoryId
	 *            分类编号
	 * @param dateTime
	 *            时间
	 * @param appId
	 *            应用ID
	 * @return
	 */
	@Deprecated
	public List<ProductEntity> queryListByTime(@Param("basicCategoryId") Integer basicCategoryId,
			@Param("dateTime") String dateTime, @Param("appId") Integer appId);




	/**
	 * *根据栏目集合查询商品实体
	 * 
	 * @param appId 应用编号 
	 * @param basicCategoryIds 分类集合
	 * @param begin 分页起始
	 * @param size  一页数量
	 * @param orderBy
	 *            依据排序字段
	 * @param order
	 *            是否降序
	 * @param productShelf 上架状态
	 * @param flag 属性
	 * @param noFlag 不存在属性
	 * @return
	 */
	public List<ProductEntity> queryList(@Param("appId") Integer appId,
			@Param("basicCategoryIds") int[] basicCategoryIds,  @Param("orderBy") String orderBy, @Param("order") boolean order,
			@Param("productShelf") Integer productShelf, @Param("flag") String[] flag, @Param("noFlag") String[] noFlag);

	/**
	 * 根据查询产品实体
	 * 
	 * @param tableName
	 *            :自定义生成的表名
	 * @param map
	 *            获取查询条件的Map key:字段名 value:List 字段的各种判断值 list[0]:是否为自定义字段
	 *            list[1]:是否为整形 list[2]:是否是等值查询 list[3]:字段的值
	 * @return 文章实体
	 */
	public List<ProductEntity> queryListForSearch(@Param("tableName") String tableName,
			@Param("map") Map<String, List> map, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize,
			@Param("websiteId") int websiteId, @Param("ids") List ids, @Param("sortMap") Map sortMap);


	/**
	 * 根据查询产品实体
	 * 
	 * @param tableName
	 *            :自定义生成的表名
	 * @param map
	 *            获取查询条件的Map key:字段名 value:List 字段的各种判断值 list[0]:是否为自定义字段
	 *            list[1]:是否为整形 list[2]:是否是等值查询 list[3]:字段的值
	 * @param appId
	 *            应用id
	 * @param sortMap
	 *            依据排序字段
	 * @param page
	 *            分页对象
	 * @return 产品实体列表
	 */
	public List<ProductEntity> queryProductSpecificationForSearch(@Param("tableName") String tableName,
			@Param("map") Map<String, List> map, @Param("appId") int appId, @Param("ids") List ids,
			@Param("sortMap") Map sortMap, @Param("page") PageUtil page, @Param("orderBy") String orderBy,
			@Param("order") boolean order, @Param("flag") String flag, @Param("noFlag") String noFlag);

	/**
	 * 根据查询商品实体总数
	 * 
	 * @param tableName
	 *            :自定义生成的表名
	 * @param map
	 *            key:字段名 value:List 字段的各种判断值 list[0]:是否为自定义字段 list[1]:是否为整形
	 *            list[2]:是否是等值查询 list[3]:字段的值
	 * @return 文章实体总数
	 */
	public int getProducntSpecificationSearchCount(@Param("tableName") String tableName,
			@Param("map") Map<String, List> map, @Param("appId") Integer appId, @Param("ids") List ids,
			@Param("flag") String flag, @Param("noFlag") String noFlag);



	/**
	 * 根据商品id集合和分页对象查询商品列表信息
	 * 
	 * @param appId
	 *            商品id集合
	 * @param categoryId
	 *            分类id
	 * @param basicIds
	 *            商品id集合
	 * @param page
	 *            分页对象
	 * @param orderBy
	 *            依据排序字段
	 * @param order
	 *            是否降序
	 * @return 返回商品列表信息
	 */
	@Deprecated
	public List<ProductEntity> queryByBasicIds(@Param("appId") int appId, @Param("categoryId") Integer categoryId,
			@Param("basicIds") List<Integer> basicIds, @Param("page") PageUtil page, @Param("orderBy") String orderBy,
			@Param("order") boolean order, @Param("productShelf") Integer productShelf);

	
	/**
	 * 商品查询模块
	 * @param appId
	 * @param categoryId
	 * @param brands
	 * @param minPrice
	 * @param maxPrice
	 * @param specMap
	 * @return
	 */
	public List<ProductEntity> search(
		@Param("appId") int appId, 
		@Param("modelId") int modelId,
		@Param("categories") int[] categories, 
		@Param("brands") int[] brands,
		@Param("minPrice") double minPrice,
		@Param("maxPrice") double maxPrice,
		@Param("specSql") String specSql,
		@Param("orderBy") String orderBy,
		@Param("orderByType") String orderByType
	);
	
	/**
	 * 查询用某些用户购买过哪些商品
	 * @param appId
	 * @param productId
	 * @param num
	 * @return
	 */
	public List<ProductEntity> getProductsByPeopleIds(
		@Param("appId") int appId, 
		@Param("categoryId") int categoryId,
		@Param("peopleIds") List<Integer> peopleIds,
		@Param("num") int num
	);
	
	public List<Integer> getPeopleIdsByProductId(
		@Param("appId") int appId, 
		@Param("productId") int productId
	);
}

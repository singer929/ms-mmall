package net.mingsoft.mall.biz;

import java.util.List;
import java.util.Map;

import com.mingsoft.basic.biz.IBasicBiz;
import com.mingsoft.basic.entity.BasicCategoryEntity;
import com.mingsoft.mdiy.entity.ContentModelEntity;
import com.mingsoft.util.PageUtil;

import net.mingsoft.mall.constant.e.ProductEnum;
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
 * @author 姓名 史爱华
 * 
 * @version 300-001-001
 * 
 *          <p>
 *          版权所有 铭飞科技
 *          </p>
 * 
 *          <p>
 *          Comments:商品管理业务处理层 接口|| 继承IBasicBiz业务处理层
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
public interface IProductBiz extends IBasicBiz {
	
	/**
	 * 根据appID查询产品的总数
	 * @param appId
	 * @return
	 */
	public int getCountByAppId(Integer appId);
	
	/**
	 * 根据appid查询产品分页
	 * @param appId 应用id
	 * @param page：页面信息
	 * @param orderBy：排序方式
	 * @param order：是否采用升序
	 * @return 产品列表数据
	 */
	public List<ProductEntity> queryListPageByAppId(Integer appId,PageUtil page);
	
	/**
	 * 根据分类与时间查询商品列表
	 * @param categoryId :分类id
	 * @param dateTime ：更新时间
	 * @param appId ：应用appID
	 * @return 产品列表数据
	 */
	public List<ProductEntity> query(Integer categoryId, String dateTime,Integer appId);
	
	
	/**
	 * 根据模块ID查询商品信息带分页
	 * @param appId 应用ID
	 * @param modelId 模块ID
	 * @param categoryId 分类ID
	 * @param begin 分页开始位置
	 * @param end 分页结束位置
	 * @return 商品集合
	 */
	public List<ProductEntity> queryPageByModelId(Integer appId,Integer modelId,Integer categoryId,PageUtil page);
	
	/**
	 * 根据模块ID查询商品总数
	 * @param appId 应用ID
	 * @param modelId 模块ID
	 * @param categoryId 分类ID
	 * @return 商品总数
	 */
	public int queryCountByModelId(Integer appId,Integer modelId,Integer categoryId);
	/**
	 * 
	 * @param appId
	 * 			 应用appID
	 * @param basicCategoryIds
	 * 			分类集合id
	 * @param orderBy
	 * 			依据排序字段
	 * @param order
	 * 			是否降序
	 * @return 产品列表数据
	 */
	public List<ProductEntity> queryList(int appId, int[] basicCategoryIds, String orderBy, boolean order,Integer productShelf,String flag,String noFlag);
	
	/**
	 * 高级查询接口，主要提供给有自定义模型的栏目，返回产品总是
	 * 
	 * @param contentModel
	 *            自定义模型
	 * @param whereMap
	 *            條件
	 * @param appId
	 *            appId 应用编号
	 * @param ids
	 *            子类id
	 * @return 记录数量
	 */
	public int getSearchCount(ContentModelEntity contentModel, Map whereMap, int appId, List ids);
	
	/**
	 * 高级查询接口，主要提供给有自定义模型的栏目，
	 * 
	 * @param conntentModel
	 *            自定义模型
	 * @param whereMap
	 *            條件
	 * @param page
	 *            分頁
	 * @param appId
	 *            應用編號
	 * @param ids
	 *            子类id
	 * @return 记录集合
	 */
	public List<ProductEntity> queryListForSearch(ContentModelEntity conntentModel, Map whereMap, PageUtil page, int appId, List ids, Map orders);
	
	/**
	 * 查询上架或下架商品的分页列表
	 * @param appId 应用id
	 * @param proudctShelf 商品的上架状态
	 * @param page ：分页对象
	 * @return 商品列表信息
	 */
	public List<ProductEntity> queryByShelf(Integer appId,Integer productShelf,Integer categoryId,PageUtil page);
	
	/**
	 * 查询站点下该板块所有上架商品
	 * @param appId 应用id
	 * @param productShelf 商品是否上架
	 * @param categoryId 分类id
	 * @return 商品列表信息
	 */
	public List<ProductEntity> queryAllShelf(Integer appId,Integer productShelf,Integer categoryId);
	
	
	/**
	 *  查询上架或下架商品的总数
	 * @param appId 应用id
	 * @param proudctShelf  商品的上架状态
	 * @return 上架或下架商品的总数
	 */
	public int getCountByShelf(Integer appId,Integer productShelf, Integer categoryId);
	
	/**
	 * 批量更新商品的
	 * @param productIds 商品的id集合
	 */
	public void updateProductShelf(String[] productIds,ProductEnum productShelf);
	
	/**
	 * 查询产品的规格产品列表
	 * @param appId 应用id
	 * @param basicCategoryIds 分类id集合
	 * @param begin 开始位置
	 * @param count  查询总数
	 * @param orderBy 依据排序字段
	 * @param order  是否降序 false：升序 true:降序
	 * @return 产品列表数据
	 */
	public List<ProductEntity> queryProducntSpecificationList(Integer appId,int[] basicCategoryIds,int begin,int count,String orderBy,boolean order,String flag,String noFlag);
	
	/**
	 * 根据产品的推荐属性和不推荐属性查找商品规格总数
	 * @param basicCategoryIds 分类id
	 * @param appId 应用id
	 * @param flag  商品推荐属性
	 * @param noFlag 商品部推荐属性
	 * @return 产品总数
	 */
	public int getProducntSpecificationCount( int[] basicCategoryIds,Integer appId,String flag ,String noFlag);
	
	/**
	 * 根据时间和分类id集合查询商品规格商品
	 * @param dateTime  时间
	 * @param appId 应用id
	 * @param categoryId 分类id
	 * @return  产品列表
	 */
	public List<ProductEntity> queryProducntSpecificationByDateAndByColumnId(String dateTime,Integer appId,Integer categoryId);
	
	/**
	 * 根据查询产品实体
	 * 
	 * @param tableName
	 *            :自定义生成的表名
	 * @param map
	 *            获取查询条件的Map key:字段名 value:List 字段的各种判断值 list[0]:是否为自定义字段
	 *            list[1]:是否为整形 list[2]:是否是等值查询 list[3]:字段的值
	 * @param appId
	 * 				应用id
	 * @param sortMap
	 * 					依据排序字段
	 * @param page
	 * 				 分页对象      
	 *  * @param 
	 * 		flag推荐属性
	 * @param noflag
	 * 			不推荐属性     
	 * @return 产品实体列表
	 */
	public List<ProductEntity> queryProducntSpecificationForSearch(ContentModelEntity conntentModel,  Map<String, List> map,  int appId,List ids, Map sortMap, PageUtil page,String orderBy,boolean order,String flag,String noFlag);
	
	/**
	 * 高级查询接口，主要提供给有自定义模型的栏目，返回产品规格总数
	 * 
	 * @param contentModel
	 *            自定义模型
	 * @param whereMap
	 *            條件
	 * @param appId
	 *            appId 应用编号
	 * @param ids
	 * @param 
	 * 		flag推荐属性
	 * @param noflag
	 * 			不推荐属性
	 *            子类id
	 * @return 记录数量
	 * 
	 */
	
	public int getProducntSpecificationSearchCount(ContentModelEntity contentModel, Map whereMap, int appId, List ids,String flag,String noFlag);
	
	/**
	 * 根据商品条件查询商品
	 * @param appId  应用id
	 * @param columId 分类id
	 * @param map 查询条件
	 * @return 商品列表信息
	 */
	public List<ProductEntity> querySearch(int appId,int categoryId,Map map);
	
	/**
	 * 根据自定义字段和商品字段信息查询商品总数
	 * @param appId 应用id
	 * @param categoryId 分类id
	 * @param map 商品字段查询条件 
	 * @param diyFieldMap 自定义字段的查询条件
	 * @return 商品总数
	 */
	int getCountByDiyField(int appId,Integer categoryId, Map productMap,Map diyFieldMap,String tableName);
	
	/**
	 * 根据自定义字段和商品字段信息查询商品列表信息
	 * @param appId 应用id
	 * @param categoryId  分类id
	 * @param map 商品字段查询条件 
	 * @param diyFieldMap 自定义字段的查询条件
	 * @return 商品列表信息
	 */
	public List<ProductEntity> queryByDiyField(int appId,Integer categoryId,Map map,Map diyFieldMap,PageUtil page, String tableName);
	
	
	/**
	 * 根据栏目id查询商品bean，主要用于前端获取数据
	 * @param categoryId 栏目id
	 * @param page 分页对象
	 * @param _isHasChilds 是否查询子分类的文章,true则进行查询子分类的文章
	 * @return  商品bean
	 */
	public List  queryByCategoryForBean(int appId,Integer categoryId,PageUtil page, boolean _isHasChilds);
	
	/**
	 * 保存商品以及属性信息
	 * @param product 商品实体
	 * @param basicCategoryList 基础表和分类关联实体
	 */
	public void saveProduct(ProductEntity product,List<BasicCategoryEntity> basicCategoryList);
	
	/**
	 * 保存商品以及属性信息
	 * @param product 商品实体
	 * @param basicCategoryList 基础表和分类关联实体
	 */
	public void updateProduct(ProductEntity product,List<BasicCategoryEntity> basicCategoryList);
	
	/**
	 * 根据商品id集合和分页对象查询商品列表信息
	 * @param categoryId 分类id
	 * @param basicIds 商品id集合
	 * @param page 分页对象
	 * @param orderBy 依据排序字段
	 * @param order 是否降序
	 * @return 返回商品列表信息
	 */
	public List<ProductEntity> queryByBasicIds(int appId,Integer categoryId,List<Integer> basicIds,PageUtil page,String orderBy,boolean order,Integer productShelf);
	
	/**
	 * 根据商品id集合和分类id,以及商品的上下架状态查询商品总数
	 * @param appId 应用id
	 * @param categoryId 分类id
	 * @param basicIds 商品id集合
	 * @param productShelf 商品上下架状态
	 * @return 符合条件的商品总数
	 */
	int getCountByBasicIds(int appId,Integer categoryId,List<Integer> basicIds,Integer productShelf);
	
	/**
	 * 删除商品信息
	 * @param appId 应用编号
	 * @param ids 商品编号
	 */
	void delete(int appId, int[] ids);
	
	/**
	 * 规格搜索功能接口
	 * @param appId		应用ID
	 * @param category	分类id
	 * @param brands	品牌id集合
	 * @param price		价格id
	 * @param specs		规格数据字符串
	 */
	public List<ProductEntity> search(int appId, int modelId, Integer category, int[] brands, String price, String specs, String sort);
	
	
	/**
	 * 获取其他用户购买了其他此商品的同类商品
	 * @param appId
	 * @param productId
	 * @param num
	 * @return
	 */
	public List<ProductEntity> getOthersPurchase(int appId, int productId, int categoryId, int num);
}

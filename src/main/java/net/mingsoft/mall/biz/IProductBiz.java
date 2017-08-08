package net.mingsoft.mall.biz;

import java.util.List;
import java.util.Map;

import com.mingsoft.basic.biz.IBasicBiz;
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
	 * 根据分类与时间查询商品列表
	 * @param categoryId :分类id
	 * @param dateTime ：更新时间
	 * @param appId ：应用appID
	 * @return 产品列表数据
	 */
	public List<ProductEntity> query(Integer categoryId, String dateTime,Integer appId);
	
	
	
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

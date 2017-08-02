package net.mingsoft.mall.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.scripting.xmltags.WhereSqlNode;
import org.aspectj.weaver.ast.Var;
import org.codehaus.groovy.ast.expr.ElvisOperatorExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.biz.IBasicCategoryBiz;
import com.mingsoft.basic.biz.ICategoryBiz;
import com.mingsoft.basic.biz.IModelBiz;
import com.mingsoft.basic.biz.impl.BasicBizImpl;
import com.mingsoft.basic.entity.BasicCategoryEntity;
import com.mingsoft.basic.entity.CategoryEntity;
import com.mingsoft.basic.entity.ModelEntity;
import com.mingsoft.basic.biz.IColumnBiz;
import com.mingsoft.mdiy.biz.IContentModelBiz;
import com.mingsoft.basic.entity.ColumnEntity;
import com.mingsoft.mdiy.entity.ContentModelEntity;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.mall.biz.IProductBiz;
import net.mingsoft.mall.constant.e.ProductEnum;
import net.mingsoft.mall.dao.IProductDao;
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
 *          Comments:商品管理业务处理层实现类 || 继承IBasicBiz业务处理层||实现IProductBiz业务层接口
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
@Service("ProductBizImpl")
public class ProductBizImpl extends BasicBizImpl implements IProductBiz {
	
	/**
	 * 产品dao处理层
	 */
	@Autowired
	private IProductDao productDao;
	
	@Override
	protected IBaseDao getDao() {
		return productDao;
	}
	
	/**
	 * 
	 */
	@Autowired
	private ICategoryBiz categoryBiz;
	
	
	/**
	 * 
	 */
	@Autowired
	private IColumnBiz columnBiz;
	
	/**
	 * 
	 */
	@Autowired
	private IContentModelBiz contentModelBiz;
	
	/**
	 * 模块管理业务层
	 */
	@Autowired
	private IModelBiz modelBiz;
	
	/**
	 * 
	 */
	@Autowired
	private IBasicCategoryBiz basicCategoryBiz;
	
	
	/**
	 * 根据appID查询产品的总数
	 * @param appId
	 * @return
	 */
	@Override
	public int getCountByAppId(Integer appId) {
		// TODO Auto-generated method stub
		return productDao.count(appId);
	}
	
	/**
	 * 根据appid查询产品分页
	 * @param appId 
	 * @param page：页面信息
	 * @param orderBy：排序方式
	 * @param order：是否采用升序
	 * @return
	 */
	@Override
	public List<ProductEntity> queryListPageByAppId(Integer appId, PageUtil page) {
		return productDao.queryPageByAppId(appId, page.getPageNo(), page.getPageSize());
	}
	
	
	/**
	 * 根据分类与时间查询商品列表
	 * @param categoryId :分类id
	 * @param dateTime ：更新时间
	 * @param appId ：应用appID
	 * @return
	 */
	@Override
	public List<ProductEntity> query(Integer categoryId, String dateTime,
			Integer appId) {
		// TODO Auto-generated method stub
		return productDao.queryListByTime(categoryId, dateTime, appId);
	}
	
	
	
	/**
	 * 根据模块ID查询商品信息带分页
	 * @param appId 应用ID
	 * @param modelId 模块ID
	 * @param categoryId 分类ID
	 * @param begin 分页开始位置
	 * @param end 分页结束位置
	 * @return 商品集合
	 */
	public List<ProductEntity> queryPageByModelId(Integer appId,Integer modelId,Integer categoryId,PageUtil page){
		if(categoryId == 0){
			categoryId = null;
		}
		return this.productDao.queryPageByModelId(appId,modelId,categoryId,page.getPageNo()*page.getPageSize(), page.getPageSize());
	}
	
	/**
	 * 根据模块ID查询商品总数
	 * @param appId 应用Id
	 * @param modelId 模块ID
	 * @param categoryId 分类ID
	 * @return 商品总数
	 */
	public int queryCountByModelId(Integer appId,Integer modelId,Integer categoryId){
		if(categoryId == 0){
			categoryId = null;
		}		
		return this.productDao.queryCountByModelId(appId,modelId,categoryId);
	}
	
	@Override
	public List<ProductEntity> queryList(int appId,
			int[] basicCategoryIds, 
			String orderBy, boolean order,Integer productShelf,String flag,String noFlag) {
		// TODO Auto-generated method stub
		if (orderBy!=null) {
			if (orderBy.equals("sort")) {
				orderBy = "basic_sort";
			} else if (orderBy.equals("date")) {
				orderBy = "basic_datetime";
			} else if (orderBy.equals("hit")) {
				orderBy = "basic_hit";
			} else if (orderBy.equals("updatedate")) {
				orderBy = "basic_updatedate";
			} else if (orderBy.equals("id")) {
				orderBy = "basic_id";
			}else if (orderBy.equals("price")){
				orderBy = "basic_id";
			}else if (orderBy.equals("sale")){
				orderBy = "product_sale";
			}else{
				orderBy=null;
			}
		}
		if (basicCategoryIds.length == 0) {
			basicCategoryIds = null;
		}
		return productDao.queryList(appId, basicCategoryIds,  orderBy, order, productShelf>=0?productShelf:null,flag==null?null:flag.split(","),noFlag==null?null:noFlag.split(","));
	}

	@Override
	public int getSearchCount(ContentModelEntity contentModel,Map wherMap, int websiteId,List  ids) {
		if (contentModel!=null) {
			return productDao.getSearchCount(contentModel.getCmTableName(),wherMap, websiteId,ids);
		}
		return productDao.getSearchCount(null,wherMap, websiteId,ids);
	}

	@Override
	public List<ProductEntity> queryListForSearch(
			ContentModelEntity conntentModel, Map whereMap, PageUtil page,
			int appId, List ids, Map orders) {
		List<ProductEntity> porductList = new ArrayList<ProductEntity>();
		int start = 0;
		int end = 0;
		String tableName = null;
		if (page!=null) {
			start = page.getPageNo();
			end = page.getPageSize();
		}
		if (conntentModel!=null) {
			tableName = conntentModel.getCmTableName();
		}
		// 查找所有符合条件的文章实体
		porductList = productDao.queryListForSearch(tableName, whereMap, start, end, appId,ids,orders);
		

		return porductList;
	}

	@Override
	public List<ProductEntity> queryByShelf(Integer appId,
			Integer productShelf, Integer categoryId,PageUtil page) {
		if(categoryId!=null && categoryId==0){
			categoryId = null;
		}
		// TODO Auto-generated method stub
		return this.productDao.queryPageByShelf(appId, productShelf,categoryId, page.getPageNo(), page.getPageSize());
	}

	@Override
	public int getCountByShelf(Integer appId, Integer productShelf, Integer categoryId) {
		if(categoryId!=null && categoryId==0){
			categoryId = null;
		}
		return productDao.getCountByShelf(appId, productShelf,categoryId);
	}

	
	
	@Override
	public void updateProductShelf(String[] productIds,ProductEnum productShelf) {
		// TODO Auto-generated method stub
		if (!StringUtil.isBlank(productIds)) {
			for (int i = 0; i < productIds.length; i++) {
				int number = Integer.parseInt(productIds[i]);
				// 获取id，查询该文章是否在该站点下
				ProductEntity product = (ProductEntity) this.productDao.getEntity(number);
				product.setProductShelf(productShelf);
				this.productDao.updateEntity(product);
			}
		}
	}
	

	@Override
	public List<ProductEntity> queryProducntSpecificationForSearch(
			ContentModelEntity conntentModel, Map<String, List> map, int appId, List ids,
			Map sortMap, PageUtil page,String orderBy,boolean order,String flag,String noFlag) {
		// TODO Auto-generated method stub
		String tableName = null;
		if (conntentModel!=null) {
			tableName = conntentModel.getCmTableName();
		}
		return this.productDao.queryProductSpecificationForSearch(tableName, map, appId, ids, sortMap, page,orderBy,order,flag,noFlag);
	}
	
	@Override
	public int getProducntSpecificationSearchCount(ContentModelEntity contentModel,Map wherMap, int websiteId,List  ids,String flag,String noFlag) {
		if (contentModel!=null) {
			return productDao.getProducntSpecificationSearchCount(contentModel.getCmTableName(),wherMap, websiteId,ids,flag, noFlag);
		}
		return productDao.getProducntSpecificationSearchCount(null,wherMap, websiteId,ids, flag, noFlag);
	}

	
	@Override
	public List<ProductEntity> querySearch(int appId, int categoryId,Map map) {
		// TODO Auto-generated method stub
		return productDao.querySearch(appId, categoryId, map);
	}

	@Override
	public int getCountByDiyField(int appId, Integer categoryId,
			Map productMap, Map diyFieldMap,String tableName) {
		// TODO Auto-generated method stub
		return this.productDao.getCountByDiyField(appId, categoryId, productMap, diyFieldMap,tableName);
	}

	@Override
	public List<ProductEntity> queryByDiyField(int appId, Integer categoryId,
			Map map, Map diyFieldMap, PageUtil page, String tableName) {
		// TODO Auto-generated method stub
		return this.productDao.queryByDiyField(appId, categoryId, map, diyFieldMap, page, tableName);
	}

	@Override
	public List<ProductEntity> queryAllShelf(Integer appId,
			Integer productShelf, Integer categoryId) {
		// TODO Auto-generated method stub
		return this.productDao.queryAllShelf(appId, productShelf, categoryId);
	}

	@Override
	public List queryByCategoryForBean(int appId, Integer categoryId,
			PageUtil page, boolean _isHasChilds) {
		// TODO Auto-generated method stub
		ModelEntity model=modelBiz.getEntityByModelCode(net.mingsoft.mall.constant.ModelCode.MALL_CATEGORY);
		//查询该分类下的所有子分类
		List list = categoryBiz.queryChildrenCategory(categoryId,appId,model.getModelId());
		// 分类不存在直接返回
		if (list == null || list.size() == 0) {
						return null;
		}
		//如果没有子分类
		if(list.size()==1){
			ColumnEntity column = (ColumnEntity) columnBiz.getEntity(categoryId);
				if (column.getColumnContentModelId() != 0) { // 存在自定义模型
					ContentModelEntity contentModel = (ContentModelEntity) contentModelBiz.getEntity(column.getColumnContentModelId());
						return productDao.queryByCategoryForBean(categoryId, null, page, contentModel.getCmTableName());
					} else {
						return productDao.queryByCategoryForBean(categoryId, null, page, null);
					}
				}else{
					if (_isHasChilds) {
						return productDao.queryByCategoryForBean(categoryId, list, page,null);
					} else {
						return productDao.queryByCategoryForBean(categoryId, null, page,null);
					}
		}
	}

	@Override
	public void saveProduct(ProductEntity product, List<BasicCategoryEntity> basicCategoryList) {
		// TODO Auto-generated method stub
		this.saveBasic(product);
		List<BasicCategoryEntity> newBasicCategoryList = new ArrayList<BasicCategoryEntity>();
		for(int i=0;i<basicCategoryList.size();i++){
			BasicCategoryEntity basicCategory= basicCategoryList.get(i);
			basicCategory.setBcBasicId(product.getBasicId());
			newBasicCategoryList.add(basicCategory);
			
		}
		if(newBasicCategoryList==null || newBasicCategoryList.size()<=0){
			return;
		}
		this.basicCategoryBiz.deleteEntity(product.getBasicId());
		this.basicCategoryBiz.saveBatch(newBasicCategoryList);
	}

	@Override
	public void updateProduct(ProductEntity product, List<BasicCategoryEntity> basicCategoryList) {
		
		this.updateBasic(product);
		if(basicCategoryList==null || basicCategoryList.size()<=0){
			return;
		}
		if(product!=null){
			this.basicCategoryBiz.deleteEntity(product.getBasicId());
			this.basicCategoryBiz.saveBatch(basicCategoryList);
		}
	}

	@Override
	public List<ProductEntity> queryByBasicIds(int appId,Integer categoryId,List<Integer> basicIds, PageUtil page, String orderBy, boolean order,Integer productShelf) {
		if (orderBy!=null) {
			if (orderBy.equals("sort")) {
				orderBy = "basic_sort";
			} else if (orderBy.equals("date")) {
				orderBy = "basic_datetime";
			} else if (orderBy.equals("hit")) {
				orderBy = "basic_hit";
			} else if (orderBy.equals("updatedate")) {
				orderBy = "basic_updatedate";
			} else if (orderBy.equals("id")) {
				orderBy = "basic_id";
			}else if (orderBy.equals("price")){
				orderBy = "basic_id";
			}else if (orderBy.equals("sale")){
				orderBy = "product_sale";
			}else{
				orderBy=null;
			}
		}
		return this.productDao.queryByBasicIds(appId,categoryId,basicIds, page, orderBy, order,productShelf);
	}

	@Override
	public int getCountByBasicIds(int appId, Integer categoryId, List<Integer> basicIds, Integer productShelf) {
		return this.productDao.getCountByBasicIds(appId, categoryId, basicIds, productShelf);
	}

	@Override
	public void delete(int appId, int[] ids) {
		
	}

	/**
	 * 商城的搜索接口
	 * @param appId		应用ID
	 * @param modelId	模块ID
	 * @param category	分类ID
	 * @param brand		品牌ID
	 * @param price		字符串
	 * @param specs		规格值字符串       颜色:白|黑,尺寸:1寸
	 */
	@Override
	public List<ProductEntity> search(int appId, int modelId, Integer category, int[] brands, String price, String specs, String sort) {
		
		if (brands != null && brands.length == 0) brands = null;
		
		int[] categories = null;
		if (category == 0) {
			category = null;
		}
		else {
			categories = categoryBiz.queryChildrenCategoryIds(category, appId, modelId);
			if (categories.length == 0) categories = new int[]{category};
		}
		
		// 解析价格数据
		double minPrice = 0;
		double maxPrice = Double.MAX_VALUE;
		if (!StringUtil.isBlank(price)){
			String[] priceArr = price.split("-");
			int index = price.indexOf("-");
			if (index == -1){
				minPrice = maxPrice = Double.parseDouble(price);
			}
			else if (priceArr.length == 2){
				minPrice = Double.parseDouble(priceArr[0]);
				maxPrice = Double.parseDouble(priceArr[1]);
			}
			else if (priceArr.length == 1) {
				if (index == 0) {
					maxPrice = Double.parseDouble(priceArr[0]);
				}
				else if(index == price.length() - 1){
					minPrice = Double.parseDouble(priceArr[0]);
				}
			}
		}
		
		String specSql = "select tmp0.ps_product_id from ";
		String conSql = "";
		
		// 解析规格数据, 拼接成sql传入myBatis
		if (!StringUtil.isBlank(specs)){
			
			String[] specsArr = specs.split(",");
			int specLen = specsArr.length;
			for (int j = 0; j < specLen; j ++){
				
				String spec = specsArr[j];
				String[] specArr = spec.split(":");
				String specName = specArr[0];
				String specValues = specArr[1];
				
				String[] specValueArr = null;
				if (specValues.indexOf("@") == -1){
					specValueArr = new String[]{specValues};
				}
				else{
					specValueArr = specValues.split("@");
				}
				 
				conSql += "(select ps_product_id from mall_product_specification where ps_spec_name = '"+ specName +"' and ps_spec_value in (";
				
				for (int i = 0; i < specValueArr.length; i ++){
					String specValue = specValueArr[i];
					if (i != 0){
						conSql += ",";
					}
					
					conSql += "'"+ specValue +"'";
					
				}
				conSql += ")) as tmp" + j + (j == specLen - 1 ? " " : ", ");
			}
			
			specSql += conSql;
			
			// 规格条件不为1的时候 需要添加 where条件
			if (specLen != 1){
				specSql += " where 1 = 1 ";
				for (int j = 1; j < specLen; j ++){
					specSql += "and tmp0.ps_product_id = tmp" + j + ".ps_product_id ";  
				}
			}
			specSql += " group by ps_product_id";
			specSql = "("+specSql+") as tmpSpec ";
			
			LOG.debug("规格拼接sql:" + specSql);
		}
		else {
			specSql = null;
		}
		
		String orderBy = null;
		String orderByType = "desc";
		
		// 解析排序数据
		if (!StringUtil.isBlank(sort)){
			
			int index = sort.indexOf("-");
			if (index == -1){
				orderBy = "product_" + sort;
			}
			else {
				String[] arr = sort.split("-");
				orderBy = "product_" + arr[0];
				orderByType = arr[1];
			}
		}
		
		List<ProductEntity> list = productDao.search(appId, modelId, categories, brands, minPrice, maxPrice, specSql, orderBy, orderByType);
		
		return list;
	}

	@Override
	public List<ProductEntity> getOthersPurchase(int appId, int productId, int categoryId, int num) {
		
		List<Integer> peopleIds = productDao.getPeopleIdsByProductId(appId, productId);
		List<ProductEntity> list = null;
		if (peopleIds != null && peopleIds.size() == 0){
			return new ArrayList<ProductEntity>();
		}
		
		list = productDao.getProductsByPeopleIds(appId, categoryId, peopleIds, num);
		
		return list;
	}
}

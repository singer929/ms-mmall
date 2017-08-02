
package net.mingsoft.mall.action.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.entity.ListJson;
import com.mingsoft.basic.biz.ICategoryBiz;
import com.mingsoft.basic.biz.IColumnBiz;
import com.mingsoft.basic.entity.AppEntity;
import com.mingsoft.basic.entity.CategoryEntity;
import com.mingsoft.basic.entity.ColumnEntity;
import com.mingsoft.parser.IParserRegexConstant;
import com.mingsoft.util.FileUtil;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.mall.action.BaseAction;
import net.mingsoft.mall.biz.IProductBiz;
import net.mingsoft.mall.constant.ModelCode;
import net.mingsoft.mall.constant.e.ProductEnum;
import net.mingsoft.mall.entity.ProductEntity;
import net.mingsoft.mall.parser.MallParser;

/**
 * 
 * <p>
 * <b>铭飞科技-商品</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 *
 * @author 成卫雄
 *                QQ:330216230
 *
 * <p>
 * Comments:产品控制层外部Ajax请求,继承BasicAction
 * </p>
 *
 * <p>
 * Create Date:2014-12-1
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Controller("webProductAction")
@RequestMapping("/mall/product")
public class ProductAction extends BaseAction{
	
	/**
	 * 注入商品详情业务层
	 */
	@Autowired
	private IProductBiz productBiz;	
	
	@Autowired
	private IColumnBiz columnBiz;	
	
	@Autowired
	private MallParser mallParser;
	
	@Resource(name="categoryBiz")
	private ICategoryBiz categoryBiz;
	
	
	@RequestMapping("/{productId}/getEntity")
	public void getEntity(@PathVariable("productId")Integer productId, HttpServletRequest request, HttpServletResponse response){
		if(!StringUtil.isInteger(productId)){
			this.outJson(response, ModelCode.MALL_PRODUCT,false,this.getResString("err"));
			return ;
		}
		ProductEntity product =  (ProductEntity) this.productBiz.getEntity(productId);
		if(product != null){
			this.outJson(response, ModelCode.MALL_PRODUCT,true,JSONObject.toJSONString(product));
		}else{
			this.outJson(response, ModelCode.MALL_PRODUCT,false,this.getResString("err"));
		}
	}

	/**
	 * 根据商品 查询购买过此商品的用户还购买过哪些其他商品
	 * @param productId		商品id，必须字段
	 * @param num			返回数据个数，不足则全部，不传默认全部
	 * @param categoryId 	类型id，所需要查询商品的类别，不传默认所有类别
	 * @param request	
	 * @param response
	 */
	@RequestMapping("/getOthersPurchase")
	public void getOthersPurchase(@RequestParam("productId") int productId, HttpServletRequest request, HttpServletResponse response) {
		
		int num = BasicUtil.getInt("num");
		int categoryId = BasicUtil.getInt("categoryId");
		int appId = BasicUtil.getAppId();
		
		List<ProductEntity> list = productBiz.getOthersPurchase(appId, productId, categoryId, num);
		
		if (list == null){
			outJson(response, ModelCode.MALL_PRODUCT, false, getResString("err"));
		}
		else {
			String jsonStr = JSONArray.toJSONString(list);
			outJson(response, ModelCode.MALL_PRODUCT, true, jsonStr);
		}
	}

	
	
	/**
	 * 商城搜索功能 search.do
	 * 主要实现明确ID或者值的搜索, 名称关键字的搜索有其他接口
	 * 
	 * @param request
	 * <i>请求参数包含字段信息参考：</i><br/>
	 * <br/>{
	 * <br/>	*brand: 品牌id可以为数组 (与category至少有一个必填)
	 * <br/>	*category: 分类ID, (与brand至少有一个必填)
	 * <br/>	price: 商品价格, 可以是区间值 用 "-"分隔, 例如: "23.66", "12-45", "-324", "123-" 
	 * <br/>	spec:  规格筛选的字符串, 规格名:规格值,规格名:规格值@规格值 , 例如:  "颜色:白,尺寸:1寸@2寸" (@ 表示或的关系)  
	 * <br/> 	sort:  排序方式, 字段-方式, 例如: "price-asc"  (方式默认为desc)，支持字段：price（价格）sale（销量）inventory（库存）
	 * <br/> 	dataType: 数据类型: "json" 表示json格式数据返回, "html" 表示返回数据解析后的html页面, 模板取分类下的列表模板
	 * <br/>} 
	 * @param response
	 */
	@RequestMapping("/search")
	public void search(HttpServletRequest request, HttpServletResponse response) {
		
		String dataType = BasicUtil.getString("dataType");
		int[] brands = BasicUtil.getInts("brand");
		String price = BasicUtil.getString("price");
		String spec = BasicUtil.getString("spec");
		String sort = BasicUtil.getString("sort");
		Integer category = BasicUtil.getInt("category");
		int appId = BasicUtil.getAppId();
		int modelId = this.getModelCodeId(request, ModelCode.MALL_CATEGORY);
		
		//BasicUtil.startPage();
		List<ProductEntity> list = productBiz.search(appId, modelId, category, brands, price, spec, sort);
	
		//BasicUtil.endPage(list);
		
		// 数据类型是 json 则ajax方式返回json数据, 否则返回跳转html
		if (dataType.equals("json")){
			String jsonStr = JSONArray.toJSONString(list);
			outJson(response, jsonStr);
		}
		else {
			
			String webSiteTmpPath = "";
			String tmpPath = getRealPath(request, IParserRegexConstant.REGEX_SAVE_TEMPLATE);
			AppEntity app = BasicUtil.getApp();
			// 获取模版名称
			String tmpName = app.getAppStyle();
			
			webSiteTmpPath = tmpPath + File.separator + app.getAppId() + File.separator + tmpName;
			String templatePath = "";
			
			// 没有分类数据根据品牌寻找模板
			if (category == null || category == 0){
				if (brands == null || brands.length == 0){
					outJson(response, false);
					return;
				}
				
				CategoryEntity cate = (CategoryEntity)this.categoryBiz.getEntity(brands[0]);
				ColumnEntity col = (ColumnEntity) columnBiz.getEntity(cate.getCategoryCategoryId());
				templatePath = col.getColumnListUrl();
			}
			// 有分类根据分类查找模板
			else{
				ColumnEntity col = (ColumnEntity) columnBiz.getEntity(category);
				templatePath = col.getColumnListUrl();
			}
			
			// 读取模板内容
			String htmlContent = FileUtil.readFile(webSiteTmpPath + File.separator + templatePath);
			
			Map map  = new HashMap();                          
			map.put(MallParser.PRODUCT_SEARCH_LIST_ARTICLE, list);
			
			// 对模板内容进行解析
			htmlContent = mallParser.parse(htmlContent, app, list, map);// generaterFactory.buildSearch(app, htmlContent, webSiteTmpPath, no, articleList, null,column, page);				
			
			outString(response, htmlContent);
		}
	}
}

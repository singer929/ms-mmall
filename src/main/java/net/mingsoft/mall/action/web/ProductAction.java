
package net.mingsoft.mall.action.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.entity.ListJson;
import com.mingsoft.basic.biz.IBasicCategoryBiz;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.mall.action.BaseAction;
import net.mingsoft.mall.biz.IProductBiz;
import net.mingsoft.mall.constant.ModelCode;
import net.mingsoft.mall.constant.e.ProductEnum;
import net.mingsoft.mall.entity.ProductEntity;

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
	
	/**
	 * 基础分类关联业务层
	 */
	@Autowired
	private IBasicCategoryBiz basicCategoryBiz;
	
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
	 * 根据分类id和指定的条件查询商品信息
	 * @param categoryId 分类id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/{categoryId}/queryByCategory")
	public void queryByCategory(@PathVariable("categoryId")Integer categoryId, HttpServletRequest request,HttpServletResponse response){
		//分页
		Integer pageNo = this.getInt(request, "pageNo", 1);
		//分页数量
		Integer pageSize = this.getInt(request, "pageSize", 10);
		
		//获取依据排序字段
		String orderBy = request.getParameter("orderBy");
		
		//是否降序
		String order = request.getParameter("order");
		int appId = getAppId(request);
		
		/**
		 * 判断文章列表的orderby属性
		 */
		if (StringUtil.isBlank(order)) {
			order = "desc";
		}
		
		//获取查询 依据查询条件的集合
		String categoryType = request.getParameter("categoryTypeIds");
		List<Integer> basicIds = new ArrayList<Integer>();
		
		if(!StringUtil.isBlank(categoryType) && StringUtil.isIntegers(categoryType.split(","))){
			
			String[] _categoryType = categoryType.split(",");
			//Integer[] ids = StringUtil.stringsToIntegers(categoryType.split(","));
			StringUtil.stringsToIntegers(categoryType.split(","));
			//将获取到的集合转换成整型列表类型
			int ids[] = new int[_categoryType.length];
			for (int i = 0; i < ids.length; i++){
				ids[i] = Integer.parseInt(_categoryType[i]);
			}
				
			//获取符合条件的商品id集合
			basicIds = basicCategoryBiz.queryBasicIdsByCategoryId(ids);
		}
		
		if(basicIds.size() <= 0){
			basicIds = null;
			//this.outJson(response, null);
			//this.outJson(response, JSONObject.toJSONStringWithDateFormat(json,"yyyy-MM-dd HH:mm:ss"));
		}
		int count = productBiz.getCountByBasicIds(appId, categoryId, basicIds,ProductEnum.ON_SHELF.toInt());
		PageUtil page=new PageUtil(pageNo,pageSize,count,getUrl(request)+"/queryByCategory/list.do");
		
		List<ProductEntity> list = productBiz.queryByBasicIds(appId,categoryId,basicIds, page, orderBy, order.equals("desc") ? true : false,ProductEnum.ON_SHELF.toInt());
		ListJson json = new ListJson(count,list);
		this.outJson(response, JSONObject.toJSONStringWithDateFormat(json,"yyyy-MM-dd HH:mm:ss"));
	}
	
	
	/**
	 * 商城搜索功能 search.do
	 * 主要实现明确ID或者值的搜索, 名称关键字的搜索有其他接口
	 * 
	 * 关键字如下表示:
	 * 
	 * 品牌: brand=123
	 * 价格: price=23.00  间于:price=12-34  大于:price=123- 小于:price=-234
	 * 规格: spec=颜色:白,尺寸:1寸
	 * 产品名称: name=三路奶粉  (模糊)
	 * 分类名称: category=123   
	 * 
	 * 排序:
	 * sort=price 默认是desc降序 升序为: sort=price-asc
	 * 支持字段:
	 * 		价格:price
	 * 		销量:sale
	 * 		    .....
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping("/search")
	public void search(HttpServletRequest request, HttpServletResponse response) {
		
		int[] brands = BasicUtil.getInts("brand");
		String price = BasicUtil.getString("price");
		String spec = BasicUtil.getString("spec");
		Integer category = BasicUtil.getInt("category");
		int appId = BasicUtil.getAppId();
		
		BasicUtil.startPage();
		List<ProductEntity> list = productBiz.search(appId, category, brands, price, spec);
		BasicUtil.endPage(list);
		
		String jsonStr = JSONObject.toJSONString(list);
		
		outJson(response, jsonStr);
	}
}

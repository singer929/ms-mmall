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
import com.mingsoft.basic.biz.ICategoryBiz;
import com.mingsoft.basic.entity.CategoryEntity;

import net.mingsoft.mall.action.BaseAction;


/**
 * 
 * <p>
 * <b>铭飞科技-商城</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 *
 * @author 史爱华
 *
 * <p>
 * Comments:商品分类外部请求栏目属性使用
 * </p>
 *
 * <p>
 * Create Date:2015-08-18
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Controller("webColumnType")
@RequestMapping("/mall/column/type")
public class ColumnTypeAction extends BaseAction {
	
	
	/**
	 * 分类业务层
	 */
	@Autowired
	private ICategoryBiz categoryBiz;
	
	/**
	 * 根据分类id查询该分类下的栏目属性列表
	 * @param categoryId 分类id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/{categoryId}/query")
	public void query(@PathVariable("categoryId")Integer categoryId,HttpServletRequest request,HttpServletResponse response){
		//获取应用id
		int appId = this.getAppId(request);
		//获取模块id
		int modelId = this.getModelCodeId(request, net.mingsoft.mall.constant.ModelCode.MALL_CATEGORY_TYPE);
		
		CategoryEntity category = new CategoryEntity();
		category.setCategoryAppId(appId);
		category.setCategoryModelId(modelId);
		category.setCategoryId(categoryId);
		List<CategoryEntity> categoryList = categoryBiz.queryChilds(category);
		
		this.outJson(response,JSONObject.toJSONString(categoryList));
	}
	
	/**
	 * 根据分类id查询该分类下的所有子栏目属性列表
	 * @param categoryId 分类id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/{categoryId}/queryChild")
	public void queryChild(@PathVariable("categoryId")Integer categoryId,HttpServletRequest request,HttpServletResponse response){
		//获取应用id
		int appId = this.getAppId(request);
		//获取模块id
		int modelId = this.getModelCodeId(request, net.mingsoft.mall.constant.ModelCode.MALL_CATEGORY_TYPE);
		List<CategoryEntity> categoryList = categoryBiz.queryChildrenCategory(categoryId, appId, modelId);
		
		List<CategoryEntity> cCategoryList = new ArrayList<CategoryEntity>();
		for(int i=0;i<categoryList.size();i++){
			CategoryEntity pCategory = categoryList.get(i);
			for(int n=0;n<categoryList.size();n++){
				CategoryEntity cCategory = categoryList.get(n);
				if(cCategory.getCategoryCategoryId()==pCategory.getCategoryId()){
					cCategoryList.add(cCategory);
				}
			}
			
		}
		this.outJson(response,JSONObject.toJSONString(cCategoryList));
	}
}

package net.mingsoft.mall.action.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import net.mingsoft.mall.action.BaseAction;
import net.mingsoft.mall.biz.IProductBiz;
import net.mingsoft.mall.biz.IProductSpecificationDetailBiz;
import net.mingsoft.mall.constant.ModelCode;
import net.mingsoft.mall.entity.ProductEntity;
import net.mingsoft.mall.entity.ProductSpecificationDetailEntity;

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
 * @author MS
 *
 * <p>
 * Comments: 商品规格明细控制层
 * </p>
 *
 * <p>
 * Create Date:2014-11-26
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Controller("webProductSpecificationDetailAction")
@RequestMapping("/mall/productSpecificationDetail")
public class ProductSpecificationDetailAction extends BaseAction{
	
	@Autowired 
	private IProductSpecificationDetailBiz detailBiz;
	
	@Autowired 
	private IProductBiz productBiz;
	
	/**
	 * 获取规格列表,返回JSON数据
	 * @param request
	 * @param response
	 */
	@RequestMapping("/{detailId}/get")
	public void get(@PathVariable int detailId, HttpServletRequest request, HttpServletResponse response){
		
		ProductSpecificationDetailEntity detail = (ProductSpecificationDetailEntity) detailBiz.getEntity(detailId);
		int productId = detail.getProductId();
		ProductEntity product = (ProductEntity) productBiz.getEntity(productId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product", product);
		map.put("detail", detail);
		
		String jsonStr = JSONObject.toJSONString(map);
		
		this.outJson(response, ModelCode.MALL_SPECIFICATIONS, true, jsonStr);
	}
}

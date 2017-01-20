package net.mingsoft.mall.action.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.mall.action.BaseAction;
import net.mingsoft.mall.biz.IProductSpecificationDetailBiz;
import net.mingsoft.mall.biz.ISpecificationBiz;
import net.mingsoft.mall.constant.ModelCode;
import net.mingsoft.mall.entity.ProductSpecificationDetailEntity;
import net.mingsoft.mall.entity.SpecificationEntity;

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
@RequestMapping("/mall/specificationDetail")
public class ProductSpecificationDetailAction extends BaseAction{
	
	@Autowired 
	private IProductSpecificationDetailBiz detailBiz;
	
	/**
	 * 获取规格列表,返回JSON数据
	 * @param request
	 * @param response
	 */
	@RequestMapping("/{detailId}/get")
	public void get(@PathVariable int detailId, HttpServletRequest request, HttpServletResponse response){
		
		//int appId = BasicUtil.getAppId();
		ProductSpecificationDetailEntity detail = (ProductSpecificationDetailEntity) detailBiz.getEntity(detailId);
		
		String jsonStr = JSONObject.toJSONString(detail);
		if(detail != null){
			this.outJson(response, ModelCode.MALL_SPECIFICATIONS, true, jsonStr);
		}
		else{
			this.outJson(response, ModelCode.MALL_SPECIFICATIONS, false);
		}
	}
}

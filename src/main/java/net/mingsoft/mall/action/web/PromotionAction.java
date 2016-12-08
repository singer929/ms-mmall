/**
 * 
 */
package net.mingsoft.mall.action.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import net.mingsoft.mall.action.BaseAction;
import net.mingsoft.mall.biz.IPromotionBiz;
import net.mingsoft.mall.entity.PromotionEntity;

/**
 * 
 * 
 * 
 * <p>
 * <b>铭飞科技</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 * 
 * @author killfen
 * 
 * <p>
 * Comments:促销
 * </p>
 * 
 * <p>
 * Create Date:2015-6-3
 * </p>
 * 
 * <p>
 * Modification history:
 * </p>
 */
@Controller("webPromotionAction")
@RequestMapping("/mall/promotion")
public class PromotionAction extends BaseAction{

	/**
	 * 注入商品详情业务层
	 */
	@Resource(name="promotionBizImpl")
	private IPromotionBiz promotionBiz;	
	
	/**
	 * 查询商品详情列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/query")
	@ResponseBody
	public void query(HttpServletRequest request,HttpServletResponse response){
		List list = promotionBiz.queryPromotionProduct(this.getAppId(request), null);
		this.outJson(response, JSONObject.toJSON(list));
	}
	
	/**
	 * 根据商品编号活取促销信息，日期、时间、销量
	 * @param request
	 * @param response
	 */
	@RequestMapping("/{productId}/detail")
	@ResponseBody
	public void date(@PathVariable("productId") int productId,HttpServletRequest request,HttpServletResponse response){
		PromotionEntity promotion = promotionBiz.getByProductId(this.getAppId(request), productId);
		this.outJson(response, JSONObject.toJSON(promotion));
	}
	
	

}

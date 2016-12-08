/**
 * 
 */
package net.mingsoft.mall.action.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.action.BaseAction;
import com.mingsoft.util.StringUtil;

import net.mingsoft.mall.biz.IProductSpecificationsBiz;
import net.mingsoft.mall.constant.ModelCode;
import net.mingsoft.mall.entity.ProductSpecificationsEntity;

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
 * @author 成卫雄
 *                QQ:330216230
 *
 * <p>
 * Comments:商品规格外部请求JSON
 * </p>
 *
 * <p>
 * Create Date:2014-12-11
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Controller("webproductSpecifications")
@RequestMapping("/mall/productSpecifications")
public class ProductSpecificationAction extends BaseAction{

	/**
	 * 产品规格关联业务层
	 */
	@Autowired
	private IProductSpecificationsBiz productSpecificationBiz;
	
	/**
	 * 当前商品规格集合
	 */
	@RequestMapping("/{productId}/list")
	@ResponseBody
	public void list(@PathVariable("productId")Integer productId,HttpServletRequest request,HttpServletResponse response){
		//验证传入的商品ID
		if(!StringUtil.isInteger(productId)){
			this.outJson(response, ModelCode.MALL_SPECIFICATIONS,false,null);
			return ;
		}
		//根据商品Id查询当前商品的规格数据
		List<ProductSpecificationsEntity> psList = this.productSpecificationBiz.queryListJsonByProduct(productId);
		this.outJson(response, JSONObject.toJSONString(psList));
	}
	
	/**
	 * 当前商品规格集合
	 */
	@RequestMapping("/{productSpecificationsId}/queryByProductSpecificationsId")
	@ResponseBody
	public void queryByProductSpecificationsId(@PathVariable("productSpecificationsId")Integer productSpecificationsId,HttpServletRequest request,HttpServletResponse response){
		//验证传入的商品ID
		if(!StringUtil.isInteger(productSpecificationsId)){
			this.outJson(response, ModelCode.MALL_SPECIFICATIONS,false,null);
			return ;
		}
		//根据商品Id查询当前商品的规格数据
		List<ProductSpecificationsEntity> psList = this.productSpecificationBiz.queryListByProductSpecificationsId(productSpecificationsId);
		this.outJson(response, JSONObject.toJSONString(psList));
	}
	
}

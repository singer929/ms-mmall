/**
 * 
 */
package net.mingsoft.mall.action.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingsoft.base.action.BaseAction;
import com.mingsoft.util.StringUtil;

import net.mingsoft.mall.biz.IProductSpecBiz;
import net.mingsoft.mall.constant.ModelCode;

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
 * Comments: 商品规格前台 请求
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
@Controller("webProductSpec")
@RequestMapping("/mall/productSpec")
public class ProductSpecAction extends BaseAction{

	/**
	 * 产品规格关联业务层
	 */
	@Autowired
	private IProductSpecBiz specBiz;
	
	/**
	 * 当前商品规格集合
	 */
	@RequestMapping("/{productId}/list")
	@ResponseBody
	public void list(@PathVariable("productId")int productId, HttpServletRequest request, HttpServletResponse response){
		
		if(!StringUtil.isInteger(productId)){
			this.outJson(response, ModelCode.MALL_SPECIFICATIONS, false, null);
			return;
		}
		
		//根据商品Id查询当前商品的规格数据
		String str = specBiz.getDataStrByProductId(productId);
		this.outJson(response, str);
	}
	
	/**
	 * 根据商品规格获取上平规格数据
	 */
	@RequestMapping("/{psId}/prodcutSpecList")
	@ResponseBody
	public void queryByProductSpecificationsId(@PathVariable("psId")int psId, HttpServletRequest request, HttpServletResponse response){

		if(!StringUtil.isInteger(psId)){
			this.outJson(response, ModelCode.MALL_SPECIFICATIONS, false, "产品规格id不是整数");
			return ;
		}
		//根据商品Id查询当前商品的规格数据
		//List<ProductSpecificationsEntity> psList = this.specBiz.queryListByProductSpecificationsId(productSpecificationsId);
		//this.outJson(response, JSONObject.toJSONString(psList));
	}
	
}

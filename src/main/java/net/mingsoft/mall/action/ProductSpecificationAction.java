/**
 * 
 */
package net.mingsoft.mall.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingsoft.util.StringUtil;

import net.mingsoft.mall.biz.IProductSpecificationsBiz;
import net.mingsoft.mall.constant.ModelCode;

/**
 * 
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
 * Comments:商品规格
 * </p>
 * 
 * <p>
 * Create Date:2015-5-24
 * </p>
 * 
 * <p>
 * Modification history:
 * </p>
 */
@Controller("productSpecifications")
@RequestMapping("/${managerPath}/mall/productSpecifications")
public class ProductSpecificationAction extends BaseAction{

	/**
	 * 产品规格关联业务层
	 */
	@Autowired
	private IProductSpecificationsBiz productSpecificationBiz;
	
	
	/**
	 * 当前商品规格集合
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void list(HttpServletRequest request,HttpServletResponse response){
		String[] productIds = request.getParameterValues("productId");
		int[] _productIds = null;
		if (StringUtil.isIntegers(productIds)) {
			_productIds = StringUtil.stringsToInts(productIds);
		}
		this.productSpecificationBiz.deleteEntityByProductId(_productIds);
	}
	
	
	
}

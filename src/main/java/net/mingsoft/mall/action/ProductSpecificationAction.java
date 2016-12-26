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

import net.mingsoft.mall.biz.IProductSpecificationBiz;
import net.mingsoft.mall.biz.IProductSpecificationDetailBiz;
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
@Controller("productSpecification")
@RequestMapping("/${managerPath}/mall/productSpecification")
public class ProductSpecificationAction extends BaseAction{

	/**
	 * 产品规格关联业务层
	 */
	@Autowired
	private IProductSpecificationBiz productSpecBiz;
	
	/**
	 * 产品规格明细关联业务层
	 */
	@Autowired
	private IProductSpecificationDetailBiz detailBiz;
	
	/**
	 * 删除当前商品的规格(设置规格页面)
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void list(HttpServletRequest request, HttpServletResponse response){
		
		String[] productIdStr = request.getParameterValues("productId");
		
		// 参数类型不匹配直接退出
		if (!StringUtil.isIntegers(productIdStr)) return;
		
		int[] productIds = StringUtil.stringsToInts(productIdStr);
		
		// 删除产品规格表中的数据
		productSpecBiz.deleteEntityByProductIds(productIds);
		// 删除产品规格明细表中的数据
		detailBiz.deleteByProductIds(productIds);
	}
}

/**
 * 
 */
package net.mingsoft.mall.constant;

import com.mingsoft.base.constant.e.BaseEnum;

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
 * Comments:
 * </p>
 * 
 * <p>
 * Create Date:2015-5-9
 * </p>
 * 
 * <p>
 * Modification history:
 * </p>
 */ 
public  enum ModelCode implements BaseEnum{
	
	/**
	 * 商品属性
	 */
	PRODUCT_PROPERTY("06990100"), 
	/**
	 * 商城订单状态
	 */
	ORDER_STATUS("06960000"),
	/**
	 * 商品管理
	 */
	MALL("06000000"),
	
	/**
	 * 产品管理
	 */
	MALL_PRODUCT("06980000"),
	
	/**
	 * 规格管理
	 */
	MALL_SPECIFICATIONS("06030000"),
	
	/**
	 * 商品分类
	 */
	MALL_CATEGORY("06990000"),
	
	/**
	 * 商品品牌管理
	 */
	MALL_BRAND("06990200"),
	
	/**
	 * 商城订单
	 */
	MALL_ORDER("06970000"),
	
	/**
	 * 限时抢购
	 */
	MALL_PROMOTION("06040100"),
	/**
	 * 商品分类属性管理
	 */
	MALL_CATEGORY_TYPE("06700000")
	;

	ModelCode(String code) {
		this.code = code;
	}

	private String code;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return code;
	}

	public int toInt() {
		// TODO Auto-generated method stub
		return Integer.parseInt(code);
	}

}

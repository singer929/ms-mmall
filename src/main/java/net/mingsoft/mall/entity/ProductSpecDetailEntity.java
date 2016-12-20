package net.mingsoft.mall.entity;

import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.entity.ColumnEntity;

/**
 * 
 * 
 * <p>
 * <b>铭飞MS平台</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 * 
 * <p>
 * Company:景德镇铭飞科技有限公司
 * </p>
 * 
 * @author 王敏
 * 
 * @version 300-001-001
 * 
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments:产品规格明细数据 描述产品设置了不同规格之后的数据(价格,库存等)
 * </p>
 *  
 * <p>
 * Create Date:2014-11-20
 * </p>
 *
 * <p>
 * </p>
 */
public class ProductSpecDetailEntity extends BaseEntity{
	
	/**
	 * 产品规格关联id
	 */
	private int detailId;
	
	/**
	 * 产品ID
	 */
	private int productId;
	
	/**
	 * 规格产品的类型值集合 (重要字段) 格式: {规格id:规格值, 规格id:规格值} json格式字符串
	 */
	private String specValues;
	
	/**
	 * 库存
	 */
	private String stock;
	
	/**
	 * 价格
	 */
	private double price;
	
	/**
	 * 销量
	 */
	private int sale;
	
	/**
	 * 产品编号
	 */
	private String code;
	
	/**
	 * 关联产品的排序
	 */
	private int sort;

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getSpecValues() {
		return specValues;
	}

	public void setSpecValues(String specValues) {
		this.specValues = specValues;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	
}

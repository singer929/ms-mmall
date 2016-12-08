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
 * @author 姓名：史爱华
 * 
 * @version 300-001-001
 * 
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments:产品规格库存实体类，继承BaseEntity
 * </p>
 *  
 * <p>
 * Create Date:2014-11-20
 * </p>
 *
 * <p>
 * </p>
 */
public class ProductSpecificationsInventoryEntity extends BaseEntity{
	
	/**
	 * 产品规格关联id
	 */
	private int productSpecificationsId;
	
	/**
	 * 产品ID
	 */
	private int productId;
	
	/**
	 * 规格产品的价格
	 */
	private double specificationsPrice;
	
	/**
	 * 规格产品的库存量
	 */
	private int specificationsInvertory;
	
	/**
	 * 规格产品的销量
	 */
	private int specificationsSale;
	
	/**
	 * 产品编号
	 */
	private String productSpecificationsInventoryCode;
	
	
	/**
	 * 关联产品的排序
	 */
	private Integer productSpecificationsInventorySort;
	
	/**
	 * 规格商品所属栏目
	 */
	private ColumnEntity column;
	
	/**
	 * 规格所属商品
	 */
	private ProductEntity product;
	
	
	
	public ColumnEntity getColumn() {
		return column;
	}

	public void setColumn(ColumnEntity column) {
		this.column = column;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public Integer getProductSpecificationsInventorySort() {
		return productSpecificationsInventorySort;
	}

	public void setProductSpecificationsInventorySort(
			Integer productSpecificationsInventorySort) {
		this.productSpecificationsInventorySort = productSpecificationsInventorySort;
	}
	
	/**
	 * 获取productId
	 * @return  productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * 设置productId
	 * @param productId
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}


	/**
	 * 获取productSpecificationsInventoryCode
	 * @return  productSpecificationsInventoryCode
	 */
	public String getProductSpecificationsInventoryCode() {
		return productSpecificationsInventoryCode;
	}

	/**
	 * 设置productSpecificationsInventoryCode
	 * @param productSpecificationsInventoryCode
	 */
	public void setProductSpecificationsInventoryCode(
			String productSpecificationsInventoryCode) {
		this.productSpecificationsInventoryCode = productSpecificationsInventoryCode;
	}

	/**
	 * 获取产品规格关联id
	 * @return
	 */
	public int getProductSpecificationsId() {
		return productSpecificationsId;
	}
	
	/**
	 * 设置产品规格关联id
	 * @param productSpecificationsId
	 */
	public void setProductSpecificationsId(int productSpecificationsId) {
		this.productSpecificationsId = productSpecificationsId;
	}
	
	/**
	 * 获取该规格产品的价格
	 * @return
	 */
	public double getSpecificationsPrice() {
		return specificationsPrice;
	}

	/**
	 * 设置该规格产品的价格
	 * @param specificationsPrice
	 */
	public void setSpecificationsPrice(double specificationsPrice) {
		this.specificationsPrice = specificationsPrice;
	}
	
	/**
	 * 获取该规格产品的库存量
	 * @return
	 */
	public int getSpecificationsInvertory() {
		return specificationsInvertory;
	}

	/**
	 * 设置该规格产品的库存量
	 * @param specificationsInvertory
	 */
	public void setSpecificationsInvertory(int specificationsInvertory) {
		this.specificationsInvertory = specificationsInvertory;
	}

	/**
	 * 获取该规格产品的销量
	 * @return
	 */
	public int getSpecificationsSale() {
		return specificationsSale;
	}
	
	/**
	 * 设置该规格产品的销量
	 * @param specificationsSale
	 */
	public void setSpecificationsSale(int specificationsSale) {
		this.specificationsSale = specificationsSale;
	}
	
	
}

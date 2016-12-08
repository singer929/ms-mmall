package net.mingsoft.mall.entity;

import java.util.ArrayList;
import java.util.List;

import com.mingsoft.base.entity.BaseEntity;

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
 * Comments:产品规格关联管理类，继承BaseEntity
 * </p>
 *  
 * <p>
 * Create Date:2014-11-21
 * </p>
 *
 * <p>
 * </p>
 */
public class ProductSpecificationsEntity extends BaseEntity{
	
	/**
	 * 产品规格关联id
	 */
	private int  productSpecificationsId;
	
	/**
	 * 产品id
	 */
	private int productId;
	
	/**
	 * 规格id
	 */
	private int specificationsId;
	
	/**
	 * 规格字段义字段
	 */
	private String specificationsField;
	
	/**
	 * 规格商品父关联ID
	 */
	private int productSpecificationsFatherId;
	
	/**
	 * 规格图片
	 */
	private String productSpecificationsImg;
	
	/**
	 * 当前规格关联的子类
	 */
	private List<ProductSpecificationsEntity> childProductSpecifications;
	
	/**
	 * 关联产品规格对应的商品详情
	 */
	private ProductSpecificationsInventoryEntity productSpecificationsInventory;
	
	/**
	 * 关联产品规格
	 */
	private SpecificationsEntity specifications;

	/**
	 * 获取productSpecificationsImg
	 * @return  productSpecificationsImg
	 */
	public String getProductSpecificationsImg() {
		return productSpecificationsImg;
	}

	/**
	 * 设置productSpecificationsImg
	 * @param productSpecificationsImg
	 */
	public void setProductSpecificationsImg(String productSpecificationsImg) {
		this.productSpecificationsImg = productSpecificationsImg;
	}

	/**
	 * 获取specifications
	 * @return  specifications
	 */
	public SpecificationsEntity getSpecifications() {
		return specifications;
	}

	/**
	 * 设置specifications
	 * @param specifications
	 */
	public void setSpecifications(SpecificationsEntity specifications) {
		this.specifications = specifications;
	}

	/**
	 * 获取childProductSpecifications
	 * @return  childProductSpecifications
	 */
	public List<ProductSpecificationsEntity> getChildProductSpecifications() {
		if(this.childProductSpecifications == null){
			this.childProductSpecifications = new ArrayList<ProductSpecificationsEntity>();
		}
		return childProductSpecifications;
	}

	/**
	 * 设置childProductSpecifications
	 * @param childProductSpecifications
	 */
	public void setChildProductSpecifications(
			List<ProductSpecificationsEntity> childProductSpecifications) {
		this.childProductSpecifications = childProductSpecifications;
	}

	/**
	 * 获取productSpecificationsInventory
	 * @return  productSpecificationsInventory
	 */
	public ProductSpecificationsInventoryEntity getProductSpecificationsInventory() {
		return productSpecificationsInventory;
	}

	/**
	 * 设置productSpecificationsInventory
	 * @param productSpecificationsInventory
	 */
	public void setProductSpecificationsInventory(
			ProductSpecificationsInventoryEntity productSpecificationsInventory) {
		this.productSpecificationsInventory = productSpecificationsInventory;
	}

	/**
	 * 获取productSpecificationsFatherId
	 * @return  productSpecificationsFatherId
	 */
	public int getProductSpecificationsFatherId() {
		return productSpecificationsFatherId;
	}

	/**
	 * 设置productSpecificationsFatherId
	 * @param productSpecificationsFatherId
	 */
	public void setProductSpecificationsFatherId(int productSpecificationsFatherId) {
		this.productSpecificationsFatherId = productSpecificationsFatherId;
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
	 * 获取产品id
	 * @return
	 */
	public int getProductId() {
		return productId;
	}
	
	/**
	 * 设置产品id 
	 * @param productId
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	/**
	 * 获取规格Id 
	 * @return
	 */
	public int getSpecificationsId() {
		return specificationsId;
	}
	
	/**
	 * 设置产品规格id
	 * @param specificationsId
	 */
	public void setSpecificationsId(int specificationsId) {
		this.specificationsId = specificationsId;
	}
	
	/**
	 * 获取产品规格字段名
	 * @return
	 */
	public String getSpecificationsField() {
		return specificationsField;
	}
	
	/**
	 * 设置产品规格字段名
	 * @param specificationsField
	 */
	public void setSpecificationsField(String specificationsField) {
		this.specificationsField = specificationsField;
	}
	
	

}

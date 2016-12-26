package net.mingsoft.mall.entity;

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
 * @author 姓名：王敏
 * 
 * @version 300-001-001
 * 
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments:产品规格数据模型，继承BaseEntity
 * </p>
 *  
 * <p>
 * Create Date:2014-11-21
 * </p>
 *
 * <p>
 * </p>
 */
public class ProductSpecificationEntity extends BaseEntity{

	/**
	 * 产品规格id 主键
	 */
	private int psId;
	
	/**
	 * 产品id
	 */
	private int productId;
	
	/**
	 * 规格名称, 规格表 主键
	 */
	private String specName;
	
	/**
	 * 规格的值, 比如颜色规格下 白色
	 */
	private String specValue;
	
	/**
	 * 规格图片url
	 */
	private String img;
	

	public int getPsId() {
		return psId;
	}

	public void setPsId(int psId) {
		this.psId = psId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getSpecValue() {
		return specValue;
	}

	public void setSpecValue(String specValue) {
		this.specValue = specValue;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	
}

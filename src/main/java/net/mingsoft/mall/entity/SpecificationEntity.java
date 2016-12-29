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
 * @author 王敏
 * 
 * @version 300-001-001
 * 
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments:规格数据模型, 包含所有产品可能出现的规格
 * </p>
 *  
 * <p>
 * Create Date:2014-11-21
 * </p>
 *
 * <p>
 * </p>
 */
public class SpecificationEntity extends BaseEntity{
	
	/**
	 * 规格ID 主键
	 */
	private int specId;
	
	/**
	 * 规格名 
	 */
	private String name;
	
	/**
	 * 规格分类ID
	 */
	private int specCateId = 0;
	
	/**
	 * 默认的字段名
	 */
	private String defaultFields;
	
	/**
	 * 规格类型 1:标准规格 2:自定义规格
	 */
	private int type = 1;
	
	/**
	 * 规格所属的appId
	 */
	private int appId;
	

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public int getSpecCateId() {
		return specCateId;
	}

	public void setSpecCateId(int specCateId) {
		this.specCateId = specCateId;
	}

	public int getSpecId() {
		return specId;
	}

	public void setSpecId(int specId) {
		this.specId = specId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDefaultFields() {
		return defaultFields;
	}

	public void setDefaultFields(String defaultFields) {
		this.defaultFields = defaultFields;
	}
}

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
 * @author 姓名：史爱华
 * 
 * @version 300-001-001
 * 
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments:产品规格实体类，继承BaseEntity
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
	 * 规格id
	 */
	private int specId;
	
	/**
	 * 规格名称
	 */
	private String name;
	
	/**
	 * 默认的字段值
	 */
	private String defaultFields;
	
	/**
	 * appId
	 */
	private int appId;

	
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

	public String getDefaultFields() {
		return defaultFields;
	}

	public void setDefaultFields(String defaultValue) {
		this.defaultFields = defaultValue;
	} 

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}
}

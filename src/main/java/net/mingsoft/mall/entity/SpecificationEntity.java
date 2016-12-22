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
	 * 规格名 主键
	 */
	private String name;
	
	/**
	 * 默认的字段名
	 */
	private String defaultFields;
	
	/**
	 * 规格所属的appId
	 */
	private int appId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefaultFields() {
		return defaultFields;
	}

	public void setDefaultFields(String defaultFields) {
		this.defaultFields = defaultFields;
	}
}

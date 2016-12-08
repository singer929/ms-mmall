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
public class SpecificationsEntity extends BaseEntity{
	
	/**
	 * 规格id
	 */
	private int specificationsId;
	
	/**
	 * 规格名
	 */
	private String specificationsName;
	
	/**
	 * 定义自字段名称
	 */
	private String specificationsField;
	
	/**
	 * 规格所属的appId
	 */
	private int specificationsAppId;
	
	/**
	 *获取规格id
	 * @return
	 */
	public int getSpecificationsId() {
		return specificationsId;
	}
	
	/**
	 * 设置规格id
	 * @param specificationsId
	 */
	public void setSpecificationsId(int specificationsId) {
		this.specificationsId = specificationsId;
	}
	
	/**
	 * 获取规格名
	 * @return
	 */
	public String getSpecificationsName() {
		return specificationsName;
	}
	
	/**
	 * 设置规格名
	 * @param specificationsName
	 */
	public void setSpecificationsName(String specificationsName) {
		this.specificationsName = specificationsName;
	}
	
	/**
	 * 获取定义自字段名称
	 * @return
	 */
	public String getSpecificationsField() {
		return specificationsField;
	}
	
	/**
	 * 设置定义自字段名称
	 * @param specificationsField
	 */
	public void setSpecificationsField(String specificationsField) {
		this.specificationsField = specificationsField;
	}
	
	/**
	 * 获取规格所属的appId
	 * @return
	 */
	public int getSpecificationsAppId() {
		return specificationsAppId;
	}

	/**
	 * 设置规格所属的appId
	 * @param specificationsAppId
	 */
	public void setSpecificationsAppId(int specificationsAppId) {
		this.specificationsAppId = specificationsAppId;
	}
	
	
	
}

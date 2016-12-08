package net.mingsoft.mall.entity;

import com.mingsoft.basic.entity.BasicEntity;

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
 * Comments:产品实体店实体类，继承BasicEntity
 * </p>
 *  
 * <p>
 * Create Date:2014-11-20
 * </p>
 *
 * <p>
 * </p>
 */
public class EntityShopEntity extends BasicEntity{
	
	/**
	 * 商店实体店id
	 */
	private int entityShopId;
	
	/**
	 * 商店实体店所在的城市id
	 */
	private int entityShopCity;
	
	/**
	 * 商店实体店的联系电话
	 */
	private String  entityShopPhone;
	
	/**
	 * 商店实体店的详细地址
	 */
	private String  entityShopAddress;
	
	/**
	 * 商店实体店的x轴
	 */
	private String   entityShopX;
	
	/**
	 * 商店实体店的Y轴
	 */
	private String  entityShopY;
	
	/**
	 * 商店实体店所属的应用appid
	 */
	private int  entityShopAppId;
	
	/**
	 * 商店所在的省id
	 */
	private int  entityShopProvinceId;
	
	/**
	 * 商店所在的区或县id
	 */
	private int entityShopAreaId;
	
	/**
	 * 商店所在的城市名
	 */
	private String entityShopCityTitle;
	
	/**
	 * 商店所在的省名
	 */
	private String entityShopProvinceTitle;

	public int getEntityShopId() {
		return entityShopId;
	}

	public void setEntityShopId(int entityShopId) {
		this.entityShopId = entityShopId;
	}

	public int getEntityShopCity() {
		return entityShopCity;
	}

	public void setEntityShopCity(int entityShopCity) {
		this.entityShopCity = entityShopCity;
	}

	public String getEntityShopPhone() {
		return entityShopPhone;
	}

	public void setEntityShopPhone(String entityShopPhone) {
		this.entityShopPhone = entityShopPhone;
	}

	public String getEntityShopAddress() {
		return entityShopAddress;
	}

	public void setEntityShopAddress(String entityShopAddress) {
		this.entityShopAddress = entityShopAddress;
	}

	public String getEntityShopX() {
		return entityShopX;
	}

	public void setEntityShopX(String entityShopX) {
		this.entityShopX = entityShopX;
	}

	public String getEntityShopY() {
		return entityShopY;
	}

	public void setEntityShopY(String entityShopY) {
		this.entityShopY = entityShopY;
	}

	public int getEntityShopAppId() {
		return entityShopAppId;
	}

	public void setEntityShopAppId(int entityShopAppId) {
		this.entityShopAppId = entityShopAppId;
	}

	public int getEntityShopProvinceId() {
		return entityShopProvinceId;
	}

	public void setEntityShopProvinceId(int entityShopProvinceId) {
		this.entityShopProvinceId = entityShopProvinceId;
	}

	public int getEntityShopAreaId() {
		return entityShopAreaId;
	}

	public void setEntityShopAreaId(int entityShopAreaId) {
		this.entityShopAreaId = entityShopAreaId;
	}

	public String getEntityShopCityTitle() {
		return entityShopCityTitle;
	}

	public void setEntityShopCityTitle(String entityShopCityTitle) {
		this.entityShopCityTitle = entityShopCityTitle;
	}

	public String getEntityShopProvinceTitle() {
		return entityShopProvinceTitle;
	}

	public void setEntityShopProvinceTitle(String entityShopProvinceTitle) {
		this.entityShopProvinceTitle = entityShopProvinceTitle;
	}

	
	
}

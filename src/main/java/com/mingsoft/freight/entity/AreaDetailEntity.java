package com.mingsoft.freight.entity;

import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.entity.CategoryEntity;

public class AreaDetailEntity extends BaseEntity {
	/**
	 * 主键
	 */
	public int fadId;
	/**
	 * 区域id 对应freight_area表id
	 */
	public int fadAreaId;
	/**
	 * 快递公司id 对应category快递数据id
	 */
	public int fadExpressId;
	/**
	 * 获取的快递公司Entity
	 */
	public CategoryEntity fadExpress;
	/**
	 * 基础运费
	 */
	public double fadBasePrice;
	/**
	 * 基础运费的数量，表示这个值以内都是属于基础运费范畴
	 */
	public double fadBaseAmount;
	/**
	 * 增长运费
	 */
	public double fadIncreasePrice;
	/**
	 * 增长数量，表示每增加这么多数量增加一次运费
	 */
	public double fadIncreaseAmount;
	/**
	 * 勾选状态，表示是否勾选
	 */
	public int fadEnable;
	
	public int getFadId() {
		return fadId;
	}
	
	public void setFadId(int fadId) {
		this.fadId = fadId;
	}
	
	public int getFadAreaId() {
		return fadAreaId;
	}
	
	public void setFadAreaId(int fadAreaId) {
		this.fadAreaId = fadAreaId;
	}
	
	public CategoryEntity getFadExpress() {
		return fadExpress;
	}
	
	public void setFadExpress(CategoryEntity fadExpress) {
		this.fadExpress = fadExpress;
	}
	
	public double getFadBasePrice() {
		return fadBasePrice;
	}
	
	public void setFadBasePrice(double fadBasePrice) {
		this.fadBasePrice = fadBasePrice;
	}
	
	public double getFadBaseAmount() {
		return fadBaseAmount;
	}
	
	public void setFadBaseAmount(double fadBaseAmount) {
		this.fadBaseAmount = fadBaseAmount;
	}
	
	public double getFadIncreasePrice() {
		return fadIncreasePrice;
	}
	
	public void setFadIncreasePrice(double fadIncreasePrice) {
		this.fadIncreasePrice = fadIncreasePrice;
	}
	
	public double getFadIncreaseAmount() {
		return fadIncreaseAmount;
	}
	
	public void setFadIncreaseAmount(double fadIncreaseAmount) {
		this.fadIncreaseAmount = fadIncreaseAmount;
	}

	public int getFadExpressId() {
		return fadExpressId;
	}

	public void setFadExpressId(int fadExpressId) {
		this.fadExpressId = fadExpressId;
	}

	public int getFadEnable() {
		return fadEnable;
	}

	public void setFadEnable(int fadEnable) {
		this.fadEnable = fadEnable;
	}
	
}

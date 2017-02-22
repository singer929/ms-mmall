package com.mingsoft.freight.entity;

import com.mingsoft.base.entity.BaseEntity;

public class FreightAreaDetailEntity extends BaseEntity {
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
	
	public int getFadExpressId() {
		return fadExpressId;
	}
	
	public void setFadExpressId(int fadExpressId) {
		this.fadExpressId = fadExpressId;
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
	
}

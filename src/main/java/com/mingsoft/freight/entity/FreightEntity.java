/**
The MIT License (MIT) * Copyright (c) 2016 铭飞科技(mingsoft.net)

 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.mingsoft.freight.entity;

import java.util.Date;

import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.entity.CategoryEntity;


public class FreightEntity extends BaseEntity {
	
	/**
	 *主键id
	 */
	private int freightId;
	/**
	 * 城市id
	 */
	private int freightCityId;
	
	/**
	 * 快递ID，对应category里的快递分类'
	 */
	private int freightExpressId;
	
	/**
	 * 基础运费
	 */
	private double freightBasePrice;
	
	/**
	 * 基础运费的数量，表示这个值以内都是属于基础运费范畴
	 */
	private double freightBaseAmount;
	
	/**
	 * 增长运费
	 */
	private double freightIncreasePrice;
	
	/**
	 * 增长数量，表示每增加这么多数量增加一次运费
	 */
	private double freightIncreaseAmount;

	/**
	 * 快递公司Entity
	 */
	private CategoryEntity freExpress;
	
	/**
	 * 快递是否启用
	 */
	private int freightEnable;
	
	/**
	 * 获取主键id
	 * @return
	 */
	public int getFreightId() {
		return freightId;
	}

	/**
	 * 设置运费主键id
	 * @param freightId
	 */
	public void setFreightId(int freightId) {
		this.freightId = freightId;
	}
	
	/**
	 * 获取 城市id
	 * @return 
	 */
	public int getFreightCityId() {
		return freightCityId;
	}

	/**
	 * 设置 城市id
	 * @param freightCityId
	 */
	public void setFreightCityId(int freightCityId) {
		this.freightCityId = freightCityId;
	}

	/**
	 * 获取快递id
	 * @return
	 */
	public int getFreightExpressId() {
		return freightExpressId;
	}

	/**
	 * 设置快递id
	 * @param freightExpressId
	 */
	public void setFreightExpressId(int freightExpressId) {
		this.freightExpressId = freightExpressId;
	}

	/**
	 * 获取基础运费
	 * @return
	 */
	public double getFreightBasePrice() {
		return freightBasePrice;
	}

	/**
	 * 设置基础运费
	 * @param freightBasePrice
	 */
	public void setFreightBasePrice(double freightBasePrice) {
		this.freightBasePrice = freightBasePrice;
	}

	/**
	 * 获取基础运费的数量，表示这个值以内都是属于基础运费范畴
	 * @return
	 */
	public double getFreightBaseAmount() {
		return freightBaseAmount;
	}

	/**
	 * 设置基础运费的数量
	 * @param freightBaseAmount
	 */
	public void setFreightBaseAmount(double freightBaseAmount) {
		this.freightBaseAmount = freightBaseAmount;
	}

	/**
	 * 获取增长运费
	 * @return
	 */
	public double getFreightIncreasePrice() {
		return freightIncreasePrice;
	}

	/**
	 * 设置增长运费
	 * @param freightIncreasePrice
	 */
	public void setFreightIncreasePrice(double freightIncreasePrice) {
		this.freightIncreasePrice = freightIncreasePrice;
	}

	/**
	 * 获取增长数量，表示每增加这么多数量增加一次运费
	 * @return
	 */
	public double getFreightIncreaseAmount() {
		return freightIncreaseAmount;
	}

	/**
	 * 设置增长数量
	 * @param freightIncreaseAmount
	 */
	public void setFreightIncreaseAmount(double freightIncreaseAmount) {
		this.freightIncreaseAmount = freightIncreaseAmount;
	}

	/**
	 * 获取的快递公司Entity
	 * @return
	 */
	public CategoryEntity getFreExpress() {
		return freExpress;
	}

	/**
	 * 设置获取的快递公司Entity
	 * @param freExpress
	 */
	public void setFreExpress(CategoryEntity freExpress) {
		this.freExpress = freExpress;
	}

	/**
	 * 获取快递的启用数据
	 * @return
	 */
	public int getFreightEnable() {
		return freightEnable;
	}

	/**
	 * 设置快递的启用数据
	 * @param freightEnable
	 */
	public void setFreightEnable(int freightEnable) {
		this.freightEnable = freightEnable;
	}	
}
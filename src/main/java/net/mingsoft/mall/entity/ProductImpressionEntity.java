/**
The MIT License (MIT) * Copyright (c) 2016 铭飞科技

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

package net.mingsoft.mall.entity;

import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.util.*;
import java.util.*;

 /**
 * 商品印象实体
 * @author 铭飞开发团队
 * @version 
 * 版本号：1.0.0<br/>
 * 创建日期：<br/>
 * 历史修订：<br/>
 */
public class ProductImpressionEntity extends BaseEntity {

	private static final long serialVersionUID = 1468625638831L;
	
	
	/**
	 * 
	 */
	private java.lang.Integer piId; 
	/**
	 * 商品编号
	 */
	private java.lang.Integer piBasicId; 
	/**
	 * 印象
	 */
	private java.lang.String piTitle; 
	/**
	 * 添加用户
	 */
	private java.lang.Integer piPeopleId; 
	/**
	 * 数量
	 */
	private java.lang.Integer piAmount; 
	/**
	 * 添加时间
	 */
	private java.util.Date piDatetime; 
	
	public ProductImpressionEntity(){}
	
	public ProductImpressionEntity(java.lang.Integer piBasicId) {
	this.piBasicId = piBasicId;	
	}
	
	public ProductImpressionEntity(java.lang.Integer piBasicId,java.lang.String piTitle) {
	this.piBasicId = piBasicId;	this.piTitle = piTitle;	
	}
	
	public ProductImpressionEntity(java.lang.Integer piBasicId,java.lang.String piTitle,java.lang.Integer piPeopleId) {
	this.piBasicId = piBasicId;	this.piTitle = piTitle;	this.piPeopleId = piPeopleId;	
	}
	
	public ProductImpressionEntity(java.lang.Integer piBasicId,java.lang.String piTitle,java.lang.Integer piPeopleId,java.lang.Integer piAmount) {
	this.piBasicId = piBasicId;	this.piTitle = piTitle;	this.piPeopleId = piPeopleId;	this.piAmount = piAmount;	
	}
	
	public ProductImpressionEntity(java.lang.Integer piBasicId,java.lang.String piTitle,java.lang.Integer piPeopleId,java.lang.Integer piAmount,java.util.Date piDatetime) {
	this.piBasicId = piBasicId;	this.piTitle = piTitle;	this.piPeopleId = piPeopleId;	this.piAmount = piAmount;	this.piDatetime = piDatetime;	
	}
	
	
	/**
	 * 设置
	 */
	public void setPiId(java.lang.Integer piId) {
		this.piId = piId;
	}

	/**
	 * 获取
	 */
	public java.lang.Integer getPiId() {
		return this.piId;
	}
	
	/**
	 * 设置商品编号
	 */
	public void setPiBasicId(java.lang.Integer piBasicId) {
		this.piBasicId = piBasicId;
	}

	/**
	 * 获取商品编号
	 */
	public java.lang.Integer getPiBasicId() {
		return this.piBasicId;
	}
	
	/**
	 * 设置印象
	 */
	public void setPiTitle(java.lang.String piTitle) {
		this.piTitle = piTitle;
	}

	/**
	 * 获取印象
	 */
	public java.lang.String getPiTitle() {
		return this.piTitle;
	}
	
	/**
	 * 设置添加用户
	 */
	public void setPiPeopleId(java.lang.Integer piPeopleId) {
		this.piPeopleId = piPeopleId;
	}

	/**
	 * 获取添加用户
	 */
	public java.lang.Integer getPiPeopleId() {
		return this.piPeopleId;
	}
	
	/**
	 * 设置数量
	 */
	public void setPiAmount(java.lang.Integer piAmount) {
		this.piAmount = piAmount;
	}

	/**
	 * 获取数量
	 */
	public java.lang.Integer getPiAmount() {
		return this.piAmount;
	}
	
	/**
	 * 设置添加时间
	 */
	public void setPiDatetime(java.util.Date piDatetime) {
		this.piDatetime = piDatetime;
	}

	/**
	 * 获取添加时间
	 */
	public java.util.Date getPiDatetime() {
		return this.piDatetime;
	}
	
}
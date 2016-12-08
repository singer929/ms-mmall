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
 * 订单评价实体
 * @author 铭飞开发团队
 * @version 
 * 版本号：1.0.0<br/>
 * 创建日期：<br/>
 * 历史修订：<br/>
 */
public class OrderCommentEntity extends CommentEntity {

	private static final long serialVersionUID = 1468625639035L;
	
	
	/**
	 * 评论编号
	 */
	private java.lang.Integer ocCommentId; 
	/**
	 * 订单编号
	 */
	private java.lang.Integer ocOrderId; 
	/**
	 * 印象
	 */
	private java.lang.String ocImpression; 
	/**
	 * 商品符合度
	 */
	private java.lang.Integer ocProduct; 
	/**
	 * 店家服务态度
	 */
	private java.lang.Integer ocService; 
	/**
	 * 物流发货速度
	 */
	private java.lang.Integer ocLogistics; 
	/**
	 * 评价服务
	 */
	private java.lang.String ocServiceDescribe; 
	
	public OrderCommentEntity(){}
	
	public OrderCommentEntity(java.lang.Integer ocOrderId) {
	this.ocOrderId = ocOrderId;	
	}
	
	public OrderCommentEntity(java.lang.Integer ocOrderId,java.lang.String ocImpression) {
	this.ocOrderId = ocOrderId;	this.ocImpression = ocImpression;	
	}
	
	public OrderCommentEntity(java.lang.Integer ocOrderId,java.lang.String ocImpression,java.lang.Integer ocProduct) {
	this.ocOrderId = ocOrderId;	this.ocImpression = ocImpression;	this.ocProduct = ocProduct;	
	}
	
	public OrderCommentEntity(java.lang.Integer ocOrderId,java.lang.String ocImpression,java.lang.Integer ocProduct,java.lang.Integer ocService) {
	this.ocOrderId = ocOrderId;	this.ocImpression = ocImpression;	this.ocProduct = ocProduct;	this.ocService = ocService;	
	}
	
	public OrderCommentEntity(java.lang.Integer ocOrderId,java.lang.String ocImpression,java.lang.Integer ocProduct,java.lang.Integer ocService,java.lang.Integer ocLogistics) {
	this.ocOrderId = ocOrderId;	this.ocImpression = ocImpression;	this.ocProduct = ocProduct;	this.ocService = ocService;	this.ocLogistics = ocLogistics;	
	}
	
	public OrderCommentEntity(java.lang.Integer ocOrderId,java.lang.String ocImpression,java.lang.Integer ocProduct,java.lang.Integer ocService,java.lang.Integer ocLogistics,java.lang.String ocServiceDescribe) {
	this.ocOrderId = ocOrderId;	this.ocImpression = ocImpression;	this.ocProduct = ocProduct;	this.ocService = ocService;	this.ocLogistics = ocLogistics;	this.ocServiceDescribe = ocServiceDescribe;	
	}
	
	
	/**
	 * 设置评论编号
	 */
	public void setOcCommentId(java.lang.Integer ocCommentId) {
		this.ocCommentId = ocCommentId;
	}

	/**
	 * 获取评论编号
	 */
	public java.lang.Integer getOcCommentId() {
		return this.ocCommentId;
	}
	
	/**
	 * 设置订单编号
	 */
	public void setOcOrderId(java.lang.Integer ocOrderId) {
		this.ocOrderId = ocOrderId;
	}

	/**
	 * 获取订单编号
	 */
	public java.lang.Integer getOcOrderId() {
		return this.ocOrderId;
	}
	
	/**
	 * 设置印象
	 */
	public void setOcImpression(java.lang.String ocImpression) {
		this.ocImpression = ocImpression;
	}

	/**
	 * 获取印象
	 */
	public java.lang.String getOcImpression() {
		return this.ocImpression;
	}
	
	/**
	 * 设置商品符合度
	 */
	public void setOcProduct(java.lang.Integer ocProduct) {
		this.ocProduct = ocProduct;
	}

	/**
	 * 获取商品符合度
	 */
	public java.lang.Integer getOcProduct() {
		return this.ocProduct;
	}
	
	/**
	 * 设置店家服务态度
	 */
	public void setOcService(java.lang.Integer ocService) {
		this.ocService = ocService;
	}

	/**
	 * 获取店家服务态度
	 */
	public java.lang.Integer getOcService() {
		return this.ocService;
	}
	
	/**
	 * 设置物流发货速度
	 */
	public void setOcLogistics(java.lang.Integer ocLogistics) {
		this.ocLogistics = ocLogistics;
	}

	/**
	 * 获取物流发货速度
	 */
	public java.lang.Integer getOcLogistics() {
		return this.ocLogistics;
	}
	
	/**
	 * 设置评价服务
	 */
	public void setOcServiceDescribe(java.lang.String ocServiceDescribe) {
		this.ocServiceDescribe = ocServiceDescribe;
	}

	/**
	 * 获取评价服务
	 */
	public java.lang.String getOcServiceDescribe() {
		return this.ocServiceDescribe;
	}
	
}
package net.mingsoft.mall.entity;

import com.mingsoft.base.entity.BaseEntity;
import java.util.Date;

 /**
 * 产品规格关联实体
 * @author 伍晶晶
 * @version 
 * 版本号：0.0<br/>
 * 创建日期：2017-8-15 14:29:39<br/>
 * 历史修订：<br/>
 */
public class ProductAttributeEntity extends BaseEntity {

	private static final long serialVersionUID = 1502778579831L;
	
	/**
	 * 
	 */
	private Integer paId; 
	/**
	 * 商品编号
	 */
	private Integer paProductId; 
	/**
	 * 分类扩展属性编号
	 */
	private Integer paCaId; 
	/**
	 * 
	 */
	private String paName; 
	/**
	 * 
	 */
	private String paValue; 
	
	public ProductAttributeEntity(){}
	
	public ProductAttributeEntity(Integer paProductId) {
		this.paProductId = paProductId;	
	}
	
	public ProductAttributeEntity(Integer paProductId,Integer paCaId) {
		this.paProductId = paProductId;		this.paCaId = paCaId;	
	}
	
	public ProductAttributeEntity(Integer paProductId,Integer paCaId,String paName) {
		this.paProductId = paProductId;		this.paCaId = paCaId;		this.paName = paName;	
	}
	
	public ProductAttributeEntity(Integer paProductId,Integer paCaId,String paName,String paValue) {
		this.paProductId = paProductId;		this.paCaId = paCaId;		this.paName = paName;		this.paValue = paValue;	
	}
	
		
	/**
	 * 设置
	 */
	public void setPaId(Integer paId) {
		this.paId = paId;
	}

	/**
	 * 获取
	 */
	public Integer getPaId() {
		return this.paId;
	}
	
	/**
	 * 设置商品编号
	 */
	public void setPaProductId(Integer paProductId) {
		this.paProductId = paProductId;
	}

	/**
	 * 获取商品编号
	 */
	public Integer getPaProductId() {
		return this.paProductId;
	}
	
	/**
	 * 设置分类扩展属性编号
	 */
	public void setPaCaId(Integer paCaId) {
		this.paCaId = paCaId;
	}

	/**
	 * 获取分类扩展属性编号
	 */
	public Integer getPaCaId() {
		return this.paCaId;
	}
	
	/**
	 * 设置
	 */
	public void setPaName(String paName) {
		this.paName = paName;
	}

	/**
	 * 获取
	 */
	public String getPaName() {
		return this.paName;
	}
	
	/**
	 * 设置
	 */
	public void setPaValue(String paValue) {
		this.paValue = paValue;
	}

	/**
	 * 获取
	 */
	public String getPaValue() {
		return this.paValue;
	}
	
}
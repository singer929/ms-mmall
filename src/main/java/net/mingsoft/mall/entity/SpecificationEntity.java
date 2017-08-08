package net.mingsoft.mall.entity;

import com.mingsoft.base.entity.BaseEntity;
import java.util.Date;

 /**
 * 默认规格数据实体
 * @author 伍晶晶
 * @version 
 * 版本号：100<br/>
 * 创建日期：2017-8-8 15:18:35<br/>
 * 历史修订：<br/>
 */
public class SpecificationEntity extends BaseEntity {

	private static final long serialVersionUID = 1502176715887L;
	
	/**
	 * 规格id 主键
	 */
	private Integer specificationId; 
	/**
	 * 规格名称
	 */
	private String specificationName; 
	/**
	 * 规格的类型id
	 */
	private Integer specificationCategoryId; 
	/**
	 * 默认的字段
	 */
	private String specificationDefaultFields; 
	/**
	 * 应用ID
	 */
	private Integer specificationAppId; 
	/**
	 * 规格类型:1-标准规格,2-自定义规格
	 */
	private Byte specificationType; 
	
	public SpecificationEntity(){}
	public SpecificationEntity(Integer specificationId) {
	this.specificationId = specificationId;	
	}
	
	public SpecificationEntity(String specificationName) {
		this.specificationName = specificationName;	
	}
	
	public SpecificationEntity(String specificationName,Integer specificationCategoryId) {
		this.specificationName = specificationName;		this.specificationCategoryId = specificationCategoryId;	
	}
	
	public SpecificationEntity(String specificationName,Integer specificationCategoryId,String specificationDefaultFields) {
		this.specificationName = specificationName;		this.specificationCategoryId = specificationCategoryId;		this.specificationDefaultFields = specificationDefaultFields;	
	}
	
	public SpecificationEntity(String specificationName,Integer specificationCategoryId,String specificationDefaultFields,Integer specificationAppId) {
		this.specificationName = specificationName;		this.specificationCategoryId = specificationCategoryId;		this.specificationDefaultFields = specificationDefaultFields;		this.specificationAppId = specificationAppId;	
	}
	
	public SpecificationEntity(String specificationName,Integer specificationCategoryId,String specificationDefaultFields,Integer specificationAppId,Byte specificationType) {
		this.specificationName = specificationName;		this.specificationCategoryId = specificationCategoryId;		this.specificationDefaultFields = specificationDefaultFields;		this.specificationAppId = specificationAppId;		this.specificationType = specificationType;	
	}
	
		
	/**
	 * 设置规格id 主键
	 */
	public void setSpecificationId(Integer specificationId) {
		this.specificationId = specificationId;
	}

	/**
	 * 获取规格id 主键
	 */
	public Integer getSpecificationId() {
		return this.specificationId;
	}
	
	/**
	 * 设置规格名称
	 */
	public void setSpecificationName(String specificationName) {
		this.specificationName = specificationName;
	}

	/**
	 * 获取规格名称
	 */
	public String getSpecificationName() {
		return this.specificationName;
	}
	
	/**
	 * 设置规格的类型id
	 */
	public void setSpecificationCategoryId(Integer specificationCategoryId) {
		this.specificationCategoryId = specificationCategoryId;
	}

	/**
	 * 获取规格的类型id
	 */
	public Integer getSpecificationCategoryId() {
		return this.specificationCategoryId;
	}
	
	/**
	 * 设置默认的字段
	 */
	public void setSpecificationDefaultFields(String specificationDefaultFields) {
		this.specificationDefaultFields = specificationDefaultFields;
	}

	/**
	 * 获取默认的字段
	 */
	public String getSpecificationDefaultFields() {
		return this.specificationDefaultFields;
	}
	
	/**
	 * 设置应用ID
	 */
	public void setSpecificationAppId(Integer specificationAppId) {
		this.specificationAppId = specificationAppId;
	}

	/**
	 * 获取应用ID
	 */
	public Integer getSpecificationAppId() {
		return this.specificationAppId;
	}
	
	/**
	 * 设置规格类型:1-标准规格,2-自定义规格
	 */
	public void setSpecificationType(Byte specificationType) {
		this.specificationType = specificationType;
	}

	/**
	 * 获取规格类型:1-标准规格,2-自定义规格
	 */
	public Byte getSpecificationType() {
		return this.specificationType;
	}
	
}
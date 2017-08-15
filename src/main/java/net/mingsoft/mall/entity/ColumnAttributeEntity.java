package net.mingsoft.mall.entity;

import com.mingsoft.base.entity.BaseEntity;
import java.util.Date;

 /**
 * 默认规格数据实体
 * @author 伍晶晶
 * @version 
 * 版本号：0.0<br/>
 * 创建日期：2017-8-15 14:29:39<br/>
 * 历史修订：<br/>
 */
public class ColumnAttributeEntity extends BaseEntity {

	private static final long serialVersionUID = 1502778579809L;
	
	/**
	 * 规格id 主键
	 */
	private Integer caId; 
	/**
	 * 规格的类型id(预留)
	 */
	private Integer caCategoryId; 
	/**
	 * 规格名称
	 */
	private String caName; 
	/**
	 * 默认的字段,多个值用逗号隔开
	 */
	private String caFields; 
	/**
	 * 类型
	 */
	private Integer caType; 
	/**
	 * 0:不允许搜索 搜索状态
	 */
	private Integer caSearch; 
	
	public ColumnAttributeEntity(){}
	
	public ColumnAttributeEntity(Integer caCategoryId) {
		this.caCategoryId = caCategoryId;	
	}
	
	public ColumnAttributeEntity(Integer caCategoryId,String caName) {
		this.caCategoryId = caCategoryId;		this.caName = caName;	
	}
	
	public ColumnAttributeEntity(Integer caCategoryId,String caName,String caFields) {
		this.caCategoryId = caCategoryId;		this.caName = caName;		this.caFields = caFields;	
	}
	
	public ColumnAttributeEntity(Integer caCategoryId,String caName,String caFields,Integer caType) {
		this.caCategoryId = caCategoryId;		this.caName = caName;		this.caFields = caFields;		this.caType = caType;	
	}
	
	public ColumnAttributeEntity(Integer caCategoryId,String caName,String caFields,Integer caType,Integer caSearch) {
		this.caCategoryId = caCategoryId;		this.caName = caName;		this.caFields = caFields;		this.caType = caType;		this.caSearch = caSearch;	
	}
	
		
	/**
	 * 设置规格id 主键
	 */
	public void setCaId(Integer caId) {
		this.caId = caId;
	}

	/**
	 * 获取规格id 主键
	 */
	public Integer getCaId() {
		return this.caId;
	}
	
	/**
	 * 设置规格的类型id(预留)
	 */
	public void setCaCategoryId(Integer caCategoryId) {
		this.caCategoryId = caCategoryId;
	}

	/**
	 * 获取规格的类型id(预留)
	 */
	public Integer getCaCategoryId() {
		return this.caCategoryId;
	}
	
	/**
	 * 设置规格名称
	 */
	public void setCaName(String caName) {
		this.caName = caName;
	}

	/**
	 * 获取规格名称
	 */
	public String getCaName() {
		return this.caName;
	}
	
	/**
	 * 设置默认的字段,多个值用逗号隔开
	 */
	public void setCaFields(String caFields) {
		this.caFields = caFields;
	}

	/**
	 * 获取默认的字段,多个值用逗号隔开
	 */
	public String getCaFields() {
		return this.caFields;
	}
	
	/**
	 * 设置类型
	 */
	public void setCaType(Integer caType) {
		this.caType = caType;
	}

	/**
	 * 获取类型
	 */
	public Integer getCaType() {
		return this.caType;
	}
	
	/**
	 * 设置0:不允许搜索 搜索状态
	 */
	public void setCaSearch(Integer caSearch) {
		this.caSearch = caSearch;
	}

	/**
	 * 获取0:不允许搜索 搜索状态
	 */
	public Integer getCaSearch() {
		return this.caSearch;
	}
	
}
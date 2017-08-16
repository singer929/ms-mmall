package net.mingsoft.mall.bean;

import java.util.List;

import net.mingsoft.mall.entity.ColumnAttributeEntity;
import net.mingsoft.mall.entity.ProductAttributeEntity;
import net.mingsoft.mall.entity.ProductEntity;
import net.mingsoft.mall.entity.ProductSpecificationDetailEntity;
import net.mingsoft.mall.entity.ProductSpecificationEntity;

/**
 * 商品保存更新时, 传输的数据
 * @author 王敏
 */
public class ProductSaveData {
	/**
	 * 商品
	 */
	private ProductEntity product;
	/**
	 * 商品规格列表
	 */
	private List<ProductSpecificationEntity> productSpecList;
	/**
	 * 商品规格详情列表
	 */
	private List<ProductSpecificationDetailEntity> detailList;
	/**
	 * 栏目属性列表
	 */
	private List<ColumnAttributeEntity> columnAttributeList;
	/**
	 * 商品栏目属性列表
	 */
	private List<ProductAttributeEntity> productAttributeList;
	
	public ProductEntity getProduct() {
		return product;
	}
	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	public List<ProductSpecificationEntity> getProductSpecList() {
		return productSpecList;
	}
	public void setProductSpecList(List<ProductSpecificationEntity> productSpecList) {
		this.productSpecList = productSpecList;
	}
	public List<ProductSpecificationDetailEntity> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<ProductSpecificationDetailEntity> detailList) {
		this.detailList = detailList;
	}
	public List<ColumnAttributeEntity> getColumnAttributeList() {
		return columnAttributeList;
	}
	public void setColumnAttributeList(List<ColumnAttributeEntity> columnAttributeList) {
		this.columnAttributeList = columnAttributeList;
	}
	public List<ProductAttributeEntity> getProductAttributeList() {
		return productAttributeList;
	}
	public void setProductAttributeList(List<ProductAttributeEntity> productAttributeList) {
		this.productAttributeList = productAttributeList;
	}
	
}

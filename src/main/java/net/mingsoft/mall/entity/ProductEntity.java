
package net.mingsoft.mall.entity;



import java.math.BigDecimal;

import com.mingsoft.basic.entity.BasicEntity;
import com.mingsoft.basic.entity.ColumnEntity;

import net.mingsoft.mall.constant.e.ProductEnum;

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
 * Comments:产品实体类，继承BaseEntity
 * </p>
 *  
 * <p>
 * Create Date:2014-11-20
 * </p>
 *
 * <p>
 * </p>
 */
public class ProductEntity extends BasicEntity{
	/**
	 * 产品ID
	 */
	private int productId;
	
	/**
	 * 产品价格
	 */
	private double productPrice;
	
	/**
	 * 产品库存量
	 */
	private int productStock;
	
	/**
	 * 产品属于的appId
	 */
	private int productAppId;
	
	/**
	 * 商品编码
	 */
	private String productCode;

	/**
	 * 商品销量
	 */
	private int productSale;
	
	/**
	 * 商品链接地址
	 */
	private String productLinkUrl;
	
	/**
	 * 商品详情
	 */
	private String productContent;
	
	/**
	 * 品牌
	 */
	private int productBrand;
	
	/**
	 * 产品所属的栏目实体
	 */
	private ColumnEntity column;

	/**
	 * 商品原价
	 */
	private double productCostPrice = 0.00;
	
	/**
	 * 商品是否上架
	 * 1：上架
	 * 0：下架
	 */
	private int productShelf = -1;
	
	/**
	 * 产品所属的规格id
	 */
	private int productSpecificationId;
	
	/**
	 * 产品的规格价
	 */
	private double productSpecificationPrice;
	
	/**
	 * 产品的规格库存
	 */
	private int productSpecificationStock;
	
	/**
	 * 产品的规格编码
	 */
	private String productSpecificationCode;
	
	
	/**
	 * 规格名
	 */  
	private String productSpecificationTitle;
	
	/**
	 * 规格图片
	 */
	private String productSpecificationImg;
	
	
	
	
	public String getProductSpecificationImg() {
		return productSpecificationImg;
	}

	public void setProductSpecificationImg(String productSpecificationImg) {
		this.productSpecificationImg = productSpecificationImg;
	}

	public String getProductSpecificationTitle() {
		return productSpecificationTitle;
	}

	public void setProductSpecificationTitle(String productSpecificationTitle) {
		this.productSpecificationTitle = productSpecificationTitle;
	}


	public int getProductSpecificationId() {
		return productSpecificationId;
	}

	public void setProductSpecificationId(int productSpecificationId) {
		this.productSpecificationId = productSpecificationId;
	}

	public double getProductSpecificationPrice() {
		return productSpecificationPrice;
	}

	public void setProductSpecificationPrice(double productSpecificationPrice) {
		this.productSpecificationPrice = productSpecificationPrice;
	}

	public int getProductSpecificationStock() {
		return productSpecificationStock;
	}

	public void setProductSpecificationStock(int productSpecificationStock) {
		this.productSpecificationStock = productSpecificationStock;
	}

	public String getProductSpecificationCode() {
		return productSpecificationCode;
	}

	public void setProductSpecificationCode(String productSpecificationCode) {
		this.productSpecificationCode = productSpecificationCode;
	}

	/**
	 * 获取productCostPrice
	 * @return  productCostPrice
	 */
	public double getProductCostPrice() {
		return productCostPrice;
	}

	/**
	 * 设置productCostPrice
	 * @param productCostPrice
	 */
	public void setProductCostPrice(double productCostPrice) {
		this.productCostPrice = productCostPrice;
	}

	/**
	 * 获取productSale
	 * @return  productSale
	 */
	public int getProductSale() {
		return productSale;
	}

	/**
	 * 设置productSale
	 * @param productSale
	 */
	public void setProductSale(int productSale) {
		this.productSale = productSale;
	}

	/**
	 * 获取产品id
	 * @return
	 */
	public int getProductId() {
		return productId;
	}
	
	/**
	 * 设置产品id
	 * @param productId
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	/**
	 * 获取产品价格
	 * @return
	 */
	public double getProductPrice() {
		BigDecimal b = new BigDecimal(productPrice);
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 设置产品价格
	 * @param productPrice
	 */
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	/**
	 * 获取产品库存量
	 * @return
	 */
	public int getProductStock() {
		return productStock;
	}
	
	/**
	 * 设置产品的库存量
	 * @param productStock
	 */
	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}
	
	/**
	 * 获取产品的appID
	 * @return
	 */
	public int getProductAppId() {
		return productAppId;
	}
	
	/**
	 * 设置产品的appID
	 * @param productAppId
	 */
	public void setProductAppId(int productAppId) {
		this.productAppId = productAppId;
	}
	
	/**
	 * 获取商品编码
	 * @return
	 */
	public String getProductCode() {
		return productCode;
	}
	
	/**
	 * 设置商品编码
	 * @param productCode
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	/**
	 * 获取栏目实体
	 * @return
	 */
	public ColumnEntity getColumn() {
		if (column==null) {
			column = new ColumnEntity();
		}
		return column;
	}
	
	/**
	 * 设置栏目实体
	 * @param column
	 */
	public void setColumn(ColumnEntity column) {
		this.column = column;
	}
	
	/**
	 * 获取商品链接地址
	 * @return
	 */
	public String getProductLinkUrl() {
		return productLinkUrl;
	}

	/**
	 * 设置商品链接地址
	 * @param productLinkUrl
	 */
	public void setProductLinkUrl(String productLinkUrl) {
		this.productLinkUrl = productLinkUrl;
	}

	public int getProductShelf() {
		return productShelf;
	}

	@Deprecated
	public void setProductShelf(int productShelf) {
		this.productShelf = productShelf;
	}
	
	public void setProductShelf(ProductEnum productShelf) {
		this.productShelf = productShelf.toInt();
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public int getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(int productBrand) {
		this.productBrand = productBrand;
	}
	
	
	
	
}

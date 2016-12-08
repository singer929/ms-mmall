package net.mingsoft.mall.bean;

import java.sql.Timestamp;
import java.util.Date;

import com.mingsoft.basic.entity.ColumnEntity;
import com.mingsoft.util.StringUtil;

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
 * @author 姓名 史爱华
 * 
 * @version 
 * 
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments: ProductEntity实体的bean用于给外部请求数据使用
 * </p>
 *  
 * <p>
 * Create Date:2015-06-15
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
public class ProductBean {
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
	 * 产品所属的栏目实体
	 */
	private ColumnEntity column;

	/**
	 * 商品原价
	 */
	private double productCostPrice = 0.00;
	
	/**
	 * 商品的图片集
	 */
	private String productImages;
	
	/**
	 * 商品的缩略图
	 */
	private String productImage;
	
	/**
	 * 商品标题
	 */
	private String productTitle;
	
	/**
	 * 商品描述
	 */
	private String productDescription;
	
	/**
	 * 商品属性
	 */
	private String productType;
	
	/**
	 * 发布时间
	 */
	private Timestamp productDateTime;

	
	/**
	 * 更新时间
	 */
	private Date productUpdateTime;
	
	
	
	public Timestamp getProductDateTime() {
		return productDateTime;
	}

	public void setProductDateTime(Timestamp productDateTime) {
		this.productDateTime = productDateTime;
	}

	public Date getProductUpdateTime() {
		return productUpdateTime;
	}

	public void setProductUpdateTime(Date productUpdateTime) {
		this.productUpdateTime = productUpdateTime;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getProductShelf() {
		return productShelf;
	}

	public void setProductShelf(int productShelf) {
		this.productShelf = productShelf;
	}

	private int productShelf;
	
	

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getProductSale() {
		return productSale;
	}

	public void setProductSale(int productSale) {
		this.productSale = productSale;
	}

	public String getProductLinkUrl() {
		return productLinkUrl;
	}

	public void setProductLinkUrl(String productLinkUrl) {
		this.productLinkUrl = productLinkUrl;
	}

	public ColumnEntity getColumn() {
		return column;
	}

	public void setColumn(ColumnEntity column) {
		this.column = column;
	}

	public double getProductCostPrice() {
		return productCostPrice;
	}

	public void setProductCostPrice(double productCostPrice) {
		this.productCostPrice = productCostPrice;
	}

	public String getProductImages() {
		
		return productImages;
	}

	public void setProductImages(String productImages) {
		this.productImages = productImages;
	}
	
	
	public String getProductImage() {
		if(!StringUtil.isBlank(productImages)){
			String [] imgs = productImages.replaceAll(" ", "").split("\\|");
			productImage= imgs[0];
		}
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	
	
}

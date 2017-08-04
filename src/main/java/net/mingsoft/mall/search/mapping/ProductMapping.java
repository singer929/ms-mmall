package net.mingsoft.mall.search.mapping;

import java.util.Date;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import net.mingsoft.base.elasticsearch.bean.BaseMapping;

/**
 * 铭飞商城
 * 
 * @author 铭飞开发团队
 * @version 版本号：100-000-000<br/>
 *          创建日期：2017年7月28日<br/>
 *          历史修订：<br/>
 */
@Document(indexName = "ms-mmall", type = "product")
public class ProductMapping extends BaseMapping {

	/**
	 * 标题
	 */
	private String basicTitle;

	/**
	 * 缩略图
	 */
	private String basicThumbnails;
	
	/**
	 * 评论数
	 */
	private int basicComment;
	
	/**
	 * 预览数
	 */
	private int basicHit;
	
	/**
	 * 商品属性
	 */
	private int basicType;

	/**
	 * 商品价格
	 */
	private double productPrice;
	
	/**
	 * 上架时间
	 */
	private Date basicUpdateTime;
	
	/**
	 * 市场价格
	 */
	private double productCostPrice;
	
	/**
	 * 商品库存
	 */
	private int productStock;
	
	/**
	 * 商品销量
	 */
	private int productSale;
	
	/**
	 * 商品详情地址
	 */
	private String productLinkUrl;
	
	/**
	 * 商品规格详情数据
	 */
	private String productSpecDetails;
	
	/**
	 * 商品规
	 */
	private String productSpecs;
	
	/**
	 * 品牌
	 */
	private int productBrand;
	
	/**
	 * 分类编号
	 */
	private int basicCategoryId;
	
	/**
	 * 好评度
	 */
	private double productGood;
	

	public String getBasicTitle() {
		return basicTitle;
	}

	public void setBasicTitle(String basicTitle) {
		this.basicTitle = basicTitle;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public double getProductCostPrice() {
		return productCostPrice;
	}

	public void setProductCostPrice(double productCostPrice) {
		this.productCostPrice = productCostPrice;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
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


	public int getBasicComment() {
		return basicComment;
	}

	public void setBasicComment(int basicComment) {
		this.basicComment = basicComment;
	}

	public int getBasicHit() {
		return basicHit;
	}

	public void setBasicHit(int basicHit) {
		this.basicHit = basicHit;
	}

	public Date getBasicUpdateTime() {
		return basicUpdateTime;
	}

	public void setBasicUpdateTime(Date basicUpdateTime) {
		this.basicUpdateTime = basicUpdateTime;
	}

	public int getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(int productBrand) {
		this.productBrand = productBrand;
	}

	public int getBasicCategoryId() {
		return basicCategoryId;
	}

	public void setBasicCategoryId(int basicCategoryId) {
		this.basicCategoryId = basicCategoryId;
	}

	public double getProductGood() {
		return productGood;
	}

	public void setProductGood(double productGood) {
		this.productGood = productGood;
	}

	public String getProductSpecDetails() {
		return productSpecDetails;
	}

	public void setProductSpecDetails(String productSpecDetails) {
		this.productSpecDetails = productSpecDetails;
	}

	public String getProductSpecs() {
		return productSpecs;
	}

	public void setProductSpecs(String productSpecs) {
		this.productSpecs = productSpecs;
	}

	public String getBasicThumbnails() {
		return basicThumbnails;
	}

	public void setBasicThumbnails(String basicThumbnails) {
		this.basicThumbnails = basicThumbnails;
	}

	public int getBasicType() {
		return basicType;
	}

	public void setBasicType(int basicType) {
		this.basicType = basicType;
	}
	
	
	
}

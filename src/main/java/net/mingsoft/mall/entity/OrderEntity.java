package net.mingsoft.mall.entity;

import java.util.ArrayList;
import java.util.List;

import net.mingsoft.order.entity.OrderCancelEntity;

public class OrderEntity extends net.mingsoft.order.entity.OrderEntity {

	private List<GoodsEntity> goods = new ArrayList<GoodsEntity>();

	/**
	 * 评论编号
	 */
	private int commentId;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	/**
	 * 商品实体
	 */
	private ProductEntity productEntity;

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}
	/**
	 * 购物车实体
	 */
	private CartEntity cartEntity;

	public CartEntity getCartEntity() {
		return cartEntity;
	}

	public void setCartEntity(CartEntity cartEntity) {
		this.cartEntity = cartEntity;
	}
}	

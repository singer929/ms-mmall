package net.mingsoft.mall.entity;

import java.util.ArrayList;
import java.util.List;

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
	
	
	
}

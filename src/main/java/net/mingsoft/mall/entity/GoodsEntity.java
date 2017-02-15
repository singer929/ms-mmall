package net.mingsoft.mall.entity;

import com.mingsoft.util.StringUtil;

public class GoodsEntity extends net.mingsoft.order.entity.GoodsEntity {
	
	/**
	 * 商品规格编号
	 */
	private int goodsProductDetailId;
	
	/**
	 * 商品规格内容
	 */
	private String goodsProductDetailText;

	private int cartId;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	
	@Override
	public void setGoodsThumbnail(String goodsThumbnail) {
		// TODO Auto-generated method stub
		if(!StringUtil.isBlank(goodsThumbnail)) {
			super.setGoodsThumbnail(goodsThumbnail);
		}
	}

	public int getGoodsProductDetailId() {
		return goodsProductDetailId;
	}

	public void setGoodsProductDetailId(int goodsProductDetailId) {
		this.goodsProductDetailId = goodsProductDetailId;
	}

	public String getGoodsProductDetailText() {
		return goodsProductDetailText;
	}

	public void setGoodsProductDetailText(String goodsProductDetailText) {
		this.goodsProductDetailText = goodsProductDetailText;
	}
	
	
}

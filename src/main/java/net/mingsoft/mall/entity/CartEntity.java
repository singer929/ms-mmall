package net.mingsoft.mall.entity;
import java.util.Date;
import com.mingsoft.base.entity.BaseEntity;
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
 * @author 姓名：张敏
 * 
 * @version 300-001-001
 * 
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments:购物车实体类，继承BaseEntity
 * </p>
 *  
 * <p>
 * Create Date:2014-8-29
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
public class CartEntity extends net.mingsoft.order.entity.CartEntity  {
	
	/**
	 * 商品规格编号
	 */
	private int cartProductDetailId;
	
	/**
	 * 商品规格内容
	 */
	private String cartProductDetailText;

	public int getCartProductDetailId() {
		return cartProductDetailId;
	}

	public void setCartProductDetailId(int cartProductDetailId) {
		this.cartProductDetailId = cartProductDetailId;
	}

	@Override
	public void setCartPrice(double cartPrice) {
		// TODO Auto-generated method stub
		if(cartPrice > 0) {
			super.setCartPrice(cartPrice);
		}
	}

	@Override
	public void setCartThumbnail(String cartThumbnail) {
		// TODO Auto-generated method stub
		if(!StringUtil.isBlank(cartThumbnail)) {
			super.setCartThumbnail(cartThumbnail);
		}
	}

	public String getCartProductDetailText() {
		return cartProductDetailText;
	}

	public void setCartProductDetailText(String cartProductDetailText) {
		this.cartProductDetailText = cartProductDetailText;
	}

	@Override
	public void setCartNum(int cartNum) {
		// TODO Auto-generated method stub
		if(cartNum > 0) {
			super.setCartNum(cartNum);
		}
	}

	
	
	
}

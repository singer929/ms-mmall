/**
The MIT License (MIT) * Copyright (c) 2016 铭飞科技

 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.mingsoft.mall.entity;

import com.mingsoft.base.constant.e.BaseEnum;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.util.*;
import java.util.*;

/**
 * 商城订单关联表实体
 * 
 * @author 铭飞团队
 * @version 版本号：1<br/>
 *          创建日期：2017-2-6 17:45:51<br/>
 *          历史修订：<br/>
 */
public class OrderProductEntity extends BaseEntity {

	private static final long serialVersionUID = 1486374351633L;

	/**
	 * 商品用户编号
	 */
	private int opPeopleId;
	/**
	 * 商品编号
	 */
	private int opProductId;
	/**
	 * 商品规格编号
	 */
	private int opProductDetailId;
	/**
	 * 标题
	 */
	private String opTitle;
	/**
	 * 图片
	 */
	private String opThumbnail;
	/**
	 * 商品状态0购物车 1订单
	 */
	private int opStatus;
	
	/**
	 * 对应购物车或订单的编号
	 */
	private int opGoodsId;
	
	/**
	 * 对应购物车编号-临时使用
	 */
	private int cartId;

	public OrderProductEntity() {
	}
	
	public OrderProductEntity(int opGoodsId,int opPeopleId,OpStatus os) {
		this.opGoodsId = opGoodsId;
		this.opPeopleId = opPeopleId;
		this.opStatus = os.toInt();
	}
	
	public OrderProductEntity(int productId,int opGoodsId,int opPeopleId,OpStatus os) {
		this.opGoodsId = opGoodsId;
		this.opPeopleId = opPeopleId;
		this.opStatus = os.toInt();
		this.opProductId = productId;
	}

	/**
	 * 设置商品规格编号
	 */
	public void setOpProductDetailId(int opProductDetailId) {
		this.opProductDetailId = opProductDetailId;
	}

	/**
	 * 获取商品规格编号
	 */
	public int getOpProductDetailId() {
		return this.opProductDetailId;
	}

	/**
	 * 设置标题
	 */
	public void setOpTitle(String opTitle) {
		this.opTitle = opTitle;
	}

	/**
	 * 获取标题
	 */
	public String getOpTitle() {
		return this.opTitle;
	}


	/**
	 * 设置商品状态0购物车 1订单
	 */
	public void setOpStatus(int opStatus) {
		this.opStatus = opStatus;
	}

	/**
	 * 设置商品状态0购物车 1订单
	 */
	public void setOpStatus(OpStatus opStatus) {
		this.opStatus = opStatus.toInt();
	}

	/**
	 * 获取商品状态0购物车 1订单
	 */
	public int getOpStatus() {
		return this.opStatus;
	}

	public int getOpPeopleId() {
		return opPeopleId;
	}

	public void setOpPeopleId(int opPeopleId) {
		this.opPeopleId = opPeopleId;
	}
	
	

	public String getOpThumbnail() {
		return opThumbnail;
	}

	public void setOpThumbnail(String opThumbnail) {
		this.opThumbnail = opThumbnail;
	}



	public int getOpProductId() {
		return opProductId;
	}

	public void setOpProductId(int opProductId) {
		this.opProductId = opProductId;
	}



	/**
	 * 商品状态
	 * @author Administrator
	 *
	 */
	public static enum OpStatus implements BaseEnum {
		CART(0), ORDER(1);

		private int value;

		OpStatus(int value) {
			this.value = value;
		}

		@Override
		public int toInt() {
			return value;
		}
	}



	public int getOpGoodsId() {
		return opGoodsId;
	}

	public void setOpGoodsId(int opGoodsId) {
		this.opGoodsId = opGoodsId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	
	
}
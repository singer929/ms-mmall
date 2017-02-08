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
package net.mingsoft.mall.biz;

import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.util.*;
import java.util.*;

import net.mingsoft.mall.entity.CartEntity;
import net.mingsoft.mall.entity.OrderProductEntity;

/**
 * 商城订单关联表业务
 * @author 铭飞团队
 * @version 
 * 版本号：1<br/>
 * 创建日期：2017-2-6 17:45:51<br/>
 * 历史修订：<br/>
 */
public interface IOrderProductBiz extends IBaseBiz {

	/**
	 * 查询
	 * @param orderProduct 商城订单关联表
	 * @return
	 */
	List query(OrderProductEntity orderProduct);

	/**
	 * 通过加入购物车或立即购买方式保存
	 * @param cart 购物车
	 * @return
	 */
	int saveEntity(CartEntity cart);
}
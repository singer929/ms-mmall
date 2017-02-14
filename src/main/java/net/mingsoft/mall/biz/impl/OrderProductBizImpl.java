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

package net.mingsoft.mall.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.entity.BasicEntity;

import net.mingsoft.mall.biz.IOrderProductBiz;
import net.mingsoft.mall.dao.IOrderProductDao;
import net.mingsoft.mall.dao.IProductDao;
import net.mingsoft.mall.dao.IProductSpecificationDao;
import net.mingsoft.mall.dao.IProductSpecificationDetailDao;
import net.mingsoft.mall.entity.CartEntity;
import net.mingsoft.mall.entity.OrderProductEntity;
import net.mingsoft.mall.entity.ProductEntity;
import net.mingsoft.mall.entity.ProductSpecificationDetailEntity;
import net.mingsoft.order.dao.ICartDao;

/**
 * 商城订单关联表管理持久化层
 * 
 * @author 铭飞团队
 * @version 版本号：1<br/>
 *          创建日期：2017-2-6 17:45:51<br/>
 *          历史修订：<br/>
 */
@Service("orderProductBizImpl")
public class OrderProductBizImpl extends BaseBizImpl implements IOrderProductBiz {

	@Autowired
	private IOrderProductDao orderProductDao;



	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return orderProductDao;
	}

	@Override
	public List query(OrderProductEntity orderProduct) {
		return orderProductDao.query(orderProduct);
	}

	

	
}
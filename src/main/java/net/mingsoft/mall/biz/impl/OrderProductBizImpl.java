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
	
	@Autowired
	private ICartDao cartDao;
	
	@Autowired
	private IProductDao productDao;
	
	@Autowired
	private IProductSpecificationDetailDao detailDao;
	
	@Autowired
	private IProductSpecificationDao productSpecDao;

	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return orderProductDao;
	}

	@Override
	public List query(OrderProductEntity orderProduct) {
		return orderProductDao.query(orderProduct);
	}

	@Override
	public int saveEntity(CartEntity cart) {
		//检查购物车是否存在
		OrderProductEntity op = new OrderProductEntity();
		op.setOpPeopleId(cart.getCartPeopleId());
		op.setOpProductDetailId(cart.getCartProductDetailId());
		op.setOpProductId(cart.getCartBasicId());
		op.setOpStatus(OrderProductEntity.OpStatus.CART);
		op.setOpNum(cart.getCartNum());
		//如果存在就直接更新数量，如果不存在就进行新增操作
		
		List list = orderProductDao.query(op);
		OrderProductEntity _op = (list != null && list.size() > 0) ? (OrderProductEntity)list.get(0) : null;
		
		// 商品有规格 且 有数据
		if(list != null && list.size() > 0 && cart.getCartProductDetailId() > 0) {
			
			//_op = (OrderProductEntity) list.get(0);
			_op.setOpNum(_op.getOpNum() + op.getOpNum());
			orderProductDao.updateEntity(_op);
			return _op.getOpProductDetailId();
			
		} 
		// 商品无规格 或 无数据
		else 
		{
			//取出当前信息的标题保存在基础购物车
			ProductEntity product = (ProductEntity)productDao.getEntity(cart.getCartBasicId());
			if (product == null) {
				return 0;
			}
			
			net.mingsoft.order.entity.CartEntity _cart  = (net.mingsoft.order.entity.CartEntity)cartDao.getByEntity(cart);
			if (_cart != null && cart.getCartProductDetailId()==0) {
				_cart.setCartNum(_cart.getCartNum() + cart.getCartNum());
				cartDao.updateEntity(_cart);
				
				if (_op != null){
					_op.setOpNum(op.getOpNum() + _op.getOpNum());
					orderProductDao.updateEntity(_op);
				}
				
			} else {
				cart.setCartThumbnail(product.getBasicThumbnails());
				cart.setCartTitle(product.getBasicTitle());
				cart.setCartUrl(product.getProductLinkUrl());
				cart.setCartPrice(product.getProductPrice());
				cart.setCartDiscount(product.getProductCostPrice());
				cartDao.saveEntity(cart);
				
				if(cart.getCartProductDetailId() == 0) {
					op.setOpTitle(cart.getCartTitle());
					orderProductDao.saveEntity(op);
				}
			}
			
			op.setOpPeopleId(cart.getCartPeopleId());
			if(cart.getCartProductDetailId()>0) {
			//根据商品规格信息取出标题与图片
				ProductSpecificationDetailEntity detail = (ProductSpecificationDetailEntity) detailDao.getEntity(cart.getCartProductDetailId());
				op.setOpTitle(cart.getCartTitle()+"  "+detail.getSpecValues());		// 前端不需要商品名字
				String[] temp = detail.getSpecValues().split(",");
				for(String _temp:temp) {
					if(_temp.split(":").length>2) {
						op.setOpThumbnail(_temp.split(":")[2]);
						op.setOpTitle(_temp.split(":")[0]+":"+_temp.split(":")[1]);
						break;
					} 
				}
				op.setOpPrice(detail.getPrice());
				orderProductDao.saveEntity(op);
			}
			
		}
		return 1;
	}

}
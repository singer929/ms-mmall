package net.mingsoft.mall.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.util.StringUtil;

import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.mall.biz.IOrderBiz;
import net.mingsoft.mall.dao.IOrderProductDao;
import net.mingsoft.mall.dao.IProductSpecificationDetailDao;
import net.mingsoft.mall.entity.GoodsEntity;
import net.mingsoft.mall.entity.OrderEntity;
import net.mingsoft.mall.entity.OrderProductEntity;
import net.mingsoft.mall.entity.ProductSpecificationDetailEntity;
import net.mingsoft.order.constant.e.OrderStatusEnum;
import net.mingsoft.order.dao.ICartDao;
import net.mingsoft.order.dao.IGoodsDao;
import net.mingsoft.order.dao.IOrderDao;
import net.mingsoft.order.entity.CartEntity;

@Service("mallOrderBiz")
public class OrderBizImpl extends net.mingsoft.order.biz.impl.OrderBizImpl implements IOrderBiz {

	@Autowired
	private IOrderProductDao orderProductDao;

	@Autowired
	private IOrderDao orderDao;

	@Autowired
	private ICartDao cartDao;
	
	@Autowired
	private IProductSpecificationDetailDao productSpecificationDetailDao;

	@Resource(name = "mallCartDao")
	private net.mingsoft.mall.dao.ICartDao mallCartDao;
	
	@Resource(name = "mallOrderDao")
	private net.mingsoft.mall.dao.IOrderDao mallOrderDao;

	@Autowired
	private IGoodsDao goodsDao;

	@Override
	public int saveEntity(OrderEntity order, int[] cartIds) {
		// TODO Auto-generated method stub
		// 设置订单号
		order.setOrderNo(StringUtil.getDateSimpleStr() + StringUtil.randomNumber(4));
		order.setOrderStatus(OrderStatusEnum.UNPAY);
		List list = mallCartDao.query(cartIds, null, null, order.getOrderPeopleId(), BasicUtil.getAppId());
		orderDao.saveEntity(order);
		if (list == null) {
			return -1;
		}
		for (int i = 0; i < list.size(); i++) {
			CartEntity _cart = (CartEntity) list.get(i);
			GoodsEntity goods = new GoodsEntity();
			goods.setGoodsAppId(BasicUtil.getAppId());
			goods.setGoodsBasicId(_cart.getCartBasicId());
			goods.setGoodsName(_cart.getCartTitle());
			goods.setGoodsNum(_cart.getCartNum());
			goods.setGoodsPrice(_cart.getCartPrice());
			goods.setGoodsRebate(_cart.getCartDiscount());
			goods.setGoodsThumbnail(_cart.getCartThumbnail());
			goods.setGoodsUrl(_cart.getCartUrl());
			goods.setOrderId(order.getOrderId());
			goods.setCartId(_cart.getCartId());
			order.getGoods().add(goods);
			cartDao.deleteByEntity(new CartEntity(_cart.getCartId(), order.getOrderPeopleId(), order.getOrderAppId(), goods.getGoodsBasicId()));
			
		}
		
		if (order.getGoods().size() > 0) {
			goodsDao.saveBatch(order.getGoods());
		}
		OrderProductEntity ope = null;
		for(int i=0;i<order.getGoods().size();i++) {
			GoodsEntity goods = (GoodsEntity)order.getGoods().get(i);
			ope = new OrderProductEntity();
			ope.setCartId(goods.getCartId());
			ope.setOpGoodsId(goods.getGoodsId());
			ope.setOpPeopleId(order.getOrderPeopleId());
			orderProductDao.updateEntity(ope);
//			OrderProductEntity temp = (OrderProductEntity) orderProductDao.getByEntity(ope);
//			if(temp != null){
//				ProductSpecificationDetailEntity psdEntity = (ProductSpecificationDetailEntity) productSpecificationDetailDao.getEntity(temp.getOpProductDetailId());
//				psdEntity.setStock(psdEntity.getStock() - goods.getGoodsNum());
//				productSpecificationDetailDao.updateEntity(psdEntity);
//			}
		}
		return order.getOrderId();
	}

	@Override
	public List query(OrderEntity entity) {
		// TODO Auto-generated method stub
		return mallOrderDao.query(entity);
	}

	@Override
	public net.mingsoft.mall.entity.OrderEntity getByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return mallOrderDao.getByOrderNo(orderNo);
	}

	@Override
	public OrderEntity getEntityById(int id) {
		return mallOrderDao.getEntityById(id);
		
	}

	@Override
	public void editProductStockByEntity(int productId, int productStock) {
		mallOrderDao.editProductStockByEntity(productId,productStock);
		
	}

	
}

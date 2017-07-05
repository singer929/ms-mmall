/**
 * 
 */
package net.mingsoft.mall.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;

import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.mall.biz.ICartBiz;
import net.mingsoft.mall.dao.ICartDao;
import net.mingsoft.mall.dao.IOrderProductDao;
import net.mingsoft.mall.dao.IProductDao;
import net.mingsoft.mall.dao.IProductSpecificationDao;
import net.mingsoft.mall.dao.IProductSpecificationDetailDao;
import net.mingsoft.mall.entity.OrderProductEntity;
import net.mingsoft.mall.entity.ProductEntity;
import net.mingsoft.mall.entity.ProductSpecificationDetailEntity;
import net.mingsoft.order.entity.CartEntity;

@Service("mallCartBiz")
public class CartBizImpl extends BaseBizImpl implements ICartBiz {

	@Autowired
	private IOrderProductDao orderProductDao;

	/**
	 * 注入商品关注持久化层
	 */
	@Autowired
	private net.mingsoft.order.dao.ICartDao cartDao;

	@Resource(name = "mallCartDao")
	private ICartDao mallCartDao;

	@Autowired
	private IProductDao productDao;

	@Autowired
	private IProductSpecificationDetailDao detailDao;

	/**
	 * 关联dao
	 */
	@Override
	protected IBaseDao getDao() {
		return this.cartDao;
	}

	@Override
	public List<net.mingsoft.mall.entity.CartEntity> query(int[] cartIds, int[] cartProductDetailIds, int peopleId) {
		return mallCartDao.query(cartIds, null, cartProductDetailIds, peopleId, BasicUtil.getAppId());
	}

	@Override
	public List<net.mingsoft.mall.entity.CartEntity> query(int productId, int cartProductDetailId, int peopleId) {
		return mallCartDao.query(null, new int[] { productId }, new int[] { cartProductDetailId }, peopleId,
				BasicUtil.getAppId());
	}

	@Override
	public int saveEntity(net.mingsoft.mall.entity.CartEntity cart) {
		// 检查购物车是否存在
		OrderProductEntity op = new OrderProductEntity();
		op.setOpPeopleId(cart.getCartPeopleId());
		op.setOpProductDetailId(cart.getCartProductDetailId());
		op.setOpProductId(cart.getCartBasicId());
		op.setOpPeopleId(cart.getCartPeopleId());
		op.setOpStatus(OrderProductEntity.OpStatus.CART);
		// 如果存在就直接更新数量，如果不存在就进行新增操作

		List<net.mingsoft.mall.entity.CartEntity> list = mallCartDao.query(null, new int[] { cart.getCartBasicId() },cart.getCartProductDetailId()>0?new int[]{cart.getCartProductDetailId()}:null, cart.getCartPeopleId(), BasicUtil.getAppId());

		// 商品有规格 且 有数据
		if (list != null && list.size() > 0) {
			net.mingsoft.mall.entity.CartEntity _cart = list.get(0);
			_cart.setCartNum(_cart.getCartNum()+cart.getCartNum());
			cartDao.updateEntity(_cart);
			return list.get(0).getCartId();
		} else {
			// 取出当前信息的标题保存在基础购物车
			ProductEntity product = (ProductEntity) productDao.getEntity(cart.getCartBasicId());
			if (product == null) {
				return 0;
			}
			// 判断商品有无规格
			if(op.getOpProductDetailId() != 0){
				ProductSpecificationDetailEntity detail = (ProductSpecificationDetailEntity) detailDao.getEntity(cart.getCartProductDetailId());
				cart.setCartTitle(product.getBasicTitle());
				cart.setCartUrl(product.getProductLinkUrl());
				cart.setCartPrice(detail.getPrice());
				cart.setCartDiscount(product.getProductCostPrice());
				cart.setCartThumbnail(product.getBasicThumbnails());
				cartDao.saveEntity(cart);
				if (cart.getCartProductDetailId() > 0) {
					// 根据商品规格信息取出标题与图片
					op.setOpTitle(detail.getSpecValues()); // 前端不需要商品名字
					String[] temp = detail.getSpecValues().split(",");
					for (String _temp : temp) {
						if (_temp.split(":").length > 2) {
							op.setOpThumbnail(_temp.split(":")[2]);
							op.setOpTitle(_temp.split(":")[0] + ":" + _temp.split(":")[1]);
							break;
						}
					}
					op.setOpGoodsId(cart.getCartId());
					orderProductDao.saveEntity(op);
				}
				return cart.getCartId();
			}else{
				cart.setCartTitle(product.getBasicTitle());
				cart.setCartUrl(product.getProductLinkUrl());
				cart.setCartPrice(product.getProductPrice());
				cart.setCartDiscount(product.getProductCostPrice());
				cart.setCartThumbnail(product.getBasicThumbnails());
				cartDao.saveEntity(cart);
				return cart.getCartId();
			}
		}

	}

}

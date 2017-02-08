/**
 * 
 */
package net.mingsoft.mall.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;

import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.mall.biz.ICartBiz;
import net.mingsoft.mall.dao.ICartDao;
import net.mingsoft.mall.entity.ProductEntity;
import net.mingsoft.order.entity.CartEntity;

@Service("mallCartBiz")
public class CartBizImpl extends BaseBizImpl implements ICartBiz {

	/**
	 * 注入商品关注持久化层
	 */
	@Resource(name = "mallCartDao")
	private ICartDao cartDao;

	/**
	 * 关联dao
	 */
	@Override
	protected IBaseDao getDao() {
		return this.cartDao;
	}

	@Override
	public List<net.mingsoft.mall.entity.CartEntity> query(int[] cartIds, int[] cartProductDetailIds, int peopleId) {
		return cartDao.query(cartIds, cartProductDetailIds, peopleId, BasicUtil.getAppId());
	}

}

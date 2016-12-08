package net.mingsoft.mall.action.people;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.mingsoft.base.filter.DateValueFilter;
import com.mingsoft.base.filter.DoubleValueFilter;
import com.mingsoft.people.entity.PeopleEntity;

import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.mall.biz.ICartBiz;
import net.mingsoft.mall.biz.IProductBiz;
import net.mingsoft.mall.entity.ProductEntity;
import net.mingsoft.order.constant.ModelCode;
import net.mingsoft.order.entity.CartEntity;

/**
 * 铭飞商城-购物车
 * 
 * @author 铭飞开发团队
 * @version 版本号：100-000-000<br/>
 *          创建日期：2014年7月12日<br/>
 *          历史修订：<br/>
 */
@Controller("peopleMallOrderCartAction")
@RequestMapping("/people/mall/order/cart")
public class CartAction extends com.mingsoft.people.action.BaseAction {

	/**
	 * 注入购物车信息
	 */
	@Resource(name = "mallCartBiz")
	private ICartBiz cartBiz;
	
	@Autowired
	private net.mingsoft.order.biz.ICartBiz _cartBiz;
	
	@Autowired
	private IProductBiz productBiz;
	/**
	 * 购物车列表 <br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * [<br/>
	 * { <br/>
	 * "cartDiscount": 折扣, <br/>
	 * "cartId": 自增长编号, <br/>
	 * "cartNum": 数量, <br/>
	 * "cartPrice": 价格, <br/>
	 * "cartThumbnail": "缩略图", <br/>
	 * "cartTime": "添加日期", <br/>
	 * "cartTitle": "标题"<br/>
	 * "cartUrl": "链接地址"<br/>
	 * }<br/>
	 * ]<br/>
	 */
	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		List list = cartBiz.query(new CartEntity(this.getPeopleBySession(request).getPeopleId(), BasicUtil.getAppId()));
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(list,new DoubleValueFilter(),new DateValueFilter("yyyy-MM-dd")));
	}
	
	/**
	 * 添加到购物车，如果购物车内已经存在一样的信息，系统会只更新相同信息的数量
	 * 
	 * @param cart
	 *            <i>cart参数包含字段信息参考：</i><br/>
	 *            cartId 购物车编号<br/>
	 *            cartBasicId 信息编号<br/>
	 *            cartNum 数量<br/>
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            {code:"错误编码",<br/>
	 *            result:"true｜false",<br/>
	 *            resultMsg:"错误信息"<br/>
	 *            }
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public void save(@ModelAttribute CartEntity cart, HttpServletResponse response, HttpServletRequest request) {
		PeopleEntity people = (PeopleEntity) getPeopleBySession();
		if (cart.getCartBasicId() <= 0) {
			this.outJson(response, ModelCode.ORDER_CART, false);
			return;
		}
		
		cart.setCartPeopleId(people.getPeopleId());
		CartEntity _cart  = (CartEntity)_cartBiz.getEntity(cart);
		// 查询购物车中是否存在该商品
		if (_cart != null) {
			// 更新商品数量
			_cart.setCartNum(_cart.getCartNum() + cart.getCartNum());
			_cartBiz.updateEntity(_cart);
			this.outJson(response, ModelCode.ORDER_CART, true);
			return;
		} else {
			ProductEntity product = (ProductEntity)productBiz.getEntity(cart.getCartBasicId());
			if (product == null) {
				this.outJson(response, ModelCode.ORDER_CART, false);
				return;
			}
			cart.setCartThumbnail(product.getBasicThumbnails());
			cart.setCartTitle(product.getBasicTitle());
			// 保存会员ID
			cart.setCartPeopleId(people.getPeopleId());
			cart.setCartAppId(BasicUtil.getAppId());
			cart.setCartPrice(product.getProductPrice());
			cart.setCartUrl(product.getProductLinkUrl());
			_cartBiz.saveEntity(cart);
			// 购物车添加商品成功后返回当前的购物车ID至页面
			this.outJson(response, ModelCode.ORDER_CART, true);
		}

	}

}

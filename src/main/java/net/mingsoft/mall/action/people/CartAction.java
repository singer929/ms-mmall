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

import com.mingsoft.base.filter.DateValueFilter;
import com.mingsoft.base.filter.DoubleValueFilter;
import com.mingsoft.people.entity.PeopleEntity;

import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.mall.biz.ICartBiz;
import net.mingsoft.mall.biz.IOrderProductBiz;
import net.mingsoft.mall.biz.IProductBiz;
import net.mingsoft.mall.entity.OrderProductEntity;
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
	private ICartBiz mallCartBiz;
	
	
	@Autowired
	private net.mingsoft.order.biz.ICartBiz cartBiz;
	
	/**
	 *用户已购买的商品规格数据
	 */
	@Autowired
	private IOrderProductBiz orderProductBiz;
	
	
	/**
	 * 购物车列表 <br/>
	 *            <i>参数：</i><br/>
	 *            cartIds 购物车编号，多个用逗号隔开<br/>
	 *            cartProductDetailIds 规格编号，多个用逗号隔开<br/>
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
	 * "cartProductDetailText": "规格内容，格式如：颜色：红,尺寸:L"<br/>
	 * "cartUrl": "链接地址"<br/>
	 * }<br/>
	 * ]<br/>
	 */
	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		int[] cartIds = BasicUtil.getInts("cartIds",",");
		List list = mallCartBiz.query(cartIds, null, this.getPeopleBySession().getPeopleId());
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(list,new DoubleValueFilter(),new DateValueFilter("yyyy-MM-dd")));
	}
	
	/**
	 * 删除购物车中的商品</br>
	 * 当执行单个删除时直接在地址中传入ID即可</br>
	 * 当执行批量删除时多个cartIds直接用逗号隔开,cartIds=1,2,3,4，cartProductDetailIds用逗号隔开，位置个数与cartId一直
	 *
	 *            <i>参数：</i><br/>
	 *            cartIds 购物车编号，多个用逗号隔开<br/>
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            {code:"错误编码",<br/>
	 *            result:"true｜false",<br/>
	 *            resultMsg:"错误信息"<br/>
	 *            }
	 */
	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		PeopleEntity people = this.getPeopleBySession();
		int[] cartIds = BasicUtil.getInts("cartIds",",");
		int appId = BasicUtil.getAppId();
		int i=0; 
		for (int id : cartIds) {
			if (id > 0) {
				cartBiz.deleteEntity(new CartEntity(id, people.getPeopleId(), appId,-1));
				orderProductBiz.deleteEntity(new OrderProductEntity(cartIds[i],people.getPeopleId(),OrderProductEntity.OpStatus.CART));
			}
			i++;
		}
		this.outJson(response, ModelCode.ORDER_CART, true);
	}
	
	
	/**
	 * 添加到购物车，如果购物车内已经存在一样的信息，系统会只更新相同信息的数量
	 * 
	 * @param cart
	 *            <i>cart参数包含字段信息参考：</i><br/>
	 *            cartId 购物车编号<br/>
	 *            cartBasicId 信息编号<br/>
	 *             cartProductDetailId 规格编号<br/>
	 *            cartNum 数量<br/>
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            {code:"错误编码",<br/>
	 *            result:"true｜false",<br/>
	 *            resultMsg:"错误信息"<br/>
	 *            }
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public void save(@ModelAttribute net.mingsoft.mall.entity.CartEntity cart, HttpServletResponse response, HttpServletRequest request) {
		PeopleEntity people = (PeopleEntity) getPeopleBySession();
		if (cart.getCartBasicId() <= 0) {
			this.outJson(response, ModelCode.ORDER_CART, false);
			return;
		}
		cart.setCartPeopleId(people.getPeopleId());
		cart.setCartAppId(BasicUtil.getAppId());
		//检查是否存在规格信息
		this.outJson(response, ModelCode.ORDER_CART, mallCartBiz.saveEntity(cart)>0);
	}

	/**
	 * 更新购物车,只能更新购物车的数量 <br/>
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
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(@ModelAttribute net.mingsoft.mall.entity.CartEntity cart, HttpServletResponse response, HttpServletRequest request) {
		PeopleEntity people = (PeopleEntity) this.getPeopleBySession(request);
		if (cart.getCartBasicId() <= 0 || cart.getCartId() <= 0 || cart.getCartNum() <= 0) {
			this.outJson(response, ModelCode.ORDER_CART, false);
			return;
		}
		cart.setCartPeopleId(people.getPeopleId());
		cart.setCartAppId(BasicUtil.getAppId());
		cartBiz.updateEntity(cart);
		this.outJson(response, ModelCode.ORDER_CART, true);
	}
}

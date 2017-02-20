package net.mingsoft.mall.action.people;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mingsoft.base.filter.DateValueFilter;
import com.mingsoft.base.filter.DoubleValueFilter;
import com.mingsoft.util.StringUtil;

import net.mingsoft.basic.bean.ListBean;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.mall.biz.ICartBiz;
import net.mingsoft.mall.entity.CartEntity;
import net.mingsoft.mall.biz.IOrderBiz;
import net.mingsoft.order.constant.ModelCode;
import net.mingsoft.order.constant.e.OrderStatusEnum;
import net.mingsoft.order.entity.GoodsEntity;
import net.mingsoft.mall.entity.OrderEntity;

/**
 * 商城订单管理控制层
 * 
 * @author 铭飞开发团队
 * @version 版本号：1.0.0<br/>
 *          创建日期：<br/>
 *          历史修订：<br/>
 */
@Controller("peopleMallOrderAction")
@RequestMapping("/people/mall/order")
public class OrderAction extends net.mingsoft.people.action.BaseAction {

	/**
	 * 注入订单评价业务层
	 */
	@Resource(name = "mallOrderBiz")
	private IOrderBiz mallOrderBiz;
	

	

	/**
	 * 注入购物车信息
	 */
	@Resource(name = "mallCartBiz")
	private ICartBiz mallCartBiz;

	/**
	 * 订单详情
	 * 
	 * @param order
	 *            <i>order参数包含字段信息参考：</i><br/>
	 *            "orderNo": "订单号", <br/>
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            <i>order参数包含字段信息参考：</i><br/>
	 *            "orderUserName": "收货人", <br/>
	 *            "orderPhone": "联系电话", <br/>
	 *            "orderAddress": "收货地址", <br/>
	 *            "orderDescription": "订单描述留言", <br/>
	 *            "orderExpress": 快递方式, <br/>
	 *            "orderExpressTitle": 快递派送描述, <br/>
	 *            "orderInvoiceName": 发票抬头, <br/>
	 *            "orderInvoiceDefinite": 发票明细, <br/>
	 *            "orderInvoiceType": 发票类型，暂时不用, <br/>
	 *            "orderPayment": 支付方式, <br/>
	 *            "orderStatus": 订单状态－数值, <br/>
	 *            "orderExpressInfo":订单物流信息，没有返回null
	 *            格式参考http://m.kuaidi100.com/query?type=yuantong&postid=
	 *            882595201560601055
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public void detail(@ModelAttribute OrderEntity order, HttpServletRequest request, HttpServletResponse response) {
		if (order == null) {
			this.outJson(response, ModelCode.ORDER, false);
			return;
		}

		if (StringUtil.isBlank(order.getOrderNo())) {
			this.outJson(response, ModelCode.ORDER, false);
			return;
		}

		OrderEntity _order = (OrderEntity) mallOrderBiz.getByOrderNo(order.getOrderNo());
		if (_order.getOrderPeopleId() != this.getPeopleBySession().getPeopleId()) {
			this.outJson(response, ModelCode.ORDER, false);
			return;
		}
		this.outJson(response, net.mingsoft.base.util.JSONObject.toJSONString(_order, new DoubleValueFilter(),
				new DateValueFilter("yyyy-MM-dd")));
	}

	/**
	 * 订单列表
	 * 
	 * @param order
	 *            <i>order参数包含字段信息参考：</i><br/>
	 *            orderNo 订单号<br/>
	 *            orderStatus 小于0表示取所有的订单状态，默认0 订单<br/>
	 *            orderExpress 小于0表示取所有的配送方式订单状态,默认0 送货上门<br/>
	 *            orderPayment 小于0表示取所有的支付方式订单状态，默认0 未选择支付方式<br/>
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            { "list":[ {<br/>
	 *            //订单列表 "orderId": 订单自增长编号, <br/>
	 *            "orderPeopleId": 用户编号, <br/>
	 *            "orderNo": "订单号", <br/>
	 *            "orderTime": "下单日期", <br/>
	 *            "orderPrice": 订单金额, <br/>
	 *            "orderUserName": "收货人", <br/>
	 *            "orderPhone": "联系电话", <br/>
	 *            "orderAddress": "收货地址", <br/>
	 *            "orderDescription": "订单描述留言", <br/>
	 *            "orderExpress": 快递方式, <br/>
	 *            "orderExpressTitle": 快递方式-送货上门, <br/>
	 *            "orderPayment": 支付方式－数值, <br/>
	 *            "orderPaymentTitle": "支付方式－面付", <br/>
	 *            "orderStatus": 订单状态－数值, <br/>
	 *            "orderStatusTitle": "订单状态－已付款", <br/>
	 *            "orderCategoryId": 订单分类－平台自定义, <br/>
	 *            "orderModelId": 所属模块-平台自定义, <br/>
	 *            "peopleUser": {<br/>
	 *            "peopleUserIcon": "头像", <br/>
	 *            "peopleUserNickName": "昵称", <br/>
	 *            "peopleUserRealName": "真实姓名", <br/>
	 *            }<br/>
	 *            "goods": [<br/>
	 *            {<br/>
	 *            "goodsBasicId": 信息编号, <br/>
	 *            "goodsName": "标题", <br/>
	 *            "goodsNum": 数量, <br/>
	 *            "goodsPrice": 价格, <br/>
	 *            "goodsRebate": 折扣, <br/>
	 *            "goodsThumbnail": "缩略图", <br/>
	 *            }<br/>
	 *            ]<br/>
	 *            } ]<br/>
	 *            "page":{"endRow": 2, <br/>
	 *            "firstPage": 1, <br/>
	 *            "hasNextPage": true存在下一页false不存在, <br/>
	 *            "hasPreviousPage": true存在上一页false不存在, <br/>
	 *            "isFirstPage": true是第一页false不是第一页, <br/>
	 *            "isLastPage": true是最后一页false不是最后一页, <br/>
	 *            "lastPage": 最后一页的页码, <br/>
	 *            "navigatePages": 导航数量，实现 1...5.6.7....10效果, <br/>
	 *            "navigatepageNums": []导航页码集合, <br/>
	 *            "nextPage": 下一页, <br/>
	 *            "pageNum": 当前页码, <br/>
	 *            "pageSize": 一页显示数量, <br/>
	 *            "pages": 总页数, <br/>
	 *            "prePage": 上一页, <br/>
	 *            "size": 总记录, <br/>
	 *            "startRow": , <br/>
	 *            "total":总记录数量}<br/>
	 *            }<br/>
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(@ModelAttribute net.mingsoft.mall.entity.OrderEntity order, HttpServletRequest request, HttpServletResponse response) {
		if (order == null) {
			order = new  net.mingsoft.mall.entity.OrderEntity();
		}
		order.setOrderModelId(this.getModelCodeId(request, net.mingsoft.mall.constant.ModelCode.MALL_ORDER));
		order.setOrderAppId(BasicUtil.getAppId());
		order.setOrderPeopleId(this.getPeopleBySession().getPeopleId());
		order.setOrderTime(null);
		BasicUtil.startPage();
		List list = mallOrderBiz.query(order);
		
		ListBean _list = new ListBean(list, BasicUtil.endPage(list));
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(_list, new DoubleValueFilter(),new DateValueFilter("yyyy-MM-dd")));
	}

	/**
	 * 提交订单 <i>参数：</i><br/>
	 * cartIds 购物车编号，多个用逗号隔开<br/>
	 * cartProductDetailIds 规格编号，多个用逗号隔开<br/>
	 * 
	 * @param order
	 *            <i>order参数包含字段信息参考：</i><br/>
	 *            "orderUserName": "收货人", <br/>
	 *            "orderPhone": "联系电话", <br/>
	 *            "orderAddress": "收货地址", <br/>
	 *            "orderDescription": "订单描述留言", <br/>
	 *            "orderExpress": 快递方式, <br/>
	 *            "orderExpressTitle": 快递派送描述, <br/>
	 *            "orderInvoiceName": 发票抬头, <br/>
	 *            "orderInvoiceDefinite": 发票明细, <br/>
	 *            "orderInvoiceType": 发票类型，暂时不用, <br/>
	 *            "orderPayment": 支付方式, <br/>
	 *            "orderStatus": 订单状态－数值, <br/>
	 *            cartId: 购物车编号，多个编号用逗号隔开<br/>
	 */
	@RequestMapping("/submit")
	@ResponseBody
	public void submit(@ModelAttribute net.mingsoft.mall.entity.OrderEntity order, HttpServletRequest request,
			HttpServletResponse response) {
		int[] cartIds = BasicUtil.getInts("cartIds", ",");
		if (order == null) {
			this.outJson(response, ModelCode.ORDER, false);
			return;
		}

		if (StringUtil.isBlank(order.getOrderAddress()) || StringUtil.isBlank(order.getOrderUserName())
				|| StringUtil.isBlank(order.getOrderPhone())) {
			this.outJson(response, ModelCode.ORDER, false);
			return;
		}
		order.setOrderAppId(BasicUtil.getAppId());
		order.setOrderPeopleId(this.getPeopleBySession().getPeopleId());
		if (cartIds == null) {
			this.outJson(response, ModelCode.ORDER, false);
			return;
		}
		order.setOrderModelId(this.getModelCodeId(request, net.mingsoft.mall.constant.ModelCode.MALL_ORDER));
		mallOrderBiz.saveEntity(order, cartIds);
		this.outJson(response, order);
	}

}
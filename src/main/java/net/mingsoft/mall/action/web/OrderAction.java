package net.mingsoft.mall.action.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.util.AlipayNotify;
import net.mingsoft.bank.biz.IPayBiz;
import net.mingsoft.bank.constant.e.BankPayEnum;
import net.mingsoft.bank.entity.PayEntity;

import net.mingsoft.mall.action.BaseAction;
import net.mingsoft.order.biz.IOrderBiz;
import net.mingsoft.order.constant.e.OrderStatusEnum;
import net.mingsoft.order.entity.OrderEntity;

/**
 * 模块订单控制层 类的描述
 * 
 * @author fuchen
 * @version 项目名：ms-mupgrader 创建日期：:2015年10月22日 历史修订：2015年10月22日
 */
@Controller("mallOrder")
@RequestMapping("/mall/order")
public class OrderAction extends BaseAction {

	@Autowired
	private IPayBiz bankPayBiz;
	
	@Autowired
	private IOrderBiz orderBiz;

	/**
	 * 充值同步 , 该方式是通用的支付异步请求方法，需要对out_trade_no进行判断后进行业务处理<br/>
	 * 返回数据实例： <br/>
	 * buyer_email=killfen@126.com<br/>
	 * buyer_id=2088002041598607<br/>
	 * ckey=6plucw3sh7n17pnaq334chv0z673qb2q<br/>
	 * cpartner=2088511445728971<br/>
	 * discount=0.00<br/>
	 * gmt_create=2014-12-28 12:55:40<br/>
	 * gmt_payment=2014-12-28 12:56:04<br/>
	 * is_total_fee_adjust=N<br/>
	 * notify_id=7d5217bcdaef33b4a6c8fb73588232c95c<br/>
	 * notify_time=2014-12-28 12:56:04<br/>
	 * notify_type =trade_status_sync<br/>
	 * out_trade_no=1419738626037
	 * 注意out_trade_no：如果是积分的支付单号为score打头例如：score-虚拟币类型-模块编号-用户编号-随机码<br/>
	 * payment_type=1<br/>
	 * price=0.01<br/>
	 * quantity=1<br/>
	 * seller_email=942770163@qq.com<br/>
	 * seller_id=2088511445728971<br/>
	 * sign=de0ac9113a82c06887e32b6f7e9326c3<br/>
	 * sign_type=MD5<br/>
	 * subject=小米3 3G手机 银灰色16G 电信版<br/>
	 * total_fee=0.0<br/>
	 * 
	 * @param type
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "notify")
	public void notify(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

		String price = request.getParameter("price");

		// 支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

		// 交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		PayEntity bpe = (PayEntity) bankPayBiz.getEntity(new PayEntity(this.getAppId(request), BankPayEnum.ALIPAY.toString()));
		params.put("ckey", bpe.getBankPayKey());
		params.put("cpartner", bpe.getBankPayPartner());
		LOG.debug("支付回调");
		if (AlipayNotify.verify(params)) {// 验证成功
			OrderEntity order = orderBiz.getByOrderNo(out_trade_no);
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			LOG.debug("支付回调成功");
			if (trade_status.equals("TRADE_FINISHED")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序
				
				LOG.debug("支付回调订单:" + order.getOrderNo());
				if (order != null) {
					if (order.getOrderStatus() == OrderStatusEnum.UNPAY.toInt()) {
						order.setOrderStatus(OrderStatusEnum.PAY);
						orderBiz.updateEntity(order);
					}
				}
				// 注意：
				// 该种交易状态只在两种情况下出现
				// 1、开通了普通即时到账，买家付款成功后。
				// 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
				LOG.debug("支付回调订单 TRADE_SUCCESS:" + order.getOrderNo());
				if (order != null) {
					if (order.getOrderStatus() == OrderStatusEnum.UNPAY.toInt()) {
						order.setOrderStatus(OrderStatusEnum.PAY);
						orderBiz.updateEntity(order);
					}
				}
			}
			try {
				response.getWriter().print("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {// 验证失败
			try {
				LOG.debug("支付回调订单失败:");
				response.getWriter().print("fail");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

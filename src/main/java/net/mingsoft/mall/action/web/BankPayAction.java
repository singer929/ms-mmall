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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;
import com.mingsoft.bank.action.BaseAction;
import com.mingsoft.bank.biz.IBankPayBiz;
import com.mingsoft.bank.biz.IBankPeopleBiz;
import com.mingsoft.bank.constant.e.BankPayEnum;
import com.mingsoft.bank.constant.e.BankPayTypeEnum;
import com.mingsoft.bank.entity.BankPayEntity;
import com.mingsoft.base.constant.Const;
import com.mingsoft.util.StringUtil;

import net.mingsoft.basic.util.BasicUtil;

@Controller("webBankPayActionc")
@RequestMapping("/bank/paya")
public class BankPayAction extends BaseAction {

	/**
	 * 支付方式业务层
	 */
	@Autowired
	private IBankPayBiz bankPayBiz;

	/**
	 * 用户积分业务层
	 */
	@Autowired
	private IBankPeopleBiz bankPeopleBiz;

	/**
	 * 支付宝支付接口
	 * 
	 * @param pay
	 *            <i>peopleUser参数包含字段信息参考：</i><br/>
	 *            notifyUrl:接口异步请求地址<br/>
	 *            returnUrl:返回地址<br/>
	 *            orderNo:订单编号<br/>
	 *            orderName:订单标题<br/>
	 *            orderPrice:订单价格<br/>
	 *            orderDesc:订单描述<br/>
	 *            showUrl:商品显示地址<br/>
	 */
	@RequestMapping("alipay")
	public void alipay(@ModelAttribute Pay pay, HttpServletRequest request, HttpServletResponse response) {
		String notifyUrl = pay.getNotifyUrl();
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		if (StringUtil.isBlank(notifyUrl)) {
			this.outJson(response, false);
		}
		sParaTemp.put("_input_charset", Const.UTF8);// 参数编码字符集
		sParaTemp.put("notify_url", notifyUrl);// 服务器异步通知页面路径
		sParaTemp.put("return_url", pay.getReturnUrl());// 页面跳转同步通知页面路径
		sParaTemp.put("out_trade_no", pay.getOrderNo());// 商户网站唯一订单号
		sParaTemp.put("subject", pay.getOrderName());// 商品名称
		sParaTemp.put("total_fee", pay.getOrderPrice());// 交易金额
		sParaTemp.put("body", pay.getOrderDesc());// 商品描述
		sParaTemp.put("show_url", pay.getShowUrl());// 商品展示网址

		BankPayEntity bpe = (BankPayEntity) bankPayBiz.getEntity(new BankPayEntity(BasicUtil.getAppId(), "alipay"));
		if (bpe == null) {
			this.outJson(response, null, false, this.getResString("err.not.exist", this.getResString("bank.pay")));
			return;
		}

		// 判断是否是以支付宝的方式进行付款
		if (bpe.getBankPayType().equals(BankPayEnum.ALIPAY.toString())) {
			sParaTemp.put("service", bpe.getBankPayApitype());// 接口名称
			// sParaTemp.put("payment_type",
			// BankPayEnum.ALIPAY_PAYMENT_TYPE_SHOP.toString());//支付类型
			sParaTemp.put("partner", bpe.getBankPayPartner());// 合作者身份ID
			sParaTemp.put("ckey", bpe.getBankPayKey());// 商家密钥
			// 判断是否是手机端，如果是则按照手机端的方式进行
			if (this.isMobileDevice(request)) {// 判断是否是手机端
				sParaTemp.put("seller_id", bpe.getBankPayPartner());// 卖家支付宝用户号
				sParaTemp.put("service", bpe.getBankPayApitype());// 接口名称
				// 建立请求
				String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
				this.outString(response, sHtmlText);
			} else {
				sParaTemp.put("seller_email", bpe.getBankPayNo());// 卖家支付宝账号
				// 建立请求
				String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "post", "确认");
				this.outString(response, sHtmlText);
			}

		}
	}

	/**
	 * 支付宝支付接口
	 * @param pay
	 *            <i>peopleUser参数包含字段信息参考：</i><br/>
	 *            notifyUrl:接口异步请求地址<br/>
	 *            returnUrl:返回地址<br/>
	 *            orderNo:订单编号<br/>
	 *            orderName:订单标题<br/>
	 *            orderPrice:订单价格<br/>
	 *            orderDesc:订单描述<br/>
	 *            showUrl:商品显示地址<br/>
	 */
	@RequestMapping("weixin")
	public void weixin(@ModelAttribute Pay pay, HttpServletRequest request, HttpServletResponse response) {

	}
}

class Pay {
	private String notifyUrl;
	private String returnUrl;
	private String orderNo;
	private String orderName;
	private String orderPrice;
	private String orderDesc;
	private String showUrl;

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}

}

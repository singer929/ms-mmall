package net.mingsoft.mall.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingsoft.base.constant.e.BaseEnum;
import com.mingsoft.base.filter.DateValueFilter;
import com.mingsoft.base.filter.DoubleValueFilter;

import net.mingsoft.basic.bean.EUListBean;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.mall.biz.IOrderBiz;
import net.mingsoft.order.constant.e.OrderStatusEnum;

@Controller("managerMallOrder")
@RequestMapping("/${managerPath}/mall/order")
@Scope("request")
public class OrderAction extends BaseAction {

	@Resource(name = "mallOrderBiz")
	private IOrderBiz mallOrderBiz;


	@SuppressWarnings("rawtypes")
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("orderStatus", BasicUtil.enumToMap(OrderStatusEnum.class.getEnumConstants()));
		return this.view("/mall/order/index");
	}


	@RequestMapping("/list")
	@ResponseBody
	public void list(@ModelAttribute net.mingsoft.mall.entity.OrderEntity order, HttpServletRequest request,
			HttpServletResponse response) {
		if (order == null) {
			order = new net.mingsoft.mall.entity.OrderEntity();
		}
		order.setOrderModelId(this.getModelCodeId(request, net.mingsoft.mall.constant.ModelCode.MALL_ORDER));
		order.setOrderAppId(BasicUtil.getAppId());
		order.setOrderTime(null);
		order.setOrderPayment(-1);
		order.setOrderExpress(-1);
		BasicUtil.startPage();
		List list = mallOrderBiz.query(order);
		EUListBean _list = new EUListBean(list, (int) BasicUtil.endPage(list).getTotal());
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(_list, new DoubleValueFilter(),
				new DateValueFilter("yyyy-MM-dd")));
	}

	private void test(Enum e) {

	}
}

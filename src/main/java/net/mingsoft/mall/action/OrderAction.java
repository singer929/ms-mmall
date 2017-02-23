package net.mingsoft.mall.action;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
		Map orderStatus = new HashMap();
		BaseEnum[] ose = OrderStatusEnum.class.getEnumConstants();
		for (BaseEnum _ose : ose) {
			orderStatus.put(_ose.toInt() + "", _ose.toString());
			Enum e = (Enum) _ose;
		} 
		request.setAttribute("orderStatus", this.resToMap("net.mingsoft.mall.resources.product_field"));
		return this.view("/mall/order/index");
	}

	/**
	 * 枚举类型转map类型
	 * 
	 * @param clazz
	 * @return
	 */
	protected Map enumToMap(BaseEnum clazz) {
		return enumToMap(clazz, true);
	}

	/**
	 * 枚举类型转map类型
	 * 
	 * @param clazz
	 * @param idKey
	 * @return
	 */
	protected Map enumToMap(BaseEnum clazz, boolean idKey) {
		Map map = new HashMap();
		BaseEnum[] ose = clazz.getClass().getEnumConstants();
		for (BaseEnum _ose : ose) {
			if (idKey) {
				map.put(_ose.toInt() + "", _ose.toString());
			} else {
				Enum e = (Enum) _ose;
				map.put(e.name(), _ose.toString());
			}
		}
		return map;
	}
	
	/**
	 * 资源文件转map类型
	 * @param rb资源文件包路径
	 * @return map集合
	 */
	private Map resToMap(String resPath) {
		return this.resToMap(ResourceBundle.getBundle(resPath));
	}
	
	/**
	 * 资源文件转map类型
	 * @param rb资源文件
	 * @return map集合
	 */
	@SuppressWarnings("rawtypes")
	private Map resToMap(ResourceBundle rb) {
		Map map = new HashMap();
		Enumeration e = rb.getKeys();
		while(e.hasMoreElements()) {
			String key = e.nextElement().toString();
			map.put(key, rb.getString(key));
		}
		return map;
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
		BasicUtil.startPage();
		List list = mallOrderBiz.query(order);
		EUListBean _list = new EUListBean(list, (int) BasicUtil.endPage(list).getTotal());
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(_list, new DoubleValueFilter(),
				new DateValueFilter("yyyy-MM-dd")));
	}

	private void test(Enum e) {

	}
}

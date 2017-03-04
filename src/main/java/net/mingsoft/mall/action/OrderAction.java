package net.mingsoft.mall.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingsoft.base.constant.e.BaseEnum;
import com.mingsoft.base.filter.DateValueFilter;
import com.mingsoft.base.filter.DoubleValueFilter;
import com.mingsoft.basic.biz.ICategoryBiz;
import com.mingsoft.basic.entity.CategoryEntity;
import com.mingsoft.freight.biz.IFreightBiz;
import com.mingsoft.freight.entity.FreightEntity;
import com.mingsoft.util.StringUtil;

import net.mingsoft.base.util.JSONArray;
import net.mingsoft.basic.bean.EUListBean;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.mall.biz.IOrderBiz;
import net.mingsoft.mall.entity.OrderEntity;
import net.mingsoft.order.constant.e.OrderStatusEnum;

/**
 * 订单查询，发货，取消功能
 * @author 伍晶晶
 *
 */
@Controller("managerMallOrder")
@RequestMapping("/${managerPath}/mall/order")
@Scope("request")
public class OrderAction extends BaseAction {

	@Resource(name = "mallOrderBiz")
	private IOrderBiz mallOrderBiz;
	@Autowired
	private ICategoryBiz categoryBiz;
	@Autowired
	private IFreightBiz freightBiz;

	/**
	 * 订单主界面
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("orderStatus", BasicUtil.enumToMap(OrderStatusEnum.class.getEnumConstants()));
		return this.view("/mall/order/index");
	}

	/**
	 * 订单列表
	 * @param order 搜索条件订单实体
	 * @param request
	 * @param response
	 */
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

	/**
	 * 订单取消
	 * @param order 根据订单号取消订单
	 * @param request
	 * @param response
	 */
	@RequestMapping("/cancle")
	@ResponseBody
	public void cancle(@ModelAttribute net.mingsoft.mall.entity.OrderEntity order, HttpServletRequest request,
			HttpServletResponse response) {
		if(StringUtil.isBlank(order.getOrderNo())) {
			this.outJson(response, false);
			return;
		}
		OrderEntity _order =  (OrderEntity)mallOrderBiz.getByOrderNo(order.getOrderNo());
		_order.setOrderStatus(OrderStatusEnum.CLOSE);
		mallOrderBiz.updateEntity(_order);
		this.outJson(response, true);
	}
	
	/**
	 * 订单发货,发货需要选中快递公司，填写快递单号
	 * @param order 根据订单号发货
	 * @param request
	 * @param response
	 */
	@RequestMapping("/express")
	@ResponseBody
	public void express(@ModelAttribute net.mingsoft.mall.entity.OrderEntity order, HttpServletRequest request,
			HttpServletResponse response) {
		if(StringUtil.isBlank(order.getOrderExpressTitle()) || StringUtil.isBlank(order.getOrderExpressNo()) ||order.getOrderExpressPrice()<0) {
			this.outJson(response, false);
			return;
		}
		OrderEntity _order =  (OrderEntity)mallOrderBiz.getByOrderNo(order.getOrderNo());
		_order.setOrderStatus(OrderStatusEnum.SHIPPED); //设置发货状态
		_order.setOrderExpressTitle(order.getOrderExpressTitle()); //设置快递名称
		_order.setOrderExpressNo(order.getOrderExpressNo()); //设置快递号
		_order.setOrderExpressPrice(order.getOrderExpressPrice()); //设置快递价格
		mallOrderBiz.updateEntity(_order);
		this.outJson(response, true);
	}
	
	/**
	 * 返回已启用的快递公司名称与id集合
	 * @param order
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delivery")
	@ResponseBody
	public void delivery(@ModelAttribute net.mingsoft.mall.entity.OrderEntity order, HttpServletRequest request,
			HttpServletResponse response) {
		int freightCityId = Integer.parseInt(request.getParameter("orderExpressCityId"));//获取orderExpressCityId
		List<FreightEntity> freight = freightBiz.queryByCityEnable(freightCityId);//根据orderExpressCityId查询freight表中的启用数据
		int[] expressCompanyIds = new int[freight.size()];	
		List<String> expressCompanyTitles = new ArrayList();
		for( int i = 0 ; i<freight.size() ; i++){
			expressCompanyIds[i] = freight.get(i).getFreightExpressId();//根据获取启用数据的集合遍历出expressCompanyIds集合
			expressCompanyTitles.add(((CategoryEntity) categoryBiz.getEntity(expressCompanyIds[i])).getCategoryTitle());//通过id获取快递公司名称集合
		}
		List<Map> company = new ArrayList<Map>();
		int key;  
        Object value;  
		for(int i = 0 ; i<freight.size() ; i++){//将expressCompanyIds集合与快递公司名称集合组合成map数据
			Map valueMap = new HashMap();
			key = expressCompanyIds[i];
			value = expressCompanyTitles.get(i);
			valueMap.put("expressCompanyId", key);
			valueMap.put("expressCompanyTitle", value);
			company.add(valueMap);
		}
		String jsonStr = JSONArray.toJSONString(company);
		this.outJson(response,jsonStr); 
	}
}

package net.mingsoft.mall.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.mingsoft.mall.biz.IOrderBiz;

@Controller("managerMallOrder")
@RequestMapping("/${managerPath}/mall/order")
@Scope("request")
public class OrderAction extends BaseAction{
	
	@Resource(name="mallOrderBiz")
	private IOrderBiz mallOrderBiz;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return this.view("/mall/order/index");
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public void list(HttpServletRequest request, HttpServletResponse response) {
		this.outJson(response, "[{id:1,name:'abc',price:0.5},{id:1,name:'abc',price:0.5}]");
	}
}

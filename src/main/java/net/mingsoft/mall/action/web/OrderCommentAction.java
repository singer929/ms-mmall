package net.mingsoft.mall.action.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import net.mingsoft.basic.bean.ListBean;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.mall.bean.ProductCommentSummarBean;
import net.mingsoft.mall.biz.IOrderCommentBiz;
import net.mingsoft.mall.entity.OrderCommentEntity;
	
/**
 * 订单评价管理控制层
 * @author 铭飞开发团队
 * @version 
 * 版本号：1.0.0<br/>
 * 创建日期：<br/>
 * 历史修订：<br/>
 */
@Controller("webOrderCommentAction")
@RequestMapping("/mall/orderComment")
public class OrderCommentAction extends net.mingsoft.mall.action.BaseAction{
	
	/**
	 * 注入订单评价业务层
	 */	
	@Autowired
	private IOrderCommentBiz orderCommentBiz;
	
	
	/**
	 * 查询订单评价列表
	 * @param orderComment 订单评价实体
	 * <i>orderComment参数包含字段信息参考：</i><br/>
	 * ocCommentId 评论编号<br/>
	 * ocOrderId 订单编号<br/>
	 * ocImpression 印象<br/>
	 * ocProduct 商品符合度<br/>
	 * ocService 店家服务态度<br/>
	 * ocLogistics 物流发货速度<br/>
	 * ocServiceDescribe 评价服务<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * [<br/>
	 * { <br/>
	 * ocCommentId: 评论编号<br/>
	 * ocOrderId: 订单编号<br/>
	 * ocImpression: 印象<br/>
	 * ocProduct: 商品符合度<br/>
	 * ocService: 店家服务态度<br/>
	 * ocLogistics: 物流发货速度<br/>
	 * ocServiceDescribe: 评价服务<br/>
	 * }<br/>
	 * ]<br/>	 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(@ModelAttribute OrderCommentEntity orderComment,HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		BasicUtil.startPage();
		List orderCommentList = orderCommentBiz.query(orderComment);
		ListBean list = new ListBean(orderCommentList, BasicUtil.endPage(orderCommentList));
		this.outJson(response, JSONArray.toJSONStringWithDateFormat(list, "yyyy-MM-dd"));
	}
	
	/**
	 * 商品评论汇总
	 * @param orderComment 订单评价实体
	 * <i>orderComment参数包含字段信息参考：</i><br/>
	 * commentBasicId 商品编号<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * [<br/>
	 * { <br/>
	 * commenTcount: 评论编号<br/>
	 * goodRate: 好评率<br/>
	 * goodCount: 好评<br/>
	 * generalCount: 中评<br/>
	 * poorCount: 差评<br/>
	 * }<br/>
	 * ]<br/>
	 * @param response
	 * @param request
	 * @param model
	 */
	@RequestMapping("/summar")
	@ResponseBody
	public void summar(@ModelAttribute OrderCommentEntity orderComment,HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		//测试代码
		ProductCommentSummarBean pcsb = new ProductCommentSummarBean();
		this.outJson(response, pcsb);
	}
		
}
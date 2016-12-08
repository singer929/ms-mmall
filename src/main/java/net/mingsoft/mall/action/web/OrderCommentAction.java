package net.mingsoft.mall.action.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.mingsoft.mall.biz.IOrderCommentBiz;
import net.mingsoft.mall.entity.OrderCommentEntity;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.basic.bean.ListBean;
import net.mingsoft.basic.util.BasicUtil;
	
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
	 * 获取订单评价
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
	 * { <br/>
	 * ocCommentId: 评论编号<br/>
	 * ocOrderId: 订单编号<br/>
	 * ocImpression: 印象<br/>
	 * ocProduct: 商品符合度<br/>
	 * ocService: 店家服务态度<br/>
	 * ocLogistics: 物流发货速度<br/>
	 * ocServiceDescribe: 评价服务<br/>
	 * }<br/>
	 */
	@RequestMapping("/get")
	@ResponseBody
	public void get(@ModelAttribute OrderCommentEntity orderComment,HttpServletResponse response, HttpServletRequest request,ModelMap model){
		if(orderComment.getOcCommentId()<=0) {
			this.outJson(response, null, false, getResString("err.error", this.getResString("oc.comment.id")));
		}
		OrderCommentEntity _orderComment = (OrderCommentEntity)orderCommentBiz.getEntity(orderComment.getOcCommentId());
		model.addAttribute("orderComment", _orderComment);		
		BasicUtil.removeUrlParams(new String[]{"ocCommentId"});
		this.outJson(response, _orderComment);
	}
	
	/**
	 * 保存订单评价实体
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
	 * { <br/>
	 * ocCommentId: 评论编号<br/>
	 * ocOrderId: 订单编号<br/>
	 * ocImpression: 印象<br/>
	 * ocProduct: 商品符合度<br/>
	 * ocService: 店家服务态度<br/>
	 * ocLogistics: 物流发货速度<br/>
	 * ocServiceDescribe: 评价服务<br/>
	 * }<br/>
	 */
	@PostMapping("/save")
	@ResponseBody
	public void save(@ModelAttribute OrderCommentEntity orderComment, HttpServletResponse response, HttpServletRequest request) {
		//验证印象的值是否合法			
		if(StringUtil.isBlank(orderComment.getOcImpression())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oc.impression")));
			return;			
		}
		if(!StringUtil.checkLength(orderComment.getOcImpression()+"", 1, 255)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oc.impression"), "1", "255"));
			return;			
		}
		//验证商品符合度的值是否合法			
		if(StringUtil.isBlank(orderComment.getOcProduct())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oc.product")));
			return;			
		}
		if(!StringUtil.checkLength(orderComment.getOcProduct()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oc.product"), "1", "10"));
			return;			
		}
		//验证店家服务态度的值是否合法			
		if(StringUtil.isBlank(orderComment.getOcService())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oc.service")));
			return;			
		}
		if(!StringUtil.checkLength(orderComment.getOcService()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oc.service"), "1", "10"));
			return;			
		}
		//验证物流发货速度的值是否合法			
		if(StringUtil.isBlank(orderComment.getOcLogistics())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oc.logistics")));
			return;			
		}
		if(!StringUtil.checkLength(orderComment.getOcLogistics()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oc.logistics"), "1", "10"));
			return;			
		}
		//验证评价服务的值是否合法			
		if(StringUtil.isBlank(orderComment.getOcServiceDescribe())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oc.service.describe")));
			return;			
		}
		if(!StringUtil.checkLength(orderComment.getOcServiceDescribe()+"", 1, 255)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oc.service.describe"), "1", "255"));
			return;			
		}
		orderCommentBiz.saveEntity(orderComment);
		this.outJson(response, orderComment);
	}

	/**
	 * @param orderComment 订单评价实体
	 * <i>orderComment参数包含字段信息参考：</i><br/>
	 * ocCommentId:多个ocCommentId直接用逗号隔开,例如ocCommentId=1,2,3,4
	 * 批量删除订单评价
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            {code:"错误编码",<br/>
	 *            result:"true｜false",<br/>
	 *            resultMsg:"错误信息"<br/>
	 *            }
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(@ModelAttribute OrderCommentEntity orderComment,HttpServletResponse response, HttpServletRequest request) {
		int[] ids = BasicUtil.getInts("ocCommentId");
		if(ids==null){
			this.outJson(response,null, false);
			return;
		}
		orderCommentBiz.delete(ids);		
		this.outJson(response, true);
	}
	
	/** 
	 * 更新订单评价信息订单评价
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
	 * { <br/>
	 * ocCommentId: 评论编号<br/>
	 * ocOrderId: 订单编号<br/>
	 * ocImpression: 印象<br/>
	 * ocProduct: 商品符合度<br/>
	 * ocService: 店家服务态度<br/>
	 * ocLogistics: 物流发货速度<br/>
	 * ocServiceDescribe: 评价服务<br/>
	 * }<br/>
	 */
	@PostMapping("/update")
	@ResponseBody	 
	public void update(@ModelAttribute OrderCommentEntity orderComment, HttpServletResponse response,
			HttpServletRequest request) {
		//验证印象的值是否合法			
		if(StringUtil.isBlank(orderComment.getOcImpression())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oc.impression")));
			return;			
		}
		if(!StringUtil.checkLength(orderComment.getOcImpression()+"", 1, 255)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oc.impression"), "1", "255"));
			return;			
		}
		//验证商品符合度的值是否合法			
		if(StringUtil.isBlank(orderComment.getOcProduct())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oc.product")));
			return;			
		}
		if(!StringUtil.checkLength(orderComment.getOcProduct()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oc.product"), "1", "10"));
			return;			
		}
		//验证店家服务态度的值是否合法			
		if(StringUtil.isBlank(orderComment.getOcService())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oc.service")));
			return;			
		}
		if(!StringUtil.checkLength(orderComment.getOcService()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oc.service"), "1", "10"));
			return;			
		}
		//验证物流发货速度的值是否合法			
		if(StringUtil.isBlank(orderComment.getOcLogistics())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oc.logistics")));
			return;			
		}
		if(!StringUtil.checkLength(orderComment.getOcLogistics()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oc.logistics"), "1", "10"));
			return;			
		}
		//验证评价服务的值是否合法			
		if(StringUtil.isBlank(orderComment.getOcServiceDescribe())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oc.service.describe")));
			return;			
		}
		if(!StringUtil.checkLength(orderComment.getOcServiceDescribe()+"", 1, 255)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oc.service.describe"), "1", "255"));
			return;			
		}
		orderCommentBiz.updateEntity(orderComment);
		this.outJson(response, orderComment);
	}
	
		
}
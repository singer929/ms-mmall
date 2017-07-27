package net.mingsoft.mall.action;

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
import org.springframework.web.bind.annotation.RequestBody;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.mingsoft.mall.biz.IOrderCommentBiz;
import net.mingsoft.mall.entity.OrderCommentEntity;
import net.mingsoft.base.util.JSONObject;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;
import com.mingsoft.base.entity.BaseEntity;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.comment.biz.ICommentBiz;
import net.mingsoft.basic.bean.ListBean;
import com.mingsoft.base.filter.DateValueFilter;
import com.mingsoft.base.filter.DoubleValueFilter;
import com.mingsoft.mdiy.entity.SearchEntity;

import net.mingsoft.basic.bean.EUListBean;
	
/**
 * 订单评价管理控制层
 * @author 伍晶晶
 * @version 
 * 版本号：100<br/>
 * 创建日期：2017-7-22 11:12:39<br/>
 * 历史修订：<br/>
 */
@Controller
@RequestMapping("/${managerPath}/mall/orderComment")
public class OrderCommentAction extends net.mingsoft.mall.action.BaseAction{
	
	/**
	 * 注入订单评价业务层
	 */	
	@Autowired
	private IOrderCommentBiz orderCommentBiz;
	
	/**
	 * 注入评价业务层
	 */	
	@Autowired
	private ICommentBiz commentBiz;
	
	
	/**
	 * 返回主界面index
	 */
	@RequestMapping("/index")
	public String index(HttpServletResponse response,HttpServletRequest request){
		return view ("/mall/order_comment/index");
	}
	
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
	 * <dd>[<br/>
	 * { <br/>
	 * ocCommentId: 评论编号<br/>
	 * ocOrderId: 订单编号<br/>
	 * ocImpression: 印象<br/>
	 * ocProduct: 商品符合度<br/>
	 * ocService: 店家服务态度<br/>
	 * ocLogistics: 物流发货速度<br/>
	 * ocServiceDescribe: 评价服务<br/>
	 * }<br/>
	 * ]</dd><br/>	 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(@ModelAttribute OrderCommentEntity orderComment,HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		if(orderComment == null){
			orderComment = new OrderCommentEntity();
		}
		orderComment.setCommentAppId(BasicUtil.getAppId());
		BasicUtil.startPage();
		List orderCommentList = orderCommentBiz.query(orderComment);
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(new EUListBean(orderCommentList,(int)BasicUtil.endPage(orderCommentList).getTotal()),new DoubleValueFilter(),new DateValueFilter()));
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
	 * <dd>{ <br/>
	 * ocCommentId: 评论编号<br/>
	 * ocOrderId: 订单编号<br/>
	 * ocImpression: 印象<br/>
	 * ocProduct: 商品符合度<br/>
	 * ocService: 店家服务态度<br/>
	 * ocLogistics: 物流发货速度<br/>
	 * ocServiceDescribe: 评价服务<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/update")
	@ResponseBody	 
	public void update(@RequestBody List<OrderCommentEntity> orderComments, HttpServletResponse response,
			HttpServletRequest request) {
		if(orderComments.size() > 0){
			for(int i = 0;i<orderComments.size();i++){
				//验证印象的值是否合法			
				if(StringUtil.isBlank(orderComments.get(i).getOcImpression())){
					this.outJson(response, null,false,getResString("err.empty", this.getResString("oc.impression")));
					return;			
				}
				if(!StringUtil.checkLength(orderComments.get(i).getOcImpression()+"", 1, 255)){
					this.outJson(response, null, false, getResString("err.length", this.getResString("oc.impression"), "1", "255"));
					return;			
				}
				//验证商品符合度的值是否合法			
				if(StringUtil.isBlank(orderComments.get(i).getOcProduct())){
					this.outJson(response, null,false,getResString("err.empty", this.getResString("oc.product")));
					return;			
				}
				if(!StringUtil.checkLength(orderComments.get(i).getOcProduct()+"", 1, 10)){
					this.outJson(response, null, false, getResString("err.length", this.getResString("oc.product"), "1", "10"));
					return;			
				}
				//验证店家服务态度的值是否合法			
				if(StringUtil.isBlank(orderComments.get(i).getOcService())){
					this.outJson(response, null,false,getResString("err.empty", this.getResString("oc.service")));
					return;			
				}
				if(!StringUtil.checkLength(orderComments.get(i).getOcService()+"", 1, 10)){
					this.outJson(response, null, false, getResString("err.length", this.getResString("oc.service"), "1", "10"));
					return;			
				}
				//验证物流发货速度的值是否合法			
				if(StringUtil.isBlank(orderComments.get(i).getOcLogistics())){
					this.outJson(response, null,false,getResString("err.empty", this.getResString("oc.logistics")));
					return;			
				}
				if(!StringUtil.checkLength(orderComments.get(i).getOcLogistics()+"", 1, 10)){
					this.outJson(response, null, false, getResString("err.length", this.getResString("oc.logistics"), "1", "10"));
					return;			
				}
				//验证评价服务的值是否合法			
				if(StringUtil.isBlank(orderComments.get(i).getOcServiceDescribe())){
					this.outJson(response, null,false,getResString("err.empty", this.getResString("oc.service.describe")));
					return;			
				}
				if(!StringUtil.checkLength(orderComments.get(i).getOcServiceDescribe()+"", 1, 255)){
					this.outJson(response, null, false, getResString("err.length", this.getResString("oc.service.describe"), "1", "255"));
					return;			
				}
				orderComments.get(i).setCommentAudit(1);
				commentBiz.updateEntity(orderComments.get(i));
			}
			this.outJson(response, true);
		}else{
			this.outJson(response, false);
		}
	}
		
}
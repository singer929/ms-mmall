package net.mingsoft.mall.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.basic.constant.Const;
import com.mingsoft.util.DateUtil;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.mall.biz.IPromotionBiz;
import net.mingsoft.mall.constant.ModelCode;
import net.mingsoft.mall.entity.PromotionEntity;

/**
 * 
 *  <p>
 * <b>限时促销</b>
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 * 
 * <p>
 * Company:景德镇铭飞科技有限公司
 * </p>
 * 
 * @author 郭鹏辉
 * 
 * @version 140-000-000
 * 
 *          <p>
 *          版权所有 铭飞科技
 *          </p>
 *
 *         @ClassName: PromotionAction
 *
 *          <p>
 *          Comments:  限时抢购业务处理层
 *          </p>
 * 
 *          <p>
 *          CreatrDate:Jun 1, 2015 3:49:37 PM
 *          </p>
 * 
 *          <p>
 *          Modification history:
 *          </p>
 *
 */
@Controller("promotionAction")
@RequestMapping("/${managerPath}/mall/promotion")
public class PromotionAction extends BaseAction {

	/**
	 * 注入商品抢购业务逻辑层
	 */
	@Resource(name="promotionBizImpl")
	private IPromotionBiz promotionBiz;
	
	/**
	 * 限时抢购商品列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		int appId = this.getAppId(request);
		Integer pageNo = this.getInt(request, "pageNo", 1);
		Integer pageSize = this.getInt(request,"pageSize", 10);
		//获取总条数
		int count = promotionBiz.queryCountByAppId(appId, null,null);
		//分页实体
		PageUtil page = new PageUtil(pageNo,pageSize,count,null);
		//分页查询
		List<PromotionEntity> promotionList = promotionBiz.queryByPageAppid(appId, null, page, null, false);
		
		model.addAttribute("promotionList",promotionList);
		model.addAttribute("page",page);
		return view("/mall/promotion/promotion_list");
	}
	
	/**
	 * 添加限时抢购商品
	 * @param request
	 * @param response
	 * @param promotion 抢购实体
	 * @return
	 */
	@RequestMapping("/save")
	public void save(@ModelAttribute PromotionEntity promotion, HttpServletRequest request, HttpServletResponse response){
		int appId = this.getAppId(request);
		String PromotionDate= request.getParameter("promotionStartEndDate");//分割日期
		
		Date[] date = DateUtil.beginEndStringToDate(PromotionDate, "至", "yyyy-MM-dd");
		if (date!=null) {
			promotion.setPromotionStartDate(date[0]);
			promotion.setPromotionEndDate(date[1]);
		}else{
			this.outJson(response, ModelCode.MALL_PROMOTION, false);
			return;
		}
		
		//验证表单
		if(!this.checkForm(promotion, request, response)){
			return;
		}
		promotion.setPromotionAppId(appId);
		//发布时间
		promotion.setPromotionDateTime(new Date());
		promotionBiz.saveEntity(promotion);
		this.outJson(response, ModelCode.MALL_PROMOTION, true);
	}
	
	/**
	 * 更新限时抢购商品
	 * @param promotionId 商品id
	 * @param request
	 * @param response
	 * @param promotion抢购实体
	 * @return
	 */
	@RequestMapping("/{promotionId}/update")
	public void update(@PathVariable int promotionId,@ModelAttribute PromotionEntity promotion , HttpServletRequest request, HttpServletResponse response){
		String PromotionDate= request.getParameter("promotionStartEndDate");//分割日期
		Date[] date = DateUtil.beginEndStringToDate(PromotionDate, "至", "yyyy-MM-dd");
		if (date!=null) {
			promotion.setPromotionStartDate(date[0]);
			promotion.setPromotionEndDate(date[1]);
		}else{
			this.outJson(response, ModelCode.MALL_PROMOTION, false);
			return;
		}
		//验证表单
		if(!this.checkForm(promotion, request, response)){
			return;
		}
		promotion.setPromotionAppId(this.getAppId(request));
		promotionBiz.updateEntity(promotion);
		this.outJson(response, ModelCode.MALL_PROMOTION, true);
	}
	
	
	/**
	 * 删除限时抢购商品
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response){
		int appId = this.getAppId(request);
		String [] ids = request.getParameterValues("ids");
		if(ids.length==0 || ids ==null){
			this.outJson(response, ModelCode.MALL_PROMOTION, false);
			return;
		}
		//删除多条商品
		promotionBiz.deletes(ids, appId, null);
		this.outJson(response, ModelCode.MALL_PROMOTION, true);
	}
	
	
	/**
	 * 获取限时商品
	 * @param promotionId 限时编号
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/{promotionId}/edit")
	public void edit(@PathVariable int promotionId,HttpServletRequest request, HttpServletResponse response){
		//获取限时商品实体
		PromotionEntity promotion = (PromotionEntity) promotionBiz.getEntity(promotionId);
		this.outJson(response, ModelCode.MALL_PROMOTION, true, JSONObject.toJSONStringWithDateFormat(promotion, "yyyy-MM-dd"));
	}
	
	/**
	 * 表单验证
	 * @param promotion 要验证的实体
	 * @param request
	 * @param response
	 * @return 是否验证成功
	 */
	public boolean checkForm(PromotionEntity promotion , HttpServletRequest request, HttpServletResponse response){
		//判断商品id是否大于0
		if(!StringUtil.isMaxZeroInteger(promotion.getPromotionProductId())){
			this.outJson(response, ModelCode.MALL_PROMOTION, false);
			return false;
		}
		//判断商品分类id是否大于0
		if(!StringUtil.isMaxZeroInteger(promotion.getProductCategoryId())){
			this.outJson(response, ModelCode.MALL_PROMOTION, false);
			return false;
		}
		//判断标题是否为null
		if(StringUtil.isBlank(promotion.getPromotionTitle())){
			this.outJson(response, ModelCode.MALL_PROMOTION, false);
			return false;
		}
		//判断标题长度
		if(!StringUtil.checkLength(promotion.getPromotionTitle(), 1, 50)){
			this.outJson(response, ModelCode.MALL_PROMOTION, false);
			return false;
		}
		//判断活动时间是否为null
		if(StringUtil.isBlank(promotion.getPromotionStartTime()) || StringUtil.isBlank(promotion.getPromotionEndTime()) ){
			this.outJson(response, ModelCode.MALL_PROMOTION, false);
			return false;
		}
		//如果起止日期为同一天
		if(promotion.getPromotionStartDate().equals(promotion.getPromotionEndDate())){
			//转换时间
			Date startDate = DateUtil.stringToDate(promotion.getPromotionStartTime(), "HH:mm");
			Date endDate=DateUtil.stringToDate(promotion.getPromotionEndTime(), "HH:mm");;
			//比较时间
			if(endDate.before(startDate)){
				this.outJson(response, ModelCode.MALL_PROMOTION, false);
				return false;
			}
		}
		
		return true;
	}
	

}

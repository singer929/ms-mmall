package net.mingsoft.mall.action;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.basic.biz.ICategoryBiz;
import com.mingsoft.basic.constant.Const;
import com.mingsoft.basic.constant.e.CookieConstEnum;
import com.mingsoft.basic.entity.CategoryEntity;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.mall.biz.IEntityShopBiz;
import net.mingsoft.mall.constant.ModelCode;
import net.mingsoft.mall.entity.EntityShopEntity;

/**
 * 
 * 
 * <p>
 * <b>铭飞MS平台</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 * 
 * <p>
 * Company:景德镇铭飞科技有限公司
 * </p>
 * 
 * @author 史爱华
 * 
 * @version 300-001-001
 * 
 * 
 *          <p>
 *          版权所有 铭飞科技
 *          </p>
 * 
 *          <p>
 *          Comments: 产品控制层，继承BaseAction
 *          </p>
 * 
 *          <p>
 *          Create Date:2015-02-03
 *          </p>
 * 
 *          <p>
 *          Modification history:
 *          </p>
 */
@Controller
@RequestMapping("/${managerPath}/mall/entityShop")
public class EntityShopAction extends BaseAction {
	
	/**
	 * 注入商品实体店业务层
	 */
	@Autowired
	private IEntityShopBiz entityShopBiz;
	
	/**
	 * 基础分类业务层
	 */
	@Autowired
	private ICategoryBiz categoryBiz;

	
	/**
	 * 商品店列表信息
	 * @param request
	 * @param mode
	 * @param response
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, ModelMap mode, HttpServletResponse response){
		// 当前页面
		int pageNo = 1;
		// 获取页面的当页数
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		
		//获取当前app下的商品店总数
		int recordCount = this.entityShopBiz.getCountByAppId(this.getAppId(request));
		PageUtil page=new PageUtil(pageNo,recordCount,getUrl(request)+"/manager/mall/entityShop/list.do");
		//分页查询当前app下的商品店
		List<EntityShopEntity> listMallEntityShop= this.entityShopBiz.queryListPageByAppId(this.getAppId(request),page);
		//压入返回的url地址
		String url = getUrl(request)+"/manager/mall/entityShop/list.do";
		this.setCookie(request, response, CookieConstEnum.BACK_COOKIE,url+"?pageNo="+pageNo);
		mode.addAttribute("listMallEntityShop", listMallEntityShop);
		mode.addAttribute("page", page);
		return view("/mall/entityshop/entity_shop_list");
	}
	
	/**
	 * 异步请求门店信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/listForAjax")
	@ResponseBody
	public void listForAjax(HttpServletRequest request, HttpServletResponse response) {
		List<EntityShopEntity> entityShops= this.entityShopBiz.queryListPageByAppId(this.getAppId(request),null);
		this.outJson(response, JSONObject.toJSONString(entityShops));
	}
	
	
	/**
	 * 跳转到商品实体店保存页面
	 * @return 站点保存页面
	 */
	@RequestMapping("/add")
	public String add(HttpServletRequest request,ModelMap mode){
		int appId= this.getAppId(request);
		mode.addAttribute("appId",appId);
		List<CategoryEntity>listCity = categoryBiz.queryByAppIdOrModelId(appId,this.getModelCodeId(request,com.mingsoft.basic.constant.ModelCode.CITY));
		mode.addAttribute("listCity",listCity);
		return view("/mall/entityshop/entity_shop");
	}
	
	/**
	 * 保存实体信息
	 * @param mallEntityShop 商店实体
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/save")
	public void save(@ModelAttribute EntityShopEntity mallEntityShop,HttpServletRequest request, HttpServletResponse response){
		//判断提交数据是否符合规范
		if(!checkForm(mallEntityShop,response)){ 
				return ;
		}
		// 获取appId
		int appId = getManagerBySession(request).getBasicId();
		mallEntityShop.setEntityShopAppId(appId);
		// 设置发布时间
		mallEntityShop.setBasicDateTime(new Timestamp(System.currentTimeMillis()));
		this.entityShopBiz.saveBasic(mallEntityShop);
		this.outJson(response,null, true);
	}
	
	/**
	 * 根据id删除实体对象
	 * @param basicId
	 * @param request
	 * @param response
	 */
	@RequestMapping("/{basicId}/delete")
	public void delete(@PathVariable int basicId, HttpServletRequest request, HttpServletResponse response){
		this.entityShopBiz.deleteBasic(basicId);
		// 获取cookie
		String cookie =this.getCookie(request, CookieConstEnum.BACK_COOKIE);
		this.outJson(response, null, true,String.valueOf(cookie));
	}
	
	/**
	 * 跳转到门店编辑页面
	 * @param basicId 商品id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/{basicId}/edit")
	public String edit(@PathVariable int basicId, HttpServletRequest request, ModelMap mode){
		int appId= this.getAppId(request);
		mode.addAttribute("appId",appId);
		List<CategoryEntity>listCity = categoryBiz.queryByAppIdOrModelId(appId,this.getModelCodeId(request,com.mingsoft.basic.constant.ModelCode.CITY));
		mode.addAttribute("listCity",listCity);
		EntityShopEntity entityShop = (EntityShopEntity) this.entityShopBiz.getEntity(basicId);
		mode.addAttribute("entityShop",entityShop);
		return view("/mall/entityshop/entity_shop");
	}
	
	/**
	 * 更新门店信息
	 * @param mallEntityShop 门店实体
	 * @param request
	 * @param response
	 */
	@RequestMapping("/update")
	public void update(@ModelAttribute EntityShopEntity mallEntityShop, HttpServletRequest request, HttpServletResponse response){
		//判断提交数据是否符合规范
		if(!checkForm(mallEntityShop,response)){
				return ;
		}
		this.entityShopBiz.updateBasic(mallEntityShop);
		// 获取cookie
		String cookie =this.getCookie(request, CookieConstEnum.BACK_COOKIE);
		this.outJson(response, ModelCode.MALL_PRODUCT, true,String.valueOf(cookie));
	}
	
	/**
	 * 验证门店信息的合法性
	 * @param mallEntityShop 门店实体
	 * @param response
	 */
	private boolean checkForm(EntityShopEntity mallEntityShop, HttpServletResponse response){
		//判断商品店的名称是否介于1-300之间
		if(!StringUtil.checkLength(mallEntityShop.getBasicTitle(), 1, 200)){
				this.outJson(response, ModelCode.MALL_PRODUCT, false, getResString("err.length", this.getResString("basicTitle"), "1", "200"));
				return false;
		}
		
		//判断商品店的详细地址是否介于1-300之间
		if(!StringUtil.checkLength(mallEntityShop.getEntityShopAddress(), 1, 200)){
				this.outJson(response, ModelCode.MALL_PRODUCT, false, getResString("err.length", this.getResString("basicTitle"), "1", "200"));
				return false;
		}
		
		return true;
	}
}

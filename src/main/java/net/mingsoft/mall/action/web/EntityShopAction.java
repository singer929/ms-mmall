package net.mingsoft.mall.action.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import net.mingsoft.mall.action.BaseAction;
import net.mingsoft.mall.biz.IEntityShopBiz;
import net.mingsoft.mall.constant.ModelCode;
import net.mingsoft.mall.entity.EntityShopEntity;

@Controller("webEntityShopAction")
@RequestMapping("/mall/entityShop")
public class EntityShopAction extends BaseAction{
	
	/**
	 * 门店业务层
	 */
	@Autowired
	private IEntityShopBiz entityShopBiz;
	
	/**
	 * 根据门店id查询门店实体信息
	 * @param entityShopId 门店id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/{entityShopId}/getEntity")
	public void getEntity(@PathVariable("entityShopId")Integer entityShopId,HttpServletRequest request,HttpServletResponse response){
		EntityShopEntity entityShop= this.entityShopBiz.getEntityShopInfor(entityShopId);
		
		if(entityShop != null){
			this.outJson(response,null,true,JSONObject.toJSONString(entityShop));
		}else{
			this.outJson(response, ModelCode.MALL_PRODUCT,false,this.getResString("err"));
		}
	}
	
	/**
	 * 获取所有的商店信息
	 * @param request
	 * @param mode
	 * @param response
	 */
	@RequestMapping("/query")
	public void query(HttpServletRequest request, ModelMap mode, HttpServletResponse response){
		
		List<EntityShopEntity> listEntityShop = this.entityShopBiz.queryListPageByAppId(this.getAppId(request), null);
		String jsonStr = JSONObject.toJSONString(listEntityShop);
		LOG.debug(jsonStr);
		this.outJson(response, jsonStr);
	}
	
	/**
	 * 获取所有的商店信息
	 * @param request
	 * @param mode
	 * @param response
	 */
	@RequestMapping("/queryListInfor")
	public void queryListInfor(HttpServletRequest request, ModelMap mode, HttpServletResponse response){
		
		List  listEntityShop = this.entityShopBiz.queryListInforByAppId(this.getAppId(request), null);
		String jsonStr = JSONObject.toJSONString(listEntityShop);
		LOG.debug(jsonStr);
		this.outJson(response, jsonStr);
	}
	
	/**
	 * 根据省份id查找该省的所有门店信息
	 * @param request
	 * @param mode
	 * @param response
	 */
	@RequestMapping("/{provinceId}/queryListByProvinceId")
	public void queryListByProvinceId(HttpServletRequest request, ModelMap mode, HttpServletResponse response,@PathVariable("provinceId")Integer provinceId){
		List  listEntityShop = this.entityShopBiz.queryListByProvinceId(this.getAppId(request),provinceId, null);
		String jsonStr = JSONObject.toJSONString(listEntityShop);
		LOG.debug(jsonStr);
		this.outJson(response, jsonStr);
	}
}

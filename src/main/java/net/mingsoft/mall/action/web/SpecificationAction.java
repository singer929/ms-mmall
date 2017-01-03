package net.mingsoft.mall.action.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.mall.action.BaseAction;
import net.mingsoft.mall.biz.ISpecificationBiz;
import net.mingsoft.mall.constant.ModelCode;
import net.mingsoft.mall.entity.SpecificationEntity;

/**
 * 
 * <p>
 * <b>铭飞科技-商品</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 *
 * @author 成卫雄
 *                QQ:330216230
 *
 * <p>
 * Comments:商品规格控制层
 * </p>
 *
 * <p>
 * Create Date:2014-11-26
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Controller("webSpecificationAction")
@RequestMapping("/mall/specifications")
public class SpecificationAction extends BaseAction{
	
	@Autowired 
	private ISpecificationBiz specBiz;
	
	/**
	 * 获取规格列表,返回JSON数据
	 * @param request
	 * @param response
	 */
	@RequestMapping("/listByAjax")
	public void listByAjax(HttpServletRequest request, HttpServletResponse response){
		
		Integer tmp = BasicUtil.getInt("specCateId");
		int specCateId = (tmp == null) ? 0 : tmp.intValue();
		
		int appId = BasicUtil.getAppId();
		List<SpecificationEntity> list = specBiz.queryPageByAppId(appId, null);
		
		if(list != null && list.size() > 0){
			this.outJson(response, ModelCode.MALL_SPECIFICATIONS,true, JSONObject.toJSONString(list));
		}
		else{
			this.outJson(response, ModelCode.MALL_SPECIFICATIONS, false);
		}
	}
}

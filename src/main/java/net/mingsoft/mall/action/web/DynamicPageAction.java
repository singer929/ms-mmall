/**
 * 
 */
package net.mingsoft.mall.action.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mingsoft.util.StringUtil;

import net.mingsoft.mall.action.BaseAction;
import net.mingsoft.mall.parser.MallParser;

/**
 * 
 * 
 * 
 * <p>
 * <b>铭飞科技</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 * 
 * @author killfen
 * 
 * <p>
 * Comments:该类作废，自定义页面请调用对应模块下面的DynamicPageAction
 * </p>
 * 
 * <p>
 * Create Date:2015-4-20
 * </p>
 * 
 * <p>
 * Modification history:动态生成页面，需要后台配置自定义页数据
 * </p>
 */
@Controller("mallDynamicPageAction")
@RequestMapping("/mall")
@Deprecated
public class DynamicPageAction extends com.mingsoft.mdiy.action.BaseAction{
	private String date = "{date/}";
	private String host = "{host/}";
	/**
	 *文章解析器
	 */
	@Autowired
	private MallParser mallParser;
	
	/**
	 * 前段会员中心所有页面都可以使用该方法 请求地址例如：　／{key}.do,例如登陆界面，与注册界面都可以使用
	 * 
	 * @param key
	 */
	@RequestMapping("/{key}.do")
	@ExceptionHandler(java.lang.NullPointerException.class) 
	public void model(@PathVariable(value = "key") String key, HttpServletRequest req, HttpServletResponse resp) {
		String content = this.generaterPage(key,mallParser,req);
		if (StringUtil.isBlank(content)) {
			this.outString(resp, this.getResString("err"));
			return;
		}
		//增加时间
		content =  content.replace(date, StringUtil.getDateSimpleStr());
		content =  content.replace(host, this.getApp(req).getAppHostUrl());
		this.outString(resp, content);
	}
	
}

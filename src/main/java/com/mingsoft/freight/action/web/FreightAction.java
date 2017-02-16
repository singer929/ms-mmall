/**
The MIT License (MIT) * Copyright (c) 2016 铭飞科技(mingsoft.net)

 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.mingsoft.freight.action.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.mingsoft.basic.action.BaseAction;
import com.mingsoft.basic.biz.ICategoryBiz;
import com.mingsoft.basic.constant.ModelCode;
import com.mingsoft.basic.entity.CategoryEntity;
import com.mingsoft.freight.biz.IFreightBiz;

import net.mingsoft.basic.util.BasicUtil;


/**
 * 
 * @author ww
 *
 */
@Controller()
@RequestMapping("/${managerPath}/freight")
public class FreightAction extends BaseAction {

	/**
	 * 注入用户基础业务层
	 */
	@Autowired
	private IFreightBiz freightBiz;
	
	/**
	 * 业务层的注入
	 */
	@Autowired
	private ICategoryBiz categoryBiz;
	
	/**
	 * 左边城市列表
	 * @param categoryEntity 新建实体，查询条件
	 * @param response
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/list")
	public String index(@ModelAttribute CategoryEntity categoryEntity, HttpServletResponse response, HttpServletRequest request) {
		//peopleBiz.
		//创建一个modeId(基于BasicUtil里的方法)
		int modelId = BasicUtil.getModelCodeId(ModelCode.CITY);
		//新建一个CategoryEntity实体，以供查询
		CategoryEntity category = new CategoryEntity();
		category.setCategoryCategoryId(modelId);
		//通过category查询数据库里的数据
		List<?> list = categoryBiz.queryChilds(category);	
		//返回给前端数据categoryJson，具体指的是查询后的数据
		request.setAttribute("categoryJson", JSONArray.toJSONString(list));		
		return view("/freight/freight_details/index");
	}
	
}
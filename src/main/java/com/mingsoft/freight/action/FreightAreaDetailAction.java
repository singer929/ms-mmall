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

package com.mingsoft.freight.action;

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
import com.mingsoft.freight.biz.IFreightAreaBiz;
import com.mingsoft.freight.biz.IFreightAreaDetailBiz;
import com.mingsoft.freight.entity.FreightAreaEntity;
import com.mingsoft.freight.entity.FreightAreaDetailEntity;

import net.mingsoft.basic.util.BasicUtil;

@Controller
@RequestMapping("/${managerPath}/freightAreaDetail")
public class FreightAreaDetailAction extends BaseAction {
	
	@Autowired
	private IFreightAreaBiz freightArealBiz;
	@Autowired
	private IFreightAreaDetailBiz freightAreaDetailBiz;
	@Autowired
	private ICategoryBiz categoryBiz;
	
	/**
	 * 加载页面显示所有区域信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	private String index(HttpServletRequest request){
		//左侧列表
		List<FreightAreaEntity> listArea = freightArealBiz.queryAllArea();
		//table
		List<FreightAreaDetailEntity> faList = freightAreaDetailBiz.queryAllFad();
		
		request.setAttribute("listArea", listArea);
		request.setAttribute("faList", faList);
		return view("/freight/areaDetail/index");
	}
	
	/**
	 * 加载页面显示快递信息
	 * @param area
	 * @param response
	 * @param request
	 */
	@RequestMapping("/list")
	private void list(@ModelAttribute FreightAreaDetailEntity area, HttpServletResponse response, HttpServletRequest request){
		
	}
}
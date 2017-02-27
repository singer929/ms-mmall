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
import com.mingsoft.freight.biz.IAreaBiz;
import com.mingsoft.freight.entity.AreaEntity;

import net.mingsoft.basic.util.BasicUtil;

/**
 * 运费模块区域的设置、添加、修改
 * @author 上官德辉
 *
 */
@Controller
@RequestMapping("/${managerPath}/freight/area")
public class AreaAction extends BaseAction {

	@Autowired
	private IAreaBiz freightAreaBiz;
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
		List<AreaEntity> listArea = freightAreaBiz.queryAllArea();
		request.setAttribute("listArea", listArea);
		//树形部分
		int modelId =  BasicUtil.getModelCodeId(ModelCode.CITY);
		CategoryEntity category = new CategoryEntity();
		category.setCategoryModelId(modelId);
		List<CategoryEntity> list = categoryBiz.queryChilds(category);
		String categoryJson = JSONArray.toJSONString(list);
		request.setAttribute("categoryJson", categoryJson);
		
		return view("/freight/area/index");
	}
	
	/**
	 * 更新区域信息
	 * @return
	 */
	@RequestMapping("/update")
	private void update(@ModelAttribute AreaEntity area, HttpServletResponse response, HttpServletRequest request){
		AreaEntity areaEntity = freightAreaBiz.getAreaEntity(area);
		this.outJson(response, null, true, null,areaEntity.getFaCityIds());
	}
	
	/**
	 * 保存添加的区域信息
	 * @return
	 */
	@RequestMapping("/save")
	private void save(@ModelAttribute AreaEntity area, HttpServletResponse response, HttpServletRequest request){
		AreaEntity areaEntity = freightAreaBiz.getAreaEntity(area);
		boolean op = false;
		if(areaEntity == null){
			freightAreaBiz.saveAreaEntity(area);
			op = true;
			this.outJson(response,op);
		}else{
			op = false;
			this.outJson(response,op);
		}
	}
	
	/**
	 * 删除区域功能
	 * @param area
	 * @param response
	 * @param request
	 */
	@RequestMapping("/delete")
	private void delete(@ModelAttribute AreaEntity area, HttpServletResponse response, HttpServletRequest request){
		String faIds = request.getParameter("faIds");
		String [] faIdsArr= faIds.split(",");
		freightAreaBiz.delete(faIdsArr);
	}
}
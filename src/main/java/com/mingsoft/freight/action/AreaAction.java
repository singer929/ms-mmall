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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mingsoft.base.entity.BaseEntity;
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
	private IAreaBiz areaBiz;
	@Autowired
	private ICategoryBiz categoryBiz;
	
	/**
	 * 加载页面显示所有区域信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	private String index(HttpServletRequest request){
		areaList(request);
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
	 * 查询所有区域
	 * @param request
	 * @return
	 */
	@RequestMapping("/areaList")
	private void areaList(HttpServletRequest request){
		//左侧列表
		List<BaseEntity> areas = areaBiz.queryAll();
		request.setAttribute("areas", areas);
	}
	
	/**
	 * 保存和修改添加的区域信息
	 * @param area
	 * @param response
	 * @param request
	 */
	@RequestMapping("/save")
	@ResponseBody
	private void save(@ModelAttribute AreaEntity area, HttpServletResponse response, HttpServletRequest request){
		String faTitle = area.getFaTitle();
		AreaEntity areaEntity = new AreaEntity();
		areaEntity.setFaTitle(faTitle);
		BaseEntity temporaryEntity = areaBiz.getEntity(areaEntity);
		if(temporaryEntity == null){
			areaBiz.saveEntity(area);
			this.outJson(response,true);
		}else{
			areaBiz.updateEntity(area);
			this.outJson(response,false);
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
		String areaIds = request.getParameter("areaIds");
		String[] areaId=areaIds.split(",");
		int[] ids=new int[areaId.length];
		for (int i = 0; i < areaId.length; i++) {
			ids[i]=Integer.parseInt(areaId[i]);
		}
		//删除freight_area_detail表和freight_area表
		areaBiz.deleteArea(ids);
		
	}
}
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

package net.mingsoft.freight.action;

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
import com.mingsoft.util.StringUtil;

import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.freight.biz.IAreaBiz;
import net.mingsoft.freight.entity.AreaEntity;

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
	@ResponseBody
	private void areaList(HttpServletResponse response,HttpServletRequest request){
		//左侧列表
		List<BaseEntity> areas = areaBiz.queryAll();
		this.outJson(response, areas);
	}
	
	/**
	 * 保存区域信息
	 * @param area
	 * @param response
	 * @param request
	 */
	@RequestMapping("/save")
	@ResponseBody
	private void save(@ModelAttribute AreaEntity area, HttpServletResponse response, HttpServletRequest request){
		areaBiz.saveEntity(area);
		this.outJson(response,true);
	}
	
	/**
	 * 修改区域信息
	 * @param area
	 * @param response
	 * @param request
	 */
	@RequestMapping("/update")
	@ResponseBody
	private void update(@ModelAttribute AreaEntity area, HttpServletResponse response, HttpServletRequest request){
		areaBiz.updateEntity(area);
		this.outJson(response,true);
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
		int[] ids = StringUtil.stringsToInts(areaId);
		//删除freight_area_detail表和freight_area表
		areaBiz.deleteArea(ids);
	}
}
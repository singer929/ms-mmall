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
import com.mingsoft.freight.biz.IFreightBiz;
import com.mingsoft.freight.entity.FreightEntity;

import net.mingsoft.basic.util.BasicUtil;


/**
 * 运费详情
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
	public String list(@ModelAttribute CategoryEntity categoryEntity, HttpServletResponse response, HttpServletRequest request) {
		//创建一个modeId(基于BasicUtil里的方法)
		int modelId = BasicUtil.getModelCodeId(ModelCode.CITY);
		//新建一个CategoryEntity实体，以供查询
		CategoryEntity category = new CategoryEntity();
		category.setCategoryModelId(modelId);
		//通过category查询数据库里的数据
		List<CategoryEntity> list = categoryBiz.queryChilds(category);	
		//返回给前端数据categoryJson，具体指的是查询后的数据
		String str = JSONArray.toJSONString(list);
		request.setAttribute("categoryJson", str);		
		return view("/freight/freight_details/index");
	}
	
	/**
	 * 右边页面显示数据
	 * @param freightEntity 前端传过来的城市id
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/form")
	public String form(@ModelAttribute FreightEntity freightEntity, HttpServletResponse response, HttpServletRequest request) {
		//将前端传过来的categoryId转成int类型
		int freightCityId = Integer.parseInt(request.getParameter("categoryId"));
		//创建一个modeId(基于BasicUtil里的方法)
		int modelId = BasicUtil.getModelCodeId(net.mingsoft.mall.constant.ModelCode.MALL_CATEGORY);
		//通过freightCityId查对应的数据
		List<FreightEntity> entityList = freightBiz.queryAllFreight(freightCityId , modelId);
		request.setAttribute("freightList", entityList);
		
		return view("/freight/freight_details/freight_form");
		
	}
	
	/**
	 * 更新运费基本数据
	 * @param freightEntity
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	public String update(@ModelAttribute FreightEntity freightEntity, HttpServletResponse response, HttpServletRequest request) {
		FreightEntity entity = freightBiz.queryByCityExpress(freightEntity);
		if(entity != null){
			freightBiz.updateEntity(freightEntity);
		}else{
			freightBiz.saveEntity(freightEntity);
		}
		
		return view("/freight/freight_details/freight_form");
		
	}
	
}
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
import com.mingsoft.freight.biz.IAreaBiz;
import com.mingsoft.freight.biz.IAreaDetailBiz;
import com.mingsoft.freight.biz.IFreightBiz;
import com.mingsoft.freight.entity.AreaEntity;
import com.mingsoft.freight.entity.FreightEntity;
import com.mingsoft.freight.entity.AreaDetailEntity;

import net.mingsoft.basic.util.BasicUtil;

/**
 * 运费模块的区域运费设置和修改
 * @author 上官德辉
 *
 */
@Controller
@RequestMapping("/${managerPath}/freight/areaDetail")
public class AreaDetailAction extends BaseAction {
	
	@Autowired
	private IFreightBiz freightBiz;
	@Autowired
	private IAreaBiz freightAreaBiz;
	@Autowired
	private IAreaDetailBiz freightAreaDetailBiz;
	
	/**
	 * 加载页面显示所有区域信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	private String index(HttpServletRequest request){
		//左侧列表
		List<BaseEntity> areas = freightAreaBiz.queryAll();
		request.setAttribute("areas", areas);
		return view("/freight/area_detail/index");
	}
	
	/**
	 * 右侧列表的快递信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	private String list(HttpServletRequest request){
		//table
		int modelId = BasicUtil.getModelCodeId(net.mingsoft.mall.constant.ModelCode.MALL_CATEGORY);
		int faId = Integer.parseInt(request.getParameter("faId"));
		List<AreaDetailEntity> faList = freightAreaDetailBiz.queryFreightAreaDetail(faId,modelId);
		request.setAttribute("faList", faList);
		request.setAttribute("faId", faId);
		return view("/freight/area_detail/list");
	}
	
	/**
	 * 区域运费的修改
	 * @param areaDetail
	 * @param freightEntity
	 * @param response
	 * @param request
	 */
	@RequestMapping("/update")
	private void update(HttpServletResponse response, HttpServletRequest request){
		String str = request.getParameter("str");
		List<AreaDetailEntity> areaDetailList = JSONArray.parseArray(str, AreaDetailEntity.class);
		for(int i=0;i<areaDetailList.size();i++){
			AreaDetailEntity areaDetailEntity = areaDetailList.get(i);
			//修改freight_area_detail表
			freightAreaDetailBiz.updateEntity(areaDetailEntity);
			//修改freigh表
			int fadAreaId = areaDetailEntity.fadAreaId;
			AreaEntity area = new AreaEntity();
			area.setFaId(fadAreaId);
			BaseEntity freightAreaEntity = freightAreaBiz.getEntity(area);
			String faCityIds = ((AreaEntity) freightAreaEntity).getFaCityIds();
			String[] faCityId = faCityIds.split(",");
			FreightEntity freightEntity = new FreightEntity();
			freightEntity.setFreightEnable(areaDetailEntity.getFadEnable());
			freightEntity.setFreightExpressId(areaDetailEntity.getFadExpressId());
			freightEntity.setFreightBaseAmount(areaDetailEntity.getFadBaseAmount());
			freightEntity.setFreightBasePrice(areaDetailEntity.getFadBasePrice());
			freightEntity.setFreightIncreaseAmount(areaDetailEntity.getFadIncreaseAmount());
			freightEntity.setFreightIncreasePrice(areaDetailEntity.getFadIncreasePrice());
			for(int j=0;j<faCityId.length;j++){
				freightEntity.setFreightCityId(Integer.parseInt(faCityId[j]));
				FreightEntity temporaryEntity = freightBiz.queryByCityExpress(freightEntity);
				if(temporaryEntity == null){
					freightBiz.saveEntity(freightEntity);
				}else{
					freightBiz.updateEntity(freightEntity);
				}
			}
		}
		
	}
	/**
	 * 区域运费的插入
	 * @param areaDetail
	 * @param freightEntity
	 * @param response
	 * @param request
	 */
	@RequestMapping("/save")
	private void save(HttpServletResponse response, HttpServletRequest request){
		String str = request.getParameter("str");
		List<AreaDetailEntity> areaDetailList = JSONArray.parseArray(str, AreaDetailEntity.class);
		for(int i=0;i<areaDetailList.size();i++){
			AreaDetailEntity areaDetailEntity = areaDetailList.get(i);
			//插入freight_area_detail表
			freightAreaDetailBiz.updateEntity(areaDetailEntity);
			//插入freigh表
			int fadAreaId = areaDetailEntity.fadAreaId;
			AreaEntity area = new AreaEntity();
			area.setFaId(fadAreaId);
			BaseEntity freightAreaEntity = freightAreaBiz.getEntity(area);
			String faCityIds = ((AreaEntity) freightAreaEntity).getFaCityIds();
			String[] faCityId = faCityIds.split(",");
			FreightEntity freightEntity = new FreightEntity();
			freightEntity.setFreightEnable(areaDetailEntity.getFadEnable());
			freightEntity.setFreightExpressId(areaDetailEntity.getFadExpressId());
			freightEntity.setFreightBaseAmount(areaDetailEntity.getFadBaseAmount());
			freightEntity.setFreightBasePrice(areaDetailEntity.getFadBasePrice());
			freightEntity.setFreightIncreaseAmount(areaDetailEntity.getFadIncreaseAmount());
			freightEntity.setFreightIncreasePrice(areaDetailEntity.getFadIncreasePrice());
			for(int j=0;j<faCityId.length;j++){
				freightEntity.setFreightCityId(Integer.parseInt(faCityId[j]));
				FreightEntity temporaryEntity = freightBiz.queryByCityExpress(freightEntity);
				if(temporaryEntity == null){
					freightBiz.saveEntity(freightEntity);
				}else{
					freightBiz.updateEntity(freightEntity);
				}
			}
		}
	}
}
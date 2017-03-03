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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingsoft.basic.action.BaseAction;
import com.mingsoft.basic.biz.ICategoryBiz;
import com.mingsoft.freight.biz.IFreightBiz;
import com.mingsoft.freight.entity.FreightEntity;

/**
 * 运费详情计算前段接口
 * @author 
 *
 */
@Controller("webFreight")
@RequestMapping("/freight")
public class FreightAction extends BaseAction {

	/**
	 * 注入城市基础业务层
	 */
	@Autowired
	private IFreightBiz freightBiz;
	
	/**
	 * 运费
	 * @param freigh
	 *  <i>freigh参数包含字段信息参考：</i><br/>
	 *            freightCityId:城市编号<br/>
	 *            freightExpressId:快递编号<br/>
	 *            scale：快递的重量<br/>
	 * <dt><span class="strong">返回：邮费价格</span></dt><br/>
	 * @param response
	 * @param request
	 */
	@RequestMapping("/cost")
	@ResponseBody
	public void cost(@ModelAttribute FreightEntity freigh, HttpServletResponse response, HttpServletRequest request) {
		FreightEntity freightentity = freightBiz.queryByCityExpress(freigh);
		String weigth = request.getParameter("scale");
		double scale = Double.parseDouble(weigth);
		boolean op = false;
		if(freightentity == null){
			this.outJson(response, op); 
		}else if(scale <= 0){
			this.outJson(response, op);
		}else{
			op = true;
			double FreightBasePrice = freightentity.getFreightBasePrice();					//基础运费
			double FreightBaseAmount = freightentity.getFreightBaseAmount();				//基础重量
			double FreightIncreasePrice = freightentity.getFreightIncreasePrice();			//增长运费
			double FreightIncreaseAmount = freightentity.getFreightIncreaseAmount();		//增长数量
			double surplusWeight = scale - FreightBaseAmount;						//获取超过的部分
			double IncreasePrice = Math.ceil(surplusWeight/FreightIncreaseAmount);	//获取超过的次数
			if(surplusWeight<0){
				String baseAmount =String.valueOf(FreightBaseAmount);
				this.outJson(response, op, baseAmount);							//如果不超过基础重量，直接输出基础运费
			}else{
				double postage = FreightBasePrice+FreightIncreasePrice*IncreasePrice;
				String cost =String.valueOf(postage);
				this.outJson(response, op, cost);										//如果超出，输出计算后的运费
			}
		}
		
	}
	
}
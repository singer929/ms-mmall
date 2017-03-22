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

package net.mingsoft.freight.biz;

import java.util.List;

import com.mingsoft.base.biz.IBaseBiz;

import net.mingsoft.freight.entity.FreightEntity;

public interface IFreightBiz extends IBaseBiz {
	
	/**
	 * 通过城市id查询启用数据
	 * @param freightCityId
	 * @return
	 */
	public List<FreightEntity> queryByCityEnable(int freightCityId);
	
	/**
	 * 通过快递公司分类categoryModelId和城市编号freightCityId查询运费数据
	 * @return
	 */
	public List<FreightEntity> queryAllFreight(int freightCityId , int categoryModelId);
	
	/**
	 * 保存或更新运费基础数据
	 * @param freightEntity
	 */
	public void saveOrUpdate(FreightEntity freightEntity);
	
	/**
	 * 计算运费
	 */
	public double cost(FreightEntity freightentity,double scale);
}
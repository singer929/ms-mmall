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

package com.mingsoft.freight.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.freight.entity.FreightEntity;

public interface IFreightDao extends IBaseDao {
	
	/**
	 * 通过城市id查询基础数据
	 * @return
	 */
	public List<FreightEntity> queryByCity(int freightCityId);
	
	/**
	 * 通过城市id与快递id查询基础数据
	 * @return
	 */
	public FreightEntity queryByCityExpress(FreightEntity entity);
	
	/**
	 * 通过城市id与快递分类id查询基础数据
	 * @return
	 */
	public List<FreightEntity> queryAllFreight(@Param("freightCityId") int freightCityId ,  @Param("categoryModelId") int categoryModelId);
	
	/**
	 * 保存数据
	 * @param entity
	 * @return
	 */
	public void saveEntity(FreightEntity entity);
	
	/**
	 * 通过快递公司idfreightExpressId和freightCityId更新运费数据
	 * @param entity
	 * @return
	 */
	public void updateEntity(FreightEntity entity);
}
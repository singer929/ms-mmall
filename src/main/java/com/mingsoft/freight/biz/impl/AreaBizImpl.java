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

package com.mingsoft.freight.biz.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.freight.biz.IAreaBiz;
import com.mingsoft.freight.dao.IAreaDao;
import com.mingsoft.freight.entity.AreaEntity;

@Service("freightAreaBizImpl")
public class AreaBizImpl extends BaseBizImpl implements IAreaBiz{

	/**
	 * 用户持久化层
	 */
	@Autowired
	private IAreaDao freightAreaDao; 
	
	/**
	 * 获取freightDao
	 */
	@Override
	protected IBaseDao getDao() {
		return freightAreaDao;
	}
	/**
	 * 查询全部区域信息
	 */
	public List<AreaEntity> queryAllArea() {
		return  (List<AreaEntity>) freightAreaDao.queryAllArea();
	}
	
	
	/**
	 * 删除区域信息
	 */
	@Override
	public void delete(String[] faIdsArr) {
		freightAreaDao.delete(faIdsArr);
	}
	
	/**
	 * 增加区域信息
	 */
	public void saveAreaEntity(AreaEntity area){
		freightAreaDao.saveAreaEntity(area);
	}
	
	/**
	 * 查找单个区域信息
	 */
	public AreaEntity getAreaEntity(AreaEntity newEntity) {
		return freightAreaDao.getAreaEntity(newEntity);
	}
}
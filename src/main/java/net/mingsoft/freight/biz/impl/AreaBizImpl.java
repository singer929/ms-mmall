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

package net.mingsoft.freight.biz.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;

import net.mingsoft.freight.biz.IAreaBiz;
import net.mingsoft.freight.dao.IAreaDao;
import net.mingsoft.freight.dao.IAreaDetailDao;

/**
 * 运费模块区域设置业务层实现类
 * @author 上官德辉
 *
 */
@Service("AreaBizImpl")
public class AreaBizImpl extends BaseBizImpl implements IAreaBiz{

	/**
	 * 持久化层
	 */
	@Autowired
	private IAreaDao areaDao; 
	@Autowired
	private IAreaDetailDao areaDetailDao; 
	
	/**
	 * 获取AreaDao
	 */
	@Override
	protected IBaseDao getDao() {
		return areaDao;
	}
	/**
	 * 删除freight_area_detail表和freight_area表
	 */
	@Override
	public void deleteArea(int[] ids) {
		areaDetailDao.delete(ids);
		areaDao.delete(ids);
		
	}
	
}
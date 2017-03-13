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
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.freight.biz.IAreaBiz;
import com.mingsoft.freight.biz.IAreaDetailBiz;
import com.mingsoft.freight.biz.IFreightBiz;
import com.mingsoft.freight.dao.IAreaDetailDao;
import com.mingsoft.freight.entity.AreaDetailEntity;
import com.mingsoft.freight.entity.AreaEntity;
import com.mingsoft.freight.entity.FreightEntity;

/**
 * 运费模块区域运费设置业务层实现类
 * @author 上官德辉
 *
 */
@Service("freightAreaDetailBizImpl")
public class AreaDetailBizImpl extends BaseBizImpl implements IAreaDetailBiz{

	/**
	 * 用户持久化层
	 */
	@Autowired
	private IAreaDetailDao freightAreaDetailDao; 
	@Autowired
	private IFreightBiz freightBiz;
	@Autowired
	private IAreaBiz freightAreaBiz;
	
	/**
	 * 获取freightDao
	 */
	@Override
	protected IBaseDao getDao() {
		return freightAreaDetailDao;
	}
	
	/**
	 * 获取所有的区域信息
	 */
	@Override
	public List<AreaDetailEntity> queryFreightAreaDetail(int faId,int modelId) {
		return freightAreaDetailDao.queryFreightAreaDetail(faId,modelId);
	}
	
	@Override
	public void saveOrUpdate(AreaDetailEntity areaDetailEntity) {
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
			FreightEntity temporaryEntity = (FreightEntity) freightBiz.query(freightEntity);
			if(temporaryEntity == null){
				freightBiz.saveEntity(freightEntity);
			}else{
				freightBiz.updateEntity(freightEntity);
			}
		}
		
	}
}
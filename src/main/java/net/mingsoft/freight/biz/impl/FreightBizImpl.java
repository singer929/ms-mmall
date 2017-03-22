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

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;

import net.mingsoft.freight.biz.IFreightBiz;
import net.mingsoft.freight.dao.IFreightDao;
import net.mingsoft.freight.entity.FreightEntity;

/**
 * 运费详情业务层实现类
 * @author 伍晶晶
 *
 */
@Service("freightBizImpl")
public class FreightBizImpl extends BaseBizImpl implements IFreightBiz{

	/**
	 * 用户持久化层
	 */
	@Autowired
	private IFreightDao freightDao; 
	
	/**
	 * 获取peopleDao
	 */
	@Override
	protected IBaseDao getDao() {
		return freightDao;
	}

	/**
	 * 通过快递公司分类categoryModelId和城市编号freightCityId查询运费数据
	 */
	@Override
	public List<FreightEntity> queryAllFreight(int freightCityId, int categoryModelId) {		
		return freightDao.queryAllFreight(freightCityId, categoryModelId);
	}

	/**
	 * 通过城市id查询启用数据
	 */
	@Override
	public List<FreightEntity> queryByCityEnable(int freightCityId) {
		return freightDao.queryByCityEnable(freightCityId);
	}

	/**
	 * 保存或更新运费基础数据
	 */
	@Override
	public void saveOrUpdate(FreightEntity freightEntity) {
		if(freightDao.getByEntity(freightEntity) != null){
			freightDao.updateEntity(freightEntity);
		}else{
			freightDao.saveEntity(freightEntity);
		}
		
	}
	@Override
	public double cost(FreightEntity freightentity, double scale) {
		if(freightentity == null){
			return -1;
		}else if(scale <= 0){
			return -1;
		}else if(freightentity.getFreightEnable() == 0){
			return -1;
		}
		else{
			double freightBasePrice = freightentity.getFreightBasePrice();					//基础运费
			double freightBaseAmount = freightentity.getFreightBaseAmount();				//基础重量
			double freightIncreasePrice = freightentity.getFreightIncreasePrice();			//增长运费
			double freightIncreaseAmount = freightentity.getFreightIncreaseAmount();		//增长数量
			double surplusWeight = scale - freightBaseAmount;								//获取超过的部分
			if(surplusWeight < 0){
				return freightBaseAmount;													//如果不超过基础重量，直接输出基础运费
			}else if(freightIncreaseAmount != 0){
				double IncreasePrice = Math.ceil(surplusWeight/freightIncreaseAmount);		//获取超过的次数
				return freightBasePrice+freightIncreasePrice*IncreasePrice;																//如果超出，输出计算后的运费
			}else if(freightIncreaseAmount == 0){
				return freightBaseAmount;
			}else{
				return -1;
			}
		}
	}

}
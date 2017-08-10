package net.mingsoft.mall.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.base.dao.IBaseDao;

import net.mingsoft.basic.biz.impl.BasicPeopleBizImpl;
import net.mingsoft.mall.biz.IProductPeopleBiz;
import net.mingsoft.mall.dao.IProductPeopleDao;

@Service("ProductPeopleBizImpl")
public class ProductPeopleBizImpl extends BasicPeopleBizImpl implements IProductPeopleBiz{

	@Autowired
	private IProductPeopleDao productPeopleDao;

	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return productPeopleDao;
	}

	@Override
	public List queryByPeople(int appId, int modelId, int peopleId) {
		// TODO Auto-generated method stub
		return productPeopleDao.queryByPeople(appId, modelId, peopleId);
	}
	
}

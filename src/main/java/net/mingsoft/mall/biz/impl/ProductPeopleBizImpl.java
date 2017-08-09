package net.mingsoft.mall.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mingsoft.basic.biz.impl.PeopleBizImpl;
import net.mingsoft.mall.biz.IProductPeopleBiz;
import net.mingsoft.mall.dao.IProductPeopleDao;

@Service("ProductPeopleBizImpl")
public class ProductPeopleBizImpl extends PeopleBizImpl implements IProductPeopleBiz{

	@Autowired
	private IProductPeopleDao productPeopleDao;
}

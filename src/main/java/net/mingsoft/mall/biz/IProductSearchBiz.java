package net.mingsoft.mall.biz;

import java.util.List;

import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.basic.biz.IBasicBiz;
import com.mingsoft.util.PageUtil;

import net.mingsoft.mall.entity.ProductEntity;
import net.mingsoft.mall.search.mapping.ProductMapping;

public interface IProductSearchBiz extends IBaseBiz {
	
	public List<ProductMapping> queryForSearchMapping();
}

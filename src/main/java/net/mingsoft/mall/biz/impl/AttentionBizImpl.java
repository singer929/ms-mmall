/**
 * 
 */
package net.mingsoft.mall.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.util.PageUtil;

import net.mingsoft.attention.constant.e.AttentionTypeEnum;
import net.mingsoft.attention.entity.BasicAttentionEntity;
import net.mingsoft.mall.biz.IAttentionBiz;
import net.mingsoft.mall.dao.IAttentionDao;
import net.mingsoft.mall.entity.ProductEntity;

/**
 * <p>
 * <b>铭飞-科技</b>
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2015 - 2016
 * </p>
 * 
 * @author 石马人山
 * 
 * @version 300-001-001
 * 
 * @Description: 商品关注业务层实现类
 *
 * <p>
 * Create Date:2015-6-18 上午1:50:19
 * </p>
 * 
 * <p>
 * Modification history:
 * </p>
 */
@Service("mallAttentionBiz")
public class AttentionBizImpl extends BaseBizImpl implements IAttentionBiz{
	
	/**
	 * 注入商品关注持久化层
	 */
	@Resource(name="mallAttentionDao")
	private IAttentionDao attentionDao;
	
	/**
	 * 关联dao
	 */
	@Override
	protected IBaseDao getDao() {
		return this.attentionDao;
	}
	
	
	@Override
	public List<ProductEntity> query(BasicAttentionEntity attentionEntity) {
		return this.attentionDao.query(attentionEntity);
	}
	

	
	
}

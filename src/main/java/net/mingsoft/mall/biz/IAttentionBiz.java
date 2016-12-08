package net.mingsoft.mall.biz;

import java.util.List;

import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.util.PageUtil;

import net.mingsoft.attention.constant.e.AttentionTypeEnum;
import net.mingsoft.attention.entity.BasicAttentionEntity;
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
 * @Description: 商品模块关注业务层
 *
 *               <p>
 *               Create Date:2015-6-18 上午1:49:42
 *               </p>
 * 
 *               <p>
 *               Modification history:
 *               </p>
 */
public interface IAttentionBiz extends IBaseBiz {

	/**
	 * 用户关注列表
	 * 
	 * @param attention
	 * @return
	 */
	List<ProductEntity> query(BasicAttentionEntity attention);

}

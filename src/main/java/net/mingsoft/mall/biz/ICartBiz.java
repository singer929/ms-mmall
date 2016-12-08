package net.mingsoft.mall.biz;

import java.util.List;

import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.util.PageUtil;

import net.mingsoft.attention.constant.e.AttentionTypeEnum;
import net.mingsoft.attention.entity.BasicAttentionEntity;
import net.mingsoft.mall.entity.ProductEntity;
import net.mingsoft.order.entity.CartEntity;

/**
 * 铭飞商城－购物车
 * @author 铭飞开发团队
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2014年7月12日<br/>
 * 历史修订：<br/>
 */
public interface ICartBiz extends IBaseBiz {

	/**
	 * 用户关注列表
	 * 
	 * @param attention
	 * @return
	 */
	List<ProductEntity> query(CartEntity cart);

}

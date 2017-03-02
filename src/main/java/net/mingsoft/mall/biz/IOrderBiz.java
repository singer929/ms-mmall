package net.mingsoft.mall.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.entity.BaseEntity;

import net.mingsoft.mall.entity.OrderEntity;

public interface IOrderBiz extends net.mingsoft.order.biz.IOrderBiz {
	/**
	 * 保存订单
	 * 
	 * @param order
	 *            订单实体
	 * @param cartIds
	 *            购物车编号
	 * @return 订单编号
	 */
	int saveEntity(OrderEntity order, int[] cartIds);

	List query(OrderEntity entity);

	public void editOrderStatus(net.mingsoft.order.entity.OrderEntity orderEntity);
}

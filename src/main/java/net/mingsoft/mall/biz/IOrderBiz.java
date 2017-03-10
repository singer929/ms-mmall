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
	
	/**
	 * 根据订单号修改订单状态
	 * @param orderEntity
	 */
	public void editOrderStatus(net.mingsoft.order.entity.OrderEntity orderEntity);
	
	/**
	 * 根据ID查找product
	 * @param id
	 * @return 
	 */
	public OrderEntity getEntityById(int id);
	
	/**
	 * 修改库存
	 */
	public void editProductStockByEntity(int productId, int productStock);
}

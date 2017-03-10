package net.mingsoft.mall.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.mingsoft.base.dao.IBaseDao;

import net.mingsoft.mall.entity.OrderEntity;

@Component("mallOrderDao")
public interface IOrderDao extends IBaseDao {
	/**
	 * 查询指定订单编号的订单
	 * 
	 * @param orderNo
	 *            订单编号
	 * @return 订单实体
	 */
	 OrderEntity getByOrderNo(@Param("orderNo") String orderNo);
	 
	 /**
	  * 根据订单号修改订单状态
	  * @param orderEntity
	  */
	 public void editOrderStatus(net.mingsoft.order.entity.OrderEntity orderEntity);
	 
	 /**
	 * 根据ID查找product
	 * @param id
	 */
	public OrderEntity getEntityById(@Param("cartId") int cartId);
	
	/**
	 * 修改库存
	 */
	public void editProductStockByEntity(@Param("productId") int  productId,@Param("productStock") int  productStock);
}

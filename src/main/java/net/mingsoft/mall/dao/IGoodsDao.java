package net.mingsoft.mall.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mingsoft.base.dao.IBaseDao;

import net.mingsoft.mall.entity.GoodsEntity;

@Component("mallGGoodsDao")
public interface IGoodsDao extends IBaseDao {
	/**
	 * 查询指定订单编号里的商品集合
	 * @param orderId 订单编号
	 * @return 返回商品集合
	 */
	public List<GoodsEntity> queryByOrderId(Integer orderId);
}

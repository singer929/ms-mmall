/**
 * 
 */
package net.mingsoft.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.util.PageUtil;

import net.mingsoft.attention.entity.BasicAttentionEntity;
import net.mingsoft.mall.entity.CartEntity;

/**
 * 铭飞商城－购物车
 * @author 铭飞开发团队
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2016年7月12日<br/>
 * 历史修订：<br/>
 */
@Component("mallCartDao")
public interface ICartDao extends IBaseDao {

	/**
	 * 购物车查询
	 * @param cartIds 购物车编号
	 * @param cartProductDetailIds 规格编号
	 * @param peopleId 用户编号
	 * @param appId 应用编号
	 * @return
	 */
	List<CartEntity> query(@Param("cartIds")int[] cartIds, @Param("cartProductDetailIds")int[] cartProductDetailIds, @Param("peopleId")int peopleId, @Param("appId")int appId);
	
}

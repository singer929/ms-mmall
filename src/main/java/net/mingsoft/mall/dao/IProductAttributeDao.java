package net.mingsoft.mall.dao;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.util.*;
import java.util.*;

import org.apache.ibatis.annotations.Param;

import net.mingsoft.mall.entity.ProductAttributeEntity;

/**
 * 产品规格关联持久层
 * @author 伍晶晶
 * @version 
 * 版本号：0.0<br/>
 * 创建日期：2017-8-15 14:29:39<br/>
 * 历史修订：<br/>
 */
public interface IProductAttributeDao extends IBaseDao {
	/**
	 * 根据商品id删除商品栏目绑定属性
	 * @param paProductId 商品主键编号
	 */
	void deleteByProduct(@Param("paProductId") int paProductId);
}
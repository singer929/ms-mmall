package net.mingsoft.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;

/**
 * 商品浏览记录
 * @author 伍晶晶
 *
 */
public interface IProductPeopleDao extends IBaseDao  {
	
	List queryByPeople(@Param("appId")int appId, @Param("modelId")int modelId, @Param("peopleId")int peopleId);

}

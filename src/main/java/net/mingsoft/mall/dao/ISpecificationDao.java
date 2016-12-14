package net.mingsoft.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.util.PageUtil;

import net.mingsoft.mall.entity.SpecificationEntity;
import net.mingsoft.mall.entity.SpecificationsEntity;

/**
 * 
 * 
 * <p>
 * <b>铭飞MS平台</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 * 
 * <p>
 * Company:景德镇铭飞科技有限公司
 * </p>
 * 
 * @author 姓名：史爱华
 * 
 * @version 300-001-001
 * 
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments:产品规格dao层接口，继承IBaseDao
 * </p>
 *  
 * <p>
 * Create Date:2014-11-20
 * </p>
 *
 * <p>
 * </p>
 */
public interface ISpecificationDao extends IBaseDao {
	
	/**
	 * 查询app下的规格数据 带分页功能
	 * @param appId	appId
	 * @param begin 查询起始位置
	 * @param end	查询结束位置
	 * @return	规格数据集合
	 */
	public List<SpecificationEntity> queryByAppId(@Param("appId") int appId, @Param("begin")int begin, @Param("end")int end);
	
	/**
	 * 统计appId下的规格数量
	 * @param appId
	 * @return 规格总数
	 */
	public int countByAppId(@Param("appId") int appId);
}

package net.mingsoft.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.util.PageUtil;

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
public interface ISpecificationsDao extends IBaseDao {
	

	/**
	 * 根据appId查询规格(带分页)
	 * 优化说名：dao成所以的查询语句都尽可能全面,比如说在业务层查詢使用queryById(int id),queryByName(String name) ,queryBy***(...)，通过方法的重载来提高程序扩展和可读性
	 * 但是dao中不能扩展。如果dao也使用这种方式将会导致在xml文件里面写一大堆类似的sql 查询语句，那么可以在dao编写查询方式的时候形参考虑全面一些，例如文章查询为例
	 * query(Integer categoryId,Integer appId,Integer modelId,String keyWord,String orderFiled,Boolean isDesc);等到形参可以再长都没关系，但是业务层还是使用重载的方式例如：
	 * queryByCategoryId() {
	 * 	dao.query(categoryId,null,null,null,null,null,null);
	 * }
	 * queryByCategoryIdAndAppId() {
	 * 	dao.query(categoryId,appId,null,null,null,null,null);
	 * }
	 * 这样做的好处是，减少了xml冗余的查询SQL语句，提供了重用性
	 * 总结：要做的只需要dao里面的查询等其他的方式形参丰富一些，考虑全一些，同时使用对象类型声明形参，这样就可以在xml中使用!=null这个条件，同时也不会影响控制层与业务层；
	 * @param appId 应用ID
	 * @param begin 开始
	 * @param end 开始
	 * @return 规格列表
	 */
	public List<SpecificationsEntity> query(@Param("appId")Integer appId,@Param("begin")Integer begin,@Param("end")Integer end);
	
	/**
	 * 根据appId查询规格总数
	 * @param appId 应用ID
	 * @return 规格总数
	 */
	public int count(@Param("appId")Integer appId);
	
}

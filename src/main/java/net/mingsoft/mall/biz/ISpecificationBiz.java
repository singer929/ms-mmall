package net.mingsoft.mall.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.util.PageUtil;

import net.mingsoft.mall.entity.SpecificationEntity;

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
 * @author 王敏
 * 
 * @version 300-001-001
 * 
 *          <p>
 *          版权所有 铭飞科技
 *          </p>
 * 
 *          <p>
 *          Comments:商品规格管理业务处理层 接口|| 继承IBaseBiz业务处理层
 *          </p>
 * 
 *          <p>
 *          Create Date:2014-7-14
 *          </p>
 * 
 *          <p>
 *          Modification history:
 *          </p>
 */
public interface ISpecificationBiz extends IBaseBiz{
	
	/**
	 * 根据appId查询规格(带分页)
	 * @param appId 应用ID
	 * @param page 分页
	 * @return 规格列表
	 */
	public List<SpecificationEntity> queryPageByAppId(int appId, PageUtil page);
	
	/**
	 * 根据产品规格类型查询数据
	 * @return
	 */
	public List<SpecificationEntity> queryBySpecCateId(int specCateId, int appId);
	
	/**
	 * 根据商品Id查找所相关的规格
	 * @param productId
	 * @return
	 */
	public List<SpecificationEntity> queryByProductId(int productId);
	
	/**
	 * 根据appId查询规格总数
	 * @param appId 应用ID
	 * @return 规格总数
	 */
	public int countByAppId(int appId);
	
	/**
	 * 依据名字删除规格实体和相关表的关联数据
	 * @param specName	规格名称
	 */
	public void deleteBySpecificationName(String specName);
	
	/**
	 * 依据ID删除规格实体和相关表的关联数据
	 * @param specId
	 */
	public void deleteSpecificationById(int specId);
}

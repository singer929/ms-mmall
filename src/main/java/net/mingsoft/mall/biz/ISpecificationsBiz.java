package net.mingsoft.mall.biz;

import java.util.List;


import com.mingsoft.base.biz.IBaseBiz;
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
 * @author 姓名 史爱华
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
public interface ISpecificationsBiz extends IBaseBiz{
	
	
	/**
	 * 根据appId查询规格(带分页)
	 * @param appId 应用ID
	 * @param page 分页
	 * @return 规格列表
	 */
	public List<SpecificationsEntity> queryPageByAppId(Integer appId,PageUtil page);
	
	/**
	 * 根据appId查询规格总数
	 * @param appId 应用ID
	 * @return 规格总数
	 */
	public int queryCountByAppId(Integer appId);
}

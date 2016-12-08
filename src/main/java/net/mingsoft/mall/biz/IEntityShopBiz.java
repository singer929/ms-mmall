package net.mingsoft.mall.biz;

import java.util.List;

import com.mingsoft.basic.biz.IBasicBiz;
import com.mingsoft.util.PageUtil;

import net.mingsoft.mall.entity.EntityShopEntity;

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
 *          Comments:商品实体店管理业务处理层 || 继承IBasicBiz业务处理层
 *          </p>
 * 
 *          <p>
 *          Create Date:2015-02-03
 *          </p>
 * 
 *          <p>
 *          Modification history:
 *          </p>
 */
public interface IEntityShopBiz  extends IBasicBiz {
	
	/**
	 * 根据应用id获取总数
	 * @param appId 应用id
	 * @return 门店总数
	 */
	public int getCountByAppId(Integer appId);
	
	/**
	 * 根据appid查询门店分页
	 * @param appId 
	 * @param page：页面信息
	 * @return 门店列表
	 */
	public List<EntityShopEntity> queryListPageByAppId(Integer appId,PageUtil page);
	
	/**
	 * 获取门店具体信息
	 * @param entityShopId 门店id
	 * @return 门店实体信息
	 */
	public EntityShopEntity getEntityShopInfor(Integer entityShopId);
	
	/**
	 * 根据appid查询产品门店的详细信息分页
	 * @param appId 应用id
	 * @param page：页面信息
	 * @return 门店列表信息
	 */
	public List  queryListInforByAppId(Integer appId,PageUtil page);
	
	/**
	 * 根据appId和省份id查询门店的详细信息
	 * @param appId 
	 * @param provinceId ：省份id
	 * @param page 页面
	 * @return 门店列表信息
	 */
	public List<EntityShopEntity> queryListByProvinceId(Integer appId,Integer provinceId,PageUtil page);
}

package net.mingsoft.mall.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.biz.impl.BasicBizImpl;
import com.mingsoft.util.PageUtil;

import net.mingsoft.mall.biz.IEntityShopBiz;
import net.mingsoft.mall.dao.IEntityShopDao;
import net.mingsoft.mall.entity.EntityShopEntity;

@Service("entityShopBiz")
public class EntityShopBizImpl extends BasicBizImpl implements IEntityShopBiz {
	
	@Autowired
	private IEntityShopDao entityShopDao;
	
	
	/**
	 * 获取IBaseDao的持久化层
	 * 
	 * @return 返回持articleDao的久化对象
	 */
	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return entityShopDao;
	}

	@Override
	public int getCountByAppId(Integer appId) {
		// TODO Auto-generated method stub
		return entityShopDao.getCountByAppId(appId);
	}

	@Override
	public List<EntityShopEntity> queryListPageByAppId(Integer appId, PageUtil page) {
		// TODO Auto-generated method stub
		if (page!=null) {
			return entityShopDao.queryPageByAppId(appId, page.getPageNo(), page.getPageSize());
		}
		return entityShopDao.queryPageByAppId(appId, null,null);
	}

	@Override
	public EntityShopEntity getEntityShopInfor(Integer entityShopId) {
		EntityShopEntity  entityShop= (EntityShopEntity) this.entityShopDao.getEntity(entityShopId);
		return entityShop;
	}

	
	@Override
	public List<EntityShopEntity> queryListInforByAppId(Integer appId, PageUtil page) {
		 List<EntityShopEntity> listEntityShop = this.queryListPageByAppId(appId, page);
		return listEntityShop;
	}

	@Override
	public List<EntityShopEntity> queryListByProvinceId(Integer appId, Integer provinceId,
			PageUtil page) {
		
		 List<EntityShopEntity>  listEntityShopInfo = null;
		 if(page!=null){
			 listEntityShopInfo = this.entityShopDao.queryListByProvinceId(appId, page.getPageNo(), page.getPageSize(),provinceId);
		 }else{
			 listEntityShopInfo = this.entityShopDao.queryListByProvinceId(appId,null, null,provinceId);
		 }
		
		return listEntityShopInfo;
	}
	
	
	
	
}

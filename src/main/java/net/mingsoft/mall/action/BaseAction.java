package net.mingsoft.mall.action;

import java.util.List;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingsoft.basic.biz.IModelBiz;
import com.mingsoft.basic.constant.e.SessionConstEnum;
import com.mingsoft.basic.entity.CategoryEntity;
import com.mingsoft.basic.entity.ModelEntity;
import com.mingsoft.util.StringUtil;

import net.mingsoft.mall.constant.ModelCode;
import net.mingsoft.order.constant.e.OrderStatusEnum;

/**
 * 
 * 
 * 
 * <p>
 * <b>铭飞科技</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 * 
 * @author 王天培 QQ:78750478
 * 
 *         <p>
 *         Comments:
 *         </p>
 * 
 *         <p>
 *         Create Date:2015-1-23
 *         </p>
 * 
 *         <p>
 *         Modification history:
 *         </p>
 */
public class BaseAction extends com.mingsoft.basic.action.BaseAction {
	/**
	 * 根据传递过来的模块编码获取当前订单模块
	 * 
	 * @param request
	 * @param modelId
	 *            编码
	 * @return 错误返回null
	 */
	protected ModelEntity getOrderModelCode(HttpServletRequest request, int modelId) {
		com.mingsoft.basic.biz.IModelBiz modelBiz = (com.mingsoft.basic.biz.IModelBiz) getBean(request.getServletContext(), "modelBiz");
		return modelBiz.getModel(IModelBiz.ORDER_MODEL, modelId);
	}

	/**
	 * 根据当前模块编码父模块编号
	 * 
	 * @param request
	 * @param code
	 *            编码
	 * @return 模块编号，如果没有返回0
	 */
	protected ModelEntity getOrderStatusModelCode(HttpServletRequest request) {
		Object obj = this.getSession(request, SessionConstEnum.MODEL_ID_SESSION);
		if (StringUtil.isInteger(obj)) {
			com.mingsoft.basic.biz.IModelBiz modelBiz = (com.mingsoft.basic.biz.IModelBiz) getBean(request.getServletContext(), "modelBiz");
			return modelBiz.getModel(IModelBiz.ORDER_STATUS_MODEL, Integer.parseInt(obj.toString()));
		} else {
			return null;
		}
	}

	
	/**
	 * 根据当前模块编码父模块编号
	 * 
	 * @param request
	 * @param modelCodeId
	 *            订单分类的编码
	 * 
	 * @return 模块编号，如果没有返回0
	 */
	protected ModelEntity getOrderStatusModelCode(HttpServletRequest request, int modelCodeId) {
		if (modelCodeId > 0) {
			com.mingsoft.basic.biz.IModelBiz modelBiz = (com.mingsoft.basic.biz.IModelBiz) getBean(request.getServletContext(), "modelBiz");
			return modelBiz.getModel(IModelBiz.ORDER_STATUS_MODEL, modelCodeId);
		} else {
			return null;
		}
	}
	
	/**
	 * 获取商城模块信息
	 * @param request
	 * @return 模块实体
	 */
	protected ModelEntity getMallModel(HttpServletRequest request) {
			com.mingsoft.basic.biz.IModelBiz modelBiz = (com.mingsoft.basic.biz.IModelBiz) getBean(request.getServletContext(), "modelBiz");
			return modelBiz.getEntityByModelCode(ModelCode.MALL);
	}

	/**
	 * 将订单状态取出显示在页面上面，依赖商城的订单状态
	 * 
	 * @param request
	 * @param response
	 * @param appId
	 *            应用编号
	 */
	protected List queryOrderStatus(HttpServletRequest request, HttpServletResponse response, int appId, int modelId) {
		ModelEntity orderModel = this.getOrderStatusModelCode(request, modelId);
		if (orderModel == null) {
			this.outString(response, this.getResString("err"));
			return null;
		}
		com.mingsoft.basic.biz.ICategoryBiz categoryBiz = (com.mingsoft.basic.biz.ICategoryBiz) getBean(request.getServletContext(), "categoryBiz");
		List<CategoryEntity> list = categoryBiz.queryByAppIdOrModelId(appId, orderModel.getModelId());
		OrderStatusEnum[] ose = OrderStatusEnum.values();
		for (OrderStatusEnum _ose:ose) {
			CategoryEntity _c = new CategoryEntity(_ose.toInt(), _ose.toString());
			list.add(_c);
		}		
		return list;
	}
	
	/**
	 * 查询商城订单分类
	 * @param request
	 * @return 订单状态分类
	 */
	protected List queryOrderStatus(HttpServletRequest request,HttpServletResponse response) {
		return this.queryOrderStatus(request, response, this.getAppId(request), this.getMallModel(request).getModelId());
	}

	/**
	 * 根据当前模块编码获取订单模块
	 * 
	 * @param request
	 * @return 错误返回null
	 */
	protected ModelEntity getOrderModelCode(HttpServletRequest request) {
		Object obj = this.getSession(request, SessionConstEnum.MODEL_ID_SESSION);
		if (StringUtil.isInteger(obj)) {
			com.mingsoft.basic.biz.IModelBiz modelBiz = (com.mingsoft.basic.biz.IModelBiz) getBean(request.getServletContext(), "modelBiz");
			return modelBiz.getModel(IModelBiz.ORDER_MODEL, Integer.parseInt(obj.toString()));
		} else {
			return null;
		}
	}
	

	@Override
	protected String getResString(String key) {
		// TODO Auto-generated method stub
		String str = "";
		try {
			str = super.getResString(key);
		} catch (MissingResourceException e) {
			str = net.mingsoft.mall.constant.Const.RESOURCES.getString(key);
		}

		return str;
	}

}


package net.mingsoft.mall.action.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.entity.ListJson;
import com.mingsoft.basic.biz.IBasicCategoryBiz;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.mall.action.BaseAction;
import net.mingsoft.mall.biz.IProductBiz;
import net.mingsoft.mall.constant.ModelCode;
import net.mingsoft.mall.constant.e.ProductEnum;
import net.mingsoft.mall.entity.ProductEntity;

/**
 * 铭飞商城模块－支付
 * @author 铭飞开发团队
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2016年8月2日<br/>
 * 历史修订：<br/>
 */
@Controller("webPayAction")
@RequestMapping("/mall/pay")
public class PayAction extends BaseAction{
	
	
	@RequestMapping("/notify")
	public void notify(HttpServletRequest request,HttpServletResponse response){
	}
	
	
}

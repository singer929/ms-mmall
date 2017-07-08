package net.mingsoft.mall.action.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mingsoft.basic.biz.ICategoryBiz;
import com.mingsoft.basic.entity.CategoryEntity;

import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.mall.action.BaseAction;
import net.mingsoft.mall.constant.ModelCode;

/**
 * 
 * 商城 品牌管理
 * 
 * @author 铭飞开发团队
 * @version 版本号：100-000-000<br/>
 *          创建日期：2016年7月8日<br/>
 *          历史修订：<br/>
 */
@Controller("webMallBrandAction")
@RequestMapping("/mall/brand")
public class BrandAction extends BaseAction {
	/**
	 * 业务层的注入
	 */
	@Resource(name="categoryBiz")
	private ICategoryBiz categoryBiz;

	/**
	 * 品牌列表<br/>
	 * <i>category参数包含字段信息参考：</i><br/>
	 * categoryCategoryId 商品分类编号<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * [{<br/>
	 * "categoryTitle": "乐视", <br/>
	 * "categoryDescription": "1", <br/>
	 * "categoryId": 6059, <br/>
	 * "categoryCategoryId": 6053, <br/>
	 * "categorySort": 0, <br/>
	 * "categorySmallImg": "", <br/>
	 * }]<br/> 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(@ModelAttribute CategoryEntity category, HttpServletRequest request,
			HttpServletResponse response) {
		List list = new ArrayList();
		if(category.getCategoryCategoryId() > 0){
			list = categoryBiz.queryChildrenCategory(category.getCategoryCategoryId(), BasicUtil.getAppId(), BasicUtil.getModelCodeId(ModelCode.MALL_BRAND));
		}else{
			// 查询指定的appId下的分类
			category.setCategoryAppId(BasicUtil.getAppId());
			category.setCategoryModelId(BasicUtil.getModelCodeId(ModelCode.MALL_BRAND));
			list = categoryBiz.query(category);
		}
		String jsonStr = JSON.toJSONString(list);
		this.outJson(response, jsonStr);
	}
}

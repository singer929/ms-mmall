package net.mingsoft.mall.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mingsoft.basic.biz.ICategoryBiz;
import com.mingsoft.basic.entity.CategoryEntity;
import com.mingsoft.util.StringUtil;

import net.mingsoft.basic.util.BasicUtil;
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
@Controller
@RequestMapping("/${managerPath}/mall/brand")
public class BrandAction extends BaseAction {
	/**
	 * 业务层的注入
	 */
	@Resource(name="categoryBiz")
	private ICategoryBiz categoryBiz;

	/**
	 * 加载添加栏目页面
	 * 
	 * @param request
	 *            请求对象
	 * @return 添加栏目页面
	 */
	@RequestMapping("/add")
	public String add(@ModelAttribute CategoryEntity category, HttpServletRequest request) {
		CategoryEntity pc = new CategoryEntity();
		pc.setCategoryModelId(BasicUtil.getModelCodeId(ModelCode.MALL_CATEGORY));
		pc.setCategoryAppId(BasicUtil.getAppId());
		List productCategorys = categoryBiz.query(pc);
		request.setAttribute("productCategorys", JSONArray.toJSONString(productCategorys));
		request.setAttribute("category", category);
		request.setAttribute("appId", BasicUtil.getAppId());
		return view("/mall/brand/brand_form");
	}

	/**
	 * 验证栏目实体是否合法
	 * 
	 * @param category
	 * @param response
	 * @return true：实体信息合法，false：实体信息不合法
	 */
	private boolean checkForm(CategoryEntity category, HttpServletResponse response) {

		// 栏目标题空值验证
		if (StringUtil.isBlank(category.getCategoryTitle())) {
			this.outJson(response, ModelCode.MALL_BRAND, false,
					getResString("err.empty", this.getResString("categoryTitle")));
			return false;
		}
		// 栏目标题长度验证
		if (!StringUtil.checkLength(category.getCategoryTitle(), 1, 31)) {
			this.outJson(response, ModelCode.MALL_BRAND, false,
					getResString("err.length", this.getResString("categoryTitle"), "1", "30"));
			return false;
		}

		return true;
	}

	/**
	 * 根据栏目id删除栏目
	 * 
	 * @param categoryId
	 *            栏目id
	 * @param response
	 *            响应对象
	 * @param request
	 *            请求对象
	 */
	@RequestMapping("/delete")
	public void delete(HttpServletResponse response, HttpServletRequest request) {
		int[] categoryId = BasicUtil.getInts("categoryId");
		categoryBiz.delete(categoryId);
		this.outJson(response, true);
	}

	/**
	 * 加载栏目更新页面
	 * 
	 * @param categoryId
	 *            栏目id
	 * @param request
	 *            请求对象
	 * @return 栏目更新页面地址
	 */
	@RequestMapping("/edit")
	public String edit(@ModelAttribute CategoryEntity category, HttpServletRequest request) {

		CategoryEntity _category = (CategoryEntity) categoryBiz.getEntity(category.getCategoryId());
		request.setAttribute("category", _category);
		
		CategoryEntity pc = new CategoryEntity();
		pc.setCategoryModelId(BasicUtil.getModelCodeId(ModelCode.MALL_CATEGORY));
		pc.setCategoryAppId(BasicUtil.getAppId());
		List productCategorys = categoryBiz.query(pc);
		request.setAttribute("productCategorys", JSONArray.toJSONString(productCategorys));
		
		request.setAttribute("appId", BasicUtil.getAppId());
		return view("/mall/brand/brand_form");
	}

	/**
	 * 栏目首页面列表显示
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 * @return 栏目列表页面地址
	 */
	@RequestMapping("/list")
	public String list(@ModelAttribute CategoryEntity category, HttpServletRequest request,
			HttpServletResponse response) {
		// 查询指定的appId下的分类
		category.setCategoryAppId(BasicUtil.getAppId());
		category.setCategoryModelId(BasicUtil.getModelCodeId(ModelCode.MALL_BRAND));
		List list = categoryBiz.query(category);
		request.setAttribute("brandList", list);
		return view("/mall/brand/brand_list");
	}
	
	

	/**
	 * 添加栏目
	 * 
	 * @param category
	 *            栏目对象
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 */
	@RequestMapping("/save")
	public void save(@ModelAttribute CategoryEntity category, HttpServletRequest request,
			HttpServletResponse response) {

		if (!checkForm(category, response)) {
			return;
		}
		category.setCategoryDateTime(new Timestamp(System.currentTimeMillis()));
		category.setCategoryAppId(this.getAppId(request));
		category.setCategoryModelId(this.getModelCodeId(request));
		categoryBiz.saveCategoryEntity(category);
		this.outJson(response, ModelCode.MALL_BRAND, true, null, String.valueOf(category.getCategoryId()));
	}

	/**
	 * 栏目更新
	 * 
	 * @param category
	 *            栏目实体
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 */
	@RequestMapping("/update")
	public void update(@ModelAttribute CategoryEntity category, HttpServletRequest request,
			HttpServletResponse response) {
		if (!checkForm(category, response)) {
			return;
		}
		categoryBiz.updateCategoryEntity(category);
		this.outJson(response, ModelCode.MALL_BRAND, true, null, JSONArray.toJSONString(category.getCategoryId()));

	}

}

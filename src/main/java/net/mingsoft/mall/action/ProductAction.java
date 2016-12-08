package net.mingsoft.mall.action;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.base.entity.ListJson;
import com.mingsoft.basic.biz.ICategoryBiz;
import com.mingsoft.basic.biz.IColumnBiz;
import com.mingsoft.basic.constant.e.CookieConstEnum;
import com.mingsoft.basic.entity.BasicCategoryEntity;
import com.mingsoft.basic.entity.CategoryEntity;
import com.mingsoft.basic.entity.ColumnEntity;
import com.mingsoft.mdiy.biz.IContentModelBiz;
import com.mingsoft.mdiy.biz.IContentModelFieldBiz;
import com.mingsoft.mdiy.entity.ContentModelEntity;
import com.mingsoft.mdiy.entity.ContentModelFieldEntity;
import com.mingsoft.parser.IParserRegexConstant;
import com.mingsoft.util.JsonUtil;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.mall.biz.IProductBiz;
import net.mingsoft.mall.biz.IProductSpecificationsBiz;
import net.mingsoft.mall.biz.IProductSpecificationsInventoryBiz;
import net.mingsoft.mall.biz.ISpecificationsBiz;
import net.mingsoft.mall.constant.ModelCode;
import net.mingsoft.mall.constant.e.ProductEnum;
import net.mingsoft.mall.entity.ProductEntity;
import net.mingsoft.mall.entity.SpecificationsEntity;

/**
 * 
 * 产品控制层，继承BasicAction
 * 
 * @author 史爱华
 * @version 版本号：100-000-000<br/>
 *          创建日期：2014-11-22<br/>
 *          历史修订：<br/>
 */
@Controller
@RequestMapping("/${managerPath}/mall/product")
public class ProductAction extends BaseAction {

	/**
	 * 注入产品业务层
	 */
	@Autowired
	private IProductBiz productBiz;

	/**
	 * 注入栏目业务层
	 */
	@Autowired
	private IColumnBiz columnBiz;

	/**
	 * 注入自定义字段业务层
	 */
	@Autowired
	private IContentModelFieldBiz fieldBiz;

	/**
	 * 注入内容模型业务层
	 */
	@Autowired
	private IContentModelBiz contentModelBiz;

	/**
	 * 注入规格业务层
	 */
	@Autowired
	private ISpecificationsBiz specificationsBiz;

	/**
	 * 注入规格商品关联业务层
	 */
	@Autowired
	private IProductSpecificationsBiz productSpecificationsBiz;

	/**
	 * 注入产品库存信息业务层
	 */
	@Autowired
	private IProductSpecificationsInventoryBiz productSpecificationsInventoryBiz;

	/**
	 * 判断是否为checkbox类型
	 */
	private static final int CHECKBOX = 11;

	/**
	 * 商品属性分类的业务层
	 */
	@Autowired
	private ICategoryBiz categoryBiz;

	/**
	 * 加载页面显示所有文章信息
	 * 
	 * @param request
	 * @return 返回文章页面显示地址
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request, ModelMap mode, HttpServletResponse response) {
		// 获取站点id
		int appId = getManagerBySession(request).getBasicId();
		List<ColumnEntity> list = columnBiz.queryAll(appId, this.getModelCodeId(request, ModelCode.MALL_CATEGORY));
		JSONObject ja = new JSONObject();
		request.setAttribute("listColumn", ja.toJSON(list).toString());
		// 返回路径
		return view("/mall/product/index");
	}

	/**
	 * 实现商品分页查询的方法
	 * 
	 * @param request
	 *            请求对象
	 * @param mode:返回数据到页面的对象
	 * @param response：响应对象
	 * @return
	 */
	@RequestMapping("/list")
	public String list(@ModelAttribute ProductEntity product, HttpServletRequest request, ModelMap model,
			HttpServletResponse response) {
		// 获取modelId
		int appId = this.getAppId(request);
		int modelId = this.getModelCodeId(request, net.mingsoft.mall.constant.ModelCode.MALL_CATEGORY);
		// 查询当前分类的所有子分类
		int[] childColumnId = this.categoryBiz.queryChildrenCategoryIds(product.getBasicCategoryId(), appId, modelId);
		/// 查询的总数
		// int recordCount = productBiz.getCountByColumnId(appId, childColumnId,
		/// product.getProductShelf(), null, null);

		// 当前页面
		BasicUtil.startPage();
		List<ProductEntity> listProduct = this.productBiz.queryList(appId, childColumnId, null, true,
				product.getProductShelf(), null, null);
		BasicUtil.endPage(listProduct);

		// 压入返回的url地址
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("product", product);
		this.removeUrlParams(request, new String[] { "basicID" });

		return view("/mall/product/product_list");
	}

	/**
	 * 
	 * 跳转到产品保存页面
	 * 
	 * @return 站点保存页面
	 */
	@RequestMapping("/add")
	public String add(@ModelAttribute ProductEntity product, HttpServletRequest request, ModelMap model) {
		// 获取当前app下的栏目列表信息
		product.setProductShelf(ProductEnum.ON_SHELF);
		int modelId = this.getModelCodeId(request, ModelCode.MALL_CATEGORY);
		List<ColumnEntity> columnList = columnBiz.queryAll(this.getAppId(request), modelId);
		List<SpecificationsEntity> specificationsList = this.specificationsBiz.queryPageByAppId(this.getAppId(request),null);
		
		List brands = categoryBiz.query(new CategoryEntity(BasicUtil.getAppId(),BasicUtil.getModelCodeId(ModelCode.MALL_BRAND)));
		
		model.addAttribute("brands", JSONArray.toJSONString(brands));
		model.addAttribute("appId",BasicUtil.getAppId());
		model.addAttribute("columnList", JSONArray.toJSONString(columnList));
		model.addAttribute("specificationsJson",JSONObject.toJSONString(new ListJson(specificationsList.size(), specificationsList)));
		model.addAttribute("categoryId", request.getParameter("categoryId"));
		model.addAttribute("categoryTitle", request.getParameter("categoryTitle"));
		model.addAttribute("product", product);
		return view("/mall/product/product_form");
	}

	/**
	 * 对产品实体实现保存
	 * 
	 * @param product：要保存的产品实体对象
	 * @param request：请求对象
	 * @param response：响应对象
	 */
	@ResponseBody
	@RequestMapping("/save")
	public void save(@ModelAttribute ProductEntity product, HttpServletRequest request, HttpServletResponse response) {
		// 判断提交数据是否符合规范
		if (!checkForm(product, response)) {
			return;
		}
		// 获取appId
		int appId = getManagerBySession(request).getBasicId();
		// 获取产品所属的栏目实体
		ColumnEntity column = (ColumnEntity) columnBiz.getEntity(product.getBasicCategoryId());
		// 判断商品所属栏目是否存在
		if (column == null) {
			this.LOG.debug("保存商品时,选择的栏目不存在");
			this.outJson(response, ModelCode.MALL_PRODUCT, false);
			return;
		}

		// 设置该产品的appId
		product.setBasicAppId(appId);
		product.setProductAppId(appId);
		product.setColumn(column);
		product.setProductShelf(ProductEnum.ON_SHELF);
		// 设置产品的发布时间
		product.setBasicDateTime(new Timestamp(System.currentTimeMillis()));
		product.setBasicUpdateTime(new Timestamp(System.currentTimeMillis()));
		// 产品模块ID
		product.setBasicModelId(this.getModelCodeId(request, ModelCode.MALL_PRODUCT));
		productBiz.saveBasic(product);
		// 获取当前新增商品ID
		int productId = product.getBasicId();
		// 更新访问商品详情链接地址(静态页面地址)
		product.setProductLinkUrl(column.getColumnPath() + File.separator + productId + IParserRegexConstant.HTML_SUFFIX);
		productBiz.updateBasic(product);
		// 添加商品规格
		String specificationsJson = request.getParameter("specificationsJson");
		if (!StringUtil.isBlank(specificationsJson)) {
			this.productSpecificationsBiz.saveProductSpecificationsEntity(specificationsJson, product.getBasicId());
		}

		// 判断该栏目是否存在其他内容模型
		if (column.getColumnContentModelId() != 0) {
			// 保存所有的字段信息
			List<BaseEntity> listField = fieldBiz.queryListByCmid(column.getColumnContentModelId());
			// 获取内容模型实体
			ContentModelEntity contentModel = (ContentModelEntity) contentModelBiz
					.getEntity(column.getColumnContentModelId());
			// 判断内容模型是否存在
			if (contentModel == null) {
				this.outJson(response, ModelCode.MALL_PRODUCT, false);
				return;
			}
			// 保存新增字段的信息
			Map<String, Object> param = this.checkField(listField, request, productId);
			LOG.debug("商品保存自定义模型编号:" + product.getBasicId());
			// 向新增内容模型表中插入数据
			fieldBiz.insertBySQL(contentModel.getCmTableName(), param);
		}

		this.outJson(response, ModelCode.MALL_PRODUCT, true, String.valueOf(product.getBasicId()));
	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @param request
	 * @param model
	 * @param basicId
	 * @return 修改
	 */
	@RequestMapping("/edit")
	public String edit(@ModelAttribute ProductEntity product, HttpServletRequest request, ModelMap model) {
		int appId = this.getAppId(request);
		// 根据商品id查找产品实体
		ProductEntity _product = (ProductEntity) productBiz.getEntity(product.getBasicId());
		// 获取当前app下的栏目列表信息
		int modelId = this.getModelCodeId(request, ModelCode.MALL_CATEGORY);
		List<ColumnEntity> columnList = columnBiz.queryAll(appId, modelId);
		model.addAttribute("columnList", JSONArray.toJSONString(columnList));
		model.addAttribute("product", _product);
		model.addAttribute("appId", BasicUtil.getAppId());
		return view("/mall/product/product_form");
	}

	/**
	 * 验证用户输入商品信息的合法性
	 * 
	 * @param product
	 *            商品实体
	 */
	public boolean checkForm(ProductEntity product, HttpServletResponse response) {
		// 判断产品的标题是否介于1-300之间
		if (!StringUtil.checkLength(product.getBasicTitle(), 1, 300)) {
			this.outJson(response, ModelCode.MALL_PRODUCT, false,
					getResString("err.length", this.getResString("basicTitle"), "1", "300"));
			return false;
		}
		return true;
	}

	/**
	 * 根据商品id删除商品
	 * 
	 * @param basicId
	 *            ：商品id
	 * @param request
	 */
	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String[] ids = request.getParameterValues("ids");
		if (ids.length == 0 || ids == null || !StringUtil.isIntegers(ids)) {
			this.outJson(response, ModelCode.MALL_PRODUCT, false, "", this.redirectBack(request, false));
			return;
		}

		int[] _ids = StringUtil.stringsToInts(ids);
		productBiz.deleteBasic(_ids);
		this.outJson(response, ModelCode.MALL_PRODUCT, true);
	}

	/**
	 * 更新商品信息
	 * 
	 * @param product
	 *            商品实体信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/update")
	public void update(@ModelAttribute ProductEntity product, HttpServletRequest request,
			HttpServletResponse response) {
		// 判断提交数据是否符合规范
		if (!checkForm(product, response)) {
			return;
		}
		// 获取appid
		int appId = getManagerBySession(request).getBasicId();
		// //查询网站实体信息
		// AppEntity website = (AppEntity) appBiz.getEntity(appId);
		// 获取更新前的商品实体
		ProductEntity oldProduct = (ProductEntity) productBiz.getEntity(product.getBasicId());
		// 获取新的商品所属的栏目实体
		ColumnEntity column = (ColumnEntity) columnBiz.getEntity(product.getBasicCategoryId());
		if (oldProduct != null) {
			// 获取更改前的文章所属栏目实体
			ColumnEntity oldColumn = (ColumnEntity) columnBiz.getEntity(oldProduct.getBasicCategoryId());
			// 通过表单类型id判断是否更改了表单类型,如果更改则先删除记录
			if (oldColumn.getColumnContentModelId() != column.getColumnContentModelId()) {
				// 获取旧的内容模型id
				ContentModelEntity contentModel = (ContentModelEntity) contentModelBiz
						.getEntity(oldColumn.getColumnContentModelId());
				// 删除旧的内容模型中保存的值
				Map wheres = new HashMap();
				wheres.put("basicId", product.getBasicId());
				if (contentModel != null) {
					fieldBiz.deleteBySQL(contentModel.getCmTableName(), wheres);
				}
				// 判断栏目是否存在新增字段
				if (column.getColumnContentModelId() != 0) {
					// 保存所有的字段信息
					List<BaseEntity> listField = fieldBiz.queryListByCmid(column.getColumnContentModelId());
					// 获取新的内容模型
					ContentModelEntity newContentModel = (ContentModelEntity) contentModelBiz
							.getEntity(column.getColumnContentModelId());
					if (newContentModel != null) {
						Map param = this.checkField(listField, request, product.getBasicId());
						fieldBiz.insertBySQL(newContentModel.getCmTableName(), param);
					}
				}
			}
			// 添加商品所属的站点id
			product.setProductAppId(appId);
			// 添加商品更新时间
			product.setBasicUpdateTime(new Timestamp(System.currentTimeMillis()));
			product.setColumn(column);
			// 设置商品的链接地址
			product.setProductLinkUrl(
					column.getColumnPath() + File.separator + product.getBasicId() + IParserRegexConstant.HTML_SUFFIX);
			// 更新文章
			productBiz.updateBasic(product);
			String productType = request.getParameter("productTypeJson");
			if (!StringUtil.isBlank(productType)) {
				// 将JSON字符串转换为数组
				List<BasicCategoryEntity> basicCategoryList = JSONArray.parseArray(productType,
						BasicCategoryEntity.class);
				productBiz.updateProduct(product, basicCategoryList);
			} else {
				// 更新商品信息
				productBiz.updateBasic(product);
			}
			// 更新商品规格
			// 添加商品规格
			String specificationsJson = request.getParameter("specificationsJson");
			if (!StringUtil.isBlank(specificationsJson)) {
				this.productSpecificationsBiz.updateEntityByProduct(specificationsJson, product.getBasicId());
			}

			// 判断该文章所属栏目是否存在新的内容模型
			if (column.getColumnContentModelId() != 0) {
				// 保存所有的字段信息
				List<BaseEntity> listField = fieldBiz.queryListByCmid(column.getColumnContentModelId());
				// // update中的where条件
				Map where = new HashMap();
				// 压入默认的basicId字段
				where.put("basicId", product.getBasicId());
				// 遍历字段的信息
				Map param = this.checkField(listField, request, product.getBasicId());
				ContentModelEntity contentModel = (ContentModelEntity) contentModelBiz
						.getEntity(column.getColumnContentModelId());
				if (contentModel != null) {
					// 遍历所有的字段实体,得到字段名列表信息
					List<String> listFieldName = new ArrayList<String>();
					listFieldName.add("basicId");
					// 查询新增字段的信息
					List fieldLists = fieldBiz.queryBySQL(contentModel.getCmTableName(), listFieldName, where);
					// 判断新增字段表中是否存在该商品信息，不存在则保存，否则更新
					if (fieldLists != null && fieldLists.size() > 0) {
						fieldBiz.updateBySQL(contentModel.getCmTableName(), param, where);
					} else {
						fieldBiz.insertBySQL(contentModel.getCmTableName(), param);
					}

				}
			}
		}
		int pageNo = 1;
		// 获取cookie
		String cookie = this.getCookie(request, CookieConstEnum.PAGENO_COOKIE);
		// 判断cookies是否为空
		if (!StringUtil.isBlank(cookie) && Integer.valueOf(cookie) > 0) {
			pageNo = Integer.valueOf(cookie);
		}
		this.outJson(response, ModelCode.MALL_PRODUCT, true, String.valueOf(pageNo));
	}

	/**
	 * 遍历出所有文章新增字段的信息
	 * 
	 * @param listField
	 *            字段列表
	 * @param request
	 * @param basicId
	 *            文章id
	 * @return 字段信息
	 */
	private Map<String, Object> checkField(List<BaseEntity> listField, HttpServletRequest request, int basicId) {
		Map<String, Object> mapParams = new HashMap<String, Object>();
		// 压入默认的basicId字段
		mapParams.put("basicId", basicId);
		LOG.debug("保存规格编号:" + basicId);
		// 遍历字段名
		for (int i = 0; i < listField.size(); i++) {
			ContentModelFieldEntity field = (ContentModelFieldEntity) listField.get(i);
			// 判断字段类型是否为checkbox类型
			if (field.getFieldType() == CHECKBOX) {
				String langtyp[] = request.getParameterValues(field.getFieldFieldName());
				if (langtyp != null) {
					StringBuffer sb = new StringBuffer();
					for (int j = 0; j < langtyp.length; j++) {
						sb.append(langtyp[j] + ",");
					}
					mapParams.put(field.getFieldFieldName(), sb.toString());
				} else {
					mapParams.put(field.getFieldFieldName(), langtyp);
				}
			} else {
				if (StringUtil.isBlank(request.getParameter(field.getFieldFieldName()))) {
					mapParams.put(field.getFieldFieldName(), null);
				} else {
					mapParams.put(field.getFieldFieldName(), request.getParameter(field.getFieldFieldName()));
				}
			}
		}
		return mapParams;
	}

}

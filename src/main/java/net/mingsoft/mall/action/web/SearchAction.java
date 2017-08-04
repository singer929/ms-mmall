package net.mingsoft.mall.action.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.basic.biz.IAppBiz;
import com.mingsoft.basic.biz.ICategoryBiz;
import com.mingsoft.basic.biz.IColumnBiz;
import com.mingsoft.basic.entity.AppEntity;
import com.mingsoft.basic.entity.ColumnEntity;
import com.mingsoft.mdiy.biz.IContentModelBiz;
import com.mingsoft.mdiy.biz.IContentModelFieldBiz;
import com.mingsoft.mdiy.biz.ISearchBiz;
import com.mingsoft.mdiy.entity.ContentModelEntity;
import com.mingsoft.mdiy.entity.ContentModelFieldEntity;
import com.mingsoft.mdiy.entity.SearchEntity;
import com.mingsoft.mdiy.parser.ListParser;
import com.mingsoft.parser.IParserRegexConstant;
import com.mingsoft.util.FileUtil;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.base.elasticsearch.bean.SearchBean;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.basic.util.ElasticsearchUtil;
import net.mingsoft.mall.action.BaseAction;
import net.mingsoft.mall.biz.IProductBiz;
import net.mingsoft.mall.entity.ProductEntity;
import net.mingsoft.mall.parser.MallParser;
import net.mingsoft.mall.search.IProductSearch;

/**
 * 
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
 * @author killfen
 * 
 *         <p>
 *         Comments: 自定义搜索，根据搜索结果生成html页面
 *         </p>
 * 
 *         <p>
 *         Create Date:2015-4-21
 *         </p>
 * 
 *         <p>
 *         Modification history:
 *         </p>
 */
@Controller(value = "webMallSearchAction")
@RequestMapping("/mmall")
public class SearchAction extends BaseAction {

	private final static String PRODUCT_FIELD_PRICE = "product_price";

	private final static String PRODUCT_FIELD_SPECIFICATION_PRICE = "psd_price";

	/**
	 * 注入商品业务层
	 */
	@Autowired
	private IProductBiz productBiz;

	/**
	 * 内容模型业务层
	 */
	@Autowired
	private IContentModelBiz contentModelBiz;

	/**
	 * 内容字段业务层
	 */
	@Autowired
	private IContentModelFieldBiz fieldBiz;

	/**
	 * 注入站点业务层
	 */
	@Autowired
	private IAppBiz appBiz;

	/**
	 * 注入搜索业务层
	 */
	@Autowired
	private ISearchBiz searchBiz;

	/**
	 * 注入栏目业务层
	 */
	@Autowired
	private IColumnBiz columnBiz;

	/**
	 * 商城解析器
	 */
	@Autowired
	private MallParser mallParser;

	@Autowired
	private ICategoryBiz categoryBiz;

	@Autowired
	private IProductSearch productSearch;

	/**
	 * 自定义搜索接口
	 * 
	 * @param request
	 * @param searchId
	 * @param response
	 */
	@RequestMapping(value = "/{searchId}/search")
	@ResponseBody
	public void search(HttpServletRequest request, @PathVariable int searchId, HttpServletResponse response) {
		// 获取产品基本字段
		Map<String, String> productField = getMapByProperties("net/mingsoft/mall/resources/product_field");
		Map<String, String[]> field = new HashMap<String, String[]>();
		// 获取app实体
		AppEntity app = BasicUtil.getApp();
		// 获取模版名称
		String tmpName = app.getAppStyle();
		// 获取系统模版存放物理路径
		String tmpPath = getRealPath(request, IParserRegexConstant.REGEX_SAVE_TEMPLATE);
		// 排序参数格式 字段名称-方式
		String sort = request.getParameter("sort");
		Map<String, String> sortMap = null;
		if (!StringUtil.isBlank(sort) && sort.indexOf("-") > 0) {
			sortMap = new HashMap();
			String[] tmp = sort.split("-");
			sortMap.put("order", tmp[0]);
			sortMap.put("by", tmp[1]);
		}
		field = request.getParameterMap(); // 读取请求字段
		String pageNo = request.getParameter("pageNo"); // 分页
		int no = 1;
		if (StringUtil.isInteger(pageNo)) {
			no = Integer.parseInt(pageNo);
		}
		// 获取栏目ID
		String basicCategoryId = request.getParameter("categoryId");
		Integer[] categoryIds = null;
		if (basicCategoryId != null) {
			if (StringUtil.isIntegers(basicCategoryId.split(","))) {
				categoryIds = StringUtil.stringsToIntegers(basicCategoryId.split(","));
				basicCategoryId = categoryIds[0] + "";
			}
		}
		ColumnEntity column = null; // 当前栏目
		ContentModelEntity contentModel = null; // 栏目对应模型
		List fieldList = null; // 栏目对应字段
		// 若栏目ID不为空，则选择了栏目
		if (StringUtil.isInteger(basicCategoryId)) {
			column = (ColumnEntity) columnBiz.getEntity(Integer.valueOf(basicCategoryId));
			// 获取表单类型的id
			if (column != null) {
				// 获取内容模型实体
				contentModel = (ContentModelEntity) contentModelBiz.getEntity(column.getColumnContentModelId());
				if (contentModel != null) {
					// 获取自定义字段
					fieldList = fieldBiz.queryByContentModelId(contentModel.getCmId());
				}
			}
		}
		// 获取对应搜索模型
		SearchEntity search = (SearchEntity) searchBiz.getByIdAndAppId(searchId, app.getAppId());
		// 默认显示的数量为20条
		int size = 1;
		// 判断是否存在搜索模型
		if (search != null) {
			String webSiteTmpPath = "";
			if (isMobileDevice(request) && !StringUtil.isBlank(app.getAppMobileStyle())) {
				// 根据站点id组装站点信息路径 格式：templets／站点ID/模版风格
				webSiteTmpPath = tmpPath + File.separator + app.getAppId() + File.separator + tmpName + File.separator
						+ app.getAppMobileStyle();
			} else {
				webSiteTmpPath = tmpPath + File.separator + app.getAppId() + File.separator + tmpName;
			}
			// 读取模板内容
			String htmlContent = FileUtil.readFile(webSiteTmpPath + File.separator + search.getSearchTemplets());
			// 文章字段集合
			Map<String, Object> productFieldName = new HashMap<String, Object>();
			// 自定义字段集合
			Map<String, String> diyFieldName = new HashMap<String, String>();
			// 分页连接地址
			String pageUrl = app.getAppHostUrl() + File.separator + "mmall" + File.separator + searchId + File.separator
					+ "search.do";
			// 遍历取字段集合
			for (Entry<String, String[]> entry : field.entrySet()) {
				if (entry != null) {
					String value = entry.getValue()[0];
					if (StringUtil.isBlank(value)) {
						continue;
					}
					String key = entry.getKey();
					if (request.getMethod().equals(RequestMethod.GET)) { // 如果是get方法需要将请求地址参数转吗
						value = StringUtil.isoToUTF8(value);
						key = StringUtil.isoToUTF8(value);
					}
					// 若为文章字段，则保存至文章字段集合；否则保存至自定义字段集合
					if (!StringUtil.isBlank(productField.get(key)) && !StringUtil.isBlank(value)) {
						productFieldName.put(key, value);
					} else {
						diyFieldName.put(key, value);
					}
					htmlContent = htmlContent.replaceAll("\\{" + key + "/\\}", value); // 将用户请求的值返回到页面上；
					if (pageUrl.indexOf("?") < 0) {
						pageUrl += "?" + entry.getKey() + "=" + value;
					} else {
						pageUrl += "&" + entry.getKey() + "=" + value;
					}
				}
			}
			// 去除多余的pageNo
			int pageNoIndex = pageUrl.indexOf("&pageNo");
			if (pageNoIndex > 0) {
				pageUrl = pageUrl.substring(0, pageNoIndex);
			}
			// 保存栏目Id
			List ids = null;
			int modelId = this.getModelCodeId(request, net.mingsoft.mall.constant.ModelCode.MALL_CATEGORY);
			if (StringUtil.isInteger(basicCategoryId)) {
				ids = categoryBiz.queryChildrenCategory(Integer.parseInt(basicCategoryId), this.getAppId(request),
						modelId);
				if (categoryIds != null && categoryIds.length > 0) {
					for (Integer id : categoryIds) {
						ids.addAll(categoryBiz.queryChildrenCategory(id, this.getAppId(request), modelId));
					}
				}
			}
			Map whereMap = this.searchMap(productFieldName, diyFieldName, fieldList);
			int count = 0;
			// 读取列表标签中中的
			Map<String, String> property = net.mingsoft.mall.parser.impl.ListParser.listProperty(htmlContent, true);
			// 显示文章的形式flag属性
			String flag = property.get(ListParser.LIST_FLAG);
			// 显示文章的形式noflag属性
			String noFlag = property.get(ListParser.LIST_NOFLAG);
			// 获取符合条件的文章总数
			count = productBiz.getProducntSpecificationSearchCount(contentModel, whereMap, app.getAppId(), ids, flag,
					noFlag);
			List<ProductEntity> procductList = new ArrayList<ProductEntity>();

			// 列表每页显示的数量
			if (StringUtil.string2Int(property.get(ListParser.LIST_SIZE)) > 0) {
				size = StringUtil.string2Int(property.get(ListParser.LIST_SIZE));
			}
			// 页面对象
			PageUtil page = new PageUtil(no, size, count, pageUrl);
			// 判断列表标签中是否存在
			String isPaging = property.get(net.mingsoft.mall.parser.impl.ListParser.LIST_ISPAGING);
			String orderBy = property.get(net.mingsoft.mall.parser.impl.ListParser.LIST_ORDERBY);
			String order = "desc";

			if (!StringUtil.isBlank(property.get(net.mingsoft.mall.parser.impl.ListParser.LIST_ORDER))) {
				order = property.get(net.mingsoft.mall.parser.impl.ListParser.LIST_ORDER);
			}

			// 判断是否存在分页,不存在则分页对象不存在
			if (isPaging == null || !isPaging.equals("true")) {
				page = null;
			}

			procductList = productBiz.queryProducntSpecificationForSearch(contentModel, whereMap, app.getAppId(), ids,
					sortMap, page, orderBy, order.equals("desc") ? true : false, flag, noFlag);
			Map map = new HashMap();
			map.put(MallParser.PRODUCT_SEARCH_LIST_ARTICLE, procductList);
			// 移动端与pc端分离
			if (isMobileDevice(request) && !StringUtil.isBlank(app.getAppMobileStyle())) {
				// 对模板内容进行解析
				htmlContent = mallParser.parse(htmlContent, app, column, procductList, page, map);// generaterFactory.buildSearch(app,
																									// htmlContent,
																									// webSiteTmpPath,
																									// no,
																									// articleList,null,
																									// column,
																									// page,app.getAppMobileStyle());
			} else {
				// 对模板内容进行解析
				htmlContent = mallParser.parse(htmlContent, app, column, procductList, page, map);// generaterFactory.buildSearch(app,
																									// htmlContent,
																									// webSiteTmpPath,
																									// no,
																									// articleList,
																									// null,column,
																									// page);
			}
			this.outString(response, htmlContent);
		} else {
			this.outString(response, this.getResString("err"));
		}
	}

	/**
	 * 动态组织查询where条件 获取查询条件的Map key:字段名 value:List 字段的各种判断值 list[0]:是否为自定义字段
	 * list[1]:是否为整形 list[2]:是否是等值查询 list[3]:字段的值
	 * 
	 * @param articleField
	 *            文章字段
	 * @param diyFieldName
	 *            动态字段
	 * @param fields
	 *            模型对应的字段类型
	 * @return 记录集合
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map<String, List> searchMap(Map<String, Object> articleField, Map<String, String> diyFieldName,
			List fields) {
		Map<String, List> map = new HashMap<String, List>();
		// 遍历文章中的字段
		for (Iterator iter = articleField.keySet().iterator(); iter.hasNext();) {
			String key = iter.next().toString();
			String fieldValue = articleField.get(key).toString();
			List list = new ArrayList();
			List listValue = new ArrayList();
			// 是否为自定义字段
			list.add(false);
			if (key.equals(PRODUCT_FIELD_PRICE) || key.equals(PRODUCT_FIELD_SPECIFICATION_PRICE)) {
				if (articleField.get(key).toString().indexOf("-") > 0) {
					String[] values = fieldValue.toString().split("-");
					// 是否是数字类型，false:是
					list.add(false);
					// 是否是区间比较 false:是
					list.add(false);
					// 字段值1
					listValue.add(values[0]);
					listValue.add(values[1]);
				} else {
					// 是否是数字类型，false:是2
					list.add(true);
					// 是否是区间比较 true:不是3
					list.add(true);
					// 字段值 1
					listValue.add(fieldValue);
				}
			} else {
				// 是否是数字类型，true:不是
				list.add(true);
				// 是否是模糊查询3
				list.add(true);
				// 字段值
				listValue.add(articleField.get(key));
			}
			list.add(listValue);
			map.put(key, list);
		}
		// 遍历字段自定义字段
		for (Iterator iter = diyFieldName.keySet().iterator(); iter.hasNext();) {
			String key = iter.next().toString();
			String fieldValue = diyFieldName.get(key);
			// 获取字段实体
			ContentModelFieldEntity field = this.get(key, fields);
			if (field != null) {
				List list = new ArrayList();
				// 是否为自定义字段0
				list.add(0, true);
				List listValue = new ArrayList();
				// 字段的值
				if (field.getFieldType() == IContentModelFieldBiz.INT
						|| field.getFieldType() == IContentModelFieldBiz.FLOAT) {
					// 判断是否为区间查询
					if (diyFieldName.get(key).toString().indexOf("-") > 0) {
						String[] values = fieldValue.toString().split("-");
						// 是否是数字类型，false:是
						list.add(false);
						// 是否是区间比较 false:是
						list.add(false);
						// 字段值1
						listValue.add(values[0]);
						listValue.add(values[1]);
					} else {
						// 是否是数字类型，false:是2
						list.add(false);
						// 是否是区间比较 true:不是3
						list.add(true);
						// 字段值 1
						listValue.add(fieldValue);
					}
				} else {
					// 是否是数字类型，true:不是2
					list.add(true);
					list.add(false);
					// 字段值 1
					listValue.add(fieldValue);
				}
				list.add(listValue);
				map.put(key, list);
			}
		}
		return map;
	}

	/**
	 * 根据字段名称获取字段类型
	 * 
	 * @param columnName
	 *            字段名称
	 * @return 自定义模型实体
	 */
	private ContentModelFieldEntity get(String columnName, List<ContentModelFieldEntity> fields) {
		if (fields == null) {
			return null;
		}
		for (ContentModelFieldEntity field : fields) {
			if (field.getFieldFieldName().equals(columnName)) {
				return field;
			}
		}
		return null;
	}

	/**
	 * 全文检索
	 * 
	 * @param search
	 *            <i>search参数包含字段信息参考：</i><br/>
	 *            pageNumber 当前页码<br/>
	 *            pageSize 一页显示数量，默认20条<br/>
	 *            orderBy 排序字段，默认id<br/>
	 *            order 排序方式 默认desc|asc<br/>
	 *            keyworkd 关键字<br/>
	 *            brand 品牌<br/>
	 *            category 分类<br/>
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            { "data": [ { "basicHit": 10000, "basicCategoryId": 900,
	 *            "productGood": 0.9, "basicComment": 100, "basicPic":
	 *            "/upload/mall/product/1574///1500458469564.jpg!350x350.jpg",
	 *            "basicTitle": "【鸿星尔克】秋冬儿童新款运动鞋休闲鞋", "id": "9", "productSale":
	 *            99, "productBrand": 1000, "productStock": 100,
	 *            "basicUpdateTime": 1501310573655, "productSpecs":
	 *            "[{\"createBy\":0,\"delFlag\":0,\"img\":\"/upload/mall/product/1574///1500459392916.jpg!60x60.jpg\",\"productId\":15797,\"psId\":33,\"specName\":\"颜色\",\"specValue\":\"粉色\",\"updateBy\":0},{\"createBy\":0,\"delFlag\":0,\"img\":\"/upload/mall/product/1574///1500459395122.jpg!60x60.jpg\",\"productId\":15797,\"psId\":34,\"specName\":\"颜色\",\"specValue\":\"黑色\",\"updateBy\":0},{\"createBy\":0,\"delFlag\":0,\"img\":\"/upload/mall/product/1574///1500459397436.jpg!60x60.jpg\",\"productId\":15797,\"psId\":35,\"specName\":\"颜色\",\"specValue\":\"红色\",\"updateBy\":0}]",
	 *            "productSpecDetails":
	 *            "[{\"code\":\"001\",\"createBy\":0,\"delFlag\":0,\"detailId\":36,\"price\":0.01,\"productId\":15797,\"sale\":0,\"sort\":0,\"specValues\":\"颜色:粉色:/upload/mall/product/1574///1500459392916.jpg!60x60.jpg\",\"stock\":94,\"updateBy\":0},{\"code\":\"002\",\"createBy\":0,\"delFlag\":0,\"detailId\":37,\"price\":0.02,\"productId\":15797,\"sale\":0,\"sort\":0,\"specValues\":\"颜色:黑色:/upload/mall/product/1574///1500459395122.jpg!60x60.jpg\",\"stock\":92,\"updateBy\":0}]",
	 *            "productCostPrice": 509, "productPrice": 293.99,
	 *            "productLinkUrl": "/html/1574/6786/6790/15793.html" } ],
	 *            "page": { "pageSize": 1, "currentPage": 0, "totalPage": 2,
	 *            "totalCount": 2 } }
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/search")
	@ResponseBody
	public void search(SearchBean search, HttpServletRequest request, HttpServletResponse response) {
		// ProductMapping product = new ProductMapping();
		// product.setId("8");
		// product.setBasicTitle(" 烟花烫冬装新品女气质修身毛呢套装 凡翠");
		// product.setBasicPic("/upload/mall/product/1574///1500458469564.jpg!350x350.jpg");
		// product.setBasicComment(100);
		// product.setBasicHit(10000);
		// product.setProductPrice(293.99);
		// product.setProductCostPrice(509);
		// product.setBasicUpdateTime(new Date());
		// product.setProductStock(100);
		// product.setProductSale(99);
		// product.setProductLinkUrl("/html/1574/6786/6790/15793.html");
		// product.setProductSpecDetails("[{\"code\":\"001\",\"createBy\":0,\"delFlag\":0,\"detailId\":36,\"price\":0.01,\"productId\":15797,\"sale\":0,\"sort\":0,\"specValues\":\"颜色:粉色:/upload/mall/product/1574///1500459392916.jpg!60x60.jpg\",\"stock\":94,\"updateBy\":0},{\"code\":\"002\",\"createBy\":0,\"delFlag\":0,\"detailId\":37,\"price\":0.02,\"productId\":15797,\"sale\":0,\"sort\":0,\"specValues\":\"颜色:黑色:/upload/mall/product/1574///1500459395122.jpg!60x60.jpg\",\"stock\":92,\"updateBy\":0}]");
		// product.setProductSpecs("[{\"createBy\":0,\"delFlag\":0,\"img\":\"/upload/mall/product/1574///1500459392916.jpg!60x60.jpg\",\"productId\":15797,\"psId\":33,\"specName\":\"颜色\",\"specValue\":\"粉色\",\"updateBy\":0},{\"createBy\":0,\"delFlag\":0,\"img\":\"/upload/mall/product/1574///1500459395122.jpg!60x60.jpg\",\"productId\":15797,\"psId\":34,\"specName\":\"颜色\",\"specValue\":\"黑色\",\"updateBy\":0},{\"createBy\":0,\"delFlag\":0,\"img\":\"/upload/mall/product/1574///1500459397436.jpg!60x60.jpg\",\"productId\":15797,\"psId\":35,\"specName\":\"颜色\",\"specValue\":\"红色\",\"updateBy\":0}]");
		// product.setProductBrand(1000);
		// product.setBasicCategoryId(900);
		// product.setProductGood(0.9);
		// ElasticsearchUtil.saveOrUpdate(product.getId(), product);
		// search.setOrder("desc");
		int category = BasicUtil.getInt("category"); // 分类编号
		int brand = BasicUtil.getInt("brand"); // 品牌编号
		String price = BasicUtil.getString("price"); // 价格范围
		
		if (StringUtils.isBlank(search.getKeyword()) && category<=0 && brand<=0) { //
			this.outJson(response, false);
			return;
		}

		double[] _price = null;
		if (StringUtil.isDoubles(price.split("-"))) {
			StringUtil.stringsToDoubles(price.split("-"));
		} else {
			price = null;
		}

		String sort = BasicUtil.getString("sort"); // 排序方式
		if (sort.split("-").length == 2) {
			search.setOrderBy(sort.split("-")[0]);
			search.setOrder(sort.split("-")[1]);
		}

		BoolQueryBuilder bqb = QueryBuilders.boolQuery();
		if(!StringUtil.isBlank(search.getKeyword())) {
			bqb.must(QueryBuilders.matchQuery("basicTitle", search.getKeyword()));
		}
		
		if(category > 0) {
			bqb.should(QueryBuilders.matchQuery("basicCategoryId", category));
		}
		
		if(brand > 0) {
			bqb.should(QueryBuilders.matchQuery("productBrand", brand));
		}
		if(price!=null) {
			bqb.filter(QueryBuilders.rangeQuery("price").gte(_price[0]).lte(_price[1]));
		}

		Pageable pageable = new PageRequest(search.getPageNumber() - 1, search.getPageSize());
		SearchQuery sq = new NativeSearchQueryBuilder().withPageable(pageable)
				.withSort(SortBuilders.fieldSort(search.getOrderBy())
						.order(search.getOrder().equalsIgnoreCase("asc") ? SortOrder.ASC : SortOrder.DESC))
				.withQuery(bqb).build();
		Page p = productSearch.search(sq);
		ElasticsearchUtil.Pager pager = new ElasticsearchUtil.Pager();
		pager.setCurrentPage(p.getNumber());
		pager.setPageSize(p.getSize());
		pager.setTotalCount(p.getTotalElements());
		pager.setTotalPage(p.getTotalPages());
		Map map = new HashMap();
		map.put("data", p.getContent());
		map.put("page", pager);

		this.outJson(response, JSONObject.toJSON(map));
	}

}

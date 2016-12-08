/**
 * 
 */
package net.mingsoft.mall.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.biz.IColumnBiz;
import com.mingsoft.basic.biz.IModelBiz;
import com.mingsoft.basic.entity.AppEntity;
import com.mingsoft.basic.entity.ColumnEntity;
import com.mingsoft.basic.parser.IGeneralParser;
import com.mingsoft.cms.parser.CmsParser;
import com.mingsoft.cms.parser.impl.ArticleTypeIdParser;
import com.mingsoft.cms.parser.impl.ArticleTypeLinkParser;
import com.mingsoft.cms.parser.impl.ArticleTypeTitleParser;
import com.mingsoft.cms.parser.impl.ChannelParser;
import com.mingsoft.mdiy.biz.IContentModelBiz;
import com.mingsoft.mdiy.biz.IContentModelFieldBiz;
import com.mingsoft.mdiy.entity.ContentModelEntity;
import com.mingsoft.mdiy.entity.ContentModelFieldEntity;
import com.mingsoft.mdiy.parser.TaglibParser;
import com.mingsoft.parser.IParserRegexConstant;
import com.mingsoft.parser.PageUtilHtml;
import com.mingsoft.parser.impl.general.PageNumParser;
import com.mingsoft.parser.impl.general.PageParser;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.mall.biz.IProductBiz;
import net.mingsoft.mall.constant.e.ProductEnum;
import net.mingsoft.mall.entity.ProductEntity;
import net.mingsoft.mall.parser.impl.ListParser;
import net.mingsoft.mall.parser.impl.ProductCodeParser;
import net.mingsoft.mall.parser.impl.ProductCollectParser;
import net.mingsoft.mall.parser.impl.ProductCommentParser;
import net.mingsoft.mall.parser.impl.ProductContentParser;
import net.mingsoft.mall.parser.impl.ProductCostPriceParser;
import net.mingsoft.mall.parser.impl.ProductIdParser;
import net.mingsoft.mall.parser.impl.ProductLinkParser;
import net.mingsoft.mall.parser.impl.ProductLitpicParser;
import net.mingsoft.mall.parser.impl.ProductPriceParser;
import net.mingsoft.mall.parser.impl.ProductSaleParser;
import net.mingsoft.mall.parser.impl.ProductSpecificationId;
import net.mingsoft.mall.parser.impl.ProductStockParser;
import net.mingsoft.mall.parser.impl.ProductTitleParser;
import net.mingsoft.mall.parser.impl.ProductTypeIdParser;
import net.mingsoft.mall.parser.impl.ProductTypeLinkParser;
import net.mingsoft.mall.parser.impl.ProductTypeTitleParser;

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
 * @author killfen
 * 
 *         <p>
 *         Comments:商品解析器
 *         </p>
 * 
 *         <p>
 *         Create Date:2015-4-19
 *         </p>
 * 
 *         <p>
 *         Modification history:
 *         </p>
 */
@Component
@Scope("prototype")
public class MallParser extends IGeneralParser {

	/**
	 * 商品业务层
	 */
	@Autowired
	private IProductBiz productBiz;

	/**
	 * 栏目业务层
	 */
	@Autowired
	private IColumnBiz columnBiz;

	/**
	 * 新增字段业务层
	 */
	@Autowired
	private IContentModelFieldBiz fieldBiz;

	/**
	 * 新增模块业务层
	 */
	@Autowired
	private IModelBiz modelBiz;

	/**
	 * 内容模型业务层
	 */
	@Autowired
	private IContentModelBiz contentBiz;

	/**
	 * 文章解析器
	 */
	@Autowired
	private CmsParser cmsParser;

	/**
	 * 当前页码
	 */
	private int curPageNo;

	/**
	 * 列表连接地址
	 */
	private String listLinkPath;

	/**
	 * 栏目实体
	 */
	private ColumnEntity column;

	/**
	 * 当前栏目编号，根据栏目生成时候用到
	 */
	private int curColumnId;

	/**
	 * 当前商品实体
	 */
	private ProductEntity product;

	/**
	 * 搜索的商品列表
	 */
	private List productSearchList = null;

	/**
	 * 搜索列表的静态变量名
	 */
	public static final String PRODUCT_SEARCH_LIST_ARTICLE = "productSearchList";

	/**
	 * 模块id
	 */
	private int modelId;

	/**
	 * 当前栏目id,当前页码,列表链接地址
	 */
	public static final String CUR_COLUMNID = "curColumnId", CUR_PAGE_NO = "curPageNo", LIST_LINK_PATH = "listLinkPath";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mingsoft.parser.IGeneralParser#parse(java.lang.Object[])
	 */
	@Override
	public String parse(String html, Object... obj) {
		super.htmlContent = html;
		htmlContent = cmsParser.parse(html, obj); // 解析文章
		init(obj);
		htmlContent = parseGeneral();
		htmlContent = parseSearchList();
		htmlContent = parseList();
		htmlContent = parseProduct();
		htmlContent = parseProductList();
		htmlContent = parsePage();
		htmlContent = parseChannel();
		return super.htmlContent;
	}

	/**
	 * 初始化解析器中的参数
	 * 
	 * @param obj
	 *            参数集合对象
	 */
	public void init(Object... obj) {
		super.init(obj);
		mobilePath = "";
		product = null;
		column = null;
		curColumnId = 0;
		productSearchList = null;

		modelId = this.modelBiz.getEntityByModelCode(net.mingsoft.mall.constant.ModelCode.MALL_CATEGORY).getModelId();
		for (Object o : obj) {
			if (o != null) {
				if (o instanceof Map) {
					Map temp = (Map) o;
					// 获取当前栏目id
					if (StringUtil.isInteger(temp.get(CUR_COLUMNID))) {
						curColumnId = Integer.parseInt(temp.get(CUR_COLUMNID) + "");
					}
					// 获取当前页码
					if (StringUtil.isInteger(temp.get(CUR_PAGE_NO))) {
						curPageNo = Integer.parseInt(temp.get(CUR_PAGE_NO) + "");
					}
					if (!StringUtil.isBlank(temp.get(LIST_LINK_PATH))) {
						listLinkPath = temp.get(LIST_LINK_PATH) + "";
					}
					if (!StringUtil.isBlank(temp.get(MOBILE))) {
						mobilePath = temp.get(MOBILE) + "";
					}
					if (temp.get(PRODUCT_SEARCH_LIST_ARTICLE) instanceof java.util.List) {
						// 搜索时候的文章列表数据
						productSearchList = (List) temp.get(PRODUCT_SEARCH_LIST_ARTICLE);
					}
				}
				if (o instanceof ColumnEntity) {
					column = (ColumnEntity) o;
					// 直接影响ms:arclist的数据，根据栏目生成的时候需要必须要进行此操作
					this.curColumnId = column.getCategoryId();
				}
				if (o instanceof ProductEntity) { // 显示商品内容的时候必须存在
					product = (ProductEntity) o;
				}
				if (o instanceof PageUtil) { // 显示商品搜索的时候必须存在
					page = (PageUtil) o;
				}
			}
		}
	}

	/**
	 * 对商品详细内容的解析
	 */
	public String parseProduct() {
		if (product == null) {
			return htmlContent;
		}
		String productCode = product.getProductCode();// 商品编吗
		String productPrice = product.getProductPrice() + "";// 商品价格
		String productSale = product.getProductSale() + "";// 商品销售
		String productComment = product.getBasicComment() + "";// 商品评论
		String productCollect = product.getBasicCollect() + "";// 商品收藏
		String productStock = product.getProductStock() + "";// 商品库存
		String productTitle = product.getBasicTitle();
		// 替换商品介绍 {ms:field.content/}
		htmlContent = new ProductContentParser(htmlContent, product.getProductContent()).parse();
		// 替换商品的编码 {ms:field.code/}
		htmlContent = new ProductCodeParser(htmlContent, productCode).parse();
		// 替换商品的原价 {ms:field.costprice/}
		htmlContent = new ProductCostPriceParser(htmlContent, product.getProductCostPrice() + "").parse();
		// 替换商品的id {ms:field.id/}
		htmlContent = new ProductCodeParser(htmlContent, product.getProductId() + "").parse();
		// 替换商品的id {ms:field.link/}
		htmlContent = new ProductLinkParser(htmlContent, product.getProductLinkUrl()).parse();
		// 替换商品标题标签 {ms:field.title/}
		htmlContent = new ProductTitleParser(htmlContent, productTitle).parse();
		// 替换商品价格{ms:field.price/}
		htmlContent = new ProductPriceParser(htmlContent, productPrice).parse();
		// 替换商品销量{ms:field.sale/}
		htmlContent = new ProductSaleParser(htmlContent, productSale).parse();
		htmlContent = new ProductCommentParser(htmlContent, productComment).parse();
		htmlContent = new ProductCollectParser(htmlContent, productCollect).parse();
		// 替换商品规格{ms:field.specification/}
		htmlContent = new ProductSpecificationId(htmlContent, product.getProductSpecificationId() + "").parse();
		// 替换商品销量{ms:field.stock/}
		htmlContent = new ProductStockParser(htmlContent, productStock).parse();
		// 替换商品id{ms:field.id/}
		htmlContent = new ProductIdParser(htmlContent, product.getProductId() + "").parse();
		// 获取商品所属的栏目实体
		ColumnEntity column = (ColumnEntity) columnBiz.getEntity(product.getBasicCategoryId());
		// 替换商品栏目id标签{ms:filed.typeid/}
		htmlContent = new ProductTypeIdParser(htmlContent, column.getCategoryId() + "").parse();

		htmlContent = new ProductLitpicParser(htmlContent, product.getBasicThumbnails()).parse();
		// 替换商品的栏目标题标签{ms:filed.typetitle/}
		ProductTypeTitleParser ptp = new ProductTypeTitleParser(htmlContent, column.getCategoryTitle());
		// 判断是否是取该商品所属栏目的父栏目标题
		ColumnEntity tmp = (ColumnEntity) columnBiz.getEntity(column.getCategoryCategoryId());
		if (ptp.isTop()) {
			if (tmp != null) {
				ptp.setNewCotent(tmp.getCategoryTitle());
			}
		}
		htmlContent = ptp.parse();
		// 替换商品的栏目链接标签
		ProductTypeLinkParser plp = new ProductTypeLinkParser(htmlContent,
				this.getWebsiteUrl() + column.getColumnPath() + File.separator + IParserRegexConstant.HTML_INDEX);
		if (plp.isTop()) {
			if (tmp != null) {
				ptp.setNewCotent(
						this.getWebsiteUrl() + tmp.getColumnPath() + File.separator + IParserRegexConstant.HTML_INDEX);
			}
		}
		htmlContent = plp.parse();
		// 判断该文章是否有新增字段
		if (column.getColumnContentModelId() != 0) {
			// 根据表单类型id查找出所有的字段信息
			List<BaseEntity> listField = fieldBiz.queryListByCmid(column.getColumnContentModelId());
			// 遍历所有的字段实体,得到字段名列表信息
			List<String> listFieldName = new ArrayList<String>();
			for (int i = 0; i < listField.size(); i++) {
				ContentModelFieldEntity field = (ContentModelFieldEntity) listField.get(i);
				listFieldName.add(field.getFieldFieldName());
			}
			ContentModelEntity contentModel = (ContentModelEntity) contentBiz
					.getEntity(column.getColumnContentModelId());
			// 组织where条件
			Map<String, Integer> where = new HashMap<String, Integer>();
			where.put("basicId", product.getBasicId());
			// 获取各字段的值
			List fieldLists = fieldBiz.queryBySQL(contentModel.getCmTableName(), listFieldName, where);
			if (fieldLists != null && fieldLists.size() > 0) {
				Map filedValue = (Map) fieldLists.get(0);
				if (filedValue != null) {
					htmlContent = new TaglibParser(htmlContent, filedValue, column.getColumnContentModelId(), fieldBiz,
							listField).parse();
				}
			}

			// 读取并解析各标签内容
		}
		return htmlContent;
	}

	/**
	 * 对商品列表的解析不带分页
	 * 
	 * @return
	 */
	public String parseProductList() {
		// 查找当前模版页面拥有多少个列表标签
		int listNum = net.mingsoft.mall.parser.impl.ListParser.countPrcList(super.htmlContent);
		// 替换完分页标签后的HTML代码
		for (int i = 0; i < listNum; i++) {
			// 当前列表标签中属性的集合-------------------
			Map<String, String> property = net.mingsoft.mall.parser.impl.ListParser.listProperty(super.htmlContent,
					false);
			// 取当前标签下的栏目ID
			int columnId = StringUtil.string2Int(property.get(ListParser.LIST_TYPEID));
			int[] columnIds;
			// 列表每页显示的数量
			int size = StringUtil.string2Int(property.get(ListParser.LIST_SIZE));
			// 显示文章的形式flag属性
			String flag = property.get(ListParser.LIST_FLAG);
			// 显示文章的形式noflag属性
			String noFlag = property.get(ListParser.LIST_NOFLAG);
			// 排序
			String orderBy = property.get(ListParser.LIST_ORDERBY);
			String order = property.get(ListParser.LIST_ORDER);
			// 取出当前栏目下的子栏目Id
			if (columnId != 0) {
				columnIds = columnBiz.queryChildrenCategoryIds(columnId, app.getAppId(), modelId);
			} else {
				columnId = this.curColumnId;
				// 如果没有栏目id，则取所有的栏目信息，否则去取栏目的子栏目
				if (columnId == 0) {
					columnIds = columnBiz.queryCategoryIdsByModelIdAndAppId(app.getAppId(), modelId);
				} else {
					columnIds = columnBiz.queryChildrenCategoryIds(columnId, app.getAppId(), modelId);
				}
			}
			if (columnIds == null || columnIds.length <= 0) {
				htmlContent = new net.mingsoft.mall.parser.impl.ListParser(app, htmlContent, null, this.getWebsiteUrl(),
						property, false, fieldBiz, contentBiz).parse();
				return htmlContent;
			}
			// int productCount
			// =productBiz.getProducntSpecificationCount(columnIds,
			// app.getAppId(),flag,noFlag);
			// 如果没有指定文章每页显示数量则显示所有数量
			// 当数据库中该栏目下没有该文章时不取数据
			/**
			 * 判断文章列表的orderby属性
			 */
			if (StringUtil.isBlank(order)) {
				order = "desc";
			}
			PageHelper.startPage(0, size == 0 ? 99999 : size, false);
			List<ProductEntity> productList = productBiz.queryList(app.getAppId(), columnIds, orderBy,
					order.equals("desc") ? true : false, ProductEnum.ON_SHELF.toInt(), flag, noFlag);
			PageInfo page = new PageInfo(productList);
			if (page.getTotal() != 0) {
				// 替换列表标签
				htmlContent = new net.mingsoft.mall.parser.impl.ListSpecificationParser(app, htmlContent, productList,
						this.getWebsiteUrl(), property, false, fieldBiz, contentBiz).parse();

			} else {
				htmlContent = new net.mingsoft.mall.parser.impl.ListSpecificationParser(app, htmlContent, null,
						this.getWebsiteUrl(), property, false, fieldBiz, contentBiz).parse();
			}
		}
		return htmlContent;

	}

	/**
	 * 对商品列表的解析带分页
	 * 
	 * @return
	 */
	public String parseList() {
		// 当前列表标签中属性的集合-------------------
		Map<String, String> property = net.mingsoft.mall.parser.impl.ListSpecificationParser.listProperty(htmlContent,
				true);
		if (property == null) { // 没有找到分页标签标签
			return htmlContent;
		}
		String isPaging = property.get(ListParser.LIST_ISPAGING);
		// 判断是否使用了分页标签
		if (!StringUtil.isBlank(isPaging) && isPaging.equals("true")) {
			if (column == null) {
				this.curColumnId = 0;
			} else {
				this.curColumnId = column.getCategoryId();
			}
			// 查询当前栏目的子栏目id
			int[] columnIds = columnBiz.queryChildrenCategoryIds(curColumnId, app.getAppId(), modelId);
			// 列表每页显示的数量
			int size = StringUtil.string2Int(property.get(ListParser.LIST_SIZE));
			// 排序
			String orderBy = property.get(ListParser.LIST_ORDERBY);
			String order = property.get(ListParser.LIST_ORDER);

			String flag = property.get(ListParser.LIST_FLAG);
			String noFlag = property.get(ListParser.LIST_NOFLAG);
			/**
			 * 判断文章列表的orderby属性
			 */
			if (StringUtil.isBlank(order)) {
				order = "desc";
			}
			PageHelper.startPage(this.curPageNo, size, false);
			List<ProductEntity> productList = productBiz.queryList(app.getAppId(), columnIds, orderBy,
					order.equals("desc") ? true : false, ProductEnum.ON_SHELF.toInt(), flag, noFlag);
			PageInfo _page = new PageInfo(productList);
			if (_page.getSize() > 0) {
				if (page==null)  {
					page = new PageUtilHtml(curPageNo, size, (int)_page.getSize(), listLinkPath);
				}
				htmlContent = new net.mingsoft.mall.parser.impl.ListSpecificationParser(app, htmlContent, productList,
						this.getWebsiteUrl(), property, true, fieldBiz, contentBiz).parse();
			}
		} else {
			htmlContent = new net.mingsoft.mall.parser.impl.ListSpecificationParser(app, htmlContent, null,
					this.getWebsiteUrl(), property, true, fieldBiz, contentBiz).parse();
		}
		return htmlContent;

	}

	/**
	 * 对分页标签进行解析
	 * 
	 * @return
	 */
	public String parsePage() {
		// 替换分页标签
		htmlContent = new PageParser(htmlContent, page).parse();
		// 替换页面的总数，当前文章处于第几页，列表文章的总数标签
		htmlContent = new PageNumParser(htmlContent, page).parse();
		return htmlContent;
	}

	/**
	 * 解析分类标签
	 * 
	 * @param htmlContent
	 *            原始html内容
	 * @param column
	 * 
	 * 
	 * @param websiteUrl
	 *            网站连接地址
	 * @return
	 */
	private String parseChannel() {
		// 当只存在栏目ID时，解析相关的文章中的栏目标签
		if (column != null) {
			// 查找当前模版页面拥有多少个栏目列表标签
			String columnTitle = column.getCategoryTitle();
			ColumnEntity tmp = null;
			// 替换文章所在栏目标签：{ms:field.typetitle/}
			ArticleTypeTitleParser attp = new ArticleTypeTitleParser(htmlContent, columnTitle);
			if (attp.isTop()) {
				if (column.getCategoryCategoryId() > 0) {
					tmp = (ColumnEntity) columnBiz.getEntity(column.getCategoryCategoryId());
					columnTitle = tmp.getCategoryTitle();
				}
			}
			attp.setNewCotent(columnTitle);
			htmlContent = attp.parse();
			// 替换文章栏目链接标签{ms:filed.typelink/}
			ArticleTypeLinkParser atlp = new ArticleTypeLinkParser(htmlContent,
					this.getWebsiteUrl() + column.getColumnPath() + File.separator + IParserRegexConstant.HTML_INDEX);
			if (atlp.isTop()) {
				if (tmp == null && column.getCategoryCategoryId() > 0l) { // 如果用户写分类名称标签的时候没有使用top属性，而在使用连接标签的时候使用就再次查询分类
					tmp = (ColumnEntity) columnBiz.getEntity(column.getCategoryCategoryId());
				}
				atlp.setNewCotent(
						this.getWebsiteUrl() + tmp.getColumnPath() + File.separator + IParserRegexConstant.HTML_INDEX);
			}
			htmlContent = atlp.parse();
			// //----------------------------解析栏目标签----------------------------
			// 解析当前栏目id
			htmlContent = new ArticleTypeIdParser(htmlContent, column.getCategoryId() + "").parse();
			int strNumType = ChannelParser.channelNum(htmlContent);
			for (int i = 0; i < strNumType; i++) {
				// 当前列表栏目中属性的集合
				Map<String, String> mapProperty = ChannelParser.channelProperty(htmlContent);
				// 取当前标签下的栏目ID
				int tempColumnId = StringUtil.string2Int(mapProperty.get(ChannelParser.CHANNEL_TYPEID));
				if (tempColumnId == 0) {
					tempColumnId = column.getCategoryId();
				}
				// 指定要显示的栏目数量
				String size = mapProperty.get(ChannelParser.CHANNEL_TYPE_SIZE);
				Integer _size = null;
				if (!StringUtil.isBlank(size) && StringUtil.isInteger(size)) {
					_size = StringUtil.string2Int(size);
				}
				List<ColumnEntity> categoryList = null;
				if (tempColumnId != 0) {
					// 取出栏目的取值范围
					String type = mapProperty.get(ChannelParser.CHANNEL_TYPE);
					// 判断用户填写的栏目属性，如果未填写那么取当前栏目的下级栏目，如果但前栏目没有下级栏目那么晚取本级栏目
					if (type == null) {
						categoryList = columnBiz.queryChildListByColumnId(tempColumnId, _size);
					} else if (type.equals(ChannelParser.CHANNEL_TYPE_SON)) {// 如果填写:son,那么取下级栏目，没有下级栏目则取本级栏目
						categoryList = columnBiz.queryChildListByColumnId(tempColumnId, _size);
					} else if (type.equals(ChannelParser.CHANNEL_TYPE_TOP)) {// 如果为：top,那么取上级栏目，如果没有上级栏目则取本级栏目
						categoryList = columnBiz.queryTopSiblingListByColumnId(tempColumnId, _size);
					} else if (type.equals(ChannelParser.CHANNEL_TYPE_LEVEL)) {// 如果为：level,则取本级栏目
						categoryList = columnBiz.querySibling(tempColumnId, _size);
					}
					// 替换栏目标签
					htmlContent = new ChannelParser(htmlContent, categoryList, this.getWebsiteUrl(),
							column != null ? column.getCategoryId() : 0, mapProperty.get(ChannelParser.CHANNEL_CLASS))
									.parse();
				} else {
					categoryList = columnBiz.queryChild(tempColumnId, app.getAppId(), null, _size);
					// 替换栏目标签
					htmlContent = new ChannelParser(htmlContent, categoryList, this.getWebsiteUrl()).parse();
				}
			}
		}
		return htmlContent;
	}

	/**
	 * 获取当前分页总数
	 * 
	 * @param app
	 *            应用实体
	 * @param htmlContent
	 *            模板内容
	 * @param column
	 *            当前栏目实体
	 * @return 当前分页总数
	 */
	public int getPageSize(AppEntity app, String htmlContent, ColumnEntity column) {
		// 页面总数，默认为1
		int pageSize = 1;
		// 当前列表标签中属性的集合-------------------
		Map<String, String> property = net.mingsoft.mall.parser.impl.ListParser.listProperty(htmlContent, true);
		// 没有找到分页标签标签
		if (property == null) {
			return pageSize;
		}

		String isPaging = property.get(ListParser.LIST_ISPAGING);
		if (!StringUtil.isBlank(isPaging) && isPaging.equals("true")) {
			int[] columnIds = null;
			// 取出当前栏目下的子栏目Id
//			columnIds.add(column.getCategoryId());
			if (column != null) {
				// 取出当前栏目下的子栏目Id
				if (column.getCategoryId() != 0) {
					columnIds = columnBiz.queryChildrenCategoryIds(column.getCategoryId(), app.getAppId(), modelId);
//					columnIds.add(column.getCategoryId());
				}
			}
			// 列表每页显示的数量
			int size = StringUtil.string2Int(property.get(ListParser.LIST_SIZE));
		}
		return pageSize;
	}

	/**
	 * 搜索的分页查询
	 * 
	 * @param htmlContent
	 *            搜索模板内容
	 * @param articleList
	 *            文章实体列表
	 * @param page
	 *            分页
	 * @return
	 */
	public String parseSearchList() {
		// 当前列表标签中属性的集合-------------------
		Map<String, String> property = ListParser.listProperty(htmlContent, true);
		if (property == null) { // 没有找到分页标签标签
			return htmlContent;
		}
		String isPaging = property.get(ListParser.LIST_ISPAGING);
		if (isPaging != null && isPaging.equals("true")) {
			// 排序
			String orderBy = property.get(ListParser.LIST_ORDERBY);
			String order = property.get(ListParser.LIST_ORDER);
			// 取当前标签下的栏目ID
			int columnId = StringUtil.string2Int(property.get(ListParser.LIST_TYPEID));
			int[] columnIds = null;
			// 从数据库取出文章列表数组
			List listProduct = productSearchList;
			int productCount = 0;
			// 列表每页显示的数量
			int size = StringUtil.string2Int(property.get(ListParser.LIST_SIZE));
			/**
			 * 判断文章列表的orderby属性
			 */
			if (StringUtil.isBlank(order)) {
				order = "desc";
			}
			// 从数据库取出文章列表数组
			/*
			 * 判断栏目id是否指定 如果指定则取该栏目下的文章,否则取符合搜索条件的文章
			 */
			if (columnId != 0) {
				columnIds = columnBiz.queryChildIdsByColumnId(columnId, app.getAppId());
//				columnIds.add(columnId);
				PageHelper.startPage(0, size, false);
				listProduct = productBiz.queryList(app.getAppId(), columnIds, orderBy,
						order.equals("desc") ? true : false, ProductEnum.ON_SHELF.toInt(), null, null);
			}
			// 当数据库中该栏目下没有该文章时不取数据
			if (listProduct != null) {
				/**
				 * 判断文章列表的orderby属性
				 */
				if (StringUtil.isBlank(order)) {
					order = "desc";
				}
				// 替换列表标签
				htmlContent = new net.mingsoft.mall.parser.impl.ListSpecificationParser(app, htmlContent, listProduct,
						this.getWebsiteUrl(), property, true, fieldBiz, contentBiz).parse();
			}

		}
		return htmlContent;
	}
}

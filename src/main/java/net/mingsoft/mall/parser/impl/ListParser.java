package net.mingsoft.mall.parser.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mingsoft.mdiy.biz.IContentModelBiz;
import com.mingsoft.mdiy.biz.IContentModelFieldBiz;
import com.mingsoft.basic.entity.AppEntity;
import com.mingsoft.cms.entity.ArticleEntity;
import com.mingsoft.parser.IParserRegexConstant;
import com.mingsoft.parser.impl.general.DateParser;
import com.mingsoft.util.StringUtil;

import net.mingsoft.mall.entity.ProductEntity;

/**
 * 解析商品列表标签, {ms:prclist typeid= size= titlelen= flag = }:列表头标签,<br/>
 * {/ms:prclist}:列表尾标签,<br/>
 * 列表中的属性：<br/>
 * typeid 类型 int栏目ID,在列表模板和档案模板中一般不需要指定，在首页模板中允许用","分开表示多个栏目,<br/>
 * size 类型 int 返回文档列表总数,默认为20条全部返回，也可以配合分页使用,<br/>
 * titlelen 类型 int 标题长度,等同于titlelength。默认40个汉字,<br/>
 * flag 类型 String flag = c 自定义属性值：推荐[c]幻灯[f],<br/>
 * [field.index/]:序号,根据显示条数显示的序号1 2 …..10,<br/>
 * [field.id/]:编号,对应文章在数据库里的自动编号,<br/>
 * [field.title/]:标题,标题长度根据titlelen的属性值指定，默认40个汉字,<br/>
 * [field.fulltitle/]:全部标题,显示完整的标题,<br/>
 * [field.content length=/]:内容,length=:内容的长度,指定获取文章长度(非必填),<br/>
 * [field.typename/]:分类名称，文章所属分类的名称,<br/>
 * [field.typeid/]:分类编号,文章所属分类的编号,<br/>
 * [field.typelink/]:分类连接,点击连接连接到当前分类的列表,<br/>
 * [field.link/]:内容链接,点击显示文章具体的内容地址,<br/>
 * [field.code/]:商品编码<br/>
 * [field.price/]:商品价格<br/>
 * [field.sale/]:商品销量<br/>
 * [field.stock/]:商品库存<br/>
 * 
 * @author 史爱华 技术支持：景德镇铭飞科技 官网：www.ming-soft.com
 */
public class ListParser extends com.mingsoft.cms.parser.impl.ListParser {

	/**
	 * 带分页列表头标签, 文章列表标签 {ms:arclist typeid= size= titlelen= flag = ispaging=}
	 */
	protected final static String PRODUCT_LIST_PAGING = "\\{ms:prclist.*.(ispaging=true){1}.*\\}";

	/**
	 * 不带分页列表头标签, 文章列表标签 {ms:arclist typeid= size= titlelen= flag = }
	 */
	protected final static String PRODUCT_LIST_NOPAGING = "\\{ms:prclist(?![^\\{\\}]*? ispaging=true[^\\{\\}]*?}).*?\\}";

	/**
	 * 定位有分页列表标签属性
	 */
	protected final static String PRODUCT_LIST_PAGING_PROPERTY = "\\{ms:prclist(.*.(ispaging=true){1}.*)\\}";

	/**
	 * 定位没有分页列表标签中所有的属性
	 */
	protected final static String PRODUCT_LIST_PROPERTY = "\\{ms:prclist((?![^\\{\\}]*?ispaging=true[^{}]*?}).*?)\\}";

	/**
	 * 商品编码 商品列表子标签 [field.code/]
	 */
	protected final static String CODE_FIELD_LIST = "\\[field.code/\\]";

	/**
	 * 商品销量 商品列表子标签 [field.sale/]
	 */
	protected final static String SALE_FIELD_LIST = "\\[field.sale/\\]";

	/**
	 * 商品销量 商品列表子标签 [field.comment/]
	 */
	protected final static String COMMENT_FIELD_LIST = "\\[field.comment/\\]";
	/**
	 * 商品销量 商品列表子标签 [field.collect/]
	 */
	protected final static String COLLECT_FIELD_LIST = "\\[field.collect/\\]";

	/**
	 * 商品价格 商品列表子标签 [field.price/]
	 */
	protected final static String PRICE_FIELD_LIST = "\\[field.price/\\]";

	/**
	 * 商品库存 商品列表子标签 [field.stock/]
	 */
	protected final static String STOCK_FIELD_LIST = "\\[field.stock/\\]";

	/**
	 * 商品价格 商品列表子标签 [field.price/]
	 */
	protected final static String COST_PRICE_FIELD_LIST = "\\[field.costprice/\\]";

	/**
	 * 临时列表结束标签
	 */
	protected final static String PRODUCT_LIST_TEMP_TAB_END = "\\{MS:TAB}([\\s\\S]*?)(\\{\\/ms:prclist})";

	/**
	 * 列表尾标签 文章列表标签 {/ms:arclist}
	 */
	protected final static String PRODUCT_LIST_END = "\\{/ms:prclist\\}";

	/**
	 * 列表解析构造,
	 * 
	 * @param htmlCotent
	 *            原始内容
	 * @param articles
	 *            文章列表
	 * @param websiteUrl
	 *            网站地址
	 * @param isPaging
	 *            ture:分页
	 * @param listProperty
	 *            当前标签属性
	 */
	public ListParser(AppEntity app, String htmlCotent, List<ProductEntity> productList, String websiteUrl,
			Map<String, String> listProperty, boolean isPaging, IContentModelFieldBiz fieldBiz,
			IContentModelBiz contentBiz) {
		super(app, htmlCotent, null, websiteUrl, listProperty, isPaging, fieldBiz, contentBiz);
		String tabTmpContent = "";
		if (isPaging) {
			// 在HTML模版中标记出要用内容替换的标签
			tabTmpContent = replaceStartAndEnd(htmlCotent, PRODUCT_LIST_PAGING);
		} else {
			tabTmpContent = replaceStartAndEnd(htmlCotent, PRODUCT_LIST_NOPAGING);
		}
		this.listProperty = listProperty;
		this.fieldBiz = fieldBiz;
		this.contentBiz = contentBiz;
		// 经过遍历后的数组标签
		super.newCotent = list(tabTmpContent, htmlCotent, productList, websiteUrl);
		super.htmlCotent = tabTmpContent;

	}

	/**
	 * 遍历文章数组，将取出的内容替换标签
	 * 
	 * @param tabHtmlContent
	 *            替换过的html模版
	 * @param htmlContent
	 *            原始htlm模版内容
	 * @param articleList
	 *            文章数组
	 * @param path
	 *            项目路径
	 * @return 用内容替换标签后的HTML代码
	 */
	@Override
	protected String list(String tabHtmlContent, String htmlContent, List productList, String path) {
		String htmlList = "";
		String tabHtml = "";
		tabHtml = tabHtml(tabHtmlContent);
		if (productList != null && tabHtml != null && productList.size() != 0 && tabHtml != "") {
			for (int i = 0; i < productList.size(); i++) {
				ProductEntity product = (ProductEntity) productList.get(i);
				// 序号,根据显示条数显示的序号1 2 …..10。
				htmlList += tabContent(tabHtml, StringUtil.int2String((i + 1)), INDEX_FIELD_LIST);
				// 编号,对应文章在数据库里的自动编号。
				htmlList = tabContent(htmlList, StringUtil.int2String(product.getBasicId()), ID_FIELD_LIST);
				// 标题,标题长度根据titlelen的属性值指定，默认40个汉字,
				// htmlList = tabContent(htmlList,
				// titleLength(product.getBasicTitle(),
				// htmlContent),TITLE_FIELD_LIST);
				// 全部标题,显示完整的标题。
				// htmlList = tabContent(htmlList,
				// StringUtil.null2String(product.getBasicTitle()),FULLTITLE_FIELD_LIST);
				// 商品发布时间(非必填),
				htmlList = new DateParser(htmlList, product.getBasicDateTime()).parse();// tabContent(htmlList,
																						// date(article.getBasicUpdateTime(),
																						// htmlList),DATE_FIELD_LIST);
				// 商品内容
				htmlList = tabContent(htmlList, contentLength(product.getBasicDescription(), htmlList),
						CONTENT_FIELD_LIST);
				// 分类名称，商品所属分类的名称,
				if (product.getColumn() != null) {
					htmlList = tabContent(htmlList, StringUtil.null2String(product.getColumn().getCategoryTitle()),
							TYPENAME_FIELD_LIST);
				}
				String thumbnails = product.getBasicThumbnails();
				// 获取其中的一张缩略图
				if (!StringUtil.isBlank(product.getBasicThumbnails())) {
					String[] imgs = product.getBasicThumbnails().replaceAll(" ", "").split("\\|");
					// 判断是否存在图片
					if (imgs.length > 0) {
						thumbnails = imgs[0];
					}
				}

				String productCode = product.getProductCode();// 商品编吗
				String productPrice = product.getProductPrice() + "";// 商品价格
				String productSale = product.getProductSale() + "";// 商品销售
				String productComment = product.getBasicComment() + "";// 商品评论
				String productCollect = product.getBasicCollect() + "";// 商品收藏
				String productStock = product.getProductStock() + "";// 商品库存
				String productTitle = product.getBasicTitle();
				// 商品链接 ：[field.link/]
				String link = path + StringUtil.null2String(product.getColumn().getColumnPath()) + File.separator
						+ product.getBasicId() + IParserRegexConstant.HTML_SUFFIX;
				link = StringUtil.removeRepeatStr(link, File.separator);
				// 判断该商品是否存在规格
				if (product.getProductSpecificationId() != 0) {
					link = path + StringUtil.null2String(product.getColumn().getColumnPath()) + File.separator
							+ product.getProductSpecificationId() + IParserRegexConstant.HTML_SUFFIX;
					productCode = product.getProductSpecificationCode();
					productPrice = product.getProductSpecificationPrice() + "";
					productSale = product.getProductSpecificationStock() + "";
					productStock = product.getProductSpecificationStock() + "";
					productTitle = product.getProductSpecificationTitle();
					if (!StringUtil.isBlank(product.getProductSpecificationImg())) {
						String[] imgs = product.getProductSpecificationImg().replaceAll(" ", "").split("\\|");
						if (imgs.length > 0) {
							thumbnails = imgs[imgs.length - 1];
						}

					}
				}
				// 商品略图[field.litpic/]
				htmlList = tabContent(htmlList, StringUtil.null2String(thumbnails), LITPIC_FIELD_LIST);
				// 标题,标题长度根据titlelen的属性值指定，默认40个汉字,
				htmlList = tabContent(htmlList, titleLength(productTitle, htmlContent), TITLE_FIELD_LIST);
				// 全部标题,显示完整的标题。
				htmlList = tabContent(htmlList, StringUtil.null2String(productTitle), FULLTITLE_FIELD_LIST);
				link = StringUtil.removeRepeatStr(link, File.separator).replace(":/", "://");
				htmlList = tabContent(htmlList, link, LINK_FIELD_LIST);
				// 分类编号,文章所属分类的编号,
				htmlList = tabContent(htmlList, product.getBasicCategoryId(), TYPEID_FIELD_LIST);
				// 商品编码[field.code/]
				htmlList = tabContent(htmlList, StringUtil.null2String(productCode), CODE_FIELD_LIST);
				// 商品价格[field.price/]
				htmlList = tabContent(htmlList, StringUtil.null2String(productPrice), PRICE_FIELD_LIST);
				// 商品销量[field.sale/]
				htmlList = tabContent(htmlList, StringUtil.null2String(productSale), SALE_FIELD_LIST);
				// 商品销量[field.comment/]
				htmlList = tabContent(htmlList, StringUtil.null2String(productComment), COMMENT_FIELD_LIST);
				// 商品销量[field.collect/]
				htmlList = tabContent(htmlList, StringUtil.null2String(productCollect), COLLECT_FIELD_LIST);
				// 商品库存[field.stock/]
				htmlList = tabContent(htmlList, StringUtil.null2String(productStock), STOCK_FIELD_LIST);
				// 商品原价[field.costPrice/]
				htmlList = tabContent(htmlList, StringUtil.null2String(product.getProductCostPrice() + ""),
						COST_PRICE_FIELD_LIST);
				// 当前页面商品的数量[field.num]
				String numArticle = Integer.toString(productList.size());
				htmlList = tabContent(htmlList, numArticle, NUM_ARTICLE_LIST);
				// 分类连接：[field.typelink/] 点击连接连接到当前分类的列表
				String channelLink = path + File.separator + StringUtil.null2String(product.getColumn().getColumnPath())
						+ File.separator + IParserRegexConstant.HTML_INDEX;
				channelLink = StringUtil.removeRepeatStr(channelLink, File.separator);
				htmlList = tabContent(htmlList, channelLink, TTYPELINK_FIELD_LIST);
				// 对自定义字段进行替换
				htmlList = replaceField(htmlList, product.getColumn(), product.getBasicId());
			}
		}
		return htmlList;
	}

	/**
	 * 获取模版文件中文章列表的个数
	 * 
	 * @param html
	 *            文件模版
	 * @return 返回该字符串的个数
	 */
	public static int countPrcList(String html) {
		int listNumBegin = count(html, PRODUCT_LIST_NOPAGING);
		return listNumBegin;
	}

	/**
	 * 临时将arclist替换成ms:tab
	 * 
	 * @param htmlCotent
	 *            原始内容
	 * @param regex
	 *            规则，主要是两种，一种有分页，一种没有分页
	 * @return 替换好的内容
	 */
	protected String replaceStartAndEnd(String htmlCotent, String regex) {
		super.htmlCotent = htmlCotent;
		super.newCotent = TAB_BEGIN_LIST;
		htmlCotent = super.replaceFirst(regex);
		if (htmlCotent.equals("")) {
			htmlCotent = IParserRegexConstant.REGEX_ERRO;
		}
		Pattern pattern = Pattern.compile(PRODUCT_LIST_TEMP_TAB_END);
		Matcher matcher = pattern.matcher(htmlCotent);
		if (matcher.find()) {
			String tmp = matcher.group();
			String tmp2 = tmp;
			tmp = tmp.replaceAll(PRODUCT_LIST_END, TAB_END_LIST);
			htmlCotent = htmlCotent.replace(tmp2, tmp);
		}
		if (htmlCotent.equals("")) {
			htmlCotent = IParserRegexConstant.REGEX_ERRO;
		}
		return htmlCotent;
	}

	/**
	 * 取出列表标签中的属性
	 * 
	 * @param html
	 *            HTML模版
	 * @param isPaging
	 *            true;为分页属性，false:为普通列表属性
	 * @return 属性集合Map(属性名称,属性值)
	 */
	public static Map<String, String> listProperty(String html, boolean isPaging) {
		Map<String, String> listPropertyMap = new HashMap<String, String>();
		String listProperty = "";
		if (isPaging) {
			listProperty = parseFirst(html, PRODUCT_LIST_PAGING_PROPERTY, 1);
		} else {
			listProperty = parseFirst(html, PRODUCT_LIST_PROPERTY, 1);
		}
		if (listProperty == null) {
			return listPropertyMap;
		}
		List<String> listPropertyName = parseAll(listProperty, PRORETY_NAME, 1);
		List<String> listPropertyValue = parseAll(listProperty, PROPERTY_VALUE, 1);
		for (int i = 0; i < listPropertyName.size(); i++) {
			listPropertyMap.put(listPropertyName.get(i).trim(), listPropertyValue.get(i).trim());
		}
		return listPropertyMap;
	}

}

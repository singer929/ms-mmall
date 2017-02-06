package net.mingsoft.mall.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mingsoft.basic.biz.IAppBiz;
import com.mingsoft.basic.biz.IColumnBiz;
import com.mingsoft.basic.biz.IModelBiz;
import com.mingsoft.basic.constant.Const;
import com.mingsoft.basic.entity.AppEntity;
import com.mingsoft.basic.entity.CategoryEntity;
import com.mingsoft.basic.entity.ColumnEntity;
import com.mingsoft.cms.parser.CmsParser;
import com.mingsoft.parser.IParserRegexConstant;
import com.mingsoft.util.FileUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.mall.biz.IProductBiz;
import net.mingsoft.mall.constant.ModelCode;
import net.mingsoft.mall.entity.ProductEntity;
import net.mingsoft.mall.parser.MallParser;

/**
 * 
 * <p>
 * <b>mswx-铭飞微信酒店预订平台</b>
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
 * @author 史爱华
 * 
 * @version 400-001-001
 * 
 *          <p>
 *          版权所有 铭飞科技
 *          </p>
 * 
 *          <p>
 *          Comments:应用静态生成，适用于静态信息的应用，例如网站、商城、，但是像论坛这些就不需要这些生成功能；
 *          </p>
 * 
 *          <p>
 *          Create Date:2015-04-22
 *          </p>
 * 
 *          <p>
 *          Modification history:
 *          </p>
 */
@Controller("mallSpecificationGenerate")
@RequestMapping("/${managerPath}/mall/generate")
@Scope("request")
public class GeneraterAction extends BaseAction{
	
	/**
	 * 栏目业务层注入
	 */
	@Autowired
	private IColumnBiz columnBiz;
	
	/**
	 *站点管理业务层
	 */
	@Autowired
	private IAppBiz appBiz;
	
	/**
	 * 模块业务层注入
	 */
	@Autowired
	private IModelBiz modelBiz;
	
	/**
	 * 产品业务层注入
	 */
	@Autowired
	private IProductBiz productBiz;
	
	
	/**
	 * 商城解析器
	 */
	@Autowired
	private MallParser mallParser;
	
	/**
	 * 更新主页
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return view("/mall/generate/generate_index");
	}

	/**
	 * 生成主页
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/generateIndex")
	@ResponseBody
	public boolean generateIndex(HttpServletRequest request, HttpServletResponse response) {
		String tmpFileName = request.getParameter("url"); // 模版文件名称
		String generateFileName = request.getParameter("position");// 生成后的文件名称

		// 获取站点信息
		int websiteId = getManagerBySession(request).getBasicId();
		AppEntity app = (AppEntity) appBiz.getEntity(websiteId);
		String tmpName = app.getAppStyle();// 获取模版名称
		String tmpPath = getRealPath(request, IParserRegexConstant.REGEX_SAVE_TEMPLATE); // 获取系统模版存放物理路径
		String webSiteTmpPath = tmpPath + File.separator + app.getAppId() + File.separator + tmpName;// 根据站点id组装站点信息路径　格式：templets／站点ID/模版风格
		// 模版路径加上(用户选择的主页的模版的路径)default/index.html
		String tmpFilePath = webSiteTmpPath + File.separator + tmpFileName;
		//读取手机端的模板
		String tmpMobileFilePath = webSiteTmpPath + File.separator + IParserRegexConstant.MOBILE + File.separator + tmpFileName;// 手机端

		// 生成地址
		String generatePath = getRealPath(request, IParserRegexConstant.HTML_SAVE_PATH) + File.separator + websiteId + File.separator + generateFileName;
		String generateMobilePath = getRealPath(request, IParserRegexConstant.HTML_SAVE_PATH) + File.separator + websiteId + File.separator + IParserRegexConstant.MOBILE + File.separator + generateFileName;

		FileUtil.createFolder(getRealPath(request, IParserRegexConstant.HTML_SAVE_PATH) + File.separator + websiteId);
		FileUtil.createFolder(getRealPath(request, IParserRegexConstant.HTML_SAVE_PATH) + File.separator + websiteId + File.separator + IParserRegexConstant.MOBILE); // 手机端
		// 获取文件所在路径 首先判断用户输入的模版文件是否存在
		File file = new File(tmpFilePath);

		// 判断文件是否存在，若不存在弹出返回信息
		if (!file.exists()) {
			return false;
		} else {
			// 当前模版的物理路径
			String htmlContent = FileUtil.readFile(tmpFilePath); // 读取模版文件内容
			String mobileHtmlContent = FileUtil.readFile(tmpMobileFilePath); // 读取手机端模版文件内容
			if (!StringUtil.isBlank(htmlContent)) {
				
				htmlContent = mallParser.parse(htmlContent,app);
				Map map = new HashMap();
				map.put(CmsParser.MOBILE,IParserRegexConstant.MOBILE);
				mobileHtmlContent = mallParser.parse(mobileHtmlContent,app,map);
				// 解析HTML上的标签
				FileUtil.writeFile(htmlContent, generatePath, FileUtil.URF8);
				FileUtil.writeFile(mobileHtmlContent, generateMobilePath, FileUtil.URF8);
				return true;
			} else {
				// 提示错误：未找到模版
				htmlContent = webSiteTmpPath + File.separator + tmpFileName ;
			}
			return false;
		}
	}
	
	
	/**
	 * 生成列表的静态页面
	 * 
	 * @param request
	 * @param response
	 * @param columnId 要更新的栏目id
	 */
	@RequestMapping("/{columnId}/genernateColumn")
	@ResponseBody
	public boolean genernateColumn(HttpServletRequest request, HttpServletResponse response, @PathVariable int columnId){
		// 获取站点id
		AppEntity app = BasicUtil.getApp();
		int appId = app.getAppId();
		String mobileStyle = app.getAppMobileStyle(); // 手机端模版
		String url = app.getAppHostUrl() + File.separator + IParserRegexConstant.HTML_SAVE_PATH + File.separator + app.getAppId();
		// 站点生成后保存的html地址
		String generatePath = getRealPath(request, IParserRegexConstant.HTML_SAVE_PATH) + File.separator + appId + File.separator;
		FileUtil.createFolder(generatePath);
		// 网站风格物理路径
		String tmpPath = getRealPath(request, IParserRegexConstant.REGEX_SAVE_TEMPLATE) + File.separator + appId + File.separator + app.getAppStyle();
		List<ColumnEntity> columns = new ArrayList<ColumnEntity>();
		int modelId = this.getModelCodeId(request, net.mingsoft.mall.constant.ModelCode.MALL_CATEGORY);
		
		// 如果栏目id小于0则更新所有的栏目，否则只更新选中的栏目
		if (columnId > 0) {
			List<CategoryEntity> categorys = columnBiz.queryChildrenCategory(columnId, app.getAppId(),modelId);
			if (categorys == null || categorys.size() == 0){
				ColumnEntity c = (ColumnEntity) columnBiz.getEntity(columnId);
				columns.add(c);
			}
			else{
				ColumnEntity pe = (ColumnEntity) columnBiz.getEntity(columnId);
				columns.add(pe);
				
				for (CategoryEntity c : categorys) {
					ColumnEntity ce = (ColumnEntity) columnBiz.getEntity(c.getCategoryId());
					columns.add(ce);
				}
			}
		} else {
			//获取所有的商品分类
			columns = columnBiz.queryAll(app.getAppId(),this.getModelCodeId(request,ModelCode.MALL_CATEGORY));
		}
		// 获取栏目列表模版
		for (ColumnEntity column : columns) {
			String columnPath = null;// pc端
			String mobilePath = null;// 手机端
			// 生成列表保存路径
			FileUtil.createFolder(generatePath + column.getColumnPath());
			// 判断是否为顶级栏目，进行栏目路径的组合
			if (column.getCategoryCategoryId() == 0) {
					FileUtil.createFolder(generatePath + column.getCategoryId());
					columnPath = generatePath + File.separator + column.getCategoryId();
					if (!StringUtil.isBlank(mobileStyle)) {
						FileUtil.createFolder(generatePath + mobileStyle + File.separator + column.getCategoryId());
						mobilePath = generatePath + mobileStyle + File.separator + column.getCategoryId();
					}
			} else {
					if (!StringUtil.isBlank(mobileStyle)) {
						mobilePath = generatePath + mobileStyle + File.separator + column.getColumnPath();
						FileUtil.createFolder(mobilePath);
					}
					columnPath = generatePath + column.getColumnPath();
			}
			// 判断列表类型
			//判断是否存在手机端
			if(!StringUtil.isBlank(mobileStyle)){
				FileUtil.createFolder(mobilePath);
				String mobileListTtmpContent = FileUtil.readFile(tmpPath + File.separator + mobileStyle + File.separator + column.getColumnListUrl());
				// 如果模版不为空就进行标签替换
				if (!StringUtil.isBlank(mobileListTtmpContent)) {
					// 生成手机端模版
					// 要生成手机的静态页面数
					int mobilePageSize = mallParser.getPageSize(app,mobileListTtmpContent, column);
					// 根据页面数,循环生成静态页面个数在
					Map map = new HashMap();
					for (int i = 0; i < mobilePageSize; i++) {
						String writePath = mobilePath + File.separator + IParserRegexConstant.PAGE_LIST + (i + 1) + IParserRegexConstant.HTML_SUFFIX;
						if (i == 0) {
							writePath = mobilePath + File.separator + IParserRegexConstant.HTML_INDEX;
						}
						String pagePath = url + File.separator + mobileStyle + File.separator + column.getColumnPath() + File.separator + IParserRegexConstant.PAGE_LIST ;
						map.put(mallParser.LIST_LINK_PATH, pagePath);
						map.put(mallParser.CUR_PAGE_NO, i + 1);
						map.put(mallParser.MOBILE,IParserRegexConstant.MOBILE);
						String pageContent = mallParser.parse(mobileListTtmpContent,app, column,map);
						FileUtil.writeFile(pageContent, writePath, FileUtil.URF8);// 写文件
					}
				}
			}
			// 读取列表模版地址
			String listTtmpContent = FileUtil.readFile(tmpPath + File.separator + column.getColumnListUrl());
			// 要生成的静态页面数
			int pageSize = mallParser.getPageSize(app,listTtmpContent, column);// generaterFactory.getPageSize(app, listTtmpContent, column);
			// 根据页面数,循环生成静态页面个数在
			Map map = new HashMap();
			for (int i = 0; i < pageSize; i++) {
				String writePath = columnPath + File.separator + IParserRegexConstant.PAGE_LIST + (i + 1) + IParserRegexConstant.HTML_SUFFIX;
				if (i == 0) {
					writePath = columnPath + File.separator + IParserRegexConstant.HTML_INDEX;
				}
				String pagePath = app.getAppHostUrl() + File.separator + IParserRegexConstant.HTML_SAVE_PATH + File.separator + app.getAppId() + File.separator + column.getColumnPath() + File.separator + "list";
				map.put(mallParser.LIST_LINK_PATH, pagePath);
				map.put(mallParser.CUR_PAGE_NO, i + 1);
				String pageContent = mallParser.parse(listTtmpContent,app, column,map);
				FileUtil.writeFile(pageContent, writePath, FileUtil.URF8);// 写文件
			}
		}
		
		return true;
	}
	
	
	/**
	 * 根据栏目id生成商品
	 * @param request 
	 * @param response
	 * @param columnId 栏目id
	 * @return 是否更新成功信息
	 */
	@RequestMapping("/{columnId}/generateProduct")
	@ResponseBody
	public boolean generateProduct(HttpServletRequest request, HttpServletResponse response, @PathVariable int columnId) {
		String dateTime = request.getParameter("dateTime");
		AppEntity app = BasicUtil.getApp();
		String mobileStyle = null;
		if (app != null) {
			mobileStyle = app.getAppMobileStyle(); // 手机端模版
		}
		String generatePath = getRealPath(request, IParserRegexConstant.HTML_SAVE_PATH) + File.separator + app.getAppId() + File.separator;// 站点生成后保存的html地址
		FileUtil.createFolder(generatePath);
		String tmpPath = getRealPath(request, IParserRegexConstant.REGEX_SAVE_TEMPLATE) + File.separator + app.getAppId() + File.separator + app.getAppStyle(); // 网站风格物理路径
		List<ColumnEntity> columns = new ArrayList<ColumnEntity>();
		int modelId = this.getModelCodeId(request, net.mingsoft.mall.constant.ModelCode.MALL_CATEGORY);
		if (columnId > 0) {
			List<CategoryEntity> categorys = columnBiz.queryChildrenCategory(columnId, app.getAppId(),modelId);
			for (CategoryEntity c : categorys) {
				columns.add((ColumnEntity) columnBiz.getEntity(c.getCategoryId()));
			}
		} else {
			//获取所有的商品分类
			columns = columnBiz.queryAll(app.getAppId(),modelId);
		}
		String url = app.getAppHostUrl() + File.separator + IParserRegexConstant.HTML_SAVE_PATH + File.separator + app.getAppId() + File.separator; // 商品地址前缀
		// 如果没有选择栏目，生成规则
		// 1先读取所有的栏目,从最低级的分类取
		List<ProductEntity> productList = null;
		for (ColumnEntity tempColumn : columns) {// 循环分类
				FileUtil.createFolder(generatePath + tempColumn.getColumnPath());
				String writePath = null;
				productList = this.productBiz.query(tempColumn.getCategoryId(), dateTime, app.getAppId());
				//productList = this.productBiz.queryListB
				//productList = productBiz.queryProducntSpecificationByDateAndByColumnId(dateTime, app.getAppId(), tempColumn.getCategoryId());
				// 有符合条件的新闻就更新
				if (productList.size() > 0) {
						// 生成文档
					String tmpContent = FileUtil.readFile(tmpPath + File.separator + tempColumn.getColumnUrl());// 读取文章模版地址
							String mobileTmpContent = null;
							if (!StringUtil.isBlank(mobileStyle)) {
								mobileTmpContent = FileUtil.readFile(tmpPath + File.separator + mobileStyle + File.separator + tempColumn.getColumnUrl());// 读取手机端文章模版地址
							}
							for (int ai = 0; ai < productList.size();) {
								ProductEntity product = productList.get(ai);
								if (tempColumn.getCategoryCategoryId() == 0) { // 如果是顶级下面有文章，那么文章的生成地址就是　分类id/文章编号
									FileUtil.createFolder(generatePath + tempColumn.getCategoryId());
									// 组合文章路径如:html/站点id/栏目id/文章id.html
									writePath = generatePath + tempColumn.getColumnPath() + File.separator + product.getProductId() + IParserRegexConstant.HTML_SUFFIX;
									product.setProductLinkUrl(url + tempColumn.getColumnPath() + File.separator + product.getProductId() + IParserRegexConstant.HTML_SUFFIX);
									if(product.getProductSpecificationId()!=0){
										writePath = generatePath + tempColumn.getColumnPath() + File.separator + product.getProductSpecificationId() + IParserRegexConstant.HTML_SUFFIX;
										product.setProductLinkUrl(url + tempColumn.getColumnPath() + File.separator + product.getProductSpecificationId() + IParserRegexConstant.HTML_SUFFIX);
									}
								} else { // 如果有父级别编号，需要组合路径。格式如:父ID/子id/文章id.html
									String path = File.separator + product.getProductId() + IParserRegexConstant.HTML_SUFFIX;
									writePath = generatePath + tempColumn.getColumnPath() + File.separator + path;
									product.setProductLinkUrl(url + tempColumn.getColumnPath() + File.separator + product.getProductId() + IParserRegexConstant.HTML_SUFFIX);
									
								}
								String content =  mallParser.parse(tmpContent,app,tempColumn,product);
								FileUtil.writeFile(content, writePath, FileUtil.URF8);// 写文件
								// 手机端
								if (!StringUtil.isBlank(mobileTmpContent)) {
									FileUtil.createFolder(generatePath + mobileStyle + File.separator + tempColumn.getColumnPath());
									if (tempColumn.getCategoryCategoryId() == 0) { // 如果是顶级下面有文章，那么文章的生成地址就是　分类id/文章编号
										// 组合文章路径如:html/站点id/栏目id/文章id.html
										writePath = generatePath + mobileStyle + File.separator + tempColumn.getColumnPath() + File.separator +  product.getProductId() + IParserRegexConstant.HTML_SUFFIX;
										product.setProductLinkUrl(url + mobileStyle + File.separator + tempColumn.getColumnPath() + File.separator + product.getProductId()+ IParserRegexConstant.HTML_SUFFIX);
									} else { // 如果有父级别编号，需要组合路径。格式如:父ID/子id/文章id.html
										String path = File.separator +  product.getProductId() + IParserRegexConstant.HTML_SUFFIX;
										writePath = generatePath + mobileStyle + File.separator + tempColumn.getColumnPath() + File.separator + path;
										product.setProductLinkUrl(url + mobileStyle + File.separator + tempColumn.getColumnPath() + File.separator +  product.getProductId() + IParserRegexConstant.HTML_SUFFIX);
									}
									Map map = new HashMap();
									map.put(CmsParser.MOBILE,IParserRegexConstant.MOBILE);
									String tmp = mallParser.parse(mobileTmpContent,app,tempColumn,product,map);//;generaterFactory.builderArticle(app, tempColumn, article, mobileTmpContent, tmpPath, previous, next, mobileStyle); // 解析标签
									FileUtil.writeFile(tmp, writePath, FileUtil.URF8);// 写文件
								}
								ai++;
							}
						}
			}
		return true;
	}
	
	/**
	 * 用户预览主页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/{position}/viewIndex")
	public String viewIndex(HttpServletRequest request, @PathVariable String position) {
		//获取应用实体信息
		AppEntity app = this.getApp(request);
		//组织主页预览地址
		String indexPosition = app.getAppHostUrl() + File.separator + IParserRegexConstant.HTML_SAVE_PATH + File.separator + app.getAppId() + File.separator + position;
		return "redirect:" + indexPosition;
	}
	
	/**
	 * 更新栏目
	 * @param request 
	 * @param response 
	 * @param request 
	 * 
	 * @return 栏目更新页面
	 */
	@RequestMapping("/column")
	public String column(HttpServletRequest request, ModelMap model) {
		// 该站点ID有session提供
		int appId =  this.getAppId(request);
		//根据modelId查询商品分类
		List<ColumnEntity> list = columnBiz.queryAll(appId, this.getModelCodeId(request,net.mingsoft.mall.constant.ModelCode.MALL_CATEGORY));
		model.addAttribute("list", JSONArray.toJSONString(list));
		return view("/mall/generate/generate_column");
	}
	
	/**
	 * 更新产品
	 * @param request
	 * @return 商品更新页面
	 */
	@RequestMapping("/product")
	public String product(HttpServletRequest request, ModelMap model){

		// 该站点ID有session提供
		int appId =  BasicUtil.getAppId();
		//根据modelId查询商品分类
		List<ColumnEntity> list = columnBiz.queryAll(appId, this.getModelCodeId(request,net.mingsoft.mall.constant.ModelCode.MALL_CATEGORY));
		model.addAttribute("list",  JSONArray.toJSONString(list));		
		request.setAttribute("now", new Date());
		return view("/mall/generate/generate_product");
	}
	

}

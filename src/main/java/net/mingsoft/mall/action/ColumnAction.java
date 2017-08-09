package net.mingsoft.mall.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mingsoft.basic.constant.Const;
import com.mingsoft.basic.constant.ModelCode;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.mingsoft.base.util.JSONObject;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;
import com.mingsoft.base.entity.BaseEntity;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.basic.bean.ListBean;
import com.mingsoft.base.filter.DateValueFilter;
import com.mingsoft.base.filter.DoubleValueFilter;
import com.mingsoft.basic.biz.IColumnBiz;
import com.mingsoft.basic.biz.IModelBiz;
import com.mingsoft.basic.constant.e.SessionConstEnum;
import com.mingsoft.basic.entity.CategoryEntity;
import com.mingsoft.basic.entity.ColumnEntity;
import com.mingsoft.basic.entity.ManagerEntity;
import com.mingsoft.mdiy.biz.IContentModelBiz;

import net.mingsoft.basic.bean.EUListBean;
	
/**
 * 栏目表管理控制层
 * @author 伍晶晶
 * @version 
 * 版本号：100<br/>
 * 创建日期：2017-8-8 15:28:53<br/>
 * 历史修订：<br/>
 */
@Controller("mallColumnAction")
@RequestMapping("/${managerPath}/mall/column")
public class ColumnAction extends BaseAction{
	
	/**
	 * 注入栏目表业务层
	 */	
	@Autowired
	private IColumnBiz columnBiz;
	/**
	 * 业务层的注入表单内容模型
	 */
	@Autowired
	private IContentModelBiz contentModelBiz;
	/**
	 * 模块业务层注入
	 */
	@Autowired
	private IModelBiz modelBiz;

	
	/**
	 * 返回主界面index
	 */
	@RequestMapping("/index")
	public String index(HttpServletResponse response,HttpServletRequest request){
		return view ("/mall/column/index");
	}
	/**
	 * 栏目添加跳转页面
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	public String add(HttpServletRequest request,ModelMap model) {
		ManagerEntity managerSession = (ManagerEntity) getSession(request, SessionConstEnum.MANAGER_SESSION);
		// 站点ID
		int appId =this.getAppId(request);
		List<ColumnEntity> list = columnBiz.queryAll(appId, this.getModelCodeId(request));
		// 查询属于当前管理员的内容模型
		List<BaseEntity> listCm = contentModelBiz.queryByManagerId(managerSession.getManagerId());
		ColumnEntity columnSuper = new ColumnEntity();
		model.addAttribute("columnSuper", columnSuper);
		model.addAttribute("column",new ColumnEntity());
		model.addAttribute("listColumn", JSONArray.toJSONString(list));
		model.addAttribute("listCm", listCm);
		return view("/mall/column/column_form");
	}
	/**
	 * 栏目更新页面跳转
	 * @param columnId 栏目ID
	 * @param request
	 * @param model
	 * @return 编辑栏目页
	 */
	@RequestMapping("/{columnId}/edit")
	public String edit(@PathVariable int columnId, HttpServletRequest request,ModelMap model) {
		// 获取管理实体
		ManagerEntity managerSession = (ManagerEntity) getSession(request, SessionConstEnum.MANAGER_SESSION);
		// 站点ID
		int appId = this.getAppId(request);
		List<ColumnEntity> list = new ArrayList<ColumnEntity>();
		// 判断管理员权限,查询其管理的栏目集合
		list = columnBiz.queryAll(appId, this.getModelCodeId(request));
		//查询当前栏目实体
		ColumnEntity column = (ColumnEntity) columnBiz.getEntity(columnId);
		// 查询属于当前管理员的内容模型
		List<BaseEntity> listCm = contentModelBiz.queryByManagerId(managerSession.getManagerId());
		model.addAttribute("column", column);
		model.addAttribute("columnc", column.getCategoryId());
		model.addAttribute("listCm", listCm);
		ColumnEntity columnSuper = new ColumnEntity();
		// 获取父栏目对象
		if (column.getCategoryCategoryId() != Const.COLUMN_TOP_CATEGORY_ID) {
			columnSuper = (ColumnEntity) columnBiz.getEntity(column.getCategoryCategoryId());
		}
		model.addAttribute("columnSuper", columnSuper);
		model.addAttribute("listColumn", JSONArray.toJSONString(list));
		return view("/mall/column/column_form");
	}
	/**
	 * 根据栏目ID删除栏目记录
	 * @param categoryId 栏目ID
	 * @param response
	 * @param request
	 */
	@RequestMapping("/{categoryId}/delete")
	@ResponseBody
	public void delete(@PathVariable int categoryId,HttpServletResponse response, HttpServletRequest request) {
		// 站点ID有session获取
		int websiteId = this.getAppId(request);
		// 查询该栏目是否有子栏目,如果存在子栏目则返回错误提示，否则删除该栏目
		if (columnBiz.queryChild(categoryId, websiteId,this.getModelCodeId(request),null).size() > 0) {
			this.outJson(response, false);
		} else {
			columnBiz.deleteCategory(categoryId);
			this.outJson(response, true);
		}
	}
		
	/**
	 * 根据栏目ID进行栏目删除确认，如果有子栏目则不能被删除
	 * @param categoryId 栏目ID
	 * @param response
	 * @param request
	 */
	@RequestMapping("/{categoryId}/deleteConfirm")
	public void deleteConfirm(@PathVariable int categoryId,HttpServletResponse response, HttpServletRequest request){
		// 站点ID有session获取
		int websiteId = this.getAppId(request);
		// 查询该栏目是否有子栏目,如果存在子栏目则返回错误提示，否则删除该栏目
		if (columnBiz.queryColumnChildListCountByWebsiteId(categoryId, websiteId) > 0) {
			this.outJson(response, ModelCode.CMS_COLUMN, true, "false");
		} else {
			this.outJson(response, ModelCode.CMS_COLUMN, true, "true");
		}
	}
	/**
	 * 查询栏目表列表
	 * @param column 栏目表实体
	 * <i>column参数包含字段信息参考：</i><br/>
	 * columnCategoryid 关联category表（类别表ID）<br/>
	 * columnKeyword 栏目简介<br/>
	 * columnDescrip 栏目关键字的扩展<br/>
	 * columnType 1,代表最终列表栏目。2，代表频道封面。3，带表外部链接<br/>
	 * columnUrl 如果是外部链接，则保持外部链接地址。如果为最终列表栏目，就保存文章显示列表<br/>
	 * columnListurl 最终列表栏目的列表模板地址<br/>
	 * columnTentmodelid 栏目类型,直接影响栏目发布的表单样式<br/>
	 * columnWebsiteid 栏目所属站点ID<br/>
	 * columnPath 栏目路径<br/>
	 * columnContentmodelid 栏目管理的内容模型id<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>[<br/>
	 * { <br/>
	 * columnCategoryid: 关联category表（类别表ID）<br/>
	 * columnKeyword: 栏目简介<br/>
	 * columnDescrip: 栏目关键字的扩展<br/>
	 * columnType: 1,代表最终列表栏目。2，代表频道封面。3，带表外部链接<br/>
	 * columnUrl: 如果是外部链接，则保持外部链接地址。如果为最终列表栏目，就保存文章显示列表<br/>
	 * columnListurl: 最终列表栏目的列表模板地址<br/>
	 * columnTentmodelid: 栏目类型,直接影响栏目发布的表单样式<br/>
	 * columnWebsiteid: 栏目所属站点ID<br/>
	 * columnPath: 栏目路径<br/>
	 * columnContentmodelid: 栏目管理的内容模型id<br/>
	 * }<br/>
	 * ]</dd><br/>	 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(@ModelAttribute ColumnEntity column,HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		
		// 站点ID有session获取
		int websiteId = this.getAppId(request);
		// 需要打开的栏目节点树的栏目ID
		List list = columnBiz.queryAll(websiteId, this.getModelCodeId(request));
		EUListBean _list = new EUListBean(list, list.size());
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(_list));
	}
	
	
	/**
	 * 获取栏目表
	 * @param column 栏目表实体
	 * <i>column参数包含字段信息参考：</i><br/>
	 * columnCategoryid 关联category表（类别表ID）<br/>
	 * columnKeyword 栏目简介<br/>
	 * columnDescrip 栏目关键字的扩展<br/>
	 * columnType 1,代表最终列表栏目。2，代表频道封面。3，带表外部链接<br/>
	 * columnUrl 如果是外部链接，则保持外部链接地址。如果为最终列表栏目，就保存文章显示列表<br/>
	 * columnListurl 最终列表栏目的列表模板地址<br/>
	 * columnTentmodelid 栏目类型,直接影响栏目发布的表单样式<br/>
	 * columnWebsiteid 栏目所属站点ID<br/>
	 * columnPath 栏目路径<br/>
	 * columnContentmodelid 栏目管理的内容模型id<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * columnCategoryid: 关联category表（类别表ID）<br/>
	 * columnKeyword: 栏目简介<br/>
	 * columnDescrip: 栏目关键字的扩展<br/>
	 * columnType: 1,代表最终列表栏目。2，代表频道封面。3，带表外部链接<br/>
	 * columnUrl: 如果是外部链接，则保持外部链接地址。如果为最终列表栏目，就保存文章显示列表<br/>
	 * columnListurl: 最终列表栏目的列表模板地址<br/>
	 * columnTentmodelid: 栏目类型,直接影响栏目发布的表单样式<br/>
	 * columnWebsiteid: 栏目所属站点ID<br/>
	 * columnPath: 栏目路径<br/>
	 * columnContentmodelid: 栏目管理的内容模型id<br/>
	 * }</dd><br/>
	 */
	@RequestMapping("/get")
	@ResponseBody
	public void get(@ModelAttribute ColumnEntity column,HttpServletResponse response, HttpServletRequest request,ModelMap model){
		
		//ColumnEntity _column = (ColumnEntity)columnBiz.getEntity(column.getColumnCategoryid());
		//this.outJson(response, _column);
	}
	
	/**
	 * 保存栏目表实体
	 * @param column 栏目表实体
	 * <i>column参数包含字段信息参考：</i><br/>
	 * columnCategoryid 关联category表（类别表ID）<br/>
	 * columnKeyword 栏目简介<br/>
	 * columnDescrip 栏目关键字的扩展<br/>
	 * columnType 1,代表最终列表栏目。2，代表频道封面。3，带表外部链接<br/>
	 * columnUrl 如果是外部链接，则保持外部链接地址。如果为最终列表栏目，就保存文章显示列表<br/>
	 * columnListurl 最终列表栏目的列表模板地址<br/>
	 * columnTentmodelid 栏目类型,直接影响栏目发布的表单样式<br/>
	 * columnWebsiteid 栏目所属站点ID<br/>
	 * columnPath 栏目路径<br/>
	 * columnContentmodelid 栏目管理的内容模型id<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * columnCategoryid: 关联category表（类别表ID）<br/>
	 * columnKeyword: 栏目简介<br/>
	 * columnDescrip: 栏目关键字的扩展<br/>
	 * columnType: 1,代表最终列表栏目。2，代表频道封面。3，带表外部链接<br/>
	 * columnUrl: 如果是外部链接，则保持外部链接地址。如果为最终列表栏目，就保存文章显示列表<br/>
	 * columnListurl: 最终列表栏目的列表模板地址<br/>
	 * columnTentmodelid: 栏目类型,直接影响栏目发布的表单样式<br/>
	 * columnWebsiteid: 栏目所属站点ID<br/>
	 * columnPath: 栏目路径<br/>
	 * columnContentmodelid: 栏目管理的内容模型id<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/save")
	@ResponseBody
	public void save(@ModelAttribute ColumnEntity column, HttpServletResponse response, HttpServletRequest request) {
		//验证栏目简介的值是否合法			
		if(StringUtil.isBlank(column.getColumnKeyword())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("column.keyword")));
			return;			
		}
		if(!StringUtil.checkLength(column.getColumnKeyword()+"", 1, 300)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("column.keyword"), "1", "300"));
			return;			
		}
		//验证栏目关键字的扩展的值是否合法			
		if(StringUtil.isBlank(column.getColumnDescrip())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("column.descrip")));
			return;			
		}
		if(!StringUtil.checkLength(column.getColumnDescrip()+"", 1, 500)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("column.descrip"), "1", "500"));
			return;			
		}
		//验证1,代表最终列表栏目。2，代表频道封面。3，带表外部链接的值是否合法			
		if(StringUtil.isBlank(column.getColumnType())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("column.type")));
			return;			
		}
		if(!StringUtil.checkLength(column.getColumnType()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("column.type"), "1", "10"));
			return;			
		}
		//验证如果是外部链接，则保持外部链接地址。如果为最终列表栏目，就保存文章显示列表的值是否合法			
		if(StringUtil.isBlank(column.getColumnUrl())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("column.url")));
			return;			
		}
		if(!StringUtil.checkLength(column.getColumnUrl()+"", 1, 50)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("column.url"), "1", "50"));
			return;			
		}
		
		columnBiz.saveEntity(column);
		this.outJson(response, JSONObject.toJSONString(column));
	}
	
	/**
	 * @param column 栏目表实体
	 * <i>column参数包含字段信息参考：</i><br/>
	 * columnCategoryid:多个columnCategoryid直接用逗号隔开,例如columnCategoryid=1,2,3,4
	 * 批量删除栏目表
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            <dd>{code:"错误编码",<br/>
	 *            result:"true｜false",<br/>
	 *            resultMsg:"错误信息"<br/>
	 *            }</dd>
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(@RequestBody List<ColumnEntity> columns,HttpServletResponse response, HttpServletRequest request) {
		int[] ids = new int[columns.size()];
		for(int i = 0;i<columns.size();i++){
			//ids[i] = columns.get(i).getColumnCategoryid();
		}
		columnBiz.delete(ids);		
		this.outJson(response, true);
	}
	
	/** 
	 * 更新栏目表信息栏目表
	 * @param column 栏目表实体
	 * <i>column参数包含字段信息参考：</i><br/>
	 * columnCategoryid 关联category表（类别表ID）<br/>
	 * columnKeyword 栏目简介<br/>
	 * columnDescrip 栏目关键字的扩展<br/>
	 * columnType 1,代表最终列表栏目。2，代表频道封面。3，带表外部链接<br/>
	 * columnUrl 如果是外部链接，则保持外部链接地址。如果为最终列表栏目，就保存文章显示列表<br/>
	 * columnListurl 最终列表栏目的列表模板地址<br/>
	 * columnTentmodelid 栏目类型,直接影响栏目发布的表单样式<br/>
	 * columnWebsiteid 栏目所属站点ID<br/>
	 * columnPath 栏目路径<br/>
	 * columnContentmodelid 栏目管理的内容模型id<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * columnCategoryid: 关联category表（类别表ID）<br/>
	 * columnKeyword: 栏目简介<br/>
	 * columnDescrip: 栏目关键字的扩展<br/>
	 * columnType: 1,代表最终列表栏目。2，代表频道封面。3，带表外部链接<br/>
	 * columnUrl: 如果是外部链接，则保持外部链接地址。如果为最终列表栏目，就保存文章显示列表<br/>
	 * columnListurl: 最终列表栏目的列表模板地址<br/>
	 * columnTentmodelid: 栏目类型,直接影响栏目发布的表单样式<br/>
	 * columnWebsiteid: 栏目所属站点ID<br/>
	 * columnPath: 栏目路径<br/>
	 * columnContentmodelid: 栏目管理的内容模型id<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/update")
	@ResponseBody	 
	public void update(@ModelAttribute ColumnEntity column, HttpServletResponse response,
			HttpServletRequest request) {
		//验证栏目简介的值是否合法			
		if(StringUtil.isBlank(column.getColumnKeyword())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("column.keyword")));
			return;			
		}
		if(!StringUtil.checkLength(column.getColumnKeyword()+"", 1, 300)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("column.keyword"), "1", "300"));
			return;			
		}
		//验证栏目关键字的扩展的值是否合法			
		if(StringUtil.isBlank(column.getColumnDescrip())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("column.descrip")));
			return;			
		}
		if(!StringUtil.checkLength(column.getColumnDescrip()+"", 1, 500)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("column.descrip"), "1", "500"));
			return;			
		}
		//验证1,代表最终列表栏目。2，代表频道封面。3，带表外部链接的值是否合法			
		if(StringUtil.isBlank(column.getColumnType())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("column.type")));
			return;			
		}
		if(!StringUtil.checkLength(column.getColumnType()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("column.type"), "1", "10"));
			return;			
		}
		//验证如果是外部链接，则保持外部链接地址。如果为最终列表栏目，就保存文章显示列表的值是否合法			
		if(StringUtil.isBlank(column.getColumnUrl())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("column.url")));
			return;			
		}
		if(!StringUtil.checkLength(column.getColumnUrl()+"", 1, 50)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("column.url"), "1", "50"));
			return;			
		}
		columnBiz.updateEntity(column);
		this.outJson(response, JSONObject.toJSONString(column));
	}
		
}
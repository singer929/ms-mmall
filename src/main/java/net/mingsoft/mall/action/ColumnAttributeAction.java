package net.mingsoft.mall.action;

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


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.mingsoft.mall.biz.IColumnAttributeBiz;
import net.mingsoft.mall.entity.ColumnAttributeEntity;
import net.mingsoft.base.util.JSONObject;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;
import com.mingsoft.base.entity.BaseEntity;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.basic.bean.ListBean;
import com.mingsoft.base.filter.DateValueFilter;
import com.mingsoft.base.filter.DoubleValueFilter;
import net.mingsoft.basic.bean.EUListBean;
	
/**
 * 默认规格数据管理控制层
 * @author 伍晶晶
 * @version 
 * 版本号：0.0<br/>
 * 创建日期：2017-8-15 14:29:39<br/>
 * 历史修订：<br/>
 */
@Controller
@RequestMapping("/${managerPath}/mall/columnAttribute")
public class ColumnAttributeAction extends net.mingsoft.mall.action.BaseAction{
	
	/**
	 * 注入默认规格数据业务层
	 */	
	@Autowired
	private IColumnAttributeBiz columnAttributeBiz;
	
	/**
	 * 返回主界面index
	 */
	@RequestMapping("/index")
	public String index(HttpServletResponse response,HttpServletRequest request ,ModelMap model){
		int categoryId =BasicUtil.getInt("categoryId");
		model.addAttribute("categoryId",categoryId);
		return view ("/mall/column_attribute/index");
	}
	
	/**
	 * 查询默认规格数据列表
	 * @param columnAttribute 默认规格数据实体
	 * <i>columnAttribute参数包含字段信息参考：</i><br/>
	 * caId 规格id 主键<br/>
	 * caCategoryId 规格的类型id(预留)<br/>
	 * caName 规格名称<br/>
	 * caFields 默认的字段,多个值用逗号隔开<br/>
	 * caType 类型<br/>
	 * caSearch 0:不允许搜索 搜索状态<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>[<br/>
	 * { <br/>
	 * caId: 规格id 主键<br/>
	 * caCategoryId: 规格的类型id(预留)<br/>
	 * caName: 规格名称<br/>
	 * caFields: 默认的字段,多个值用逗号隔开<br/>
	 * caType: 类型<br/>
	 * caSearch: 0:不允许搜索 搜索状态<br/>
	 * }<br/>
	 * ]</dd><br/>	 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(@ModelAttribute ColumnAttributeEntity columnAttribute,HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		if(columnAttribute == null){
			columnAttribute = new ColumnAttributeEntity();
		}
		BasicUtil.startPage();
		List columnAttributeList = columnAttributeBiz.query(columnAttribute);
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(new EUListBean(columnAttributeList,(int)BasicUtil.endPage(columnAttributeList).getTotal()),new DoubleValueFilter(),new DateValueFilter()));
	}
	
	/**
	 * 返回编辑界面columnAttribute_form
	 */
	@RequestMapping("/form")
	public String form(@ModelAttribute ColumnAttributeEntity columnAttribute,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		if(columnAttribute.getCaId() != null){
			BaseEntity columnAttributeEntity = columnAttributeBiz.getEntity(columnAttribute.getCaId());			
			model.addAttribute("columnAttributeEntity",columnAttributeEntity);
		}
		return view ("/mall/column_attribute/form");
	}
	
	/**
	 * 获取默认规格数据
	 * @param columnAttribute 默认规格数据实体
	 * <i>columnAttribute参数包含字段信息参考：</i><br/>
	 * caId 规格id 主键<br/>
	 * caCategoryId 规格的类型id(预留)<br/>
	 * caName 规格名称<br/>
	 * caFields 默认的字段,多个值用逗号隔开<br/>
	 * caType 类型<br/>
	 * caSearch 0:不允许搜索 搜索状态<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * caId: 规格id 主键<br/>
	 * caCategoryId: 规格的类型id(预留)<br/>
	 * caName: 规格名称<br/>
	 * caFields: 默认的字段,多个值用逗号隔开<br/>
	 * caType: 类型<br/>
	 * caSearch: 0:不允许搜索 搜索状态<br/>
	 * }</dd><br/>
	 */
	@RequestMapping("/get")
	@ResponseBody
	public void get(@ModelAttribute ColumnAttributeEntity columnAttribute,HttpServletResponse response, HttpServletRequest request,ModelMap model){
		if(columnAttribute.getCaId()<=0) {
			this.outJson(response, null, false, getResString("err.error", this.getResString("ca.id")));
			return;
		}
		ColumnAttributeEntity _columnAttribute = (ColumnAttributeEntity)columnAttributeBiz.getEntity(columnAttribute.getCaId());
		this.outJson(response, _columnAttribute);
	}
	
	/**
	 * 保存默认规格数据实体
	 * @param columnAttribute 默认规格数据实体
	 * <i>columnAttribute参数包含字段信息参考：</i><br/>
	 * caId 规格id 主键<br/>
	 * caCategoryId 规格的类型id(预留)<br/>
	 * caName 规格名称<br/>
	 * caFields 默认的字段,多个值用逗号隔开<br/>
	 * caType 类型<br/>
	 * caSearch 0:不允许搜索 搜索状态<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * caId: 规格id 主键<br/>
	 * caCategoryId: 规格的类型id(预留)<br/>
	 * caName: 规格名称<br/>
	 * caFields: 默认的字段,多个值用逗号隔开<br/>
	 * caType: 类型<br/>
	 * caSearch: 0:不允许搜索 搜索状态<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/save")
	@ResponseBody
	public void save(@ModelAttribute ColumnAttributeEntity columnAttribute, HttpServletResponse response, HttpServletRequest request) {
		//验证规格的类型id(预留)的值是否合法			
		if(StringUtil.isBlank(columnAttribute.getCaCategoryId())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("ca.category.id")));
			return;			
		}
		if(!StringUtil.checkLength(columnAttribute.getCaCategoryId()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("ca.category.id"), "1", "10"));
			return;			
		}
		//验证默认的字段,多个值用逗号隔开的值是否合法			
		if(StringUtil.isBlank(columnAttribute.getCaFields())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("ca.fields")));
			return;			
		}
		if(!StringUtil.checkLength(columnAttribute.getCaFields()+"", 1, 255)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("ca.fields"), "1", "255"));
			return;			
		}
		columnAttributeBiz.saveEntity(columnAttribute);
		this.outJson(response, JSONObject.toJSONString(columnAttribute));
	}
	
	/**
	 * @param columnAttribute 默认规格数据实体
	 * <i>columnAttribute参数包含字段信息参考：</i><br/>
	 * caId:多个caId直接用逗号隔开,例如caId=1,2,3,4
	 * 批量删除默认规格数据
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            <dd>{code:"错误编码",<br/>
	 *            result:"true｜false",<br/>
	 *            resultMsg:"错误信息"<br/>
	 *            }</dd>
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(@RequestBody List<ColumnAttributeEntity> columnAttributes,HttpServletResponse response, HttpServletRequest request) {
		int[] ids = new int[columnAttributes.size()];
		for(int i = 0;i<columnAttributes.size();i++){
			ids[i] = columnAttributes.get(i).getCaId();
		}
		columnAttributeBiz.delete(ids);		
		this.outJson(response, true);
	}
	
	/** 
	 * 更新默认规格数据信息默认规格数据
	 * @param columnAttribute 默认规格数据实体
	 * <i>columnAttribute参数包含字段信息参考：</i><br/>
	 * caId 规格id 主键<br/>
	 * caCategoryId 规格的类型id(预留)<br/>
	 * caName 规格名称<br/>
	 * caFields 默认的字段,多个值用逗号隔开<br/>
	 * caType 类型<br/>
	 * caSearch 0:不允许搜索 搜索状态<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * caId: 规格id 主键<br/>
	 * caCategoryId: 规格的类型id(预留)<br/>
	 * caName: 规格名称<br/>
	 * caFields: 默认的字段,多个值用逗号隔开<br/>
	 * caType: 类型<br/>
	 * caSearch: 0:不允许搜索 搜索状态<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/update")
	@ResponseBody	 
	public void update(@ModelAttribute ColumnAttributeEntity columnAttribute, HttpServletResponse response,
			HttpServletRequest request) {
		//验证规格的类型id(预留)的值是否合法			
		if(StringUtil.isBlank(columnAttribute.getCaCategoryId())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("ca.category.id")));
			return;			
		}
		if(!StringUtil.checkLength(columnAttribute.getCaCategoryId()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("ca.category.id"), "1", "10"));
			return;			
		}
		//验证默认的字段,多个值用逗号隔开的值是否合法			
		if(StringUtil.isBlank(columnAttribute.getCaFields())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("ca.fields")));
			return;			
		}
		if(!StringUtil.checkLength(columnAttribute.getCaFields()+"", 1, 255)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("ca.fields"), "1", "255"));
			return;			
		}
		columnAttributeBiz.updateEntity(columnAttribute);
		this.outJson(response, JSONObject.toJSONString(columnAttribute));
	}
		
}
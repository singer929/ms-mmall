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
import net.mingsoft.mall.biz.ISpecificationBiz;
import net.mingsoft.mall.entity.SpecificationEntity;
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
 * 版本号：100<br/>
 * 创建日期：2017-8-8 15:18:35<br/>
 * 历史修订：<br/>
 */
@Controller
@RequestMapping("/${managerPath}/mall/specification")
public class SpecificationAction extends net.mingsoft.mall.action.BaseAction{
	
	/**
	 * 注入默认规格数据业务层
	 */	
	@Autowired
	private ISpecificationBiz specificationBiz;
	
	/**
	 * 返回主界面index
	 */
	@RequestMapping("/index")
	public String index(HttpServletResponse response,HttpServletRequest request ,ModelMap model){
		int categoryId =Integer.parseInt(request.getParameter("categoryId")) ;
		model.addAttribute("categoryId",categoryId);
		return view ("/mall/specification/index");
	}
	
	/**
	 * 查询默认规格数据列表
	 * @param specification 默认规格数据实体
	 * <i>specification参数包含字段信息参考：</i><br/>
	 * specificationId 规格id 主键<br/>
	 * specificationName 规格名称<br/>
	 * specificationCategoryId 规格的类型id(预留)<br/>
	 * specificationDefaultFields 默认的字段<br/>
	 * specificationAppId 应用ID<br/>
	 * specificationType 规格类型:1-标准规格,2-自定义规格<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>[<br/>
	 * { <br/>
	 * specificationId: 规格id 主键<br/>
	 * specificationName: 规格名称<br/>
	 * specificationCategoryId: 规格的类型id(预留)<br/>
	 * specificationDefaultFields: 默认的字段<br/>
	 * specificationAppId: 应用ID<br/>
	 * specificationType: 规格类型:1-标准规格,2-自定义规格<br/>
	 * }<br/>
	 * ]</dd><br/>	 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(@ModelAttribute SpecificationEntity specification,HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		if(StringUtil.isBlank(specification)){
			specification = new SpecificationEntity();
		}
		specification.setSpecificationAppId(BasicUtil.getAppId());
		BasicUtil.startPage();
		List specificationList = specificationBiz.query(specification);
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(new EUListBean(specificationList,(int)BasicUtil.endPage(specificationList).getTotal()),new DoubleValueFilter(),new DateValueFilter()));
	}
	
	/**
	 * 返回编辑界面specification_form
	 */
	@RequestMapping("/form")
	public String form(@ModelAttribute SpecificationEntity specification,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		if(specification.getSpecificationId() != null){
			BaseEntity specificationEntity = specificationBiz.getEntity(specification.getSpecificationId());			
			model.addAttribute("specificationEntity",specificationEntity);
		}
		return view ("/mall/specification/form");
	}
	
	/**
	 * 获取默认规格数据
	 * @param specification 默认规格数据实体
	 * <i>specification参数包含字段信息参考：</i><br/>
	 * specificationId 规格id 主键<br/>
	 * specificationName 规格名称<br/>
	 * specificationCategoryId 规格的类型id(预留)<br/>
	 * specificationDefaultFields 默认的字段<br/>
	 * specificationAppId 应用ID<br/>
	 * specificationType 规格类型:1-标准规格,2-自定义规格<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * specificationId: 规格id 主键<br/>
	 * specificationName: 规格名称<br/>
	 * specificationCategoryId: 规格的类型id(预留)<br/>
	 * specificationDefaultFields: 默认的字段<br/>
	 * specificationAppId: 应用ID<br/>
	 * specificationType: 规格类型:1-标准规格,2-自定义规格<br/>
	 * }</dd><br/>
	 */
	@RequestMapping("/get")
	@ResponseBody
	public void get(@ModelAttribute SpecificationEntity specification,HttpServletResponse response, HttpServletRequest request,ModelMap model){
		if(specification.getSpecificationId()<=0) {
			this.outJson(response, null, false, getResString("err.error", this.getResString("specification.id")));
			return;
		}
		SpecificationEntity _specification = (SpecificationEntity)specificationBiz.getEntity(specification.getSpecificationId());
		this.outJson(response, _specification);
	}
	
	/**
	 * 保存默认规格数据实体
	 * @param specification 默认规格数据实体
	 * <i>specification参数包含字段信息参考：</i><br/>
	 * specificationId 规格id 主键<br/>
	 * specificationName 规格名称<br/>
	 * specificationCategoryId 规格的类型id(预留)<br/>
	 * specificationDefaultFields 默认的字段<br/>
	 * specificationAppId 应用ID<br/>
	 * specificationType 规格类型:1-标准规格,2-自定义规格<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * specificationId: 规格id 主键<br/>
	 * specificationName: 规格名称<br/>
	 * specificationCategoryId: 规格的类型id(预留)<br/>
	 * specificationDefaultFields: 默认的字段<br/>
	 * specificationAppId: 应用ID<br/>
	 * specificationType: 规格类型:1-标准规格,2-自定义规格<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/save")
	@ResponseBody
	public void save(@ModelAttribute SpecificationEntity specification, HttpServletResponse response, HttpServletRequest request) {
		//验证默认的字段的值是否合法			
		if(StringUtil.isBlank(specification.getSpecificationDefaultFields())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("specification.default.fields")));
			return;			
		}
		if(!StringUtil.checkLength(specification.getSpecificationDefaultFields()+"", 1, 255)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("specification.default.fields"), "1", "255"));
			return;			
		}
		specification.setSpecificationAppId(BasicUtil.getAppId());
		specificationBiz.saveEntity(specification);
		this.outJson(response, JSONObject.toJSONString(specification));
	}
	
	/**
	 * @param specification 默认规格数据实体
	 * <i>specification参数包含字段信息参考：</i><br/>
	 * specificationId:多个specificationId直接用逗号隔开,例如specificationId=1,2,3,4
	 * 批量删除默认规格数据
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            <dd>{code:"错误编码",<br/>
	 *            result:"true｜false",<br/>
	 *            resultMsg:"错误信息"<br/>
	 *            }</dd>
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(@RequestBody List<SpecificationEntity> specifications,HttpServletResponse response, HttpServletRequest request) {
		int[] ids = new int[specifications.size()];
		for(int i = 0;i<specifications.size();i++){
			ids[i] = specifications.get(i).getSpecificationId();
		}
		specificationBiz.delete(ids);		
		this.outJson(response, true);
	}
	
	/** 
	 * 更新默认规格数据信息默认规格数据
	 * @param specification 默认规格数据实体
	 * <i>specification参数包含字段信息参考：</i><br/>
	 * specificationId 规格id 主键<br/>
	 * specificationName 规格名称<br/>
	 * specificationCategoryId 规格的类型id(预留)<br/>
	 * specificationDefaultFields 默认的字段<br/>
	 * specificationAppId 应用ID<br/>
	 * specificationType 规格类型:1-标准规格,2-自定义规格<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * specificationId: 规格id 主键<br/>
	 * specificationName: 规格名称<br/>
	 * specificationCategoryId: 规格的类型id(预留)<br/>
	 * specificationDefaultFields: 默认的字段<br/>
	 * specificationAppId: 应用ID<br/>
	 * specificationType: 规格类型:1-标准规格,2-自定义规格<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/update")
	@ResponseBody	 
	public void update(@ModelAttribute SpecificationEntity specification, HttpServletResponse response,
			HttpServletRequest request) {
		//验证默认的字段的值是否合法			
		if(StringUtil.isBlank(specification.getSpecificationDefaultFields())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("specification.default.fields")));
			return;			
		}
		if(!StringUtil.checkLength(specification.getSpecificationDefaultFields()+"", 1, 255)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("specification.default.fields"), "1", "255"));
			return;			
		}
		specificationBiz.updateEntity(specification);
		this.outJson(response, JSONObject.toJSONString(specification));
	}
		
}
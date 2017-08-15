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
import net.mingsoft.mall.biz.IProductAttributeBiz;
import net.mingsoft.mall.entity.ProductAttributeEntity;
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
 * 产品规格关联管理控制层
 * @author 伍晶晶
 * @version 
 * 版本号：0.0<br/>
 * 创建日期：2017-8-15 14:29:39<br/>
 * 历史修订：<br/>
 */
@Controller
@RequestMapping("/${managerPath}/mall/productAttribute")
public class ProductAttributeAction extends net.mingsoft.mall.action.BaseAction{
	
	/**
	 * 注入产品规格关联业务层
	 */	
	@Autowired
	private IProductAttributeBiz productAttributeBiz;
	
	/**
	 * 返回主界面index
	 */
	@RequestMapping("/index")
	public String index(HttpServletResponse response,HttpServletRequest request){
		return view ("/mall/product_attribute/index");
	}
	
	/**
	 * 查询产品规格关联列表
	 * @param productAttribute 产品规格关联实体
	 * <i>productAttribute参数包含字段信息参考：</i><br/>
	 * paId <br/>
	 * paProductId 商品编号<br/>
	 * paCaId 分类扩展属性编号<br/>
	 * paName <br/>
	 * paValue <br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>[<br/>
	 * { <br/>
	 * paId: <br/>
	 * paProductId: 商品编号<br/>
	 * paCaId: 分类扩展属性编号<br/>
	 * paName: <br/>
	 * paValue: <br/>
	 * }<br/>
	 * ]</dd><br/>	 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(@ModelAttribute ProductAttributeEntity productAttribute,HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		BasicUtil.startPage();
		List productAttributeList = productAttributeBiz.query(productAttribute);
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(new EUListBean(productAttributeList,(int)BasicUtil.endPage(productAttributeList).getTotal()),new DoubleValueFilter(),new DateValueFilter()));
	}
	
	/**
	 * 返回编辑界面productAttribute_form
	 */
	@RequestMapping("/form")
	public String form(@ModelAttribute ProductAttributeEntity productAttribute,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		if(productAttribute.getPaId() != null){
			BaseEntity productAttributeEntity = productAttributeBiz.getEntity(productAttribute.getPaId());			
			model.addAttribute("productAttributeEntity",productAttributeEntity);
		}
		
		return view ("/mall/product_attribute/form");
	}
	
	/**
	 * 获取产品规格关联
	 * @param productAttribute 产品规格关联实体
	 * <i>productAttribute参数包含字段信息参考：</i><br/>
	 * paId <br/>
	 * paProductId 商品编号<br/>
	 * paCaId 分类扩展属性编号<br/>
	 * paName <br/>
	 * paValue <br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * paId: <br/>
	 * paProductId: 商品编号<br/>
	 * paCaId: 分类扩展属性编号<br/>
	 * paName: <br/>
	 * paValue: <br/>
	 * }</dd><br/>
	 */
	@RequestMapping("/get")
	@ResponseBody
	public void get(@ModelAttribute ProductAttributeEntity productAttribute,HttpServletResponse response, HttpServletRequest request,ModelMap model){
		if(productAttribute.getPaId()<=0) {
			this.outJson(response, null, false, getResString("err.error", this.getResString("pa.id")));
			return;
		}
		ProductAttributeEntity _productAttribute = (ProductAttributeEntity)productAttributeBiz.getEntity(productAttribute.getPaId());
		this.outJson(response, _productAttribute);
	}
	
	/**
	 * 保存产品规格关联实体
	 * @param productAttribute 产品规格关联实体
	 * <i>productAttribute参数包含字段信息参考：</i><br/>
	 * paId <br/>
	 * paProductId 商品编号<br/>
	 * paCaId 分类扩展属性编号<br/>
	 * paName <br/>
	 * paValue <br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * paId: <br/>
	 * paProductId: 商品编号<br/>
	 * paCaId: 分类扩展属性编号<br/>
	 * paName: <br/>
	 * paValue: <br/>
	 * }</dd><br/>
	 */
	@PostMapping("/save")
	@ResponseBody
	public void save(@ModelAttribute ProductAttributeEntity productAttribute, HttpServletResponse response, HttpServletRequest request) {
		//验证的值是否合法			
		if(StringUtil.isBlank(productAttribute.getPaName())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("pa.name")));
			return;			
		}
		if(!StringUtil.checkLength(productAttribute.getPaName()+"", 1, 255)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("pa.name"), "1", "255"));
			return;			
		}
		//验证的值是否合法			
		if(StringUtil.isBlank(productAttribute.getPaValue())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("pa.value")));
			return;			
		}
		if(!StringUtil.checkLength(productAttribute.getPaValue()+"", 1, 255)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("pa.value"), "1", "255"));
			return;			
		}
		productAttributeBiz.saveEntity(productAttribute);
		this.outJson(response, JSONObject.toJSONString(productAttribute));
	}
	
	/**
	 * @param productAttribute 产品规格关联实体
	 * <i>productAttribute参数包含字段信息参考：</i><br/>
	 * paId:多个paId直接用逗号隔开,例如paId=1,2,3,4
	 * 批量删除产品规格关联
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            <dd>{code:"错误编码",<br/>
	 *            result:"true｜false",<br/>
	 *            resultMsg:"错误信息"<br/>
	 *            }</dd>
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(@RequestBody List<ProductAttributeEntity> productAttributes,HttpServletResponse response, HttpServletRequest request) {
		int[] ids = new int[productAttributes.size()];
		for(int i = 0;i<productAttributes.size();i++){
			ids[i] = productAttributes.get(i).getPaId();
		}
		productAttributeBiz.delete(ids);		
		this.outJson(response, true);
	}
	
	/** 
	 * 更新产品规格关联信息产品规格关联
	 * @param productAttribute 产品规格关联实体
	 * <i>productAttribute参数包含字段信息参考：</i><br/>
	 * paId <br/>
	 * paProductId 商品编号<br/>
	 * paCaId 分类扩展属性编号<br/>
	 * paName <br/>
	 * paValue <br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * paId: <br/>
	 * paProductId: 商品编号<br/>
	 * paCaId: 分类扩展属性编号<br/>
	 * paName: <br/>
	 * paValue: <br/>
	 * }</dd><br/>
	 */
	@PostMapping("/update")
	@ResponseBody	 
	public void update(@ModelAttribute ProductAttributeEntity productAttribute, HttpServletResponse response,
			HttpServletRequest request) {
		//验证的值是否合法			
		if(StringUtil.isBlank(productAttribute.getPaName())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("pa.name")));
			return;			
		}
		if(!StringUtil.checkLength(productAttribute.getPaName()+"", 1, 255)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("pa.name"), "1", "255"));
			return;			
		}
		//验证的值是否合法			
		if(StringUtil.isBlank(productAttribute.getPaValue())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("pa.value")));
			return;			
		}
		if(!StringUtil.checkLength(productAttribute.getPaValue()+"", 1, 255)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("pa.value"), "1", "255"));
			return;			
		}
		productAttributeBiz.updateEntity(productAttribute);
		this.outJson(response, JSONObject.toJSONString(productAttribute));
	}
		
}
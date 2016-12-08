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


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.mingsoft.mall.biz.IProductImpressionBiz;
import net.mingsoft.mall.entity.ProductImpressionEntity;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;
import net.mingsoft.basic.util.BasicUtil;
	
/**
 * 商品印象管理控制层
 * @author 铭飞开发团队
 * @version 
 * 版本号：1.0.0<br/>
 * 创建日期：<br/>
 * 历史修订：<br/>
 */
@Controller
@RequestMapping("/${managerPath}/mall/productImpression")
public class ProductImpressionAction extends net.mingsoft.mall.action.BaseAction{
	
	/**
	 * 注入商品印象业务层
	 */	
	@Autowired
	private IProductImpressionBiz productImpressionBiz;
	
	
	/**
	 * 查询商品印象列表
	 * @param response
	 * @param request
	 * @param model
	 */
	@RequestMapping("/list")
	public String list(@ModelAttribute ProductImpressionEntity productImpression,HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		BasicUtil.startPage();
		List productImpressionList = productImpressionBiz.query(productImpression);
		BasicUtil.endPage(productImpressionList);
		model.addAttribute("productImpressionList", productImpressionList);	
		return view("/mall/product_impression/product_impression_list");
	}
	
	/**
	 * 新增 商品印象
	 * @param productImpression 商品印象实体
	 * <i>productImpression参数包含字段信息参考：</i><br/>
	 * piId <br/>
	 * piBasicId 商品编号<br/>
	 * piTitle 印象<br/>
	 * piPeopleId 添加用户<br/>
	 * piAmount 数量<br/>
	 * piDatetime 添加时间<br/>
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(@ModelAttribute ProductImpressionEntity productImpression,HttpServletResponse response, HttpServletRequest request,ModelMap model){
		model.addAttribute("productImpression", productImpression);		
		return view("/mall/product_impression/product_impression_form");
	}
	
	/**
	 * 编辑
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(@ModelAttribute ProductImpressionEntity productImpression,HttpServletResponse response, HttpServletRequest request,ModelMap model){
		if(productImpression.getPiId()<=0) {
			this.outJson(response, null, false, getResString("err.error", this.getResString("pi.id")));
		}
		ProductImpressionEntity _productImpression = (ProductImpressionEntity)productImpressionBiz.getEntity(productImpression.getPiId());
		model.addAttribute("productImpression", _productImpression);		
		BasicUtil.removeUrlParams(new String[]{"piId"});
		return view("/mall/product_impression/product_impression_form");
	}
	
	/**
	 * 保存商品印象实体
	 * @param productImpression 商品印象实体
	 * <i>productImpression参数包含字段信息参考：</i><br/>
	 * piId <br/>
	 * piBasicId 商品编号<br/>
	 * piTitle 印象<br/>
	 * piPeopleId 添加用户<br/>
	 * piAmount 数量<br/>
	 * piDatetime 添加时间<br/>
	 * @param response
	 * @param request
	 */
	@PostMapping("/save")
	@ResponseBody
	public void save(@ModelAttribute ProductImpressionEntity productImpression, HttpServletResponse response, HttpServletRequest request) {
		//验证商品编号的值是否合法			
		if(StringUtil.isBlank(productImpression.getPiBasicId())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("pi.basic.id")));
			return;			
		}
		if(!StringUtil.checkLength(productImpression.getPiBasicId()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("pi.basic.id"), "1", "10"));
			return;			
		}
		//验证印象的值是否合法			
		if(StringUtil.isBlank(productImpression.getPiTitle())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("pi.title")));
			return;			
		}
		if(!StringUtil.checkLength(productImpression.getPiTitle()+"", 1, 255)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("pi.title"), "1", "255"));
			return;			
		}
		//验证添加用户的值是否合法			
		if(StringUtil.isBlank(productImpression.getPiPeopleId())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("pi.people.id")));
			return;			
		}
		if(!StringUtil.checkLength(productImpression.getPiPeopleId()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("pi.people.id"), "1", "10"));
			return;			
		}
		//验证数量的值是否合法			
		if(StringUtil.isBlank(productImpression.getPiAmount())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("pi.amount")));
			return;			
		}
		if(!StringUtil.checkLength(productImpression.getPiAmount()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("pi.amount"), "1", "10"));
			return;			
		}
		//验证添加时间的值是否合法			
		if(StringUtil.isBlank(productImpression.getPiDatetime())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("pi.datetime")));
			return;			
		}
		if(!StringUtil.checkLength(productImpression.getPiDatetime()+"", 1, 19)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("pi.datetime"), "1", "19"));
			return;			
		}
		productImpressionBiz.saveEntity(productImpression);
		this.outJson(response, true);
	}

	/**
	 * 批量删除商品印象
	 * @param response
	 * @param request
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(HttpServletResponse response, HttpServletRequest request) {
		int[] ids = BasicUtil.getInts("piId");
		if(ids==null){
			this.outJson(response,null, false);
			return;
		}
		productImpressionBiz.delete(ids);		
		this.outJson(response, true);
	}
	
	/** 
	 * 更新商品印象信息商品印象
	 * @param productImpression 商品印象实体
	 * <i>productImpression参数包含字段信息参考：</i><br/>
	 * piId <br/>
	 * piBasicId 商品编号<br/>
	 * piTitle 印象<br/>
	 * piPeopleId 添加用户<br/>
	 * piAmount 数量<br/>
	 * piDatetime 添加时间<br/>
	 * @param response
	 * @param request
	 */
	@PostMapping("/update")
	@ResponseBody	 
	public void update(@ModelAttribute ProductImpressionEntity productImpression, HttpServletResponse response,
			HttpServletRequest request) {
		//验证商品编号的值是否合法			
		if(StringUtil.isBlank(productImpression.getPiBasicId())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("pi.basic.id")));
			return;			
		}
		if(!StringUtil.checkLength(productImpression.getPiBasicId()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("pi.basic.id"), "1", "10"));
			return;			
		}
		//验证印象的值是否合法			
		if(StringUtil.isBlank(productImpression.getPiTitle())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("pi.title")));
			return;			
		}
		if(!StringUtil.checkLength(productImpression.getPiTitle()+"", 1, 255)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("pi.title"), "1", "255"));
			return;			
		}
		//验证添加用户的值是否合法			
		if(StringUtil.isBlank(productImpression.getPiPeopleId())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("pi.people.id")));
			return;			
		}
		if(!StringUtil.checkLength(productImpression.getPiPeopleId()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("pi.people.id"), "1", "10"));
			return;			
		}
		//验证数量的值是否合法			
		if(StringUtil.isBlank(productImpression.getPiAmount())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("pi.amount")));
			return;			
		}
		if(!StringUtil.checkLength(productImpression.getPiAmount()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("pi.amount"), "1", "10"));
			return;			
		}
		//验证添加时间的值是否合法			
		if(StringUtil.isBlank(productImpression.getPiDatetime())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("pi.datetime")));
			return;			
		}
		if(!StringUtil.checkLength(productImpression.getPiDatetime()+"", 1, 19)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("pi.datetime"), "1", "19"));
			return;			
		}
		productImpressionBiz.updateEntity(productImpression);
		this.outJson(response, true);
	}
	
		
}
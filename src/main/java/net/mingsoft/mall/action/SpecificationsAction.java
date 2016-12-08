package net.mingsoft.mall.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.basic.constant.Const;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.mall.biz.ISpecificationsBiz;
import net.mingsoft.mall.constant.ModelCode;
import net.mingsoft.mall.entity.SpecificationsEntity;

/**
 * 
 * <p>
 * <b>铭飞科技-商品</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 *
 * @author 成卫雄
 *                QQ:330216230
 *
 * <p>
 * Comments:商品规格控制层
 * </p>
 *
 * <p>
 * Create Date:2014-11-26
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Controller
@RequestMapping("/${managerPath}/mall/specifications")
public class SpecificationsAction extends BaseAction{
	
	/**
	 * 注入规格业务层
	 */
	@Autowired
	private ISpecificationsBiz specificationsBiz;
	
	/**
	 * 规格列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(ModelMap model,HttpServletRequest request){
		int appId = this.getAppId(request);
		String pageNo = request.getParameter("pageNo");
		if(StringUtil.isBlank(pageNo)){
			pageNo = "1";
		}
		int count = this.specificationsBiz.queryCountByAppId(appId);
		PageUtil page = new PageUtil(StringUtil.string2Int(pageNo),count,this.getUrl(request)+"/manager/mall/specifications/list.do");
		List<SpecificationsEntity> list = this.specificationsBiz.queryPageByAppId(appId, page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return view("/mall/specifications/specifications_list");
	}
	
	/**
	 * 新增商品规格
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	public void save(@ModelAttribute SpecificationsEntity specifications,HttpServletRequest request,HttpServletResponse response){
		if(specifications == null){
			this.outJson(response,ModelCode.MALL_SPECIFICATIONS, false);
			return ;
		}
		//验证前端提交数据
		if(!this.checkForm(specifications, response)){
			return ;
		};
		
		//获取加载APPid
		int appId = this.getAppId(request);
		specifications.setSpecificationsAppId(appId);
		
		this.specificationsBiz.saveEntity(specifications);
		if(specifications.getSpecificationsId() > 0){
			//新增成功
			this.outJson(response, ModelCode.MALL_SPECIFICATIONS, true,JSONObject.toJSONString(specifications));
		}else{
			//新增失败
			this.outJson(response, ModelCode.MALL_SPECIFICATIONS, false);
		}
	}
	
	/**
	 * 更新商品规格
	 * @param specifications
	 * @param request
	 * @param response
	 */
	@RequestMapping("/update")
	public void update(@ModelAttribute SpecificationsEntity specifications,HttpServletRequest request,HttpServletResponse response){
		if(specifications == null){
			this.outJson(response, ModelCode.MALL_SPECIFICATIONS, false);
			return ;
		}	
		//验证前端提交数据
		if(!this.checkForm(specifications, response)){
			return ;
		};
		
		if(!(specifications.getSpecificationsId() > 0)){
			this.outJson(response, ModelCode.MALL_SPECIFICATIONS, false);
			return ;
		}
		
		//执行更新
		this.specificationsBiz.updateEntity(specifications);
		this.outJson(response, ModelCode.MALL_SPECIFICATIONS,true);
	}
	
	/**
	 * 删除规格
	 * @param request
	 * @param response
	 */
	@RequestMapping("/{specificationsId}/delete")
	public void delete(@PathVariable("specificationsId")Integer specificationsId,HttpServletRequest request,HttpServletResponse response){
		if(!StringUtil.isInteger(specificationsId)){
			this.outJson(response, ModelCode.MALL_SPECIFICATIONS,false);
			return ;
		}
		this.specificationsBiz.deleteEntity(specificationsId);
		this.outJson(response, ModelCode.MALL_SPECIFICATIONS,true);
	}
	
	/**
	 * 获取规格列表,返回JSON数据
	 * @param request
	 * @param response
	 */
	@RequestMapping("/listByAjax")
	public void listByAjax(HttpServletRequest request,HttpServletResponse response){
		int appId = this.getAppId(request);
		List<SpecificationsEntity> list = this.specificationsBiz.queryPageByAppId(appId,null);
		if(list != null && list.size() > 0){
			this.outJson(response, ModelCode.MALL_SPECIFICATIONS,true,JSONObject.toJSONString(list));
		}else{
			this.outJson(response, ModelCode.MALL_SPECIFICATIONS,false);
		}
	}
	
	/**
	 * 验证前端提交数据
	 * @param specifications 规格实体
	 * @param response
	 * @return
	 */
	public boolean checkForm(SpecificationsEntity specifications, HttpServletResponse response){
		//判断产品规格的标题是否介于1-300之间
		if(!StringUtil.checkLength(specifications.getSpecificationsName(), 1, 12)){
			this.outJson(response, ModelCode.MALL_SPECIFICATIONS, false, getResString("err.length","SpecificationsName","1","12"));
			return false;
		}
		//判断产品规格型号的长度不能超过200
		if(specifications.getSpecificationsField() != null && specifications.getSpecificationsField().length() > 200){
			this.outJson(response, ModelCode.MALL_SPECIFICATIONS, false, getResString("err.length","specificationsName","0","200"));
			return false;			
		}
		return true;
	}
	
	@RequestMapping("/add")
	public String add(){
		return view("/mall/specifications/IProductSpecificationsInventory");
	}
	
}

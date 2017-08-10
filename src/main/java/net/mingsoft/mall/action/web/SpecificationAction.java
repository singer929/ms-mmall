package net.mingsoft.mall.action.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.mingsoft.mall.biz.ISpecificationBiz;
import net.mingsoft.mall.entity.SpecificationEntity;

import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;
import net.mingsoft.basic.util.BasicUtil;
	
/**
 * 默认规格数据管理控制层
 * @author 伍晶晶
 * @version 
 * 版本号：100<br/>
 * 创建日期：2017-8-8 15:18:35<br/>
 * 历史修订：<br/>
 */
@Controller("webSpecificationAction")
@RequestMapping("/mall/specification")
public class SpecificationAction extends net.mingsoft.mall.action.BaseAction{
	
	/**
	 * 注入默认规格数据业务层
	 */	
	@Autowired
	private ISpecificationBiz specificationBiz;
	
	
	/**
	 * 查询默认规格数据列表
	 * @param categoryId 栏目Id
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
	@RequestMapping("/queryByCategoryId")
	@ResponseBody
	public void queryByCategoryId(@RequestParam int categoryId, HttpServletResponse response, HttpServletRequest request) {
		if(categoryId <= 0){
			this.outJson(response, false);
		}
		SpecificationEntity specification = new SpecificationEntity();
		specification.setSpecificationCategoryId(categoryId);
		List specificationList = specificationBiz.query(specification);
//		for(int i = 0 ;i<specificationList.size();i++){
//			BasicUtil.getIds(specificationList.get(i).)
//		}
//		Map DefaultField =new HashMap();
//		DefaultField.put("specificationDefaultFields", value)
		
	}
	
		
}
package net.mingsoft.mall.action.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import net.mingsoft.mall.biz.ISpecificationBiz;
import net.mingsoft.mall.entity.SpecificationEntity;
	
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
	@PostMapping("/queryByCategoryId")
	@ResponseBody
	public void queryByCategoryId(@RequestParam int categoryId, HttpServletResponse response, HttpServletRequest request) {
		if(categoryId <= 0){
			this.outJson(response, false);
		}
		SpecificationEntity specification = new SpecificationEntity();
		specification.setSpecificationCategoryId(categoryId);
		List specificationList = specificationBiz.query(specification);
		List<SpecificationEntity> specificationEntityList = new ArrayList<SpecificationEntity>();
		List<Map> List = new ArrayList<Map>();
		for(int i = 0 ;i<specificationList.size();i++){
			//转实体，获取规格名称
			SpecificationEntity temp = (SpecificationEntity) specificationList.get(i);
			Map tempMap =new HashMap();
			tempMap.put("specificationName", temp.getSpecificationName());
			//切割默认值组成数组
			String[] defaultFields = temp.getSpecificationDefaultFields().split(",");
			List<Map> specificationDefaultFields = new ArrayList<Map>();
			for(int j=0; j < defaultFields.length; j++){
				//获取默认规格参数，组成list
				Map tempDefaultField =new HashMap();
				tempDefaultField.put("specificationDefaultField", defaultFields[j]);
				specificationDefaultFields.add(tempDefaultField);
			}
			tempMap.put("specificationDefaultFields", specificationDefaultFields);
			List.add(tempMap);
		}
		String jsonStr = JSONArray.toJSONString(List);
		this.outJson(response,jsonStr); 
	}
	
		
}
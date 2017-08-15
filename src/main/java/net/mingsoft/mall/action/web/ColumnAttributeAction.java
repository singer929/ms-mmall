package net.mingsoft.mall.action.web;

import java.util.ArrayList;
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
import net.mingsoft.mall.biz.IColumnAttributeBiz;
import net.mingsoft.mall.entity.ColumnAttributeEntity;

import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;
import net.mingsoft.basic.util.BasicUtil;
	
/**
 * 默认规格数据管理控制层
 * @author 伍晶晶
 * @version 
 * 版本号：0.0<br/>
 * 创建日期：2017-8-15 14:29:39<br/>
 * 历史修订：<br/>
 */
@Controller("webColumnAttributeAction")
@RequestMapping("/mall/columnAttribute")
public class ColumnAttributeAction extends net.mingsoft.mall.action.BaseAction{
	
	/**
	 * 注入默认规格数据业务层
	 */	
	@Autowired
	private IColumnAttributeBiz columnAttributeBiz;
	
	
	/**
	 * 查询默认规格数据列表
	 * @param categoryId 栏目Id
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>[<br/>
	 * { <br/>
	 * caId: 规格id 主键<br/>
	 * caName: 规格名称<br/>
	 * caCategoryId: 分类Id<br/>
	 * caFields: 默认的字段<br/>
	 * }<br/>
	 * ]</dd><br/>	 
	 */
	@PostMapping("/queryByCategoryId")
	@ResponseBody
	public void queryByCategoryId(@RequestParam int categoryId, HttpServletResponse response, HttpServletRequest request) {
		if(categoryId <= 0){
			this.outJson(response, false);
		}
		String jsonStr = columnAttributeBiz.queryByCategoryId(categoryId);
		this.outJson(response,jsonStr); 
	}
	
		
}
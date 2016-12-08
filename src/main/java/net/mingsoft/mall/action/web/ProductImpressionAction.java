package net.mingsoft.mall.action.web;

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

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.mingsoft.mall.biz.IProductImpressionBiz;
import net.mingsoft.mall.entity.ProductImpressionEntity;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;
import net.mingsoft.basic.util.BasicUtil;

/**
 * 商品印象管理控制层
 * 
 * @author 铭飞开发团队
 * @version 版本号：1.0.0<br/>
 *          创建日期：<br/>
 *          历史修订：<br/>
 */
@Controller("webProductImpressionAction")
@RequestMapping("/mall/productImpression")
public class ProductImpressionAction extends net.mingsoft.mall.action.BaseAction {

	/**
	 * 注入商品印象业务层
	 */
	@Autowired
	private IProductImpressionBiz productImpressionBiz;

	/**
	 * 查询商品印象列表
	 * 
	 * @param productImpression
	 *            商品印象实体 <i>productImpression参数包含字段信息参考：</i><br/>
	 *            piId <br/>
	 *            piBasicId 商品编号<br/>
	 *            piTitle 印象<br/>
	 *            piPeopleId 添加用户<br/>
	 *            piAmount 数量<br/>
	 *            piDatetime 添加时间<br/>
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            [<br/>
	 *            { <br/>
	 *            piId: <br/>
	 *            piBasicId: 商品编号<br/>
	 *            piTitle: 印象<br/>
	 *            piPeopleId: 添加用户<br/>
	 *            piAmount: 数量<br/>
	 *            piDatetime: 添加时间<br/>
	 *            }<br/>
	 *            ]<br/>
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(@ModelAttribute ProductImpressionEntity productImpression, HttpServletResponse response,
			HttpServletRequest request, ModelMap model) {
		BasicUtil.startPage();
		List productImpressionList = productImpressionBiz.query(productImpression);
		BasicUtil.endPage(productImpressionList);
		this.outJson(response, JSONArray.toJSONStringWithDateFormat(productImpressionList, "yyyy-MM-dd"));
	}

}
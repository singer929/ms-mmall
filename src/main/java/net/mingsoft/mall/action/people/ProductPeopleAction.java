package net.mingsoft.mall.action.people;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.mingsoft.base.filter.DateValueFilter;
import com.mingsoft.base.filter.DoubleValueFilter;
import com.mingsoft.people.action.BaseAction;
import com.mingsoft.people.constant.e.SessionConstEnum;

import net.mingsoft.basic.bean.ListBean;
import net.mingsoft.basic.biz.IPeopleBiz;
import net.mingsoft.basic.entity.PeopleEntity;
import net.mingsoft.basic.util.BasicUtil;

@Controller("ProductPeopleAction")
@RequestMapping("/people/mall/productPeople")
public class ProductPeopleAction extends BaseAction{
	
	/**
	 * 注入通用用户与信息一对多表业务层
	 */	
	@Resource(name="basicPeopleBizImpl")
	private IPeopleBiz peopleBiz;
	
	
	/**
	 * 查询商品浏览记录
	 * @param people 通用用户与信息一对多表实体
	 * <i>people参数包含字段信息参考：</i><br/>
	 * appId 站点id<br/>
	 * modelId 模块id<br>
	 * peopleId 用户编号<br/>
	 *  "page":{"endRow": 2, <br/>
	 *            "firstPage": 1, <br/>
	 *            "hasNextPage": true存在下一页false不存在, <br/>
	 *            "hasPreviousPage": true存在上一页false不存在, <br/>
	 *            "isFirstPage": true是第一页false不是第一页, <br/>
	 *            "isLastPage": true是最后一页false不是最后一页, <br/>
	 *            "lastPage": 最后一页的页码, <br/>
	 *            "navigatePages": 导航数量，实现 1...5.6.7....10效果, <br/>
	 *            "navigatepageNums": []导航页码集合, <br/>
	 *            "nextPage": 下一页, <br/>
	 *            "pageNum": 当前页码, <br/>
	 *            "pageSize": 一页显示数量, <br/>
	 *            "pages": 总页数, <br/>
	 *            "prePage": 上一页, <br/>
	 *            "size": 总记录, <br/>
	 *            "startRow": , <br/>
	 *            "total":总记录数量}<br/>
	 *            }<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>[<br/>
	 * { <br/>
	 * 商品信息<br/>
	 * basicTitle: 商品标题<br/>
	 * basicThumbnails: 商品缩略图<br/>
	 * productLinkUrl: 商品链接<br/>
	 * productPrice: 商品价格<br/> 
	 * productSale: 商品销量<br/>
	 * basicDescription: 商品描述等<br/>
	 * }<br/>
	 * ]</dd><br/>	 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		PeopleEntity people = (PeopleEntity) this.getSession(request, SessionConstEnum.PEOPLE_SESSION);
		if(people == null){
			this.outJson(response, false);
			return;
		}
		int modelId = BasicUtil.getModeld();
		BasicUtil.startPage();
		List productList = peopleBiz.queryByPeople(BasicUtil.getAppId(), modelId, people.getBpPeopleId());
		BasicUtil.endPage(productList);
		PageInfo page = BasicUtil.endPage(productList);
		ListBean _list = new ListBean(productList, page);
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(_list,new DoubleValueFilter(),new DateValueFilter("yyyy-MM-dd")));
	}
}

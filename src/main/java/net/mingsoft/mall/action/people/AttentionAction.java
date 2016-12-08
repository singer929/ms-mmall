package net.mingsoft.mall.action.people;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.mingsoft.base.entity.ListJson;
import com.mingsoft.base.filter.DateValueFilter;
import com.mingsoft.base.filter.DoubleValueFilter;
import com.mingsoft.people.action.BaseAction;
import com.mingsoft.people.entity.PeopleEntity;
import com.mingsoft.util.PageUtil;

import net.mingsoft.attention.constant.ModelCode;
import net.mingsoft.attention.constant.e.AttentionTypeEnum;
import net.mingsoft.attention.entity.BasicAttentionEntity;
import net.mingsoft.basic.bean.ListBean;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.mall.biz.IAttentionBiz;
import net.mingsoft.mall.entity.ProductEntity;

/**
 * <p>
 * <b>铭飞-科技</b>
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2015 - 2016
 * </p>
 * 
 * @author 石马人山
 * 
 * @version 300-001-001
 * 
 *
 *          <p>
 *          Create Date:2015-6-17 上午11:50:18
 *          </p>
 * 
 *          <p>
 *          Modification history:
 *          </p>
 */
@Controller("mallPeopleAttention")
@RequestMapping("/people/mall/attention")
public class AttentionAction extends BaseAction {

	/**
	 * 注入关注业务层
	 */
	@Resource(name = "mallAttentionBiz")
	private IAttentionBiz attentionBiz;

	/**
	 * 商品收藏&关注列表
	 * 
	 * @param attentionEntity
	 *            <i>basicAttention参数包含字段信息参考：</i><br/>
	 *            basicAttentionType 关注类型 具体平台也可以根据自身的规则定义 ，<br/>
	 *            paveNo 页码<br/>
	 *            pageSize 一页显示数量 一页显示数量
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            { "list": [<br/>
	 *            {<br/>
	 *            "basicPic": "商品缩略图", <br/>
	 *            "basicTitle": "商品标题", <br/>
	 *            "productLinkUrl": "商品链接地址", <br/>
	 *            "productPrice": 商家价格, <br/>
	 *            "productStock": 商品库存<br/>
	 *            "basicComment": 评论数, <br/>
	 *            "basicCollect": 收藏数量, <br/>
	 *            "basicHit": 点数量, <br/>
	 *            "basicAppId": 1, <br/>
	 *            "basicCategoryId": 160, <br/>
	 *            "basicDateTime": 1468568887000,<br/>
	 *            "basicId": 244, <br/>
	 *            "basicModelId": 97, <br/>
	 *            "basicPeopleId": 0, <br/>
	 *            "basicShare": 0, <br/>
	 *            "basicSort": 0, <br/>
	 *            "basicThumbnails": "/upload/mall/product/1/1468568853464.jpg",
	 *            <br/>
	 *            "basicTypeIds": [ ], <br/>
	 *            "basicUpdateTime": shan, <br/>
	 *            "column": {}, <br/>
	 *            "productAppId": 1, <br/>
	 *            "productBrand": 0, <br/>
	 *            "productCode": "PPCHUADE005", <br/>
	 *            "productCostPrice": 308, "productId": 0, <br/>
	 *            "productSale": 0, <br/>
	 *            "productShelf": -1, <br/>
	 *            "productSpecificationId": 0, <br/>
	 *            "productSpecificationPrice": 0, <br/>
	 *            "productSpecificationStock": 0, <br/>
	 *            }],<br/> "page":{"endRow": 2, <br/>
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
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public void list(@ModelAttribute BasicAttentionEntity attentionEntity, HttpServletRequest request,
			HttpServletResponse response) {
		// 查询关注的列表
		attentionEntity.setBasicAttentionPeopleId(this.getPeopleBySession().getPeopleId());
		attentionEntity.setBasicAttentionAppId(BasicUtil.getAppId());
		BasicUtil.startPage();
		List<ProductEntity> products = this.attentionBiz.query(attentionEntity);
		PageInfo page = BasicUtil.endPage(products);
		ListBean _list = new ListBean(products, page);
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(_list,new DoubleValueFilter(),new DateValueFilter("yyyy-MM-dd")));
	}

}

package net.mingsoft.mall.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingsoft.basic.action.BasicAction;

import net.mingsoft.mall.search.IProductSearch;


/**
 * 
 * 商城搜索引擎控制层，主页负责同步商品数据
 * @author 铭飞开发团队
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2017年7月27日<br/>
 * 历史修订：<br/>
 */
@Controller("mallSearch")
@RequestMapping("/${managerPath}/mall/search")
public class SearchAction extends BasicAction {

	private IProductSearch productSearch;
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@RequestMapping("/config")
	public String config() {
		return view("/mall/search/config");
	}
	
	@RequestMapping("/sync")
	@ResponseBody
	public void sync() {
		//读取所有商品数据，需要在dao对于编写查询 同时 返回类型list<BaseMapping>
		//调用el工具类的批量更新方法
	}	
}

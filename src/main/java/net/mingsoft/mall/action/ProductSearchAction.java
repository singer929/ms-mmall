package net.mingsoft.mall.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.mingsoft.mall.biz.IProductSearchBiz;
import net.mingsoft.mall.search.IProductSearch;
import net.mingsoft.mall.search.mapping.ProductMapping;

@Controller
@RequestMapping("/${managerPath}/mall/productSearch")
public class ProductSearchAction extends BaseAction {
	
	/**
	 * 产品dao处理层
	 */
	@Autowired
	private IProductSearchBiz productSearchBiz;
	
	@Autowired
	private IProductSearch productSearch;
	
	@RequestMapping("/index")
	public void index(HttpServletRequest request, HttpServletResponse response) {
		List<ProductMapping> productMapping =  productSearchBiz.queryForSearchMapping();
		for(int i = 0 ;i < productMapping.size(); i++){
			productSearch.save(productMapping.get(i));
		}
		
		this.outJson(response, productMapping);
	}

	
}

package net.mingsoft.mall.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.mingsoft.util.StringUtil;

import net.mingsoft.mall.biz.IProductSpecBiz;
import net.mingsoft.mall.biz.IProductSpecDetailBiz;
import net.mingsoft.mall.entity.ProductEntity;
import net.mingsoft.mall.entity.ProductSpecDetailEntity;
import net.mingsoft.mall.entity.ProductSpecEntity;

@Controller
@RequestMapping("/${managerPath}/mall/test")
public class TestAction extends BaseAction {

	@Autowired
	IProductSpecBiz specBiz;
	
	@Autowired
	IProductSpecDetailBiz detailBiz;
	
	@RequestMapping("/index")
	public String index(String jsonStr, HttpServletRequest request) {
		
		SaveData1 data = null;
		if (!StringUtil.isBlank(jsonStr)){
			data = JSON.parseObject(jsonStr, SaveData1.class);
		}
		
		//String key = request.getParameter("key");
		//LOG.debug("====" +key+ "===========" + data.getA() + "," + data.getB());
		if (data != null && data.getDetailList() != null && data.getProductSpecList() != null) {
			specBiz.saveEntitiesByProductId(220, data.getProductSpecList());
			detailBiz.saveEntitiesByProductId(220, data.getDetailList());
		}
		
		return view("/mall/test");
	}
}

class TestData {
	private String a;
	
	private String b;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}
}

/**
 * 保存数据的结构数据结构
 */
class SaveData1{
	
	private ProductEntity product;
	private List<ProductSpecEntity> productSpecList;
	private List<ProductSpecDetailEntity> detailList;
	
	public ProductEntity getProduct() {
		return product;
	}
	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	public List<ProductSpecEntity> getProductSpecList() {
		return productSpecList;
	}
	public void setProductSpecList(List<ProductSpecEntity> productSpecList) {
		this.productSpecList = productSpecList;
	}
	public List<ProductSpecDetailEntity> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<ProductSpecDetailEntity> detailList) {
		this.detailList = detailList;
	}
}


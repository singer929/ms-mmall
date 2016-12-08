package net.mingsoft.mall.parser.impl;

import com.mingsoft.parser.IParser;

/**
 * 
 * 解析商品原价标签{ms:field.costprice/}
 * @author 史爱华
 * @version 
 * 版本号：【100-000-000】
 * 创建日期：2015年9月4日 
 * 历史修订：
 */
public class ProductCostPriceParser  extends IParser{
	/**
	 * 商品的编码标签
	 */
	private final static String PRODUCT_FIELD_COSTPRICE="\\{ms:field.costprice/\\}";
	
	/**
	 * 构造标签的属性
	 * @param htmlContent 原HTML代码
	 * @param newContent 替换的内容
	 */
	public ProductCostPriceParser(String htmlContent,String newContent){
		super.htmlCotent = htmlContent;
		super.newCotent = newContent;
	}
	@Override
	public String parse() {
		// TODO Auto-generated method stub
		return replaceAll(PRODUCT_FIELD_COSTPRICE);
	}

}

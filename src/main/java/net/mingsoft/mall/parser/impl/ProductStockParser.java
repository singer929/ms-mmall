package net.mingsoft.mall.parser.impl;

import com.mingsoft.parser.IParser;

public class ProductStockParser  extends IParser {
	
	/**
	 * 商品的库存标签
	 */
	private final static String PRODUCT_FIELD_STOCk="\\{ms:field.stock/\\}";
	
	/**
	 * 构造标签的属性
	 * @param htmlContent 原HTML代码
	 * @param newContent 替换的内容
	 */
	public ProductStockParser(String htmlContent,String newContent){
		super.htmlCotent = htmlContent;
		super.newCotent = newContent;
	}
	
	@Override
	public String parse() {
		// TODO Auto-generated method stub
		return replaceAll(PRODUCT_FIELD_STOCk);
	}

}

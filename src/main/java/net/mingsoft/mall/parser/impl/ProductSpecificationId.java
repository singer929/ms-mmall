package net.mingsoft.mall.parser.impl;

import com.mingsoft.parser.IParser;

public class ProductSpecificationId extends IParser {

	/**
	 * 商品价格
	 */
	private final static String PRODUCT_FIELD_PRICE="\\{ms:field.specification/\\}";
	
	/**
	 * 构造标签的属性
	 * @param htmlContent 原HTML代码
	 * @param newContent 替换的内容
	 */
	public ProductSpecificationId(String htmlContent,String newContent){
		super.htmlCotent = htmlContent;
		super.newCotent = newContent;
	}
	@Override
	public String parse() {
		// TODO Auto-generated method stub
		return replaceAll(PRODUCT_FIELD_PRICE);
	}

}

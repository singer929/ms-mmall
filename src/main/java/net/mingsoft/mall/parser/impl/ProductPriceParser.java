package net.mingsoft.mall.parser.impl;

import com.mingsoft.parser.IParser;

/**
 * 商品价格标签（单标签）
 * {ms:field.}
 * @author 史爱华
 * 技术支持：景德镇铭飞科技
 * 官网：www.ming-soft.com
 */
public class ProductPriceParser  extends IParser {
	
	/**
	 * 商品价格
	 */
	private final static String PRODUCT_FIELD_PRICE="\\{ms:field.price/\\}";
	
	/**
	 * 构造标签的属性
	 * @param htmlContent 原HTML代码
	 * @param newContent 替换的内容
	 */
	public ProductPriceParser(String htmlContent,String newContent){
		super.htmlCotent = htmlContent;
		super.newCotent = newContent;
	}
	@Override
	public String parse() {
		// TODO Auto-generated method stub
		return replaceAll(PRODUCT_FIELD_PRICE);
	}
	
}

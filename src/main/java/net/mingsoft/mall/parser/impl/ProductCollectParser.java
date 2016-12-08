package net.mingsoft.mall.parser.impl;

import com.mingsoft.parser.IParser;


/**
 * 商品收藏标签（单标签）
 * {ms:field.collect}
 * @author 史爱华
 * 技术支持：景德镇铭飞科技
 * 官网：www.ming-soft.com
 */
public class ProductCollectParser extends IParser {
	/**
	 * 商品收藏
	 */
	private final static String PRODUCT_FIELD_COLLECT="\\{ms:field.collect/\\}";
	
	/**
	 * 构造标签的属性
	 * @param htmlContent 原HTML代码
	 * @param newContent 替换的内容
	 */
	public ProductCollectParser(String htmlContent,String newContent){
		super.htmlCotent = htmlContent;
		super.newCotent = newContent;
	}
	@Override
	public String parse() {
		// TODO Auto-generated method stub
		return replaceAll(PRODUCT_FIELD_COLLECT);
	}
	
	
	
}

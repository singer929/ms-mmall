package net.mingsoft.mall.parser.impl;

import com.mingsoft.parser.IParser;


/**
 * 商品缩略图(单标签)	
 * 商品内容标签
 * {ms:field.litpic/}
 * @author 史爱华
 * 技术支持：景德镇铭飞科技
 * 官网：www.ming-soft.com
 */
public class ProductLitpicParser extends IParser {

	/**
	 * 文章缩略标签
	 */
	private final static String PRODUCT_LITPIC_FIELD="\\{ms:field.litpic/\\}";
	
	/**
	 * 构造标签的属性
	 * @param htmlContent原HTML代码
	 * @param newContent替换的内容
	 */
	public ProductLitpicParser(String htmlContent,String newContent){
		super.htmlCotent = htmlContent;
		super.newCotent = newContent;
	}
	
	@Override
	public String parse() {
		// TODO Auto-generated method stub
		return super.replaceAll(PRODUCT_LITPIC_FIELD);
	}

}

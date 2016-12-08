package net.mingsoft.mall.parser.impl;

import com.mingsoft.parser.IParser;

/**
 * 内容标题(单标签)
 * 商品id标签
 * {ms:field.id/}
 * @author 史爱华
 * 技术支持：景德镇铭飞科技
 * 官网：www.ming-soft.com
 */
public class ProductIdParser extends IParser  {
	/**
	 * 商品id标签
	 */
	private final static String PRODUCT_TITLE_FIELD="\\{ms:field.id/\\}";
	/**
	 * 构造标签的属性
	 * @param htmlContent原HTML代码
	 * @param newContent替换的内容
	 */
	public ProductIdParser(String htmlContent,String newContent){
		super.htmlCotent = htmlContent;
		super.newCotent = newContent;
	}
	
	@Override
	public String parse() {
		// TODO Auto-generated method stub
		return super.replaceAll(PRODUCT_TITLE_FIELD);
	}

}

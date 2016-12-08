package net.mingsoft.mall.parser.impl;

import com.mingsoft.parser.IParser;

/**
 * 商品链接(单标签)	
 * 商品内容标签
 * {ms:field.link/}
 * @author 史爱华
 * 技术支持：景德镇铭飞科技
 * 官网：www.ming-soft.com
 */
public class ProductLinkParser extends IParser {

	/**
	 * 文章内容连接标签
	 */
	private final static String 	PRODUCT_LINK_FIELD="\\{ms:field.link/\\}";
	
	
	/**
	 * 构造标签的属性
	 * @param htmlContent原HTML代码
	 * @param newContent替换的内容
	 */
	public ProductLinkParser(String htmlContent,String newContent){
		super.htmlCotent = htmlContent;
		super.newCotent = newContent;
	}
	
	@Override
	public String parse() {
		// TODO Auto-generated method stub
		return super.replaceAll(PRODUCT_LINK_FIELD);
	}

}

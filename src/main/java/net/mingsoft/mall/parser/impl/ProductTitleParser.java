package net.mingsoft.mall.parser.impl;

import com.mingsoft.parser.IParser;

/**
 * 内容标题(单标签)
 * 商品标题标签
 * {ms:field.title/}
 * @author 史爱华
 * 技术支持：景德镇铭飞科技
 * 官网：www.ming-soft.com
 */
public class ProductTitleParser  extends IParser {
	
	/**
	 * 文章标题标签
	 */
	private final static String PRODUCT_TITLE_FIELD="\\{ms:field.title/\\}";
	
	/**
	 * 构造标签的属性
	 * @param htmlContent 原HTML代码
	 * @param newContent 替换的内容
	 */
	public ProductTitleParser(String htmlContent,String newContent){
		super.htmlCotent = htmlContent;
		super.newCotent = newContent;
	}
	@Override
	public String parse() {
		// TODO Auto-generated method stub
		return super.replaceAll(PRODUCT_TITLE_FIELD);
	}

}

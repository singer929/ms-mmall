package net.mingsoft.mall.parser.impl;


import com.mingsoft.parser.IParser;
import com.mingsoft.util.StringUtil;

/**
 * 
 * 解析商品栏目id标签{ms:field.typeid/}
 * @author 史爱华
 * @version 
 * 版本号：【100-000-000】
 * 创建日期：2015年9月4日 
 * 历史修订：
 */
public class ProductTypeIdParser extends IParser {

	/**
	 * 商品所属栏目标题标签
	 */
	private final static String PRODUCT_TYPEID ="\\{ms:field.typeid(.*)?/\\}";
	
	/**
	 *商品栏目标签的属性 
	 */
	private  final static String TYPE = "type";
	
	/**
	 * 商品栏目标签的属性值。top表示取顶级栏目
	 */
	private final static String TYPE_TOP = "top";
	
	/**
	 * 构造标签的属性
	 * @param htmlContent原HTML代码
	 * @param newContent替换的内容
	 */
	public ProductTypeIdParser(String htmlContent,String newContent){
		super.htmlCotent = htmlContent;
		super.newCotent = newContent;
	}
	@Override
	public String parse() {
		// TODO Auto-generated method stub
		return super.replaceAll(PRODUCT_TYPEID);
	}
	
	/**
	 *  是否存在type=top的属性
	 * @return　true:存在 false:不存在
	 */
	public boolean  isTop() {
		String temp  = super.getProperty(PRODUCT_TYPEID).get(TYPE);
		if (StringUtil.isBlank(temp)) {
			return false;
		} else {
			return temp.equalsIgnoreCase(TYPE_TOP) ;
		}
	}

}

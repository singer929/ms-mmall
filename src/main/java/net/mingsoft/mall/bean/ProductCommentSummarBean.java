package net.mingsoft.mall.bean;

/**
 * 
 * 
 * 
 * <p>
 * <b>铭飞MS平台</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 * 
 * <p>
 * Company:景德镇铭飞科技有限公司
 * </p>
 * 
 * @author WTP
 * 
 * @version 
 * 
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments:  商品评论信息汇总
 * </p>
 *  
 * <p>
 * Create Date: 2017年2月28日
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
public class ProductCommentSummarBean {

	/**
	 * 评论总数
	 */
	private int commenTcount;
	
	/**
	 * 好评率
	 */
	private String goodRate;
	
	/**
	 * 好评总数 评分为4~5之间
	 */
	private int goodCount;
	
	/**
	 * 中评数,评分为3
	 */
	private int generalCount;
	
	/**
	 * 差评数,评分为1~2之间
	 */
	private int poorCount;

	public int getCommenTcount() {
		return commenTcount;
	}

	public void setCommenTcount(int commenTcount) {
		this.commenTcount = commenTcount;
	}

	public String getGoodRate() {
		return goodRate;
	}

	public void setGoodRate(String goodRate) {
		this.goodRate = goodRate;
	}

	public int getGoodCount() {
		return goodCount;
	}

	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
	}

	public int getGeneralCount() {
		return generalCount;
	}

	public void setGeneralCount(int generalCount) {
		this.generalCount = generalCount;
	}

	public int getPoorCount() {
		return poorCount;
	}

	public void setPoorCount(int poorCount) {
		this.poorCount = poorCount;
	}
	
	
}

package net.mingsoft.mall.entity;

import java.sql.Time;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.mingsoft.base.entity.BaseEntity;

/**
 * 
 *  <p>
 * <b>铭飞-BBS论坛平台</b>
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 * 
 * <p>
 * Company:景德镇铭飞科技有限公司
 * </p>
 * 
 * @author 郭鹏辉
 * 
 * @version 140-000-000
 * 
 *          <p>
 *          版权所有 铭飞科技
 *          </p>
 *
 *         @ClassName: PromotionEntity
 *
 * 	@Description: TODO 限时抢购
 *
 *          <p>
 *          Comments:  继承BaseEntity类
 *          </p>
 * 
 *          <p>
 *          CreatrDate:Jun 1, 2015 3:25:56 PM
 *          </p>
 * 
 *          <p>
 *          Modification history:
 *          </p>
 */
public class PromotionEntity extends BaseEntity {

	
	/**
	 *应用id
	 */
	private int promotionAppId;
	
	/**
	 *发布时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date promotionDateTime;
	
	/**
	 *结束日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date promotionEndDate;
	
	/**
	 *结束时间,不参与表结构
	 */
	private String promotionEndTime;
	
	
	/**
	 *促销编号
	 */
	private int promotionId;
	/**
	 *每天限制购买数量,不参与表结构
	 */
	private int  promotionLimitDay;

	/**
	 *每人限制购买数量,不参与表结构
	 */
	private int promotionLimitPeople;
	
	
	/**
	 * 产品id
	 */
	private int promotionProductId;
	
	/**
	 * 开始日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date promotionStartDate;

	/**
	 * 开始时间,不参与表结构
	 */
	private String promotionStartTime;
	
	/**
	 * 抢购主题
	 */
	private String promotionTitle;
	
	
	/**
	 * 大于0表示没有开始，小于0表示已经开始,主要提供流程判断使用
	 */
	private int startDay;
	

	/**
	 *大于0表示没有结束，小于0表示已经结束，主要提供流程判断使用
	 */
	private int endDay;
	
	/**
	 * 距离今天结束时间，计算规则=日期（今天）+promotionEndTime - (日期)今天时间new Date()，小于0表示已经结束
	 */
	private int todayOverTime;
	
	/**
	 * 距离今天开始时间，计算规则=日期（今天）+promotionStartTime - (日期)今天时间new Date()，小于0表示没有开始，小于0表示已经开始
	 */
	private int todayStartTime;
	

	/**
	 * 距离明天开始时间，计算规则= 日期（明天）+promotionStartTime - (日期)今天时间new Date()，
	 * 一般是（当天的活动结束）后需要显示明天的开始时间，
	 */
	private int tomorrowTime;
	
	/**
	 * 通过当前日期与活动的开始日期+开始时间进行比较，计算出距离活动开始还有多长时间，小于0表示还没有开始
	 * 与tomorrowTime不同，这个是计算整个活的开始时间，例如:活动开始时间是 2015-12-01，而当前时间是2015-09-01,表示活动还没有正式开始
	 */
	private int startTime;
	/**
	 * 通过当前日期与活动的结束日期+结束时间进行比较，计算出距离活动结束还有多长时间，小于0表示还没有结束
	 * 与todayOverTime不同，这个是计算整个活的结束时间，例如:活动结束时间是 2015-12-01，而当前时间是2015-09-01,表示活动还没有完全结束
	 */
	private int endTime;
	/**
	 * 当天销售量,不参与表结构
	 */
	private int daySales;
	
	/**
	 * 商品图片,不参与表结构
	 */
	private String pic;
	
	/**
	 * 产品栏目id,不参与表结构
	 */
	private int productCategoryId;
	
	/**
	 * 产品名称,不参与表结构
	 */
	private String productTitle;


	public int getTodayStartTime() {
		return todayStartTime;
	}

	public void setTodayStartTime(int todayStartTime) {
		this.todayStartTime = todayStartTime;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public int getDaySales() {
		return daySales;
	}
	
	public int getEndDay() {
		return endDay;
	}

	public String getPic() {
		return pic;
	}


	public int getProductCategoryId() {
		return productCategoryId;
	}
	

	public String getProductTitle() {
		return productTitle;
	}

	public int getPromotionAppId() {
		return promotionAppId;
	}
	
	public Date getPromotionDateTime() {
		return promotionDateTime;
	}

	public Date getPromotionEndDate() {
		return promotionEndDate;
	}


	public String getPromotionEndTime() {
		return promotionEndTime;
	}



	public int getPromotionId() {
		return promotionId;
	}


	



	public int getPromotionLimitDay() {
		return promotionLimitDay;
	}

	public int getPromotionLimitPeople() {
		return promotionLimitPeople;
	}

	public int getPromotionProductId() {
		return promotionProductId;
	}

	public Date getPromotionStartDate() {
		return promotionStartDate;
	}

	public String getPromotionStartTime() {
		return promotionStartTime;
	}



	public String getPromotionTitle() {
		return promotionTitle;
	}




	public int getStartDay() {
		return startDay;
	}



	public int getTodayOverTime() {
		return todayOverTime;
	}


	public int getTomorrowTime() {
		return tomorrowTime;
	}
	
	
	public void setDaySales(int daySales) {
		this.daySales = daySales;
	}


	public void setEndDay(int endDay) {
		this.endDay = endDay;
	}


	public void setPic(String pic) {
		this.pic = pic;
	}


	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}


	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}


	public void setPromotionAppId(int promotionAppId) {
		this.promotionAppId = promotionAppId;
	}


	public void setPromotionDateTime(Date promotionDateTime) {
		this.promotionDateTime = promotionDateTime;
	}


	public void setPromotionEndDate(Date promotionEndDate) {
		this.promotionEndDate = promotionEndDate;
	}


	public void setPromotionEndTime(String promotionEndTime) {
		this.promotionEndTime = promotionEndTime;
	}


	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}


	public void setPromotionLimitDay(int promotionLimitDay) {
		this.promotionLimitDay = promotionLimitDay;
	}


	public void setPromotionLimitPeople(int promotionLimitPeople) {
		this.promotionLimitPeople = promotionLimitPeople;
	}


	public void setPromotionProductId(int promotionProductId) {
		this.promotionProductId = promotionProductId;
	}




	public void setPromotionStartDate(Date promotionStartDate) {
		this.promotionStartDate = promotionStartDate;
	}


	public void setPromotionStartTime(String promotionStartTime) {
		this.promotionStartTime = promotionStartTime;
	}


	public void setPromotionTitle(String promotionTitle) {
		this.promotionTitle = promotionTitle;
	}


	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}


	public void setTodayOverTime(int todayOverTime) {
		this.todayOverTime = todayOverTime;
	}


	public void setTomorrowTime(int tomorrowTime) {
		this.tomorrowTime = tomorrowTime;
	}
	
	
	
}

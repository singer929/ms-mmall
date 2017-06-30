package net.mingsoft.mall.aop;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mingsoft.basic.aop.BaseAop;
import com.mingsoft.basic.biz.IBasicBiz;

import net.mingsoft.comment.bean.CommentSumeryBean;
import net.mingsoft.comment.biz.ICommentBiz;
import net.mingsoft.mall.biz.IProductBiz;
import net.mingsoft.mall.entity.OrderCommentEntity;
import net.mingsoft.mall.entity.ProductEntity;

/**
 * 铭飞评论插件
 * @author 铭飞开发团队
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2017年2月16日<br/>
 * 历史修订：<br/>
 */

@Component("mailOrderCommentAop")
@Aspect
public class CommentAop extends BaseAop {

	private IBasicBiz baicBiz;
	
	@Autowired
	private ICommentBiz commentBiz;
	@Autowired
	private IProductBiz productBiz;
	
	@Pointcut("execution(* net.mingsoft.comment.biz.impl.CommentBizImpl.saveComment(..))")
	public void save() {
	}

	/**
	 * 更新商品好评率
	 * @param jp
	 * @return
	 * @throws Throwable
	 */
	@Around("save()")
	public Object save(ProceedingJoinPoint jp) throws Throwable {
		OrderCommentEntity comment = this.getType(jp, OrderCommentEntity.class);
		Object obj = jp.proceed(); 
		if(comment != null && comment.getCommentBasicId() > 0) {
			CommentSumeryBean csb =  commentBiz.sumery(comment);
			ProductEntity product = (ProductEntity) productBiz.getEntity(comment.getCommentBasicId());
			product.setProductGood(csb.getCommentPointsCount()/(csb.getCommentCount()*5));
			productBiz.updateEntity(product);
		}
		return obj;
	}
	

}

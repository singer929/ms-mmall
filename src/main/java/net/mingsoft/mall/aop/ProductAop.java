package net.mingsoft.mall.aop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mingsoft.basic.aop.BaseAop;
import com.mingsoft.basic.biz.ICategoryBiz;
import com.mingsoft.basic.entity.CategoryEntity;
import com.mingsoft.util.StringUtil;

import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.basic.util.SpringUtil;
import net.mingsoft.mall.constant.ModelCode;
import net.mingsoft.mall.entity.ProductEntity;

@Component
@Aspect
public class ProductAop extends BaseAop {

	
	@Autowired
	private ICategoryBiz categoryBiz;
	
	@Pointcut("execution(* net.mingsoft.mall.action.ProductAction.save(..)) || execution(* net.mingsoft.mall.action.ProductAction.update(..))")
	public void saveOrUpdate() { 

	}

	@Around("saveOrUpdate()")
	public Object saveOrUpdate(ProceedingJoinPoint pjp) throws Throwable {
//		ProductEntity product = this.getType(pjp, ProductEntity.class);
//				if (!StringUtil.isBlank(product.getBasicType())) {
//					basicTypeBiz.deleteByBasicId(product.getBasicId());
//					int ids[]=StringUtil.stringsToInts(product.getBasicType().split(","));
//					for (int id:ids) {
//						basicTypeBiz.saveEntity(new BasicTypeEntity(product.getBasicId(),id));
//			}
//		}

		return pjp.proceed();
	}
	@Pointcut("execution(* net.mingsoft.mall.action.ProductAction.add(..)) || execution(* net.mingsoft.mall.action.ProductAction.edit(..))")
	public void addOrEdit() { 
		
	}
	
	@Around("addOrEdit()")
	public Object addOrEdit(ProceedingJoinPoint pjp) throws Throwable {
		BasicUtil.getModelCodeId(ModelCode.PRODUCT_PROPERTY);
		int mallTypemodelId = BasicUtil.getModelCodeId(net.mingsoft.mall.constant.ModelCode.PRODUCT_PROPERTY);
		List<CategoryEntity> mallTypeList = this.categoryBiz.queryByAppIdOrModelId(BasicUtil.getAppId(),mallTypemodelId);
		HttpServletRequest request = SpringUtil.getRequest();
		request.setAttribute("productTypes", mallTypeList);
		return pjp.proceed();
	}

}
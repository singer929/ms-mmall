package net.mingsoft.mall.biz;

import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.util.*;
import java.util.*;
import net.mingsoft.mall.entity.ColumnAttributeEntity;

/**
 * 默认规格数据业务
 * @author 伍晶晶
 * @version 
 * 版本号：0.0<br/>
 * 创建日期：2017-8-15 14:29:39<br/>
 * 历史修订：<br/>
 */
public interface IColumnAttributeBiz extends IBaseBiz {
	
	/**
	 * 通过分类Id查询该栏目的属性
	 * @param categoryId
	 * @return 该栏目下的属性值得json数据
	 */
	public String queryByCategoryId(int categoryId);
}
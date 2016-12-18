import org.activiti.engine.test.mock.MockServiceTask;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.sql.ast.expr.SQLSequenceExpr.Function;

import net.mingsoft.mall.biz.IProductSpecBiz;

public class TestProductSpec {

	@MockServiceTask
	IProductSpecBiz specBiz;
	
	@Before
	public void init(){
		
	}
	
	@Test
	public void test() {
		
		String str = specBiz.getDataStrByProductId(220);
		
		System.out.println(str);
	}

}

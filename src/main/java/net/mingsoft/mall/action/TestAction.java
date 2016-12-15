package net.mingsoft.mall.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/${managerPath}/mall/test")
public class TestAction extends BaseAction {

	@RequestMapping("/index")
	public String index(@ModelAttribute TestData data, HttpServletRequest request) {
		
		String key = request.getParameter("key");
		
		LOG.debug("====" +key+ "===========" + data.getA() + "," + data.getB());
		
		return view("/mall/test");
	}
}

class TestData {
	private String a;
	
	private String b;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}
}

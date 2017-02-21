$(function() {//检测页面是否为登录状态
	var thisHref = this.location.href;
	ms.load(["ms.people","ms","ms.mall"],function(mpeople,ms,mmall) {
		mpeople.checkLoginStatus(function(returnJson) {//检测是否登录
			if (returnJson.result)//如果已登录将会有以下操作
			{
				mpeople.people.user.info(function(returnJson) {
					$("#logLink").html("<a href='"+ms.base+"/people/center.do'>亲爱的"+returnJson.peopleName+",欢迎您!&nbsp;</a>");
				});
				$("#regLink").html('<a href="#" class="quitLogin">退出</a>');

				mmall.people.mall.cart.list("",function(json){
					$("#shoppingAmount").text(""+json.length+"");
				});
			}
		});
		
		$("#regLink").click(function() {//点击退出
			mpeople.people.quit(function() {
				$("#logLink").html("<a href='"+ms.base+"/login.do'>请登录</a>");
				$("#regLink").html("<a href='"+ms.base+"/register.do'>注册</a>");
			});
			location.href = ""+ms.base+"/login.do";
		});

		
	});
});

$(function(){//页面点击左侧菜单追加样式
	var curPage = $(".this-title").attr("data-id");
	$("#"+curPage+"").addClass("choose");
});

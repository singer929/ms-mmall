// JavaScript Document
$(function(){
	$("#message > input").click(function(){		//登录页，复选框事件
		var i = $(".error-messages").css("display");
		if($(".error-messages").css("display") === "block"){	//如果错误信息为显示状态，点击复选框都将隐藏错误信息
			$(".error-messages").hide();
			if ($("#checkBtn").get(0).checked) {	//如果复选框为选中状态：显示提示信息
				$(".tip-messages").show();
			}
		}
		else {	//如果提示信息为显示状态，点击复选框提示信息正常有无切换
			$(".tip-messages").toggle();
		}
	});

	$("input").focus(function(){	//输入框，获得焦点事件：改变其框颜色
		$(this).parent().css("border-color","#3aa2e4");
		$(this).css("border-color","#3aa2e4");
	});

	$("input").blur(function(){		//输入框，失去焦点时间：还原输入框颜色
		$(this).parent().css("border-color","#DDDDDD");
		$(this).css("border-color","#DDDDDD");
	});

	function reClass(){		//我的订单页，猜你喜欢换页。清除当前样式
	  $(".switch-page > a").removeClass("curr");
	  $(".guessing-goods-list > ul").hide();
	}

	$(".switch-page").delegate("a","mouseover",function(){		//我的订单页，猜你喜欢换页。为其动态换页
		reClass();
		$(this).addClass("curr");		//为鼠标覆盖的地方添加样式
		var page=$(this).text();		//获取当前是第几页
		$(".tabcon-"+page+"").show();	//让当前页的推荐商品显示
	});
});
$(function(){
	/*
	 * 商品映像处样式
	 * */
	$(".orderCo-w").delegate(".orderCo-tag-item","mouseover",function(){//鼠标经过添加红色样式
		$(this).addClass("orderCo-tag-red");
	})
	$(".orderCo-w").delegate(".orderCo-tag-item","mouseout",function(){//鼠标移开样式
		$(this).removeClass("orderCo-tag-red");
	})
	
	$(".orderCo-w").delegate(".orderCo-tag-item-a","mouseover",function(){//自定义标签 鼠标经过添加红色样式
		$(this).addClass("orderCo-tag-red");
		$(this).find(".orderCo-tag-item-span").css("color","#E4393C");
	})
	$(".orderCo-w").delegate(".orderCo-tag-item-a","mouseout",function(){//鼠标经过添加红色样式
		$(this).removeClass("orderCo-tag-red");
		$(this).find(".orderCo-tag-item-span").css("color","#666");
	})
	$(".orderCo-w").delegate(".orderCo-tag-item-a","click",function(){// 点击自定义标签输入文字
		$(this).find(".orderCo-tag-item-span").hide();
		$(this).find(".orderCo-tag-item-input").show().select();
	})
	$(".orderCo-w").delegate(".orderCo-tag-item-input","blur",function(){	// 当自定义标签的输入框失焦时
		var inputText = $(this).val();		//获取输入框的值
		if(inputText == ""){								//判断输入框值是否为空，为空时显示原来的自定义标签
			$(".orderCo-tag-item-span").show();				
			$(".orderCo-tag-item-input").hide();
		}else{											//否则在自定义标签前面追加刚刚输入的
			$(this).parent(".orderCo-tag-item-a").before("<a class='orderCo-tag-item-afe-a orderCo-tag-red orderCo-small'><i class='fa icon iconfont' style='font-size: 14px' id='orderCo-fa'></i><span class='custom-title'>"+inputText+"</span></a>");
			$(this).val("");			
			$(this).siblings(".orderCo-tag-item-span").show();
			$(this).hide();
		}
	})
	
	$(".orderCo-w").delegate(".orderCo-tag-item-afe-a","mouseover",function(){		//鼠标经过添加删除样式
		$(this).find("#orderCo-fa").show();
	});
	$(".orderCo-w").delegate(".orderCo-tag-item-afe-a","mouseout",function(){		//鼠标移开去除样式
		$(this).find("#orderCo-fa").hide();
	});	
	$(".orderCo-w").delegate("#orderCo-fa","click",function(){					//点击删除样式时把标签移除
		$(this).parent(".orderCo-tag-item-afe-a").remove();
	});
	$(".orderCo-w").delegate(".orderCo-tag-item-afe-a","click",function(){		// 鼠标点击添加样式
		$(this).toggleClass("orderCo-small");
		$(this).toggleClass("orderCo-tag-red");
	});
	$(".orderCo-w").delegate(".orderCo-tag-item","click",function(){// 点击自定义标签输入文字
		$(this).toggleClass("orderCo-small");
	})  	
	//星级评分
	var socer;
	$(".orderCo-w").delegate(".ordetCo-span-img","mouseover",function(){//鼠标上移事件
		if($(this).hasClass("star1") || $(this).prevAll().hasClass("star1")){
			socer =  $(this).parent().next().children().text();
		}else{
			socer = 0;
		}	
		$(this).addClass("star2");
		$(this).removeClass("remo");
		$(this).prevAll().addClass("star2");
		$(this).prevAll().removeClass("remo");
		var num = $(this).attr("data-id");
		$(this).parent().next().children().text(num).css("color","red");
		$(this).parent().next().css("color","red");
	})
	$(".orderCo-w").delegate(".ordetCo-sp-img > span","mouseout",function(){//鼠标移开事件
		var dss= $(this).prevAll().hasClass("star1");
		if($(this).hasClass("star1") || $(this).prevAll().hasClass("star1")){
			$(".ordetCo-span-img").removeClass("star2")
			$(this).parent().next().children().text(socer);
		}else{
			$(".ordetCo-span-img").removeClass("star2")
			$(this).parent().next().children().text(0).css("color","#999");
			$(this).parent().next().css("color","#999");
		}	
	})	
	$(".orderCo-w").delegate(".ordetCo-span-img","click",function(){//点击星级事件
		$(this).addClass("star1");
		$(this).removeClass("remo");
		$(this).prevAll().addClass("star1");
		$(this).prevAll().removeClass("remo");
		$(this).nextAll().removeClass("star1");
		$(this).nextAll().addClass("remo");
		var sum = $(this).attr("data-id");
		socer = $(this).attr("data-id");
		$(this).parent().next().children().text(sum);
	})
})

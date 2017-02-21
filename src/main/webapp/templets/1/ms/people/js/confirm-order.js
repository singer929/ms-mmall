 //确认订单
$(function(){
//	//更多收货地址
	$(".more-address").click(function () {
		$(".more-address").toggle();
		$(".retract-address").toggle();
	});
	//收货人点击选中
	$("#user-name-hide-two p").click(function () {
		$(this).addClass("border-haver");							//为新的收货人加上边框样式
		$("#user-name-hide-one p").removeClass("border-haver");		//移除旧的收货人边框样式
	});
	$("#user-name-hide-one p").click(function () {
		$(this).addClass("border-haver");
		$("#user-name-hide-two p").removeClass("border-haver");
	});
	//收货人鼠标上移变红色，下移恢复
	
	//收货地址鼠标上移变红色同时追加 设为默认地址 编辑 删除，下移恢复
	$("#user-name-hide-one").mouseover(function () {
		$("#user-name-hide-one").css("background-color","#fff3f3");
		$("#user-name-hide-one .op-btns").removeClass("hide");
	});
	$("#user-name-hide-one").mouseout(function () {
		$("#user-name-hide-one").css("background-color","#fff");
		$("#user-name-hide-one .op-btns").addClass("hide");
	});
	$("#user-name-hide-two").mouseover(function () {
		$("#user-name-hide-two").css("background-color","#fff3f3");
		$("#user-name-hide-two .op-btns").removeClass("hide");
	});
	$("#user-name-hide-two").mouseout(function () {
		$("#user-name-hide-two").css("background-color","#fff");
		$("#user-name-hide-two .op-btns").addClass("hide");
	});
	//点击设为默认地址显示默认地址标识
	$("#user-name-hide-one #setdefault").click(function () {
		$("#user-name-hide-one .default-address").removeClass("hide")
		$("#user-name-hide-two .default-address").addClass("hide")
		$("#user-name-hide-two #setdefault").removeClass("hide");
		$("#user-name-hide-one #setdefault").addClass("hide");
	});
	$("#user-name-hide-two #setdefault").click(function () {
		$("#user-name-hide-two .default-address").removeClass("hide")
		$("#user-name-hide-one .default-address").addClass("hide")
		$("#user-name-hide-one #setdefault").removeClass("hide");
		$("#user-name-hide-two #setdefault").addClass("hide");
	});
	
	//支付方式点击选中
	$(".inner-pay-item").click(function() {
		$(this).addClass("inner-pay-item-red");
		$(this).removeClass("inner-pay-item");
		$(".inner-pay-item-haver").addClass("inner-pay-item-active")
		$(".inner-pay-item-haver").removeClass("inner-pay-item-haver");
	});
	$(".inner-pay-item-haver").click(function() {
		$(this).addClass("inner-pay-item-haver");
		$(this).removeClass("inner-pay-item-active");
		$(".inner-pay-item-red").addClass("inner-pay-item")
		$(".inner-pay-item-red").removeClass("inner-pay-item-red");
	});
	//支付方式鼠标上移边框变红色，下移恢复
	$(".inner-pay > div").mouseover(function () {
		$(this).css("border","2px solid #e4393c");
	});
	$(".inner-pay > div").mouseout(function () {
		$(this).css("border","2px solid #E6E6E6");
	});
	//点击新增收货地址显示收货地址弹窗
	$(".add-address").click(function() {	
		$(".shipping em").hide();
		$("input[name='peopleAddressConsigneeName']").val("");
		$("input[name='peopleAddressAddress']").val("");
		$("input[name='peopleAddressPhone']").val("");
		$("input[name='peopleAddressMail']").val("");
		$(".province option:selected").text("请选择");
		$(".city option").text("请选择");
		$(".district option").text("请选择");
		$(".street option").text("请选择");
		$(".shipping").show();
		$(".shipping-head p:first").show();
		$(".shipping-head p:last").hide();
		$("#save").show();
		$("#upData").hide();
		$(".city").hide();
		$(".district").hide();
		$(".street").hide();
		$(".city-tip").hide();
		$(".district-tip").hide();
		$(".street-tip").hide();
	});
	$("#hide").click(function(){
		$(".shipping").hide();
	})
	$(".ship-people input").blur(function(){	//收货人姓名判断
		var shipPeople = $(".ship-people input").val();
		if(validator.isNull(shipPeople)){
			$(".ship-people em").text("请输入收货人姓名");
			$(".ship-people em").css("display","inline-block");
		}else if(shipPeople.length > 25){
			$(".ship-people em").text("收货人姓名不能大于25位");
			$(".ship-people em").css("display","inline-block");
		}else{
			$(".ship-people em").hide();
		}
	})
	$(".ship-people input").focus(function(){	//隐藏掉收货人姓名的错误提示信息
		$(".ship-people em").hide();
	})
	$(".detail-address input").blur(function(){	//详细收货地址的判断
		var detailAddress = $(".detail-address input").val();
		if(validator.isNull(detailAddress)){
			$(".detail-address em").text("请填写详细收货地址");
			$(".detail-address em").css("display","inline-block");
		}else if(detailAddress.length > 50){
			$(".detail-address em").text("收货人详细地址过长");
			$(".detail-address em").css("display","inline-block");
		}else{
			$(".detail-address em").hide();
		}
	});
	$(".detail-address input").focus(function(){ //隐藏详细收货地址的提示信息
		$(".detail-address em").hide();
	});
	$(".moblie-number input").blur(function(){	//手机号错误提示信息
		var moblieNumber = $(".moblie-number input").val();
		if(validator.isNull(moblieNumber)){
			$(".fix-number em:last").hide();
			$(".fix-number em:first").css("display","inline-block");
		}
		if(!validator.isMobilePhone(moblieNumber,'zh-CN') && !validator.isNull(moblieNumber)){
			$(".fix-number em:first").hide();
			$(".fix-number em:last").css("display","inline-block");
		}
	})
	$(".moblie-number input").focus(function(){	//隐藏掉手机号的提示信息
		$(".fix-number em").hide();
	})
	$(".people-email input").blur(function(){	//邮箱的错误提示信息
		var peopleEmail = $(".people-email input").val();
		if(!validator.isEmail(peopleEmail) && !validator.isNull(peopleEmail)){
			$(".people-email em").css("display","inline-block");			
		}else{
			$(".people-email em").hide();
		}
	})	
	$(".people-email input").focus(function(){	//隐藏掉邮箱的错误提示信息
		$(".people-email em").hide();
	})
	function judgeNull(){                      //收货地址提示
		var judge=true;
		var shipPeople = $(".ship-people input").val();
		var detailAddress = $(".detail-address input").val();
		var moblieNumber = $(".moblie-number input").val();
		var fixNumber = $(".fix-number input").val();
		var peopleEmail = $(".people-email input").val();
		if(shipPeople.length > 25){
			$(".ship-people em").css("display","inline-block");
			return;
		}else{
			$(".ship-people em").hide();
		}
		if(detailAddress.length > 50){
			$(".detail-address em").css("display","inline-block");
			return;
		}else{
			$(".detail-address em").hide();
		}
		if(validator.isNull(shipPeople)){
			$(".ship-people em").css("display","inline-block");
		}else{
			$(".ship-people em").hide();
		}
		if(validator.isNull(detailAddress)){
			$(".detail-address em").css("display","inline-block");
		}else{
			$(".detail-address em").hide();
		}
		if(validator.isNull(moblieNumber)){
			$(".fix-number em:last").hide();
			$(".fix-number em:first").css("display","inline-block");
		}
		if(!validator.isMobilePhone(moblieNumber,'zh-CN') && !validator.isNull(moblieNumber)){
			$(".fix-number em:first").hide();
			$(".fix-number em:last").css("display","inline-block");
		}
	}
	$(".add-new-address").click(function(){
		judgeNull();
	});	
});

/*$.ajax({
	url : "/mcms/category/city.do",
	type:"post",
	dataType:"json",
	success : function(returnJson) {		
		$(".province").focus(function(){
			$(".province").empty();
			$(".city").empty().show();
			$(".city-tip").show();
			$(".city").append('<option value="0">请选择</option>');
			$(".district").empty().hide();
			$(".district-tip").hide();
			$(".district").append('<option value="0">请选择</option>');
			$(".street").empty().hide();
			$(".street-tip").hide();
			$(".street").append('<option value="0">请选择</option>');
			
			for (var i = 0 ; i < returnJson.length; i++) {
				if(returnJson[i].categoryCategoryId === 0){
					$(".province").append('<option value='+returnJson[i].categoryId+'>'+returnJson[i].categoryTitle+'</option>');
				}						
			}
		})
		$(".city").focus(function(){	
			var province;
			$(".city").empty();
			$(".district").empty().show();
			$(".district-tip").show();
			$(".district").append('<option value="0">请选择</option>');
			$(".street").empty().hide();
			$(".street-tip").hide();
			$(".street").append('<option value="0">请选择</option>');
			if($(".province option:selected").val() == 0){
				for (var i = 0 ; i < returnJson.length; i++){
					if(returnJson[i].categoryTitle == $(".province option:selected").text()){
						province = returnJson[i].categoryId;
					}
				}
			}else{
				province = $(".province option:selected").val();
			}
			for (var i = 0 ; i < returnJson.length; i++){
				if(returnJson[i].categoryCategoryId == province && returnJson[i].categoryCategoryId !=0){
					$(".city").append('<option value='+returnJson[i].categoryId+'>'+returnJson[i].categoryTitle+'</option>');
				}
			}
		})
		$(".district").focus(function(){
			var city;
			$(".district").empty();
			$(".street").empty().show();
			$(".street-tip").show();
			$(".street").append('<option value="0">请选择</option>');
			if($(".province option:selected").val() == 0){
				for (var i = 0 ; i < returnJson.length; i++){
					if(returnJson[i].categoryTitle == $(".city option:selected").text()){
						city = returnJson[i].categoryId;
					}
				}
			}else{
				city = $(".city option:selected").val();
			}
			for (var i = 0 ; i < returnJson.length; i++){
				if(returnJson[i].categoryCategoryId == city && returnJson[i].categoryCategoryId !=0){
					$(".district").append('<option value='+returnJson[i].categoryId+'>'+returnJson[i].categoryTitle+'</option>');
				}
			}
		})
		$(".street").focus(function(){
			var district;
			$(".street").empty();
			if($(".province option:selected").val() == 0){
				for (var i = 0 ; i < returnJson.length; i++){
					if(returnJson[i].categoryTitle == $(".district option:selected").text()){
						district = returnJson[i].categoryId;
					}
				}
			}else{
				district = $(".district option:selected").val();
			}
			for (var i = 0 ; i < returnJson.length; i++){
				if(returnJson[i].categoryCategoryId == district && returnJson[i].categoryCategoryId !=0){
					$(".street").append('<option value='+returnJson[i].categoryId+'>'+returnJson[i].categoryTitle+'</option>');
				}
			}
		})
	},
	error : function(status) {
		alert(status);
	}
});*/











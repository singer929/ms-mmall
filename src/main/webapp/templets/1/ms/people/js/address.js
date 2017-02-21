// JavaScript Document
$(document).ready(function(){
	$("#hide").click(function(){            	//显示新增收货地址界面
		$(".shipping").hide();
	})
	$(".address-show").click(function()	{		//隐藏新增收货地址界面
		if($("#Sum").text() == 20 ) {
			alert("收货地址不能超过20个")
			return;
		}else{
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
		}
	})
	/*忘记密码页*/
	$(".common input[type = 'text']").focus(function(){   //忘记密码页面输入框选中特效
		$(this).css("border-color","#3aa2e4");
	});
	$(".common input[type='text']").blur(function(){
		$(this).css("border-color","#A9A9A9");
	});
	
	$(".new-password input[type='password']").focus(function(){   //忘记密码3页面输入框选中特效
		$(this).css("border-color","#3aa2e4");
	});
	$(".new-password input[type='password']").blur(function(){
		$(this).css("border-color","#A9A9A9");
	});
	$('#pass').blur(function(){			//重置密码   判断输入格式是否正确
		 if($("#pass").val().length === 0) { 
			 $(".error-messages p").text('密码不能为空');
			 $(".error-messages").show();
		 }else if($("#pass").val().length < 6 || $("#newPass").val().length > 20) { 
			 $(".error-messages p").text('密码长度不正确');
			 $(".error-messages").show();
		 }else{ 
			 $(".error-messages").hide();
		 }
	});
	$('#newPass').blur(function(){		//判断重置密码石俩次输入的密码是否想通过
		if($("#pass").val()  != $("#newPass").val()) { 
			 $(".error-messages p").text('俩次输入的密码不一致');
			 $(".error-messages").show();
		}else{
			$(".error-messages").hide();
		}
	});
	/*忘记密码页结束*/
	
	$(function(){
		var startYear = 1900;
		var thisYear = startYear;
		var endYear = new Date().getUTCFullYear();		//获取当前年份
		for (var i = 1 ; thisYear <= endYear; i++,thisYear++) {
			$(".year").append('<option value='+i+'>'+thisYear+'</option>');
	 	}
		for (var i = 1 ; i <= 12; i++) {
			$(".month").append('<option value='+i+'>'+i+'</option>');
		}
	})	
	$(function(){//时时获取这年这月应有的天数
		$(".year").change(function(){
			var theYear = Number($(".year option:selected").text());
			var theMonth = Number($(".month option:selected").text());
			day(theYear,theMonth);
		});
		$(".month").change(function(){
			var theYear = Number($(".year option:selected").text());
			var theMonth = Number($(".month option:selected").text());
			day(theYear,theMonth);
		});
	});	
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
	$(".add-new-address").click(function() {                      //收货地址提示
		var judge=true;
		var shipPeople = $(".ship-people input").val();
		var peopleAddress;
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
		if(!validator.isEmail(peopleEmail) && !validator.isNull(peopleEmail)){
			$(".people-email em").css("display","inline-block");			
		}else{
			$(".people-email em").hide();
		}
	});	
	$(".shipping-content").find("input").click(function(){		//点击输入框，输入框变色
		$(this).css("border-color","rgb(58, 162, 228)");
	});
	$(".shipping-content").find("input").blur(function(){		//当输入框失焦时，输入框变回原来的颜色
		$(this).css("border-color","#E6E6E6");
	});
});
function day (years,months){			//个人信息页面出生的日期
	var dates = 0;
		if((months <= 7 && months % 2 !== 0) || (months > 7 && months % 2 === 0)){
			dates = 31;
		}else if(months !== 0){
				dates = 30;
				if(months === 2){
					if(years % 4 === 0){
					dates = 29;	
					}else{
						dates = 28 ;	
					}
				}
			}
		$(".date").empty();
		$(".date").append('<option value= '+0+'>请选择</option>');
		for (var i = 1 ; i <= dates; i++) {
			$(".date").append('<option value='+i+'>'+i+'</option>');
		}
}

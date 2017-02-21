function tipMeg(){
	//$(".error-messages").hide();
	//$(".tip-messages").show();
	//$("#megText1").text("公共场所不建议自动登录，以防账号丢失");
	//$("#checkBtn").get(0).checked = true;
	$("#login").get(0).disabled = false;
}

function errMeg(message){
	$(".tip-messages").hide();
	$(".error-messages").show();
	$("#megText2").text(message);
	$("#login").get(0).disabled = true;
}

$(function(){//检查密码是否符合规范
	var passDom = $("input[name='peoplePassword']");
	passDom.blur(function(){
		if (!validator.isLength(passDom.val(),{min:6,max:12})){
			errMeg("密码在6-12字符之间!");
		}
	});
	passDom.focus(function(){
		tipMeg();
	});
})

$(function(){//检测验证码是否符合规范
	var codeDom = $("input[name='rand_code']");
	codeDom.blur(function(){
		if (validator.isNull(codeDom.val())){
			errMeg("输入验证码为空!");
		}
		else if (codeDom.val().length != 4){
			errMeg("输入验证码长度错误!");
		}
	});
	codeDom.focus(function(){
		tipMeg();
	});
});
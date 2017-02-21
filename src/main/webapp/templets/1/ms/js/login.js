

function gwLogin(){
	var userName = $("#userName").val();
	var userPwd = $("#userPwd").val();
	var codetryk = $("#code").val();
	var uril = $("#uril").val();
	if(userName != ''){
		if(userPwd != ''){

			$("#erro_tip").html("");$("#log_btn").html("正在登录中...");
			$.post("/Public-doLogin",{uname:userName,password:userPwd,code:codetryk,uril:uril},function(msg){
				switch(parseInt(msg.dis)){
					case 1:$("#erro_tip").html("用户名/邮箱不正确!");$("#log_btn").html("登      录");break;
					case 2:$("#erro_tip").html("密码不正确!");$("#log_btn").html("登      录");break;
					case 3:
					$(".letip").height(310);
					$("#code_div").show();$("#text_span").html('<input type="text" id="code" name="code" value="" style="width:80px;height:25px;margin-top:5px;">');
					if(msg.data=="1"){
						$("#erro_tip").html("请输入验证码！");
					}else{
						if(msg.type==1){
							$("#erro_tip").html("密码错误！");
						}else if(msg.type==2){
							$("#erro_tip").html("用户名/邮箱错误！");
						}else if(msg.type==3){
							$("#erro_tip").html("验证码错误！");
						}
						getcode();
					}
					$("#log_btn").html("登      录");
					break;
					case 4:
                     window.location.reload();
					break;
				}
				
			});
		}else{
			$("#erro_tip").html('请输入密码！');
			$("#userPwd").css('background-color','#FFE7E7').focus();
		}
	}else{
		$("#erro_tip").html('请输入登录名！');
		$("#userName").css('background-color','#FFE7E7').focus();
	}
	$("#userName").keydown(function(){
		$("#erro_tip").text('');
		$("#userName").css('background-color','#FFF');

	});
	$("#userPwd").keydown(function(){
		$("#erro_tip").text('');
		$("#userPwd").css('background-color','#FFF');
	});
}

function getcode(){
	var r="/Public-verify-"+new Date().getTime();
	$("#codeimg").attr("src",r);
}


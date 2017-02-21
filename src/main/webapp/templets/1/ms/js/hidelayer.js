
var hasqiantao=false;
function isqiantao(){
  var qt=$("#qiantao").val();
  if(parseFloat(qt)>0){
    hasqiantao=true;
  }
}

//登陆窗口
function alert_log_new(){
    var h='<div id="waper" class="lean_overlay" style="display: none;"></div>'+
    '<div class="t_reg_new letip" id="tan_reg" style="height:270px;"> <div class="t_reg1 letip" style="height:270px;width:340px;margin:0px;"><div class="t_reg2_new letip">'+'<div class="t_regtop_new" ><div class="t_reg_lf_new"></div>'+
    '<div class="t_reg_close_new"><a href="javascript:;" class="t_clsoe_btn_new" onclick="reg_close()"></a></div></div>'+
    '<div class="t_reg_bo" style="margin:0px;"><div class="t_reg_cendiv_new" style="height:auto;"><div class="reg_1">'+
    '<div class="reg_3" style="margin-bottom:5px;">'+
    '<p class="reg_p"><input type="text" id="userName" value="请输入用户名/密码" class="reg_input_new"/></p></div>'+
    '<div class="reg_2" style="width:98%;">'+
    '<div class="reg6_new"><input type="password" id="userPwd" value="" /></div></div>'+
    '<div style="display:none;margin:10px 0 5px 0;float:left;width:100%;" id="code_div">\
    <span style="float: left; display: block; width: 52px; height: 34px; line-height: 36px; text-align: center;">验证码</span>\
    <span style="float: left; display: block;" id="text_span"> </span>\
    <span style="margin-left: 5px; display: block; float: left; height: 30px; margin-top: 5px;"> <img src="Public/verify" id="codeimg" height="30" width="80" alt="看不清楚，点击换一张" onclick="getcode()"/></span></div>'+
    '<span id="erro_tip" style="height:30px; color:red;line-height:31px;float:left; font-size:12px;"></span>'+
    '<div class="reg_2" style="margin-top:10;"><a id="log_btn" style="margin-top:0px;" class="btn_new" onclick="gwLogin()" href="javascript:;">登      录</a>'+
    '<div class="btn_left" style="margin-top:0px;"><a style="text-decoration:none;" class="reg5" href="/public-forgot.html" target="_blank">忘记密码 ?</a><a target="_blank" href="/public-reg.html" style="text-decoration:none;" class="reg5">免费注册</a></div></div>';
    isqiantao();  
    if(hasqiantao){
        makelink(h);
    }else{
        $("body").append(h);
        $('#userName').focus(function(){
          var nv=$(this).val();
          if(nv=="请输入用户名/密码"){
             $(this).val("");
          }
          if(nv==""){
              $(this).val("请输入用户名/密码");
          }
        }).blur(function(){
            var nv=$(this).val();
            if(nv==""){
                $(this).val("请输入用户名/密码");
            }
        });
        $("#waper").css({display:"block", opacity:0.75 });
        $("#waper").fadeTo(500, 0.75);autocenter();
    }
}
function reg_close(){
  isqiantao();
  if(hasqiantao==false){
      $("#waper").remove();
      $("#tan_reg").remove();
    }
} 
function alert_log(){
    var h='<div id="waper" class="lean_overlay" style="display: none;"></div>'+
    '<div class="t_reg" id="tan_reg"> <div class="t_reg1"><div class="t_reg2">'+'<div class="t_regtop"><div class="t_reg_lf"></div>'+
    '<div class="t_reg_close"><a href="javascript:;" class="t_clsoe_btn" onclick="reg_close()"></a></div></div>'+
    '<div class="t_reg_bo"><div class="t_reg_cendiv"><div class="reg_1">'+
    '<div class="reg_3" style="margin-bottom:5px;"><span class="reg4">用户名/邮箱：</span></div>'+
    '<p class="reg_p"><input type="text" id="userName"   value="" class="reg_input"/></p></div>'+
    '<div class="reg_2"><div class="reg_3" style="margin:5px 0;"><span class="reg4">密码：</span></div>'+
    '<div class="reg6"><input type="password" id="userPwd" value="" /></div></div>'+
    '<div style="display:none;margin:5px 0;" id="code_div">\
    <span style="margin-right:5px;">验证码</span>\
    <span style="margin-top:5px;" id="text_span"> </span>\
    <span style="margin-left:5px;"> <img src="Public/verify" id="codeimg"  height="30" width="80" alt="看不清楚，点击换一张"/></span>\
    <span style="margin-left:5px;"><a href="javascript:;" onclick="getcode()" style="font-size:12px; color:#00A2C6;text-decoration:none;font-size:12px;">看不清楚,换一张</a></span>\
    </div>'+
    '<span id="erro_tip" style="height:30px; color:red;line-height:31px;float:left; font-size:12px;"></span>'+
    '<div class="reg_2" style="margin-top:0;"><a id="log_btn" style="margin-top:10px;" class="btn" onclick="gwLogin()" href="javascript:;">登      录</a></div>'+
    '<div class="reg_2" style="margin-top:10px;"><a style="text-decoration:none;" class="reg5" href="/public-forgot.html">忘记密码 ?</a><a href="/public-reg.html" style="text-decoration:none;" class="reg5">免费注册</a></div></div></div></div></div></div>';
    isqiantao();  
    if(hasqiantao){
      makelink(h);
    }else{
      $("body").append(h);
    }
    $("#waper").css({display:"block", opacity:0.75 });
    $("#waper").fadeTo(500, 0.75);autocenter();
}
/*
  msg:要显示的提示信息
  width:弹出层的宽度，不填写请为空,
  height:高度，不填写请为空
  align:提示信息的位置
*/
function alert_msg(msg,width,height,align){
    var w1,h1,w2,h2,w3,h;
    if(width==""){width=350;}
    w1=width-24;w2=w1;
    h1=height-32;h2=height-30;
    w3=w1-10;
    if(height==""){height=150;}
      isqiantao(); 
    if(hasqiantao){
      alert(msg);
    }else{
      h='<div id="waper" class="alert_bg" style="display: none;"></div>'+
      '<div class="alert_change" id="tan_reg" style="height:'+height+'px;width:'+width+'px;"> '+
      '<div class="t_reg1_alert" style="height:'+h1+'px;width:'+w1+'px;">'+
      '<div class="t_reg2_alert" style="height:'+h2+'px;width:'+w2+'px;">'+
      '<div class="t_regtop_alert_2">'+
      '<div class="t_reg_close"><a href="javascript:;" class="t_clsoe_btn_alert_2" onclick="reg_close()"></a></div></div>'+
      '<div class="t_reg_bo"><div class="t_reg_cendiv_alert" style="text-align:'+align+';width:'+w3+'px;">'+msg+'</div></div></div>';
      $("body").append(h);
      $("#waper").css({
      display:"block",
      opacity:0.75
      });
      $("#waper").fadeTo(500, 0.75);
      autocenter();
    }
}

function alert_msg_import_order(msg,width,height,align){
    var w1,h1,w2,h2,w3,h;
    if(width==""){width=350;}
    w1=width-24;w2=w1;
    h1=height-32;h2=height-30;
    w3=w1-10;
    if(height==""){height=150;}
      isqiantao(); 
    if(hasqiantao){
      alert(msg);
    }else{
      h='<div id="waper" class="alert_bg" style="display: none;"></div>'+
      '<div class="alert_change" id="tan_reg" style="height:'+height+'px;width:'+width+'px;"> '+
      '<div class="t_reg1_alert" style="height:'+h1+'px;width: auto;">'+
      '<div class="t_reg2_alert" style="height:'+h2+'px;width: auto;">'+
      '<div class="t_regtop_alert_2">'+
      '<div class="t_reg_close"><a href="javascript:;" class="t_clsoe_btn_alert_2" onclick="reg_close_order()"></a></div></div>'+
      '<div class="t_reg_bo"><div class="t_reg_cendiv_alert" style="text-align:'+align+';width: auto;">'+msg+'</div></div></div>';
      $("body").append(h);
      $("#waper").css({
      display:"block",
      opacity:0.75
      });
      $("#waper").fadeTo(500, 0.75);
      autocenter();
    }
}

function reg_close_order(){
	  isqiantao();
	  if(hasqiantao==false){
	      $("#waper").remove();
	      $("#tan_reg").remove();
	      window.location.reload();
	    }
	} 

function alert_msg_catr(msg,width,height,align,url){
    var w1,h1,w2,h2,w3,h;
    if(width==""){width=350;}
    if(!url || url==''){
        url='/cart-disp.html';
    }
    w1=width-24;w2=w1;
    h1=height-32;h2=height-30;
    w3=w1-10;
    if(height==""){height=250;}
    isqiantao();  
    if(hasqiantao){
      if(confirm("您确定要添加到购物车吗?")){
        window.location.href=url;
      }
    }else{
        h='<div id="waper" class="alert_bg" style="display: none;"></div>'+
        '<div class="alert_change" id="tan_reg" style="height:'+height+'px;width:'+width+'px;"> '+
        '<div class="t_reg1_alert" style="height:'+h1+'px;width:'+w1+'px;">'+
        '<div class="t_reg2_alert" style="height:'+h2+'px;width:'+w2+'px;">'+
        '<div class="t_regtop_alert_2">'+
        '<div class="t_reg_close"><a href="javascript:;" class="t_clsoe_btn_alert_2" onclick="reg_close()"></a></div></div>'+
        '<div class="t_reg_bo"><div class="t_reg_cendiv_alert" style="text-align:'+align+';width:'+w3+'px;"><img src="http://igm1.igetmall.net/Public/skin/alert/images/cart.png" />&nbsp;&nbsp;'+msg+'</div><div class="alert_catr_btn"><a href="'+url+'" class="cart_btn">去购物车结算</a><a href="javascript:location.reload()" class="agin_btn">继续购物</a><br clear="all" /></div></div></div>';
        $("body").append(h);
        $("#waper").css({
        display:"block",
        opacity:0.75
        });
        $("#waper").fadeTo(500, 0.75);
        autocenter();
    }
}
//新消息弹出框
function alert_msg_new(msg,width,height,align){
    var w1,h1,w2,h2,w3,h;
    if(width==""){width=350;}
    w1=width-24;w2=w1;
    h1=height-32;h2=height-30;
    w3=w1-10;
    if(height==""){height=150;}
    isqiantao(); 
    if(hasqiantao){
      alert(msg);
      setTimeout("reg_close()",3000);
    }else{
      h='<div id="waper" class="alert_bg" style="display: none;"></div>'+
      '<div class="alert_change" id="tan_reg" style="height:'+height+'px;width:'+width+'px;"> '+
      '<div class="t_reg1_alert" style="height:'+h1+'px;width:'+w1+'px;">'+
      '<div class="t_reg2_alert" style="height:'+h2+'px;width:'+w2+'px;">'+
      '<div class="t_regtop_alert_2">'+
      '<div class="t_reg_close"><a href="javascript:;" class="t_clsoe_btn_alert_2" onclick="reg_close()"></a></div></div>'+
      '<div class="t_reg_bo"><div class="t_reg_cendiv_alert" style="text-align:'+align+';width:'+w3+'px;">'+msg+'</div></div></div>';
      $("body").append(h);
      $("#waper").css({
      display:"block",
      opacity:0.75
      });
      $("#waper").fadeTo(500, 0.75);
        setTimeout("reg_close()",3000);
    }
    autocenter();
}

/*
   确认提示框
   txt:传入的文本
   f:传入的确定按钮点击事件,
   title:提示标题
*/
function comfrim_msg(txt,f,title,width,height,align){
    var w1,h1,w2,h2,w3,h;
    if(width==""){width=350;}
    w1=width-24;w2=w1;
    h1=height-32;h2=height-30;
    w3=w1-10;
    if(height==""){height=150;}
    h='<div id="waper" class="alert_bg" style="display: none;"></div>'+
    '<div class="alert_change" id="tan_reg" style="height:'+height+'px;width:'+width+'px;"> '+
    '<div class="t_reg1_alert" style="height:'+h1+'px;width:'+w1+'px;">'+
    '<div class="t_reg2_alert" style="height:'+h2+'px;width:'+w2+'px;"><div class="t_regtop_alert_2"><div class="t_regtop_alert_2">'+
    '<div style="float: left; margin-left: 10px; color: white; height: 30px;">'+title+'</div>'+
    '<div class="t_reg_close">'+
    '<a href="javascript:;" class="t_clsoe_btn_alert_2" onclick="reg_close()"></a>'+
    '</div>'+
    '</div></div>'+
    '<div class="t_reg_bo"><div class="t_reg_cendiv_alert" style="text-align:'+align+';width:'+w3+'px;">'+txt+'<div class="reg_1_alert"><a class="reg_surebtn" onclick="'+f+'" href="javascript:;">确定</a><a class="reg_qxbtn" onclick="reg_close()" href="javascript:;">取消</a></div></div></div></div>';
    $("body").append(h);
    $("#waper").css({
                display:"block",
                opacity:0.75
    });
    $("#waper").fadeTo(500, 0.75);
    autocenter();
} 
/*
   确认提示框
   txt:传入的文本
   f:传入的确定按钮点击事件,
   title:提示标题
*/
function comfrim_msg_new(txt,f,title,width,height,align){
    var w1,h1,w2,h2,w3,h;
    if(width==""){width=350;}
    w1=width-24;w2=w1;
    h1=height-32;h2=height-30;
    w3=w1-10;
    if(height==""){height=150;}
    isqiantao();  
    if(hasqiantao){
      if(confirm(txt)){
        f;
      }
    }else{
      h='<div id="waper" class="alert_bg" style="display: none;"></div><div class="alert_change" id="tan_reg" style="height:'+height+'px;width:'+width+'px;"> <div class="t_regtop_alert_2" style="height:'+h1+'px;width:'+w1+'px;"><div class="t_reg2_alert" style="height:'+h2+'px;width:'+w2+'px;"><div class="t_regtop_alert_2"><div class="t_regtop_alert_2"><div style="float: left; margin-left: 10px; color: white; height: 30px;">'+title+'</div><div class="t_reg_close"><a href="javascript:;" class="t_clsoe_btn_alert_2" onclick="reg_close()"></a></div></div></div><div class="t_reg_bo"><div class="t_reg_cendiv_alert" style="text-align:'+align+';width:'+w3+'px;">'+txt+'<div class="reg_1_alert"><a class="reg_surebtn" onclick="'+f+'" href="javascript:;">确定</a><a class="reg_qxbtn" onclick="reg_close()" href="javascript:;">取消</a></div></div></div></div>';
      $("body").append(h);
      $("#waper").css({
                  display:"block",
                  opacity:0.75
      });
      $("#waper").fadeTo(500, 0.75);
      autocenter();
    }
} 
/*
   确认提示框
   txt:传入的文本
   f:传入的确定按钮点击事件,
   title:提示标题
*/
function alert_confrim(txt,f,title,width,height,align){
    var w1,h1,w2,h2,w3,h;
    if(width==""){width=350;}
    w1=width-24;w2=w1;
    h1=height-32;h2=height-30;
    w3=w1-10;
    if(height==""){height=150;} 
    isqiantao();  
    if(hasqiantao){
      if(confirm(txt)){
        f;
      }
    }else{
      h='<div id="waper" class="alert_bg" style="display: none;"></div><div class="alert_change" id="tan_reg" style="height:'+height+'px;width:'+width+'px;"> <div class="t_regtop_alert_2" style="height:'+h1+'px;width:'+w1+'px;"><div class="t_reg2_alert" style="height:'+h2+'px;width:'+w2+'px;"><div class="t_regtop_alert_2"><div class="t_regtop_alert_2"><div style="float: left; margin-left: 10px; color: white; height: 30px;">'+title+'</div><div class="t_reg_close"><a href="javascript:;" class="t_clsoe_btn_alert_2" onclick="reg_close()"></a></div></div></div><div class="t_reg_bo"><div class="t_reg_cendiv_alert" style="text-align:'+align+';width:'+w3+'px;">'+txt+'<div class="reg_1_alert" ><a class="reg_surebtn" style="margin-left: 122px;" onclick="'+f+'" href="javascript:;">确定</a></div></div></div></div>';
      $("body").append(h);
      $("#waper").css({
                  display:"block",
                  opacity:0.75
      });
      $("#waper").fadeTo(500, 0.75);
      autocenter();
    }
}
/*
  src:要显示的图片地址
  width:弹出层的宽度，不填写请为空,
  height:高度，不填写请为空
  align:提示信息的位置
*/
function alert_img(src,width,height,align){
    var w1,h1,w2,h2,w3,h;
    if(width==""){width=350;}
    w1=width-24;w2=w1;
    h1=height-32;h2=height-30;
    w3=w1-10;
    if(height==""){height=150;}
      h='<div id="waper" class="alert_bg" style="display: none;"></div>'+
      '<div class="alert_change" id="tan_reg" style="margin-top:-100px;margin-left:-100px;height:'+height+'px;width:'+width+'px;"> '+
      '<div class="t_reg1_alert" style="height:'+h1+'px;width:'+w1+'px;">'+
      '<div style="margin:12px;position:fixed;z-index:30000">'+
      '<div class="t_regtop_alert_2">'+
      '<div class="t_reg_close"><a href="javascript:;" class="t_clsoe_btn_alert_2" onclick="reg_close()"></a></div></div>'+
      '<div class="t_reg_bo" style="margin-top:0"><div><img src="'+src+'"/></div></div></div>';
      $("body").append(h);
      $("#waper").css({
      display:"block",
      opacity:0.75
      });
      $("#waper").fadeTo(500, 0.75);
}
//---demo
function demo(){
	comfrim_msg('您确定要删除吗？',"ok()");
}

function ok(){
  alert("tst");    //这里是确定按钮的操作，在最后要调用reg_close()；
}
function reload(){
   location.reload(); 
}
//广告弹出
function alert_ad(msg,width,height){
    var h;
    h='<div id="waper" onclick="disnone(this)" class="lean_overlay" style="display: none;"></div>'+
    '<style>.lickclose{float:right;height:40px;width:30px;}</style><div class="t_reg_alert" id="tan_reg" style="height:'+height+'px;width:'+width+'px;border:0px;top:148px;left:50%;background:url('+msg+');"><a href="javascript:;" class="lickclose" onclick="disnone()"></a></div>';
    $("body").append(h);
    $("#waper").css({
                display:"block",
                opacity:0.75
    });
    $("#waper").fadeTo(500, 0.75);
    autocenter();
}
function disnone(){
  $("#waper").remove();$("#tan_reg").remove();
}
//自动居中
function autocenter(){
    var tobj=$("#tan_reg");
    var w=tobj.width();
    var h=tobj.height();
    var s_w=$(window).width();//可见区域宽
    var s_h=$(window).height();//高
    var f_w=(parseFloat(s_w)-parseFloat(w))/2;
    var f_h=(parseFloat(s_h)-parseFloat(h))/2;
    if(f_h<20){
      f_h=100;
    }
    tobj.css({"margin-left":f_w,"margin-top":f_h,"left":0,"top":0});
}



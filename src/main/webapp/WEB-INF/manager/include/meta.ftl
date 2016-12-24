  <base target="mainFrame" />
  <#assign static="http://cdn.mingsoft.net">
  <#setting url_escaping_charset='utf-8'> 
  <#assign bootstrap="3.3.5">
  <#assign manager_ui="4.5.7">
  <#assign manager_ui_tmp="4.7.0">
  <#assign temp_home="${base}/static">
  <meta content="IE=edge" http-equiv="X-UA-Compatible" /> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
  <meta content="text/html; charset=utf-8" http-equiv="Content-Type" /> 
  
  <!--特殊字体文件-->
  <link rel="stylesheet" type="text/css" href="${static}/plugins/animate/1.0.0/animate.css" media="all" /> 
  <link rel="stylesheet" type="text/css" href="${static}/plugins/iconfont/1.0.0/iconfont.css"/>
  <!--jquery文件-->
  <script type="text/javascript" src="${static}/plugins/jquery/1.9.1/jquery-1.9.1.js"></script> 
  
  <link rel="stylesheet" type="text/css" href="${static}/plugins/ztree/3.5/zTreeStyle.css" media="all" /> 
  <script type="text/javascript" src="${static}/plugins/ztree/3.5/jquery.ztree.all-3.5.min.js"></script> 
  <!--bootstrap文件-->
  <link rel="stylesheet" type="text/css" href="${static}/plugins/bootstrap/${bootstrap}/css/bootstrap.min.css" media="all" /> 
  <link rel="stylesheet" type="text/css" href="${static}/plugins/bootstrap/${bootstrap}/css/bootstrap-switch.css" media="all" /> 
  <link rel="stylesheet" type="text/css" href="${static}/plugins/bootstrap/${bootstrap}/css/bootstrapValidator.css" media="all" /> 
  <link rel="stylesheet" type="text/css" href="${static}/plugins/bootstrap/${bootstrap}/css/bootstrap-notify.css" media="all" />
  <script type="text/javascript" src="${static}/plugins/bootstrap/${bootstrap}/js/bootstrap.min.js"></script> 
  <script type="text/javascript" src="${static}/plugins/bootstrap/${bootstrap}/js/bootstrap-switch.min.js"></script>   
  <script type="text/javascript" src="${static}/plugins/bootstrap/${bootstrap}/js/bootstrapValidator.js"></script> 
  <script type="text/javascript" src="${static}/plugins/bootstrap/${bootstrap}/js/bootstrap-notify.js"></script>
  
  <!--easyUI-->
  <link rel="stylesheet" type="text/css" href="${static}/plugins/jquery.easyui/1.5/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="${static}/plugins/jquery.easyui/1.5/themes/icon.css">
  <script type="text/javascript" src="${static}/plugins/jquery.easyui/1.5/jquery.easyui.min.js"></script>
  
  <!--时间插件-->
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script> 
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" type="text/css"> 
  <script type="text/javascript" src="${static}/plugins/jquery.validation/1.15.0/jquery.validate.min.js"></script>
  <script type="text/javascript" src="${static}/plugins/jquery.tmpl/1.4.2/jquery.tmpl.min.js"></script>
  <!--时间插件-->
  <link href="${static}/plugins/bootstrap.daterangepicker/1.3.4/daterangepicker.css" rel="stylesheet">
  <script type="text/javascript" src="${static}/plugins/bootstrap.daterangepicker/1.3.4/moment.js"></script>
  <script type="text/javascript" src="${static}/plugins/bootstrap.daterangepicker/1.3.4/daterangepicker.js"></script>
  <script type="text/javascript" src="${static}/plugins/jquery.cookie/2.2.0/jquery.cookie.js"></script>
    
  <!----上传图片--->
  <script type="text/javascript" src="${static}/plugins/jquery.swfupload/1.0.0/swfupload.js"></script>
  <script type="text/javascript" src="${static}/plugins/jquery.swfupload/1.0.0/jquery.swfupload.js"></script>
  <script type="text/javascript" src="${static}/plugins/jquery.swfupload/1.0.0/fileprogress.js"></script>
  
  <!--后台UI-->
  <script type="text/javascript" src="${static}/skin/manager/${manager_ui}/js/ms.validate.js"></script>
  <script type="text/javascript" src="${static}/skin/manager/${manager_ui}/js/ms.web.js"></script>
  <script type="text/javascript" src="${static}/skin/manager/${manager_ui}/js/ms.page.js"></script> 
  <!--<script type="text/javascript" src="${static}/skin/manager/${manager_ui}/js/ms.manager.min.js"></script> -->
  <!-- <link rel="stylesheet" type="text/css" href="${static}/skin/manager/${manager_ui}/css/ms.manager.min.css" media="all" /> -->
  
  <script type="text/javascript" src="http://mb.mstore.mingsoft.net/cdn/skin/manager/${manager_ui_tmp}/js/ms.manager.js"></script>
  <link rel="stylesheet" type="text/css" href="http://mb.mstore.mingsoft.net/cdn/skin/manager/${manager_ui_tmp}/css/ms.manager.css" media="all" />
  
  <#assign skin_manager_logo="${temp_home}/skin/manager/${manager_ui_tmp}/images/logo.png"/>  
  <#assign skin_manager_loadding="${temp_home}/skin/manager/${manager_ui_tmp}/images/loading.gif"/>  
       
  <#include "${managerViewPath}/include/macro.ftl"/>
  <script>
    var basePath = "${basePath}";
    var base = "${base}";
    var managerPath = "${managerPath}";
    var static = "${static}";
    $(function() {
    			//启用工具提示
		   //	$("[data-toggle='tooltip']").tooltip();
		   //	$("[data-toggle='popover']").popover({html:true});
    })
	<#if manager_session?exists>
		var websiteId= "${manager_session.basicId?default('0')}" ;
	</#if>
  </script>	 
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		  <script src="${base}/https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="${base}/https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	
	<script type="text/javascript" charset="utf-8" src="${base}/static/plugins/ueditor/1.4.3.1/ueditor.parse.js"></script>
	<script type="text/javascript" charset="utf-8" src="${base}/static/plugins/ueditor/1.4.3.1/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${base}/static/plugins/ueditor/1.4.3.1/ueditor.all.js"></script>
	<script type="text/javascript" charset="utf-8" src="${base}/static/plugins/ueditor/1.4.3.1/lang/zh-cn/zh-cn.js"></script>

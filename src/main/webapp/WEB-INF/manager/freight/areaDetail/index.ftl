<!DOCTYPE html>
<html lang="zh">
 <head>
<#include "${managerViewPath}/include/macro.ftl"/>
<#include "${managerViewPath}/include/meta.ftl"/>
</head>
	<body style ="height:100%">
		<@ms.contentMenu>
			<div style="margin-top:10px;"></div>
           	<#list listArea as areaEntity>
           		<div style="padding:3px 0 0 40px;cursor:pointer;" id="${areaEntity.faId?default(0)}" onclick="list(${areaEntity.faId?default(0)})">
	        		${areaEntity.faTitle?default(0)}
        		</div>
	        </#list>
		</@ms.contentMenu>
		<@ms.contentBody width="85%" style="overflow-y: hidden;">
			<@ms.contentPanel  style="margin:0;padding:0;overflow-y: hidden;">
	           	<iframe src="${managerPath}/freightAreaDetail/list.do?faId=188" style="width:100%;maring:0;padding:0;border:none;height:100%;background-repeat: no-repeat;  background-position: center;" id="listFrame" target="listFrame" ></iframe>
			</@ms.contentPanel>
		</@ms.contentBody>
	</body>
</html>
</script>
function list(num){
	$.post(${managerPath}/freightAreaDetail/list.do,
		{
			faId:num
		},
		function(data,status){}
	);
}
<script>

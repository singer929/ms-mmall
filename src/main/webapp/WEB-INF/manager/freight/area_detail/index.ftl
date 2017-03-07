<@ms.html5>
	<style>
		.areaActive{
			color: #3497db;
		}
	</style>
	<@ms.panel>
		<@ms.contentMenu>
			<div style="margin-top:10px;"></div>
           	<#list areas as areaEntity>
           		<#if areaEntity_index == 0>
	           		<div style="padding:3px 0 0 40px;cursor:pointer;" class="areaActive" id="${areaEntity.faId?default(0)}" onclick="getFaId(this)">
		        		${areaEntity.faTitle?default(0)}
	        		</div>
        		<#else>
        			<div style="padding:3px 0 0 40px;cursor:pointer;" class="" id="${areaEntity.faId?default(0)}" onclick="getFaId(this)">
	        		${areaEntity.faTitle?default(0)}
        		</div>
        		</#if>
	        </#list>
		</@ms.contentMenu>
		<@ms.contentBody width="85%" style="overflow-y: hidden;">
			<@ms.contentPanel  style="margin:0;padding:0;overflow-y: hidden;">
	           	<iframe src="${managerPath}/freight/areaDetail/list.do?faId=3" style="width:100%;maring:0;padding:0;border:none;height:100%;background-repeat: no-repeat;  background-position: center;" id="listFrame" target="listFrame" ></iframe>
			</@ms.contentPanel>
		</@ms.contentBody>
	</@ms.panel>
</@ms.html5>
<script>
	//页面跳转时修改ID
	function getFaId(obj){
		$("#listFrame").attr("src","${managerPath}/freight/areaDetail/list.do?faId="+obj.id);
		$(obj).addClass('areaActive').siblings().removeClass('areaActive');
	}
</script>

<@ms.html5>
	<style>
		.freightInput{
			height:34px;
			width:85px;
			padding-left:5px;
			border:1px solid #ccc;
			border-radius:4px;
		}
	</style>
    <@ms.panel> 
    	<@ms.nav title="区域运费管理"><@ms.saveButton id="saveButton" /> </@ms.nav>
		<@ms.contentMenu>
			<div style="margin-top:50px;"></div>
           	<#list listArea as areaEntity>
           		<div style="padding:3px 0 0 40px;cursor:pointer;" id="${areaEntity.faId?default(0)}" onclick="edit(${areaEntity.faId?default(0)})">
	        		${areaEntity.faTitle?default(0)}
        		</div>
	        </#list>
		</@ms.contentMenu>
		<@ms.contentBody width="85%" style="overflow-y: hidden;">
			<@ms.table head=['编号,80','快递公司,200','基础运费,150','基础运费数量,150','增长运费,150','增长数量,150']>
				<#list faList as faListEntity>
					<tr>
				        <td>
				        	<#if (faListEntity.fadExpress)??>
				        		${faListEntity.fadExpress.categoryId?default('')}
				        	<#else>
				        		
				        	</#if>
				        </td>
				        <td>
				        	<#if faListEntity.fadExpress?has_content>
				        		${faListEntity.fadExpress.categoryTitle?default('')}
				        	<#else>
				        		
				        	</#if>
				        </td>
				        <td>
				        	<#if faListEntity.fadExpress?has_content>
				        		<input type="text" class="freightInput"
					        		name="${faListEntity.fadExpress.categoryId?default('')}"
					        		areaid="188"
					        		<#if faListEntity.fadBasePrice = 0> 
					        			value = "" 
					        		<#else> 
					        			value="${faListEntity.fadBasePrice}" 
					        		</#if> 
					        		onblur="basePrice(this)"
				        		/>
				        	<#else>
				        		
				        	</#if>
				        </td>
				        <td>
				        	<#if faListEntity.fadExpress?has_content>
				        		<input type="text" class="freightInput"
					        		name="${faListEntity.fadExpress.categoryId?default('')}"
						        	areaid="188"
					        		<#if faListEntity.fadBaseAmount = 0> 
					        			value = "" 
					        		<#else> 
					        			value="${faListEntity.fadBaseAmount}" 
					        		</#if> 
					        		onblur="baseAmount(this)"
				        		/>
				        	<#else>
				        		
				        	</#if>
				        </td>
				        <td>
				        	<#if faListEntity.fadExpress?has_content>
				        		<input type="text" class="freightInput"
				        			name="${faListEntity.fadExpress.categoryId?default('')}"
						        	areaid="188"
					        		<#if faListEntity.fadIncreasePrice = 0> 
					        			value = "" 
					        		<#else> 
					        			value="${faListEntity.fadIncreasePrice}" 
					        		</#if> 
					        		onblur="increasePrice(this)"
				        		/>
				        	<#else>
				        		
				        	</#if>
				        </td>
				        <td>
				        	<#if faListEntity.fadExpress?has_content>
				        		<input type="text" class="freightInput"
				        			name="${faListEntity.fadExpress.categoryId?default('')}"
						        	areaid="188"
					        		<#if faListEntity.fadIncreaseAmount = 0> 
					        			value = "" 
					        		<#else> 
					        			value="${faListEntity.fadIncreaseAmount}" 
					        		</#if> 
					        		onblur="increaseAmount(this)"
				        		/>
				        	<#else>
				        		
				        	</#if>
				        </td>
					</tr>
				</#list>
			</@ms.table>
		</@ms.contentBody>
    </@ms.panel>
</@ms.html5>
<script>
//基础运费的保存和修改
function basePrice(obj){
	var basePrice = obj.value;
	var fadAreaId = obj.getAttribute("areaid");
	var fadExpressId = obj.name;
	if(basePrice != ""){
		$.post("${managerPath}/freightAreaDetail/edit.do",
			{
				fadBasePrice: basePrice,
				fadAreaId:fadAreaId,
				fadExpressId:fadExpressId
			},
			function(data,status){}
		);
	}else{
		var BasePrice = 0;
		$.post("${managerPath}/freightAreaDetail/edit.do",
		{
			fadBasePrice:BasePrice,
			fadAreaId:fadAreaId,
			fadExpressId:fadExpressId
		},
		function(data,status){}
	);
	}
}
//基础运费数量的保存和修改
function baseAmount(obj){
	var fadBaseAmount = obj.value;
	var fadAreaId = obj.getAttribute("areaid");
	var fadExpressId = obj.name;
}
//增长运费的保存和修改
function increasePrice(obj){
	var fadIncreasePrice = obj.value;
	var fadAreaId = obj.getAttribute("areaid");
	var fadExpressId = obj.name;
}
//增长运费数量的保存和修改
function increaseAmount(obj){
	var fadIncreaseAmount = obj.value;
	var fadAreaId = obj.getAttribute("areaid");
	var fadExpressId = obj.name;
}
</script>

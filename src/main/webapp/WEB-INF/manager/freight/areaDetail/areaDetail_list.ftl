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
    	<@ms.nav title="区域运费管理"><@ms.saveButton id="saveButton"/></@ms.nav>
    	<@ms.table head=['编号,80','快递公司,200','基础运费,150','基础运费数量,150','增长运费,150','增长数量,150'] checkbox="ids">
				<#list faList as faListEntity>
					<tr>
						<td>
							<input type="checkbox" name="ids" id="checkId"
							<#if faListEntity.fadEnable?has_content>
								<#if faListEntity.fadEnable = 1>
									checked="checked"
								<#else>
								
								</#if>
							</#if>
							/>
				        </td>
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
					        		name="basePrice"
					        		areaid="188"
					        		expressId="${faListEntity.fadExpress.categoryId?default('')}"
					        		<#if faListEntity.fadBasePrice = 0> 
					        			value = "0" 
					        		<#else> 
					        			value="${faListEntity.fadBasePrice}" 
					        		</#if> 
				        		/>
				        	<#else>
				        		
				        	</#if>
				        </td>
				        <td>
				        	<#if faListEntity.fadExpress?has_content>
				        		<input type="text" class="freightInput"
					        		name="baseAmount"
					        		<#if faListEntity.fadBaseAmount = 0> 
					        			value = "0" 
					        		<#else> 
					        			value="${faListEntity.fadBaseAmount}" 
					        		</#if> 
				        		/>
				        	<#else>
				        		
				        	</#if>
				        </td>
				        <td>
				        	<#if faListEntity.fadExpress?has_content>
				        		<input type="text" class="freightInput"
				        			name="increasePrice"
					        		<#if faListEntity.fadIncreasePrice = 0> 
					        			value = "0" 
					        		<#else> 
					        			value="${faListEntity.fadIncreasePrice}" 
					        		</#if> 
				        		/>
				        	<#else>
				        		
				        	</#if>
				        </td>
				        <td>
				        	<#if faListEntity.fadExpress?has_content>
				        		<input type="text" class="freightInput"
				        			name="increaseAmount"
					        		<#if faListEntity.fadIncreaseAmount = 0> 
					        			value = "0" 
					        		<#else> 
					        			value="${faListEntity.fadIncreaseAmount}" 
					        		</#if> 
				        		/>
				        	<#else>
				        		
				        	</#if>
				        </td>
					</tr>
				</#list>
			</@ms.table>
   </@ms.panel>
</@ms.html5>
<script>
$(function(){
	$("#saveButton").click(function(){
		for(var i=0;i<checkId.length;i++){
			var fadExpressId = $(checkId[i]).closest("tr").find("input[name=basePrice]").attr("expressId");
			var fadAreaId = $(checkId[i]).closest("tr").find("input[name=basePrice]").attr("areaid");
			var fadCheck = false;
			var fadbasePrice = $(checkId[i]).closest("tr").find("input[name=basePrice]").val();
			var fadbaseAmount = $(checkId[i]).closest("tr").find("input[name=baseAmount]").val();
			var fadincreasePrice = $(checkId[i]).closest("tr").find("input[name=increasePrice]").val();
			var fadincreaseAmount = $(checkId[i]).closest("tr").find("input[name=increaseAmount]").val();
			if(checkId[i].checked){
				fadCheck = "checked";
			
			}else{
				fadCheck = "false";
			}
			$.post("${managerPath}/freightAreaDetail/update.do",
				{
					fadExpressId:fadExpressId,
					fadAreaId:fadAreaId,
					fadCheck:fadCheck,
					fadbasePrice:fadbasePrice,
					fadbaseAmount:fadbaseAmount,
					fadincreasePrice:fadincreasePrice,
					fadincreaseAmount:fadincreaseAmount
				},
				function(data,status){}
			);
		}
	});
});
</script>
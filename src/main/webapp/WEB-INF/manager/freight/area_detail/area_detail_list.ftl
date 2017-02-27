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
					        		areaid="${faId}"
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
		var fadEnable = 0;
		for(var i=0;i<checkId.length;i++){
			var fadExpressId = $(checkId[i]).closest("tr").find("input[name=basePrice]").attr("expressId");
			var fadAreaId = $(checkId[i]).closest("tr").find("input[name=basePrice]").attr("areaid");
			var fadBasePrice = $(checkId[i]).closest("tr").find("input[name=basePrice]").val();
			var fadBaseAmount = $(checkId[i]).closest("tr").find("input[name=baseAmount]").val();
			var fadIncreasePrice = $(checkId[i]).closest("tr").find("input[name=increasePrice]").val();
			var fadIncreaseAmount = $(checkId[i]).closest("tr").find("input[name=increaseAmount]").val();
			if(checkId[i].checked){
				fadEnable = "1";
			
			}else{
				fadEnable = "0";
			}
			$.post("${managerPath}/freightAreaDetail/update.do",
				{
					//修改或插入freight_area_detail表
					fadExpressId:fadExpressId,
					fadAreaId:fadAreaId,
					fadEnable:fadEnable,
					fadBasePrice:fadBasePrice,
					fadBaseAmount:fadBaseAmount,
					fadIncreasePrice:fadIncreasePrice,
					fadIncreaseAmount:fadIncreaseAmount,
					//修改或插入freigh表
					freightEnable : fadEnable,
					freightExpressId : fadExpressId,
					freightBasePrice : fadBasePrice,
					freightBaseAmount : fadBaseAmount,
					freightIncreasePrice : fadIncreasePrice,
					freightIncreaseAmount : fadIncreaseAmount	 
				},
				function(data,status){}
			);
		}
		$('.ms-notifications').offset({top:43}).notify({
			type:'success',
			message: { text:'保存成功！' }
		}).show();	
	});
});
</script>
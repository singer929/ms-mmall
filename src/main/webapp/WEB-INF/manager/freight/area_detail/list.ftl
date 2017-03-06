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
						<input type="checkbox" name="ids"
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
				        		name="fadBasePrice"
				        		areaid="${faId}"
				        		fadId="${faListEntity.fadId?default('')}"
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
				        		name="fadBaseAmount"
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
			        			name="fadIncreasePrice"
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
			        			name="fadIncreaseAmount"
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
		var edit = [];
		var add = [];
		$(".table-hover input[name=ids]").each(function(){
			var fadEnable = 0;
			var fadId = $(this).closest("tr").find("input[name=fadBasePrice]").attr("fadId");
			var fadExpressId = $(this).closest("tr").find("input[name=fadBasePrice]").attr("expressId");
			var fadAreaId = $(this).closest("tr").find("input[name=fadBasePrice]").attr("areaid");
			var fadBasePrice = $(this).closest("tr").find("input[name=fadBasePrice]").val();
			var fadBaseAmount = $(this).closest("tr").find("input[name=fadBaseAmount]").val();
			var fadIncreasePrice = $(this).closest("tr").find("input[name=fadIncreasePrice]").val();
			var fadIncreaseAmount = $(this).closest("tr").find("input[name=fadIncreaseAmount]").val();
			if($(this).is(':checked')){ 					//判断复选框是否选中
				fadEnable = 1;
		  	}else{
		  		fadEnable = 0;
		  	}
		  	var obj = new Object();
			obj.fadExpressId=fadExpressId;
			obj.fadAreaId=fadAreaId;
			obj.fadEnable=fadEnable;
			obj.fadBasePrice=fadBasePrice;
			obj.fadBaseAmount=fadBaseAmount;
			obj.fadIncreasePrice=fadIncreasePrice;
			obj.fadIncreaseAmount=fadIncreaseAmount;
			if(fadId>0){
				edit.push(obj);
			}
			if(fadId=0){
				add.push(obj);
			}
			
		})
		var editStr = JSON.stringify(edit);
		var addStr = JSON.stringify(add);
		if(edit.length>0){
			$.post("${managerPath}/freight/areaDetail/update.do",
				{
					str:editStr
				},
				function(data,status){}
			);
		}
		if(add.length>0){
			$.post("${managerPath}/freight/areaDetail/save.do",
				{
					str:addStr
					
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
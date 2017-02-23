<@ms.html5>
    <@ms.nav title="运费管理" back=false>
	</@ms.nav>
    <@ms.panel> 
		<@ms.panelNav>
			<!--@ms.menuButton links=[{"click":"on","name":"上架"},{"click":"off","name":"下架"}] name="批量操作"/-->		
		</@ms.panelNav>		
		<@ms.table head=['编号,80', '快递公司,300','基础运费,300','基础运费数量,300','增长运费,300','增长数量,300'] checkbox="ids">
        	<#if freightList?has_content>
	           	<#list freightList as freight>
		        	<tr> 
		        		<td>
							<input type="checkbox" name="ids" value="${freight.freExpress.categoryId?default('')}">
				        </td>
				        <td>${freight.freExpress.categoryId?default('')}</td>
			           	<td>${freight.freExpress.categoryTitle?default('')}</td>			           	
			            <#if freight.freightBasePrice != 0>
			            	<td><@ms.text id="${freight.freExpress.categoryId}" name="freightBasePrice"  width="100"  value="${freight.freightBasePrice?default('')}" style = "padding-left:0px;"/></td>
			            <#else>
			            	<td><@ms.text id="${freight.freExpress.categoryId}" name="freightBasePrice"  width="100"  value="" style = "padding-left:0px;"/></td>
			            </#if>
			            <#if freight.freightBaseAmount != 0>
			            	<td><@ms.text id="${freight.freExpress.categoryId}" name="freightBaseAmount"  width="100"  value="${freight.freightBaseAmount?default('')}" style = "padding-left:0px;"/></td>
			            <#else>
			            	<td><@ms.text id="${freight.freExpress.categoryId}" name="freightBaseAmount"  width="100"  value="" style = "padding-left:0px;"/></td>
			            </#if>
			            <#if freight.freightIncreasePrice != 0>
			            	<td><@ms.text id="${freight.freExpress.categoryId}" name="freightIncreasePrice"  width="100"  value="${freight.freightIncreasePrice?default('')}" style = "padding-left:0px;"/></td>
			            <#else>
			            	<td><@ms.text id="${freight.freExpress.categoryId}" name="freightIncreasePrice"  width="100"  value="" style = "padding-left:0px;"/></td>
			            </#if>
			            <#if freight.freightIncreaseAmount != 0>
			            	<td><@ms.text id="${freight.freExpress.categoryId}" name="freightIncreaseAmount"  width="100"  value="${freight.freightIncreaseAmount?default('')}" style = "padding-left:0px;"/></td>
			            <#else>
			            	<td><@ms.text id="${freight.freExpress.categoryId}" name="freightIncreaseAmount"  width="100"  value="" style = "padding-left:0px;"/></td>
			            </#if>		            
			            <input type="hidden" name="freightCityId" value="${freight.freightCityId?default('')}"/>	
			        </tr>
		        </#list>
           	<#else>
	           	<td colspan="7"  class="text-center">
	            	<@ms.nodata/>
				</td>
          	</#if>
		</@ms.table>				
	</@ms.panel>
    <script>
    	$(function(){
    		var flag = 0 ;				//当前端数据为空时，传到后台的值默认为0
    		$("input[name=freightBasePrice]").blur(function(){	
    			var categoryId = $(this).attr("id");   			
				var freightBasePrice = $(this).val();				
				var freightCityId = $("input[name=freightCityId]").val();
				if(freightBasePrice != ""){
					$.post("${managerPath}/freight/update.do",
					{
						freightBasePrice:freightBasePrice,
						freightExpressId:categoryId,
						freightCityId:freightCityId
					});
				}else{
					$.post("${managerPath}/freight/update.do",
					{
						freightBasePrice:flag,
						freightExpressId:categoryId,
						freightCityId:freightCityId
					});
				}				
			});	
			
			$("input[name=freightBaseAmount]").blur(function(){		
				var freightBaseAmount = $(this).val();
				var categoryId = $(this).attr("id");
				var freightCityId = $("input[name=freightCityId]").val();
				if(freightBaseAmount != ""){
					$.post("${managerPath}/freight/update.do",
					{
						freightBaseAmount : freightBaseAmount,
						freightExpressId : categoryId,
						freightCityId : freightCityId
					});
				}else{
					$.post("${managerPath}/freight/update.do",
					{
						freightBaseAmount : flag,
						freightExpressId : categoryId,
						freightCityId : freightCityId
					});
				}				
			});
			
			$("input[name=freightIncreasePrice]").blur(function(){		
				var freightIncreasePrice = $(this).val();
				var categoryId = $(this).attr("id");
				var freightCityId = $("input[name=freightCityId]").val();
				if(freightIncreasePrice != ""){
					$.post("${managerPath}/freight/update.do",
					{
						freightIncreasePrice : freightIncreasePrice,
						freightExpressId : categoryId,
						freightCityId : freightCityId
					});
				}else{
					$.post("${managerPath}/freight/update.do",
					{
						freightIncreasePrice : flag,
						freightExpressId : categoryId,
						freightCityId : freightCityId
					});
				}				
			});
			
			$("input[name=freightIncreaseAmount]").blur(function(){		
				var freightIncreaseAmount = $(this).val();
				var categoryId = $(this).attr("id");
				var freightCityId = $("input[name=freightCityId]").val();
				if(freightIncreaseAmount != ""){
					$.post("${managerPath}/freight/update.do",
					{
						freightIncreaseAmount : freightIncreaseAmount,
						freightExpressId : categoryId,
						freightCityId : freightCityId
					});
				}else{
					$.post("${managerPath}/freight/update.do",
					{
						freightIncreaseAmount : flag,
						freightExpressId : categoryId,
						freightCityId : freightCityId
					});
				}			
			});					
    	})
    </script>
</@ms.html5>
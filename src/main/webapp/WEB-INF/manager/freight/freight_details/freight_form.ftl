<@ms.html5>
    <@ms.nav title="运费管理" back=false>
    	<@ms.saveButton id = "save" /> 
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
							<input type="checkbox" name="ids" value="${freight.freExpress.categoryId?default('')}" id = "checkedId" 
							<#if freight.freightEnable?has_content>
								<#if freight.freightEnable == 1>
									checked = "checked"								
								</#if>
							</#if>
							/>
				        </td>
				        <td class = "categoryId">${freight.freExpress.categoryId?default('')}</td>
			           	<td>${freight.freExpress.categoryTitle?default('')}</td>			           			           
		            	<td><@ms.text id="${freight.freExpress.categoryId}" name="freightBasePrice"  width="100"  value="${freight.freightBasePrice?default('')}" style = "padding-left:0px;"/></td>			           
		            	<td><@ms.text id="${freight.freExpress.categoryId}" name="freightBaseAmount"  width="100"  value="${freight.freightBaseAmount?default('')}" style = "padding-left:0px;"/></td>			            
		            	<td><@ms.text id="${freight.freExpress.categoryId}" name="freightIncreasePrice"  width="100"  value="${freight.freightIncreasePrice?default('')}" style = "padding-left:0px;"/></td>			           
		            	<td><@ms.text id="${freight.freExpress.categoryId}" name="freightIncreaseAmount"  width="100"  value="${freight.freightIncreaseAmount?default('')}" style = "padding-left:0px;"/></td>			           	            
			            <input type="hidden" name="freightCityId" value="${freightCityId?default('')}"/>	
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
	    	$("#save").click(function(){
	    		var checked = 0;	    		
	    		for(var i = 0 ; i < checkedId.length ; i++){
	    			var freightCityId = $("input[name = freightCityId]").val();
		    		var freightExpressId = $(checkedId[i]).closest("tr").find(".categoryId").text();
		    		var freightBasePrice = $(checkedId[i]).closest("tr").find("input[name = freightBasePrice]").val();
		    		var freightBaseAmount = $(checkedId[i]).closest("tr").find("input[name = freightBaseAmount]").val();
		    		var freightIncreasePrice = $(checkedId[i]).closest("tr").find("input[name = freightIncreasePrice]").val(); 
		    		var freightIncreaseAmount = $(checkedId[i]).closest("tr").find("input[name = freightIncreaseAmount]").val(); 
	    			if(checkedId[i].checked){
	    				checked = 1;    					    				   					
	    			}else{
	    				checked = 0;	    				
	    			}
	    			$.post("${managerPath}/freight/update.do",
	    				{
	    					freightEnable : checked,
	    					freightCityId : freightCityId,
	    					freightExpressId : freightExpressId,
	    					freightBasePrice : freightBasePrice,
	    					freightBaseAmount : freightBaseAmount,
	    					freightIncreasePrice : freightIncreasePrice,
	    					freightIncreaseAmount : freightIncreaseAmount	    					
	    				}
	    			)
	    		}
	    	});
    	});
    </script>
</@ms.html5>
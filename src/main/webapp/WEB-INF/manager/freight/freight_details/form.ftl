<@ms.html5>
    <@ms.nav title="运费管理" back=false>
    	<@ms.saveButton id = "save"  onclick="save()"/> 
	</@ms.nav>
    <@ms.panel> 		
		<@ms.form  name="expressForm" 
			 action="" method="post"    class="form-horizontal"  
			style="form-horizontal" isvalidation=true tooltip=false>
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
			            	<td>
			            		<@ms.number id="freightBasePrice" label="" name="freightBasePrice"  width="100"  value="${freight.freightBasePrice?default('')}" style="padding-left:0px;" min=0 max=999999 isFloat=true/>
							</td>			           
			            	<td>
			            		<@ms.number id="freightBaseAmount" name="freightBaseAmount"  width="100"  value="${freight.freightBaseAmount?default('')}" style = "padding-left:0px;" min=0 max=999999 isFloat=true/>
			            	</td>			            
			            	<td>
				            	<@ms.number id="freightIncreasePrice" name="freightIncreasePrice"  width="100"  value="${freight.freightIncreasePrice?default('')}" style = "padding-left:0px;" min=0 max=999999 isFloat=true/>
			            	</td>			           
			            	<td>
			            	<@ms.number id="freightIncreaseAmount" name="freightIncreaseAmount"  width="100"  value="${freight.freightIncreaseAmount?default('')}" style = "padding-left:0px;" min=0 max=999999 isFloat=true/>
			            	</td>			           	            
				            <input type="hidden" name="freightCityId" value="${freightCityId?default('')}"/>	
				        </tr>
			        </#list>
	           	<#else>
		           	<td colspan="7"  class="text-center">
		            	<@ms.nodata/>
					</td>
	          	</#if>
			</@ms.table>	
		</@ms.form>			
	</@ms.panel>
</@ms.html5>
 <script>   	
	function save(){  		
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
			//判断所有的值是否合法或为空
			if((freightBasePrice >=0 && freightBasePrice != "" && freightBasePrice <= 999999) && (freightBaseAmount >=0 && freightBaseAmount != "" && freightBaseAmount <= 999999) && (freightIncreasePrice >=0 && freightIncreasePrice != "" && freightIncreasePrice <= 999999) && (freightIncreaseAmount >=0 && freightIncreaseAmount != "" && freightIncreaseAmount <= 999999) ){
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
		}
		//更新数据后刷新页面
		location.reload();
	};   	
</script>
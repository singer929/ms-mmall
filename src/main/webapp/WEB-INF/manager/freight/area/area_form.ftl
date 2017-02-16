<@ms.html5>
    <@ms.nav title="管理" back=true><@ms.savebutton  id="savebutton"/></@ms.nav>
    <@ms.panel>
		<@ms.table head=['编号,90','标题','操作,100'] id="tableConterent">
			<#if categoryJson?has_content && categoryJson!="[]">
	        	<@ms.treeTable treeId="areaAddTree"   tbodyId="tableConterent" json="${categoryJson?default('')}" jsonName="categoryTitle" jsonId="categoryId" jsonPid="categoryCategoryId"/>
	      	<#else>
             	<tr>
		            <td colspan="3" class="text-center">
		            	<@ms.nodata/>
					</td>
	          	</tr>                          
        	</#if>
		</@ms.table>
		
		<script id="afterareaAddTree" type="text/x-jquery-tmpl">
			<td>
				<input type="checkbox" name="ids" onclick=getDataId(this)>
			</td>
		</script>
		
    </@ms.panel>
</@ms.html5>	        
   		
<script>
function getDataId
</script>














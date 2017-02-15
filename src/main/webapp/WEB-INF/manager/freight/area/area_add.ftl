<@ms.html5>
	<@ms.nav title="区域管理" back=true><@ms.savebutton  id="savebutton"/></@ms.nav>
	<@ms.panel>
		<@ms.table head=['编号,100','标题'] id="tableConterent" checkbox="ids">
			<#if categoryJson?has_content && categoryJson!="[]">
	        	<@ms.treeTable treeId="areaTree"   tbodyId="tableConterent" json="${categoryJson?default('')}" jsonName="categoryTitle" jsonId="categoryId" jsonPid="categoryCategoryId"/>
	      	<#else>
             	<tr>
		            <td colspan="3" class="text-center">
		            	<@ms.nodata/>
					</td>
	          	</tr>                          
        	</#if>
		</@ms.table>
	</@ms.panel>
</@ms.html5>
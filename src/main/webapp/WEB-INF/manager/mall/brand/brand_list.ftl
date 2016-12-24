<@ms.html5>
    <@ms.nav title="品牌管理">
		<@ms.buttonGroup>
			<@ms.addButton url="${managerPath}/mall/brand/add.do?${params}"/>
			<@ms.delButton url="${managerPath}/mall/brand/delete.do?${params}" fieldName="categoryId"/>
		</@ms.buttonGroup>
    </@ms.nav>
    <@ms.panel>
		<@ms.table head=['缩略图,110''标题'] id="tableConterent" checkbox="categoryId">
			<#if brandList?has_content>
				<#list brandList as brand>
					<tr>
						<td><input type="checkbox" name="categoryId" value="${brand.categoryId}"/></td>
						<td><img src="${base}/${brand.categorySmallImg?default('')}" width="100" width="30"/></td>
						<td><a href="${managerPath}/mall/brand/edit.do?categoryId=${brand.categoryId}">${brand.categoryTitle?default('')}</a></td>
					</tr>
				</#list>
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
 
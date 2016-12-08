 <@ms.html5>
    <@ms.nav title="商品印象管理" ></@ms.nav>
    <@ms.panel>
         <@ms.searchForm name="searchProductImpressionForm" action="${managerPath}/mall/productImpression/list.do">
        	
			<@ms.searchFormButton>
				 <@ms.queryButton form="searchProductImpressionForm"/> 
			</@ms.searchFormButton>			
		 </@ms.searchForm>    
		 <@ms.panelNav>
		    <@ms.buttonGroup>
		    <@ms.addButton url="${managerPath}/mall/productImpression/add.do?${params}"/>
		    <@ms.delButton url="${managerPath}/mall/productImpression/delete.do" fieldName="piId"/>
		    </@ms.buttonGroup>
		</@ms.panelNav>     
		<@ms.table 
		 	   head=["","商品编号","印象","添加用户","数量","添加时间"] 
	           checkbox="piId"
	           editField=["piId"]
	           editJs="editProductImpression"> 
	        <#if productImpressionList?has_content>
  				<#list productImpressionList as productImpression>
  				<tr>
	  					<td>
							<input type="checkbox" name="piId" value="${productImpression.piId}"/>
	  					</td>  					
		  				<td><a href="${managerPath}/mall/productImpression/edit.do?piId=${productImpression.piId}&pageNo=1&${params}">${productImpression.piId}</a></td>
		  				<td>${productImpression.piBasicId?default('')}</td>
		  				<td>${productImpression.piTitle?default('')}</td>
		  				<td>${productImpression.piPeopleId?default('')}</td>
		  				<td>${productImpression.piAmount?default('')}</td>
		  				<td>${productImpression.piDatetime?default('')}</td>
	  			</tr>
  				</#list>
  			<#else>
  			<tr>
  				<td colspan="7"><@ms.nodata content="暂无商品印象纪录" /></td>
  			</tr>
  			</#if>
  		</@ms.table>
  		<#if page?has_content>
			<@ms.pagehelper page=page url="${managerPath}/mall/productImpression/list.do?${params}"/>
		</#if>		 
    </@ms.panel>
</@ms.html5>

<@ms.html5>
    <@ms.nav title="商品管理" back=false>
    	<@ms.panelNav>
    		<@ms.buttonGroup>
				<@ms.addButton url="${managerPath}/mall/product/add.do?basicCategoryId=${product.basicCategoryId?default(0)}&column.categoryTitle=${product.column.categoryTitle?default('请选择商品分类')}"/>
				<@ms.delButton id="delWebsiteBtn" fieldName="ids" url="${managerPath}/mall/product/delete.do"/>
			</@ms.buttonGroup>
    	</@ms.panelNav>
	</@ms.nav>
	
    <@ms.searchForm name="searchForm" action="${managerPath}/mall/product/list.do">
        	<@ms.select 
			    name="productShelf" 
			    label="状态" 
			    list=[{"id":"-1","name":"全部"},{"id":"1","name":"上架"},{"id":"2","name":"下架"}]
			    listKey="id" 
			    listValue="name"
			    value="${productShelf?default('')}"
			/>
			<@ms.searchFormButton>
				 <@ms.queryButton form="searchForm"/> 
			</@ms.searchFormButton>			
		</@ms.searchForm>
    <@ms.panel> 
		<@ms.panelNav>
			<!--@ms.menuButton links=[{"click":"on","name":"上架"},{"click":"off","name":"下架"}] name="批量操作"/-->		
		</@ms.panelNav>
		
			<@ms.table head=['编号,80', '商品名','栏目名,100','价格,100','状态,60','库存,60'] checkbox="ids">
		        	<#if listProduct?has_content>
		           	<#list listProduct as listProduct>
			        	<tr> 
			        		<td>
								<input type="checkbox" name="ids" value="${listProduct.basicId?c?default(0)}">
					        </td>
				           	<td>${listProduct.basicId?c?default(0)}</td>
				            <td>
				            	<a class="btn btn-xs red tooltips edit-btn" href="${managerPath}/mall/product/edit.do?${params}&basicId=${listProduct.basicId?c?default(0)}" target="_self" data-toggle="tooltip"  data-original-title="编辑商品">
				            	${listProduct.basicTitle?default(0)}
				            	</a>
				            </td>
				            <td><#if listProduct.column?exists>${listProduct.column.categoryTitle?default("")}</#if></td>
				            <td>${listProduct.productPrice?c?default(0)}</td>
				            <td style="color:red"><#if listProduct.productShelf?default(0)==1>上架<#else>已下架</#if></td>
				            <td>${listProduct.productStock?c?default(0)}</td>
				        </tr>
			        </#list>
		           	<#else>
		           	<td colspan="7"  class="text-center">
		            	<@ms.nodata/>
					</td>
		          	</#if>
			</@ms.table>
			<#if page?has_content>
				<@ms.pagehelper page=page url="${managerPath}/mall/product/list.do?${params}"/>
			</#if>		
    </@ms.panel>
</@ms.html5>
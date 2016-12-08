 <@ms.html5>
    <@ms.nav title="订单评价管理" ></@ms.nav>
    <@ms.panel>
         <@ms.searchForm name="searchOrderCommentForm" action="${managerPath}/mall/orderComment/list.do">
        	
			<@ms.searchFormButton>
				 <@ms.queryButton form="searchOrderCommentForm"/> 
			</@ms.searchFormButton>			
		 </@ms.searchForm>    
		 <@ms.panelNav>
		    <@ms.buttonGroup>
		    <@ms.addButton url="${managerPath}/mall/orderComment/add.do?${params}"/>
		    <@ms.delButton url="${managerPath}/mall/orderComment/delete.do" fieldName="ocCommentId"/>
		    </@ms.buttonGroup>
		</@ms.panelNav>     
		<@ms.table 
		 	   head=["评论编号","订单编号","印象","商品符合度","店家服务态度","物流发货速度","评价服务"] 
	           checkbox="ocCommentId"
	           editField=["ocCommentId"]
	           editJs="editOrderComment"> 
	        <#if orderCommentList?has_content>
  				<#list orderCommentList as orderComment>
  				<tr>
	  					<td>
							<input type="checkbox" name="ocCommentId" value="${orderComment.ocCommentId}"/>
	  					</td>  					
		  				<td><a href="${managerPath}/mall/orderComment/edit.do?ocCommentId=${orderComment.ocCommentId}&pageNo=1&${params}">${orderComment.ocCommentId}</a></td>
		  				<td>${orderComment.ocOrderId?default('')}</td>
		  				<td>${orderComment.ocImpression?default('')}</td>
		  				<td>${orderComment.ocProduct?default('')}</td>
		  				<td>${orderComment.ocService?default('')}</td>
		  				<td>${orderComment.ocLogistics?default('')}</td>
		  				<td>${orderComment.ocServiceDescribe?default('')}</td>
	  			</tr>
  				</#list>
  			<#else>
  			<tr>
  				<td colspan="8"><@ms.nodata content="暂无订单评价纪录" /></td>
  			</tr>
  			</#if>
  		</@ms.table>
  		<#if page?has_content>
			<@ms.pagehelper page=page url="${managerPath}/mall/orderComment/list.do?${params}"/>
		</#if>		 
    </@ms.panel>
</@ms.html5>

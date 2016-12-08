 <@ms.html5>
    <@ms.nav title="订单评价编辑" >
    	<@ms.saveButton postForm="orderCommentForm"/>
    </@ms.nav>
    <@ms.panel>
    	<@ms.form name="orderCommentForm" action="${managerPath}/mall/orderComment/${autoCURD}.do" redirect="${managerPath}/mall/orderComment/list.do?${params}">
    				<input type="hidden" name="ocCommentId" value="${orderComment.ocCommentId?default(0)}" />
    				<@ms.number label="订单编号" name="ocOrderId" value="${orderComment.ocOrderId?default(0)}" width="20"/>
    				<@ms.text label="印象" name="ocImpression" value="${orderComment.ocImpression?default('')}"  width="510"/>
    				<@ms.number label="商品符合度" name="ocProduct" value="${orderComment.ocProduct?default(0)}" width="20"/>
    				<@ms.number label="店家服务态度" name="ocService" value="${orderComment.ocService?default(0)}" width="20"/>
    				<@ms.number label="物流发货速度" name="ocLogistics" value="${orderComment.ocLogistics?default(0)}" width="20"/>
    				<@ms.text label="评价服务" name="ocServiceDescribe" value="${orderComment.ocServiceDescribe?default('')}"  width="510"/>
    	</@ms.form>
    </@ms.panel>
</@ms.html5> 

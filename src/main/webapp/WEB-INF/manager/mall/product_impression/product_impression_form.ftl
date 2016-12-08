 <@ms.html5>
    <@ms.nav title="商品印象编辑" >
    	<@ms.saveButton postForm="productImpressionForm"/>
    </@ms.nav>
    <@ms.panel>
    	<@ms.form name="productImpressionForm" action="${managerPath}/mall/productImpression/${autoCURD}.do" redirect="${managerPath}/mall/productImpression/list.do?${params}">
    				<input type="hidden" name="piId" value="${productImpression.piId?default(0)}" />
    				<@ms.number label="商品编号" name="piBasicId" value="${productImpression.piBasicId?default(0)}" width="20"/>
    				<@ms.text label="印象" name="piTitle" value="${productImpression.piTitle?default('')}"  width="510"/>
    				<@ms.number label="添加用户" name="piPeopleId" value="${productImpression.piPeopleId?default(0)}" width="20"/>
    				<@ms.number label="数量" name="piAmount" value="${productImpression.piAmount?default(0)}" width="20"/>
    	</@ms.form>
    </@ms.panel>
</@ms.html5> 

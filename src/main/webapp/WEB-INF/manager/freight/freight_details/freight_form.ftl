<#assign defaultSpecImg = base + "/images/upload.png">
<@ms.html5>
    <@ms.nav back=true title="运费编辑"><@ms.saveButton onclick="onSave()" /> </@ms.nav>
    <@ms.panel>
    	<@ms.form name="productForm" action="${managerPath}/mall/product/${autoCURD}.do" isvalidation=true redirect="${managerPath}/mall/product/list.do?${params}">
			<input type="hidden" name="basicId" value="${product.basicId}"/>
			<@ms.formRow label="快递公司" width="300">
	            	<@ms.inputTree  
	            		treeId="brandTree" 
	            		json="[]" 
	            		jsonId="categoryId" 
	            		jsonPid="categoryCategoryId" 
	            		jsonName="categoryTitle"
	            		name="productBrand"
	            		text="请选择快递公司"
	            		value="${product.productBrand}"
	            	/>
			</@ms.formRow>
			<@ms.number label="排序"  name="basicSort" value="${product.basicSort}" min=-99999 max=99999 />
			<@ms.number label="现价"  name="productPrice" value="${product.productPrice}" min=0.1 max=9999999 isFloat=true/>
			<@ms.number label="原价"  name="productCostPrice" value="${product.productCostPrice}" min=0.1 max=9999999 isFloat=true/>
			<@ms.number label="库存"  name="productStock" value="${product.productStock}" min=1 max=9999999/>
    	</@ms.form>
    </@ms.panel>
</@ms.html5>
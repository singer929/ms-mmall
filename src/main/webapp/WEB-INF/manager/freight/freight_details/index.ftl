<!DOCTYPE html>
<html lang="zh">
 <head>
<#include "${managerViewPath}/include/macro.ftl"/>
<#include "${managerViewPath}/include/meta.ftl"/>
</head>
<body>
<@ms.content>
 	<@ms.contentMenu>
		 <!-- 树形模块菜单开始 -->
		<#if categoryJson?has_content && categoryJson!="[]">
				<@ms.tree  treeId="inputTree" json="${categoryJson?default('')}" addNodesName="全部"  jsonId="categoryId" jsonPid="categoryCategoryId" jsonName="categoryTitle"   showIcon="true" expandAll="true" getZtreeId="getZtreeId(event,treeId,treeNode);" />
			<#else> 
				<@ms.nodata content="暂无栏目"/>
		</#if>
		<!-- 树形模块菜单结束 -->
	</@ms.contentMenu>
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
</@ms.content>
<script>
//树形结点
function getZtreeId(event,treeId,treeNode){
	if (treeNode.columnType==1) {
		$("#listFrame").attr("src","${managerPath}/cms/article/"+treeNode.categoryId+"/list.do?categoryTitle="+encodeURIComponent(treeNode.categoryTitle));
	} else if(treeNode.columnType==2){
		//判断该单篇栏目是否存在文章
		$.ajax({ 
			type: "POST", 
			url: base+"${baseManager}/cms/article/"+treeNode.categoryId+"/queryColumnArticle.do",
			dataType:"json",
			success: function(msg){
				if (msg.result) {
					$("#listFrame").attr("src","${managerPath}/cms/article/add.do?categoryId="+treeNode.categoryId+"&categoryTitle="+encodeURIComponent(treeNode.categoryTitle));
				} else {
					//如果该单篇栏目下存在文章则跳转到文章编辑页
					$("#listFrame").attr("src","${managerPath}/cms/article/"+treeNode.categoryId+"/edit.do?categoryId="+treeNode.categoryId+"&categoryTitle="+encodeURIComponent(treeNode.categoryTitle));
				}
			},
		});
	} else if(treeNode.columnType=="" || treeNode.columnType == undefined){
		$("#listFrame").attr("src","${managerPath}/cms/article/0/list.do"); 
	}
}
</script>
</body>
</html>
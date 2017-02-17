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
				<@ms.tree  treeId="inputTree" json="${categoryJson?default('')}" addNodesName="全部"  jsonId="categoryId" jsonPid="categoryCategoryId" jsonName="categoryTitle"   showIcon="true" expandAll="true" getZtreeId="getZtreeId(event,treeId,treeNode);"  id="listCity"/>
			<#else> 
				<@ms.nodata content="暂无栏目"/>
		</#if>
		<!-- 树形模块菜单结束 -->
	</@ms.contentMenu>
    <@ms.contentBody width="85%" style="overflow-y: hidden;">
		<@ms.contentPanel  style="margin:0;padding:0;overflow-y: hidden;">
           <iframe src="${managerPath}/freight/form.do" style="width:100%;maring:0;padding:0;border:none;height:100%;background-image: url(${skin_manager_loadding});  background-repeat: no-repeat;  background-position: center;" id="listFrame" target="listFrame" ></iframe>
		</@ms.contentPanel>	
	</@ms.contentBody>  
</@ms.content>
<script>
//树形结点
function getZtreeId(event,treeId,treeNode){
	
		$.post("${managerPath}/freight/form.do",
				{
					categoryId:treeNode.categoryId						
				},
				function(data,status){		
					if(data.resultMsg == 1){
						
						
					}else{
						
					}
				});
		
	
}
</script>
</body>
</html>
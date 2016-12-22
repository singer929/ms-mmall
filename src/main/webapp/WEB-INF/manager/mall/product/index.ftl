<!DOCTYPE html>
<html lang="en">
<head>
<#include "${managerViewPath}/include/meta.ftl"/> <!--调用head内样式信息-->
</head>
<body>

	<@ms.content>
			<@ms.contentMenu>
					<div class="col-md-2 categoryTree">
							<ul id="categoryTree" class="ztree" style="margin-top:0; width:100%;margin-left:-9px">
					</div>
			</@ms.contentMenu>
  			<@ms.contentBody width="85%" style="overflow-y: hidden;">
				<@ms.contentPanel  style="margin:0;padding:0;overflow-y: hidden;">
                   <iframe src="${managerPath}/mall/product2/list.do?basicCategoryId=0" style="width:100%;maring:0;padding:0;border:none;height:100%;background-image: url(${skin_manager_loadding});  background-repeat: no-repeat;  background-position: center;" id="listFrame" target="listFrame" ></iframe>
				</@ms.contentPanel>	
			</@ms.contentBody>          
	</@ms.content>
	<!--引用弹出框插件-->
	<@warnModal modalName="Model"/>
	<!--JQ特效部分-->
	<script>
		$(function(){	
			$(".categoryTree").height($(document).height());
			$("#listFrame").height($(document).height());
			//zTree框架	
			var setting = {
				callback: {
					onClick: function(event, treeId, treeNode) {
						if (treeNode.type==1) {
								$("#listFrame").attr("src","${managerPath}/mall/product2/list.do?basicCategoryId="+treeNode.id+"&column.categoryTitle="+encodeURIComponent(treeNode.name));
						} else {
								$("#listFrame").attr("src","${managerPath}/mall/product2/edit.do?basicCategoryId="+treeNode.id+"&column.categoryTitle="+encodeURIComponent(treeNode.name));
						}
					}
				},
				view: {
					dblClickExpand: dblClickExpand 
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			};
	
			function dblClickExpand(treeId, treeNode) {
				return treeNode.level > 0;
			}
	
			$(document).ready(function(){
				$.fn.zTree.init($("#categoryTree"), setting, zNodes);
				zTreeObj = $.fn.zTree.getZTreeObj("categoryTree");
				zTreeObj.expandAll(true);
			});
			
			//获取栏目节点列表
			var listColumn= <#if listColumn?has_content>${listColumn}<#else>""</#if>;
			var zNodes = new Array();
			
			zNodes[0] = { id:0, pId:0,name:"全部", open:true,type:1,open:true};
			//遍历节点，添加到数字中
			for( var i = 0 ; i < listColumn.length; i++){
				zNodes[i+1] = { id:listColumn[i].categoryId, pId:listColumn[i].categoryCategoryId,name:listColumn[i].categoryTitle, open:false,type:listColumn[i].columnType};
			}
		});
		
	</script>
</body>
</html>

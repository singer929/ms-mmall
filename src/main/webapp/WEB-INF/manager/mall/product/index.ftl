<@ms.html5>
	<@ms.contentMenu>
		<div class="col-md-2 categoryTree">
			<ul id="categoryTree" class="ztree" style="margin-top:0; width:100%;margin-left:-9px">
		</div>
		</@ms.contentMenu>
		<@ms.panel style="width: 85%;right: 0; position: absolute;">
			<@ms.nav title="商品管理" back=false style="width: 85%;">
    			
			</@ms.nav>
			<@ms.searchForm name="searchForm" action="">
        		<@ms.select 
			    	name="productShelf" 
			    	label="状态" 
			    	list=[{"id":"-1","name":"全部"},{"id":"1","name":"上架"},{"id":"2","name":"下架"}]
			    	listKey="id" 
			    	listValue="name"
			    	value="${productShelf?default('')}"
				/>
				<@ms.searchFormButton>
				 	<@ms.queryButton id="submitSearch"/> 
				</@ms.searchFormButton>			
			</@ms.searchForm>
			<div id="toolbar">
				<@ms.panelNav>
    				<@ms.buttonGroup>
    					<@ms.addButton id="addBtn"/>
						<@ms.delButton id="delWebsiteBtn"/>
					</@ms.buttonGroup>
    			</@ms.panelNav>
			</div>
			<table id="productListTable"
				data-show-refresh="true"
				data-show-columns="true"
				data-show-export="true"
				data-method="post" 
				data-detail-formatter="detailFormatter" 
				data-pagination="true"
				data-page-size="10"
				data-side-pagination="server">
			</table>
		</@ms.panel>	
</@ms.html5>
<!--JQ特效部分-->
<script>
	$(function(){
	var a;	
		$(".categoryTree").height($(document).height());
		$("#searchForm").width("85%");
		$("#listFrame").height($(document).height());
		var treeHs;
		//zTree框架	
		var setting = {
			callback: {
				onClick: function(event, treeId, treeNode) {
					var basicCategoryId = treeNode.id;
					a = basicCategoryId;
    	 			var params = $('#productListTable').bootstrapTable('getOptions');
    	 			params.queryParams = function(params) {  
    	 				$.extend(params,{basicCategoryId:basicCategoryId});
            			return params;  
   		 			}  
					$("#productListTable").bootstrapTable("refresh",{query: {basicCategoryId:treeNode.id}});
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
		
		$("#productListTable").bootstrapTable({
			url:"${managerPath}/mall/product/list.do",
			contentType : "application/x-www-form-urlencoded",
			queryParamsType : "undefined",
			toolbar: "#toolbar",
	    	columns: [{ checkbox: true},{
	        	field: 'basicId',
	        	title: '编号',
	        	align: 'center'
	    	}, {
	        	field: 'basicTitle',
	        	title: '商品名',
	        	formatter: function (value, row, index){
	        		var href = "${managerPath}/mall/product/edit.do";
	    			return"<a class='btn btn-xs red tooltips edit-btn' target='_self' data-original-title='编辑商品' href=" + href + "?basicId="+ row.basicId + ">" + value + "</a>"
	    		}
	    	}, {
	        	field: 'column.categoryTitle',
	        	title: '栏目名',
	       	 	align: 'center',
	       	 	formatter: function (value, row, index){
	        		return"<span class='categoryTitle' categoryTitle="+value+" basicCategoryId ="+row.basicCategoryId+">" +value+ "</span>"
	        	}
	    	},{
	        	field: 'productPrice',
	        	title: '价格',
	        	align: 'center'
	    	},{
	        	field: 'productShelf',
	        	title: '状态',
	        	align: 'center',
	        	formatter: function (value, row, index){
	    			if(row.productShelf == 1){		
	    				return"上架";
	    			}
	    			else if(row.productShelf == 0){
	    				return"下架";
	    			}
	    		},
	    		cellStyle:function(row,index) {
					return{
					css:{"color":"red"}
					};
				}
	    		
	    	},{
	        	field: 'productStock',
	        	title: '库存',
	        	align: 'center'
	    	}]
		})
		//删除按钮
		$("#delWebsiteBtn").click(function(){
			var rows = $("#productListTable").bootstrapTable("getSelections");
			$(this).attr("disabled","true");
			$.ajax({		
		    	type:"post",
				url:"${managerPath}/mall/product/delete.do",
		    	data:JSON.stringify(rows),
		    	dataType:"json",
  				contentType:"application/json",
		    	success:function(msg) { 
					if(msg.result == true) {
						<@ms.notify msg="删除成功" type="success"/>
					}else{
						<@ms.notify msg="删除失败" type="fail"/>
					}
					location.reload();
				}
			});
		})
		//增加按钮
		$("#addBtn").click(function(){
			location.href = "${managerPath}/mall/product/add.do";
		})
		//搜索按钮触发
		$("#submitSearch").click(function(){
			var search = $("form[name='searchForm']").serializeJSON();
			var params = $('#productListTable').bootstrapTable('getOptions');
			params.queryParams = function(params) {  
		 		$.extend(params,search,{basicCategoryId:a});
	        	return params;  
			}  
			$("#productListTable").bootstrapTable('refresh', {query:$("form[name='searchForm']").serializeJSON()});
		})
	});
</script>


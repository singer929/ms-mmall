<@ms.html5>
	<@ms.nav title="栏目表管理"></@ms.nav>
	<!--@ms.searchForm name="searchForm" isvalidation=true>
		<@ms.searchFormButton>
			 <@ms.queryButton onclick="search()"/> 
		</@ms.searchFormButton>			
	</@ms.searchForm-->
	<@ms.panel>
		<div id="toolbar">
			<@ms.panelNav>
				<@ms.buttonGroup>
					<@ms.addButton id="addColumnBtn"/>
					<@ms.delButton id="delColumnBtn"/>
				</@ms.buttonGroup>
				<@ms.button id="columnBtn" value="规格设置"/>
			</@ms.panelNav>
		</div>
		<table id="columnList" 
			data-show-refresh="true"
			data-show-columns="true"
			data-show-export="true"
			data-method="post" 
			data-side-pagination="server">
		</table>
	</@ms.panel>
	
	<@ms.modal  modalName="delColumn" title="删除栏目" >
		<@ms.modalBody>删除此栏目
			<@ms.modalButton>
				<!--模态框按钮组-->
				<@ms.button  value="确认删除？"  id="deleteColumnBtn"  />
			</@ms.modalButton>
		</@ms.modalBody>
	</@ms.modal>
</@ms.html5>

<script>
	$(function(){
		$("#columnList").bootstrapTable({
			url:"${managerPath}/mall/column/list.do?modelId=${Session.model_id_session?default(0)}",
			contentType : "application/x-www-form-urlencoded",
			queryParamsType : "undefined",
			toolbar: "#toolbar",
			idField: 'categoryId',
            treeShowField: 'categoryTitle',
            parentIdField: 'categoryCategoryId',
	    	columns: [
	    		{ 
	    			checkbox: true
	    		},
		    	{
		        	field: 'categoryTitle',
		        	title: '标题',
		        	align: 'left',
		        	formatter:function(value,row,index) {
		        		var url = "${managerPath}/mall/column/"+row.categoryId+"/edit.do?modelId=${Session.model_id_session?default(0)}";
		        		return "<a href=" +url+ " target='_self'>" + value + "</a>";
		        	}
		        	
		    	},{
		        	field: 'columnType',
		        	title: '属性',
		        	align: 'center',
		        	formatter:function(value,row,index) {
		        		if(value == 1){
		        			return "列表";
		        		}else if(value == 2){
		        			return "单页";
		        		}else if(value == 3){
		        			return "外部链接";
		        		}
		        	}
		    	},{
		        	field: 'columnPath',
		        	title: '链接地址',
		        	align: 'left',
		        	formatter:function(value,row,index) {
		        		return "{ms:global.url/}"+value+"/index.html";
		        	}
		    	},{
		        	field: 'columnListUrl',
		        	title: '列表地址',
		        	align: 'left',
		        	width: '260',
		    	},{
		        	field: 'columnUrl',
		        	title: '内容地址',
		        	align: 'left',
		        	formatter:function(value,row,index) {
		        		if(row.columnType == 1){
		        			return value;
		        		}else{
		        			return "";
		        		}
		        	}
		    	},{
		        	field: 'columnUrl',
		        	title: '封面地址',
		        	align: 'left',
		        	formatter:function(value,row,index) {
		        		if(row.columnType == 2){
		        			return value;
		        		}else{
		        			return "";
		        		}
		        	}
		    	}]
	    })
	})
	//增加按钮
	$("#addColumnBtn").click(function(){
		location.href ="${managerPath}/mall/column/add.do??modelId=${Session.model_id_session?default(0)}"; 
	})
	//删除按钮
	$("#delColumnBtn").click(function(){
		//获取checkbox选中的数据
		var rows = $("#columnList").bootstrapTable("getSelections");
		//没有选中checkbox
		if(rows.length <= 0){
			<@ms.notify msg="请选择需要删除的记录" type="warning"/>
		}else{
			$(".delColumn").modal();
		}
	})
	
	$("#deleteColumnBtn").click(function(){
		var rows = $("#columnList").bootstrapTable("getSelections");
		//$(this).text("正在删除...");
		$(this).attr("disabled","true");
		$.ajax({
			type: "post",
			url: "${managerPath}/mall/column/"+rows[0].categoryId+"/delete.do",
			dataType: "json",
			contentType: "application/json",
			success:function(msg) {
				if(msg.result == true) {
					<@ms.notify msg= "删除成功" type= "success" />
				}else {
					<@ms.notify msg= "删除失败" type= "fail" />
				}
				location.reload();
			}
		})
	});
	//查询功能
	function search(){
		var search = $("form[name='searchForm']").serializeJSON();
        var params = $('#columnList').bootstrapTable('getOptions');
        params.queryParams = function(params) {  
        	$.extend(params,search);
	        return params;  
       	}  
   	 	$("#columnList").bootstrapTable('refresh', {query:$("form[name='searchForm']").serializeJSON()});
	}
</script>
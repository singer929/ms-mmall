<@ms.html5>
	<div id="toolbar">
		<@ms.panelNav>
			<@ms.buttonGroup>
				<@ms.addButton id="addSpecificationBtn"/>
				<@ms.delButton id="delSpecificationBtn"/>
			</@ms.buttonGroup>
		</@ms.panelNav>
	</div>
	<table id="specificationList" 
		data-show-refresh="true"
		data-show-columns="true"
		data-show-export="true"
		data-method="post" 
		data-pagination="true"
		data-page-size="10"
		data-side-pagination="server">
	</table>
	<@ms.modal  modalName="delSpecification" title="规格数据删除" >
		<@ms.modalBody>删除规格
			<@ms.modalButton>
				<!--模态框按钮组-->
				<@ms.button  value="确认删除？"  id="deleteSpecificationBtn"  />
			</@ms.modalButton>
		</@ms.modalBody>
	</@ms.modal>
</@ms.html5>

<script>
	$(function(){
		$("#specificationList").bootstrapTable({
			url:"${managerPath}/mall/specification/list.do",
			contentType : "application/x-www-form-urlencoded",
			queryParamsType : "undefined",
			toolbar: "#toolbar",
	    	columns: [{ checkbox: true},
				    	{
				        	field: 'specificationName',
				        	title: '规格名称',
				        	width:'255',
				        	formatter:function(value,row,index) {
				        		var url = "${managerPath}/mall/specification/form.do?specificationId="+row.specificationId;
				        		return "<a href=" +url+ " target='_self'>" + value + "</a>";
				        	}
				    	},						    	{
				        	field: 'specificationDefaultFields',
				        	title: '默认的字段',
				        	width:'255'
				    	}		]
	    })
	})
	//增加按钮
	$("#addSpecificationBtn").click(function(){
		location.href ="${managerPath}/mall/specification/form.do?specificationCategoryId="+${categoryId?default('')}; 
	})
	//删除按钮
	$("#delSpecificationBtn").click(function(){
		//获取checkbox选中的数据
		var rows = $("#specificationList").bootstrapTable("getSelections");
		//没有选中checkbox
		if(rows.length <= 0){
			<@ms.notify msg="请选择需要删除的记录" type="warning"/>
		}else{
			$(".delSpecification").modal();
		}
	})
	
	$("#deleteSpecificationBtn").click(function(){
		var rows = $("#specificationList").bootstrapTable("getSelections");
		$(this).text("正在删除...");
		$(this).attr("disabled","true");
		$.ajax({
			type: "post",
			url: "${managerPath}/mall/specification/delete.do",
			data: JSON.stringify(rows),
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
        var params = $('#specificationList').bootstrapTable('getOptions');
        params.queryParams = function(params) {  
        	$.extend(params,search);
	        return params;  
       	}  
   	 	$("#specificationList").bootstrapTable('refresh', {query:$("form[name='searchForm']").serializeJSON()});
	}
</script>
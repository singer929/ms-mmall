<@ms.html5>
	<@ms.nav title="订单评价管理"></@ms.nav>
	<@ms.searchForm name="searchForm" isvalidation=true>
			<@ms.searchFormButton>
				<@ms.select 
	 				default="未审核"
	 				value="0"
				    name="commentAudit" 
				    label="审核状态" 
				    list=[{"id":1,"value":"审核通过"},{"id":2,"value":"审核不通过"}]
				    listKey="id" 
				    listValue="value"
				/>
				<@ms.text name="basicTitle" label="商品名称"/>
				 <@ms.queryButton onclick="search()"/> 
			</@ms.searchFormButton>			
	</@ms.searchForm>
	<@ms.panel>
		<div id="toolbar">
			<@ms.panelNav>
				<@ms.button id="audit" value="审核"/>
			</@ms.panelNav>
		</div>
		<table id="orderCommentList" 
			data-show-refresh="true"
			data-show-columns="true"
			data-show-export="true"
			data-method="post" 
			data-pagination="true"
			data-page-size="10"
			data-side-pagination="server">
		</table>
	</@ms.panel>
	
	<@ms.modal  modalName="auditOrderComment" title="通过审查" >
		<@ms.modalBody>是否通过选中记录
			<@ms.modalButton>
				<!--模态框按钮组-->
				<@ms.button  value="不通过"  id="noAuditOrderCommentBtn"  />
				<@ms.button  value="通过"  id="auditOrderCommentBtn"  />
			</@ms.modalButton>
		</@ms.modalBody>
	</@ms.modal>
</@ms.html5>

<script>
	$(function(){
		$("#orderCommentList").bootstrapTable({
			url:"${managerPath}/mall/orderComment/list.do",
			contentType : "application/x-www-form-urlencoded",
			queryParamsType : "undefined",
			toolbar: "#toolbar",
	    	columns: [{ checkbox: true},
				    	{
				        	field: 'basicTitle',
				        	title: '商品名称'
				    	},{
				        	field: 'ocImpression',
				        	title: '印象',
				        	align: 'center'
				    	},{
				        	field: 'ocProduct',
				        	title: '商品符合度',
				        	align: 'center'
				    	},{
				        	field: 'commentContent',
				        	title: '评价内容'
				    	},{
				        	field: 'commentPeopleUser.peopleUserNickName',
				        	title: '用户昵称'
				    	},{
				        	field: 'commentAudit',
				        	title: '审核状态',
				        	align: 'center',
				        	formatter: function (value, row, index){
				        		if(value == 0){
				        			return "未审核"
				        		}else if(value == 1){
				        			return "审核通过"
				        		}else{
				        			return "审核不通过"
				        		}
				        	}
				    	}]
	    })
	})
	
	//审查按钮
	$("#audit").click(function(){
		//获取checkbox选中的数据
		var rows = $("#orderCommentList").bootstrapTable("getSelections");
		//没有选中checkbox
		if(rows.length <= 0){
			<@ms.notify msg="请选择需要审查的记录" type="warning"/>
		}else{
			$(".auditOrderComment").modal();
		}
	})
	
	//不通过按钮
	$("#noAuditOrderCommentBtn").click(function(){
		var rows = $("#orderCommentList").bootstrapTable("getSelections");
		for(var i = 0 ; i < rows.length ; i++){
			rows[i].commentAudit = 2;
		}
		$(this).text("正在审核...");
		$(this).attr("disabled","true");
		update(rows);
	});
	
	//通过按钮
	$("#auditOrderCommentBtn").click(function(){
		var rows = $("#orderCommentList").bootstrapTable("getSelections");
		for(var i = 0 ; i < rows.length ; i++){
			rows[i].commentAudit = 1;
		}
		$(this).text("正在审核...");
		$(this).attr("disabled","true");
		update(rows);
	});
	function update(rows){
		$.ajax({
			type: "post",
			url: "${managerPath}/mall/orderComment/update.do",
			data: JSON.stringify(rows),
			dataType: "json",
			contentType: "application/json",
			success:function(msg) {
				if(msg.result == true) {
					<@ms.notify msg= "审核成功" type= "success" />
				}else {
					<@ms.notify msg= "审核失败" type= "fail" />
				}
				location.reload();
			}
		})
	}
	
	//查询功能
	function search(){
		var search = $("form[name='searchForm']").serializeJSON();
        var params = $('#orderCommentList').bootstrapTable('getOptions');
        params.queryParams = function(params) {  
        	$.extend(params,search);
	        return params;  
       	}  
   	 	$("#orderCommentList").bootstrapTable('refresh', {query:$("form[name='searchForm']").serializeJSON()});
	}
</script>
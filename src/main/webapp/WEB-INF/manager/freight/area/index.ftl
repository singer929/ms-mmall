<!DOCTYPE html>
<@ms.html5>
	<@ms.content>
		<@ms.contentMenu>
			<div style="padding:4px 0 5px 24px; border-bottom:2px solid #ddd;">
				<input type="checkbox" name="ids" id="checkbox" value="">
				<span style="margin-right:37px;">全部</span>
				<@ms.addButton id="addButton"/>
				<@ms.delButton id="delButton"/>
			</div>
           	<#list listArea as listArea>
           		<div style="padding:3px 0 0 24px;">
        		<input type="checkbox" name="ids" id="checkbox" value="${listArea.faId?default(0)}">
        		${listArea.faTitle?default(0)}
        		</div>
	        </#list>
		</@ms.contentMenu>
		<@ms.contentBody width="85%" style="overflow-y: hidden;">
			<div>
				<@ms.text id="faTitle" label="区域名称" name="faTitle" value="${faTitle?default('')}" width="300"  placeholder="请输入区域名称" />
	        	<span style="padding-left:45%"></span>
				<@ms.savebutton id="savebutton"/>
			</div>
			<@ms.form name="areaForm"  isvalidation=true >
	    		<@ms.table head=['编号,90','标题','操作,100'] id="tableConterent">
					<#if categoryJson?has_content && categoryJson!="[]">
				    	<@ms.treeTable treeId="areaAddTree"   tbodyId="tableConterent" json="${categoryJson?default('')}" jsonName="categoryTitle" jsonId="categoryId" jsonPid="categoryCategoryId"/>
				  	<#else>
				     	<tr>
				            <td colspan="3" class="text-center" >
				            	<@ms.nodata/>
							</td>
				      	</tr>                          
					</#if>
				</@ms.table>
				<script id="afterareaAddTree" type="text/x-jquery-tmpl">
					<td>
						<input type="checkbox" id="ids" name="ids">
					</td>
				</script>
			</@ms.form>
		</@ms.contentBody>          
	</@ms.content>
	<!--删除区域-->    
	<@ms.modal modalName="delete" title="删除区域">
		<@ms.modalBody>
		 	确定要删除所选的区域吗?
		</@ms.modalBody>
		<@ms.modalButton>
			<@ms.button class="btn btn-danger rightDelete" value="确定"/>
		</@ms.modalButton>
	</@ms.modal>
</@ms.html5>
<script>
	//修改和保存
	var value="";
	$("#savebutton").click(function(){
		var faTitle = $("input[name=faTitle]").val();
		if(faTitle == ""){
			alert("区域名称不能为空，请重新输入")
			return;
		}
		
		for (var i=0;i<ids.length;i++ ){
		  if(ids[i].checked){ 					//判断复选框是否选中
		  	var dataId =$(ids[i]).parent().parent().attr("data-id");
		 	value=value + dataId+ ","; 	//值的拼凑
		  }
		}
		value=value.substring(0,value.length-1);
		if(value == ""){
			alert("未选择区域城市，请重新选择")
			return;
		}
		$.post("${managerPath}/freightArea/save.do",
		   {
				faTitle:faTitle,
				faCityIds:value
		   }, 
		   function(data,status){
			   	if(data.result){
				   alert("保存成功");
				   window.location.reload();
			   	}else{
				   alert("区域名称已存在，请重新输入");
				   window.location.reload();
			   	}
		   }
		);
	});
	//确认删除
	$(".rightDelete").click(function(){			//删除区域信息
		for (var i=0;i<checkbox.length;i++ ){
		  if(checkbox[i].checked){ 				//判断复选框是否选中
		 	faId=$(checkbox[i]).val(); 			//值的拼凑
		 	value=value + faId+ ","; 
		  }
		}
		value=value.substring(0,value.length-1);
		$.post("${managerPath}/freightArea/delete.do",
		   {
			faIds:value
		   }, 
		   function(data,status){
			   	
		   }
		);
		window.location.reload();
	});
//删除
$("#delButton").click(function(){
	$(".delete").modal();
});
</script>

<@ms.html5>
	<style>
		.areaActive{
			color: #3497db;
		}
	</style>
	<@ms.content>
		<@ms.contentMenu>
			<div style="padding:4px 0 5px 24px; border-bottom:2px solid #ddd;">
				<input type="checkbox" name="checkboxAll" id="checkboxAll" value="">
				<span style="margin-right:37px;">全部</span>
				<@ms.addButton id="addButton"/>
				<@ms.delButton id="delButton"/>
			</div>
           	<#list areas as areaEntity>
    			<div style="padding:3px 0 0 24px;cursor:pointer;" class="" cityIds="${areaEntity.faCityIds?default(0)}" id="${areaEntity.faId?default(0)}" onclick="edit(this)" >
	        		<input type="checkbox" name="checkbox" id="checkbox" value="${areaEntity.faId?default(0)}">
	        		${areaEntity.faTitle?default(0)}
        		</div>
	        </#list>
		</@ms.contentMenu>
		<@ms.contentBody width="85%" style="overflow-y: hidden;">
			<div style="border-bottom:2px solid #ddd;height:45px;padding-top:10px;">
				<span style="font-weight: 700;margin-left:15px;">添加区域</span>
				<@ms.savebutton id="savebutton" style="float:right;margin-top:-6px;margin-right: 5px;"/>
			</div>
			<div style="height:60px;">
				<span style="font-weight: 700;float:left;margin:5px 0 0 8%;">区域名称:</span>
				<@ms.text id="faTitle" name="faTitle" value="" width="300"  placeholder="请输入区域名称" />
			</div>
			<div>
				<span style="font-weight: 700;margin:5px 0 0 7%;">请选择区域:</span>
				<div style="border:2px solid #ddd;width:60%;height:450px;margin:-2% 0 0 15%;overflow:scroll;">
					<@ms.form name="areaForm"  isvalidation=true >
			    		<@ms.table head=['编号,100','操作,150','城市名称'] id="tableConterent">
							<#if categoryJson?has_content && categoryJson!="[]">
						    	<@ms.treeTable treeId="areaAddTree" tmplBefored="true" tmplAfter="false" tbodyId="tableConterent" json="${categoryJson?default('')}" jsonName="categoryTitle" jsonId="categoryId" jsonPid="categoryCategoryId"/>
						  	<#else>
						     	<tr>
						            <td colspan="3" class="text-center" >
						            	<@ms.nodata/>
									</td>
						      	</tr>                          
							</#if>
						</@ms.table>
						<script id="beforedareaAddTree" type="text/x-jquery-tmpl">
							<td>
								<input type="checkbox" id="ids" name="ids">
							</td>
						</script>
					</@ms.form>
				</div>
			</div>
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
	//保存
	$("#savebutton").click(function(){
		var value="";
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
		$.post("${managerPath}/freight/area/save.do",
		   {
				faTitle:faTitle,
				faCityIds:value
		   }, 
		   function(data,status){
			   	if(data.result){
				   alert("保存成功");
				   window.location.reload();
			   	}else{
				   alert("修改成功");
				   window.location.reload();
			   	}
		   }
		);
	});
	//删除
	$("#delButton").click(function(){
		$(".delete").modal();
	});
	//确认删除
	$(".rightDelete").click(function(){			//删除区域信息
		var value="";
		for (var i=0;i<checkbox.length;i++ ){
		  if(checkbox[i].checked){ 				//判断复选框是否选中
		 	faId=$(checkbox[i]).val(); 			//值的拼凑
		 	value=value + faId+ ","; 
		  }
		}
		value=value.substring(0,value.length-1);
		$.post("${managerPath}/freight/area/delete.do",
		   {
				areaIds:value
		   }, 
		   function(data,status){
			   	
		   }
		);
		window.location.reload();
	});
	//全选
	$("#checkboxAll").click(function(){
		var ck = $("#checkbox").is(':checked')
		if($("input[name=checkbox]").is(':checked')){
			$("input[name=checkbox]").prop("checked",false);
		}else{
			$("input[name=checkbox]").prop("checked",true);
		}
	});
	//添加
	$("#addButton").click(function(){
		$("input[name=faTitle]").val("");
		$("input[name=ids]").prop("checked",false);
	});
	//编辑
	function edit(obj){
		$(obj).addClass('areaActive').siblings().removeClass('areaActive');
		var cityIds = obj.getAttribute("cityIds");
		var faTitle = obj.getAttribute("title");
		$("input[name=faTitle]").val(faTitle);
		var value="";
		var cityIds = obj.getAttribute("cityIds");
		var arr = cityIds.split(',');
		for (var i=0;i<ids.length;i++ ){
		  if(ids[i].checked){ 					//判断复选框是否选中
		  	var dataId =$(ids[i]).parent().parent().attr("data-id");
		 	value=value + dataId+ ","; 	//值的拼凑
		  }
		}
		if(cityIds != value){
			var valuearr = value.split(',');
			for(var i=0;i<valuearr.length;i++ ){
				var columnTitleId = "columnTitle" + valuearr[i];
				var columnTitle = $('#'+'columnTitle'+valuearr[i]).find("input")
				columnTitle.prop("checked",false);
			}
			for(var i=0;i<arr.length;i++ ){
				var columnTitleId = "columnTitle" + arr[i];
				var columnTitle = $('#'+'columnTitle'+arr[i]).find("input")
				columnTitle.prop("checked",true);
			}
		}else{
			for(var i=0;i<valuearr.length;i++ ){
				var columnTitleId = "columnTitle" + valuearr[i];
				var columnTitle = $('#'+'columnTitle'+valuearr[i]).find("input")
				columnTitle.prop("checked",true);
			}
			for(var i=0;i<arr.length;i++ ){
				var columnTitleId = "columnTitle" + arr[i];
				var columnTitle = $('#'+'columnTitle'+arr[i]).find("input")
				columnTitle.prop("checked",false);
			}
		}
	}
</script>

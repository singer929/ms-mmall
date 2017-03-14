<@ms.html5>
	<style>
		#areaList {padding-left:20px;}
		#areaList li{line-height:25px;cursor: pointer;}
		#areaList li.sel{color:blue}
	</style>
	<@ms.content>
		<@ms.contentMenu>
			<div style="padding:5px 0 5px 24px; border-bottom:2px solid #ddd;">
				<input type="checkbox" name="checkboxAll" id="checkboxAll" value="">
				<span style="margin-right:10%;">全部</span>
				<@ms.addButton id="addButton"/>
				<@ms.delButton id="delButton"/>
			</div>
       		<ul id="areaList">
           	<#list areas as areaEntity>
        		<li data-id="${areaEntity.faId?default(0)}" data-ids="${areaEntity.faCityIds?default(0)}" data-title="${areaEntity.faTitle?default(0)}">
        			<input type="checkbox" name="checkbox" value="${areaEntity.faId?default(0)}">
	        		${areaEntity.faTitle?default(0)}
	        	</li>
	        </#list>
	        </ul>
		</@ms.contentMenu>
		<@ms.panel style="width: 85%;right: 0; position: absolute;">
			<@ms.nav title="区域信息" style="width: 85%;"><@ms.savebutton id="savebutton"/></@ms.nav>
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
								<input type="checkbox"  name="ids" id="ids">
							</td>
						</script>
					</@ms.form>
				</div>
			</div>
		</@ms.panel>
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
		$("#tableConterent input[type=checkbox]").each(function(){
			if($(this).is(':checked')){ 					//判断复选框是否选中
		  		var dataId =$(this).parent().parent().attr("data-id");
		 		value=value + dataId+ ","; 	//值的拼凑
		  	}
		})
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
		$("input[name=checkbox]").each(function(){
			if($(this).is(':checked')){ 					//判断复选框是否选中
		  		var faId =$(this).val();
		 		value=value + faId+ ","; 	//值的拼凑
		  	}
		})
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
	$("#areaList li").click(function(){
		$("#areaList li").removeClass("sel");
		$(this).addClass("sel");
		var cityIds = $(this).data("ids");
		var faTitle = $(this).data("title");
		$("input[name=faTitle]").val(faTitle);
		var value="";
		var arr = cityIds.split(',');
		$("#tableConterent input[type=checkbox]").each(function(){
			if($(this).is(':checked')){ 					//判断复选框是否选中
		  		var dataId =$(this).parent().parent().attr("data-id");
		 		value=value + dataId+ ","; 	//值的拼凑
		  	}
		})
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
	});
</script>

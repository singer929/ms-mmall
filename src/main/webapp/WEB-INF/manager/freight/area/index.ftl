<@ms.html5>
	<style>
		#areaList {padding-left:20px;}
		#areaList li{line-height:25px;cursor: pointer;}
		#areaList li.sel{color:blue}
	</style>
	<@ms.content>
		<@ms.contentMenu>
			<div style="padding:5px 0 5px 20px; border-bottom:2px solid #ddd;">
				<input type="checkbox" name="checkboxAll" id="checkboxAll" value="">
				<span style="margin-right:18%;">全部</span>
				<@ms.addButton id="addButton"/>
				<@ms.delButton id="delButton"/>
			</div>
       		<ul id="areaList" class="listParam"></ul>
		</@ms.contentMenu>
		<@ms.panel style="width: 85%;right: 0; position: absolute;">
			<@ms.nav title="区域信息" style="width: 85%;"><@ms.savebutton id="savebutton"/></@ms.nav>
			<div style="height:60px;">
				<span style="font-weight:700;float:left;margin:5px 0 0 8%;">区域名称:</span>
				<@ms.text id="faTitle" name="faTitle" value="" width="300"  placeholder="请输入区域名称" />
			</div>
			<div>
				<span style="font-weight: 700;margin:5px 0 0 7%;">请选择区域:</span>
				<div style="height:500px;margin:-2% 0 0 15%;overflow:scroll;">
		    		<#if categoryJson?has_content && categoryJson!="[]">
						<@ms.tree  
							treeId="areaTree" 
							json="${categoryJson?default('')}" 
							ischeck="true"
							jsonId="categoryId" 
							jsonPid="categoryCategoryId" 
							jsonName="categoryTitle"   
							showIcon="false" 
							expandAll="true" 
							getZtreeId="getZtreeId(event,treeId,treeNode);"  
							id="cityList"
						/>
					<#else> 
						<@ms.nodata content="暂无栏目"/>
					</#if>
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
<script id="myTemplate" type="text/x-jquery-tmpl">	
	<#noparse>
	<li class="xin" data-id="${faId}" data-ids="${faCityIds}" data-title="${faTitle}">
		<input type="checkbox" name="checkbox" value="${faId}">
		${faTitle}
	</li>
	</#noparse>
</script>
<script type="text/javascript">
	//左侧
	$(function(){
		areaList();
	});	
	function areaList(){
		var areaList = [] 	 		
		$.post("${managerPath}/freight/area/areaList.do",{},	
			function(data,status){	
				$(".xin").remove();
				$("#myTemplate").tmpl(data).appendTo('.listParam');			//通过tmpl追加数据
			}
		);
	}
	//保存
	$("#savebutton").click(function(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDomeareaTree");
		var nodes = treeObj.getCheckedNodes(true);
		var ids="";
		var faId="";
		for(var i=0;i<nodes.length;i++){
			ids = ids + nodes[i].categoryId + ",";
		}
		ids = ids.substring(0,ids.length-1);
		var faTitle = $("input[name=faTitle]").val();
		if(faTitle == ""){
			$('.ms-notifications').offset({top:43}).notify({
    		    type:'warning',
			    message: { text:'区域名称不能为空，请重新输入'}
			 }).show();
			return;
		}
		if(ids == ""){
			$('.ms-notifications').offset({top:43}).notify({
    		    type:'warning',
			    message: { text:'未选择区域城市，请重新选择'}
			 }).show();
			return;
		}
		
		//判断是否选中
	  	faId = $("#areaList li.sel").data("id");
	  	
		if(faId>0){
			$.post("${managerPath}/freight/area/update.do",
			   {
			   		faId:faId,
					faTitle:faTitle,
					faCityIds:ids
			   }, 
			   function(data,status){
					//保存成功提示
					$('.ms-notifications').offset({top:43}).notify({
						type:'success',
						message: { text:'保存成功！' }
					}).show();
					onAddClick();
					areaList();
			   }
			);
		}else{
			$.post("${managerPath}/freight/area/save.do",
			   {
					faTitle:faTitle,
					faCityIds:ids
			   }, 
			   function(data,status){
				   //保存成功提示
					$('.ms-notifications').offset({top:43}).notify({
						type:'success',
						message: { text:'保存成功！' }
					}).show();
					onAddClick();
					areaList();
			   }
			);
		}
	});
	//删除
	$("#delButton").click(function(){
		$(".delete").modal();
	});
	//确认删除
	$(".rightDelete").click(function(){			//删除区域信息
		$(".delete").modal("hide");
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
		   function(data,status){}
		);
		areaList();
	});
	//全选
	$(function() {
		$("input[name=checkboxAll]").on("click",function(){  
			if(this.checked){   
				$("input[name=checkbox]").each(function(){this.checked=true;});
			}else{   
				$("input[name=checkbox]").each(function(){this.checked=false;});   
			}
		});			
	})
	//添加
	$("#addButton").click(onAddClick);
	function onAddClick(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDomeareaTree");
		treeObj.checkAllNodes(false);
		$("input[name=faTitle]").val("");
		$("input[name=ids]").prop("checked",false);
		$("#areaList li").removeClass("sel");
	}
	
	//编辑
	$(".listParam").delegate("li","click",function(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDomeareaTree");
		$("#areaList li").removeClass("sel");
		$(this).addClass("sel");
		var cityIds = $(this).data("ids");
		var faTitle = $(this).data("title");
		$("input[name=faTitle]").val(faTitle);
		var arr = cityIds.split(',');
		treeObj.checkAllNodes(false);
		for(var i=0;i<arr.length;i++ ){
			treeObj.checkNode(treeObj.getNodeByParam("categoryId",arr[i]));
		}
	});
</script>
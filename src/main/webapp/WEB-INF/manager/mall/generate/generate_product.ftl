<@ms.html5>
	<@ms.nav title="生成商品">	<@ms.button class="btn btn-primary" id="updateProduct" value="生成商品" /></@ms.nav>
	<@ms.panel>
			
			<!--更新信息提示-->
					<p class="alert alert-info" role="alert" >
						<span class="glyphicon glyphicon-pushpin text-lef "></span>
						<a class="alert-link text-lef" style="margin-left: 12px;">
			            	根据时间与分类生成商品
						</a>
					</p>
			
			<!--选择栏目和选择更新时间-->
			<@ms.form name="generateArticle">
				<@ms.formRow label="类别" width="200">
	        			<#if list?has_content>
							<@ms.treeInput  treeId="inputTree" json="${list?default('')}" jsonId="categoryId" jsonPid="categoryCategoryId" jsonName="categoryTitle" addNodesName="所有栏目" buttonText="选择更新的栏目" inputName="columnId"  showIcon="true" expandAll="true" />
	    				<#else> 
							<@ms.treeInput  treeId="errorTree"  buttonText="暂无数据" />
	    				</#if>	
	    		</@ms.formRow>
	            <@ms.date name="dateTime" label="指定时间" single=true readonly="readonly" width="200" value="${now?string('yyyy-MM-dd')}" validation={"required":"true", "data-bv-notempty-message":"必填项目"} placeholder="点击该框选择时间段"  />
			</@ms.form>		
			
	</@ms.panel>
</@ms.html5>

		<!--点击进行按钮提交-->
		<script>
			$("#updateProduct").click(function() {
				
				var columnId = 0;
				if($("input[name='columnId']").val() !="" && $("input[name='columnId']").val().length>0){
					columnId = $("input[name='columnId']").val();
				}
				
				var URL="${managerPath}/mall/generate/"+columnId+"/generateProduct.do";
				if($("input[name=generateWay]").is(':checked')==true){
					URL="${managerPath}/mall/specification/generate/"+columnId+"/generateProduct.do";
				}
				var DATA = "dateTime=" + $("input[name='dateTime']").val();
				$(this).html("更新中..").attr("disabled", "disabled");
				
				$(this).request({url:URL,data:DATA,type:"json",method:"post",func:function(msg) {
					$("#updateProduct").html("更新商品").removeAttr("disabled");
					//回调处理方式
					if (msg) {
						alert("更新商品成功");
					} else {
						alert("更新商品失败");
					}
				}});
				
			});
		</script>

<@ms.html5>
	<@ms.nav title="更新商城主页">	<@ms.button class="btn btn-primary" id="updateIndex" value="生成主页" /> 	<@ms.panelNavBtnSave title="" id="viewIndex" value="预览主页" /></@ms.nav>
	<@ms.panel>
						<p class="alert alert-info" role="alert">
							<span class="glyphicon glyphicon-pushpin text-lef "></span>
							<a class="alert-link text-lef" style="margin-left: 12px;">
			            		更新主页，如果系统存在引导页面可以手动修改主页位置文件名,default.html引导页面index.html主页。
							</a>
						</p>		
		<@ms.form name="generateIndex">
			<@ms.formRow label="选择主页模板" width="200">
					<select class="form-control" id="select_id"></select>
					<input type="hidden" name="url"/>				
			</@ms.formRow>
			<@ms.text  id="position" label="选择主页位置" width="200" value="index.html" placeholder="输入主页位置" name="position"  help="注:主页位置htm文件名一般为index.html或default.html"/>
		</@ms.form>
	</@ms.panel>
</@ms.html5>	

		<script>
			$(function(){
				//点击一键更新主页时，进行主页更新
				$("#updateIndex").click(function(){
					var url = $("#select_id").val();
					$("#view").hide();
					var position =$("input[name='position']").val();
					var URL="${managerPath}/mall/generate//generateIndex.do";
					if($("input[name=generateWay]").is(':checked')==true){
						URL="${managerPath}/mall/specification/generate//generateIndex.do";
					}
					$(this).html("更新中..")
					$.ajax({
						type: "post",
						url:URL,
						data:"url="+url+"&position="+position,
						dataType:"json",
						success:function(msg){
							if(msg){
								$("#view").show();
								alert("更新成功");
							}else{
								alert("更新失败,模版路径不存在");
							}
							$("#updateIndex").html("更新主页")
						}
					});
					
				});
				//点击预览时，进行预览
				$("#viewIndex").click(function(){
					var position =$("input[name='position']").val();
					window.open("${managerPath}/mall/generate/"+position+"/viewIndex.do");
				});
				
				//获取模板下的htm文件
				$.ajax({
						type: "post",
						url:"${managerPath}/template/queryTemplateFileForColumn.do",
						dataType:"json",
						success:function(msg){
							if(msg.length==0){
								$("#select_id").append("<option value='' >暂无文件</option>")
							}
							for(var i = 0;i<msg.length;i++){
								$("#select_id").append("<option value="+msg[i]+">"+msg[i]+"</option>");
								//如果存在index.html
								if(msg[i]=="index.html" || msg[i]=="index.htm"){
									$("#select_id").find("option[value='"+msg[i]+"']").attr("selected",true);
								}
							}
							
						}
				});
			});
		</script>

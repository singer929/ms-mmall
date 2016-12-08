<!DOCTYPE html>
<html lang="en">
<head>
<#include "${managerViewPath}/include/meta.ftl"/>
</head>
<style>
	.container{margin:0;padding:0;width:auto}
	 hr{margin-top:9px;margin-bottom:9px;padding:0;}
	.ms-button-group{padding:0px 0px 8px 0px}
	.row {margin-left:0;margin-right:0}
	.form-horizontal .form-group{margin-left:0;margin-right:0}
	.form-group{overflow: hidden;}
	.bs-example>.dropdown>.dropdown-menu {position: static;margin-bottom: 5px;clear: left;}
	.bs-example>.dropdown>.dropdown-toggle {float: left;}
	.padding-zero{padding:0;}
	/*链接样式*/
	.link-style a:hover{color:#000;}
	.link-style a:visited{color:#000;}
	.operate a:visited{color:#428BCA;}
	.form-inline .form-group {display: inline-block;margin-bottom: 0;vertical-align: middle;}
	.dedeteRight{width: 32%;margin: 0 auto;overflow: hidden;}
	/*弹出窗口样式*/
	#WindowDialog .modal-dialog{width:auto;}
	.control-label{font-weight:normal;font-size:14px;}
	.has-error .form-control-feedback{float:right; margin-top:-24px; margin-right:5px; color:#A94442;}
	.has-success .form-control-feedback{float:right; margin-top:-24px; margin-right:5px; color:#3C763D;}
	.form-control{ padding-right:22px;} 
</style>
<body>
	<div class="container-fluid link-style">
	
		<!--顶部   开始-->
		<div class="row rowpadding3">
			<div class="col-md-12">
				<h3 class="page-title bottomLine">
					商品管理
					<small>规格列表</small>
				</h3>
			</div>
		</div>
		<!--顶部  结束-->
		
		<hr>
		<!--------内容 部分  开始-------->
		<div class="row">
			<!--功能按键部分 开始-->
			<div class="form-group">
				<button  type="button" class="btn btn-success col-md" id="addSpecifications">
					新增规格
					<span class="glyphicon glyphicon-plus"></span>
				</button>
			</div>
			<!--功能按键部分 结束-->
			<!-- Table 开始 -->
			<table class="table table-bordered  table-hover">
				<thead>
		        	<tr>
		        		<td colspan="6" class="text-left">
	                     	<i class="glyphicon glyphicon-pushpin"></i>
	                     	规格列表
		        		</td>
		        	</tr>
			        <tr>
			            <th class="col-md-1 col-xs-2 text-center">规格编号</th>
			            <th class="col-md-3 col-xs-3 text-center">规格名称</th>
			            <th class="col-md-6 col-xs-5 text-center">规格型号</th>
			            <th class="col-md-2 col-xs-2 text-center">操作</th>
			        </tr>
		        </thead>
		        <tbody>
		        	<#if list?has_content>
		        		<#list list as specifications>
				        	<tr data-id="${specifications.specificationsId?c?default(0)}">
					        	<td class="text-center">${specifications.specificationsId?c?default(0)}</td>
					        	<td class="text-center name">${specifications.specificationsName?default("暂无")}</td>
					        	<td class="field">${specifications.specificationsField?default("暂无")}</td>
					        	<td class="text-center">
				                    <a class="btn btn-xs red tooltips edit-btn" data-id="${specifications.specificationsId?c?default(0)}" data-toggle="tooltip"  data-original-title="编辑商品">
			                     		<i class="glyphicon glyphicon-pencil"></i>
			                    	</a>
			                    	 <a class="btn btn-xs red tooltips del-btn" data-id="${specifications.specificationsId?c?default(0)}" data-toggle="tooltip" data-original-title="删除商品">
			                     		<i class="glyphicon glyphicon-trash"></i>
			                    	</a>			        	
					        	</td>
					        </tr>
				        </#list>
			        <#else>
			           	<td colspan="4" class="text-center">
			            	<p class="alert alert-info" role="alert" style="margin:0">
			            		<span class="glyphicon glyphicon-plus"></span>
			            		<a href="#" class="alert-link">
			            			您还没有添加商品规格，点击添加商品规格
			            		</a>
						  	</p>
						</td>			        	
			        </#if>
		        </tbody>
			</table>
			<!-- Table 结束 -->
		</div>
		<!--------内容 部分  结束-------->
		<!-- 弹出框开始 -->
		<div class="modal fade" id="modal" style="display:hidden;">
			<div class="modal-dialog">
				<div class="modal-content">
					<!-- 头部开始 -->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">
							新增商品规格
						</h4>
					</div>
					<!-- 头部结束 -->
					<!-- 内容开始 -->
					<div class="modal-body">
						<form  class="form-horizontal" role="form" id="modalFrom">
							<div class="form-group">
								<label class="col-md-3 col-xs-2 control-label" >规格名称:</label>
								<div class="col-md-5  col-xs-5">
									<input type="text" class="form-control"  name="specificationsName" placeholder="请输入规格名称">
									<input type="hidden" value="0" name="specificationsId">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 col-xs-2 control-label" >规格型号:</label>
								<div class="col-md-8  col-xs-8">
									<textarea rows="3" class="form-control" name="specificationsField" placeholder="请输入规格型号,多个用','号隔开"></textarea>
									<br>
									<p class="alert alert-info" role="alert" style="margin:0">
						            	<span class="glyphicon glyphicon-pushpin text-lef "></span>
						            	<strong>提示:</strong>
										多个规格型号用","号隔开
									</p>	
								</div>
							</div>							
						</form>
					</div>
					<!-- 内容结束 -->
					<!-- 按钮开始 -->
				    <div class="modal-footer" style="margin-top:0px;">
				        <button type="button" id="saveOrUpdateBtn" class="btn btn-primary" style="float:right;">保存</button>
				        <button type="button" class="btn btn-default closeSaveModal" data-dismiss="modal" style="float:right; margin-right:10px;">关闭</button>
				    </div>						
					<!-- 按钮结束 -->
				</div>
			</div>	
		</div>		
		<!-- 弹出框结束 -->
	</div>
	<#if page?has_content>
		<@showPage page=page/>
	</#if>
	
	<!--引用弹出框插件-->
	<@warnModal modalName="Model"/>	
</body>
<script>
	//引入表单验证框架
	var bootstrapValidator;
	//表单验证函数
	function bindValidate(){
		bootstrapValidator = $("#modalFrom").bootstrapValidator({
				feedbackIcons: {
	                valid: 'glyphicon glyphicon-ok',
	                invalid: 'glyphicon glyphicon-remove',
	                validating: 'glyphicon glyphicon-refresh'
	            },
		       	fields: {
		            specificationsName: {
		                validators: {
		                    notEmpty: { message: '规格名称不能为空'},
		                    stringLength: {min: 1,max:12,message: '规格名称介于1-12个字符'}
		                }
		            },
		            specificationsField: {
		                validators: {
		                    stringLength: {max: 200,message: '规格名称不能大于200'}
		                }
		            },
		        }
		     });
		}

	$(function(){
		//点击新增
		$("#addSpecifications").bind("click",function(){
			//清空弹出框中输入框的数据 
			$("#modalFrom input[name='specificationsName']").val('');
			$("#modalFrom textarea[name='specificationsField']").val('');
			$("#modalFrom input[name='specificationsId']").val(0);
			//重置验证数据
			$("#modalFrom input[name='specificationsName']").removeAttr("data-bv-field");
			$("#modalFrom textarea[name='specificationsField']").removeAttr("data-bv-field");
			//加载默认显示值
			$("#saveOrUpdateBtn").text("保存");
			$("modal modal-title").text("新增商品规格");
			//加载默认请求URL
			$("#modalFrom").attr("action",base+"${baseManager}/mall/specifications/save.do");
			$("#modal").modal();
		});
		
		//点击更新
		$(".edit-btn").on("click",function(){
			var id = $(this).attr("data-id");
			var specificationsName = $("table tbody tr[data-id='"+id+"'] .name").text();
			var specificationsField = $("table tbody tr[data-id='"+id+"'] .field").text();
			//加载弹出框默认数据
			$("#modalFrom input[name='specificationsName']").val(specificationsName);
			$("#modalFrom textarea[name='specificationsField']").val(specificationsField);
			$("#modalFrom input[name='specificationsId']").val(id);	
			//加载默认显示值
			$("#saveOrUpdateBtn").text("更新");
			$("modal modal-title").text("更新商品规格");
			//加载默认请求URL
			$("#modalFrom").attr("action",base+"${baseManager}/mall/specifications/update.do");			
			$("#modal").modal();	
		});
		
		//点击弹出框保存按钮
		$("#saveOrUpdateBtn").on("click",function(){
			//调用表单验证
			bindValidate();
			if(bootstrapValidator.data('bootstrapValidator').validate().isValid()){
				//获取用户输入的规格型号
				var specificationsField = $("#modalFrom textarea[name='specificationsField']").val();
				//判断用户是否输入规格型号
				if(specificationsField != null && specificationsField != ""){
					//将规格型号中的全角转换成半角
					specificationsField = changeDBC(specificationsField);
					$("#modalFrom textarea[name='specificationsField']").val(specificationsField);
				}
				//获取规格ID
				var specificationsId = $("#modalFrom input[name='specificationsId']").val();
			
				//改变按钮样式,移除点击事件
				$(this).attr("class","btn btn-info");
				$(this).unbind("click");
				
				var data = $("form").serialize();
				
				var url = $("#modalFrom").attr("action");
				//发送添加的ajax请求
				$.ajax({
					type:"POST",
					data:data,
					dataType:"json",
					url:url,
					success:function(msg){
						if(msg.result == true){
							if(specificationsId>0){
								alert("更新成功!");
							}else{
								alert("新增成功!");
							}
							location.href=base+"${baseManager}/mall/specifications/list.do";
						}else{
							if(specificationsId>0){
								alert("更新失败!");
							}else{
								alert("新增失败!");
							}
							$("#saveOrUpdateBtn").attr("class","btn btn-info");
						}
					},
				});
			}
		});
		
		//删除规格
		$(".del-btn").bind("click",function(){
			var specificatiosId = $(this).attr("data-id");
			if(!(specificatiosId>0)){
				alert("删除失败!");
			}
	  		warnModel("确定要删除该规格？","删除规格","deleteSpecificatios("+specificatiosId+")");
		});
		
	});

	function deleteSpecificatios(specificatiosId){
		//发送删除请求
		$.ajax({
			type:"POST",
			url:base+"${baseManager}/mall/specifications/"+specificatiosId+"/delete.do",
			dataType:"json",
			success:function(msg){
					if(msg.result == true){
						alert("删除成功!");
						location.href=base+"${baseManager}/mall/specifications/list.do";
					}else{
						alert("删除失败!");
					}					
			},
		});
	}	
	
	function changeDBC(str){
		var _str = str.split(",");
		var returnStr = "";
		//去除多余的逗号
		for(var i=0;i<_str.length;i++){
			if(_str[i] != null && _str[i] != "" && i < (_str.length-1)){
				returnStr += _str[i]+",";
			}
			if(_str[i] != null && _str[i] != "" && i == (_str.length-1)){
				returnStr += _str[i];
			}
		}
		if(returnStr != ""){
			var _returnStr = returnStr.split("，");
			returnStr = "";
			//将全角的逗号转换成半角
			for(var n=0;n<_returnStr.length;n++){
				if(_returnStr[n] != null && _returnStr[n] != "" && n < (_returnStr.length-1)){
					returnStr += _returnStr[n]+",";
				}
				
				if(_returnStr[n] != null && _returnStr[n] != "" && n == (_returnStr.length-1)){
					returnStr += _returnStr[n];
				}
			};
		}
		return returnStr;
	}
</script>
</html>


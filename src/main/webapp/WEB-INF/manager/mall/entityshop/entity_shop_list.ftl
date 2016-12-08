<!DOCTYPE html>
<html lang="en">
<head>
<#include "${managerViewPath}/include/meta.ftl"/>
</head>

<body>
	<div class="container-fluid link-style">
		<!--顶部   开始-->
		<div class="row rowpadding3">
			<div class="col-md-12">
				<h3 class="page-title bottomLine">
					门店管理
					<small>门店列表</small>
				</h3>
			</div>
		</div>
		<!--顶部  结束-->
		<hr>
		<!--------内容 部分  开始-------->
		<div class="row">
			<!--功能按键部分 开始-->
			<div class="form-group">
				<a href="${managerViewPath}/mall/entityShop/add.do">
					<button  type="button" class="btn btn-success col-md" >新增门店&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
				</a>
			</div>
			<table class="table table-bordered table-hover">
				<thead>
		        	<tr>
		        		<td colspan="6" class="text-left">
	                     	<i class="glyphicon glyphicon-pushpin"></i>
	                     	门店列表
		        		</td>
		        	</tr>
			        <tr>
			            <th class="col-md-1 text-center">门店编号</th>
			            <th class="col-md-3 text-center">门店名称</th>
			            <th class="col-md-3 text-center">门店电话</th>
			            <th class="col-md-3 text-center">创建时间</th>
			            <th class="col-md-4 text-center">操作</th>
			        </tr>
		        </thead>
		        <tbody>
		        	<#if listMallEntityShop?has_content>
		           		<#list listMallEntityShop as listMallEntityShop>
		        	<tr> 
			           	<td class="text-center">${listMallEntityShop.basicId?c?default(0)}</td>
			            <td class="text-center">${listMallEntityShop.basicTitle?default(0)}</td>
			            <td class="text-center">${listMallEntityShop.entityShopPhone?default(0)}</td>
			            <td class="text-center">${listMallEntityShop.basicDateTime?default("")}</td>
			            <td class="text-center">
		                    <a class="btn btn-xs red tooltips edit-btn" data-rid="" data-toggle="tooltip"  data-original-title="编辑门店"  data-id="${listMallEntityShop.basicId?c?default(0)}">
	                     		<i class="glyphicon glyphicon-pencil"></i>
	                    	</a>
	                    	 <a class="btn btn-xs red tooltips del-btn " data-toggle="tooltip" data-original-title="删除门店" data-id="${listMallEntityShop.basicId?c?default(0)}">
	                     		<i class="glyphicon glyphicon-trash"></i>
	                    	</a>
						</td>
			        </tr>
			        </#list>
		           	<#else>
		           	<td colspan="6" class="text-center">
		            	<p class="alert alert-info" role="alert" style="margin:0">
		            		<span class="glyphicon glyphicon-plus"></span>
		            		<a href="${managerViewPath}/mall/entityShop/add.do" class="alert-link">
		            		您还没有添加门店，点击添加门店
		            		</a>
					  	</p>
					</td>
		          	</#if>
		        </tbody>
			</table>
		</div>
	</div>
	<#if page?has_content>
	<@showPage page=page/>
	</#if>
	<!--删除模态框-->
	<@warnModal modalName="deleteWarn"/>
</body>
</html>
<script>
	//删除门店
	function deleteEntityShop(basicId){
		var actionUrl="${managerPath}/mall/entityShop/"+basicId+"/delete.do";
		$.ajax({
			type: "post",
			url:actionUrl,
			dataType:"json",
			success:function(msg){
				alert("删除成功");
				$("#warndeleteWarnOk").attr("disabled", true);
				location.href=msg.resultMsg; 
			}
		});
	}
	//文档加载事件
	$(function(){
		//点击删除按钮
		$(".del-btn").click(function(){
			var basicId=$(this).attr("data-id");
			$("#warndeleteWarnOk").attr("disabled", false);
			warndeleteWarn("是否删除该门店？","删除门店","deleteEntityShop("+basicId+")");
		});
		//点击编辑按钮
		$(".edit-btn").click(function(){
			var basicId=$(this).attr("data-id");
			location.href="${managerViewPath}/mall/entityShop/"+basicId+"/edit.do";
		});
	});
</script>


<!DOCTYPE html>
<html lang="en">
<head>
<#include "${managerViewPath}/include/meta.ftl"/>
</head>
<style>
	.padding0{padding:0px;margin-bottom:10px;margin-right:10px;}
	a:hover{
		text-decoration:none;
	}
</style>
<body>
	<@ms.content>
		<@ms.contentBody width="100%">
		<@ms.contentNav title="代理管理">
		</@ms.contentNav >
			<@ms.contentPanel>
					<@ms.panelNav>
					<@ms.panelNavBtnGroup>
							<@ms.panelNavBtnAdd id="addAgency" />
							<@ms.panelNavBtnDel id="delAgency" />
					</@ms.panelNavBtnGroup>
					</@ms.panelNav>
				<@ms.table head=['<input type="checkbox" id="allCheck" value="全选" data-original-title="点击全选" data-toggle="tooltip" >','编号','姓名','班级','电话','代理码','所在城市']> 	
		        	<#if agencyList?has_content>
		           		<#list agencyList as agency>
		        		<tr data-id="${agency.agencyId?c?default(0)}"> 
		        			<td class="text-center"><input type="checkbox" name="agencyIds" value="${agency.agencyId?c?default(0)}"></td>
		        			<td class="text-center">${agency.agencyId?c?default(0)}</td>
			           		<td class="text-center">
			           			<a class="btn btn-xs red tooltips edit-btn" data-rid="" data-toggle="tooltip"  data-original-title="编辑代理信息"  data-id="${agency.agencyId?c?default(0)}">
			           				${agency.agencyRealName?default("还未填写")}
			           			</a>
			           		</td>
			            	<td class="text-center">${agency.agencyClass?default("还未填写")}</td>
			            	<td class="text-center">${agency.agencyPhone?default("还未填写")}</td>
			            	<td class="text-center">${agency.agencyCode?default("还未填写")}</td>
			            	<td class="text-center">${agency.agencyCityTitle?default("还未填写")}</td>
			        	</tr>
			        </#list>
		           	<#else>
		           	<td colspan="7" class="text-center">
		            		<@ms.nodata/>
					</td>
		          	</#if>
		       </@ms.table>
			</@ms.contentPanel>
			</@ms.contentBody>
		</@ms.content>
	<#if page?has_content>
	<@showPage page=page/>
	</#if>
	<!--删除模态框-->
	<@warnModal modalName="deleteWarn"/>
</body>
</html>
<script>
	$(function(){
		//点击新增代理按钮，跳转到新增代理页面
		$("#addAgency").click(function(){
			location.href="${managerViewPath}/agency/agency/add.do";
		});
		
		$("#delAgency").click(function(){
			if($("input[name='agencyIds']:checked").length<=0){
				alert("请选择要删除的代理");
				return;
			}
			alert($("input[name='agencyIds']").serialize());
			$(this).request({
						method:"post",
						url:"${managerViewPath}/agency/agency/delete.do",
						data:$("input[name='agencyIds']").serialize(),
						func:function(msg) {
							var obj = jQuery.parseJSON(msg);
							if(obj.result){
								alert("商品"+html+"成功");
								location.href="${basePath}"+obj.resultMsg;
							}
						}
				});
		});
		//点击对应的记录信息，进入代理编辑页面
		$(".edit-btn").click(function(){
			var agencyId = $(this).attr("data-id");
			location.href="${managerViewPath}/agency/agency/"+agencyId+"/edit.do";
		});
		
		//全选
		   	$("#allCheck").click(function(){  
				if(this.checked){   
					$("input[name='agencyIds']").each(function(){this.checked=true;});
				}else{   
					$("input[name='agencyIds']").each(function(){this.checked=false;});   
				}
			}); 
			
		
	});
</script>


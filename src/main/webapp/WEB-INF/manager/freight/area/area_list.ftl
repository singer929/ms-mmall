<@ms.html5>
	<@ms.nav title="区域管理"><@ms.addButton  id="addButton"/></@ms.nav>
	<@ms.panel>
		<@ms.table head=['编号,40', '城市名,240','操作,20']>
        	<#if listArea?has_content>
	           	<#list listArea as listArea>
		        	<tr> 
			           	<td>${listArea.faId?default(0)}</td>
			           	<td>${listArea.faTitle?default(0)}</td>
			           	<td>
			           		<a class="btn btn-xs red tooltips editclumnTree" onclick="editclumnTree(${listArea.faId?default(0)})" data-toggle="tooltip" data-id="${listArea.faId?default(0)}" data-original-title="编辑">                
			           			<i class="glyphicon glyphicon-pencil"></i>              
			           		</a>
			           		<a class="btn btn-xs red tooltips deleteclumnTree" onclick="deleteclumnTree(${listArea.faId?default(0)})" data-toggle="tooltip" data-id="${listArea.faId?default(0)}" data-original-title="删除">
								<i class="glyphicon glyphicon-trash"></i>              
			           		</a>
			           	</td>
			        </tr>
		        </#list>
	           	<#else>
	           	<td colspan="7"  class="text-center">
	            	<@ms.nodata/>
				</td>
          	</#if> 
		</@ms.table>
		
		<!--删除栏目-->    
		<@ms.modal modalName="delete" title="删除区域">
			  <@ms.modalBody>
			  		确定要删除所选的区域吗?
		     </@ms.modalBody>
			 <@ms.modalButton>
		 		<@ms.button class="btn btn-danger rightDelete" value="确定"/>
		 	</@ms.modalButton>
		</@ms.modal>
		
	</@ms.panel>
</@ms.html5>

<script type="text/javascript" >
var columnId=""
$(function(){
	
	//增加用户
	$("#addButton").click(function(){
		window.location.href="areaAdd.do";
	});
	//确认删除
	$(".rightDelete").click(function(){
		$.post("${managerPath}/freight/areaDel.do",
		   {
			faId:columnId
		   }, 
		   function(data,status){
		   	
		   }
		);
		 window.location.reload();
	});
});
//删除
function deleteclumnTree(num){
	columnId = num;
	$(".delete").modal();
}
//编辑
function editclumnTree(num){
	columnId = num;
	$.post("${managerPath}/freight/areaDel.do",
	   {
		faId:columnId
	   }, 
	   function(data,status){
	   	
	   }
	);
	window.location.href="alter.do";
}
</script>
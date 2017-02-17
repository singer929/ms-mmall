<@ms.html5>
    <@ms.nav title="运费管理" back=false>
    	<@ms.panelNav>
    		<@ms.buttonGroup>
				<@ms.addButton url=""/>
			</@ms.buttonGroup>
    	</@ms.panelNav>
	</@ms.nav>
    <@ms.panel> 
		<@ms.panelNav>
			<!--@ms.menuButton links=[{"click":"on","name":"上架"},{"click":"off","name":"下架"}] name="批量操作"/-->		
		</@ms.panelNav>
		
			<@ms.table head=['编号,80', '快递公司200','基础运费,150','基础运费数量,150','增长运费,150','增长数量,150'] checkbox="ids">
		        	<#if listProduct?has_content>
		           	<#list listProduct as listProduct>
			        	<tr> 
			        		<td>
								<input type="checkbox" name="ids" value="">
					        </td>
				           	<td></td>
				            <td>
				            	<a class="btn btn-xs red tooltips edit-btn"  data-toggle="tooltip"  >
				            	
				            	</a>
				            </td>
				            <td></td>
				            <td></td>
				            <td style="color:red"></td>
				            <td></td>
				        </tr>
			        </#list>
		           	<#else>
		           	<td colspan="7"  class="text-center">
		            	<@ms.nodata/>
					</td>
		          	</#if>
			</@ms.table>				
    </@ms.panel>
</@ms.html5>
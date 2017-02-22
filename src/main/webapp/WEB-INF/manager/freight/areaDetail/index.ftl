<@ms.html5>
    <@ms.panel> 
    	<@ms.nav title="区域运费管理"><@ms.saveButton id="saveButton" /> </@ms.nav>
		<@ms.contentMenu>
			<div style="margin-top:50px;"></div>
           	<#list listArea as areaEntity>
           		<div style="padding:3px 0 0 40px;cursor:pointer;" id="${areaEntity.faId?default(0)}" onclick="edit(${areaEntity.faId?default(0)})">
	        		${areaEntity.faTitle?default(0)}
        		</div>
	        </#list>
		</@ms.contentMenu>
		<@ms.contentBody width="85%" style="overflow-y: hidden;">
			<@ms.table head=['编号,80','快递公司,200','基础运费,150','基础运费数量,150','增长运费,150','增长数量,150'] checkbox="ids" >
				<#list list as listEntity>
					<tr>
			           	<td>
							<input type="checkbox" name="ids" value="">
				        </td>
				        <td>${listEntity.categoryId?default('')}</td>
				        <td>${listEntity.categoryTitle?default('')}</td>
				        <td>
					        <#list faList as faListEntity>
						        <#if listEntity.categoryId = faListEntity.fadExpressId && faListEntity.fadAreaId = 188>
							        <@ms.text width="100" value="${faListEntity.fadBasePrice?default('')}" style = "padding-left:0px;"/>
							    </#if>
					        </#list>
				        </td>
				        <td>
				         <#list faList as faListEntity>
						    <#if listEntity.categoryId = faListEntity.fadExpressId && faListEntity.fadAreaId = 188>
				        	${faListEntity.fadBaseAmount?default("")}
				        	</#if>
				        </#list>
				        </td>
				        <td>
				         <#list faList as faListEntity>
						    <#if listEntity.categoryId = faListEntity.fadExpressId && faListEntity.fadAreaId = 188>
				        	${faListEntity.fadIncreasePrice?default("")}
				        	</#if>
				        </#list>
				        </td>
				        <td>
				         <#list faList as faListEntity>
						    <#if listEntity.categoryId = faListEntity.fadExpressId && faListEntity.fadAreaId = 188>
				        	${faListEntity.fadIncreaseAmount?default("")}
				        	</#if>
				        </#list>
				        </td>
					</tr>
				</#list>
			</@ms.table>
		</@ms.contentBody>
    </@ms.panel>
</@ms.html5>
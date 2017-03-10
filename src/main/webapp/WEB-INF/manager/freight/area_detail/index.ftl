<@ms.html5>
	<style>
		.areaActive{
			color: #3497db;
		}
	</style>
	<@ms.panel>
		<@ms.contentMenu>
			<div style="margin-top:10px;"></div>
           	<#list areas as areaEntity>
           		<#if areaEntity_index == 0>
	           		<div style="padding:3px 0 0 40px;cursor:pointer;" class="areaActive" data-id="${areaEntity.faId?default(0)}" onclick="getFaId(this)">
		        		${areaEntity.faTitle?default(0)}
	        		</div>
        		<#else>
        			<div style="padding:3px 0 0 40px;cursor:pointer;" class="" data-id="${areaEntity.faId?default(0)}" onclick="getFaId(this)">
	        		${areaEntity.faTitle?default(0)}
        		</div>
        		</#if>
	        </#list>
		</@ms.contentMenu>
		<@ms.contentBody width="85%" style="overflow-y: hidden;">
			<table id="orderTable"
			data-toolbar="#toolbar"
			data-detail-view="true" 
			data-show-refresh="true"
	        data-show-columns="true"
	        data-show-export="true"
			data-method="post" 
			data-detail-formatter="detailFormatter" 
			data-pagination="true"
			data-page-size="1"
			data-side-pagination="server">
		</table>
		</@ms.contentBody>
	</@ms.panel>
</@ms.html5>
<script>
function getFaId(obj){
	//对应bootstrap-table框架的控制
	var areaId = $(obj).data("id");
	$("#orderTable").bootstrapTable({
		url:"${managerPath}/freight/areaDetail/list.do?faId="+areaId,
		contentType: "application/x-www-form-urlencoded",
		queryParams:function(params) {
			return  $.param(params)+"&pageSize="+ params.limit+"&pageNo="+(params.offset+1)+"&"+$("#searchForm").serialize();
		},
	    columns: [{ checkbox: true},{
	        field: 'fadExpress.categoryId',
	        title: '订单号'
	    }, {
	        field: 'fadExpress.categoryTitle',
	        title: '下单用户'
	    }, {
	        field: 'fadBasePrice',
	        title: '订单总额'
	    }, {
	        field: 'fadBaseAmount',
	        title: '运费金额'
	    }, {
	        field: 'fadIncreasePrice',
	        title: '订单状态'
	    }, {
	        field: 'fadIncreaseAmount',
	        title: '支付方式'
	    }]
    }); 		
}
</script>

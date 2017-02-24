<@ms.html5>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.js"></script>
<!-- Latest compiled and minified Locales -->
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/locale/bootstrap-table-zh-CN.min.js"></script>
    <@ms.nav title="商品管理" back=false>
	</@ms.nav>
    <@ms.searchForm name="searchForm" action="${managerPath}/mall/order/list.do">
    		<@ms.text name="orderNo" label="订 单 号"/>
    		<@ms.text name="orderUserName" label="收货人"/>
    		<@ms.text name="orderPhone" label="联系电话"/>
 			<@ms.select 
 				default="请选择"
 				defaultValue="-1"
			    name="orderStatus" 
			    label="订单状态" 
			    list=orderStatus
			    listKey="id" 
			    listValue="name"
			    value="${productShelf?default('')}"
			/>			
			
			<@ms.searchFormButton>
				 <@ms.queryButton onclick="search()"/> 
			</@ms.searchFormButton>			
	</@ms.searchForm>
    <@ms.panel>
    	<div id="toolbar">
    		<@ms.delButton value="取消订单" id="delButton" icon="" />
    		<@ms.updateButton value="发货" id="sendButton" icon="" />
    	</div>
		<table id="testTable"
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
    </@ms.panel>
</@ms.html5>	        
 <script>
 		$(function() {
	        $("#testTable").bootstrapTable({
	        		url:"${managerPath}/mall/order/list.do",
	        		contentType : "application/x-www-form-urlencoded",
	        		queryParams:function(params) {
						return  $.param(params)+"&pageSize="+ params.limit+"&pageNo="+(params.offset+1)+"&"+$("#searchForm").serialize();
					},
				    columns: [{ checkbox: true},{
				        field: 'orderNo',
				        title: '订单号'
				    }, {
				        field: 'peopleUser.peopleUserNickName',
				        title: '下单用户'
				    }, {
				    	sortable:true,
				        field: 'orderPrice',
				        title: '订单总额'
				    }, {
				        field: 'orderExprecessPrice',
				        title: '运费金额'
				    }, {
				        field: 'orderStatusTitle',
				        title: '订单状态'
				    }, {
				        field: 'orderPaymentTitle',
				        title: '支付方式'
				    }, {
				        field: 'orderTime',
				        title: '下单时间'
				    }]
	        }); 		
 		})
 	   function detailFormatter(index, row) {
	        return $("#test").tmpl(row);
    	}
 	   function search() {
 	   	$("#testTable").bootstrapTable('refresh');
 	   }
 </script>
<script id="test" type="text/x-jquery-tmpl">
		<table class="table">
				{{each goods}} 
		        <tr>
		            <td width="70"><img src="<#noparse>${$value.goodsThumbnail}</#noparse>" width="62" height="62"/></td>
		            <td><#noparse>${$value.goodsName}</#noparse> <#noparse>${$value.goodsProductDetailText}</#noparse></td>
		            <td>x<#noparse>${$value.goodsNum}</#noparse></td>
		        </tr>
		        {{/each}}
		</table>
		<table class="table">
			<tr>
				<td>
				收货人信息<br/>
				收货人：<#noparse>${orderUserName}</#noparse><br/>
				地址： <#noparse>${orderAddress}</#noparse><br/>
				手机号码：<#noparse>${orderPhone}</#noparse><br/>
				</td>
			</tr>
		</table>
</script>
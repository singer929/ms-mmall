<@ms.html5>
    <@ms.nav title="运费管理" back=false>
    	<@ms.saveButton id = "save"  onclick="save()"/> 
	</@ms.nav>   
    <@ms.panel>
		<table id="orderTable"
			data-toolbar="#toolbar"
			data-detail-view="true" 
			data-show-refresh="true"
	        data-show-columns="true"
	        data-show-export="true"
			data-method="post" 
			data-pagination="true"
			data-page-size="1"
			data-side-pagination="server">
		</table>
		<input type="hidden" name="categoryId" value="${categoryId?default('')}"/>
    </@ms.panel>
</@ms.html5>	        
 <script>
	$(function() {
		//对应bootstrap-table框架的控制
		var categoryId = $("input[name=categoryId]").val();
        $("#orderTable").bootstrapTable({
        		url:"${managerPath}/freight/list.do?categoryId="+categoryId,
        		contentType : "application/x-www-form-urlencoded",
        		queryParams:function(params) {
				return  $.param(params)+"&pageSize="+ params.limit+"&pageNo="+(params.offset+1)+"&"+$("#searchForm").serialize();
				},
			    columns: [{ checkbox: true},{
			        field: 'freightId',
			        title: '编号'
			    }, {
			        field: 'freExpress.categoryTitle',
			        title: '快递公司'
			    }, {
			        field: 'freightBasePrice',
			        title: '基础运费'
			    }, {
			        field: 'freightBaseAmount',
			        title: '基础运费数量'
			    }, {
			        field: 'freightIncreasePrice',
			        title: '增长运费'
			    }, {
			        field: 'freightIncreaseAmount',
			        title: '增长数量'
			    }]
        }); 		
	})
 </script>



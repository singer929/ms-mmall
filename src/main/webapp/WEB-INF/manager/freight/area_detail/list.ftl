<@ms.html5>
	<@ms.nav title="区域运费管理"><@ms.saveButton id="saveButton"/></@ms.nav>
    <@ms.panel> 
		<input type="hidden" value="${faId?default(0)}" id="faId"/>
		<table id="areaTable"
			data-toolbar="#toolbar"
	       	data-editable="true"
	        data-show-export="true"
			data-method="post" 
			data-detail-formatter="detailFormatter" 
			data-side-pagination="server">
		</table>
   </@ms.panel>
</@ms.html5>
<script>
$(document).ready(function(){
	var areaId = $("#faId").val();
	//对应bootstrap-table框架的控制
	$("#areaTable").bootstrapTable({
		url:"${managerPath}/freight/areaDetail/list.do?faId="+areaId,
		contentType: "application/x-www-form-urlencoded",
	    columns: 
	    [{	
	    	checkbox: true,
	    	formatter: function (value, row, index) {
	    		if(row.fadEnable == 1){
	    			return {
			            checked : true
			        };
	    		}else{
	    			
	    		}
                
           }
	    },{
	        field: 'fadExpress.categoryId',
	        title: '快递编号'
	    }, {
	        field: 'fadExpress.categoryTitle',
	        title: '快递公司'
	    }, {
	        field: 'fadBasePrice',
	        title: '基础运费',
	       formatter: function (value, row, index) {
                return "<input type='number' name='fadBasePrice' areaid="+row.fadAreaId+" expressId="+row.fadExpressId+" value="+value+">";
           }
	    }, {
	        field: 'fadBaseAmount',
	        title: '基础数量',
	        formatter: function (value, row, index) {
                return "<input type='number' name='fadBaseAmount' value="+value+">";
           }
	    }, {
	        field: 'fadIncreasePrice',
	        title: '增长运费',
	        formatter: function (value, row, index) {
                return "<input type='number' name='fadIncreasePrice' value="+value+">";
           }
	    }, {
	        field: 'fadIncreaseAmount',
	        title: '增长数量',
	        formatter: function (value, row, index) {
                return "<input type='number' name='fadIncreaseAmount' value="+value+">";
           }
	    }]
    });	
});

</script>
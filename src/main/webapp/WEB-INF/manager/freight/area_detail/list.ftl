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
$("#saveButton").click(function(){
	var edit = [];
	var add = [];
	$(".table-hover input[name=btSelectItem]").each(function(){
		var fadEnable = 0;
		var fadExpressId = $(this).closest("tr").find("input[name=fadBasePrice]").attr("expressId");
		var fadAreaId = $(this).closest("tr").find("input[name=fadBasePrice]").attr("areaid");
		var fadBasePrice = $(this).closest("tr").find("input[name=fadBasePrice]").val();
		var fadBaseAmount = $(this).closest("tr").find("input[name=fadBaseAmount]").val();
		var fadIncreasePrice = $(this).closest("tr").find("input[name=fadIncreasePrice]").val();
		var fadIncreaseAmount = $(this).closest("tr").find("input[name=fadIncreaseAmount]").val();
		if($(this).is(':checked')){ 					//判断复选框是否选中
			fadEnable = 1;
	  	}else{
	  		fadEnable = 0;
	  	}
	  	var obj = new Object();
		obj.fadExpressId=fadExpressId;
		obj.fadAreaId=fadAreaId;
		obj.fadEnable=fadEnable;
		obj.fadBasePrice=fadBasePrice;
		obj.fadBaseAmount=fadBaseAmount;
		obj.fadIncreasePrice=fadIncreasePrice;
		obj.fadIncreaseAmount=fadIncreaseAmount;
		if(fadAreaId > 0){
			edit.push(obj);
		}
		if(fadAreaId == 0){
			add.push(obj);
		}
	})
	var editStr = JSON.stringify(edit);
	var addStr = JSON.stringify(add);
	if(edit.length>0){
		$.post("${managerPath}/freight/areaDetail/update.do",
			{
				str:editStr
			},
			function(data,status){}
		);
	}
	if(add.length>0){
		$.post("${managerPath}/freight/areaDetail/save.do",
			{
				str:addStr
			},
			function(data,status){}
		);
	}
	//保存成功提示
	$('.ms-notifications').offset({top:43}).notify({
		type:'success',
		message: { text:'保存成功！' }
	}).show();
});
</script>
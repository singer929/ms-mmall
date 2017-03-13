<@ms.html5>
	<@ms.nav title="区域运费管理"><@ms.saveButton id="saveButton"/></@ms.nav>
    <@ms.panel> 
		<input type="hidden" value="${faId?default(3)}" id="faId"/>
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
<link href="${base}/js/manager/freight/bootstrap-editable.css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${base}/js/manager/freight/bootstrap-editable.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/js/manager/freight/bootstrap-table-editable.js"></script>
<script>
$(document).ready(function(){
	var areaId = $("#faId").val();
	//对应bootstrap-table框架的控制
	$("#areaTable").bootstrapTable({
		url:"${managerPath}/freight/areaDetail/list.do?faId="+areaId,
		contentType: "application/x-www-form-urlencoded",
	    columns: 
	    [{
	    	checkbox: true
	    },{
	        field: 'fadExpress.categoryId',
	        title: '快递编号'
	    }, {
	        field: 'fadExpress.categoryTitle',
	        title: '快递公司'
	    }, {
	        field: 'fadBasePrice',
	        title: '基础运费',
	        editable:{
	        	type:'text',
	        	title: '基础运费',
	        	validate: function (v) {
                    if (!v) return '基础运费不能为空';
                }
	        }
	    }, {
	        field: 'fadBaseAmount',
	        title: '基础数量',
	        editable:{
	        	type:'text',
	        	title: '基础数量',
	        	validate: function (v) {
                    if (!v) return '基础数量不能为空';
                }
	        }
	    }, {
	        field: 'fadIncreasePrice',
	        title: '增长运费',
	        editable:{
	        	type:'text',
	        	title: '增长运费',
	        	validate: function (v) {
                    if (!v) return '增长运费不能为空';
                }
	        }
	    }, {
	        field: 'fadIncreaseAmount',
	        title: '增长数量',
	        editable:{
	        	type:'text',
	        	title: '增长数量',
	        	validate: function (v) {
                    if (!v) return '增长数量不能为空';
                }
	        }
	    }]
    });	
});
</script>
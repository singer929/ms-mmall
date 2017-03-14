<@ms.html5>
		<style>
			#areaList {padding-left:20px;}
			#areaList li{line-height:25px;cursor: pointer;}
			#areaList li.sel{color:blue}
		</style>
		<@ms.contentMenu>
			<@ms.nav title="区域列表" style="position: relative;"></@ms.nav>
			<ul id="areaList">
           	<#list areas as areaEntity>
        		<li data-id="${areaEntity.faId?default(0)}">
	        		${areaEntity.faTitle?default(0)}
	        	</li>
	        </#list>
	        </ul>
		</@ms.contentMenu>
		<@ms.panel style="width: 85%;right: 0; position: absolute;">
			<@ms.nav title="区域快递费用详情表" style="width: 85%;"><@ms.saveButton id="saveButton"/></@ms.nav>
				<table id="areaDetailTable"
			       	data-editable="true"
			        data-show-export="true"
					data-method="post" 
					data-side-pagination="server">
				</table>
		</@ms.panel>
</@ms.html5>
<script>
	$(function() {
		$("#areaList li:first").addClass("sel");
		$("#areaList li").click(function() {
			$("#areaList li").removeClass("sel");
			$(this).addClass("sel");
			$("#areaDetailTable").bootstrapTable("refresh",{query: {faId:$(this).data("id")}});
		});
		//加载对应区域的详细列表
		$("#areaDetailTable").bootstrapTable({
			url:"${managerPath}/freight/areaDetail/list.do",
			queryParams:function(params) {
				return {faId:${areas[0].faId}};
			},
			contentType: "application/x-www-form-urlencoded",
		    columns: 
		    [{	
		    	checkbox: true,
		    	formatter: function (value, row, index) {
		    		if(row.fadEnable == 1){
		    			return {
				            checked : true
				        }
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
		        class: 'text-center',
		       	formatter: function (value, row, index) {
	                return "<input type='number' class='btn btn-default' min='0' name='fadBasePrice' areaid="+row.fadAreaId+" expressId="+row.fadExpressId+" value="+value+">";
	           }
		    }, {
		        field: 'fadBaseAmount',
		        title: '基础数量',
		        class: 'text-center',
		        formatter: function (value, row, index) {
	                return "<input type='number' class='btn btn-default' min='0' name='fadBaseAmount' value="+value+">";
	           }
		    }, {
		        field: 'fadIncreasePrice',
		        title: '增长运费',
		        class: 'text-center',
		        formatter: function (value, row, index) {
	                return "<input type='number' class='btn btn-default' min='0' name='fadIncreasePrice' value="+value+">";
	           }
		    }, {
		        field: 'fadIncreaseAmount',
		        title: '增长数量',
		        class: 'text-center',
		        formatter: function (value, row, index) {
	                return "<input type='number' class='btn btn-default' min='0' name='fadIncreaseAmount' value="+value+">";
	           }
		    }]
	    });	
	});
	
	$("#saveButton").click(function(){
		var flag = true;
		$(".table-hover input[type=number]").each(function(){
			var val = $(this).val();
			if(val < 0 || isNaN(val)){
				flag = false;
			}
		});
		
		if(!flag){
			 $('.ms-notifications').offset({top:43}).notify({
    		    type:'warning',
			    message: { text:'保存失败，输入类型错误'}
			 }).show();
			return;
		}
		var addOrEdit = [];
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
			addOrEdit.push(obj);
		})
		var str = JSON.stringify(addOrEdit);
		$.post("${managerPath}/freight/areaDetail/saveOrUpdate.do",
			{
				str:str
			},
			function(data,status){}
		);
		//保存成功提示
		$('.ms-notifications').offset({top:43}).notify({
			type:'success',
			message: { text:'保存成功！' }
		}).show();
	});	
</script>

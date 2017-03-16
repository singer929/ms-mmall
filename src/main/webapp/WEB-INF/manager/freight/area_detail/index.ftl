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
	var areaId = 0;
	function onlyNum(){
  		if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39))
		  	if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105)))
		    	event.returnValue=false;
	}
	$(function() {
		$("#areaList li").click(function() {
			$("#areaList li").removeClass("sel");
			$(this).addClass("sel");
			$("#areaDetailTable").bootstrapTable("refresh",{query: {faId:$(this).data("id")}});
			areaId = $(".sel").data("id");   //获取区域ID
		});
		$('#areaList li:first').click();
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
		    	class: 'text-center',
		    	formatter: function (value, row, index) {
		    		if(row.fadEnable == 1){
		    			return {
				            checked : true
				        }
		    		}
	           }
		    },{
		        field: 'fadExpress.categoryId',
		        class: 'text-center',
		        title: '快递编号'
		    }, {
		        field: 'fadExpress.categoryTitle',
		        class: 'text-center',
		        title: '快递公司'
		    }, {
		        field: 'fadBasePrice',
		        title: '基础运费',
		        class: 'text-center',
		       	formatter: function (value, row, index) {
	                return "<input type='number' class='btn btn-default' onkeydown='onlyNum()' min='0' max='9999' fadId="+row.fadId+" expressId="+row.fadExpress.categoryId+" name='fadBasePrice' areaid="+areaId+" value="+value+">";
	           }
		    }, {
		        field: 'fadBaseAmount',
		        title: '基础数量',
		        class: 'text-center',
		        formatter: function (value, row, index) {
	                return "<input type='number' class='btn btn-default' onkeydown='onlyNum()' min='0' max='9999' name='fadBaseAmount' value="+value+">";
	           }
		    }, {
		        field: 'fadIncreasePrice',
		        title: '增长运费',
		        class: 'text-center',
		        formatter: function (value, row, index) {
	                return "<input type='number' class='btn btn-default' onkeydown='onlyNum()' min='0' max='9999' name='fadIncreasePrice' value="+value+">";
	           }
		    }, {
		        field: 'fadIncreaseAmount',
		        title: '增长数量',
		        class: 'text-center',
		        formatter: function (value, row, index) {
	                return "<input type='number' class='btn btn-default' onkeydown='onlyNum()' min='0' max='9999' name='fadIncreaseAmount' value="+value+">";
	           }
		    }]
	    });	
	});
	
	$("#saveButton").click(function(){
		var flag = true;
		$(".table-hover input[type=number]").each(function(){
			var val = $(this).val();
			if(val < 0 || val > 9999){
				flag = false;
			}
		});
		
		if(!flag){
			 $('.ms-notifications').offset({top:43}).notify({
    		    type:'warning',
			    message: { text:'保存失败，输入值错误'}
			 }).show();
			return;
		}
		var addOrEdit = [];
		$(".table-hover input[name=btSelectItem]").each(function(){
			var fadEnable = 0;
			if($(this).is(':checked')){ 					//判断复选框是否选中
				fadEnable = 1;
		  	}else{
		  		fadEnable = 0;
		  	}
		  	var obj = new Object();
		  	obj.fadId=$(this).closest("tr").find("input[name=fadBasePrice]").attr("fadId");
			obj.fadExpressId=$(this).closest("tr").find("input[name=fadBasePrice]").attr("expressId");
			obj.fadAreaId=$(this).closest("tr").find("input[name=fadBasePrice]").attr("areaid");
			obj.fadEnable=fadEnable
			obj.fadBasePrice=$(this).closest("tr").find("input[name=fadBasePrice]").val();
			obj.fadBaseAmount=$(this).closest("tr").find("input[name=fadBaseAmount]").val();
			obj.fadIncreasePrice=$(this).closest("tr").find("input[name=fadIncreasePrice]").val();
			obj.fadIncreaseAmount=$(this).closest("tr").find("input[name=fadIncreaseAmount]").val();
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

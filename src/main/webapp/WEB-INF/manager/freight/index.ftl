<@ms.html5>
 	<@ms.contentMenu>
 		<@ms.nav title="城市列表" style="position: relative;"></@ms.nav>
		 <!-- 树形模块菜单开始 -->
		<#if categoryJson?has_content && categoryJson!="[]">
			<@ms.tree  treeId="inputTree" json="${categoryJson?default('')}"   jsonId="categoryId" jsonPid="categoryCategoryId" jsonName="categoryTitle"   showIcon="true" expandAll="true" getZtreeId="getZtreeId(event,treeId,treeNode);"  id="listCity"/>
		<#else> 
			<@ms.nodata content="暂无栏目"/>
		</#if>
		<!-- 树形模块菜单结束 -->
	</@ms.contentMenu>
	<@ms.panel style="width: 85%;right: 0; position: absolute;">	
		<@ms.nav title="城市快递费用详情表" style="width: 85%;"><@ms.saveButton id = "save"  onclick="save()"/> </@ms.nav>
       	<table id="freightTable"
			data-toolbar="#toolbar"
	        data-show-export="true"
	        data-show-refresh="true"
       	 	data-show-columns="true"
			data-method="post" 
			data-side-pagination="server">
		</table>
	</@ms.panel>
</@ms.html5>
<script>
	//获取树节点的城市ID categoryId
	function getZtreeId(event,treeId,treeNode){				
		$("#freightTable").bootstrapTable("refresh",{query: {categoryId:treeNode.categoryId}});		//刷新右侧数据
		$("#treeDomeinputTree").attr("data-id",treeNode.categoryId);  //点击追加一个对应的城市id
	}
	//对应bootstrap-table框架的控制
	var categoryId = ${list[0].categoryId?default(0)};  //默认城市id
    $("#freightTable").bootstrapTable({
		url:"${managerPath}/freight/list.do",
		queryParams:function(params) {
			return {categoryId:${list[0].categoryId?default(0)}};
		},
		contentType : "application/x-www-form-urlencoded",
	    columns: [{ 
	    	checkbox: true,
	    	formatter: function (value, row, index){
	    		if(row.freightEnable == 1){		//快递启用则勾选复选框
	    			return{checked: true};
	    		}
	    	}
	    },{
	    	align: 'center',
	        field: 'freExpress.categoryId',
	        title: '编号'
	    }, {
	    	align: 'center',
	        field: 'freExpress.categoryTitle',
	        title: '快递公司'
	    }, {
	    	align: 'center',
	        field: 'freightBasePrice',
	        formatter:function(value,row,index){return "<input type=number min= 0 max= 999999 class='form-control' name = 'freightBasePrice'  freightId="+row.freightId+" expressId="+row.freExpress.categoryId+" style='text-align: center;' value="+value+">"},
	        title: '基础运费'
	    }, {
	    	align: 'center',
	        field: 'freightBaseAmount',
	        formatter:function(value,row,index){return "<input type=number min= 0 max= 999999 class='form-control' name = 'freightBaseAmount' style='text-align: center; ' value="+value+">"},
	        title: '基础运费数量'
	    }, {
	    	align: 'center',
	        field: 'freightIncreasePrice',
	        formatter:function(value,row,index){return "<input type=number min= 0 max= 999999 class='form-control' name = 'freightIncreasePrice' style='text-align: center; ' value="+value+">"},
	        title: '增长运费'
	    }, {
	    	align: 'center',
	        field: 'freightIncreaseAmount',
	        formatter:function(value,row,index){return "<input type=number min= 0 max= 999999 class='form-control' name = 'freightIncreaseAmount' style='text-align: center; ' value="+value+">"},
	        title: '增长数量'
		}]
    }); 		
	function save(){  		
		var checked = 0;	
		var saveOrUpdate = []; 
		var list = $("input[name =btSelectItem]");
		var flag = true;
		$("input[type=number]").each(function(){
			var number = $(this).val();
			var char = number.split("e");	//文本框含有"e"时,char长度不为0;
			if(number < 0 || isNaN(number) || number > 999999 || char[0] == "" || char.length > 1){
				flag = false;
			}
		});
		if(!flag){
			 $('.ms-notifications').offset({top:43}).notify({
    		    type:'warning',
			    message: { text:'保存失败，输入类型错误'}
			 }).show();
			return;		//异常则结束整个操作
		}
		$("input[name =btSelectItem]").each(function(){	//记录的条数	
			var freightCityId = $("#treeDomeinputTree").attr("data-id");
			if(freightCityId == undefined){	//没有获取到追加的值，就赋一个默认值
				freightCityId = categoryId;
			}
			var freightId = $(this).closest("tr").find("input[name=freightBasePrice]").attr("freightId");
    		var freightExpressId = $(this).closest("tr").find("input[name=freightBasePrice]").attr("expressId");
    		var freightBasePrice = $(this).closest("tr").find("input[name = freightBasePrice]").val();
    		var freightBaseAmount = $(this).closest("tr").find("input[name = freightBaseAmount]").val();
    		var freightIncreasePrice = $(this).closest("tr").find("input[name = freightIncreasePrice]").val(); 
    		var freightIncreaseAmount = $(this).closest("tr").find("input[name = freightIncreaseAmount]").val(); 
			if($(this).is(':checked')){		//选框是否勾选
				checked = 1;    					    				   					
			}else{
				checked = 0;	    				
			}
			var obj = new Object();			//新建一个对象
			obj.freightCityId = freightCityId;
			obj.freightExpressId = freightExpressId;
			obj.freightEnable = checked;
			obj.freightBasePrice = freightBasePrice;
			obj.freightBaseAmount = freightBaseAmount;
			obj.freightIncreasePrice = freightIncreasePrice;
			obj.freightIncreaseAmount = freightIncreaseAmount;
			saveOrUpdate.push(obj);		//对象的值赋值给数据
		})
		var saveOrUpdateStr = JSON.stringify(saveOrUpdate);
		$.post("${managerPath}/freight/saveOrUpdate.do",
			{
				string:saveOrUpdateStr	    					
			},
			function(data,status){}
		);
		$('.ms-notifications').offset({top:43}).notify({
			type:'success',
			message: { text:'保存成功！' }
		}).show();	
	}; 
</script>
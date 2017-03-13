<@ms.html5>
	<@ms.panel>	
	 	<@ms.contentMenu>
			 <!-- 树形模块菜单开始 -->
			<#if categoryJson?has_content && categoryJson!="[]">
				<@ms.tree  treeId="inputTree" json="${categoryJson?default('')}" addNodesName="全部"  jsonId="categoryId" jsonPid="categoryCategoryId" jsonName="categoryTitle"   showIcon="true" expandAll="true" getZtreeId="getZtreeId(event,treeId,treeNode);"  id="listCity"/>
			<#else> 
				<@ms.nodata content="暂无栏目"/>
			</#if>
			<!-- 树形模块菜单结束 -->
		</@ms.contentMenu>
	    <@ms.contentBody width="85%" style="overflow-y: hidden;">
			<@ms.contentPanel  style="margin:0;padding:0;overflow-y: hidden;">
		    <@ms.saveButton id = "save"  onclick="save()"/> 
	           <table id="freightTable"
					data-toolbar="#toolbar"
					data-show-refresh="true"
			        data-show-columns="true"
			        data-show-export="true"
					data-method="post" 
					data-pagination="true"
					data-side-pagination="server">
				</table>
			</@ms.contentPanel>	
		</@ms.contentBody>  		
	</@ms.panel>
</@ms.html5>
<script>
	//获取树节点的城市ID categoryId追加一个src
	function getZtreeId(event,treeId,treeNode){				
		//对应bootstrap-table框架的控制
        $("#freightTable").bootstrapTable({
        		url:"${managerPath}/freight/list.do?categoryId="+treeNode.categoryId,
        		contentType : "application/x-www-form-urlencoded",
			    columns: [{ 
			    	checkbox: true,
			    	formatter: function (value, row, index){
			    		if(row.freEnable == 1){
			    			return{
			    				checked: true
			    			};
			    		}
			    	}
			    },{
			        field: 'freightId',
			        title: '编号'
			    }, {
			        field: 'freExpress.categoryTitle',
			        title: '快递公司'
			    }, {
			        field: 'freightBasePrice',
			        formatter:function(value,row,index){return "<input type=text name = 'freightBasePrice' freightCityId="+row.freightCityId+" freightId="+row.freightId+" expressId="+row.freExpress.categoryId+" style= 'width:100px;' value="+value+">"},
			        title: '基础运费'
			    }, {
			        field: 'freightBaseAmount',
			        formatter:function(value,row,index){return "<input type=text name = 'freightBaseAmount' style= 'width:100px;' value="+value+">"},
			        title: '基础运费数量'
			    }, {
			        field: 'freightIncreasePrice',
			        formatter:function(value,row,index){return "<input type=text name = 'freightIncreasePrice' style= 'width:100px;' value="+value+">"},
			        title: '增长运费'
			    }, {
			        field: 'freightIncreaseAmount',
			        formatter:function(value,row,index){return "<input type=text name = 'freightIncreaseAmount' style= 'width:100px;' value="+value+">"},
			        title: '增长数量'
			    }]
        }); 		
	}
	function save(){  		
		var checked = 0;	
		var update = [];
		var save = []; 
		var list = $("input[name =btSelectItem]");
		$("input[name =btSelectItem]").each(function(){	//记录的条数	
			var freightCityId = $(this).closest("tr").find("input[name=freightBasePrice]").attr("freightCityId");
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
			if((freightId > 0) && (freightBasePrice >=0 && freightBasePrice != "" && freightBasePrice <= 999999) && (freightBaseAmount >=0 && freightBaseAmount != "" && freightBaseAmount <= 999999) && (freightIncreasePrice >=0 && freightIncreasePrice != "" && freightIncreasePrice <= 999999) && (freightIncreaseAmount >=0 && freightIncreaseAmount != "" && freightIncreaseAmount <= 999999)){
				update.push(obj);
			}else if((freightId == 0) && (freightBasePrice >=0 && freightBasePrice != "" && freightBasePrice <= 999999) && (freightBaseAmount >=0 && freightBaseAmount != "" && freightBaseAmount <= 999999) && (freightIncreasePrice >=0 && freightIncreasePrice != "" && freightIncreasePrice <= 999999) && (freightIncreaseAmount >=0 && freightIncreaseAmount != "" && freightIncreaseAmount <= 999999)){
				save.push(obj);
			}else{
				
			}
		})
		var updateStr = JSON.stringify(update);
		var saveStr = JSON.stringify(save);
		//判断所有的值是否合法或为空
		if((update.length == list.length) || (save.length == list.length)){
			if(update.length > 0){			//判断是否保存过这条数据，为真则更新数据
				$.post("${managerPath}/freight/update.do",
    				{
    					string:updateStr	    					
    				},
    				function(data,status){}
    			);
			}
			if(save.length > 0){				//保存数据
				$.post("${managerPath}/freight/save.do",
    				{
    					string:saveStr	    					
    				},
    				function(data,status){}
    			);
			}
		}
		
		$('.ms-notifications').offset({top:43}).notify({
			type:'success',
			message: { text:'保存成功！' }
		}).show();	
		//更新数据后刷新页面
		location.reload();
	}; 
</script>
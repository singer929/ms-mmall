<!DOCTYPE html>
<html lang="zh">
 <head>
  	<#include "${managerViewPath}/include/macro.ftl"/>
  	<#include "${managerViewPath}/include/meta.ftl"/>
</head>

<body>	
	<@ms.content>
		<@ms.contentBody>
			<@ms.contentNav title="限时抢购"></@ms.contentNav >
			<@ms.contentPanel>
				<@ms.panelNav>
					<@ms.panelNavBtnGroup>
						<@ms.panelNavBtnAdd />
						<@ms.panelNavBtnDel />
					</@ms.panelNavBtnGroup>
				</@ms.panelNav>
				<@ms.table head=['<input type="checkbox" name="allCheck">','疯抢主题','产品标题','日期','时间','每日限购','每人限购','发布时间']>
					<#if promotionList?has_content>
		        			<#list promotionList as promotionList>
	                    	<tr>
	                           <td class="text-center" style="width:5%">
	                            	<@ms.checkbox  name="ids" value="${promotionList.promotionId?c?default(0)}"/> 
	                            </td>
	                            <td class="text-left promotionTitle" style="width:22%;"><a data-original-title="点击编辑" data-toggle="tooltip" style="cursor: pointer;">${promotionList.promotionTitle?default("")}</a></td>
	                            <td class="text-left" style="width: 25%;">${promotionList.productTitle?default("")}</td>
	                            <td class="text-center" style="width:13%">${promotionList.promotionStartDate?string('yyyy-MM-dd')}—${promotionList.promotionEndDate?string('yyyy-MM-dd')}</td>
	                            <td class="text-center" style="width:8%">${promotionList.promotionStartTime?default("")}—${promotionList.promotionEndTime?default("")}</td>
	                            <td class="text-center" style="width:7%">${promotionList.promotionLimitDay?c?default(0)}</td>
	                            <td class="text-center" style="width:7%">${promotionList.promotionLimitPeople?c?default(0)}</td>
	                            <td class="text-center"style="width:13%">${promotionList.promotionDateTime?string('yyyy-MM-dd HH:mm:ss')}</td>
	                    	</tr>
	                    	</#list>
	                    	<#else>
			             	<tr>
					            <td colspan="8" class="text-center">
					            	<@ms.nodata/>
								</td>
				          	</tr>
					</#if>
	            </@ms.table>
	            <!--分页-->
	            <@showPage page=page/>
	            <!--添加疯抢-->    
		    	<@ms.modal modalName="saveAndUpdate" title="添加限时抢购" resetFrom=false style="width:50%;overflow: hidden;">
					 <@ms.modalBody>
				 		<@ms.form isvalidation=true name="promotionForm" class="form-inline" action="">
				 			<@ms.select name="productCategoryId" label="商品分类:"  style="width: 70%;" default=""   id="categorySelect" list={"0":"请选择商品分类"} validation={"required":"true","data-bv-notempty-message":"请选择商品分类！"}/>
				    		<@ms.select name="promotionProductId" label="商品:"  style="width: 70%;"   default=""  id="promotionSelect" list={"0":"请选择商品"}  validation={"required":"true","data-bv-notempty-message":"请选择商品!"}/>
				    		
				    		<@ms.text name="promotionTitle" label="疯抢主题:" style="width: 40%;" maxlength="50"  title="疯抢主题名称" placeholder="请输入疯抢主题"  validation={"required":"true","data-bv-notempty-message":"请输入抢购主题!"}/>
			    			<@ms.date name="promotionStartEndDate" label="活动日期:" readonly="readonly"  style="width: 125%;"  size="5" value="" single=false  placeholder="选择活动日期" ranges=false/>
			    			<@ms.time name="promotionStartTime" label="开始时间:"  readonly="readonly"  size="3"  value=""  placeholder="选择活动开始时间" />
			    			<@ms.time name="promotionEndTime" label="结束时间:"   readonly="readonly"  size="3" value=""  placeholder="选择活动结束时间" />
				    		<@ms.text name="promotionLimitDay" label="每日限购数量:" style="width: 20%;" title="到达此数量活动自动结束" placeholder="每日限购"  maxlength="5" validation={"pattern":"^[0-9]*$","data-bv-regexp-message":"请输入正确格式的商品数量!","required":"true","data-bv-notempty-message":"请输入抢购商品数量!"}/>
				    		<@ms.text name="promotionLimitPeople" label="每人限购:"  style="width: 20%;" title="每人限购" placeholder="每人限购" maxlength="5"  validation={"pattern":"^[0-9]*$","data-bv-regexp-message":"请输入正确的限购数量!","required":"true", "data-bv-notempty-message":"请输入限购数量"}/>
				    	</@ms.form>
				     </@ms.modalBody>
				    <@ms.modalButton>
				 		<@ms.savebutton id="saveUpdateButton"  onclick="saveAndUpdateButton(this)" />  
				 	</@ms.modalButton>
				</@ms.modal>
	            
	            <!--删除限时商品-->    
				<@ms.modal modalName="delete" title="删除限时商品">
					  <@ms.modalBody>
					  		确定要删除所选的限时商品吗?
				     </@ms.modalBody>
					 <@ms.modalButton>
				 		<@ms.button class="btn btn-danger deleteAll" value="确定"/>
				 	</@ms.modalButton>
				</@ms.modal>
	            
			</@ms.contentPanel>		
		</@ms.contentBody>
	</@ms.content>                
	
	<script>
		//获取商品
		function getProduct(categoryId,productId){
			//获取所有商品分类
			var url ="${managerPath}/mall/product/queryCategoryListByAppId.do";
			$(this).request({url:url,method:"post",func:function(msg) { 
					$("#categorySelect").html("");
					var data =JSON.parse(msg);
					var json =JSON.parse(data.resultData);	
			     	if(json.length != 0){
			   			for(var i=0; i<json.length; i++){
			   				if(categoryId!=0 &&  json[i].categoryId == categoryId){
			   					$("#categorySelect").append($("<option>").val(json[i].categoryId).text(json[i].categoryTitle).attr("selected", true));
				   			}else{
				   				$("#categorySelect").append($("<option>").val(json[i].categoryId).text(json[i].categoryTitle));
				   			}
				   		}
			   		}else{
			   			$("#categorySelect").append("<option  value='0' selected='selected'>暂无商品分类</option>");
			   		}
			   					
				 $("#categorySelect").select2({
                    placeholder: "请选择商品",  
                    allowClear: true,  
                 });
			}});
			
			//获取所有上架商品
			var url ="${managerPath}/mall/product/"+categoryId+"/queryListByCategoryId.do";
			$(this).request({url:url,method:"post",data:"productShelf=1",func:function(msg) {
					$("#promotionSelect").html("");
					var data =JSON.parse(msg);
					var json =JSON.parse(data.resultData);
			     	if(json.length != 0){
			   			for(var i=0; i<json.length; i++){
			   				if( json[i].productId == productId){
			   					$("#promotionSelect").append($("<option>").val(json[i].productId).text(json[i].basicTitle).attr("selected", true));
			   				}else{
				   				$("#promotionSelect").append($("<option>").val(json[i].productId).text(json[i].basicTitle));
				   			}
				   		}
			   		}else{
			   			$("#promotionSelect").append("<option value='0' selected='selected'>暂无商品</option>");
			   		}
			   		
				 	$("#promotionSelect").select2({
                        placeholder: "请选择商品",  
                        allowClear: true,  
                     });
			}});

			
			//选择分类后触发事件
			$('#categorySelect').change(function(){
				var categoryId = $(this).children('option:selected').val();
				//获取所有上架商品
				var url ="${managerPath}/mall/product/"+categoryId+"/queryListByCategoryId.do";
				$(this).request({url:url,method:"post",data:"productShelf=1",func:function(msg) { 
						$("#promotionSelect").html("");
						var data =JSON.parse(msg);
						var json =JSON.parse(data.resultData);
				   		//$("#promotionSelect").html("");
				     	if(json.length != 0){
				   			for(var i=0; i<json.length; i++){
					   			$("#promotionSelect").append($("<option>").val(json[i].productId).text(json[i].basicTitle));
					   		}
				   		}else{
				   			$("#promotionSelect").append("<option value='0' selected='selected'>暂无商品</option>");
				   		}
				   		
				   	$("#promotionSelect").select2({
                        placeholder: "请选择商品",  
                        allowClear: true,  
                     });
				   		
				}});
			});
		}
	
	
	
		$(function(){
			//全选
		   	$("input[name='allCheck']").on("click",function(){   
				if(this.checked){   
					$("input[name='ids']").each(function(){this.checked=true;});
				}else{   
					$("input[name='ids']").each(function(){this.checked=false;});   
				}
			}); 
			
			//弹出添加窗口
			$("#addButton").click(function(){
				$("#promotionForm").attr("action","${managerPath}/mall/promotion/save.do");
				$("#saveUpdateButton").text("保存");
				getProduct(0,0);
				 $("#categorySelect").select2("val","");
				  $("#promotionSelect").select2("val","");
				 
				//重置表单
				$("#promotionForm")[0].reset();
				$(".saveAndUpdate").modal();
			});
			
			getProduct(0,0);
			
		
			
			
			//删除按钮点击
			$("#delButton").click(function() {
				var checkboxData = $("input[name='ids']").serialize();
				if(checkboxData!=""){
					$(".delete").modal();
				}else{
					alert("请选择限时商品!");
				}
			});
			
			 //更新模态框呼出触发事件
			 $(".promotionTitle").click(function() {
			 	var promotionId = $(this).parents("tr").find("input[name='ids']").val();
			 	var url =base+"${baseManager}/mall/promotion/"+promotionId+"/edit.do";
				$(this).request({url:url,method:"post",func:function(msg) { 
					var data =JSON.parse(msg);
						var json =JSON.parse(data.resultMsg);
						$("#promotionForm").attr("action","${managerPath}/mall/promotion/"+json.promotionId+"/update.do");
						$(".saveAndUpdate input[name='promotionTitle']").val(json.promotionTitle);
						$(".saveAndUpdate input[name='promotionStartEndDate']").val(json.promotionStartDate+"至"+json.promotionEndDate);
						$(".saveAndUpdate input[name='promotionStartTime']").val(json.promotionStartTime);
						$(".saveAndUpdate input[name='promotionEndTime']").val(json.promotionEndTime);
						$(".saveAndUpdate input[name='promotionLimitDay']").val(json.promotionLimitDay);
						$(".saveAndUpdate input[name='promotionLimitPeople']").val(json.promotionLimitPeople);
						
						//获取商品下拉
						getProduct(json.productCategoryId,json.promotionProductId);
						
						$("#saveUpdateButton").text("更新");
						$(".saveAndUpdate").modal();
			 	}});
			});
			 
		 
			//批量删除
			$(".deleteAll").click(function(){
				//将checkbox序列化
				var ids = $("input[name='ids']").serialize();
				if(ids!=""){
					//按钮禁用
					$(this).attr("disabled",true);
					var url =base+"${baseManager}/mall/promotion/delete.do";
					$(this).request({url:url,data:ids,method:"post",func:function(msg) { 
						var json =JSON.parse(msg);
						if (json.result) {
							alert("删除成功！");
							//关闭该模块
							$(".delete").modal("hide");
							location.reload();
						}else{
							alert("删除失败！");
						}
						//按钮禁用解开
						$(this).attr("disabled",false);
					}});
		    	}else{
		        	alert("请选择限时商品!");
		        }
			});
		});
		
		//比较时间
		function checkTime(beginDate,beginTime,endTime){
			var strs= new Array();
			if(beginDate=="") {
				alert("请选择活动日期!!");
				return false;
			}
			strs=beginDate.split("至");
			var dateA = new Date(strs[0] +" "+ beginTime);   
			var dateB = new Date(strs[1] +" "+ endTime);   
			if(isNaN(dateA) || isNaN(dateB)) {
				alert("请选择时间!!");
				return false;
			}
			if(dateA > dateB){
				alert("开始时间必须小于结束时间!"); 
				 return false;
			}else{
				return true;
			}
		}
		
		//添加抢购
	 	function saveAndUpdateButton(obj) {
	 		var vobj = $("#promotionForm").data('bootstrapValidator').validate();
	 		var categoryId = $("#categorySelect").children('option:selected').val();
	 		var promotionId = $("#promotionSelect").children('option:selected').val();
	 		if( categoryId ==0 || categoryId=="" ){
	 			alert("请选择商品分类！")
	 			return;
	 		}
	 		if(promotionId ==0 || promotionId==""){
	 			alert("请选择商品！")
	 			return;
	 		}
	 		//验证时间
	 		if(!checkTime($("input[name='promotionStartEndDate']").val(),$("input[name='promotionStartTime']").val(),$("input[name='promotionEndTime']").val())){
	 			return;
	 		};
			if(vobj.isValid()){
				//按钮禁用
				$(obj).attr("disabled",true);
				$(this).postForm("#promotionForm",{func:function(msg) {
					if (msg.result) {
				     		if($("#saveUpdateButton").text()=="保存"){
				     			alert("保存成功");
				     		}else{
				     			alert("更新成功");
				     		}
				    		location.reload();
				    	}else{
				    		if($("#saveUpdateButton").text()=="保存"){
				     			alert("保存失败");
				     		}else{
				     			alert("更新失败");
				     		}
				    	}
				    	//按钮解开
				    	$(obj).attr("disabled",false);
				}});
			}
		 };
		 
	</script>
</body>
</html>














<!DOCTYPE html>
<html lang="en">
<head>
	<#include "${managerViewPath}/include/meta.ftl"/> <!--调用head内样式信息-->
	<!--<script type="text/javascript" src="${base}/js/manager/mall/product.js"></script>
	<script type="text/javascript" src="${base}/js/manager/mall/specifications_new.js"></script>-->

</head>
<script>
	/*
	var appId = "${appId}";
	var productSpecificationsJson;
	<#if productSpecificationsJson?has_content> 
		productSpecificationsJson =  ${productSpecificationsJson?default(" ")}; // 商品的规格信息
	</#if>	
	var specificationsJson = ${specificationsJson?default("")}//商城的规格
	*/
	
	$(function(){
		
		$("#productRefresh").click(function(){
			location.href="${managerPath}/mall/product/addNew.do?categoryId=${categoryId?default(0)}&categoryTitle=${categoryTitle?default(0)}";
		});
		
		/*
		MS.mall.specifications.init();
		var json = [{"specificationsId":1,"specificationsName":"1","childProductSpecifications":[
																{"specificationsId":2,"specificationsName":"2","childProductSpecifications":[]},
																{"specificationsId":3,"specificationsName":"3","childProductSpecifications":[]}]
					},
					{"specificationsId":4,"specificationsName":"4","childProductSpecifications":[
																{"specificationsId":5,"specificationsName":"5","childProductSpecifications":[]},
																{"specificationsId":6,"specificationsName":"6","childProductSpecifications":[]}]
					},
					{"specificationsId":4,"specificationsName":"4","childProductSpecifications":[
																{"specificationsId":5,"specificationsName":"5","childProductSpecifications":[]},
																{"specificationsId":6,"specificationsName":"6","childProductSpecifications":[]}]
					}
					];
		specifications.init(json,$("#specificationsView"));
		*/
	})
	
</script>
<body>
	<!-- 内容容器开始 -->
	<@ms.content>
		<@ms.contentBody>
			<!-- 头部开始 -->
			<@ms.contentNav title="商品管理">
				<@ms.savebutton id="productSubmit" value="保存"/> 
				<@ms.contentNavBack/>
				<@ms.savebutton id="productRefresh" value="刷新"/> 
				<@ms.contentNavBack/>
			</@ms.contentNav>
			<!-- 头部结束 -->
			<!-- 表单开始 -->
			<@ms.contentPanel>
				<@ms.form  isvalidation=true name="productForm">
						<!-- 填写商品名称开始 -->
			    		<@ms.text name="basicTitle" label="商品名称:" title="" value="" style="width:65%;" validation={"required":"true", "data-bv-notempty-message":"商品名称不能为空","data-bv-stringlength-max":"100" ,"data-bv-stringlength-min":"1" ,"data-bv-stringlength-message":"长度介于1-100个字符"} placeholder="请填写商品名称"/>
						<!-- 填写商品名称结束 -->
						
						<!-- 选择商品栏目开始 -->
						<div class="row">
							<label class="col-md-3 control-label col-xs-3">商品栏目：</label>
							<div class="col-md-5 col-xs-6">
								<@ms.treeInput treeId="productCarteger" json="${columnList?default()}" jsonId="categoryId" jsonPid="categoryCategoryId" jsonName="categoryTitle" inputName="basicCategoryId" buttonText="请选择商品栏目" showIcon="true" selectParent="请选择子栏目"/>
							</div>
						</div>
						<!-- 选择商品栏目结束 -->
						
						<!-- 商品属性开始 -->
						<@ms.select label="商品属性:" style="width:25%" list=mallTypeList listKey="categoryId" listValue="categoryTitle" name="productType" default="请选择商品属性"/>
						<!-- 商品属性结束 -->
						
						<!-- 商品顺序开始 -->
			    		<@ms.text name="basicSort" label="商品顺序:" title="" value="0" style="width:15%;" validation={"required":"true","data-bv-stringlength-max":"11" ,"data-bv-stringlength-min":"0" ,"data-bv-stringlength-message":"长度介于0-11"} placeholder="请填写商品排列顺序"/>
						<!-- 商品顺序结束 -->
						
						<!-- 商品规格开始 -->
						<#include "${managerViewPath}/mall/product/product_specifications_tmpl.ftl"/>
						<!-- 商品规格结束 -->
						
<!------------------------------------------------------------------------------分割线------------------------------------------------------------------>
					<div id="specificationsView">
										
					</div>
					
					<!-- 用户选择商品规格开始 -->
					<div class="form-group">
						<label class="col-md-2 control-label col-xs-3">商品规格:</label>
						<div class="col-md-7 col-xs-8">
							<table class="table table-bordered table-hover specificationsTable">
								<tbody class="addSpecifications">
						        	<tr>
							        	<td><button type="button" class="btn btn-default">添加商品规格</button></td>
							        </tr>
								</tbody>
							</table>
						</div>
					</div>
					<!-- 用户选择商品规格结束 -->					
	
					<!-- 规格数据载体开始 -->
					<div class="specificationsInfo" style="display:none">
						<div class="name">
							<#if specificationsList?has_content>
					    		<select class="form-control" name="specificationsName" style="max-width: 50%;">
					    				<option value="0">请选择商品规格</option>
					    				<#list specificationsList as specifications>
					    					<option data-field="${specifications.specificationsField?default('')}" value="${specifications.specificationsId?c}">${specifications.specificationsName?default('')}</option>
					    				</#list>
					    		</select>
							</#if>
						</div>
					</div>
					<!-- 规格数据载体结束 -->	

					<!-- 商品库存开始(填写规格对应详细信息的表单) -->
					<div class="form-group inventoryTable">
						<input type="hidden" name="specificationsId">
						<label class="col-md-2 control-label col-xs-3">商品库存:</label>
						<div class="col-md-8 col-xs-9">
							<table class="table table-bordered table-hover">
								<tbody>
									<tr data-num="0">
										<td class="text-center col-md-2 addInventoryHead">价格(元)</td>
										<td class="text-center col-md-2">库存</td>
										<td class="text-center col-md-2">商家编码</td>
										<td class="text-center col-md-2">销量</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>				
					<!-- 商品库存结束(填写规格对应详细信息的表单) -->
					
				</@ms.form>
			</@ms.contentPanel>
			<!-- 表单结束 -->
		</@ms.contentBody>
	</@ms.content>
	<!-- 内容容器结束 -->
</body>
<script>
$(function(){
			//点击添加商品规格(添加商品规格按钮)
			$(".specificationsTable").delegate(".addSpecifications button","click",function(){
				//获取商品规格下拉框HTML
				var sepcificationsNameHtml = $(".specificationsInfo .name").html();

				//判断是否有商品规格数据
				if(sepcificationsNameHtml == null ||sepcificationsNameHtml == ""){
					alert("暂无可添加商品规格!");
					return ; 
				}
				
				//用户当前添加的商品规格选择的数量(从1开始计算)
				var num = $(".specificationsTable tbody").size();
				
				//对用户添加的商品规格的数量进行限制
				if(!(num<3)){
					$(".addSpecifications").hide();
				};				
				
				//用户选择规格的下拉框和点击添加详细规格的添加按钮
				var html ="<tbody data-num='"+num+"'>"+
						"<tr><td data-num='"+num+"'>"+sepcificationsNameHtml+"</td></tr>"+
						"<tr><td><div class='specificationsFieldList col-md-1' style='width: inherit;'></div>"+
						"<input type='hidden' data-num='"+num+"' class='fieldSelect2 col-md-4' style='padding: 0px;' />"+
						"<button data-num='"+num+"' type='button' class='btn btn-success addSpecificationsField' style='margin: 0px 10px 10px 10px;'>+添加</button>"+
						"</td></tr></tbody>";
				
				//将下拉框加载到相应的商品规格处
				$(".addSpecifications").before(html);
			});
			
			//重新选择商品规格时还原当前商品规格类型
			$(".specificationsTable").delegate("select[name='specificationsName']","change",function(){
				//获取位置标记
				var num = $(this).parent().attr("data-num");
				//获取用户当前选择的商品规格
				var specificationsId = $(this).val();
				
				//清楚其他规格类型选择框
				$(".specificationsTable tbody[data-num='"+num+"'] .specificationsFieldList").html("");
				$(".specificationsTable tbody[data-num='"+num+"'] .fieldSelect2").select2("val", "");
				$(".specificationsTable tbody[data-num='"+num+"'] .select2-container").remove();
				
				//规格类型不能重复，对选择的规格类型进行判断
				$(".specificationsTable select").each(function(){
					if(specificationsId == $(this).val() && num !=$(this).parent().attr("data-num") ){
						alert("规格名不能相同,请重新选择!");
						$(".specificationsTable tbody[data-num='"+num+"'] select").val(0);
						return ;
					}
				});
				
			});			
			
			//点击添加商品规格类型
			$(".specificationsTable").delegate(".addSpecificationsField","click",function(){
				//获取位置标记
				var num = $(this).attr("data-num");
				
				//当前用户选择商品规格下拉框对象
				var objSelect = $(".specificationsTable tbody[data-num='"+num+"'] select[name='specificationsName']");
				
				var specificationsId = objSelect.val();//获取规格ID
				//判断用户是否已经选择了规格名称
				if(!(specificationsId > 0)){
					alert("请选择商品规格!");
					return ;
				}
				
				//remove其他规格类型的选择框,保持页面只出现一个下拉框可选
				$(".select2-container").remove();
				
				//加载下拉框数据
				var field = objSelect.find("option:selected").attr("data-field");
				var _field = field.split(",");
				var json = [];
				for(var i=0;i<_field.length;i++){
					if(_field[i] != ""){
						json.push("\""+_field[i]+"\"");
					}
				}
				$(".specificationsTable tbody[data-num='"+num+"'] .fieldSelect2").select2($.parseJSON("{\"tags\":["+json+"]}"));
			});			
			
			//选择商品规格类型
			$(".specificationsTable").delegate(".fieldSelect2","change",function(){
				//获取位置标记
				var num = $(this).attr("data-num");
				
				//获取当前用户选择的table对象
				var objTable = $(".specificationsTable tbody[data-num='"+num+"']");
				
				//用户当前选择的商品规格
				var field = $(this).val();
				//标记用户选择的商品规格类型是否存在
				var mark = false;
				
				//选择完后关闭规格类型下拉框并清空下拉框的值
				objTable.find(".fieldSelect2").select2("val", "");
				objTable.find(".select2-container").remove();	
				
				//验证用户是否已经选择的商品类型
				objTable.find(".specificationsFieldList button").each(function(){
					if($(this).text() == field){
						mark = true;
						alert("该规格类型已经存在,请您重新选择!");
						return ;
					};
				});		
				
					
				if(mark == true){
					return ;
				}
				
				//将用户选择的商品类型添加到商品库存的Table中(获取规格类型的名称和ID)
				var specificationsName = objTable.find("select[name='specificationsName']").find("option:selected").text();//规格类型名称
				var specificationsId = objTable.find("select[name='specificationsName']").val();//规格Id
				
				//加载规格类型按钮
				objTable.find(".specificationsFieldList").append("<button type='button' data-id="+specificationsId+" class='btn btn-default' style='margin: 0px 10px 10px 0px;'>"+field+"</button>");
				
				//获取当前用户选择的规格类型数量(作为加载头部的依据)
				var specificationsFindNum = objTable.find(".specificationsFieldList button").size();
				
				//加载头部信息(当用户选择新的规格时加载头部信息)
				if(specificationsFindNum == 1){
					$(".inventoryTable .addInventoryHead").before("<td data-id='"+specificationsId+"' class='text-center col-md-2'>"+specificationsName+"</td>")
				}		
				
				//通用加载,价格,库存,商家编号,销量数据载体
				var InventoryInptHtml = '<td class="text-center"><input type="text" class="form-control"></td>'+
				'<td class="text-center"><input type="text" class="form-control"></td>'+
				'<td class="text-center"><input type="text" class="form-control"></td>'+
				'<td class="text-center">0</td>';
/*				
				if(num == 1){//第一级选择栏目时加载规格
					$(".inventoryTable tbody").append("<tr data-num='1'><td data-num='"+num+"' data-id='"+specificationsId+"' rowspan='1'>"+field+"</td>"+InventoryInptHtml+"</tr>");
				}else{
					if($(".inventoryTable td[data-num='"+num+"']").html() == null){
						$(".inventoryTable td[data-num='"+(num-1)+"']").after("<td data-num='"+num+"' data-id='"+specificationsId+"' rowspan='1'>"+field+"</td>");
					}else{
						//计算上级表格合并的个数
						for(var i= 1;i<num;i++){
							var rowspan = 1;
							for(var n=num;n>i;n--){
								rowspan = rowspan*$(".specificationsTable tbody[data-num='"+n+"']").find(".specificationsFieldList button").size();
							} 
							$(".inventoryTable tbody td[data-num='"+i+"']").attr("rowspan",rowspan);
							$(".inventoryTable tbody tr[data-num='"+(num-i)+"']").after("<tr data-num='"+num+"'><td data-num='"+num+"' data-id='"+specificationsId+"' rowspan='1'>"+field+"</td>"+InventoryInptHtml+"</tr>");
						}
					}
				}
*/					
				
				//从最下级的子类开始加载
				//获取已经加载选择商品规格类别的数量(获取子类规格时使用，最底层标记的data-num的值)
				var specificationsNum = $(".specificationsTable tbody").size()-1;
				
				//最底层规格对象
				var specificationsFieldSonObj = $(".specificationsTable tbody[data-num='"+specificationsNum+"'] .specificationsFieldList button");
				
				//用户选择的最底层子类规格类型的数量
 				var specificationsFieldSonNum = specificationsFieldSonObj.size();
				
				//for(var i=0;i<specificationsNum;i++){
					if(num == specificationsNum){//判断是否加载到同级类型,当加载到同级类型时只加载当前新增的规格类型
						$(".inventoryTable tbody tr[data-num='"+(num-1)+"']").after("<tr data-num='"+num+"'><td data-num='"+num+"' data-id='"+specificationsId+"' rowspan='1'>"+field+"</td>"+InventoryInptHtml+"</tr>");
						
						if($(".inventoryTable td[data-num='"+num+"']").html() == null){
							$(".inventoryTable td[data-num='"+(num-1)+"']").after("<td data-num='"+num+"' data-id='"+specificationsId+"' rowspan='1'>"+field+"</td>");
						}else{
							//计算上级表格合并的个数
							for(var i= 1;i<num;i++){
								var rowspan = 1;
								for(var n=num;n>i;n--){
									rowspan = rowspan*$(".specificationsTable tbody[data-num='"+n+"']").find(".specificationsFieldList button").size();
								} 
								$(".inventoryTable tbody td[data-num='"+i+"']").attr("rowspan",rowspan);
								$(".inventoryTable tbody tr[data-num='"+(num-i)+"']").after("<tr data-num='"+num+"'><td data-num='"+num+"' data-id='"+specificationsId+"' rowspan='1'>"+field+"</td>"+InventoryInptHtml+"</tr>");
							}
						}	
										
					}else{
						//加载最底层规格类型的Table
						specificationsFieldSonObj.each(function(){
							$(".inventoryTable tbody").append("<tr data-num='"+specificationsNum+"'><td data-num='"+specificationsNum+"' data-id='"+$(this).attr("data-id")+"' rowspan='1'>"+$(this).text()+"</td>"+InventoryInptHtml+"</tr>");
						});
					}
				//}
				
				
				//获取父类规格(标记参数比当前num小)
				for(var i=0;i<num;i++){
					$(".specificationsTable tbody[data-num='"+i+"'] .specificationsFieldList button").each(function(){
						//alert("父类规格："+i+"||"+$(this).text());
					});
				}
				
				//获取子类规格(标记参数比当前num大)
				for(var i=1;i<(specificationsNum-num);i++){
					$(".specificationsTable tbody[data-num='"+(i+parseInt(num))+"'] .specificationsFieldList button").each(function(){
						//alert("父类规格："+(i+parseInt(num))+"||"+$(this).text());
					});
				}
			
			});

})
</script>
</html>
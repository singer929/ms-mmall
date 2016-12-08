<!DOCTYPE html>
<html lang="zh">
 <head>
<#include "${managerViewPath}/include/macro.ftl"/>
<#include "${managerViewPath}/include/meta.ftl"/>
<script  type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=HMFNQUvLnivVtoWNAUAYrLIF"></script>
<script type="text/javascript" src="${base}/js/manager/entityshop/baiduMap.js"></script>
</head>

<body>	
<@ms.content>
<@ms.contentBody width="" >
	<@ms.contentNav title="" >
		<@ms.savebutton  id="saveUpdate" value=""/>
		<@ms.contentNavBack  class="btn btn-default returnList" value="返回列表" />
	</@ms.contentNav >
	<@ms.contentPanel>
		<@ms.form isvalidation=true name="mallShopForm" action="">
			<@ms.text name="basicTitle"  style="width: 15%;"  label="商店名称:" title="商店名称"  placeholder="请输入商店名称"  value="" />
			<@ms.text name="entityShopPhone"   style="width: 10%;"  label="商店电话:" title="商店电话"  placeholder="请输入商店电话" value="" />
			
			
			
			<!--商店所在地开始--->
			<div class="form-group">
				<label style="float:left">商店所在地:</label>
					<div style="width: 50%;float:left">
							<!----选择省----->
						<div style="width: 20%;float:left">
							<select class="form-control" id="mallLocationA" name="entityShopProvinceId" onchange="loadCity($('#mallLocationA  option:selected').val(),'#mallLocationB','请选择市');" data-ajax-url="">
								<option value="-1">请选择省</option>
								<#if listCity?has_content>
									<#list listCity as listCity>
										<#if listCity.categoryCategoryId?string=="0">
										<option value="${listCity.categoryId}">${listCity.categoryTitle}</option>
										</#if>
									</#list>
								</#if>
							</select>
						</div>
						
						<!----选择市----->
						<div style="width: 20%;float:left">
							<select class="form-control" id="mallLocationB"  name="entityShopCity" onchange="loadCity($('#mallLocationB  option:selected').val(),'#mallLocationC','请选择区或县');" data-ajax-url="">
								<option value="-1">请选择市</option>
							</select>
						</div>
						
						<!----选择区或县----->
						<div style="width: 20%;float:left">
							<select class="form-control" id="mallLocationC"name="entityShopAreaId" onchange="loadCity($('#mallLocationC  option:selected').val());" data-ajax-url="">
								<option value="-1">请选择区或县</option>
							</select>
						</div>
							
					</div>
			</div>
			<!--商店所在地结束-->
		
			
			<!--商店所在地结束-->
			<@ms.text name="entityShopAddress"   style="width: 10%;"  label="商店详细地址:" title="商店详细地址"  placeholder="请输入商店详细地址" value="" />
			
			
			<!---商店地图开始----->
			<div class="form-group">
				<label style="float:left">商店地图:</label>
				<div style="float:left;width: 65%;">
					<input type="hidden" name="entityShopX" value=""/>
					<input type="hidden" name="entityShopY" value=""/>
					<div style="padding:0;overflow: hidden;"><input id="suggestId" type="text" style="width: 40%;" placeholder="请输入搜索地址" class="form-control input-medium pull-left" autocomplete="off"></div>
					<div style="line-height: 34px;color:red">请选择下拉框中的数据</div>
					<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto;display:none;"></div>
					<div id="mapPanal" class="col-xs-8" Style="overflow: hidden; height:300px;clear: both;"></div>
				</div>
			</div>
			
			
			<!---商店地图结束----->
			<div class="form-group">
				<label style="float:left">商店图片:</label>
				<div style="float:left">
					<#if entityShop?has_content>
						<@uploadImg path="/upload/entityshop/${appId?default(0)}/" inputName="basicThumbnails" size="30"  filetype="" msg="提示：商品店图片,最多可上传30张" imgs="${entityShop.basicThumbnails?default('')}" maxSize="1"/>
						<#else>
						<@uploadImg path="/upload/entityshop/${appId?default(0)}/" inputName="basicThumbnails" size="30"  filetype="" msg="提示：商品店图片,最多可上传30张" imgs="" maxSize="1"/>
					</#if>
				</div>
			</div>
			<@ms.textarea name="basicDescription" label="商店简介:"  wrap="Soft" rows="4"  size=""  value="" placeholder="商店简介" />
			<@ms.hidden name="basicId" value="0"/>
					
		</@ms.form>
	</@ms.contentPanel>
</@ms.contentBody>
</@ms.content>        

</body>
	<script>
		$(function(){
			var btnHtml="";
			<#if entityShop?has_content>
				loadCity(${entityShop.entityShopProvinceId},'#mallLocationB','请选择市');
				loadCity(${entityShop.entityShopCity},'#mallLocationC','请选择区或县');
				loadCity(${entityShop.entityShopAreaId});
				$("input[name='basicTitle']").val("${entityShop.basicTitle?default("")}");
				$("input[name='entityShopAddress']").val("${entityShop.entityShopAddress?default("")}");
				$("input[name='entityShopPhone']").val("${entityShop.entityShopPhone?default("")}");
				$("input[name='entityShopX']").val("${entityShop.entityShopX?default("")}");
				$("input[name='entityShopY']").val("${entityShop.entityShopY?default("")}");
				$("textarea[name='basicDescription']").val("${entityShop.basicDescription?default('')}");
				 $("#mallShopForm").attr("action","${managerPath}/mall/entityShop/update.do");
				$("input[name='basicId']").val('${entityShop.basicId?default("")}');
				$("select[name='entityShopProvinceId']").find("option[value='${entityShop.entityShopProvinceId}']").attr("selected","true");
				$("select[name='entityShopCity']").find("option[value='${entityShop.entityShopCity}']").attr("selected","true");
				$("select[name='entityShopAreaId']").find("option[value='${entityShop.entityShopAreaId}']").attr("selected","true");
				$("#saveUpdate").text("修改");
				btnHtml="修改";
			<#else>
				loadMap("景德镇");
				$("#mallShopForm").attr("action","${managerPath}/mall/entityShop/save.do");
				$("#saveUpdate").text("保存");
				btnHtml="保存";
			</#if>
			
			//自定义表单的表单验证
			/*$('#mallShopForm').bootstrapValidator({
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh',
					autoFocus : true
				}
			});	*/
			
			
			//选择县或区后，地图的地址改变
			 $("#mallLocationC").change(function(){
    			var value = $(this).val();
    			if(this.value!="-1"){
					loadMap($("#mallLocationB").find("option:selected").text());
    			}
   		 	});
   		 	
   		 	//选择市后，地图的地址改变
			 $("#mallLocationB").change(function(){
    			var value = $(this).val();
    			if(this.value!="-1"){
					loadMap($("#mallLocationB").find("option:selected").text());
    			}
   		 	});
   		 	
   		 	//点击开始进行保存
		$("#saveUpdate").click(function(){
			$(this).postForm("#mallShopForm",{func:function(msg) {
				if (msg.result) {
			     	alert(btnHtml+"成功");
			     	var pageNo = 1;
			     	if(msg.resultMsg!=undefined){
			     		location.href=msg.resultMsg;
			     	}else{
			     		location.href="${managerPath}/mall/entityShop/list.do";
			     	}
			   }else{
			    	alert(msg.resultMsg);
			   }
			}});
		});
   		 	
});
	function loadMap(point,zoom){
		setMap("mapPanal",point,zoom);
		map.addEventListener('load', function(){
			//获取地图的坐标对象
			var pointObj = getPoint();
			$("input[name='entityShopX']").val(pointObj.lng);
			$("input[name='entityShopY']").val(pointObj.lat);
			addMarker(pointObj,true);
		});
		selectMap("suggestId");	
	}
	
	function loadCity(selectedVaule,nextObj,nextTips){
	
		//根据当前的选择id值查询其子id
		if(nextObj!=null){
			$.ajax({
				type : "POST",
				url : "${managerPath}/category/"+selectedVaule+"/queryChildren.do",
				success : function(data) {
					$(nextObj).html("<option value='-1'>"+nextTips+"</option>");
					for(var i=0; i<data.length;i++){
						if(data[i].categoryId!=selectedVaule){
							$(nextObj).append("<option value='"+data[i].categoryId+"'>"+data[i].categoryTitle+"</option>")
						}
					}
					<#if entityShop?has_content>
						$("select[name='entityShopCity']").find("option[value='${entityShop.entityShopCity}']").attr("selected","true");
						loadMap($("select[name='entityShopCity']").find("option[value='${entityShop.entityShopCity}']").html());
						$("select[name='entityShopAreaId']").find("option[value='${entityShop.entityShopAreaId}']").attr("selected","true");
					</#if>
				}
			});
			
		}
		
	}
	</script>


</html>















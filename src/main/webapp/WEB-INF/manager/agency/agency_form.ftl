<!DOCTYPE html>
<html lang="zh">
 <head>
  <#include "${managerViewPath}/include/meta.ftl"/>	
</head>

<body>	
	<@ms.content>
  			<@ms.contentBody >
				<@ms.contentNav title="代理管理">
					<@ms.savebutton id="saveOrUpdateAgency" value="保存"/> 
					<@ms.contentNavBack/>
				</@ms.contentNav>
				<@ms.contentPanel>
			  		<@ms.form  isvalidation=true name="agencyForm" action="${managerPath}agency/agency/save.do">
			    		<@ms.text name="agencyRealName" label="姓名" title="" value="" size="2" style="width:17%;" validation={"required":"true", "data-bv-notempty-message":"姓名不能为空","data-bv-stringlength-max":"20" ,"data-bv-stringlength-min":"1" ,"data-bv-stringlength-message":"长度介于1-16个字符"} placeholder="请填写代理姓名"/>
			    		<@ms.text name="agencyPhone" label="电话" title="" size="5" style="width:17%;"  validation={"required":"true", "data-bv-notempty-message":"电话不能为空","data-bv-stringlength-max":"20" ,"data-bv-stringlength-min":"1" ,"data-bv-stringlength-message":"长度介于1-16个字符"} placeholder="请填写代理电话"/>
			    		<@ms.text name="agencyCode" label="代理码" title="" size="5" style="width:17%;"   validation={"required":"true", "data-bv-notempty-message":"代理码不能为空","data-bv-stringlength":"true" ,"data-bv-stringlength-max":"16" ,"data-bv-stringlength-min":"1" ,"data-bv-stringlength-message":"长度介于1-16个字符" } placeholder="请填写6位数代理码"/>
			    		<@ms.text name="agencyCard" label="身份证号" title="" size="5" style="width:28%;"  validation={"required":"true", "data-bv-notempty-message":"身份证号不能为空","data-bv-stringlength-max":"16" ,"data-bv-stringlength-min":"1" ,"data-bv-stringlength-message":"长度介于1-16个字符"} placeholder="请填写身份证号码"/>
			    		<!----所在市---->
			    		<div class="form-group ms-form-group">	
			    			<label class="col-md-2  col-xs-3" for="agencyCity">所在城市</label>
			    			<div class="ms-form-control" style="height:auto;width:30%;">
			    			<select class="form-control" id="agencyCity" name="agencyCity"  data-ajax-url="">
										<option value="-1">请选择</option>
										<#if cityList?has_content>
											<#list cityList as listCity>
												<#if listCity.categoryCategoryId?string!="0">
												<option value="${listCity.categoryId}">${listCity.categoryTitle}</option>
												</#if>
											</#list>
										</#if>
								</select>
							</div>
			    		</div>
			    		<!----所在校园---->
			    		<div class="form-group ms-form-group">	
			    			<label class="col-md-2  col-xs-3" for="agencyCity">所在校园</label>
			    			<div class="ms-form-control" style="height:auto;width:50%;">
			    				<select class="form-control"  id="agencySchool" name="agencySchool" >
										<option value="-1">请选择</option>
								</select>
							</div>
			    		</div>
			    		<div id="hiddenInput"></div>
			    		<@ms.text name="agencyClass" label="班级" title="" size="5" style="width:28%;"   placeholder="请填写班级信息"/>
			    		<@ms.text name="agencySpecialty" label="专业" title="" size="5" style="width:28%;"   placeholder="请填写专业信息"/>
			    		<@ms.textarea name="agencyRemark" label="备注" size="3" />
			    	</@ms.form>
				</@ms.contentPanel>	
			</@ms.contentBody>          
	</@ms.content>
   <!--右边开始-->
</body>
</html>
<script>
	$(function(){
		<#if agency?has_content>
			$("input[name=agencyRealName]").val("${agency.agencyRealName?default('')}");
			$("input[name=agencyPhone]").val("${agency.agencyPhone?default('')}");
			$("input[name=agencyCode]").val("${agency.agencyCode?default('')}");
			$("input[name=agencyCard]").val("${agency.agencyCard?default('')}");
			$("input[name=agencyClass]").val("${agency.agencyClass?default('')}");
			$("input[name=agencySpecialty]").val("${agency.agencySpecialty?default('')}");
			$("textarea[name=agencyRemark]").val("${agency.agencyRemark?default('')}");
			$("#saveOrUpdateAgency").html("更新");
			$("select[name=agencyCity]").find("option[value="+${agency.agencyCity?default(0)}+"]").attr("selected",true);
			$("#agencyForm").attr("action","${managerPath}agency/agency/update.do");
			$("#hiddenInput").html("<input type='hidden' name='agencyId' />");
			$("input[name=agencyId]").val("${agency.agencyId?default(0)}");
			 var agencyCode = "${agency.agencyCode?default('')}";
			 //查询校园数据
			 $("select[name=agencySchool]").request({
			 		method:"post",
			 		url:"${managerViewPath}/basic/${agency.agencyCity?default(0)}/query.do",
			 		func:function(data) {
						$("#agencySchool").html("<option value='-1'>请选择校园</option>");
						for(var i=0; i<data.length;i++){
								$("#agencySchool").append("<option value='"+data[i].basicId+"'>"+data[i].basicTitle+"</option>")
						}
						//获取选择的校园数据
						var agencySchool =" ${agency.agencySchool?default('')}";
						if(agencySchool.trim()!=""){
							agencySchool = agencySchool.split(",");
							for(var i=0;i<agencySchool.length;i++){
								$("select[name=agencySchool]").find("option[value="+agencySchool[i]+"]").attr("selected",true);
							}
						}
					 }
			});
			
		</#if>
		//选择市后，查询该市的所有校园数据
			 $("#agencyCity").change(function(){
    			var value = $(this).find("option:selected").val();
    			$(this).attr("data-ajax-url","${managerViewPath}/basic/"+value+"/query.do");
    				$(this).request({method:"post",func:function(data) {
						$("#agencySchool").html("<option value='-1'>请选择校园</option>");
						for(var i=0; i<data.length;i++){
								$("#agencySchool").append("<option value='"+data[i].basicId+"'>"+data[i].basicTitle+"</option>")
						}
					}});
   		 	});
   		
   		 //判断代理码是否存在重复
   		 $("input[name=agencyCode]").blur(function(){
   		 		if($(this).val()!=null || (agencyCode!=undefined && $(this).val().trim()!=agencyCode.val().trim())){
   		 			$(this).request({method:"post",url:"${managerPath}agency/agency/"+$(this).val()+"/checkCode.do",func:function(msg) {
						if(!msg.result){
							alert(msg.resultMsg);
							$("input[name=agencyCode]").val("");
						}
						$("input[name=agencyCode]").removeAttr("disabled");
					}});
   		 		}
   		 		
   		 });
   		 //点击保存按钮进行代理信息的保存
   		 $("#saveOrUpdateAgency").click(function(){
   		 		var vobj = $("#agencyForm").data('bootstrapValidator').validate();
    			if(vobj.isValid()){
   		 			$(this).postForm("#agencyForm",{func:function(msg) {
						$("#saveOrUpdateAgency").removeAttr("disabled");
						if(msg.result){
							alert($("#saveOrUpdateAgency").html().trim()+"成功");
							location.href=msg.resultMsg;
						}else{
							alert(msg.resultMsg);
						}
						
					}});
				}
   		 });
	});
</script>
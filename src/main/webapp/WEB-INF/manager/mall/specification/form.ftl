<@ms.html5>
	 <@ms.nav title="默认规格数据编辑" back=true>
    	<@ms.saveButton  onclick="save()"/>
    </@ms.nav>
    <@ms.panel>
    	<@ms.form name="specificationForm" isvalidation=true>
    		<@ms.hidden name="specificationId" value="${(specificationEntity.specificationId)?default('')}"/>
			<@ms.text label="规格名称" name="specificationName" value="${(specificationEntity.specificationName)?default('')}"  width="240px;" placeholder="请输入规格名称" validation={"required":"false","maxlength":"50","data-bv-stringlength-message":"规格名称长度不能超过五十个字符长度!", "data-bv-notempty-message":"必填项目"}/>
			<@ms.hidden name="specificationCategoryId" value="${(specificationEntity.specificationCategoryId)?default('')}" />
			<@ms.text label="默认的字段" name="specificationDefaultFields" value="${(specificationEntity.specificationDefaultFields)?default('')}"  width="240px;" placeholder="默认规格用 ',' 隔开" validation={"required":"true","maxlength":"50","data-bv-stringlength-message":"默认的字段长度不能超过五十个字符长度!", "data-bv-notempty-message":"必填项目"}/>
    	</@ms.form>
    </@ms.panel>
</@ms.html5>
<script>
	var url = "${managerPath}/mall/specification/save.do";
	if($("input[name = 'specificationId']").val() > 0){
		url = "${managerPath}/mall/specification/update.do";
		$(".btn-success").text("更新");
	}
	//编辑按钮onclick
	function save() {
		$("#specificationForm").data("bootstrapValidator").validate();
			var isValid = $("#specificationForm").data("bootstrapValidator").isValid();
			if(!isValid) {
				<@ms.notify msg= "数据提交失败，请检查数据格式！" type= "warning" />
				return;
		}
		var btnWord =$(".btn-success").text();
		$(".btn-success").text(btnWord+"中...");
		$(".btn-success").prop("disabled",true);
		$.ajax({
			type:"post",
			dataType:"json",
			data:$("form[name = 'specificationForm']").serialize(),
			url:url,
			success: function(status) {
				if(status.specificationId > 0) { 
					<@ms.notify msg="保存或更新成功" type= "success" />
					history.go(-1); 
					location.reload(); 
				}
				else{
					<@ms.notify msg= "保存或更新失败！" type= "fail" />
					history.go(-1); 
					location.reload(); 
				}
			}
		})
	}	
</script>

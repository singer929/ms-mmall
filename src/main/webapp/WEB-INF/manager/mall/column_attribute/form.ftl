<@ms.html5>
	 <@ms.nav title="默认规格数据编辑" back=true>
    	<@ms.saveButton  onclick="save()"/>
    </@ms.nav>
    <@ms.panel>
    	<@ms.form name="columnAttributeForm" isvalidation=true>
    		<@ms.hidden name="caId" value="${(columnAttributeEntity.caId)?default('')}"/>
			<@ms.hidden  name="caCategoryId" value="${(columnAttributeEntity.caCategoryId)?default('')}" />
			<@ms.text label="规格名称" name="caName" value="${(columnAttributeEntity.caName)?default('')}"  width="260px;" placeholder="请输入规格名称" validation={"required":"false","maxlength":"50","data-bv-stringlength-message":"规格名称长度不能超过五十个字符长度!", "data-bv-notempty-message":"必填项目"}/>
			<@ms.text label="默认的字段" name="caFields" value="${(columnAttributeEntity.caFields)?default('')}"  width="260px;" placeholder="请输入字段,多值用逗号隔开" validation={"required":"true","maxlength":"50","data-bv-stringlength-message":"默认的字段,多个值用逗号隔开长度不能超过五十个字符长度!", "data-bv-notempty-message":"必填项目"}/>
    	</@ms.form>
    </@ms.panel>
</@ms.html5>
<script>
	var url = "${managerPath}/mall/columnAttribute/save.do";
	if($("input[name = 'caId']").val() > 0){
		url = "${managerPath}/mall/columnAttribute/update.do";
		$(".btn-success").text("更新");
	}
	//编辑按钮onclick
	function save() {
		$("#columnAttributeForm").data("bootstrapValidator").validate();
			var isValid = $("#columnAttributeForm").data("bootstrapValidator").isValid();
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
			data:$("form[name = 'columnAttributeForm']").serialize(),
			url:url,
			success: function(status) {
				if(status != null) { 
					<@ms.notify msg="保存或更新成功" type= "success" />
					location.href = "${managerPath}/mall/columnAttribute/index.do";
				}
				else{
					<@ms.notify msg= "保存或更新失败！" type= "fail" />
					location.href= "${managerPath}/mall/columnAttribute/index.do";
				}
			}
		})
	}	
</script>

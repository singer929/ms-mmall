<@ms.html5>
	 <@ms.nav title="产品规格关联编辑" back=true>
    	<@ms.saveButton  onclick="save()"/>
    </@ms.nav>
    <@ms.panel>
    	<@ms.form name="productAttributeForm" isvalidation=true>
    		<@ms.hidden name="paId" value="${productAttributeEntity.paId?default('')}"/>
    			<@ms.number label="商品编号" name="paProductId" value="${productAttributeEntity.paProductId?default('')}" width="240px;" placeholder="请输入商品编号" validation={"required":"false","maxlength":"50","data-bv-stringlength-message":"商品编号长度不能超过五十个字符长度!", "data-bv-notempty-message":"必填项目"}/>
    			<@ms.number label="分类扩展属性编号" name="paCaId" value="${productAttributeEntity.paCaId?default('')}" width="240px;" placeholder="请输入分类扩展属性编号" validation={"required":"false","maxlength":"50","data-bv-stringlength-message":"分类扩展属性编号长度不能超过五十个字符长度!", "data-bv-notempty-message":"必填项目"}/>
    			<@ms.text label="" name="paName" value="${productAttributeEntity.paName?default('')}"  width="240px;" placeholder="请输入" validation={"required":"true","maxlength":"50","data-bv-stringlength-message":"长度不能超过五十个字符长度!", "data-bv-notempty-message":"必填项目"}/>
    			<@ms.text label="" name="paValue" value="${productAttributeEntity.paValue?default('')}"  width="240px;" placeholder="请输入" validation={"required":"true","maxlength":"50","data-bv-stringlength-message":"长度不能超过五十个字符长度!", "data-bv-notempty-message":"必填项目"}/>
    	</@ms.form>
    </@ms.panel>
</@ms.html5>
<script>
	var url = "${managerPath}/mall/productAttribute/save.do";
	if($("input[name = 'paId']").val() > 0){
		url = "${managerPath}/mall/productAttribute/update.do";
		$(".btn-success").text("更新");
	}
	//编辑按钮onclick
	function save() {
		$("#productAttributeForm").data("bootstrapValidator").validate();
			var isValid = $("#productAttributeForm").data("bootstrapValidator").isValid();
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
			data:$("form[name = 'productAttributeForm']").serialize(),
			url:url,
			success: function(status) {
				if(status.result == true) { 
					<@ms.notify msg="保存或更新成功" type= "success" />
					location.href = "${managerPath}/mall/productAttribute/index.do";
				}
				else{
					<@ms.notify msg= "保存或更新失败！" type= "fail" />
					location.href= "${managerPath}/mall/productAttribute/index.do";
				}
			}
		})
	}	
</script>

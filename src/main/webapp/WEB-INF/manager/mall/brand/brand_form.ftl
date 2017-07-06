<@ms.html5>
    <@ms.nav back=true title="品牌编辑">
		<@ms.saveButton id="saveFormButton" postForm="categoryForm"/>
    </@ms.nav>
    <@ms.panel>
   			<@ms.form isvalidation=true name="categoryForm"   action="${managerPath}/mall/brand/${autoCURD}.do"  redirect="${managerPath}/mall/brand/list.do">
				    		<input type="hidden" name="categoryId" id="categoryId"  value="${category.categoryId}"/>
				    		<input type="hidden" name="parentId" id="parentId"  value=""/>
					    	<@ms.formRow label="商品分类" width="300">
					            	<@ms.inputTree  
					            		treeId="inputTree" 
					            		json="${productCategorys?default('')}" 
					            		jsonId="categoryId" 
					            		jsonPid="categoryCategoryId" 
					            		jsonName="categoryTitle"
					            		name="categoryCategoryId"
					            		required=false
					            		text="请选择商品分类"
					            		value="${category.categoryCategoryId?default('0')}"
					            		onclick="isSelf"
					            		parent=true
					            	/>				    		
					    	</@ms.formRow>	
				    		<@ms.text name="categoryTitle" width="300" label="名称:" title="类别名称" maxlength="30"  placeholder="类别名称"  
				    		validation={"required":"true", "data-bv-notempty-message":"请填写栏目类别"} value="${category.categoryTitle?default('')}"/>
				    		<@ms.textarea id="description"  name="categoryDescription" label="描述:"  title="栏目描述" placeholder="类别描述" maxlength="45" value="${category.categoryDescription?default('')}"/>
				    		
				    		<@ms.formRow label="缩略图">
								<@ms.uploadImg path="upload/${appId?default('0')}/mall/brand/" inputName="categorySmallImg" size="15" filetype="" msg=""  maxSize="1" imgs="${category.categorySmallImg?default('')}" />
				    		</@ms.formRow>
	    	</@ms.form>	
    </@ms.panel>
</@ms.html5>
<script> 
	function isSelf(event,treeId,treeNode) {
		var children = treeNode.children;
		$("#parentId").val(treeNode.categoryCategoryId);
		if (children!=null && children.length > 0) {
			<@ms.notify msg="请选择的子分类"/>
			return false;
		}
		return true;
	}
</script>
<@ms.html5>
    <@ms.nav title="商品编辑"><@ms.saveButton  postForm="productForm" /> </@ms.nav>
    <@ms.panel>
    	<@ms.form name="productForm" action="${managerPath}/mall/product/${autoCURD}.do" isvalidation=true redirect="${managerPath}/mall/product/list.do?${params}">
			<input type="hidden" name="basicId" value="${product.basicId}"/>
    		<@ms.text label="商品名称" name="basicTitle" value="${product.basicTitle?default('')}" width="500"  placeholder="请输入商品标题" 
    		validation={"maxlength":"50","required":"true", "data-bv-notempty-message":"必填项目","data-bv-stringlength-message":"网站标题在50个字符以内!"}/>
    		<@ms.formRow label="所属栏目" width="300">
	            	<@ms.inputTree  
	            		treeId="inputTree" 
	            		json="${columnList?default('')}" 
	            		jsonId="categoryId" 
	            		jsonPid="categoryCategoryId" 
	            		jsonName="categoryTitle"
	            		name="basicCategoryId"
	            		text="${product.column.categoryTitle?default('请选择商品分类')}"
	            		value="${product.basicCategoryId}"
	            		onclick="changeCategory"
	            	/>
			</@ms.formRow>
			<@ms.formRow label="品牌" width="300">
	            	<@ms.inputTree  
	            		treeId="brandTree" 
	            		json="[]" 
	            		jsonId="categoryId" 
	            		jsonPid="categoryCategoryId" 
	            		jsonName="categoryTitle"
	            		name="productBrand"
	            		text="请选择品牌"
	            		value="${product.productBrand}"
	            	/>
			</@ms.formRow>
			<@ms.checkbox name="basicType" label="属性" list=productTypes listKey="categoryId"  listValue="categoryTitle" valueList=product.basicTypeIds  />
			<@ms.number label="排序"  name="basicSort" value="${product.basicSort}" min=-99999 max=99999 />
			<@ms.number label="现价"  name="productPrice" value="${product.productPrice}" min=0.1 max=9999999 isFloat=true/>
			<@ms.number label="原价"  name="productCostPrice" value="${product.productCostPrice}" min=0.1 max=9999999 isFloat=true/>
			<@ms.number label="库存"  name="productStock" value="${product.productStock}" min=1 max=9999999/>
    		<@ms.text label="编码" name="productCode" value="${product.productCode?default('')}" width="300"  placeholder="" validation={"maxlength":"30","required":"true", "data-bv-notempty-message":"必填项目","data-bv-stringlength-message":"网站标题在50个字符以内!"}/>
			<@ms.formRow label="商品缩略图" width="400">
					<@ms.uploadImg path="upload/mall/product/${appId}/" imgs="${product.basicThumbnails?default('')}" inputName="basicThumbnails" size="30" msg="提示：产品缩略图,最多可上传30张"  maxSize="2"   />
			</@ms.formRow>
			<div id="contentModelFiled">
			</div>
			<@ms.editor name="productContent" label="商品详情" content="${product.productContent?default('')}" />
			<@ms.radio label="开售时间" direction=true name="productShelf" list=[{"id":"1","text":"立刻"},{"id":"2","text":"放入仓库"}] listKey="id" listValue="text" value="${product.productShelf}"/>
    	</@ms.form>
    </@ms.panel>
</@ms.html5>
<script>
	$(function() {
		//判断分类是否是父节点，如果是进行系统提示并取消选中数据
		var nodes = zTreeObjinputTree.getSelectedNodes();
		if (nodes!=null && nodes.length > 0) {
			var children = nodes[0].children;
			if (children!=null && children.length > 0) {
				zTreeObjinputTree.cancelSelectedNode(nodes[0]);
				$("input[name=basicCategoryId]").val(0);
				$("#treeLabelinputTree").text('请选择商品分类');
				<@ms.notify msg="请选择“${product.column.categoryTitle}”分类的子分类"/>
			} else {
					requestDiyContentModelFiled(nodes[0].categoryId,${product.basicId?c?default(0)});
			}
		}
		
		brand(${product.basicCategoryId},${product.productBrand});
		
	})
	
	/**
	*品牌级联
	*/
	function brand(categoryId,brandId)  {
		$("#treeLabelbrandTree").request({url:"${base}/mall/brand/list.do",data:{"categoryCategoryId":categoryId},func:function(data) {
			if (data.length==0) {
				$("#treeLabelbrandTree").text("无品牌数据");
			} else {
				$("#treeLabelbrandTree").text("请选择品牌");
			}
			$.fn.zTree.init($("#treebrandTree"),settingbrandTree,data);
			if (brandId>0) {
				var node = zTreeObjbrandTree.getNodeByParam("categoryId",brandId,null);
				if (node!=null) {
						zTreeObjbrandTree.selectNode(node);
						$("#treeLabelbrandTree").text(node.categoryTitle);
				}
			} else {
				zTreeObjbrandTree.refresh();
			}
		}});
	}
	
	
	function changeCategory(event,treeId,treeNode) {
		brand(treeNode.categoryId,0);
		requestDiyContentModelFiled(treeNode.categoryId,${product.basicId?c?default(0)});
	}
	
	function requestDiyContentModelFiled(categoryId,basicId) {
					//加载自定义模型
					var url="${managerPath}/mdiy/contentModel/contentModelField/"+categoryId+"/queryField.do";
					var _data="basicId="+basicId;
					$(this).request({url:url,data:_data,method:"post",func:function(data) {
						$("#contentModelFiled").html("");
						$("#contentModelFiled").html(data);
						$("select").select2();
					}});	
	}
</script>
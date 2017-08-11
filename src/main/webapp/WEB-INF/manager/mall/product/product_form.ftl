<#assign defaultSpecImg = base + "/images/upload.png">
<@ms.html5>
    <@ms.nav back=true title="商品编辑"><@ms.saveButton onclick="onSave()" /> </@ms.nav>
    <@ms.panel>
    	<@ms.form name="productForm" action="${managerPath}/mall/product/${autoCURD}.do" isvalidation=true redirect="${managerPath}/mall/product/list.do?${params}">
			<input type="hidden" name="basicId" value="${product.basicId}"/>
    		<@ms.text id="basicTitle" label="商品名称" name="basicTitle" value="${product.basicTitle?default('')}" width="500"  placeholder="请输入商品标题" 
    		validation={"maxlength":"50","required":"true", "data-bv-notempty-message":"必填项目","data-bv-stringlength-message":"商品名称在50个字符以内!"}/>
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
    		<@ms.text id="productCode" label="编码" name="productCode" value="${product.productCode?default('')}" width="300"  placeholder="" validation={"maxlength":"30","required":"true", "data-bv-notempty-message":"必填项目","data-bv-stringlength-message":"网站标题在50个字符以内!"}/>
			<@ms.formRow label="商品缩略图" width="400">
				<@ms.uploadImg path="upload/mall/product/${appId}/" imgs="${product.basicThumbnails?default('')}" inputName="basicThumbnails" size="30" msg="提示：产品缩略图,最多可上传30张"  maxSize="2"   />
			</@ms.formRow>
			<!-- 装自定义模型数据的地方 -->
			<div id="contentModelFiled"></div>
			<@ms.radio label="开售时间" direction=true name="productShelf" list=[{"id":"1","text":"立刻"},{"id":"0","text":"放入仓库"}] listKey="id" listValue="text" value="${product.productShelf}"/>
			
                <!--规格设置-->
                <div class="form-group ms-form-group has-feedback"> 
                    <label for="basicSort" class="col-sm-2  control-label ">规格</label>
                    <div class="col-sm-9 ms-from-group-input ms-form-input">       
                        <div class="goods-norms">
                            <div class="norms-group" id="addNormsBtn">
                                <div class="norms-title">
                                    <button type="button" class="btn btn-default add-norms-group">添加规格项目</button>
                                </div>
                            </div>
                            
                            <script type="text/x-jquery-tmpl" id="addNormsGroup">
                                <div class="norms-group" data-id="">
                                    <div class="norms-title">
                                        <select class="js-example-theme-single js-states form-control select2-hidden-accessible" tabindex="-1" aria-hidden="true" style="width: 100px;">
                                            <option value="" selected="selected">请输入规格</option>
                                            {{each(i, spec) specArr}}
							                 <#noparse> <option value="${spec.name}"> ${spec.name} </option>  </#noparse>
							            	{{/each}}
                                        </select>
                                        <span class="delete-norms">×</span>
                                    <label class="norms-addpic">
                                        <input type="checkbox"/><span>添加规格图片</span>
                                    </label>
                                    </div>
                                    <div class="norms-list">
                                        <span class="add-norms" style="display:none">
                                            <span class="add-norm-btn">+添加</span>
                                            <div class="add-norms-content">
                                                <div class="norms-select">
                                                    <select class="sel_menu2" multiple="multiple">
                                                    </select>
                                                    <button type="button" class="btn btn-primary save-norms">确定</button>
                                                    <button type="button" class="btn btn-default cancel-save">取消</button>
                                                </div>
                                                <div class="arrow"></div>
                                            </div>
                                        </span>
                                    </div>
                                </div>
                            </script>
                        </div>
                    </div>
                </div>

                <div class="form-group ms-form-group has-feedback"> 
                    <label for="basicSort" class="col-sm-2  control-label ">商品库存</label>
                    <div class="col-sm-9 ms-from-group-input ms-form-input">  
                        <div class="batch-set">
                        	批量设置：
                            <span>价格<input type="text" placeholder="请输入价格" id="allPriceTxt"/><span class="btn btn-success all-price">设置</span></span> 
                            <span>库存<input type="text" placeholder="请输入库存" id="allStockTxt"/><span class="btn btn-success all-stock">设置</span></span>
                        </div>

                        <table class="table table-bordered norms-table">
                            <thead>
                                <tr class="base-nav">
                                    <!--th data-id="1">颜色</th-->
                                    <th style="width:130px;" class="base-nav-th">价格(元)</th>
                                    <th style="width:120px;">库存</th>
                                    <th style="width:150px;">商品编码</th>
                                    <th>销量</th>
                                </tr>
                            </thead>
                            <tbody class="norms-table-tr">

                            </tbody>
                        </table>
                    </div>
                </div>
			<@ms.editor name="productContent" label="商品详情" content="${product.productContent?default('')}" />
    	</@ms.form>
    </@ms.panel>
</@ms.html5>


<script type="text/x-jquery-tmpl" id="showNormsGroup">
<#noparse>
    <div class="norms-group" data-id="${specName}">
        <div class="norms-title">
            <select class="js-example-theme-single js-states form-control select2-hidden-accessible" tabindex="-1" aria-hidden="true" style="width: 100px;">
                 <option value="${specName}" selected="selected">${specName}</option>
            </select>
</#noparse>
            <span class="delete-norms">×</span>
            <label class="norms-addpic">
                <input type="checkbox" {{if showPic }}checked="checked"{{/if}} /><span>添加规格图片</span>
            </label>
        </div>
        <div class="norms-list">
            {{each(i, proSpec) proSpecArr}}
<#noparse>
                <div class="norms-detail" data-value="${proSpec.specValue}">        
				<span class="norms-text">${proSpec.specValue}</span> 	
                    <span class="delete-norms">×</span>
                    <div id="normPic${proSpec.seed}" class="norms-pic" {{if showPic }} style="display:block" {{/if}}>

                    	<img title="点击上传或更改图片" id="normImg${proSpec.seed}" src="{{if proSpec.img!='' }}${proSpec.imgPath}{{else}}</#noparse> ${defaultSpecImg} <#noparse>{{/if}}" />
                    </div>
                </div>
            {{/each}}
</#noparse> 
            <span class="add-norms">
                <span class="add-norm-btn">+添加</span>
                <div class="add-norms-content">
                    <div class="norms-select">
                        <select class="sel_menu2" multiple="multiple">
                        </select>
                        <button type="button" class="btn btn-primary save-norms">确定</button>
                        <button type="button" class="btn btn-default cancel-save">取消</button>
                    </div>
                    <div class="arrow"></div>
                </div>
            </span>
        </div>
    </div>
</script>

<script type="text/javascript" src="http://cdn.mingsoft.net/plugins/plupload/2.2.1/plupload.full.min.js"></script>
<script src="http://cdn.mingsoft.net/plugins/validator/5.5.0/validator.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/js/manager/mall/SpecMgr.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/js/manager/mall/PluploaderMgr.js"></script>
<script type="text/javascript" >

	const DEFAULT_SPEC_IMG = '${defaultSpecImg}';

	// 保存数据
	function onSave(){
	
		$('#productForm').data('bootstrapValidator').validate();
		var isValid = $('#productForm').data('bootstrapValidator').isValid();
		if (!isValid){
			<@ms.notify msg="数据提交失败：请检查输入的数据!" type="fail"/>
			return;
		}
		
		var isSpecDetailValid = checkSpecDetail();
		if (!isSpecDetailValid) {
			<@ms.notify msg="商品规格下的价格和库存不能为空或负值!" type="fail"/>
			return;
		}
	
		// 规格数据
		var params = SpecMgr.buildSpecSvrData();
		
		var basicTypes =[];    
		$('input[name="basicType"]:checked').each(function(){    
			basicTypes.push($(this).val());    
		});
		
		//获取上下架信息
		var _productShelf="";
		$("[name='productShelf']").each(function(){
			if($(this).is(':checked')){
				_productShelf = $(this).val();
			}
		});
		
		// 产品数据
		var productParams = {
			basicId: ${product.basicId},
			basicTitle: $('#basicTitle').val(),
			basicCategoryId:$("#inputTree input[name='basicCategoryId']").val(),
			basicType: basicTypes.join(','),
			productBrand:$('#productBrand').val(),
			basicSort: $("[name='basicSort']").val(),
			productPrice: $("[name='productPrice']").val(),
			productCostPrice: $("[name='productCostPrice']").val(),
			productStock: $("[name='productStock']").val(),
			productCode: $('#productCode').val(),
			basicThumbnails: $('#basicThumbnails').val(),
			productShelf:_productShelf,
			productContent: UE.getEditor('editor_productContent').getContent()
		};
		
		
		params.product = productParams;
		// 自定义模型数据
		var customParams = {};
		$('#contentModelFiled').find('[name]').each(function(){
			var name = $(this).attr('name');
			customParams[name] = $(this).val();
		});

		var svrParams = {productParams:params, customParams:customParams};
		
		var paramStr = JSON.stringify(svrParams);
		$.post('${managerPath}/mall/product/${autoCURD}.do', {jsonStr:paramStr}, function(data, status){
			if (status != 'success') return;
			if (data.result == false){
				<@ms.notify msg="商品规格下的价格、库存不能为负值" type="warning"/>
				return;
			}

			var reditUrl = '${managerPath}/mall/product/index.do';
			window.location.href = reditUrl;
		});

		function getShelfStr(shelf){
			switch(Number(shelf)){
				case 1: return 'ON_SHELF';
				case 2: return 'DEPOT_SHELF';
			}
		}
	}
	
	// 检测规格明细数据
	function checkSpecDetail() {
		for (var i in SpecMgr.specDetails){
			var detailData = SpecMgr.specDetails[i];
			if (!detailData.price || detailData.price<0) return false;
			if (!detailData.stock || detailData.stock<0) return false;
		}
		
		return true;
	}

	/**
	*品牌级联
	*/
	function brand(categoryId, brandId)  {
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
    
    function requestDiyContentModelFiled(categoryId, basicId) {
        //加载自定义模型
        var url="${managerPath}/mdiy/contentModel/contentModelField/"+categoryId+"/queryField.do";
        var _data= "basicId=" + basicId;
        $(this).request({url:url,data:_data, method:"post", func:function(data) {
            $("#contentModelFiled").html("");
            $("#contentModelFiled").html(data);
            $("select").select2();
        }});   
    }

    // =================================以下为商品规格部分逻辑=====================================
    
    //判断是否勾选了选择商品图片勾选框，选中则出现商品规格图片上传
    $("body").delegate(".norms-addpic>input[type=checkbox]","change",function(){
    	var normsPic = $(this).parent().parent().siblings().find(".norms-pic")
    	var specName = $(this).parent().parent().parent().data('id');

        if($(this).is(":checked")){
            normsPic.show();     

            $(this).parent().parent().parent().find(".norms-list .norms-detail").each(function(){
            	 var specValue = $(this).data('value');

            	 var imgUrl = $(this).find('img').attr('src');
            	 var psData = SpecMgr.getProductSpecData(specName, specValue);
            	 if (psData){
            	 	psData.img = imgUrl;
            	 }
            });
        }else{
            normsPic.hide();

            // 逻辑上清空当前规格下所有值的图片, 显示上隐藏
            var psArr = SpecMgr.productSpecs[specName];
            for (var i in psArr){
            	psArr[i].img = "";
            }
        }
    });

	// 点击图片上传按钮	
    $("body").delegate(".norms-pic", "click", function(){
		var specValue = $(this).parent().data('value');
		var specName = $(this).parent().parent().parent().data('id');
    });                                                                                                                                                                                       
   
    // 批量设置
    function batchSet(key, value){

    	if (!validateValue(key, value)) return;

    	SpecMgr.batchSetValue(key, value);
    	
    	refreshTable();
    }


    String.prototype.format = function(){
        var args = arguments;
        return this.replace(/\{(\d+)\}/g,function(s,i){
            return args[i];
        });
    }

    // 格式化字符串
    function strFormat(str) {
        var args = arguments.pop();
        return this.replace(/\{(\d+)\}/g,function(s,i){
            return args[i];
        });
    }

    // 初始化请求规格数据 和 分离数据
    $(function () {

        //判断分类是否是父节点，如果是进行系统提示并取消选中数据
        var nodes = zTreeObjinputTree.getSelectedNodes();
        if (nodes!=null && nodes.length > 0) {
            var children = nodes[0].children;
            if (children!=null && children.length > 0) {
                zTreeObjinputTree.cancelSelectedNode(nodes[0]);
                $("input[name=basicCategoryId]").val(0);
                $("#treeLabelinputTree").text('请选择商品分类');
            } else {
                    requestDiyContentModelFiled(nodes[0].categoryId,${product.basicId?c?default(0)});
            }
        }
        
        brand(${product.basicCategoryId},${product.productBrand});

        $.post('${base}/mall/productSpecification/${product.basicId}/list.do', {}, specDataCallback);


    });

    // 请求规格数据返回
    function specDataCallback(data, status){

    	var result = SpecMgr.init(${product.basicId}, data);
        if (!result){
            <@ms.notify msg="规格数据解析失败" type="fail"/>
            return;
        }

        PluploaderMgr.init({
			url: "${basePath}/upload",
			path: "/upload/mall/product/${appId}/",
			basePath: "${basePath}"
		});

        // 显示商品规格数据
        for (var specName in SpecMgr.productSpecs){

            var psArr = SpecMgr.productSpecs[specName];

            var showPic = false;
            for (var i in psArr){
            	
            	var randSeed = new Date().getTime() + Math.floor(Math.random() * 1000);
	            psArr[i].seed = randSeed;

	            if (psArr[i].img) {
	            	psArr[i].imgPath = "${basePath}/" + psArr[i].img; 
            		showPic = true;
            	}
            }

            var specArr = SpecMgr.getSpecArr();
            var tmplObj = {specName:specName, proSpecArr:psArr, specArr:specArr, showPic:showPic};

            $("#addNormsBtn").before($("#showNormsGroup").tmpl(tmplObj));

            // 创建上传控件实例
            for (var i in psArr){
	            var triggerId = 'normPic' + psArr[i].seed;
	            var imgId = 'normImg' + psArr[i].seed;
            	PluploaderMgr.createInstance(triggerId, imgId, successCallback);
            }
        }

        setTimeout(initSelect2, 500);
        initEvents();

        // 显示商规格明细数据
        refreshTable(SpecMgr.specDetails);
    }

    function successCallback(result){

    	var triggerId = result.triggerId;
    	var imgId = result.imgId;

    	var specValue = $('#'+triggerId).parent().data('value');
    	var specName = $('#'+triggerId).parent().parent().parent().data('id');

    	// 修改缓存数据值
    	var productData = SpecMgr.getProductSpecData(specName, specValue);
    	if (!productData) {
    		console.log('图片上传成功时,未找到规格数据：'+specName + '-' + specValue);
    		return;
    	}

    	var imgUrl = result.response;
    	productData.img = imgUrl;
    }

    function initSelect2()
    {
        $(".js-example-theme-single").select2({tags: true});

        $(".sel_menu2").select2({tags:true});
    }

    function initEvents(){

        //鼠标移到每组规格设置区域，将显示删除按钮
        $(".goods-norms").delegate(".norms-group", "mouseover", function(){
            $(this).find(".norms-title .delete-norms").show()
        })
        $(".goods-norms").delegate(".norms-group", "mouseout", function(){
            $(this).find(".norms-title .delete-norms").hide()
        });
        $(".goods-norms").delegate(".norms-detail", "mouseover", function(){
            $(this).find(".delete-norms").show()
        });
        $(".goods-norms").delegate(".norms-detail", "mouseout", function(){
            $(this).find(".delete-norms").hide()
        });

        //显示添加规格的下拉框
        $(".goods-norms").delegate(".add-norm-btn", "click", function(){
            $(this).siblings(".add-norms-content").show();
        });

        //规格属性“取消”按钮
        $(".goods-norms").delegate(".cancel-save","click", function(){
            $(this).siblings("select").empty();
            $(this).parent().parent().hide();
        });

        // 批量设置价格和库存
	    $(".batch-set").find(".all-price").click(function(){
	    	batchSet('price', $('#allPriceTxt').val());
		});
	    $(".batch-set").find(".all-stock").click(function(){
	    	batchSet('stock', $('#allStockTxt').val());
	    });

        // 规格名字改变 可能是新增,可能是改变 (规格表新增规格, 商品规格表删除原规格, 添加新增规格)
        $(".goods-norms").delegate(".js-example-theme-single", "change", function () {

            var value = $(this).val().trim();
            // 有值则显示添加按钮
            setAddBtnVisible($(this).parent().parent(), value);
            if (!value) return;

            var oldSpecName = $(this).parent().parent().data('id');

            // 没有改变值的时候跳出
            if (oldSpecName == value) return;

            // 现有规格名字重复则不允许设置
            if (SpecMgr.productSpecs[value]){
            	$(this).val(oldSpecName).trigger('change');
            	<@ms.notify msg="不允许设置两个相同的规格!" type="warning"/>
            	return;
            }
            
            // 新加的规格没有具体数据的时候不管
            if (!oldSpecName) return;

            var spec = SpecMgr.getSpecConfigByName(value);

            // 规格不存在则新增规格
            if (!spec){
                spec = SpecMgr.addSpec(value);
            }

            // 商品规格数据修改修改id
            var specName = spec.name;
            SpecMgr.productSpecs[specName] = SpecMgr.productSpecs[oldSpecName];
            delete SpecMgr.productSpecs[oldSpecName];

            for (var i in SpecMgr.productSpecs[specName]){
                var psData = SpecMgr.productSpecs[specName][i];
                psData.specName = specName;
            }

            // 商品规格明细数据修改id
            for (var detailId in SpecMgr.specDetails){

                var detail = SpecMgr.specDetails[detailId];
                var values = detail.specValues;

                for (var spName in values){

                    if (spName != oldSpecName) continue;
                    values[specName] = values[spName];
                    delete values[spName];
                }
            }

            SpecMgr.setGridMap(SpecMgr.specDetails);

			// 修改表格的标识id
            $(this).parent().parent().data('id', specName);

            refreshTable();
        });

        //点击添加规格组按钮 添加一个新的规格数据
        $(".add-norms-group").click(function(){
        
        	var specArr = SpecMgr.getSpecArr();
        	var specs = {specArr: specArr};

            $(this).parent().parent().before($("#addNormsGroup").tmpl(specs));
            initSelect2();
        });
    }

    // 添加按钮的显示与否
    function setAddBtnVisible(parent, visible){

        var ui = $(parent).find(".add-norms");
        if (visible) {
            $(ui).css('display', 'inline-block');
        }
        else {
            $(ui).css('display', 'none');
        }
    }

    // 保存数据传给服务端
    $(".btn-success").click(function () {

        // todo 提交服务器逻辑
    })

    // 规格数据输入框, 确定添加规格
    $(".goods-norms").delegate(".save-norms", "click", function(){

        //获取属性填选框的值
        var inputValues = $(this).siblings("select").select2({
            tags: true
        });

        //将现有的属性存在数组中进行新增属性的对比
        var arrText = [];
        var detailUi = $(this).parent().parent().parent();

        detailUi.prevAll(".norms-detail").each(function(i){
            arrText[i] = $(this).find(".norms-text").text();
        });

        // 规格配置名读取id中的
        //var specName = detailUi.parent().data('id');
        var specName = detailUi.parent().parent().find(".norms-title select option:selected").text().trim();

        // 追加的属性必须不能为空
        if(inputValues.val() != null){

            // 原来数据为空,新增规格
            if (!arrText.length){

                var spec = SpecMgr.getSpecConfigByName(specName);
                spec = spec || SpecMgr.addSpec(specName);       // 规格不存在则新增规格

                detailUi.parent().parent().data('id', specName); // 添加名字<p></p>

                var psArr = [];
                SpecMgr.productSpecs[specName] = psArr;
				
				var specValues = inputValues.val();
                for (var i in inputValues.val()){

                	var seed = new Date().getTime() + Math.floor(Math.random() * 1000);
                	var triggerId = 'normPic' + seed;
                	var imgId = 'normImg' + seed;

                	var specValue = specValues[i];
                    psArr.push({specValue:specValue, img:"", productId:SpecMgr.productId, specName:specName});

                    detailUi.before(
                        $('<div class="norms-detail" data-value="' + specValue + '"><span class="norms-text">' + specValue + '</span><span class="delete-norms">×</span><div id="'+ triggerId +'" class="norms-pic"><img title="点击上传或更改图片" src="'+DEFAULT_SPEC_IMG + '" id="'+ imgId +'"></div></div>')
                    );
                    if(detailUi.parent().siblings().find(".norms-addpic>input[type=checkbox]").is(":checked")){
                        detailUi.siblings().find(".norms-pic").show();
                    }

                    PluploaderMgr.createInstance(triggerId, imgId, successCallback);
                }
            }
            else{
                for(i = 0; i < inputValues.val().length; i++){
                    //判断是否已有属性
                    if($.inArray(inputValues.val()[i], arrText) == -1){

                    	var seed = new Date().getTime() + Math.floor(Math.random()  * 1000);
	                	var triggerId = 'normPic' + seed;
	                	var imgId = 'normImg' + seed;

                        var spec = SpecMgr.getSpecConfigByName(specName);
                        var productSpec = SpecMgr.productSpecs[specName];
                        productSpec.push({specValue:inputValues.val()[i], img:"", productId:SpecMgr.productId, specName:specName});

                        detailUi.before(
                            $('<div class="norms-detail"><span class="norms-text">'+inputValues.val()[i]+'</span><span class="delete-norms">×</span><div id="'+ triggerId +'" class="norms-pic"><img title="点击上传或更改图片" src="' + DEFAULT_SPEC_IMG +'" id="'+ imgId +'"></div></div>')
                        );
                        if(detailUi.parent().siblings().find(".norms-addpic>input[type=checkbox]").is(":checked")){
                            detailUi.siblings().find(".norms-pic").show()
                        }

                        PluploaderMgr.createInstance(triggerId, imgId, successCallback);
                    }
                    else {
                        <@ms.notify msg="该属性已存在" type="warning"/>
                    }
                }
            }
        }

        SpecMgr.rebuildDetails(!arrText.length);

        refreshTable();

        //清空隐藏下拉框
        $(this).siblings("select").empty();
        $(".add-norms-content").hide();
    });

    //删除某个规格数据
    $(".goods-norms").delegate(".norms-title .delete-norms", "click", function(){

        var specName = $(this).parent().parent().data('id');
        var psArr = SpecMgr.productSpecs[specName];
        if (!psArr || !psArr.length) {
            $(this).parent().parent(".norms-group").remove();
            return;
        }

        SpecMgr.deleteProductSpecBySpecName(specName);

        refreshTable();
        $(this).parent().parent(".norms-group").remove();
    });

    //删除某个规格下的属性
    $(".goods-norms").delegate(".norms-detail .delete-norms", "click", function(){

        var specValue = $(this).parent().find(".norms-text").html();
        var specName = $(this).parent().parent().parent().data('id');

        SpecMgr.deleteProductSpecBySpecName(specName, specValue);

        refreshTable();

        // 最后删除界面元素 否则前面就找不到了
        $(this).parent().remove();
    });

    // 根据数据重新绘制表格
    function refreshTable() {

        //初始化表格内容
        $(".base-nav .base-nav-th").prevAll().remove();
        $(".norms-table-tr").html("");

        //执行规格表格绘制
        showAll(SpecMgr.productSpecs, SpecMgr.detailMap, $(".norms-table-tr"));
        $(".norms-table").append($(".norms-table-tr"));
    };

    // 获取一个数据结构中有多少个子数据
    function getDataCount(obj){

        var total = getObjLength(obj);
        if (!total) return total;

        for (var i in obj){
            count(obj[i]);
            break;
        }

        return total;

        function count(obj){

            if (!isNaN(obj)) return;

            total *= getObjLength(obj);

            for (var i in obj){
                count(obj[i]);
                break;
            }
        }
    }

    function getObjLength(obj) {

        var c = 0;
        for (var i in obj){
            c ++;
        }
        return c;
    }

    /**
     * 显示所有表格
     * @param productSpecs         规格字段数据
     * @param detailMap   规格具体数据
     * @param parent
     */
    function showAll(productSpecs, detailMap, parent) {

        // 写表头
        for (var specName in productSpecs){

            var specValues = productSpecs[specName];

            if (!specValues.length) continue;

            // 添加表头
            $(".base-nav-th").before('<th data-normsId='+specName+'>'+specName+'</th>');
        }

        // 绘制格子
        drawGrid('', detailMap, parent);
    }

    //基础列的设置
    const BASE_TH =
        '<td class="base-td"><input type="number" class="form-control" value="{0}" data-type="price" onblur="onChangeData(this)" min=0.0></td>' +
        '<td><input type="number" class="form-control" value="{1}" data-type="stock" onblur="onChangeData(this)" min=0></td>' +
        '<td><input type="text" class="form-control" value="{2}" data-type="code" onblur="onChangeData(this)"></td>' +
        '<td>{3}</td></tr>';

    /**
     * 递归绘制表格
     */
    function drawGrid(fullKey, map, parent) {

        for (var key in map){

            var arr = key.split(':');
            var specName = arr[0];
            var specValue = arr[1];

            var dKey = fullKey + (fullKey ? ',' : '') + key;

            if (!isNaN(map[key])){
                var detailId = map[key];
                //将最后一个子节点的规格保存到单元格并加上基础列组成一个规格行
                var detailData = SpecMgr.getDetailDataByKeys(dKey);
                if (detailData){
                    var price = detailData.price || '';
                    var stock = detailData.stock || '';
                    var code = detailData.code || '';
                    var sale = detailData.sale || 0;
                }
                else {
                    price = '';
                    stock = '';
                    code = '';
                    sale = '';
                }

                var baseThWithValue = BASE_TH.format(price, stock, code, sale);

                var tr = $('<tr key="'+dKey+'"></tr>');
                var _tr = $(tr).append("<td class='text-center norms-td' spec-id="+specName+">"+specValue+"</td>"+baseThWithValue);
                //将父节点的内容加到最终子节点的行内，并追加到表格中
                parent.children("td").prependTo(_tr);
                _tr.appendTo(parent);
            }
            else {
                var rowSpan = getDataCount(map[key]);    // 表格跨行数
                $('<td rowspan='+rowSpan+' class="text-center norms-td" spec-id='+specName+'>'+specValue+"</td>").appendTo(parent);
                drawGrid(dKey, map[key], parent);
            }
        }
    }

    // 表格中的数据改变, 修改保存的数据
    function onChangeData(input) {
    
        var value = $(input).val();
        var key = $(input).data('type');
        var tr = $(input).parent().parent();
        var keys = $(tr).attr('key');
        var detailData = SpecMgr.getDetailDataByKeys(keys);

        // 空字符串直接保存
        if (value.trim() == '') {
			delete detailData[key];
			return;
        }

        var result = validateValue(key, value);
        if (!result){
        	$(input).val(detailData[key]);
        	return;
        }

        detailData[key] = value;
    }

    // 校验文字
    function validateValue(key, value){

    	 //判断库存必须为整数
        if(key=="stock" && !validator.isInt(value)){
            <@ms.notify msg="请输入整数" type="warning"/>
            return false;
        }
        //判断价格必须为数字，且只有一个小数点
        if(key=="price" && !validator.isCurrency(value)){
            <@ms.notify msg="请输入正确价格" type="warning"/>
            return false;
        }
        //判断编号值不能为特殊字符
        if(key=="code" && !validator.isAlphanumeric(value)){
            <@ms.notify msg="不能输入特殊字符" type="warning"/>
            return false;
        }

        return true;
    }

    // 深度克隆
    function deepClone(obj){
        var result;
        var oClass=isClass(obj);
        if(oClass==="Object"){
            result={};
        }else if(oClass==="Array"){
            result=[];
        }else{
            return obj;
        }
        for(var key in obj){
            var copy=obj[key];
            if(isClass(copy)=="Object"){
                result[key]=arguments.callee(copy);//递归调用
            }else if(isClass(copy)=="Array"){
                result[key]=arguments.callee(copy);
            }else{
                result[key]=obj[key];
            }
        }
        return result;

        //判断对象的数据类型
        function isClass(o){
            if(o===null) return "Null";
            if(o===undefined) return "Undefined";
            return Object.prototype.toString.call(o).slice(8,-1);
        }
    }

    // 判断两个object 相等
    function objEqual(obj1, obj2){

        var len1 = getObjLength(obj1);
        var len2 = getObjLength(obj2);

        if (len1 != len2) return false;

        for (var i in obj1){
            if (obj1[i] != obj2[i]){
                return false;
            }
        }

        return true;
    }
</script>
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
			<@ms.number label="排序"  name="basicSort" value="${product.basicSort}" min=-99999 max=99999 />
			<@ms.number label="现价"  name="productPrice" value="${product.productPrice}" min=0.1 max=9999999 isFloat=true/>
			<@ms.number label="原价"  name="productCostPrice" value="${product.productCostPrice}" min=0.1 max=9999999 isFloat=true/>
			<@ms.number label="库存"  name="productStock" value="${product.productStock}" min=1 max=9999999/>
    		<@ms.text label="编码" name="productCode" value="${product.productCode?default('')}" width="300"  placeholder="" validation={"maxlength":"30","required":"true", "data-bv-notempty-message":"必填项目","data-bv-stringlength-message":"网站标题在50个字符以内!"}/>
			<@ms.formRow label="商品缩略图" width="400">
					<@ms.uploadImg path="upload/mall/product/${appId}/" imgs="${product.basicThumbnails?default('')}" inputName="basicThumbnails" size="30" msg="提示：产品缩略图,最多可上传30张"  maxSize="2"   />
			</@ms.formRow>
			<div class="ms-content-body-panel">

            <form role="form" method="post" action="/m-admin/mall/product/update.do" target="_self" id="productForm" name="productForm" class="form-horizontal bv-form" style="width: 100%; background-color: white;" novalidate="novalidate">

                <div class="form-group ms-form-group has-feedback"> 
                    <label for="basicTitle" class="col-sm-2  control-label ">
                        商品名称
                    </label>
                    <div class="col-sm-9 ms-from-group-input ms-form-input" style="width:500px;">       
                        <input type="text" autocomplete="off" name="basicTitle" class="form-control" placeholder="请输入商品标题" maxlength="50" required="true">

                    </div>
                </div>

                <div class="form-group ms-form-group has-feedback">   
                    <label class="col-sm-2 control-label">
                        所属栏目
                    </label>        
                    <div class="col-sm-9 ms-from-group-input" style="width:300px;">       
                        <input type="text" autocomplete="off" name="basicTitle" class="form-control" placeholder="请选择商品栏目" maxlength="50" required="true">

                    </div>
                </div>

                <div class="form-group ms-form-group has-feedback">   
                    <label class="col-sm-2 control-label">
                        品牌
                    </label>        
                    <div class="col-sm-9 ms-from-group-input ms-form-input" style="width:300px;">       
                        <input type="text" autocomplete="off" name="basicTitle" class="form-control" placeholder="请输入商品品牌" maxlength="50" required="true">

                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">商品缩略图:</label>
                    <div class="col-sm-9">
                        此处缺少上传图片控件
                    </div> 
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">商品描述:</label>
                    <div class="col-sm-9">
                        <textarea class="form-control"></textarea>
                    </div> 
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">关键字:</label>
                    <div class="col-sm-9">
                        <textarea class="form-control"></textarea>
                    </div> 
                </div>


                <!--规格设置-->
                <div class="form-group ms-form-group has-feedback"> 
                    <label for="basicSort" class="col-sm-2  control-label ">
                        规格
                    </label>
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
                                            <option value="">请输入规格</option>
                                        </select>
                                        <span class="delete-norms">×</span>
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
                    <label for="basicSort" class="col-sm-2  control-label ">
                        商品库存
                    </label>
                    <div class="col-sm-9 ms-from-group-input ms-form-input">  
                        <div style="padding:7px;font-size: 12px;border:1px #ddd solid;">批量设置：<a>价格</a> <a>库存</a></div>
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

                <div class="form-group ms-form-group has-feedback"> 
                    <label for="basicSort" class="col-sm-2  control-label ">
                        总库存
                    </label>
                    <div class="col-sm-9 ms-from-group-input ms-form-input" style="width:150px;">       
                        <input type="text" autocomplete="off" maxlength="10" name="basicSort" class="form-control">
                    </div>
                </div>
            </form>
        </div>
			<div id="contentModelFiled">
			</div>
			<@ms.radio label="开售时间" direction=true name="productShelf" list=[{"id":"1","text":"立刻"},{"id":"2","text":"放入仓库"}] listKey="id" listValue="text" value="${product.productShelf}"/>
    	</@ms.form>
    </@ms.panel>

    <script type="text/javascript" charset="utf-8" src="${base}/js/manager/mall/SpecMgr.js"></script>
<script type="text/javascript" >
    alert(222);
    /**
    *品牌级联
    */
    function brand(categoryId,brandId)  {
        $("#treeLabelbrandTree").request({url:"${base}/mall/brand/list.do",data:{"categoryCategoryId":categoryId},func:function(data) {
            if (data.length==0) {
                $("#treeLabelbrandTree").text("无品牌数据");
            } else {
                $("#treeLabelbrandTree").text("请选择品牌");
            $.fn.zTree.init($("#treebrandTree"),settingbrandTree,data);
            if (brandId>0) {
            }
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

    // =================================以下为商品规格部分逻辑=====================================
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

    var TEST_DATA_STR = '{"productSpecDetails":[{"code":"SB1234","createBy":0,"delFlag":0,"detailId":5,"order":true,"price":20.23,"productId":220,"sale":1234,"sort":42,"specValues":"1:1寸,2:白色","stock":"123","updateBy":0},{"code":"SB1234","createBy":0,"delFlag":0,"detailId":6,"order":true,"price":20.23,"productId":220,"sale":1234,"sort":42,"specValues":"1:1寸,2:黑色","stock":"123","updateBy":0},{"code":"SB1234","createBy":0,"delFlag":0,"detailId":7,"order":true,"price":20.23,"productId":220,"sale":1234,"sort":42,"specValues":"1:2寸,2:白色","stock":"123","updateBy":0},{"code":"SB1234","createBy":0,"delFlag":0,"detailId":8,"order":true,"price":20.23,"productId":220,"sale":1234,"sort":42,"specValues":"1:2寸,2:黑色","stock":"123","updateBy":0}],"productSpecs":[{"createBy":0,"delFlag":0,"img":"test.jpg","order":true,"productId":220,"psId":23,"specId":1,"specValue":"1寸","updateBy":0},{"createBy":0,"delFlag":0,"img":"test.jpg","order":true,"productId":220,"psId":24,"specId":1,"specValue":"2寸","updateBy":0},{"createBy":0,"delFlag":0,"img":"test.jpg","order":true,"productId":220,"psId":25,"specId":2,"specValue":"白色","updateBy":0},{"createBy":0,"delFlag":0,"img":"test.jpg","order":true,"productId":220,"psId":26,"specId":2,"specValue":"黑色","updateBy":0}],"specs":[{"createBy":0,"defaultFields":"","delFlag":0,"name":"尺寸","order":true,"specificationId":1,"updateBy":0},{"createBy":0,"defaultFields":"","delFlag":0,"name":"颜色","order":true,"specificationId":2,"updateBy":0}]}';

    // 初始加载数据
    $(function () {

        alert(1);
        //判断分类是否是父节点，如果是进行系统提示并取消选中数据
        var nodes = zTreeObjinputTree.getSelectedNodes();
        if (nodes!=null && nodes.length > 0) {
            var children = nodes[0].children;
            if (children!=null && children.length > 0) {
                zTreeObjinputTree.cancelSelectedNode(nodes[0]);
                $("input[name=basicCategoryId]").val(0);
                $("#treeLabelinputTree").text('请选择商品分类');
                //<@ms.notify msg="请选择“${product.column.categoryTitle}”分类的子分类"/>
            } else {
                    requestDiyContentModelFiled(nodes[0].categoryId,${product.basicId?c?default(0)});
            }
        }
        
        brand(${product.basicCategoryId},${product.productBrand});

        var result = SpecMgr.init(220, TEST_DATA_STR);
        if (!result){
            alert('规格数据解析失败!');
            return;
        }

        // 显示商品规格数据
        for (var specId in SpecMgr.productSpecs){
            var arr = SpecMgr.productSpecs[specId];
            var tmplObj = {specId:specId, specName:SpecMgr.getSpecConfigById(specId).name, specValues:arr};

            $("#addNormsBtn").before($("#showNormsGroup").tmpl(tmplObj));
        }

        initSelect2();
        initEvents();

        // 显示商规格明细数据
        refreshTable(SpecMgr.specDetails);
    });


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

        // 规格名字改变 可能是新增,可能是改变 (规格表新增规格, 商品规格表删除原规格, 添加新增规格)
        $(".goods-norms").delegate(".js-example-theme-single", "change", function () {

            var value = $(this).val();
            // 有值则显示添加按钮
            setAddBtnVisible($(this).parent().parent(), value);
            if (!value) return;

            var oldSpecId = $(this).parent().parent().attr('data-id');
            // 新加的规格没有 具体数据的时候不管
            if (!oldSpecId) return;

            var spec = SpecMgr.getSpecConfigByName(value);

            // 规格不存在则新增规格
            if (!spec){
                spec = SpecMgr.addSpec(value);
            }

            // 商品规格数据修改修改id
            var specId = spec.specificationId;
            SpecMgr.productSpecs[specId] = SpecMgr.productSpecs[oldSpecId];
            delete SpecMgr.productSpecs[oldSpecId];

            for (var i in SpecMgr.productSpecs[specId]){
                var psData = SpecMgr.productSpecs[specId][i];
                psData.specId = specId;
            }

            // 商品规格明细数据修改id
            for (var detailId in SpecMgr.specDetails){

                var detail = SpecMgr.specDetails[detailId];
                var values = detail.specValues;

                for (var spId in values){

                    if (spId != oldSpecId) continue;
                    values[specId] = values[spId];
                    delete values[spId];
                }
            }

            SpecMgr.setGridMap(SpecMgr.specDetails);

            console.log(SpecMgr.detailMap);

            // 修改表格的标识id
            $(this).parent().parent().attr('data-id', specId);

            refreshTable();
        });

        //点击添加规格组按钮 添加一个新的规格数据
        $(".add-norms-group").click(function(){

            $(this).parent().parent().before($("#addNormsGroup").tmpl());
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

        // 规格配置名
        var specName = detailUi.parent().parent().find(".norms-title select option:selected").text();

        // 追加的属性必须不能为空
        if(inputValues.val() != null){

            // 原来数据为空,新增规格
            if (!arrText.length){

                var spec = SpecMgr.getSpecConfigByName(specName);
                spec = spec || SpecMgr.addSpec(specName);       // 规格不存在则新增规格
                var specId = spec.specificationId;

                detailUi.parent().parent().attr('data-id', specId); // 添加id

                var psArr = [];
                SpecMgr.productSpecs[specId] = psArr;

                for (var i in inputValues.val())
                {
                    psArr.push({specValue:inputValues.val()[i], img:"", productId:SpecMgr.productId, specId:specId});

                    detailUi.before(
                        $('<div class="norms-detail"><span class="norms-text">'+inputValues.val()[i]+'</span><span class="delete-norms">×</span></div>')
                    );
                }
            }
            else{
                for(i = 0; i < inputValues.val().length; i++){
                    //判断是否已有属性
                    if($.inArray(inputValues.val()[i], arrText) == -1){

                        var spec = SpecMgr.getSpecConfigByName(specName);
                        var specId = spec.specificationId;
                        var productSpec = SpecMgr.productSpecs[specId];
                        productSpec.push({specValue:inputValues.val()[i], img:"", productId:SpecMgr.productId, specId:specId});

                        detailUi.before(
                            $('<div class="norms-detail"><span class="norms-text">'+inputValues.val()[i]+'</span><span class="delete-norms">×</span></div>')
                        );
                    }
                    else {
                        alert("该属性已存在");
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

        var specId = $(this).parent().parent().attr('data-id');
        var psArr = SpecMgr.productSpecs[specId];
        if (!psArr || !psArr.length) {
            $(this).parent().parent(".norms-group").remove();
            return;
        }

        SpecMgr.deleteProductSpecBySpecId(specId);

//        var specName = $(this).parent().parent().find(".norms-title select option:selected").text();
//
//        for (var id in SpecMgr.specs){
//
//            var spec = SpecMgr.specs[id];
//            if (spec.specName == specName){
//
//                delete SpecMgr.specs[id];
//                //rebuildSpecDetails();
//
//                break;
//            }
//        }

        //normsJsonList();
        refreshTable();
        $(this).parent().parent(".norms-group").remove();
    });

    //删除某个规格下的属性
    $(".goods-norms").delegate(".norms-detail .delete-norms", "click", function(){

        var specValue = $(this).parent().find(".norms-text").html();
        var specId = $(this).parent().parent().parent().attr('data-id');

        SpecMgr.deleteProductSpecBySpecId(specId, specValue);

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
        for (var specId in productSpecs){

            var specValues = productSpecs[specId];

            if (!specValues.length) continue;
            var specName = SpecMgr.getSpecConfigById(specId).name;

            // 添加表头
            $(".base-nav-th").before('<th data-normsId='+specId+'>'+specName+'</th>');
        }

        // 绘制格子
        drawGrid('', detailMap, parent);
    }

    //基础列的设置
    const BASE_TH =
        '<td class="base-td"><input type="text" class="form-control" value="{0}" data-type="price" onblur="onChangeData(this)"></td>' +
        '<td><input type="text" class="form-control" value="{1}" data-type="stock" onblur="onChangeData(this)"></td>' +
        '<td><input type="text" class="form-control" value="{2}" data-type="code" onblur="onChangeData(this)"></td>' +
        '<td>{3}</td></tr>';

    /**
     * 递归绘制表格
     */
    function drawGrid(fullKey, map, parent) {

        for (var key in map){

            var arr = key.split(':');
            var specId = arr[0];
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
                var _tr = $(tr).append("<td class='norms-td' spec-id="+specId+">"+specValue+"</td>"+baseThWithValue);
                //将父节点的内容加到最终子节点的行内，并追加到表格中
                parent.children("td").prependTo(_tr);
                _tr.appendTo(parent);
            }
            else {
                var rowSpan = getDataCount(map[key]);    // 表格跨行数
                $('<td rowspan='+rowSpan+' class="norms-td" spec-id='+specId+'>'+specValue+"</td>").appendTo(parent);
                drawGrid(dKey, map[key], parent);
            }
        }
    }

    // 表格中的数据改变, 修改保存的数据
    function onChangeData(input)
    {
        var value = $(input).val();
        var key = $(input).data('type');
        var tr = $(input).parent().parent();
        var keys = $(tr).attr('key');
        var detailData = SpecMgr.getDetailDataByKeys(keys);
        detailData[key] = value;
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
</@ms.html5>
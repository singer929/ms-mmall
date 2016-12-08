MS = {};
MS.mall = {};

/**
 * 商品规格处理类， 后台依赖：/manager/mall/product/product_specifications_tmpl.ftl文件
 */
MS.mall.specifications = {
	init : function() {
		var level = 0;
		// 添加“添加商品规格”按钮
		$(".addSpecificationsPanel").delegate(" .addSpecificationsButton", "click", function() {
			var dateLevel = $(this).parent().parent().prev().attr("data-level");
			if (dateLevel != undefined) {
				level = eval(dateLevel + 1);
			}
			$("#specificationsSelectTmp").tmpl(specificationsJson).insertBefore($(this)).attr("data-level", level++); // //增加下拉框按钮
			$(this).remove(); // 移除添加按钮
		});

		$("body").delegate(".specifications-value", 'mouseenter', function() {
			$(this).find("span").show();
		}).delegate('.title', 'mouseout', function() {
			$(this).find("span").hide();
		});

		// 选择规格，显示增加规格值按钮
		$(".addSpecificationsPanel").delegate(" .specificationsSelect", "change", function() {
			//删除原来的数据
			var oldId = $(this).parent().next().attr("data-id");
			if(parseInt(oldId)>0) {
				$(".specifications-field[data-id='"+oldId+"']").remove();
				$(".specifications-value[data-id='"+oldId+"']").remove();
				 $(this).parent().next().html("");
				initTableLayout();
				initInput();				
			}
			
			var curId = $(this).val(); // 当前规格的编号，唯一
			var target = $(this).parent().next();
			// 每次切换都要清空一下
			target.html("").removeAttr("data-id");
			// 检测是否已经存在
			if ($("div.row[data-id=" + curId + "]").length > 0)  {
				alert("不能选择已经使用的规格");
				return;
			}

			target.attr("data-id", curId);
			$('.popover').remove();
			var selObj = $(this).find("option:selected"); // 选中
			target.attr("data-text", selObj.text());
			target.attr("data-level", $(this).attr("data-level"));
			// 用户选择了有效数据才显示。
			if (selObj.val() != "") {

				$("#specificationsAddButtonTmp").tmpl(null).appendTo(target).attr("data-content", $("#specificationsFieldsSelectTmp").html()).attr("data-id", curId);
				$('.addSpecificationsFieldButton').popover({
					html : true
				});
				$(".selectValueDiv[data-id='" + curId + "']").find(".addSpecificationsFieldButton").attr("data-field", selObj.attr("data-field"));
				$(".selectValueDiv[data-id='" + curId + "']").find('.addSpecificationsFieldButton').on('shown.bs.popover', function() {
					var field = $(this).attr("data-field");
					var _field = field.split(",");
					var json = [];
					for ( var i = 0; i < _field.length; i++) {
						if (_field[i] != "") {
							json.push("\"" + _field[i] + "\"");
						}
					}
					$(".select2FieldValues").select2($.parseJSON("{\"tags\":[" + json + "]}")).attr("data-id", curId);
					$(".specificationsFieldsOk").attr("data-id", curId);
				});
			}
			

			// 判断是否还有规格，例如只有３个规格，那么显示３个之后就没有增加按钮
			if ($(".specificationsSelect").length < specificationsJson.count && $(".addSpecificationsButton").length == 0 && $(".specificationsSelect").length<5) {
				// 增加规则新增按钮
				$("#specificationsSelectButtonTmp").tmpl(null).appendTo($(this).parent().parent().parent());
			}
		});

		// 弹出下拉框的确认按钮，点击确认后将值显示在对应的规格里面
		$("body").delegate(".specificationsFieldsOk", "click", function() {

			var values = $(".select2FieldValues").select2("val"); // 选中的值
			for (i = 0; i < values.length; i++) {
				// 检测是否有重复的规格值
				if ($(".fieldValueDiv[data-text='" + values[i] + "']").length > 0) {
					alert("不能选择重复的规格值");
					return;
				}
				$(".inventoryTable").show();
				// 将新增的规格值显示在增加＋按钮的前面
				var target = $("#specificationsValuesTmp").tmpl(eval("({val:\"" + values[i] + "\",img:\"\"})")).insertBefore($("div.addSpecificationsFieldButton[data-id=" + $(this).attr("data-id") + "]")).attr("data-text", values[i]).attr("data-id", $(this).attr("data-id"));
				MS.mall.specifications.uploadIcon(target);
			}
			$('.addSpecificationsFieldButton').popover("hide");
			updateRowData($(this), values);
		});
		// 关闭弹出框
		$("body").delegate(".specificationsFieldsCancel", "click", function() {
			$('.addSpecificationsFieldButton').popover("hide");
		});

		// 删除规格值按钮
		$("#specificationsSelectPanel").delegate('.remove', 'click', function(event) {
			var curId = $(this).parents(".fieldValueDiv").attr("data-id");
			var level = $(this).parents(".fieldValueDiv").parent().attr("data-level");
			var text = $(this).prev().text();


			$(this).parents(".fieldValueDiv").remove(); //删除规格
			// 检测是否全部删除了。如果全部删除则将字段也需要删除
			if ($(".selectValueDiv[data-id='" + curId + "']").find(".fieldValueDiv").length == 0) {
				// 级别，如果是０级别，删除整行
				$(".specifications-field[data-id='" + curId + "']").remove();
				$(".specifications-value[data-id='" + curId + "']").remove();
				initTableLayout();
				initInput(); //重置所有值
				
			} else {
				if (level==0) { //删除整行
					$(".specifications-row[data-text="+text+"]");
				} else {
					var maxLevel = $(".specifications-field").length-1;
					if (level<maxLevel) { //表示有子规格
						//removeSpecificationsValue($('.specifications-value[data-id='+curId+']').find("li[data-text="+text+"]"),level,maxLevel);
					} else { //表示是最后一级
						var index = $('.specifications-value[data-id='+curId+']').find("li[data-text="+text+"]").index();
						console.log("index:" + index);
						$(".specifications-row-input").find(".row:eq("+index+")").remove();
					}
					
				}
			}
			
			if (level == "0") {	
				if ($(".specifications-row[data-id='" + curId + "']").length > 0) {
					$(".specifications-row[data-id='" + curId + "']").each(function() {
						if ($(this).find(".specifications-value:first").text().trim() == text.trim()) {
							$(this).remove();
						}
					});
				} else {
					$(".specifications-value[data-id='" + curId + "']").remove();
					$(".specifications-field[data-id='" + curId + "']").remove();
				}
			} else {
				// 删除列表中值
				$(".specifications-value li[data-text='" + text + "']").remove();
			}
			initTableLayout();
			
			///以后去掉下面两行代码
			initInput(); //重置所有值
			initTableColHeight();
			
		});
		
		function removeSpecificationsValue(target,level,maxLevel) {
			target.each(function() {
				var curId = $(this).parent().parent().attr("data-id");
				var level = $(this).parent().parent().attr("data-level");
				if (parseInt(level)+1<=maxLevel) {
					//上级li的位置对应下级ul的位置
					next.remove();
					removeSpecificationsValue(parseInt(level)+1,maxLevel);
				} else {
					//上级li的位置对应下级ul的位置
					var li = target.index();
					var next = $(".specifications-value[data-level="+(parseInt(level)+1)+"]").find("ul:eq("+li+")");
					next.remove();
				}
				//$(".specifications-row-input").find(".row:eq("+index+")").remove();			
				
			});

		}

		// 删除完整字段
		$("body").delegate('.removeField', 'click', function(event) {
			console.log($(this).prev().val());
			// 删除字段
			$("#specificationsList").find("*[data-id=" + $(this).prev().val() + "]").remove();
			$(this).parent().parent().remove();
			$(".specifications-field").remove();
			resetTableLayout();
			initTableLayout();
			initInput();
			if ($("#specificationsList").find(".specifications-row").length==0) {
				MS.mall.specifications.removeDisabled();
				$(".inventoryTable").hide();
			}
			
			if ($(".specificationsSelect").length<5 && $(".addSpecificationsButton").length == 0) {
				$("#specificationsSelectButtonTmp").tmpl(null).appendTo($("#specificationsSelectPanel"));
			}
			
		});

		// 删除规格
		$("body").on('.selectRow', 'hover', function(event) {
			$(this).toggle();
		});
		$('body').delegate(".fieldValueDiv", "mouseenter", function() {
			$(this).find(".remove").show();
		}).delegate(".fieldValueDiv", "mouseleave", function() {
			$(this).find(".remove").hide();
		});

		// 更新商品库存等数据信息
		// obj:规格 values:用户选中的值
		function updateRowData(obj, values) {
			obj = $("div.row[data-id=" + obj.attr("data-id") + "]");
			console.log("data-id" + obj.attr("data-id"));
			console.log("data-text" + obj.attr("data-text"));

			var curLength = $(" .specifications-row [data-id='" + obj.attr("data-id") + "'] ul li").length; // 当前个数
			var prevLength = $(" .specifications-row [data-id='" + obj.attr("data-id") + "']").prev().find(" ul li").length; // 当前个数
			if ($(".specifications-field[data-id=" + obj.attr("data-id") + "]").length > 0) { // 如果大于0表示存在
				// 优先检查第一级
				if (obj.attr("data-level") == "0") {
					for (i = 0; i < values.length; i++) {
						// 直接拷贝同级上级内容
						var temp = $("#specificationsList .specifications-row:last").clone();
						// 更改内容
						temp.attr("data-id", obj.attr("data-id"));
						temp.attr("data-text", values[i]);
						temp.find(".title:first").text(values[i]);
						temp.find(".title:first").attr("data-text", values[i]);
						temp.appendTo("#specificationsList");
					}

				} else { // 不是第一级就需要插入到指定位置。修改除外
					var valueJson = "";
					for (i = 0; i < values.length; i++) {
						if (values[i] != "") {
							valueJson += "{specificationsField:\"" + values[i] + "\"},";
						}
					}
					console.log("追加值" + valueJson);
					$("#specificationsValuesLi").tmpl(eval("[" + valueJson.substring(0, valueJson.length - 1) + "]")).appendTo(".specifications-value[data-id=" + obj.attr("data-id") + "]>ul");
				}
				console.log("old当前:" + curLength);
				console.log("old上级:" + prevLength);

			} else {// 否则新增一列
				var targetObj = null;// 插入的目标对象,主要是随意添加使用
				// 组织jison数据格式开始
				var fieldJson = eval("[{specificationsId:'" + obj.attr("data-id") + "', title:'" + obj.attr("data-text") + "', level:'" + obj.attr("data-level") + "'}]"); // 列的json
				// 组织规格值json
				var fieldValueJson = "";
				obj.find(".fieldValueDiv>.values>span").each(function() {
					// 如果规格在列表已经存在就不需要增加
					fieldValueJson += "{specificationsId:\"" + obj.attr("data-id") + "\",specificationsField:\"" + $(this).text() + "\", level:'" + obj.attr("data-level") + "'},";
				});
				fieldValueJson = eval("[" + fieldValueJson + "]"); // 规格的值
				// 组织jison数据格式结束
				if ($(".specificationsList-title").find("div[data-id='" + obj.attr("data-id") + "']").length == 0) {

					// 由于用户可能或随即选择规格，所以要将规格按照用户的选择选后顺序摆放，规则:0
					// ,1,2,3,4,5
					// 取出现有表格中的所有的字段，规则：.specifications-list-title同级的元素个数
					if ($(".specifications-list-title").prevAll(".specifications-field").length == 0) {
						$("#specificationsField").tmpl(fieldJson).insertBefore(".specifications-list-title"); // 添加列,只添加一次
					} else {
						$(".specifications-list-title").prevAll(".specifications-field").each(function() {
							var thisLevel = $(this).attr("data-level");// 当前级别
							var objLevel = obj.attr("data-level");// 传递过来的对象级别
							if (eval(objLevel < thisLevel)) {
								targetObj = $(this);
							}
						});
						if (targetObj == null) {
							if(obj.attr("data-text")==undefined){
								fieldJson = eval("[{specificationsId:'" + obj.attr("data-id") + "', title:'" + obj.find(".selectValueDiv").attr("data-text") + "', level:'" + obj.attr("data-level") + "'}]"); // 列的json
							}
							$("#specificationsField").tmpl(fieldJson).insertBefore(".specifications-list-title");
						} else {
							$("#specificationsField").tmpl(fieldJson).insertBefore($(targetObj));
						}
					}

					// 判断是否是一级根据row的data-level属性
					if (obj.attr("data-level") == "0") {

						// 如果当前已经有数据但是没有第一层，就将第一层加到前面，如果已经存在就新创建完整一行
						if ($(".specifications-value[data-level=0]").length == 0 && $(".specifications-value").length > 0) {
							$("#specificationsValuesUl").tmpl(eval("[{specificationsId:" + obj.attr("data-id") + ",size:[1],level:'" + obj.attr("data-level") + "'}]")).prependTo(".specifications-row"); // 先将ul插入，再进行li的组装
							$("#specificationsValuesLi").tmpl(fieldValueJson).appendTo($(".specifications-row .specifications-value:first>ul"));
						} else {
							// 新增一级完整一行
							$("#specificationsRow").tmpl(fieldValueJson).appendTo("#specificationsList");
						}

					} else { // 不是第一级就需要插入到指定位置。默认再输入框前面，修改除外
						// 判断是否有上级。如果有上级那么要成倍增加，检测上级是根据输入specifications-row-input的上级下面的row的元素个数
						var num = $(".specifications-row-input").prev().find("ul li").length / $(".specifications-row").length;
						console.log("增长个数:" + num);
						if (num > 0) {
							var str = "";
							for (i = 0; i < num; i++) {
								str += 1 + ",";
							}
							if (targetObj != null) { // 如果targetObj不为空，就表示新增的值要放在targetObj前面
								var target = $(".specifications-value[data-level=" + targetObj.attr("data-level") + "]");
								var cur = $("#specificationsValuesUl").tmpl(eval("[{specificationsId:" + obj.attr("data-id") + ",size:[1],level:'" + obj.attr("data-level") + "'}]")).insertBefore(target); // 先将ul插入，再进行li的组装
								$("#specificationsValuesLi").tmpl(fieldValueJson).appendTo(cur.find("ul"));
								if (fieldValueJson.length > 1) { // 如果插入多个值。同时更改下一级的数量
									var ul = target.children("ul:first").clone();
									target.html("");
									for (h = 0; h < fieldValueJson.length; h++) {
										ul.appendTo(target);
										ul = target.children("ul:first").clone();
									}
								}
							} else {
								$("#specificationsValuesUl").tmpl(eval("[{specificationsId:" + obj.attr("data-id") + ",size:[" + str + "],level:'" + obj.attr("data-level") + "'}]")).insertBefore(".specifications-row-input"); // 先将ul插入，再进行li的组装
								$("#specificationsValuesLi").tmpl(fieldValueJson).appendTo($(".specifications-row-input").prev().find("ul"));
							}
						} else {
							// 判断是否存在行，如果不存在行要先创建行
							if ($("#specificationsList .specifications-row").length == 0) {
								// 创建行
								$("#specificationsNewRow").tmpl(fieldJson).appendTo($("#specificationsList"));
							}
							// 根据values的长度来生成ul的个数
							$("#specificationsValuesUl").tmpl(eval("[{specificationsId:" + obj.attr("data-id") + ",size:[1],level:'" + obj.attr("data-level") + "'}]")).insertBefore(".specifications-row-input");
							$("#specificationsValuesLi").tmpl(fieldValueJson).appendTo($(".specifications-row-input").prev().find("ul"));
						}
					}
				}
				console.log("new当前:" + curLength);
				console.log("new上级:" + prevLength);

			}
			initTableLayout();
			initInput();
		}
		
		// 规格输入框的更新验证
		$("#specificationsList").delegate(" input", "change", function() {
			MS.mall.specifications.validatorInput($(this));
			MS.mall.specifications.calculation(); //计算
		});

	},
	disabled : function() {
		$("input[name=productPrice]").attr("readonly", true);
		$("input[name=productStock]").attr("readonly", true);
	},
	removeDisabled : function() {
		$("input[name=productPrice]").removeAttr("readonly");
		$("input[name=productStock]").removeAttr("readonly");

	},
	calculation : function() {
		var minPrice = 9999999 ;
		// 如果用户选择了规格就需要对原有的商品价格进行禁用并将规格里面的最低价格作为产品的默认价格
		$("#specificationsList").find(".specificationsPrice").each(function() {
			var temp = $(this).val(); 
			if (parseFloat(temp) < minPrice) {
				minPrice = temp;
			}
		});
		$(".productPriceInput").val(parseFloat(minPrice));
		// 库存，将规格里面的库存总和设置为产品的库存量
		var total = 0; //库存总量
		$("#specificationsList").find(".specificationsInvertory").each(function() {
			total += parseInt($(this).val());
		});
		$(".specificationsInvertoryInput").val(parseInt(total));
		// 禁用
		MS.mall.specifications.disabled();
	},
	uploadIcon : function(target) {
		var obj = target.find("em:first")[0];

		target.swfupload({
			upload_url : "/upload",
			post_params : {
				"uploadPath" : "/upload/product/"+appId+"/",
				"maxSize" : "1024",
				"allowedFile" : "*.jpg;*.png;*.gif;*.bmp;*.jpeg",
				"allowedFile" : ""
			},
			file_size_limit : "10240",
			file_types : "*.*",
			file_types_description : "All Files",
			file_upload_limit : "0",
			flash_url : "/jquery/swfupload/swfupload.swf",
			button_image_url : "/images/mall/upload-icon.png",
			button_width : 17,
			button_height : 18,
			button_placeholder : obj,
			debug : false,
		}).bind('fileQueued', function(event, file) {
			$(this).swfupload('startUpload');
		}).bind('fileQueueError', function(event, file, errorCode, message) {
			if (errorCode == -130) {
				alert('文件类型错误!');
			} else if (errorCode == -100) {
				alert("最多上传1个文件!");
			} else {
				alert(errorCode + '上传图片过多或上传图片过大!');
			}
		}).bind('uploadComplete', function(event, file) {
			$(this).swfupload('startUpload');
		}).bind('uploadSuccess', function(event, file, serverData) {
			target.find("i").html("<img src='"+serverData+"'/>");
		}).bind('uploadError', function(event, file, errorCode, message) {
			$('#log').append('<li>Upload error - ' + message + '</li>');
		});

	},
	edit : function(json) { // 编辑时使用
		$(".inventoryTable").show();
		// //第一层级
		$("#specificationsRow").tmpl(json).appendTo("#specificationsList");
		var fieldJson = eval("[{specificationsId:'" + json[0].specificationsId + "', img:\""+json[0].productSpecificationsImg+"\",title:'" + json[0].specifications.specificationsName + "', level:0}]"); // 列的json
		$("#specificationsField").tmpl(fieldJson).insertBefore(".specifications-list-title"); // 1

		initRow(json, 0);
		$("#specificationsSelectPanel").html("");
		initSelect(json, 0);
		console.log("========" + mallSpecificationsLevl);
		if (mallSpecificationsLevl < 4 && mallSpecificationsLevl < specificationsJson.count) { // 如果当前没有达到5级且没有超过当期商城的规格数量就显示添加按钮
			$("#specificationsSelectButtonTmp").tmpl(null).appendTo("#specificationsSelectPanel");
		}
		
		initTableLayout();
		initTableColHeight(); 
		
		var values = new Array();
		initProductSpecificationsInventory(json, values);
		console.log(json);
		console.log(values);
		for (i = 0; i < json.length; i++) {
			var temp = values.slice(i * (values.length / json.length), values.length / json.length * (i + 1));
			$("#specificationsOneRow").tmpl(temp).appendTo(".specifications-row-input[data-text='" + json[i].specificationsField + "']");
		}

		MS.mall.specifications.calculation(); //计算

	},
	validatorAll : function() {// 提交表单时验证
		var temp = true;
		$("#specificationsList").find("input").each(function() {
			var datamsvalidator = $(this).attr("data-ms-validator");
			if (datamsvalidator == undefined) {
				return true;
			}
			temp = MS.mall.specifications.validatorInput($(this));
			if (!temp) {
				return false;
			}
		});
		return temp;
	},
	validatorInput : function(target) { // 验证输入框
		var datamsvalidator = target.attr("data-ms-validator");
		if (datamsvalidator == undefined) {
			return;
		}
		try {
			datamsvalidator = eval("(" + datamsvalidator + ")");
		} catch (e) {
			alert("规格输入框的验证json格式错误");
			return false;
		}
		if (datamsvalidator != undefined && typeof (datamsvalidator) == "object") {
			var value = target.val();
			if (datamsvalidator.required == true) {
				if (value.trim() == "") {
					flag = false;
				}
			}

			if (datamsvalidator.range == true) {
				if (datamsvalidator.min != undefined && datamsvalidator.max != undefined) {
					flag = parseFloat(value) >= parseFloat(datamsvalidator.min) && parseFloat(value) <= parseFloat(datamsvalidator.max);
				}
			}
		}
		if (!flag) {
			target.focus();
			target.addClass("err");
		} else {
			target.removeClass("err");
		}
		return flag;
	},
	setValue : function(target) {// 组织json数据
		var json = {};
		/*
		 * {规格ID,规格名称,子:[ // {规格ID,规格名称,子[ {规格ID,规格名称,商品值}， {规格ID,规格名称,商品值}，
		 * {规格ID,规格名称,商品}]}， {规格ID,规格名称,子[ {规格ID,规格名称,商品}， {规格ID,规格名称,商品}，
		 * {规格ID,规格名称,商品}]}， ]}，
		 */
		console.log("=====buildJson");

		var jsonArr = new Array();
		var count = $("#specificationsList .specifications-field").length;
		$("#specificationsList .specifications-row").each(function() {
			var rowArr = new Array();
			var index = 0;
			$(this).find(".specifications-value").each(function() { //
				console.log("specificationsId:" + $(this).attr("data-id"));
				var childId = $(this).attr("data-id");
				rowArr[index] = "{'specificationsId':" + $(this).attr("data-id") + "}";
				var childArr = new Array();
				$(this).find("ul li").each(function() {
					console.log("specificationsField:" + $(this).attr("data-text"));
					var temp = eval("(" + rowArr[index] + ")");
					temp.specificationsField = $(this).attr("data-text");
					var img = $(".fieldValueDiv[data-text='"+temp.specificationsField+"']").find("img");
					if (img!=undefined) {
						temp.productSpecificationsImg = img.attr("src");
					}
					childArr.push(temp);
				});
				rowArr[index] = childArr;
				index++;
			});
			jsonArr.push(rowArr);
		});

		// 组织输入框值
		var inputIndex = 0;
		var maxLevel = $(".specifications-field").length - 1;
		for (i = 0; i < jsonArr.length; i++) {
			for (j = 0; j < jsonArr[i][maxLevel].length; j++) {
				$(".specifications-row-input .row:eq(" + inputIndex + ")").each(function() {
					var valueJson = {};
					valueJson.specificationsPrice = $(this).find("input:eq(0)").val();
					valueJson.specificationsInvertory = $(this).find("input:eq(1)").val();
					valueJson.productSpecificationsInventoryCode = $(this).find("input:eq(2)").val();
					jsonArr[i][maxLevel][j].productSpecificationsInventory = valueJson;
				});
				inputIndex++;
			}
		}

		for (i = 0; i < jsonArr.length; i++) {
			buildRowJosn(jsonArr[i], jsonArr[i].length - 1);
		}
		var jsonStr = new Array();
		for (i = 0; i < jsonArr.length; i++) {
			jsonStr.push(jsonArr[i][0][0]);
		}

		console.log(jsonArr);
		console.log(jsonStr);
		console.log(JSON.stringify(jsonStr));
		// JSON.stringify(jsonStr)规格的json数据
		target.val(JSON.stringify(jsonStr));
	}
};

function buildRowJosn(row, index) {
	if (row.length == 0) {
		rowArr.push(row);
	} else {
		var last = row[index - 1]; // 上一级
		var cur = row[index]; // 当前
		if (last != undefined) {// 判断下一级是否有值
			var level = cur.length / last.length;
			for (k = 0; k < cur.length; k++) {
				var lastIndex = parseInt(k / level);
				if (k % level == 0 && k > 0) {
					// lastIndex = lastIndex-1;
				}
				if (last[lastIndex].childProductSpecifications != undefined) {
					last[lastIndex].childProductSpecifications.push(cur[k]);
				} else {
					last[lastIndex].childProductSpecifications = new Array(cur[k]);
				}
			}

			buildRowJosn(row, index - 1);
		}

	}
}
/**
 * 重新绘制表格
 */
function resetTableLayout() {
	var level = 0;
	$(".specifications-row").remove(); //删除所有行
	$("#specificationsSelectPanel").find(".selectRow").each(function() { //遍历所有的规格
		$(this).attr("data-level", level); //设置级别
		var target = $(this).find(".selectValueDiv");  //规格对应的所有值
		var fieldValueJson = "";
		$(this).find(".fieldValueDiv").each(function() { //遍历当前规格的所有值
			fieldValueJson += "{specificationsId:\"" + $(this).attr("data-id") + "\",specificationsField:\"" + $(this).attr("data-text") + "\", level:'" + level + "'},";
		});

		fieldValueJson = eval("[" + fieldValueJson + "]"); // 规格的值
		if (level == 0) {
			$("#specificationsRow").tmpl(fieldValueJson).appendTo("#specificationsList");
		} else {
			if (fieldValueJson.length > 0) {
				var liLen = $(".specifications-value[data-level=" + (level - 1) + "]:first").find("li").length;
				var str = "";
				for (i = 0; i < liLen; i++) {
					str += 1 + ",";
				}
				$("#specificationsValuesUl").tmpl(eval("[{specificationsId:" + fieldValueJson[0].specificationsId + ",size:[" + str + "],level:'" + level + "'}]")).insertAfter($(".specifications-value[data-level=" + (level - 1) + "]")); // 先将ul插入，再进行li的组装
				$("#specificationsValuesLi").tmpl(fieldValueJson).appendTo($(".specifications-row-input").prev().find("ul"));
			}

		}
		
		
		var selectObj = $(this).find(".specificationsSelect");
		selectObj.attr("data-level", level);
		$(this).find(".selectValueDiv").attr("data-level", level);
		if (selectObj.val() != undefined) {
			var fieldJson = eval("[{specificationsId:'" + $(this).find(".selectValueDiv").attr("data-id") + "', title:'" + selectObj.find("option:selected").text() + "', level:" + level + "}]"); // 列的json
			$("#specificationsField").tmpl(fieldJson).insertBefore(".specifications-list-title");
		}
		level++;
	});

}

/**
 * 初始化表格样式
 */
function initTableLayout() {

	// 计算列的布局
	// 通过标题确定有多少层级
	var objs = $(".specifications-list-title").prevAll(".specifications-field");
	for (i = objs.length - 1; i > -1; i--) {
		// 1个li->Ul
		var obj = $(objs[i]);
		var liObj = $(".specifications-value[data-level=" + obj.attr("data-level") + "]:first").find("li"); // 父
		var targetObj = $(".specifications-value[data-level=" + obj.attr("data-level") + "]").next();// 子
		var ul = targetObj.children("ul:first").clone();
		targetObj.html("");

		for (k = 0; k < liObj.length; k++) {
			ul.appendTo(targetObj);
			ul = targetObj.children("ul:first").clone();
		}
	}

	// 计算高度
	for (i = 0; i < objs.length; i++) {
		var obj = $(objs[i]);
		var ulObj = $(".specifications-value[data-level=" + obj.attr("data-level") + "]:first").find("ul"); // 父
		var targetObj = $(".specifications-value[data-level=" + obj.attr("data-level") + "]").prev();// 子
		targetObj.find("li").css("line-height", (ulObj.height() - 1) + "px");
	}
	console.log("计算输入框个数：");
	
	//技术规格区域的宽度
	$("#specificationsList").width((($(".specificationsList-title>div").length+3)*($(".specificationsList-title>div").width()+45))+10+"px");
}

function initTableColHeight() {

	// 计算列的布局
	// 通过标题确定有多少层级
	var objs = $(".specifications-list-title").prevAll(".specifications-field");

	// 计算高度
	for (i = 0; i < objs.length; i++) {
		var obj = $(objs[i]);
		var ulObj = $(".specifications-value[data-level=" + obj.attr("data-level") + "]:first").find("ul"); // 父
		var targetObj = $(".specifications-value[data-level=" + obj.attr("data-level") + "]").prev();// 子
		targetObj.find("li").css("line-height", (ulObj.height() - 1) + "px");
	}
	console.log("计算输入框个数：");
}

/**
 * 初始化输入框，如果是新增字段json参数可以不需要，如果编辑json数据必须存在值
 * 
 * @param json
 */
function initInput() {
		var len = $(".specifications-row-input:first").prev().find("ul li").length;
		$(".specifications-row-input").html("");
		for (i = 0; i < len; i++) {
			$("#specificationsOneRow").tmpl(null).appendTo(".specifications-row-input");
		}

}

/**
 * 初始化下拉框
 * 
 * @param productSpecificationsJson
 *            参考规格
 * @param level
 *            层次
 */
function initSelect(productSpecificationsJson, level) {

	var id = productSpecificationsJson[0].specificationsId;
	var text = productSpecificationsJson[0].specificationsField;
	// 1下拉菜单
	var targetObj = $("#specificationsSelectButtonTmp").tmpl(null).appendTo("#specificationsSelectPanel").attr("data-id", id).attr("data-level", level);
	// 3增加下拉菜单
	var selectObj = $("#specificationsSelectTmp").tmpl(specificationsJson).appendTo(targetObj.find(".specificationsSelectPanel")).attr("data-level", level); // //增加下拉框按钮
	// 4移除增加按钮
	targetObj.find(".addSpecificationsButton").remove();
	// 5选中值
	selectObj.val(id);
	// 6规格值增加按钮
	targetObj.find(".selectValueDiv").attr("data-id", id);
	targetObj.find(".selectValueDiv").attr("data-text",productSpecificationsJson[0].specifications.specificationsName);
	var addButton = $("#specificationsAddButtonTmp").tmpl(null).appendTo(targetObj.find(".selectValueDiv")).attr("data-content", $("#specificationsFieldsSelectTmp").html()).attr("data-id", id).attr("data-field", selectObj.find("option:selected").attr("data-field")); // 选中);
	// 处理增加按钮的弹出效果
	addButton.popover({
		html : true
	});
	$(".selectValueDiv[data-id='" + id + "']").find('.addSpecificationsFieldButton').on('shown.bs.popover', function() {
		var field = $(this).attr("data-field");
		var _field = field.split(",");
		var json = [];
		for ( var i = 0; i < _field.length; i++) {
			if (_field[i] != "") {
				json.push("\"" + _field[i] + "\"");
			}
		}
		$(".select2FieldValues").select2($.parseJSON("{\"tags\":[" + json + "]}")).attr("data-id", id);
		$(".specificationsFieldsOk").attr("data-id", id);
	});
	// 7增加值
	for (i = 0; i < productSpecificationsJson.length; i++) {
		var target = $("#specificationsValuesTmp").tmpl(eval("({val:\"" + productSpecificationsJson[i].specificationsField + "\",img:\""+productSpecificationsJson[i].productSpecificationsImg+"\"})")).insertBefore(addButton).attr("data-text", productSpecificationsJson[i].specificationsField).attr("data-id", id);
		MS.mall.specifications.uploadIcon(target);

	}
	if (productSpecificationsJson[0].childProductSpecifications != undefined && productSpecificationsJson[0].childProductSpecifications.length > 0) {
		initSelect(productSpecificationsJson[0].childProductSpecifications, level + 1);
	} else {
		mallSpecificationsLevl = level + 1;
	}

}

var values = new Array();
var mallSpecificationsLevl = 0;// 当前商城规格层级
function initRow(cur, level) {
	if (cur[0].childProductSpecifications != undefined && cur[0].childProductSpecifications.length > 0) { // 如果还有子集
		var fieldJson = eval("[{specificationsId:'" + cur[0].childProductSpecifications[0].specificationsId + "', title:'" + cur[0].childProductSpecifications[0].specifications.specificationsName + "', level:" + (level + 1) + "}]"); // 列的json
		$("#specificationsField").tmpl(fieldJson).insertBefore(".specifications-list-title");
		// 渲染数据
		var parentLen = cur.length;// 父层级长度
		var childLen = cur[0].childProductSpecifications.length;// 子层级长度
		if (level == 0) {
			parentLen = 1;
		} else if (level > 1) {
			// 根据最后一个specifications-value的li格式决定ul的个数
			parentLen = $(".specifications-row:eq(0)").find(".specifications-value:last").find("li").length;
		}

		var str = "";
		for (i = 0; i < parentLen; i++) {
			str += 1 + ",";
		}
		$("#specificationsValuesUl").tmpl(eval("[{specificationsId:" + cur[0].childProductSpecifications[0].specificationsId + ",size:[" + str + "],level:" + (level + 1) + "}]")).insertBefore(".specifications-row-input");
		$("#specificationsValuesLi").tmpl(cur[0].childProductSpecifications).appendTo($(".specifications-row-input").prev().find("ul"));
		initRow(cur[0].childProductSpecifications, level + 1);

	}
}

function initProductSpecificationsInventory(json, arr) {
	for ( var i = 0; i < json.length; i++) {
		if (json[i].childProductSpecifications != undefined && json[i].childProductSpecifications.length > 0) {
			initProductSpecificationsInventory(json[i].childProductSpecifications, arr);
		} else {
			if (json[i].productSpecificationsInventory != undefined) {
				arr.push(json[i].productSpecificationsInventory);
			}
		}
	}

}

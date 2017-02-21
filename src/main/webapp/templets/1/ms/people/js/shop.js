// 'JavaScript Document
$(function(){
	var allSum = 0;	//总价
	var allNum = 0;		//商品总数

	$("input[name='toggle-checkboxes']").click(function() {  					//全选按钮
		allSum = 0;
		allNum = 0
		var isAllCheck=$(this).get(0).checked;									//获取当前全选复选框的状态：true为选中，false为未选中
		$("input[name='toggle-checkboxes']").each(function(index, element){		//让两个全选框状态保持一致
			$(this).get(0).checked=isAllCheck;
		});
		$("[name='checkItem']").each(function(index, element) {			
	    	$(this).get(0).checked=isAllCheck;
			var sum = $(this).parent().next().next().next().next().text();				//获取当前的小计值
			var num = $(this).parent().next().next().next().children("input").val()		//获取当前的商品数
			if(isAllCheck) { 
				$(this).parent().parent().css("background-color","#FFF4E8");			//如果是现在全部选中，则追加被选中颜色
				allSum += Number(sum);
				allNum += Number(num);
			}else{ 
				$(this).parent().parent().css("background-color","white");				//如果是取消全部选中，则追加未选中颜色
				allSum = 0;
				allNum = 0;	
			}
			$("#select-total-red-piece").html(allNum);
			$(".select-total-prices em").html("￥"+allSum);
	    });
		
	});

	$("[name='checkItem']").click(function(e) {									//商品复选框事件：若每个商品复选框被选中则全选按钮也被选中
	        var isAll = true;													//默认全选标记为真
			var sum = $(this).parent().next().next().next().next().text();		//获取当前商品的小计
			var num = $(this).parent().next().next().next().children("input").val()
			if ($(this).get(0).checked === false) {
				allSum -= Number(sum);
				allNum -= Number(num);			
			}
			else {
				allSum += Number(sum);
				allNum += Number(num);
			}
			$("#select-total-red-piece").html(allNum);
			$(".select-total-prices em").html("￥"+allSum);
			$("[name='checkItem']").each(function(index, element) {		//循环所有商品的复选框，若有一个未被选中，则把全选标记设置为假并直接退出循环
	            if($(this).get(0).checked === false){
					isAll=false;
					return false;										//之前我用的是break，但是在遍历里没效果，后来我上网搜了一下，是使用return false
				}
	        });
			$("input[name='toggle-checkboxes']").each(function(index, element){		//此遍历把isAll的值赋给所有全选按钮
				$(this).get(0).checked = isAll;
			});			
		});

	$("input[name='checkItem']").click(function(){ 						//商品复选框被选中后，添加被选中样式
		var checkeState=$(this).get(0).checked;
		$(this).parent().parent().css("background-color","#FFF4E8");
		if(checkeState===false) { 										//商品复选框取消选中后 ，添加未被选中样式
			$(this).parent().parent().css("background-color","white");
		}
	});

	$(".item-shop-list-num-up > a").click(function() { 	   	//商品点击加1，此次点击事件会触发商品被选中的效果
		var shopNum= $(this).prev().val();
		shopNum ++ ;	
		$(this).prev().val(shopNum);
		var thisCheck=$(this).parent().prev().prev().prev().children();
		var thisShList=$(this).parent().parent();
		$(thisCheck).get(0).checked = true;
		$(thisShList).css("background-color","#FFF4E8");
		allNum++;
		$("#select-total-red-piece").html(allNum);
		money($(this),"+");
	});
		
	$(".item-shop-list-num-down > a").click(function() { 	//商品点击减1
		var shopNum= $(this).next().val();
		if( shopNum > 0) { 							//商品应该不能减到0，你要是不想买该商品可以删除
			shopNum -- ;	
			$(this).next().val(shopNum);
			allNum--;
			$("#select-total-red-piece").html(allNum);
			money($(this),"-");
		}
	});

	$(".item-shop-list-del").click(function() { 			//点击删除，删除当前的商品列表
		$(this).parent().remove();
		money($(this),"-");
	});

	/*$(".select-inventory-batch a").click(function() { 			//删除选中的商品
		$("[name='checkItem']").each(function(index, element) {
	            if($(this).get(0).checked === true){
					$(this).parent().parent().remove();
					money($(this),"-");
				}
	    });
	});*/
	                                            
	function money(va,oper) { 									//小计
		var num = va.siblings("input").val();				//获取商品数量
		var price = va.parent().prev().text();				//获取商品单价
		var sum = num * price;								//计算小计
		va.parent().next().text(sum);						//赋值小计
		if($(va.parent().prev().prev().prev().children()).get(0).checked === true){
			if(oper == "+"){
				allSum += Number(price);
			}
			else{
				allSum -= Number(price);
			}
		}
		$(".select-total-prices em").html("￥"+allSum);				//将值输出给总价
	}

	/**我的收藏**/

	var click = true ;
	$("input[name='allchecked']").click(function() {  			//全选
		var result=$(this).get(0).checked;
		$("[name='inputchecked']").each(function(index, element) {					//  循环查找同名称的复选框
	    	$(this).get(0).checked=result;							 
			$("[name='inputchecked']").attr("checked","checked");			// 点击之后让所有复选框选中
		});
	});
	$(".cut-main-list").delegate("[name='inputchecked']","click",function(){
		$("[name='inputchecked']").each(function(index, element) {					//  循环查找同名称的复选框
			if($(this).get(0).checked) {
				click = true; 
			}else {
				click = false;
				return false;
			}
		}); 
		if(click) {
			$("input[name='allchecked']").prop("checked",true);
		}else {
			$("input[name='allchecked']").prop("checked",false);
		}
	});	
});













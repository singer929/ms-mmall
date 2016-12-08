<!DOCTYPE html>
<html lang="zh">
 <head>
<#include "${managerViewPath}/include/macro.ftl"/>
<#include "${managerViewPath}/include/meta.ftl"/>
</head>

<body>	
<@ms.content>
<@ms.contentBody >
	<@ms.contentNav title="购物车管理" ></@ms.contentNav >
	<@ms.contentPanel>
		<@ms.table head=['购物车ID','商品名称','商品图片','商品价格','商品折扣','商品数量','会员名称','商家名称','下单时间','操作']>
			<#if listArticle?has_content>
	        	<#list listArticle as listArticle>
                	 <tr>
			            <td class="text-center">${cart.cartId?c?default(0)}</td>
			            <td class="text-center">${cart.basicTitle?default("暂无")}</td>
			            <td class="text-center"><img style="width:20px; height:20px;" src="${base}${cart.basicThumbnails?default('暂无')}"/></td>
			            <td class="text-center">${cart.goodsPrise?c?default(0)}</td>
			            <td class="text-center">${cart.goodsRebate?c?default(0)}</td>
			            <td class="text-center">${cart.goodsNum?c?default(0)}</td>
			            <td class="text-center">${cart.cartPeopleId?default(0)}</td>
			            <td class="text-center">${cart.basicPeopleId?default(0)}</td>
			            <td class="text-center">${cart.cartTime?string("yyyy-MM-dd HH:mm:ss")}</td>
			            <td class="text-center operate">
		                    <a class="btn btn-xs tooltips deleteCart" data-toggle="tooltip" data-id="${cart.cartId?default(0)}" data-original-title="删除">
		                        <i class="glyphicon glyphicon-trash"></i>
		                    </a>
						</td>
			        </tr>>
			   </#list>
			<#else>
				<tr>
		            <td colspan="10" class="text-center">
		            	<@ms.nodata content="暂无购物车"/>
					</td>
	          </tr>
        	</#if>
		</@ms.table>
		<!--分页-->
	   	<@showPage page=page/>
	   	
	   	<!--删除限时文章-->    
		<@ms.modal modalName="deleteModal" title="删除购物车">
			  <@ms.modalBody>
			  		确定要删除所选的购物车吗?
		     </@ms.modalBody>
			 <@ms.modalButton>
		 		<@ms.button class="btn btn-danger rightDelete" value="确定"/>
		 	</@ms.modalButton>
		</@ms.modal>
				
				
	</@ms.contentPanel>
</@ms.contentBody>
</@ms.content>        

</body>
<script type="text/javascript">	

$(function () {
	var cartId=0;		
	//删除购物车中的商品
	$(".deleteCart").on("click",function(){
		cartId = $(this).attr("data-id");
		$(".deleteModal").modal();
	});
	
	//确定删除
	$(".rightDelete").on("click",function(){
		$(".rightDelete").request({url:base+"${baseManager}/cart/"+cartId+"/delete.do",type:"json",method:"post",func:function(data) {
			if(msg != 0) {
				alert("商品删除成功");
	    		if($("tbody tr").length==0 && msg != 1){
	    			location.href = base+"${baseManager}/cart/list.do?pageNo="+(msg-1);
				}else{
					location.href = base+"${baseManager}/cart/list.do?pageNo="+msg;
				}
	    	} else {
				alert("商品删除失败");
		    	$(".deleteModal").modal("hide");
	    	}
		}});
	});
		
});

</script>
</html>














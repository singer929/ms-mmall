<@ms.html5>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.js"></script>
<!-- Latest compiled and minified Locales -->
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/locale/bootstrap-table-zh-CN.min.js"></script>
    <@ms.nav title="商品管理" back=false>
	</@ms.nav>
    <@ms.searchForm name="searchForm" action="${managerPath}/mall/product/list.do">
        	<@ms.select 
			    name="productShelf" 
			    label="状态" 
			    list=[{"id":"-1","name":"全部"},{"id":"1","name":"上架"},{"id":"2","name":"下架"}]
			    listKey="id" 
			    listValue="name"
			    value="${productShelf?default('')}"
			/>
			<@ms.searchFormButton>
				 <@ms.queryButton form="searchForm"/> 
			</@ms.searchFormButton>			
	</@ms.searchForm>	
    <@ms.panel>
		<table data-toggle="table" data-detail-view="true"  data-detail-formatter="detailFormatter">		
		    <thead>
		        <tr>
		            <th data-width="150">订单号</th>
		            <th data-width="150">下单用户</th>
		            <th data-width="150" data-align="right">订单总额</th>
		            <th data-width="150" data-align="right">运费金额</th>
		            <th data-width="150" data-align="right">订单状态</th>
		            <th data-width="150" data-align="right">支付方式</th>
		            <th>下单时间</th>
		        </tr>
		    </thead>
		    <tbody>
		        <tr>
		            <td>14871475950320845</td>
		            <td>张三1</td>
		            <td>500.00</td>
		            <td>10.00</td>
		            <td>已完成</td>
		            <td>在线支付</td>
		            <td>2017-02-15</td>
		        </tr>
		        <tr>
		            <td>14871475950320845</td>
		            <td>张三1</td>
		            <td>500.00</td>
		            <td>10.00</td>
		            <td>已完成</td>
		            <td>在线支付</td>
		            <td>2017-02-15</td>
		        </tr>		        
		    </tbody>
		</table>
    </@ms.panel>
</@ms.html5>	        
 <script>
 	   function detailFormatter(index, row) {
	        
	        return $("#test").html();
    	}

 </script>
 <div id="test" style="display:none">
		<table class="table">
		        <tr>
		            <td width="70"><img src="http://localhost:8080/upload/mall/product/1///1484905377060.jpg!350x350.jpg" width="62" height="62"/></td>
		            <td>烟花烫女装气质复古时尚不规则套装 安晴 颜色:紫色,尺码:M</td>
		            <td>x1</td>
		        </tr>
		        <tr>
		            <td><img src="http://localhost:8080/upload/mall/product/1///1484905377060.jpg!350x350.jpg" width="62" height="62"/></td>
		            <td>烟花烫女装气质复古时尚不规则套装 安晴 颜色:紫色,尺码:M</td>
		            <td>x1</td>		            
		        </tr>
		</table>
		<table class="table">
			<tr>
				<td>
收货人信息<br/>
收货人：测试<br/>
地址： 景德镇 昌江区 瓷都大道<br/>
手机号码：133****0130<br/>
				</td>
			</tr>
		</table>
</div>
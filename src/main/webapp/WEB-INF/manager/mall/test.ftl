<html>

<head>
	<script type="text/javascript" src="${base}/static/plugins/jquery/1.9.1/jquery-1.9.1.js"></script>
</head>


<body>

<h1>Hello World</h1>

<button onclick="onTest()">Test</button>

<form action="${managerPath}/mall/test/index.do" method="post">
	<input type="hidden" name="a" value="1" />
	<input type="hidden" name="b" value="2" />
<input type="submit" value="提交" />
</form>

</body>

<script type="text/javascript">
	$(function () {
		

	});

	function onTest(){

		var params = {};

		params.productSpecList = [
			{
				productId: 220,
				specId: 1,
				specValue: '1寸',
				img: 'test.jpg'
			},

			{
				productId: 220,
				specId: 1,
				specValue: '2寸',
				img: 'test.jpg'
			},

			{
				productId: 220,
				specId: 2,
				specValue: '白色',
				img: 'test.jpg'
			},

			{
				productId: 220,
				specId: 2,
				specValue: '黑色',
				img: 'test.jpg'
			}
		];

		params.detailList = [
			{
				productId: 220,
				specValues: '{"1":"1寸", "2":"白色"}',
				stock: 123,
				price: 20.23,
				sale: 1234,
				code: 'SB1234',
				sort: 42
			}, 

			{
				productId: 220,
				specValues: '{"1":"2寸", "2":"白色"}',
				stock: 123,
				price: 20.23,
				sale: 1234,
				code: 'SB1234',
				sort: 42
			}, 

			{
				productId: 220,
				specValues: '{"1":"1寸", "2":"黑色"}',
				stock: 123,
				price: 20.23,
				sale: 1234,
				code: 'SB1234',
				sort: 42
			}, 

			{
				productId: 220,
				specValues: '{"1":"2寸", "2":"黑色"}',
				stock: 123,
				price: 20.23,
				sale: 1234,
				code: 'SB1234',
				sort: 42
			}
		];

		params.product = {
			productId: 220,
			productPrice: 123.23,
			productStock: 1234,
			productCode: 'SB2343',
			productSale: 345,
			productLinkUrl: 'test.url',
			productCostPrice: 2345.34,
			productImages: 'images'
		};

		var str = JSON.stringify(params);


		$.post("${managerPath}/mall/test/index.do?key=123", {jsonStr:str}, function(data, status){
			alert(status);
			alert(data);
		});
	}


</script>

</html>
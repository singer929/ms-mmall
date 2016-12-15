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

		$.post("${managerPath}/mall/test/index.do?key=123", {a:1, b:2}, function(data, status){
			alert(status);
			alert(data);
		});
	}


</script>

</html>
<html>

<head>
	<script type="text/javascript" src="${base}/static/plugins/jquery/1.9.1/jquery-1.9.1.js"></script>
	  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script> 
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" type="text/css"> 
</head>


<body>

<h1>Hello World</h1>

<button onclick="onTest()">Test</button>

<form action="${managerPath}/mall/test/index.do" method="post">
	<input type="hidden" name="a" value="1" />
	<input type="hidden" name="b" value="2" />
	<input type="submit" value="提交" />
</form>

<select id="sel" class="js-example-theme-single">
	<option value="0">0</option>
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
</select>

<input type="button" name="b" value="SEL" onclick="sel()" />


</body>

<script>

	$(function () {
		$('.js-example-theme-single').select2({tags:true}); 
	});

	function sel(){
		$("#sel").val('3');
	}
</script>

</html>
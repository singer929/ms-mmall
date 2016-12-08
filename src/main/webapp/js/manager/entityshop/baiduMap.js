var map = null;
var marker = null;
var pointObj = null
/**
 * 添加百度地图
 * @param {String} id 显示的id名称
 * @param {Point|String} point 显示位置,城市名称或者一个Point对象
 * @param {int} zoom 范围3-19级，若调用高清底图（针对移动端开发）时，zoom可赋值范围为3-18级
 * @param {boolean} flag 是否可以缩放
 */
function setMap(id,point,zoom){
	map = new BMap.Map(id);
	
	if(zoom){
		map.centerAndZoom(point,zoom);
	} else {
		map.centerAndZoom(point);
	}
	
 	map.enableScrollWheelZoom();
	map.enableContinuousZoom(); 
}

function setCenter(point){
	map.setCenter(point);
}

/**
 * 设置地图的事件
 * @param {} point 百度地图Point对象
 * @param {} flag 是否支持点击后触发按钮切换
 */
function addMarker(point,flag){
	marker = new BMap.Marker(point);
 	map.addOverlay(marker);
 	if(flag==true){
 		marker.enableDragging();
		map.addEventListener("click", function(e){
			marker.setPosition(new BMap.Point(e.point.lng,e.point.lat));
		});
 	}
 	return marker;
}

function setMarker(markerObj,point){
	markerObj.setPosition(point);
}

/**
 * 返回地图当前缩放级别。
 * @return {Number} 返回地图当前缩放级别。
 */
function getZoom(){
	return map.getZoom();
}

/**
 * 获取当前坐标对象
 * @return {Point} 百度地理坐标  可以getPoint().lng获取地理经度,getPoint().lat获取地理纬度
 */
function getPoint(){
	if(pointObj){
		pointObj.lng = map.getCenter().lng;
		pointObj.lat = map.getCenter().lat;
	} else {
		pointObj = new BMap.Point(map.getCenter().lng,map.getCenter().lat);
	}
	return pointObj;
}

/**
 * 获取当前坐标对象
 * @return {Point} 百度地理坐标  可以getMarkerPoint().lng获取地理经度,getMarkerPoint().lat获取地理纬度
 */
function getMarkerPoint(){
	return marker.getPosition();
}

// 百度地图API功能
function G(id) {
    return document.getElementById(id);
}

/**
 * 查询地图
 * @param {String} id input框的id
 */
function selectMap(id){
	
	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
	    {"input" : id
	    ,"location" : map
	});
	
	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
	ac.show();
	var str = "";
	    var _value = e.fromitem.value;
	    var value = "";
	    if (e.fromitem.index > -1) {
	        value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
	    }    
	    str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
	    
	    value = "";
	    if (e.toitem.index > -1) {
	        _value = e.toitem.value;
	        value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
	    }    
	    str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
	    G("searchResultPanel").innerHTML = str;
	});
	
	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
	var _value = e.item.value;
	    myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
	    G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
	    
	    setPlace();
	});
	
	$('#'+id).on('focus',function(){
		if($(this).val()!=''){
			ac.show();
		}
	});
	
	function setPlace(){
	    function myFun(){
	        var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
	        map.centerAndZoom(pp, 18);
	        setMarker(marker,pp);    //添加标注
	    }
	    var local = new BMap.LocalSearch(map, { //智能搜索
	      onSearchComplete: myFun
	    });
	    local.search(myValue);
	}
}
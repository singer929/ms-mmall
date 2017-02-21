
$(function(){
	var div = document.createElement("input");
	div.setAttribute("id", "ccstr");
	div.setAttribute("type", "hidden");
	div.setAttribute("value", "0");
	div.setAttribute("title", "");
	document.body.appendChild(div);
	$("a").bind("click",function(){ 
		$("#ccstr").attr("title",$(this).attr('href'));
	    $("#ccstr").val(Number($("#ccstr").val())+1);
	});
});
var ccstart = new Date();
var ccip = returnCitySN["cip"];
var ccip_city = returnCitySN["cname"];
var ccvisit_url = window.location.pathname;
var ccsource_url = document.referrer;

window.onbeforeunload = function() {
	var ccend = new Date();
	var ccnum = $("#ccstr").val();
	var cclink_url = $("#ccstr").attr('title');
 	$.ajax({
	    type: 'POST',
 	    async: false,
	    url: 'http://www.igetmall.net/zhuanti-gatherdata',
	    data: {ip:ccip,citys:ccip_city,st:ccstart.getTime(),et:ccend.getTime(),visit_url:ccvisit_url,s_url:ccsource_url,num:ccnum,link_url:cclink_url}
	});
}  





//index js
function s_hot(k){
	$("#tab_lt_"+k).show();
	$("#tab_rt_"+k).hide();
	$("#ht_tab_"+k).addClass("cur");
	$("#dp_tab_"+k).removeClass("cur");
}
function s_danp(k){
	$("#tab_lt_"+k).hide();
	$("#tab_rt_"+k).show();
	$("#dp_tab_"+k).addClass("cur");
	$("#ht_tab_"+k).removeClass("cur");
}
//header_index搜索
function findpro1(){
     var tj=$("#txt_name").val();
     if(tj!=""){
      if(tj == "羊春节 购洋货，2月7日开启年货活动，快来选购！"){
          window.location.href="/all-product";
      }else{
          window.location.href='/search/'+encodeURIComponent(tj);
      }  
     }else{ 
         window.location.href="/all-product";
     }
}



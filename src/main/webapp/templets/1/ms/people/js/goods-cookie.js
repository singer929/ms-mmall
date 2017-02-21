var goodsList = new Array;	//存放所有商品的数据
var favorList = new Array;	//记录下用户偏爱的栏目
var goodsData;	//接收从页面传过来的商品参数
var curGoodsNum;	//获取当前商品的数量
var saveSize = 9;	//控制存放到cookie的数量为15个
var showSize = 5;	//控制显示的cookie的个数
var j = 0;	//记录当前商品应该插入cookie中的位置，默认为0
var clash = -1;//记录下cookie中商品与本次商品冲突的位置，-1表示默认为没有冲突

/*此函数用于检测当前商品在cookie中是否已经存在,若找到就记下冲突的位置*/
function checkeConflict(goodsNum) {
	for (var i = 0; i < goodsNum; i++) {	//先查找一下是否有重复的商品，有的话记录下冲突的位置
		goodsList[i] = Cookies.getJSON("goods_history")[i];
		if (Cookies.getJSON("goods_history")[i].goodsTitle === goodsData.goodsTitle) {//如果现在这个商品在cookie中已经存在的话，记下这个冲突位置
			clash = i;
			return false;
		}
	}
}

/*保存商品到cookie*/
function setGoodsData(JSON) {
	goodsData = JSON;//获取本次商品数据
	if (Cookies.getJSON("goods_history") != undefined) {//如果cookie中有数据将会执行一下操作,要是没cookie就直接插入本次数据
		curGoodsNum = Cookies.getJSON("goods_history").length;//获取cookie的数据长度
		if (curGoodsNum === saveSize) {	//控制cookie中的数量在15个
			checkeConflict(curGoodsNum);	//检测是否有冲突
			if (clash === -1) {		//clash等于默认值就是没有冲突：那么循环cookieSize - 1次，将cookie中的数据上移一个位置
				for (var i = 0; i < saveSize - 1; i++) {
					goodsList[i] = Cookies.getJSON("goods_history")[i+1];	//将数据上移一个位置
				}
				j = saveSize - 1;	//最新的商品插入到第四个位置
			}
		}
		else {	//总数未超过3
			checkeConflict(curGoodsNum);//检测是否有冲突
			j = curGoodsNum;
		}
	}
	if (clash != -1) {	//商品在cookie中已经存在：这次商品将插入到冲突位置
		goodsList[clash] = goodsData;
	}
	else {	//商品在cookie中不存在：这次商品直接插入到最后一位
		goodsList[j] = goodsData;
	}
	
	Cookies.set('goods_history',goodsList,{path:'/mcms',expires:7,domain:'localhost'});
}

function setFavorData() {

	Cookies.set('goods_favor',favorList,{path:'/mcms',expires:7,domain:'localhost'});
}

/*显示最新的商品信息*/
function showGoodsHistory() {
	var cookieData = Cookies.getJSON("goods_history");
	if (cookieData != undefined) {	//要是cookie里没有数据的话就不用追加了
		var maxIndex = Number(cookieData.length) - 1;//获取cookie中数据的最后一位的索引值
		if (cookieData.length < 5) {
			showSize = cookieData.length;
		}
		for (var i = 0; i < showSize; i++) {//根据showSize来控制倒序显示多少个数据
			var data = cookieData[maxIndex--];
			$("#historyList").tmpl(data).insertBefore("#clear_history");
		}
		$("#clearHistory").show();
	}
	else {
		$("#clearHistory").hide();
	}
}

$(function(){
	$("#clearHistory").click(function(){//晴空浏览记录
		Cookies.remove('goods_history',{path:'/mcms',domain:'localhost'}); //删除所有浏览商品
		$("#history_list ul").remove();
		showGoodsHistory();
	});
})

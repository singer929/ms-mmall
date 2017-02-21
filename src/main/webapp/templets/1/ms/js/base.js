/*
* 相关插件
*/
/* = 下拉 = */
$.fn.drop = function(arg, direction, callback) {

	var _pop = $(arg), direction = direction || "down", callback = callback || $.noop, _this = this, posStyle,
	pos = {
		top: this.offset().top,
		left: this.offset().left
	}, posArray = [this.offset().top, this.offset().left];
	switch (direction) {
		case "left": {
			posStyle = "fixed";
			if(pos.left === posArray[1]) pos.left -= _pop.width();
			pos.top = _this[0].offsetTop;
			break;
		}
		case "right": {
			pos.left += this.width();
			break;
		}
		case "top": {
			pos.top -= _pop[0].offsetHeight;
			break;
		}
		case "down": {
			pos.top += this.height();
			break;
		}
	}
    $('.tab_box').hover(function(){
        $('.nali').removeClass('cur');
        $('#na_'+$(this).attr('t')).addClass('cur');
    },function(){
        $('.nali').removeClass('cur');
    })
	_pop.show().css({
		position: posStyle || "absolute",
		left: pos.left,
		top: pos.top
	});
	$(_this).addClass("cur");

	_pop.hover(function() {

	}, function() {
		$(this).hide();
		$(_this).removeClass("cur");
		//czj
		//$("#nav_list").hide();
		callback();
	});

	_this.hover(function() {

	}, function(e) {
		var toElement = e.toElement || e.relatedTarget,
			show = $(toElement)[0] == _pop[0] || $.inArray(_pop[0], $(toElement).parents("div")) !== -1;
		if(!show) {
			_pop.hide();
			$(_this).removeClass("cur");

		}
	});
}

/* 模拟select */
$.fn.agSelect = function(arg) {
	var target = $(arg), _this = this;
	target.click(function(e) {
		if(e.target.tagName == "A") {
			var tar = e.target, c;
			c = tar.innerHTML;
			tar.innerHTML = _this[0].innerHTML;
			_this[0].innerHTML = c;
		}
	});
}


/* = tab = */
// function Tab(arg) {
// 	return new tab.prototype.init(arg);
// }
// function tab(arg) {}
// tab.prototype = {
// 	constructor: "Tab",
// 	curIndex: 0,
// 	tabs: null,
// 	init: function(arg) {
// 		var _this = this, _this.tabs = $(arg);
// 		_this.list = $(arg).find("li");
// 		for(var i=0,l=_this.list.length; i<l; i++) {
// 			_this.list.eq(i).attr("data-index", i);
// 		}
// 		$(arg).click(function(e) {
// 			var tar = e.target;
// 			if(tar.tagName == "LI") {
// 				_this.list.removeClass("cur");
// 				$(tar).addClass("cur");
// 				this.curIndex = $(tar).attr("data-index");
// 			}
// 		});
// 	},
// 	change: function(arg) {
// 		this.tabs.click(function() {
// 			$(arg).hide();
// 			$(arg).eq(this.curIndex).show();
// 		});
// 	}
// }
// tab.prototype.init.prototype = tab.prototype;
 
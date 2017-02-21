/* = 轮播器 = */

function Stage(obj) {
	return new stage.prototype.init(obj);
}
function stage() {}
stage.prototype = {
	constructor: "stage",
	option: {
		elem: null,
		time: 5000,
		controller: {
			left: "#left",
			right: "#right",
                        point:"#point",
                        banner:"#banner"
		}
	},
	curLeft: 0,
	length: 0,
	init: function(obj) {
		$.extend(this.option, obj);
		this.length = $(this.option.elem).find("li").length;

		var left = $(this.option.controller.left), right = $(this.option.controller.right),point=$(this.option.controller.point),banner=$(this.option.controller.banner),
			_this = this, maxLength = window.screen.width*(this.length-1);
		
			
		left.on("click", function() {
                        _this.curLeft += window.screen.width;
			_this.curLeft = _this.curLeft > 0 ? -maxLength : _this.curLeft;
			_this.set(_this.curLeft);
                        point.removeClass("newig_mccM_dd");
                        point.eq(-_this.curLeft/window.screen.width).addClass("newig_mccM_dd");
			
		});
		right.on("click", function() {
                        _this.curLeft -= window.screen.width;
			_this.curLeft = _this.curLeft < -maxLength ? 0 : _this.curLeft;
			_this.set(_this.curLeft);
                        point.removeClass("newig_mccM_dd");
                        point.eq(-_this.curLeft/window.screen.width).addClass("newig_mccM_dd");	
		});
                point.hover(function(){
//                    alert(_this.curLeft/window.screen.width);
                  $(this).addClass("newig_mccM_dd");
                  $(this).siblings().removeClass("newig_mccM_dd");
                  _this.curLeft =  -$(this).index()*window.screen.width;
                  _this.set(_this.curLeft);
                });

                var timeout = setInterval(function() {
			_this.curLeft -= window.screen.width;
			_this.curLeft = _this.curLeft < -maxLength ? 0 : _this.curLeft;
			_this.set(_this.curLeft);
                        point.removeClass("newig_mccM_dd");
                        point.eq(-_this.curLeft/window.screen.width).addClass("newig_mccM_dd");
		}, this.option.time);
                banner.hover(function(){
                    clearInterval(timeout);
                },function(){
                  timeout = _this.start(_this);
                });
	},
	set: function(left) {
		var _ul = $(this.option.elem);
		_ul.animate({
			left: left + "px"
		},0);
	},
        start:function(obj){
            $.extend(this.option, obj);
		this.length = $(this.option.elem).find("li").length;

		var left = $(this.option.controller.left), right = $(this.option.controller.right),point=$(this.option.controller.point),banner=$(this.option.controller.banner),
			_this = this, maxLength = window.screen.width*(this.length-1);
         var time =  setInterval(function() {
			_this.curLeft -= window.screen.width;
			_this.curLeft = _this.curLeft < -maxLength ? 0 : _this.curLeft;
			_this.set(_this.curLeft);
                        point.removeClass("newig_mccM_dd");
                        point.eq(-_this.curLeft/window.screen.width).addClass("newig_mccM_dd");
		}, this.option.time); 
                return time;
        }
}
stage.prototype.init.prototype = stage.prototype;
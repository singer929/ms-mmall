//点击页面其他位置头部菜单收缩
$(document).bind("click",function(e){
    var target = $(e.target);
    if(target.closest(".menu-default").length == 0){
        manager.topMenu.initTop();
    }
})
$(function(){
    //收缩左侧菜单
    $(".slideMenu").click(function(){
        var obj = $(this);
        if ($(".ms-menu").css("display") == "block") {
            //修改图标
            obj.children(".icon-open").show();
            obj.children(".icon-close").hide();
            $(".ms-menu,.ms-menu-div").hide()
            //改变右侧部分宽度
            $('.easyui-tabs').tabs("resize",{
                width:$('.easyui-tabs').parent().width()
            });
        } else {
            //修改图标
            obj.children(".icon-open").hide();
            obj.children(".icon-close").show();
            $(".ms-menu,.ms-menu-div").show()

            //恢复右侧部分宽度
            $('.easyui-tabs').tabs("resize",{
                width:$('.easyui-tabs').parent().width(),
                fit:true,
                scrollDuration:1000
            });
        }
    })

    //点击左侧菜单添加选项卡
    $(".ms-menu").delegate(".ms-menu-child li a","click",function(){
        $(".easyui-tabs").show();
        $(".wellcome").hide();
        $(".ms-menu-child a").removeClass("active");
        $(this).addClass("active");
        var title = $(this).data("title");
        var content = $(this).data("url");
        if (!$('.easyui-tabs').tabs('exists', title)) {
            $('.easyui-tabs').tabs('add', {
                title: title,
                content: '<iframe src=' + content + ' frameborder="0" height="100%" width="100%" id="mainFrame" name="mainFrame"></iframe>',
                closable: true,
            });

            
        } else {
            $('.easyui-tabs').tabs('select', title);
        }
    })

    //用户在切换选项卡时，和导航树保持同步
    $('.easyui-tabs').tabs({
        onSelect: function(title){
            var _select = $(".easyui-tabs").tabs("getSelected");
            var obj = _select.panel("options").tab;
            //循环左侧菜单里每个菜单的text进行选项卡的title进行对比
            $(".ms-menu").find("li").each(function(){
                var target = $(this);
                if(target.text() == obj.text()){
                    $(".ms-menu-child a").removeClass("active");
                    //左侧对应菜单展开选中
                    target.parent().parent().slideDown();
                    target.parent().slideDown();
                    target.find("a").addClass("active");
                    //头部对应一级菜单进行选中
                    $(".ms-menu-list").find("li").removeClass("active").each(function(){
                        if(target.parent().parent().data("model-id") == $(this).data("model-id")){
                            $(this).addClass("active");
                        }
                    })
                }
            })
        }
    });

    //当关闭最后一个选项卡时，隐藏选项卡页面显示主界面
    $('.easyui-tabs').tabs({
        onClose:function(){
            if($('.easyui-tabs').tabs('tabs').length==0){
                $(".wellcome").show();
                $(".easyui-tabs").hide();
                $(".ms-menu-child a").removeClass("active");
                $(".ms-menu-list").find("li").removeClass("active")
            }
            
        }
    })    

    //点击进入MStore
    $(".ms-top-mstore").click(function(){
        $(".wellcome").hide();
        $(".easyui-tabs").show();
        var title = "MStore";
        if (!$('.easyui-tabs').tabs('exists', title)) {
            $('.easyui-tabs').tabs('add', {
                title: title,
                content: '<iframe src="mstore.htm" frameborder="0" height="100%" width="100%" id="mainFrame" name="mainFrame"></iframe>',
                closable: true,
            });
        } else {
            $('.easyui-tabs').tabs('select', title);
        }
    })
    
})

//MStore做的计时循环特效
window.setInterval(showMstore, 1000); 
function showMstore(){ 
    $(".ms-top-mstore").find(".animated").addClass("rubberBand")
} 
window.setInterval(hiddenMstore, 1500); 
function hiddenMstore(){ 
    $(".ms-top-mstore").find(".animated").removeClass("rubberBand")
}   

var manager = {
    /*头部菜单操作*/
    topMenu:{
        initEvent: function() {
            $("*[data-ms-*]").each()
        },
        /*追加头部菜单*/
        initMenu: function(json) {
            $("#ms-menu-list-tmpl").tmpl(json).appendTo(".ms-menu-list");
            if($(".ms-menu-list").children().length>5){
                $(".openMenu").show();
            }
            //将左侧菜单追加，只是隐藏了
            $("#ms-menu-tmpl").tmpl(json).appendTo(".ms-menu");
            $(".ms-menu-parent").each(function(n) {
                var arr = new Array;
                for (i = 0; i < json.length; i++)  json[i].modelModelId == $(this).data("model-id") && arr.push(json[i])
                        //alert(arr)
                $("#ms-menu-child-tmpl").tmpl(arr).appendTo($(this).find("ul:first"))
                
            })
        },

        /*头部菜单点击收缩效果*/
        topMenuOpen: function(target,menuShow){
            var _height=$(".ms-menu-list").height();
            if(target.parent().hasClass(menuShow)){
                this.initTop();
            }else{
                target.parent().addClass(menuShow);
                $('.'+menuShow).height(_height);
            }
        },
        /*初始化头部菜单*/
        initTop:function(){
            $(".menu-default").height("50px");
            $(".menu-default").removeClass("menu-show");
        },
        /*点击头部菜单展示二级菜单*/
        showChildMenu:function(target,json){
            var _json = {"modelTitle":target.text(),"modelIcon":target.data("model-icon"),"modelId":target.data("model-id")};
            var arr = new Array;
            $(".ms-menu-list").find("li").removeClass("active");
            target.addClass("active");

            //显示左侧菜单
            if ($(".ms-menu").css("display") == "none") {
                $(".slideMenu").children(".icon-open").hide();
                $(".slideMenu").children(".icon-close").show();
                $(".ms-menu,.ms-menu-div").show();
                //恢复右侧部分宽度
                $('.easyui-tabs').tabs("resize",{
                    width:$('.easyui-tabs').parent().width(),
                    fit:true,
                    scrollDuration:1000
                });
            }

            $(".ms-menu-parent").each(function(){
                if(target.data("model-id")==$(this).data("model-id")){
                    $(this).show();
                    $(".ms-menu-none").hide();
                    $(".ms-menu-parent").find("ul").slideUp();
                    $(this).find("ul").slideDown();
                }
            })
            this.initTop();
        },

    },
        
    /*左侧菜单操作*/
    leftMenu:{
        /*左侧菜单点击收缩效果*/
        leftMenuOpen:function(target,menu){
            menu.slideToggle();

        },

       
        /*移除左侧菜单*/

    },

        
}
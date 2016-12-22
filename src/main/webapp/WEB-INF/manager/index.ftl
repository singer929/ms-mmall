<!DOCTYPE html>
<html lang="zh">
<head>
<title><#if app?has_content>${app.basicTitle}<#else>MS</#if>管理系统</title>
<link rel="bookmark" href="favicon.ico"/> 
<#include "${managerViewPath}/include/macro.ftl"/>
<#include "${managerViewPath}/include/meta.ftl"/>	
</head>
<script type="text/javascript">
    $(function(){
        var menuJson=${modelList};

        $('.dropdown-toggle').dropdown();

        //加载头部菜单
        manager.topMenu.initMenu(menuJson);

        //当头部菜单超过5个时，点击展开头部菜单
        $(".openMenu").click(function(){
            manager.topMenu.topMenuOpen($(this),"menu-show");
        })
        //点击头部菜单从左侧显示当前菜单子菜单
        $(".ms-menu-list").delegate(".ms-menu-detail","click",function(){
            manager.topMenu.showChildMenu($(this),menuJson);
        })
        //点击展开左侧菜单子菜单
        $(".ms-menu").delegate(".ms-menu-parent-title","click",function(){
            var menu = $(this).parent().siblings(".ms-menu-child");
            manager.leftMenu.leftMenuOpen($(this),menu);
        })

        //移除左侧菜单
        $(".ms-menu").delegate(".ms-menu-parent","mouseover",function(){
            $(".closeMenu").hide();
            $(this).find(".closeMenu").show();
        })
        $(document).bind("mouseover",function(e){
            var target = $(e.target);
            if(target.closest(".ms-menu-parent").length == 0){
                $(".closeMenu").hide();
            }
        })
        //关闭左侧菜单
        $(".ms-menu").delegate(".closeMenu","click",function(){
            $(this).parent().parent().hide();
            var index=0;
            $(".ms-menu-parent").each(function(){
                var target = $(this)
                $(".ms-menu-list li").each(function(){
                    if(target.data("model-id")==$(this).data("model-id")){
                        $(this).removeClass("active");
                    }
                })
                    
                if($(this).css("display") == "none"){
                    index++;
                    if(index == $(".ms-menu-parent").length){
                        $(".ms-menu-list li").removeClass("active");
                        $(".ms-menu-none").show();
                    }
                }
            })
        })
        
    })
</script>

<body class="over-hide" style="height: 100%;background: #3497db">
    <!--顶部开始 -->
    <div class="ms-top">
        <!--头部LOGO-->
        <div class="ms-top-logo">
            <img src="${skin_manager_logo}"/>
            <span class="slideMenu">
                <span class="icon iconfont icon-open">&#xe823;</span>
                <span class="icon iconfont icon-close" style="display: none;">&#xe80d;</span>
            </span>
        </div>
        
        <!--头部一级菜单-->
        <div class="ms-top-menu">
            <div class="menu-default">
            	<#noparse>
                <script id="ms-menu-list-tmpl" type="text/x-jquery-tmpl">
                {{if modelModelId==0}}
                    <li data-model-id="${modelId}" data-model-icon="${modelIcon}" class="ms-menu-detail">${modelTitle}</li>
                {{/if}}
                </script>
                </#noparse>
                <ul class="ms-menu-list">

                </ul>
                <span class="glyphicon glyphicon-menu-hamburger openMenu"></span>
            </div>
        </div>
        <!--头部用户信息/进入MStore-->
        <div style="float:right;">
            <div class="dropdown" style="float:left;">
                <div id="dLabel" class="ms-top-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="glyphicon glyphicon-user"></span>
                    msopen
                    <span class="caret"></span>
                </div>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                    <li role="presentation" data-toggle="modal" data-target="#myModal"><a role="menuitem" tabindex="-1"><span class="glyphicon glyphicon-cog"></span> 修改密码</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1"><span class="glyphicon glyphicon-off"></span> 退出</a></a></li>
                </ul>
            </div>

            <div class="ms-top-mstore" data-toggle="tooltip" data-placement="bottom" title="点击进入Mstore">
                <span class="glyphicon glyphicon-th-large animated"></span>
                <span class="mstore-update">6</span>
            </div>
        </div>
    </div>

	<#noparse>
    <script id="ms-menu-tmpl" type="text/x-jquery-tmpl">
    {{if modelModelId==0}}
        <div class="ms-menu-parent" data-model-id="${modelId}">
            <div class="ms-menu-parent-header ms-menu-parent-active">
                <div class="ms-menu-parent-title" data-flag="true">
                    <i class="icon iconfont icon-logo">${modelIcon}</i>
                    ${modelTitle}    
                </div>
                <span class="closeMenu">×</span>
            </div>
            <ul class="ms-menu-child child-list${modelId}" id="tab-tools">
            </ul>
        </div>
    {{/if}}
    </script>
    <script id="ms-menu-child-tmpl" type="text/x-jquery-tmpl">
        <li><a href="javascript:void(0)" data-title='${modelTitle}' data-url='${modelUrl}'><!--span class="caret"></span-->${modelTitle}</a></li>
    </script>
    </#noparse>
    
    <!--左边菜单开始-->
    <div class="ms-menu">
        <div class="ms-menu-none"></div>
        <!--span class="slideMenu glyphicon glyphicon-tasks"></span-->
    </div>
    <div class="ms-menu-div"></div>
    <!--左边菜单结束-->

    <!--右边开始-->
    <div class="ms-content">
        <div class="wellcome" style="text-align: center;color: #555;background: #FCFCFC;height: 300px;padding-top: 200px;vertical-align: middle;border-top-left-radius:5px;height: 100%;">
            <h2>欢迎进入MS系统</h2>
            <p>欢迎加群： 221335098 或到
                <a href="http://ms.mingsoft.net/mbbs/main.do" target="_blank" style="text-decoration: none;">官方论坛进行技术交流</a>
            </p>
        </div>
        <div class="easyui-tabs">
        
        </div>
        
    </div>
    <!--右边结束-->
    <!-- 弹框 -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="margin-top: 15%">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">修改密码</h4>
                </div>
                <div class="modal-body">
                    <form role="form" method="post" action="/m-admin/updatePassword.do" target="_self" id="updatePasswordFrom" name="updatePasswordFrom" class="form-horizontal bv-form" style="width: 100%; background-color: white;" novalidate="novalidate"><button type="submit" class="bv-hidden-submit" style="display: none; width: 0px; height: 0px;"></button>
                        <input type="hidden" name="redirect">
                        <div class="form-group ms-form-group has-feedback"> 
                            <label for="managerName" class="col-sm-3  control-label ">
                            账号:
                            </label>
                            <div class="col-sm-8 ms-from-group-input ms-form-input">       
                                <input type="text" readonly="readonly" name="managerName" class="form-control" title="managerName">
                            </div>
                        </div>
                        <div class="form-group ms-form-group has-feedback"> 
                            <label for="oldManagerPassword" class="col-sm-3  control-label ">
                            旧密码:
                            </label>
                            <div class="ms-form-control ms-from-group-input col-sm-8">      
                                <input type="password" autocomplete="off" name="oldManagerPassword" class="form-control" title="managerPassword">
                            </div>
                        </div>
                        <div class="form-group ms-form-group has-feedback"> 
                            <label for="newManagerPassword" class="col-sm-3  control-label ">
                            新密码:
                            </label>
                            <div class="ms-form-control ms-from-group-input col-sm-8">      
                                <input type="password" autocomplete="off" name="newManagerPassword" class="form-control" title="managerPassword">
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-success">保存</button>
                </div>
            </div>
        </div>
        
        
    </div>

</body>
</html>
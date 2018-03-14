<#include "base.ftl"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>水印照片后台</title>
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${context}/favicon.png">
    <link rel="stylesheet" href="${context}/css/vendor/bootstrap/dist/bootstrap.css">
    <link rel="stylesheet" href="${context}/css/vendor/Animate.css">
    <link rel="stylesheet" href="${context}/css/basic.css">
    <link rel="stylesheet" href="${context}/css/vendor/font_awesome/css/font-awesome.css">
    <script>
        window.common_conf = {
            defaultHash: 'desktop',
            baseURL: './',
            navJSON: '${context}/nav'
        };

        function logout() {
            /*清除jwt*/
            deleteCookie("Authorization");
            /*清除sessionId*/
            deleteCookie("JSESSIONID");
            location.href = "${contextPath}/logout";
            return false;
        }
    </script>
</head>

<body>
<aside id="left_panel">
    <div class="login_info">
            <span>
                <div>
                    <a href="#" data-toggle="dropdown">
                        <i class="fa fa-user"></i><span class="name">admin</span><span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a tabindex="-1" href="#">个人资料</a></li>
                        <li><a tabindex="-1" href="#" onclick="return logout()">退出</a></li>
                    </ul>
                </div>
            </span>
    </div>
    <script id="nav_tpl" type="text/html">
        <ul>
            <#list nav as item>
                <li><a href="#${item.url!}"><i class="fa fa-lg fa-fw fa-file-text"></i><span>${item.title}</span>
                    <#if item.child?size !=0><b><i class="fa fa-plus-square-o"></i></b></#if></a>
                <#if item.child?size !=0>
                    <ul>
                <#list item.child as item2>
                    <li><a href="#${item2.url!}"><i class="fa fa-fw fa-file"></i><span>${item2.title}</span>
                        <#if item2.child?size !=0><b><i class="fa fa-plus-square-o"></i></b></#if></a>
                        <#if item2.child?size !=0>
                        <ul>
                            <#list item2.child as item3>
                                <li><a href="#/${item3.url!}"><i
                                        class="fa fa-fw fa-file"></i><span>${item3.title}</span></a></li>
                            </#list>
                        </ul>
                        </#if>
                    </li>
                </#list>
                    </ul>
                </#if>
                </li>
            </#list>
        </ul>
    </script>
    <nav></nav>
    <span class="minifyBtn"><i class="fa fa-arrow-circle-left"></i></span>
</aside>
<div id="main">
    <div id="ribbon">
        <ol class="breadcrumb"></ol>
    </div>
    <div id="content"></div>
</div>
<footer id="page_footer">
    <div class="inside"><i class="fa fa-copyright"></i><a href="https://www.baidu.com">水印照片2017-2018</a></div>
</footer>

<!--Common Modal -->
<div class="modal fade" id="modal_ajax_content" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content"></div>
    </div>
</div>
<div class="modal fade" id="modal_confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body"></div>
            <div class="modal-footer" style="text-align:center;">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger J_confirm_btn"><i class="fa fa-refresh fa-spin"></i> 确定
                </button>
            </div>
        </div>
    </div>
</div>

<script src="${context}/js/vendor/jquery.min.js"></script>
<script src="${context}/js/vendor/bootstrap/dist/bootstrap.js"></script>
<script src="${context}/js/vendor/catpl.js"></script>
<script src="${context}/js/vendor/ie10-viewport-bug-workaround.js"></script>
<script src="${context}/js/vendor/DataTables/js/jquery.dataTables.min.js"></script>
<script src="${context}/js/vendor/DataTables/js/dataTables.bootstrap.min.js"></script>
<script src="${context}/js/ajaxForm.js"></script>
<script src="${context}/js/basic.js"></script>
<script src="${context}/js/refuel.js"></script>
<script>
    $(function () {
        var str=getCookie(CommonStr.USER_INFO);
        console.log(str);
        if(str!=='me'){
            console.log(str);
            location.href = "login";
        }
    })
</script>
</body>
</html>
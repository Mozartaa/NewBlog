<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/amazeui.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/pageStyle.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
</head>
<body>

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">管理员列表</strong><small></small></div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 添加管理员</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="goods_list" id="account_List">
    <ul class="title_ul">
        <li>序号</li>
        <li>用户</li>
        <li>修改密码</li>
        <li>移除管理</li>
    </ul>
    <s:iterator value="Userlist" var="user">
        <ul class="list_goods_ul">
            <li><s:property value="#user.id"/> </li>
            <li><s:property value="#user.username"/></li>
            <li><a href="#" class="update"><img class="img_icon" src="${pageContext.request.contextPath}/images/edit_icon.png" alt=""></a></li>
            <li><a href="${pageContext.request.contextPath}/loginAction_delete?id=<s:property value="#user.id"/>"><img class="img_icon" src="${pageContext.request.contextPath}/images/delete_icon.png" alt=""></a></li>
        </ul>
    </s:iterator>
</div>
    <div id="modal_view">


    </div>

<div id="modal_content_account">
    <div id="close"><img src="${pageContext.request.contextPath}/images/delete_icon.png" alt=""></div>
    <div class="edit_content">

        <div class="item1">
            <div>
                <span>添加管理员：</span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>用户名：</span>
                <input type="text" class="am-form-field" id="username">&nbsp;&nbsp;
            </div>

        </div>
        <div class="item1">
            <div>
                <span>密码：</span>
                <input type="text" class="am-form-field" id="password">&nbsp;&nbsp;
            </div>

        </div>
        <div class="item1">
            <button class="am-btn am-btn-default" type="button" id="user_add">添加</button>
        </div>

    </div>
</div>

<script>
    $(function () {
        $('#add').click(function () {
            $("#modal_view").fadeIn();
            $("#modal_content_account").fadeIn();
        });

        $("#close").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content_account").fadeOut();
        });

        $(".update").click(function () {
            $("#modal_view").fadeIn();
            $("#modal_content_account2").fadeIn();
        });

        $("#close2").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content_account2").fadeOut();
        });




        /* 监听添加按钮点击 */
        $("#user_add").click(function () {
                /* 获取文本框的内容 */
                var username=$("#username").val();
                var password=$("#password").val();
                $(window).attr('location','${pageContext.request.contextPath}/loginAction_add.action?username='+username+'&password='+password);
            })

    });
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/amazeui.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/pageStyle.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <style>
        #modal_content2{
            padding: 30px 20px;
            width: 400px;
            height: 200px;
            background: white;
            position: fixed;
            left: 50%;
            top: 50%;
            margin-left: -200px;
            margin-top: -100px;
            display: none;
        }
        #close2{
            position: absolute;
            right: 10px;
            top: 10px;
        }
    </style>
</head>
<body>


<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">分类管理</strong><small></small></div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 添加分类</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="goods_list" id="account_List">
    <ul class="title_ul">
        <li>parentid</li>
        <li>分类名称</li>
        <li>修改分类</li>
        <li>删除分类</li>
    </ul>
    <%--取数据--%>
    <s:iterator value="categorylist" var="category">
        <ul class="list_goods_ul">
            <li><s:property value="#category.parentid"/> </li>
            <li><s:property value="#category.cname"/> </li>
            <li>
                <a href="#" class="updatebtn" data-id="<s:property value="#category.cid"/>">
                    <img class="img_icon" src="${pageContext.request.contextPath}/images/edit_icon.png" alt="">
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/category_delete.action?cid=<s:property value="#category.cid"/>">
                    <img class="img_icon" src="${pageContext.request.contextPath}/images/delete_icon.png" alt="">
                </a>
            </li>
        </ul>
    </s:iterator>


</div>

<div id="modal_view">


</div>

<div id="modal_content">
    <div id="close"><img src="${pageContext.request.contextPath}/images/delete_icon.png" alt=""></div>
    <div class="edit_content">

        <div class="item1">
            <div>
                <span>添加分类：</span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>parentid:</span>
                <input type="text" class="am-form-field" id="parentid">&nbsp;&nbsp;
                <br>
                <span>分类名称：</span>
                <input type="text" class="am-form-field" id="cname">&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <button class="am-btn am-btn-default" type="button" id="addcategory">添加</button>
        </div>
        
    </div>
</div>
<div id="modal_content2">
    <div id="close2"><img src="${pageContext.request.contextPath}/images/delete_icon.png" alt=""></div>
    <div class="edit_content">

        <div class="item1">
            <div>
                <span>添加分类：</span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>parentid:</span>
                <input type="text" class="am-form-field" id="parentid2">&nbsp;&nbsp;
                <br>
                <span>分类名称:</span>
                <input type="text" class="am-form-field" id="cname2">&nbsp;&nbsp;
                <input type="hidden" id="cid2"/>
            </div>
        </div>
        <div class="item1">
            <button class="am-btn am-btn-default" type="button" id="updatecategory">添加</button>
        </div>

    </div>
</div>
<script>
    $(function () {
        $('#add').click(function () {
            $("#modal_view").fadeIn();
            $("#modal_content").fadeIn();
        });

        $("#close").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content").fadeOut();
        });

        /* 监听添加按钮点击 */
        $("#addcategory").click(function () {
            /* 获取文本框的内容 */
            var parentid=$("#parentid").val();
            var cname=$("#cname").val();
            $(window).attr('location','${pageContext.request.contextPath}/category_add.action?parentid='+parentid+'&cname='+cname);
        })
        /* 监听修改按钮 */
        $(".updatebtn").click(function () {
            /* 判断取出的是哪一条数据 */
            var cid=$(this).data("id");
            $.post("${pageContext.request.contextPath}/category_updateUI.action",{"cid":cid},function (data) {
                console.log(data);
                $("#parentid2").val(data[0].parentid);
                $("#cname2").val(data[0].cname);
                $("#cid2").val(data[0].cid);
            },"json");
            /* 弹出修改界面 */
            $("#modal_view").fadeIn();
            $("#modal_content2").fadeIn();
        })
        $("#close2").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content2").fadeOut();
        });

        $("#updatecategory").click(function () {
            /* 获取文本框内容 */
            var parentid=$("#parentid2").val();
            var cname=$("#cname2").val();
            var cid=$("#cid2").val();
            /* 发送请求*/
            $(window).attr('location','${pageContext.request.contextPath}/category_update.action?parentid='+parentid+'&cname='+cname+'&cid='+cid);
        })
    });
</script>
</body>
</html>
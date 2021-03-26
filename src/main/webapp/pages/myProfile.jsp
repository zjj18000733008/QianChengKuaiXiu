<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>虔诚快修后台管理</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">

    <!-- Tell the browser to be responsive to screen width -->
    <meta
            content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
            name="viewport">


    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/morris/morris.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.theme.default.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/select2/select2.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.skinNice.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-slider/slider.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
</head>

<body class="hold-transition skin-purple sidebar-mini">

<div class="wrapper">

    <!-- 页面头部 -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- 页面头部 /-->
    <!-- 导航侧栏 -->
    <jsp:include page="aside.jsp"></jsp:include>
    <!-- 导航侧栏 /-->

    <!-- 内容区域 -->
    <div class="content-wrapper">


        <!-- 内容头部 -->
        <section class="content-header">
            <h1>
                用户管理 <small>个人信息</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/pages/main.jsp"><i
                        class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="${pageContext.request.contextPath}/product/findAll.do">用户管理</a></li>
                <li class="active">个人信息</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <form id="form_" action="${pageContext.request.contextPath}/product/add"
              method="post">
            <!-- 正文区域 -->
            <section class="content"> <!--商品信息-->
                <div class="panel panel-default">
                    <div class="panel-heading">个人信息</div>
                    <div class="row data-type">
                        <div class="col-md-2 title">账号</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" name="username"
                                   placeholder="账号"  value="${myself.username}">
                        </div>
                        <div class="col-md-2 title">真实名称</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" name="realName"
                                   placeholder="真实姓名" value="${myself.realName}">
                        </div>
                        <div class="col-md-2 title">电话号码</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" name="phone"
                                   placeholder="电话号码" value="${myself.phone}">
                        </div>

                        <div class="col-md-2 title">拥有角色</div>
                        <div class="col-md-4 data">
                            <select id="producttype" class="form-control select2" style="width: 100%"
                                    name="typeId">
                                <!--<option value="0" selected="selected">关闭</option>-->
                                <!--<option value="1">开启</option>-->
                                <c:forEach items="${myself.authorities}" var="i">
                                    <option>${i.authority.toString()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-2 title">头像</div>
                        <div class="col-md-2 ">
                            <!--模态窗口-->
                            <div class="tab-pane" id="tab-model">
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#myModal" onclick="openMyModal(0);return ;">
                                    点击修改
                                </button>

                                <div id="myModal0" class="modal modal-primary" role="dialog">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span></button>
                                                <h4 class="modal-title">将图片url粘贴至此处</h4>
                                            </div>
                                            <div class="modal-body">
                                                <div class="box-body">
                                                    <div class="form-horizontal">
                                                        <div class="form-group">
                                                            <label class="col-sm-2 control-label">url:</label>
                                                            <div class="col-sm-5">
                                                                <input id="inputurl0" type="text" class="form-control"
                                                                       placeholder="" name="profileImg" value="${myself.profileImg}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-outline" data-dismiss="modal" onclick="closeModal(0)">关闭
                                                </button>
                                                <button type="button" class="btn btn-outline" data-dismiss="modal"
                                                        onclick="showImg(0);closeModal(0)">保存
                                                </button>
                                            </div>
                                        </div>
                                        <!-- /.modal-content -->
                                    </div>

                                    <!-- /.modal-dialog -->
                                </div>
                                <!-- /.modal -->
                            </div>
                            <!--模态窗口/-->
                            <div id="imgholder0">
                                <img width="100px" height="100px" src="${myself.profileImg}"/>
                            </div>
                        </div>

                    </div>
                </div>


            </section>
            <!-- 正文区域 /-->
        </form>
        <!--订单信息/--> <!--工具栏-->
        <div class="box-tools text-center">

            <button type="submit" class="btn bg-maroon" onclick="send();return false;">确认修改</button>
            <button type="button" class="btn bg-gray"
                    onclick="history.back(-1);">返回
            </button>
        </div>
        <!--工具栏/-->
    </div>
    <!-- 内容区域 /-->

    <!-- 底部导航 -->
<%--    <footer class="main-footer">--%>
<%--        <div class="pull-right hidden-xs">--%>
<%--            <b>Version</b> 1.0.8--%>
<%--        </div>--%>
<%--        <strong>Copyright &copy; 2014-2017 <a--%>
<%--                href="http://www.itcast.cn">研究院研发部</a>.--%>
<%--        </strong> All rights reserved.--%>
<%--    </footer>--%>
    <!-- 底部导航 /-->

</div>


<script
        src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js"></script>
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/raphael/raphael-min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/morris/morris.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/sparkline/jquery.sparkline.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/knob/jquery.knob.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/adminLTE/js/app.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/markdown.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/to-markdown.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/chartjs/Chart.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.resize.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.pie.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.categories.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-slider/bootstrap-slider.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>

<script>


    $(document).ready(function () {
        // 选择框
        $(".select2").select2();

        // WYSIHTML5编辑器
        $(".textarea").wysihtml5({
            locale: 'zh-CN'
        });
    });

    // 设置激活菜单
    function setSidebarActive(tagUri) {
        var liObj = $("#" + tagUri);
        if (liObj.length > 0) {
            liObj.parent().parent().addClass("active");
            liObj.addClass("active");
        }
    }

    $(document).ready(function () {
        $('#datepicker-a3').datetimepicker({
            format: "yyyy-mm-dd hh:ii",
            autoclose: true,
            todayBtn: true,
            language: "zh-CN"
        });
    });

    $(document).ready(function () {
        // 激活导航位置
        setSidebarActive("order-manage");
        $("#datepicker-a3").datetimepicker({
            format: "yyyy-mm-dd hh:ii",

        });

    });

    index = 0;

    function openMyModal(i) {
        var myModal = $("#myModal" + i);
        myModal.addClass("in");
        myModal.css("display", "block");
    }

    function closeModal(i) {
        var myModal = $("#myModal" + i);
        myModal.removeClass("in");
        myModal.css("display", "none");
    }


    function showImg(i) {
        var url = $("#inputurl" + i).val();
        var imgholder = $("#imgholder" + i);
        imgholder.html(
            "<img width=\"100px\" height=\"100px\"  src=\"" + url + "\"/>"
        )
    }

    function send(){
        $.ajax({
            url: '${pageContext.request.contextPath}/staff/update',
            data:$("#form_").serialize(),
            success:function(e){
                alert(e);
                window.location.reload();
            },
            error:function(e){
                alert("出现错误!");
            }
        })
    }
</script>
</body>

</html>
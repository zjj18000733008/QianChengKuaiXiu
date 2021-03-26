<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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

<body class="hold-transition skin-blue sidebar-mini">

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
                商户管理 <small>全部商户</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/index.jsp"><i
                        class="fa fa-dashboard"></i> 首页</a></li>
                <li><a
                        href="${pageContext.request.contextPath}/">商户管理</a></li>

                <li class="active">全部商户</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <!-- 正文区域 -->
        <section class="content"> <!-- .box-body -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">列表</h3>
                </div>

                <div class="box-body">

                    <!-- 数据表格 -->
                    <div class="table-box">

                        <!--工具栏-->
                        <div class="pull-left">
                            <div class="form-group form-inline">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" title="新建"
                                            onclick='window.location="${pageContext.request.contextPath}/pages/merchant-add.jsp"'>
                                        <i class="fa fa-file-o"></i> 新建
                                    </button>
                                    <button type="button" class="btn btn-default" title="刷新"
                                            onclick="window.location.reload();">
                                        <i class="fa fa-refresh"></i> 刷新
                                    </button>
<%--                                    <button type="button" class="btn btn-default" title="删除"--%>
<%--                                            onclick="removeProduct()">--%>
<%--                                        <i class="fa fa-remove"></i> 删除--%>
<%--                                    </button>--%>
                                    <button type="button" class="btn btn-default" title="删除"
                                            onclick="deleteInBatch()">
                                        <i class="fa fa-remove"></i> 删除
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="box-tools pull-right">
                            <div class="has-feedback">
                                <input type="text" class="form-control input-sm"
                                       placeholder="搜索"> <span
                                    class="glyphicon glyphicon-search form-control-feedback"></span>
                            </div>
                        </div>
                        <!--工具栏/-->

                        <!--数据列表-->
                        <table id="dataList"
                               class="table table-bordered table-striped table-hover dataTable">
                            <thead>
                            <tr>
                                <th class="" style="padding-right: 0px;"><input
                                        id="selall" type="checkbox" class="icheckbox_square-blue">
                                </th>
                                <th class="sorting_asc">ID</th>
                                <th class="sorting_asc">商户图标</th>
                                <th class="sorting">图标地址</th>
                                <th class="sorting_asc">商户名称</th>
<%--                                <th class="">所有者</th>--%>
                                <th class="sorting">联系方式</th>
                                <th class="sorting">经度</th>
                                <th class="sorting">纬度</th>

                                <th class="sorting">商户地址</th>
                                <th class="sorting">URL</th>


                                <th class="text-center">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${merchants}" var="merchant">
                                <tr>
                                    <td><input name="id"style="margin-top:30px" type="checkbox" value="${merchant.id}"></td>
                                    <td>
                                       ${merchant.id}
                                    </td>
                                    <td style="display: none">
                                    <input name="id" style="margin-top:30px;display: none" value="${merchant.id}"/>
                                    </td>
                                    <td>
                                        <img src="${merchant.img}" width="220px" height="100px"/>
                                    </td>
                                    <td>
                                        <input name="img" type="text" style="margin-top:30px"class="form-control" disabled="disabled" value="${merchant.img}"/>
                                    </td>
                                    <td>
                                        <input class="form-control" style="margin-top:30px" disabled="disabled" name="name" type="text" value="${merchant.name}">
                                    </td>
                                    <td>
                                        <input type="text" name="phone"style="margin-top:30px" class="form-control" disabled    value="${merchant.phone}"/>
                                    </td>
                                    <td>
                                        <input type="text"  name="longitude"style="margin-top:30px" class="form-control" disabled="disabled" value="${merchant.longitude}"/>
                                    </td>
                                    <td>
                                        <input name="latitude" type="text"style="margin-top:30px"  class="form-control" disabled="disabled"  value="${merchant.latitude}"/>
                                    </td>
                                    <td>
                                        <input name="address" type="text" style="margin-top:30px" class="form-control" disabled="disabled"  value="${merchant.address}"/>
                                    </td>
                                    <td>
                                        <input name="url" type="text" style="margin-top:30px" class="form-control" disabled="disabled"  value="${merchant.url}"/>
                                    </td>


                                    <td class="text-center">
                                        <button type="button" class="btn bg-olive btn-xs"style="margin-top:30px"
                                                onclick='modify(this)'>修改
                                        </button>
                                        <button style="display: none;margin-top:30px" type="button" class="btn bg-fuchsia btn-xs"
                                                onclick='send(this)'>确认
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>

                        </table>
                        <!--数据列表/-->

                        <!--工具栏-->
                        <%--						<div class="pull-left">--%>
                        <%--							<div class="form-group form-inline">--%>
                        <%--								<div class="btn-group">--%>
                        <%--									<button type="button" class="btn btn-default" title="新建"--%>
                        <%--										onclick='location.href="all-order-manage-edit.html"'>--%>
                        <%--										<i class="fa fa-file-o"></i> 新建--%>
                        <%--									</button>--%>
                        <%--									<button type="button" class="btn btn-default" title="删除"--%>
                        <%--										onclick='confirm("你确认要删除吗？")'>--%>
                        <%--										<i class="fa fa-trash-o"></i> 删除--%>
                        <%--									</button>--%>
                        <%--									<button type="button" class="btn btn-default" title="开启"--%>
                        <%--										onclick='confirm("你确认要开启吗？")'>--%>
                        <%--										<i class="fa fa-check"></i> 开启--%>
                        <%--									</button>--%>
                        <%--									<button type="button" class="btn btn-default" title="屏蔽"--%>
                        <%--										onclick='confirm("你确认要屏蔽吗？")'>--%>
                        <%--										<i class="fa fa-ban"></i> 屏蔽--%>
                        <%--									</button>--%>
                        <%--									<button type="button" class="btn btn-default" title="刷新"--%>
                        <%--										onclick="window.location.reload();">--%>
                        <%--										<i class="fa fa-refresh"></i> 刷新--%>
                        <%--									</button>--%>
                        <%--								</div>--%>
                        <%--							</div>--%>
                        <%--						</div>--%>
                        <%--						<div class="box-tools pull-right">--%>
                        <%--							<div class="has-feedback">--%>
                        <%--								<input type="text" class="form-control input-sm"--%>
                        <%--									placeholder="搜索"> <span--%>
                        <%--									class="glyphicon glyphicon-search form-control-feedback"></span>--%>
                        <%--							</div>--%>
                        <%--						</div>--%>
                        <!--工具栏/-->


                    </div>
                    <!-- 数据表格 /-->

                </div>
                <!-- /.box-body -->



            </div>

        </section>
        <!-- 正文区域 /-->

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

        // 激活导航位置
        setSidebarActive("order-manage");

        // 列表按钮
        $("#dataList td input[type='checkbox']").iCheck({
            checkboxClass: 'icheckbox_square-blue',
            increaseArea: '20%'
        });
        // 全选操作
        $("#selall").click(function () {
            var clicks = $(this).is(':checked');
            if (!clicks) {
                $("#dataList td input[type='checkbox']").iCheck("uncheck");
            } else {
                $("#dataList td input[type='checkbox']").iCheck("check");
            }
            $(this).data("clicks", !clicks);
        });
    });

    function sendPageSize(pageSize){
    	window.location='${pageContext.request.contextPath}/product/query/page?currentPage=${map.get('currentPage')}&pageSize='+pageSize;
	}

	function removeProduct(){
        var str = ''
        var checked_input = $(".checked input").each(function (i, val) {
            // console.log(i)
            // console.log($(this))
            // console.log($(this)[0].value)
            i == 0 ? (str += ('ids=' + $(this).val())) : (str += ('&ids=' + $(this).val()))
        })
        // var ids=str.split(',')

        $.ajax({
            url: '${pageContext.request.contextPath}/product/deleteProductInBatch',
            data: str,
            success: function (e) {
                alert(e);
                window.location.reload()
            },
            error: function () {
                alert("出现错误")
            }
        })
    }

    function modify(obj){
        $(obj).parent().parent().find('input,select').each(function(){
            $(this).removeAttr('disabled');
        })
        $(obj).css('display','none')
        $(obj).next().css('display','');
    }

    $(document).ready(function(){
        $("input[name$='url']").each(function(){
            flashImg(this)
        })
    })

    function flashImg(obj){
        $(obj).blur(function(){
            $($(this).parent().parent().find('img')[0]).attr('src',$(this).val())
        })
    }

    function send(obj){
        var str=$(obj).parent().parent().find('input,select').serialize()
        $.ajax({
            url:'${pageContext.request.contextPath}/merchant/modify',
            data:str,
            success:function(e){
                alert(e)
                window.location.reload()
            },
            error:function(){
                alert("出现错误")
            }
        })
    }

    function createTr(){
        $($("tbody").find('tr')[0]).before(
            "<tr>\n" +
            "                                    <td><input name=\"id\" type=\"checkbox\" value=\"\"></td>\n" +
            "                                    <td>\n" +
            "                                    </td>\n" +
            "                                    <td>\n" +
            "                                        <img src=\"\" height=\"100px\" width=\"220px\"/>\n" +
            "                                    </td>\n" +
            "                                    <td>\n" +
            "                                        <input type=\"text\" style=\"margin-top:30px\" name=\"url\" onblur='flashImg(this)' class=\"form-control\"  value=\"\"/>\n" +
            "                                    </td>\n" +
            "                                    <td>\n" +
            "                                        <input name=\"productId\" type=\"text\" style=\"margin-top:30px\" class=\"form-control\"  value=\"\"/>\n" +
            "                                    </td>\n" +
            "                                    <td style=\"padding-top:38px\">\n" +
            "                                        <select class=\"select2\" name=\"type\" style=\"width: 80px\" >\n" +
            "                                           <option value='1'>轮播图</option>\n" +
            "                                           <option value='2'>广告</option>\n" +
            "                                        </select>\n" +
            "                                    </td>\n" +
            "                                    <td style=\"padding-top:40px\"></td>\n" +
            "                                    <td style=\"padding-top:38px\">\n" +
            "                                        <select  class=\"select2\" name=\"state\" style=\"width: 80px\" >\n" +
            "                                           <option value='1'>发布</option>\n" +
            "                                           <option value='0'>不发布</option>\n" +
            "                                        </select>\n" +
            "                                    </td>\n" +
            "\n" +
            "                                    <td class=\"text-center\">\n" +
            "                                        <button style=\"margin-top:30px;\" type=\"button\" class=\"btn bg-fuchsia btn-xs\"\n" +
            "                                                onclick='save(this)'>确认\n" +
            "                                        </button>\n" +
            "                                    </td>\n" +
            "                                </tr>"
        )
    }

    function save(obj){
        let str=$(obj).parent().parent().find('input,select').serialize()
        $.ajax({
            url:'${pageContext.request.contextPath}/homepage/picture/save',
            data:str,
            success:function(e){
                alert(e)
                window.location.reload()
            },
            error:function(){
                alert('出现异常')
            }
        })
    }

    function deleteInBatch() {
        $("#myModal2").modal("hide")
        var str = ''
        var checked_input = $(".checked input").each(function (i, val) {
            console.log(i)
            console.log($(this).val())
            i == 0 ? (str += ('ids=' + $(this).val())) : (str += ('&ids=' + $(this).val()))
        })
        // var ids=str.split(',')

        $.ajax({
            url: '${pageContext.request.contextPath}/merchant/deleteInBatch',
            data: str,
            success: function (e) {
                alert(e);
                window.location.reload()
            },
            error: function () {
                alert("出现错误")
            }
        })
    }
</script>
</body>

</html>
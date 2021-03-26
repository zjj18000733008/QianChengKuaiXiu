<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                手机修理区管理 <small>品牌</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/index.jsp"><i
                        class="fa fa-dashboard"></i> 首页</a></li>
                <li><a
                        href="${pageContext.request.contextPath}/role/findAll.do">手机修理区管理</a></li>

                <li class="active">品牌</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <div id="alert-success" class="alert alert-success" style="display: none" role="alert"></div>
        <div id="alert-error" class="alert alert-error" style="display: none" role="alert"></div>

        <!-- 正文区域 -->
        <section class="content"> <!-- .box-body -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">品牌</h3>
                </div>

                <div class="box-body">

                    <!-- 数据表格 -->
                    <div class="table-box">

                        <!--工具栏-->
                        <div class="pull-left">
                            <div class="form-group form-inline">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" title="新建" onclick="openModal(1)">
                                        <i class="fa fa-file-o"></i> 添加
                                    </button>

                                    <button type="button" class="btn btn-default" title="刷新">
                                        <i class="fa fa-refresh"></i> 刷新
                                    </button>
                                    <button type="button" class="btn btn-default" title="删除"
                                            onclick="openModal(2)">
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
                                <th class="" style="padding-right: 0px"><input
                                        id="selall" type="checkbox" class="icheckbox_square-blue">
                                </th>
                                <th class="sorting_asc">ID</th>
                                <th class="sorting_desc">品牌</th>
                                <th class="sorting_desc">所属电器</th>
                                <th class="sorting_desc">权重</th>
                                <th class="text-center">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${brands}" var="brand" varStatus="status">
                                <tr>
                                    <td><input name="id" type="checkbox" class="editable_${status.index}"
                                               value="${brand.id}"></td>
                                    <td>${brand.id}</td>
                                    <td><input type="text" class="form-control pull-right editable_${status.index}"
                                               readonly="readonly"
                                               value="${brand.name}"></td>
                                    <td>
                                        <select class="editable_${status.index}" disabled>
                                            <c:forEach items="${appliances}" var="appliance">
                                                <c:if test="${appliance.id==brand.electricApplianceId}">
                                                    <option value="${appliance.id}" selected>${appliance.name}</option>
                                                </c:if>
                                                <c:if test="${appliance.id!=brand.electricApplianceId}">
                                                    <option value="${appliance.id}">${appliance.name}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <select class="editable_${status.index}" disabled>
                                            <c:forEach begin="0" end="9" varStatus="statu">
                                                <c:if test="${brand.weight==statu.index}">
                                                    <option value="${statu.index}" selected>${statu.index}</option>
                                                </c:if>
                                                <c:if test="${brand.weight!=statu.index}">
                                                    <option value="${statu.index}">${statu.index}</option>
                                                </c:if>

                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td class="text-center">
                                        <button id="btn_${status.index}" type="button" class="btn bg-olive btn-xs"
                                                onclick='modify(${status.index})'>
                                            修改
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <!--
                        <tfoot>
                        <tr>
                        <th>Rendering engine</th>
                        <th>Browser</th>
                        <th>Platform(s)</th>
                        <th>Engine version</th>
                        <th>CSS grade</th>
                        </tr>
                        </tfoot>-->
                        </table>
                        <!--数据列表/-->

                    </div>
                    <!-- 数据表格 /-->

                </div>
                <!-- /.box-body -->

                <%--					<!-- .box-footer-->--%>
                <%--					<div class="box-footer">--%>
                <%--						<div class="pull-left">--%>
                <%--							<div class="form-group form-inline">--%>
                <%--								总共2 页，共14 条数据。 每页 <select class="form-control">--%>
                <%--									<option>1</option>--%>
                <%--									<option>2</option>--%>
                <%--									<option>3</option>--%>
                <%--									<option>4</option>--%>
                <%--									<option>5</option>--%>
                <%--								</select> 条--%>
                <%--							</div>--%>
                <%--						</div>--%>

                <%--						<div class="box-tools pull-right">--%>
                <%--							<ul class="pagination">--%>
                <%--								<li><a href="#" aria-label="Previous">首页</a></li>--%>
                <%--								<li><a href="#">上一页</a></li>--%>
                <%--								<li><a href="#">1</a></li>--%>
                <%--								<li><a href="#">2</a></li>--%>
                <%--								<li><a href="#">3</a></li>--%>
                <%--								<li><a href="#">4</a></li>--%>
                <%--								<li><a href="#">5</a></li>--%>
                <%--								<li><a href="#">下一页</a></li>--%>
                <%--								<li><a href="#" aria-label="Next">尾页</a></li>--%>
                <%--							</ul>--%>
                <%--						</div>--%>

                <%--					</div>--%>
                <!-- /.box-footer-->

            </div>

            <!-- Modal -->
            <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">添加品牌</h4>
                        </div>
                        <div class="modal-body">
                            <input class="form-control add" type="text" placeholder="品牌" name="name"/>

                            <select class="add select2">
                                <c:forEach items="${appliances}" var="appliance">
                                    <option value="${appliance.id}">${appliance.name}</option>
                                </c:forEach>
                            </select>

                            <select class="add select2">
                                <c:forEach begin="0" end="9" varStatus="status">
                                    <c:if test="${status.index==5}">
                                        <option value="${status.index}" selected>${status.index}</option>
                                    </c:if>
                                    <c:if test="${status.index!=5}">
                                        <option value="${status.index}" >${status.index}</option>
                                    </c:if>

                                </c:forEach>
                            </select>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" onclick="saveBrand()">保存</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                        </div>
                        <div class="modal-body">
                            此数据关联的数据也将被删除,您确定要继续吗
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" onclick="deleteAppliance()">继续</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- 正文区域 /-->

    </div>
    <!-- @@close -->
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
        setSidebarActive("admin-datalist");

        // 列表按钮
        $("#dataList td input[type='checkbox']").iCheck({
            checkboxClass: 'icheckbox_square-blue',
            increaseArea: '20%'
        });
        // 全选操作
        $("#selall").click(function () {
            var clicks = $(this).is(
                ':checked');
            if (!clicks) {
                $(
                    "#dataList td input[type='checkbox']")
                    .iCheck(
                        "uncheck");
            } else {
                $(
                    "#dataList td input[type='checkbox']")
                    .iCheck("check");
            }
            $(this).data("clicks",
                !clicks);
        });
    });

    // function openModal() {
    //     $("#myModal").modal("show");
    // }
    function openModal(i) {
        $("#myModal" + i).modal("show");
    }

    function closeModal(i) {
        $("#myModal" + i).modal("hide");
    }

    function saveAppliance() {
        $("#myModal1").modal("hide");
        var applianceName = $("#applianceName");
        $.ajax({
            url: '${pageContext.request.contextPath}/repair/brand/save',
            data: {
                name: applianceName.val()
            },
            success: function () {
                alert('添加成功')
                window.location.reload()
            },
            error: function () {
                alert("出现错误")
            }
        })
    }

    function saveBrand() {
        $("#myModal1").modal("hide")
        var input=$(".add")
        console.log(input)
        $.ajax({
            url: '${pageContext.request.contextPath}/repair/brand/save',
            data:{
                'repairBrands[0].name':$(input.get(0)).val(),
                'repairBrands[0].electricApplianceId':$(input.get(1)).val(),
                'repairBrands[0].weight':$(input.get(2)).val()
            },
            success:function(e){
                alert(e)
                window.location.reload()
            },
            error:function(){
                alert("出现错误")
            }
        })
    }

    function deleteAppliance() {
        $("#myModal2").modal("hide")
        var str = ''
        var checked_input = $(".checked input").each(function (i, val) {
            console.log(i)
            console.log($(this).val())
            i == 0 ? (str += ('ids=' + $(this).val())) : (str += ('&ids=' + $(this).val()))
        })
        // var ids=str.split(',')

        $.ajax({
            url: '${pageContext.request.contextPath}/repair/brand/deleteInBatch',
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

    function modify(i) {
        // console.log(1)
        var editable = $(".editable_" + i)
        // console.log(editable)
        //将input与select变为可编辑状态
        editable.removeAttr("readonly");
        editable.removeAttr("disabled");
        //得到改行的修改按钮
        var btn = $("#btn_" + i)
        //将其变为红色的,确认按钮
        btn.removeClass("bg-olive").addClass("bg-fuchsia")
        btn.text("确认")
        //点击确认按钮,发送更新请求
        btn.click(function () {
            $.ajax({
                url: '${pageContext.request.contextPath}/repair/brand/update',
                data: {
                    'id': $(editable.get(0)).val(),
                    'name': $(editable.get(1)).val(),
                    'electricApplianceId': $(editable.get(2)).val(),
                    'weight': $(editable.get(3)).val(),
                },
                success: function (e) {
                    // var alert=$("#alert-success")
                    // alert.text("修改成功")
                    // alert.alert()
                    alert(e)
                },
                error: function () {
                    alert("出现错误")
                },
                complete: function () {
                    btn.removeClass("bg-fuchsia").addClass("bg-olive")
                    btn.text("修改")
                }
            })

            $(editable.get(0)).attr("readonly", 'readonly')
            $(editable.get(1)).attr("readonly", 'readonly')
            $(editable.get(2)).attr("disabled", "");
            $(editable.get(3)).attr("disabled", "");
        })
    }
</script>
</body>

</html>
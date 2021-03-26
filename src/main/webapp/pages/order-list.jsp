<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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

<%--<div id="dispatch_ok" class="text-center alert alert-info" role="alert"></div>--%>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">选择派送人员</h4>
			</div>
			<div class="modal-body">
				<!-- Select multiple-->
				<div class="form-group">
					<label>派送员列表</label>
					<select id="deliveryman_list" multiple="multiple" class="form-control">

					</select>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary" onclick="delivery()">我选好了</button>
			</div>
		</div>
	</div>
</div>

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
                订单管理 <small>全部订单</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/index.jsp"><i
                        class="fa fa-dashboard"></i> 首页</a></li>
                <li><a
                        href="${pageContext.request.contextPath}/pages/order-list.jsp">订单管理</a></li>
                <li class="active">全部订单</li>
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
                                    <%--									<button type="button" class="btn btn-default" title="新建"--%>
                                    <%--										onclick='location.href="${pageContext.request.contextPath}/pages/order-update.jsp"'>--%>
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
                                    <button type="button" class="btn btn-default" title="刷新"
                                            onclick="window.location.reload();">
                                        <i class="fa fa-refresh"></i> 刷新
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
                                <th class="sorting">订单号</th>
                                <th class="sorting">收货人姓名</th>
                                <th class="sorting">收货人手机号</th>
                                <th class="sorting">配送地址</th>
                                <th class="sorting">支付方式</th>
                                <th class="sorting">配送/服务方式</th>
                                <th class="sorting">留言</th>
                                <th class="sorting">服务时间</th>
                                <th class="sorting">商品总价</th>
                                <th class="sorting">运费</th>
                                <th class="sorting">实付金额</th>
                                <th class="sorting">下单时间</th>
                                <th class="sorting">支付时间</th>
                                <th class="sorting">订单状态</th>
                                <th class="sorting">配送员</th>

                                <th class="text-center">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${orderAddressVos}" var="o">
                                <tr>
                                    <td><input name="ids" type="checkbox"></td>

                                    <td class="sorting">${o.id}</td>
                                    <td class="sorting">${o.receiverName}</td>
                                    <td class="sorting">${o.mobile}</td>
                                    <td class="sorting">${o.area}${o.streetAddress}</td>
                                    <td class="sorting">${o.paymentMethod}</td>
                                    <td class="sorting">${o.pattern}</td>
                                    <td class="sorting">${o.leaveWord}</td>
                                    <td class="sorting">${o.servicingTime}</td>
                                    <td class="sorting">${o.goodsAmount}</td>
                                    <td class="sorting">${o.freightCharge}</td>
                                    <td class="sorting">${o.actualAmount}</td>
                                    <td class="sorting">${o.orderTime}</td>
                                    <td class="sorting">${o.payTime}</td>
                                    <td class="sorting">${o.state}</td>
                                    <td class="sorting">${o.deliveryman}</td>

                                    <td class="text-center">
                                        <security:authorize access="hasAnyRole('ROLE_ADMIN')">
                                            <button type="button" class="btn bg-olive btn-xs"
                                                    onclick='openModal("${o.id}")'>指派
                                            </button>
                                        </security:authorize>
                                        <button type="button" class="btn bg-olive btn-xs"
                                                onclick='location.href="${pageContext.request.contextPath}/order/get/page?orderId=${o.id}"'>
                                            查看
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>


                            </tbody>

                        </table>
                        <!--数据列表/-->

                        <!--工具栏-->
                        <div class="pull-left">
                            <div class="form-group form-inline">
                                <div class="btn-group">
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
                                </div>
                            </div>
                        </div>
                        <div class="box-tools pull-right">
                            <%--							<div class="has-feedback">--%>
                            <%--								<input type="text" class="form-control input-sm"--%>
                            <%--									placeholder="搜索"> <span--%>
                            <%--									class="glyphicon glyphicon-search form-control-feedback"></span>--%>
                            <%--							</div>--%>
                        </div>
                        <!--工具栏/-->

                    </div>
                    <!-- 数据表格 /-->

                </div>
                <!-- /.box-body -->

                <!-- .box-footer-->
                <div class="box-footer">
                    <div class="pull-left">
                        <div class="form-group form-inline">
                            总共2 页，共14 条数据。 每页 <select class="form-control">
                            <option>10</option>
                            <option>15</option>
                            <option>20</option>
                            <option>50</option>
                            <option>80</option>
                        </select> 条
                        </div>
                    </div>

                    <div class="box-tools pull-right">
                        <ul class="pagination">
                            <li><a href="#" aria-label="Previous">首页</a></li>
                            <li><a href="#">上一页</a></li>
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li><a href="#">下一页</a></li>
                            <li><a href="#" aria-label="Next">尾页</a></li>
                        </ul>
                    </div>

                </div>
                <!-- /.box-footer-->

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

    var orderID='';

    function openModal(orderId) {
        orderID=orderId;
        $.ajax({
            url: '${pageContext.request.contextPath}/staff/queryDeleverymansAndPickers',
            dataType: 'JSON',
            success: function (e) {
				var select=$("#deliveryman_list");
				select.empty();
                //弹窗,展示所有的DELEVERYMAN和PICKER
				for(x in e){
					select.append(
							"<option value='"+e[x].id+"'>"+e[x].realName+"</option>"
					)
				}

				$("#myModal").modal('show');
            },
            error: function (e) {
                alert("异常");
            }
        })
    }

    function delivery(){
        var select =$("#deliveryman_list");
        var val=select.val();
        var staffId=val[0];
        console.log(staffId);
        $.ajax({
            url: '${pageContext.request.contextPath}/order/dispatch',
            data:{
                "staffId":staffId,
                "orderId":orderID
            },
            success:function(e){
                // var alert_=$("#dispatch_ok");
                // alert_.text(e);
                // alert_.alert();
                // setTimeout(function(){
                //     alert_.alert('close')
                // },2500);
                alert(e);
                window.location.reload();
            }
        })

        $("#myModal").modal("hide")
	}
</script>
</body>

</html>
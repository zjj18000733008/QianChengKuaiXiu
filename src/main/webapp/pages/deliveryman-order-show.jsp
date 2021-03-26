<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<link rel=“stylesheet”
	href="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css">
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
				订单管理 <small>订单详情</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="all-admin-index.html"><i
						class="fa fa-dashboard"></i> 首页</a></li>
				<li><a href="all-order-manage-list.html">订单管理</a></li>
				<li class="active">订单详情</li>
			</ol>
			</section>
			<!-- 内容头部 /-->

			<!-- 正文区域 -->
			<section class="content"> <!--订单信息-->
			<div class="panel panel-default">
				<div class="panel-heading">订单信息</div>
				<div class="row data-type">

					<div class="col-md-2 title">订单号</div>
					<div class="col-md-4 data">
						<input type="text" class="form-control" placeholder="订单号"
							value="${orderForStaff.id }" readonly="readonly">
					</div>

					<div class="col-md-2 title">收件人姓名</div>
					<div class="col-md-4 data">
						<input type="text" class="form-control" placeholder="收件人姓名"
							   value="${orderForStaff.receiverName }" readonly="readonly">
					</div>

					<div class="col-md-2 title">收件人手机号</div>
					<div class="col-md-4 data">
						<input type="text" class="form-control" placeholder="收件人手机号"
							value="${orderForStaff.mobile }" readonly="readonly">
					</div>

					<div class="col-md-2 title">收件人地址</div>
					<div class="col-md-4 data">
						<input type="text" class="form-control" placeholder="收件人地址"
							value="${orderForStaff.area }${orderForStaff.streetAddress}" readonly="readonly">
					</div>

					<div class="col-md-2 title">支付方式</div>
					<div class="col-md-4 data">
						<input type="text" class="form-control" placeholder="支付方式"
							   value="${orderForStaff.paymentMethod }" readonly="readonly">
					</div>

					<div class="col-md-2 title">留言</div>
					<div class="col-md-4 data">
						<input type="text" class="form-control" placeholder="留言"
							   value="${orderForStaff.leaveWord }" readonly="readonly">
					</div>

					<div class="col-md-2 title">服务时间</div>
					<div class="col-md-4 data">
						<div class="input-group date">
							<div class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</div>
							<input type="text" class="form-control pull-right"
								id="datepicker-a6" value="${orderForStaff.servicingTime }"
								readonly="readonly">
						</div>
					</div>

					<div class="col-md-2 title">商品总价</div>
					<div class="col-md-4 data">
						<input type="text" class="form-control" placeholder="商品总价"
							   value="${orderForStaff.goodsAmount }" readonly="readonly">
					</div>

					<div class="col-md-2 title">运费</div>
					<div class="col-md-4 data">
						<input type="text" class="form-control" placeholder="运费"
							   value="${orderForStaff.freightCharge }" readonly="readonly">
					</div>

					<div class="col-md-2 title">实付金额</div>
					<div class="col-md-4 data">
						<input type="text" class="form-control" placeholder="实付金额"
							   value="${orderForStaff.actualAmount }" readonly="readonly">
					</div>

					<div class="col-md-2 title">下单时间</div>
					<div class="col-md-4 data">
						<div class="input-group date">
							<div class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</div>
							<input type="text" class="form-control pull-right"
								   id="datepicker-a3" readonly="readonly"
								   value="${orderForStaff.orderTime}">
						</div>
					</div>

					<div class="col-md-2 title">支付时间</div>
					<div class="col-md-4 data">
						<div class="input-group date">
							<div class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</div>
							<input type="text" class="form-control pull-right"
								   id="datepicker-a4" readonly="readonly"
								   value="${orderForStaff.payTime}">
						</div>
					</div>

					<div class="col-md-2 title">订单状态</div>
					<div class="col-md-4 data">
						<input type="text" class="form-control" placeholder="订单状态"
							   value="${orderForStaff.state }" readonly="readonly">
					</div>

					<div class="col-md-2 title">配送员</div>
					<div class="col-md-4 data">
						<input type="text" class="form-control" placeholder="配送员"
							   value="${orderForStaff.deliveryman }" readonly="readonly">
					</div>

<%--					<div class="col-md-2 title rowHeight2x">留言</div>--%>
<%--					<div class="col-md-10 data rowHeight2x">--%>
<%--						<textarea class="form-control" rows="3" placeholder="留言">--%>
<%--							${orders.orderDesc }--%>
<%--						</textarea>--%>
<%--					</div>--%>

				</div>
			</div>
			<!--订单信息/--> <!--游客信息-->
			<div class="panel panel-default">
				<div class="panel-heading">订单项信息</div>

				<!--数据列表-->
				<table id="dataList"
					class="table table-bordered table-striped table-hover dataTable">
					<thead>
						<tr class="row">
							<th class="col-md-4">产品名称</th>
							<th class="col-md-2">规格</th>
							<th class="col-md-2">单价</th>
							<th class="col-md-2">数量</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach  items="${orderItemVoList}" var="o">

							<tr class="row">
								<td class="col-md-2">
									${o.productName}
								</td>
								<td class="col-md-2">
									${o.specificationName}
								</td>
								<td class="col-md-2">
									${o.unitPrice}
								</td>
								<td class="col-md-2">
									${o.buynum}
								</td>
							</tr>
						</c:forEach>


					</tbody>
				</table>
				<!--数据列表/-->
			</div>


			<!--游客信息/--> <!--联系人信息-->
<%--			<div class="panel panel-default">--%>
<%--				<div class="panel-heading">联系人信息</div>--%>
<%--				<div class="row data-type">--%>

<%--					<div class="col-md-2 title">会员</div>--%>
<%--					<div class="col-md-4 data text">${orders.member.nickname }</div>--%>

<%--					<div class="col-md-2 title">联系人</div>--%>
<%--					<div class="col-md-4 data text">${orders.member.name}</div>--%>

<%--					<div class="col-md-2 title">手机号</div>--%>
<%--					<div class="col-md-4 data text">${orders.member.phoneNum}</div>--%>

<%--					<div class="col-md-2 title">邮箱</div>--%>
<%--					<div class="col-md-4 data text">${orders.member.email}</div>--%>

<%--				</div>--%>
<%--			</div>--%>
<%--			<!--联系人信息/--> <!--费用信息--> <c:if test="${orders.orderStatus==1}">--%>
<%--				<div class="panel panel-default">--%>
<%--					<div class="panel-heading">费用信息</div>--%>
<%--					<div class="row data-type">--%>

<%--						<div class="col-md-2 title">支付方式</div>--%>
<%--						<div class="col-md-4 data text">在线支付-${orders.payTypeStr}</div>--%>

<%--						<div class="col-md-2 title">金额</div>--%>
<%--						<div class="col-md-4 data text">￥${orders.product.productPrice}</div>--%>

<%--					</div>--%>
<%--				</div>--%>
<%--			</c:if> <!--费用信息/--> <!--工具栏-->--%>
			<div class="box-tools text-center">

				<button type="button" class="btn bg-default"
					onclick="history.back(-1);">返回</button>
			</div>
			<!--工具栏/--> </section>
			<!-- 正文区域 /-->


		</div>
		<!-- 内容区域 /-->

		<!-- 底部导航 -->
<%--		<footer class="main-footer">--%>
<%--		<div class="pull-right hidden-xs">--%>
<%--			<b>Version</b> 1.0.8--%>
<%--		</div>--%>
<%--		<strong>Copyright &copy; 2014-2017 <a--%>
<%--			href="http://www.itcast.cn">研究院研发部</a>.--%>
<%--		</strong> All rights reserved. </footer>--%>
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
		$(document).ready(function() {
			// 选择框
			$(".select2").select2();

			// WYSIHTML5编辑器
			$(".textarea").wysihtml5({
				locale : 'zh-CN'
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

		$(document).ready(function() {

			// 激活导航位置
			setSidebarActive("order-manage");

			// 列表按钮 
			$("#dataList td input[type='checkbox']").iCheck({
				checkboxClass : 'icheckbox_square-blue',
				increaseArea : '20%'
			});
			// 全选操作 
			$("#selall").click(function() {
				var clicks = $(this).is(':checked');
				if (!clicks) {
					$("#dataList td input[type='checkbox']").iCheck("uncheck");
				} else {
					$("#dataList td input[type='checkbox']").iCheck("check");
				}
				$(this).data("clicks", !clicks);
			});
		});

		$(document).ready(function(){
			var boxTool= $(".box-tools")
			if(${order.state=='2'}){
				boxTool.append(
						"<button type=\"button\" class=\"btn bg-aqua\"\n" +
						"\t\t\t\t\t\tonclick=\"send('3')\">前往取件</button>\n" +
						"\t\t\t\t<button type=\"button\" class=\"btn bg-aqua\"\n" +
						"\t\t\t\t\t\tonclick=\"send('7')\">前往派送</button>"
				)
			}else if(${order.state=='3' || order.state=='5'}){
				boxTool.append(
						"<button type='button' class='btn bg-aqua'\n"+
						" onclick=\"send('4')\">已确认取件</button>"
				)
			}else if(${order.state=='4' || order.state=='6'}){
				boxTool.append(
						"<button type='button' class='btn bg-aqua' \n" +
						"\t\t\t\t\t\t\tonclick='send(7)'>前往派送</button>"
				)
			}else if(${order.state=='7' || order.state=='9'}){
				boxTool.append(
						"<button type='button' class='btn bg-aqua' \n" +
						"\t\t\t\t\t\t\tonclick='send(8)'>已送达</button>"
				)
			}
		})

		function send(state){
			$.ajax({
				url:'${pageContext.request.contextPath}/order/deliveryman/changeOrderState',
				data:{
					orderId:'${orderForStaff.id}',
					"state":state
				},
				success:function(e){
					alert(e)
					window.location.reload()
				},
				error:function(){
					alert('出现错误')
				}
			})
		}
	</script>
</body>


</html>
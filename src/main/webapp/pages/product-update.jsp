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
                商品管理 <small>修改商品</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/pages/main.jsp"><i
                        class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="${pageContext.request.contextPath}/product/findAll.do">商品管理</a></li>
                <li class="active">修改商品</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <form id="form_"
              method="post">
            <!-- 正文区域 -->
            <section class="content"> <!--商品信息-->
                <div class="panel panel-default">
                    <div class="panel-heading">商品信息</div>
                    <div class="row data-type">
                        <input type="text" name="id" value="${productVO.id}" style="display:none"/>
                        <div class="col-md-2 title">商品名称</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" name="productName"
                                   placeholder="商品名称" value="${productVO.productName}">
                        </div>
                        <div class="col-md-2 title">商品简介</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" name="overview"
                                   placeholder="商品简介" value="${productVO.overview}">
                        </div>
                        <%--						<div class="col-md-2 title">出发时间</div>--%>
                        <%--						<div class="col-md-4 data">--%>
                        <%--							<div class="input-group date">--%>
                        <%--								<div class="input-group-addon">--%>
                        <%--									<i class="fa fa-calendar"></i>--%>
                        <%--								</div>--%>
                        <%--								<input type="text" class="form-control pull-right"--%>
                        <%--									id="datepicker-a3" name="departureTime">--%>
                        <%--							</div>--%>
                        <%--						</div>--%>


                        <%--						<div class="col-md-2 title">出发城市</div>--%>
                        <%--						<div class="col-md-4 data">--%>
                        <%--							<input type="text" class="form-control" name="cityName"--%>
                        <%--								placeholder="出发城市" value="">--%>
                        <%--						</div>--%>

                        <%--						<div class="col-md-2 title">产品价格</div>--%>
                        <%--						<div class="col-md-4 data">--%>
                        <%--							<input type="text" class="form-control" placeholder="产品价格"--%>
                        <%--								name="productPrice" value="">--%>
                        <%--						</div>--%>

                        <div class="col-md-2 title">类型</div>
                        <div class="col-md-4 data">
                            <select id="producttype" class="form-control select2" style="width: 100%"
                                    name="typeId">
                                <!--<option value="0" selected="selected">关闭</option>-->
                                <!--<option value="1">开启</option>-->
                            </select>
                        </div>

                        <div class="col-md-2 title">商品具体类型</div>
                        <div class="col-md-4 data">
                            <select id="concretType" class="form-control select2" style="width: 100%"
                                    name="concretTypeId">
                                <!--<option value="0" selected="selected">关闭</option>-->
                                <!--<option value="1">开启</option>-->
                            </select>
                        </div>

                        <div class="col-md-2 title brand" style="display: none">手机品牌</div>
                        <div class="col-md-4 data brand" style="display: none">
                            <select id="secondType" class="form-control select2" style="width: 100%"
                                    name="secondTypeId">
                                <!--<option value="0" selected="selected">关闭</option>-->
                                <!--<option value="1">开启</option>-->
                            </select>
                        </div>

                        <div class="col-md-12 title">商品封面图↓</div>
                        <div class="box-success">
                            <%--                            <div class="box-header">--%>
                            <%--                                <!-- /. tools -->--%>
                            <%--                            </div>--%>
                            <!-- /.box-header -->
                            <div class="box-body pad">

                                <textarea id="surfaceImgArea" class="textarea"
                                          placeholder="在工具栏中点击图片logo传入图片url,图片将在此回显"
                                          style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
                            </div>
                        </div>

                        <div class="col-md-2 title">商品轮播图↓</div>
                        <div class="box-success">
                            <div class="box-header">

                                <!-- tools box -->
                                <div class="pull-right box-tools">
                                    <button type="button" class="btn btn-default btn-sm" data-widget="collapse"
                                            data-toggle="tooltip" title="Collapse">
                                        <i class="fa fa-minus"></i></button>
                                    <button type="button" class="btn btn-default btn-sm" data-widget="remove"
                                            data-toggle="tooltip" title="Remove">
                                        <i class="fa fa-times"></i></button>
                                </div>
                                <!-- /. tools -->
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body pad">

								<textarea id="slideImgArea" class="textarea" placeholder="在工具栏中点击图片logo传入图片url,图片将在此回显"
                                          style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
                            </div>
                        </div>

                        <div class="col-md-2 title">商品详情图↓</div>
                        <div class="box-success">
                            <div class="box-header">

                                <!-- tools box -->
                                <div class="pull-right box-tools">
                                    <button type="button" class="btn btn-default btn-sm" data-widget="collapse"
                                            data-toggle="tooltip" title="Collapse">
                                        <i class="fa fa-minus"></i></button>
                                    <button type="button" class="btn btn-default btn-sm" data-widget="remove"
                                            data-toggle="tooltip" title="Remove">
                                        <i class="fa fa-times"></i></button>
                                </div>
                                <!-- /. tools -->
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body pad">

                                <textarea id="introImgArea" class="textarea" placeholder="在工具栏中点击图片logo传入图片url,图片将在此回显"
                                          style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
                            </div>
                        </div>

                        <div class="col-md-2 title">商品权重</div>
                        <div class="col-md-4 data">
                            <select class="form-control select2" style="width: 100%"
                                    name="weight">
                                <c:forEach begin="1" end="10" varStatus="status">
                                    <c:if test="${status.index==productVO.weight}">
                                        <option selected value="${status.index}">${status.index}</option>
                                    </c:if>
                                    <c:if test="${status.index!=productVO.weight}">
                                        <option value="${status.index}">${status.index}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-2 title">商品状态</div>
                        <div class="col-md-4 data">
                            <select id="productstate" class="form-control select2" style="width: 100%"
                                    name="state">
                                <c:if test="${productVO.state==1}">
                                    <option selected value="1">出售</option>
                                    <option value="0">暂不出售</option>
                                </c:if>
                                <c:if test="${productVO.state==0}">
                                    <option value="1">出售</option>
                                    <option selected value="0">暂不出售</option>
                                </c:if>
                            </select>
                        </div>


                        <%--                        <div class="col-md-2 title rowHeight2x">其他信息</div>--%>
                        <%--                        <div class="col-md-10 data rowHeight2x">--%>
                        <%--							<textarea class="form-control" rows="3" placeholder="其他信息"--%>
                        <%--                                      name="productDesc"></textarea>--%>
                        <%--                        </div>--%>


                    </div>
                </div>

                <!-- Modal -->
                <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel2">Modal title</h4>
                            </div>
                            <div class="modal-body">
                                确认要下架此种规格吗?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button id="confirmDelete" type="button" class="btn btn-primary">确认</button>
                            </div>
                        </div>
                    </div>
                </div>

                <c:forEach items="${specifications}" var="specification" varStatus="status">

                    <!--商品规格信息-->
                    <div id="specification_update${status.index}" class="panel panel-group">
                        <div class="panel-heading">
                            <span>商品规格信息</span>
                            <i class="glyphicon glyphicon-remove" onclick="confirmRemoveSpecification(${status.index})"
                               style="float:right"></i>
                        </div>

                        <input style="display:none" type="text" name="specifications[${status.index}].id"
                               value="${specification.id}"/>
                        <input style="display:none" type="text" name="specifications[${status.index}].productId"
                               value="${productVO.id}"/>

                        <div class="row data-type">
                            <div class="col-md-2 title">商品规格名称</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control" name="specifications[${status.index}].name"
                                       placeholder="商品规格名称" value="${specification.name}">
                            </div>
                            <div class="col-md-2 title">原价(单位:元)</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control"
                                       name="specifications[${status.index}].originalPrice"
                                       placeholder="商品原价" value="${specification.originalPrice}">
                            </div>
                            <div class="col-md-2 title">现价(单位:元)</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control"
                                       name="specifications[${status.index}].currentPrice"
                                       placeholder="商品现价" value="${specification.currentPrice}">
                            </div>
                            <div class="col-md-2 title">库存数</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control" name="specifications[${status.index}].inventory"
                                       placeholder="库存数" value="${specification.inventory}">
                            </div>
                                <%--							<div class="col-md-2 title">状态</div>--%>
                                <%--							<div class="col-md-4 data">--%>
                                <%--								<input type="text" class="form-control" name="specifications[${status.index}].state"--%>
                                <%--									   placeholder="状态" value="${specification.state}">--%>
                                <%--							</div>--%>
                            <div class="col-md-2 title">商品规格图</div>
                            <div class="col-md-2 ">
                                <!--模态窗口-->
                                <div class="tab-pane" id="tab-model">
                                    <button type="button" class="btn btn-primary" data-toggle="modal"
                                            data-target="#myModal" onclick="openMyModal(${status.index});return ;">
                                        点击修改
                                    </button>

                                    <div id="myModal${status.index}" class="modal modal-primary" role="dialog">
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
                                                                    <input id="inputurl0" type="text"
                                                                           class="form-control"
                                                                           placeholder=""
                                                                           name="specifications[${status.index}].img"
                                                                           value="${specification.img}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-outline" data-dismiss="modal"
                                                            onclick="closeModal(${status.index})">关闭
                                                    </button>
                                                    <button type="button" class="btn btn-outline" data-dismiss="modal"
                                                            onclick="showImg(${status.index});closeModal(${status.index})">
                                                        保存
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
                            </div>
                            <div class="col-md-5" id="imgholder${status.index}">
                                <img width="100px" height="100px" src="${specification.img}"/>
                            </div>
                        </div>
                    </div>
                    <!--商品规格信息end-->
                </c:forEach>



			</section>
            <!-- 正文区域 /-->
        </form>
        <form id="form_2">

        </form>
		<!--订单信息/--> <!--工具栏-->
		<div class="box-tools text-center">

			<button type="submit" class="btn bg-maroon" onclick="send();return false;">确认修改</button>
			<button type="button" class="btn bg-aqua" onclick="createSpecificationForm();">继续添加商品规格</button>
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

    $(document).ready(function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/type",
            dataType: "json",
            success: function (res) {
                // $("#producttype").html()
                var arr = new Array();
                arr = res;
                var select = $("#producttype");
                for (x in arr) {
                    // select.html('<option value="'+x.id+'">'+x.type+'</option>')
                    if (arr[x].id == '${productVO.typeId}') {
                        select.append("<option selected value=\"" + arr[x].id + "\">" + arr[x].type + "</option>")
                    } else {
                        select.append("<option value=\"" + arr[x].id + "\">" + arr[x].type + "</option>")
                    }
                }
            }
        });
    });

    $(document).ready(function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/concretType",
            dataType: "json",
            success: function (res) {
                // $("#producttype").html()
                var arr = new Array();
                arr = res;
                var select = $("#concretType");
                for (x in arr) {
                    console.log(x)
                    if (arr[x].id == '${productVO.concretTypeId}') {
                        select.append("<option selected value=\"" + arr[x].id + "\">" + arr[x].name + "</option>")
                    } else {
                        select.append("<option value=\"" + arr[x].id + "\">" + arr[x].name + "</option>")
                    }
                }
            },
            complete:function(){
                var selected=$("#concretType option:selected")
                if($(selected[0]).val()==1){
                    $('.brand').each(function(){
                        $(this).css('display','');
                    })
                }else{
                    $('.brand').each(function(){
                        $(this).css('display','none')
                    })
                }

                $("#concretType").on('change',function(){

                    if($($("#concretType option:selected")).val()==1){
                        $('.brand').each(function(){
                            $(this).css('display','');
                        })
                    }else{
                        $('.brand').each(function(){
                            $(this).css('display','none')
                        })
                    }
                })
            }
        });
    });
    $(document).ready(function(){
        $.ajax({
            url: "${pageContext.request.contextPath}/secondType",
            dataType: "json",
            success: function (res) {
                // $("#producttype").html()
                var arr = new Array();
                arr = res;
                var select = $("#secondType");
                for (x in arr) {
                    if (arr[x].id == '${productVO.secondTypeId}') {
                        select.append("<option selected value=\"" + arr[x].id + "\">" + arr[x].name + "</option>")
                    } else {
                        select.append("<option value=\"" + arr[x].id + "\">" + arr[x].name + "</option>")
                    }
                }
            }
        })
    })

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

    function createSpecificationForm() {
        // var specificationForm = $("#specification" + index);
		var form=$("#form_2");
        // specificationForm.after(
		form.append(
            " <!--商品规格信息-->\n" +
            "                <div id=\"specification" + index + "\" class=\"panel panel-group\">\n" +
            "                    <div class=\"panel-heading\">" +
            "<span>商品规格信息</span>\n" +
            "                        <i class=\"glyphicon glyphicon-remove\" onclick=\"removeSpecification(" + index + ")\" style=\"float:right\"></i>" +
            "</div>\n" +
			" <input style=\"display:none\" type=\"text\" name=\"specifications["+index+"].productId\"\n" +
				"                               value=\"${productVO.id}\"/>"+
            "                    <div class=\"row data-type\">\n" +
            "                        <div class=\"col-md-2 title\">商品规格名称</div>\n" +
            "                        <div class=\"col-md-4 data\">\n" +
            "                            <input type=\"text\" class=\"form-control\" name=\"specifications[" + index + "].name\"\n" +
            "                                   placeholder=\"商品规格名称\" value=\"\">\n" +
            "                        </div>\n" +
            "                        <div class=\"col-md-2 title\">原价(单位:元)</div>\n" +
            "                        <div class=\"col-md-4 data\">\n" +
            "                            <input type=\"text\" class=\"form-control\" name=\"specifications[" + index + "].originalPrice\"\n" +
            "                                   placeholder=\"商品原价\" value=\"\">\n" +
            "                        </div>\n" +
            "                        <div class=\"col-md-2 title\">现价(单位:元)</div>\n" +
            "                        <div class=\"col-md-4 data\">\n" +
            "                            <input type=\"text\" class=\"form-control\" name=\"specifications[" + index + "].currentPrice\"\n" +
            "                                   placeholder=\"商品现价\" value=\"\">\n" +
            "                        </div>\n" +
            "                        <div class=\"col-md-2 title\">库存数</div>\n" +
            "                        <div class=\"col-md-4 data\">\n" +
            "                            <input type=\"text\" class=\"form-control\" name=\"specifications[" + index + "].inventory\"\n" +
            "                                   placeholder=\"库存数\" value=\"\">\n" +
            "                        </div>\n" +
            "                        <div class=\"col-md-2 title\">商品规格图</div>\n" +
            "                        <div class=\"col-md-2 \">\n" +
            "                            <!--模态窗口-->\n" +
            "                            <div class=\"tab-pane\" id=\"tab-model\">\n" +
            "                                <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\"\n" +
            "                                        data-target=\"#myModal\" onclick=\"openMyModal(" + index + ");return ;\">\n" +
            "                                    点击添加\n" +
            "                                </button>\n" +
            "\n" +
            "                                <div id=\"myModal" + index + "\" class=\"modal modal-primary\" role=\"dialog\">\n" +
            "                                    <div class=\"modal-dialog modal-lg\">\n" +
            "                                        <div class=\"modal-content\">\n" +
            "                                            <div class=\"modal-header\">\n" +
            "                                                <button type=\"button\" class=\"close\" data-dismiss=\"modal\"\n" +
            "                                                        aria-label=\"Close\">\n" +
            "                                                    <span aria-hidden=\"true\">&times;</span></button>\n" +
            "                                                <h4 class=\"modal-title\">将图片url粘贴至此处</h4>\n" +
            "                                            </div>\n" +
            "                                            <div class=\"modal-body\">\n" +
            "                                                <div class=\"box-body\">\n" +
            "                                                    <div class=\"form-horizontal\">\n" +
            "                                                        <div class=\"form-group\">\n" +
            "                                                            <label class=\"col-sm-2 control-label\">url:</label>\n" +
            "                                                            <div class=\"col-sm-5\">\n" +
            "                                                                <input id=\"inputurl" + index + "\" type=\"text\" class=\"form-control\"\n" +
            "                                                                       placeholder=\"\" name=\"specifications[" + index + "].img\" value=\"http://\">\n" +
            "                                                            </div>\n" +
            "                                                        </div>\n" +
            "                                                    </div>\n" +
            "                                                </div>\n" +
            "                                            </div>\n" +
            "                                            <div class=\"modal-footer\">\n" +
            "                                                <button type=\"button\" class=\"btn btn-outline\" data-dismiss=\"modal\" onclick=\"closeModal(" + index + ")\">关闭\n" +
            "                                                </button>\n" +
            "                                                <button type=\"button\" class=\"btn btn-outline\" data-dismiss=\"modal\"\n" +
            "                                                        onclick=\"showImg(" + index + ");closeModal(" + index + ")\">保存\n" +
            "                                                </button>\n" +
            "                                            </div>\n" +
            "                                        </div>\n" +
            "                                        <!-- /.modal-content -->\n" +
            "                                    </div>\n" +
            "\n" +
            "                                    <!-- /.modal-dialog -->\n" +
            "                                </div>\n" +
            "                                <!-- /.modal -->\n" +
            "                            </div>\n" +
            "                            <!--模态窗口/-->\n" +
            "                        </div>\n" +
            "                        <div class=\"col-md-5\" id=\"imgholder" + index + "\">\n" +
            "\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <!--商品规格信息end-->"
        )
        index = index + 1;
    }

    function showImg(i) {
        var url = $("#inputurl" + i).val();
        var imgholder = $("#imgholder" + i);
        imgholder.html(
            "<img width=\"100px\" height=\"100px\"  src=\"" + url + "\"/>"
        )
    }

    $(document).ready(function () {
        $("#confirmDelete").click(function () {
            setSpecificationValueToZero(trans_idnex)
            $("#myModal2").modal("hide");
        })
    })

    var trans_idnex;

    function confirmRemoveSpecification(i) {
        trans_idnex = i;
        var myModal = $("#myModal2");
        myModal.modal("show");
        // removeSpecification(i)
    }

    function setSpecificationValueToZero(i) {
        var specificationForm = $("#specification_update" + i);
        var name_input = specificationForm.find("input").get(2);
        $(name_input).val("0");
        specificationForm.css("display", "none");
    }

    function removeSpecification(i) {
        var specificationForm = $("#specification" + i);
        // specificationForm.remove();
		// var name_input=specificationForm.find("input").get(1);
		// $(name_input).val("0");
        specificationForm.find("input").each(function(i,val){
            if(i==0){

            }else{
                $(this).val("0");
            }
        })
		specificationForm.css("display","none");
    }

    $(document).ready(function () {

        // iframes.get(0).contentDocument.body.innerHTML='<p>1233</p>'
        // console.log(iframes.get(0).contentDocument.find("img"))
        // console.log(iframes.get(0).contentWindow.document.find("img"))
    })

    function send() {
        var iframes = $(".wysihtml5-sandbox");
        // console.log(iframes.get(1));
        // console.log(iframes.get(1).contentDocument)
        // console.log(iframes.get(1).contentDocument.body)
        // console.log(iframes.get(1).contentDocument.body.innerHTML)
        var surfaceImg = iframes.get(1).contentDocument.body.innerHTML;
        var slideImg = iframes.get(3).contentDocument.body.innerHTML;
        var introImg = iframes.get(5).contentDocument.body.innerHTML;
        console.log(surfaceImg)
        console.log(slideImg)
        console.log(introImg)

        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/product/updateProductAndSpecification",
            data: $("#form_").serialize() + '&surfaceImg=' + surfaceImg + "&slideImg=" + slideImg + "&introImg=" + introImg,
            success: function (e) {

                var arr = e.responseJSON;
                var msg = e;
                for (x in arr) {
                    msg = msg + arr[x] + '\n';
                }
                console.log(msg)
                alert(msg);
            },
            error: function (e) {
                console.log(e);
                var arr = e.responseJSON;
                var msg = "";
                for (x in arr) {
                    msg = msg + arr[x] + '\n';
                }
                console.log(msg)
                alert(msg);
            }
        })

        var flag=false;

        for(var i=0;i<index;i++){
            var name_input=$("#specification"+i).find("input").get(1);
            if($(name_input).val()!=0){
                flag=true;
            }
        }

		if(flag){
            $.ajax({
                type:"POST",
                url: "${pageContext.request.contextPath}/product/addSpecification",
                data: $("#form_2").serialize(),
                success: function(e){
                    var arr = e.responseJSON;
                    var msg = e;
                    for (x in arr) {
                        msg = msg + arr[x] + '\n';
                    }
                    console.log(msg)
                    alert(msg);
                },
                error: function(e){
                    var arr = e.responseJSON;
                    var msg = "";
                    for (x in arr) {
                        msg = msg + arr[x] + '\n';
                    }
                    console.log(msg)
                    alert(msg);
                }
            })
        }
    }

    $(document).ready(function () {
        var editor1 = new wysihtml5.Editor("surfaceImgArea", {
            toolbar: "",
            // name:"surfaceImg",
            parserRules: {
                attribute: {
                    "name": "surfaceImg"
                }
            }
        });
        var editor2 = new wysihtml5.Editor("slideImgArea", {
            toolbar: "",
            // name:"slideImg",
            parserRules: {
                attribute: {
                    "name": "slideImg"
                }
            }
        });
        var editor3 = new wysihtml5.Editor("introImgArea", {
            toolbar: "",
            // name:"introImg",
            parserRules: {
                attribute: {
                    "name": "introImg"
                }
            }
        });


        editor1.setValue('${productVO.surfaceImg}');
        editor2.setValue('${productVO.slideImg}');
        editor3.setValue('${productVO.introImg}');
        // editor.setValue('<b>12345</b><img src="https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e"/>');
    })
</script>
</body>

</html>
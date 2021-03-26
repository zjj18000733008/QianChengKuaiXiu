<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
                手机修理区管理 <small>故障类型</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/pages/main.jsp"><i
                        class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="${pageContext.request.contextPath}/product/findAll.do">手机修理区管理</a></li>
                <li class="active">故障类型</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <form id="form_"
              method="post">
            <!-- 正文区域 -->
            <section class="content"> <!--商品信息-->

                <div id="model-info" class="panel panel-default">
                    <div class="panel-heading">机型信息</div>
                    <div class="row data-type">

                        <div class="col-md-2 title">机型</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" name=""
                                   placeholder="机型" disabled="disabled" value="${model.name}">
                        </div>
                        <div class="col-md-2 title">品牌</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" name="overview"
                                   placeholder="品牌" disabled="disabled" value="${model.brandName}">
                        </div>
                        <div class="col-md-2 title">所属电器</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" name="overview"
                                   placeholder="所属电器" value="${model.applianceName}" disabled="disabled">
                        </div>

                    </div>
                    <security:csrfInput/>
                </div>

                <!--故障类型信息-->
                <div id="malfunction" class="panel bg-gray-light panel-group newly">
                    <div class="panel-heading">
                        <span style="font-size:18px;color: #0044cc">故障类型信息</span>
                        <span class="glyphicon glyphicon-remove" onclick="deleteMalfunction(this)" style="float:right;"></span>
                        <span class="fa fa-minus" onclick="hide(this)" style="float:right;margin-right:10px"></span>
                        <span class="fa fa-plus" onclick="show(this)" style="display:none;float:right;margin-right:10px"></span>
                    </div>

                    <div class="row data-type malfunction">
                        <div class="col-md-2 title">故障名称</div>
                        <div class="col-md-10 data">
                            <input type="text" class="form-control" name="name"
                                   placeholder="商品规格名称" value="">
                            <input type="text" name="modelId" value="${model.id}" style="display: none"/>
                        </div>
                    </div>

                    <div class="panel panel-group  malfunction-item-div">
                        <div class="panel-heading">
                            <span style="color: #00c0ef">故障类型项</span>
                            <i class="glyphicon glyphicon-remove" style="float:right"></i>
                        </div>
                        <div class="row data-type malfunction-item">
                            <div class="col-md-2 title">具体故障</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control" name="repairMalfunctionItems[?].itemName"
                                       placeholder="具体故障" value="">
                            </div>
                            <div class="col-md-1 title">原价</div>
                            <div class="col-md-2 data">
                                <input type="text" class="form-control" name="repairMalfunctionItems[?].originalPrice"
                                       placeholder="原价" value="">
                            </div>
                            <div class="col-md-1 title">现价</div>
                            <div class="col-md-2 data">
                                <input type="text" class="form-control" name="repairMalfunctionItems[?].currentPrice"
                                       placeholder="现价" value="">
                            </div>
                        </div>
                    </div>

                    <div class="box-tools text-center" style="margin:10px 0">
                        <button type="button" class="btn bg-teal" onclick="createMalfunctionItemDiv2(this)">继续添加故障类型项</button>
                    </div>
                </div>
                <!--故障类型信息end-->


                <!--订单信息/--> <!--工具栏-->
                <div class="box-tools text-center">

                    <button type="submit" class="btn bg-maroon" onclick="send();return false;">保存</button>
                    <button type="button" class="btn bg-aqua" onclick="createMalfunctionDiv(this);">继续故障类型</button>
                    <button type="button" class="btn bg-gray"
                            onclick="history.back(-1);">返回
                    </button>
                </div>
                <!--工具栏/--> </section>
            <!-- 正文区域 /-->
        </form>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">故障类型</h4>
                </div>
                <div class="modal-body">
                    确认删除此种故障类型吗?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary">确认</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel2">故障类型项</h4>
                </div>
                <div class="modal-body">
                    确认删除此种故障类型项吗?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary">确认</button>
                </div>
            </div>
        </div>
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

    function hide(obj){
        var malfunctionDiv=$(obj).parent().parent()
        malfunctionDiv.children().css("display",'none')
        var panelHeading=malfunctionDiv.children('.panel-heading')
        panelHeading.children('.fa-plus').css('display','');
        panelHeading.children('.fa-minus').css('display','none');

        panelHeading.removeAttr('style')
    }

    function show(obj){
        var malfunctionDiv=$(obj).parent().parent()
        malfunctionDiv.children().css('display','')
        var panelHeading=malfunctionDiv.children('.panel-heading')
        panelHeading.children('.fa-plus').css('display','none')
        panelHeading.children('.fa-minus').css('display','')
    }

    $(document).ready(function () {
        $.ajax({
            url: '${pageContext.request.contextPath}/repair/malfunction/queryWithItems',
            data: {
                modelId:${model.id}
            },
            success: function (e) {
                var modelInfoDiv = $("#model-info")
                for (x in e) {
                    // console.log(e[x])
                    modelInfoDiv.after(
                        "<!--故障类型信息-->\n" +
                        "                <div class=\"malfunction-info-div panel panel-group bg-gray-light exist\">\n" +
                        "                    <div class=\"panel-heading\">\n" +
                        "                        <span style='font-size:18px;color: #0044cc'>故障类型信息</span>\n" +
                        "<span class=\"glyphicon glyphicon-remove\" onclick=\"deleteMalfunction(this)\" style=\"float:right;\"></span>\n" +
                        "                        <span class=\"fa fa-minus\" onclick=\"hide(this)\" style=\"float:right;margin-right:10px\"></span>\n" +
                        "                        <span class=\"fa fa-plus\" onclick=\"show(this)\" style=\"display:none;float:right;margin-right:10px\"></span>\n" +
                        "                    </div>\n" +
                        "                    <div  class=\"malfunction-name-div row data-type malfunction\">\n" +
                        "                       <input type='text' class='malfunction-id-input' value='"+e[x].id+"' style='display:none' name='repairMalfunctions[?].id'/>\n  " +
                        "                        <div class=\"col-md-2 title\">故障名称</div>\n" +
                        "                        <div class=\"col-md-10 data\">\n" +
                        "                            <input type=\"text\" class=\"form-control\" name=\"repairMalfunctions[?].name\"\n" +
                        "                                   placeholder=\"商品规格名称\" value=\"" + e[x].name + "\">\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "\n" +
                        "                </div>\n" +
                        "                <!--故障类型信息end-->"
                    )
                    var malItems = e[x].repairMalfunctionItems;
                    var malfunctionNameDiv;
                    for (y in malItems) {
                        malfunctionNameDiv = $(".malfunction-info-div")[0]
                        // console.log($(malfunctionNameDiv))
                        $(malfunctionNameDiv).append(
                            "<div class=\"panel panel-group exist\">\n" +
                            "                        <div class=\"panel-heading\">\n" +
                            "                            <span style='color:#00c0ef'>故障类型项</span>\n" +
                            "                            <i class=\"glyphicon glyphicon-remove\" onclick='deleteMalfunctionItem(this)' style=\"float:right\"></i>\n" +
                            "                        </div>\n" +
                            "                        <div class=\"row data-type malfunction-item\">\n" +
                            "                            <input type='text' class='malItemId-input' style='display:none' value='"+malItems[y].id+"' name='repairMalfunctionItems[?].id'/>" +
                            "                            <div class=\"col-md-2 title\">具体故障</div>\n" +
                            "                            <div class=\"col-md-4 data\">\n" +
                            "                                <input type=\"text\" class=\"form-control \" name=\"repairMalfunctionItems[?].itemName\"\n" +
                            "                                       placeholder=\"具体故障\" value=\"" + malItems[y].itemName + "\">\n" +
                            "                            </div>\n" +
                            "                            <div class=\"col-md-1 title\">原价</div>\n" +
                            "                            <div class=\"col-md-2 data\">\n" +
                            "                                <input type=\"text\" class=\"form-control\" name=\"repairMalfunctionItems[?].originalPrice\"\n" +
                            "                                       placeholder=\"原价\" value=\"" + malItems[y].originalPrice + "\">\n" +
                            "                            </div>\n" +
                            "                            <div class=\"col-md-1 title\">现价</div>\n" +
                            "                            <div class=\"col-md-2 data\">\n" +
                            "                                <input type=\"text\" class=\"form-control\" name=\"repairMalfunctionItems[0].currentPrice\"\n" +
                            "                                       placeholder=\"现价\" value=\"" + malItems[y].currentPrice + "\">\n" +
                            "                            </div>\n" +
                            "                        </div>\n" +
                            "                    </div>"
                        )
                    }
                    $(malfunctionNameDiv).append(
                        "<div class=\"box-tools text-center\" style='margin:10px 0'>\n" +
                        "                            <button type=\"button\" class=\"btn bg-teal\" onclick='createMalfunctionItemDiv(this)'>继续添加故障类型项</button>\n" +
                        "                        </div>"
                    )
                }
            },
            error: function (e) {
                console.log("出现异常")
            },
            complete:function(){
                $(".exist").find('input').each(function(){
                    $(this).focus(function(){
                        $(this).parent().parent().addClass('changed')
                    })
                })
            }
        })
    })

    function send(){
        var malfunctionChanged=$(".malfunction.changed")
        var malfunctionItemChanged=$(".malfunction-item.changed")
        var malfunctionItemNewlyWithMalId=$(".malfunction-item.newly-withMalId")

        if(malfunctionChanged.length>0){
            var str=''
            malfunctionChanged.each(function(i,val){
                str+=($(this).find('input').serialize().replace(/%3F/g,i)+'&')
            })
            $.ajax({
                url:'${pageContext.request.contextPath}/repair/malfunction/updateInBatch',
                data:str,
                success:function(e){

                },
                error:function(){
                    alert('出现错误')
                }
            })
        }
        if(malfunctionItemChanged.length>0){
            var str2=''
            malfunctionItemChanged.each(function(i,val){
                str2+=($(this).find('input').serialize().replace(/%3F/g,i)+'&')
            })
            $.ajax({
                url:'${pageContext.request.contextPath}/repair/malfunctionItem/updateInBatch',
                data:str2,
                success:function(e){
                    // alert(e)
                    // window.location.reload();
                },
                error:function(){
                    alert('出现错误')
                }
            })
        }
        if(malfunctionItemNewlyWithMalId.length>0){
            var str3=''
            malfunctionItemNewlyWithMalId.each(function(i,val){
                str3+=($(this).find('input').serialize().replace(/%3F/g,i)+'&')
            })
            // console.log(str3);
            $.ajax({
                url:'${pageContext.request.contextPath}/repair/malfunctionItem/saveInBatch',
                data:str3,
                success:function(e){
                    // alert(e)
                    // window.location.reload();
                },
                error:function(){
                    alert('出现错误')
                }
            })
        }

        $(".newly").each(function(){
            if($(this).find("input")[0].value!=''){
                var str=''
                $(this).find('.malfunction-item-div').each(function(i,val){
                    if($(this).find('input')[0].value!=''){//malfunctionItem的name为空值的将不会上传
                        str+=($(this).find('input').serialize().replace(/%3F/g,i)+'&')
                    }
                })
                $.ajax({
                    url:'${pageContext.request.contextPath}/repair/malfunction/saveWithItems',
                    data:$(this).find('.malfunction').find('input').serialize()+'&'+str
                })
            }
        })
        alert('操作成功')
        window.location.reload()
    }

    /**
     * 创建一个malfunctionItem表单,其中已包含malfunctionId
     * @param obj
     */
    function createMalfunctionItemDiv(obj){
        var malfunctionDiv=$(obj).parent().parent()
        malfunctionDiv.children('.box-tools').before(
            "<div class=\"panel panel-group \">\n" +
            "                        <div class=\"panel-heading\">\n" +
            "                            <span style='color:#00c0ef'>故障类型项</span>\n" +
            "                            <i class=\"glyphicon glyphicon-remove\" onclick='removeDiv(this)' style=\"float:right\"></i>\n" +
            "                        </div>\n" +
            "                        <div class=\"row data-type malfunction-item newly-withMalId\">\n" +
            "                            <input type='text' style='display:none' value='"+malfunctionDiv.find('.malfunction-id-input').val()+"' name='repairMalfunctionItems[?].malfunctionId'/>" +
            "                            <div class=\"col-md-2 title\">具体故障</div>\n" +
            "                            <div class=\"col-md-4 data\">\n" +
            "                                <input type=\"text\" class=\"form-control\" name=\"repairMalfunctionItems[?].itemName\"\n" +
            "                                       placeholder=\"具体故障\" value=\"\">\n" +
            "                            </div>\n" +
            "                            <div class=\"col-md-1 title\">原价</div>\n" +
            "                            <div class=\"col-md-2 data\">\n" +
            "                                <input type=\"text\" class=\"form-control\" name=\"repairMalfunctionItems[?].originalPrice\"\n" +
            "                                       placeholder=\"原价\" value=\"\">\n" +
            "                            </div>\n" +
            "                            <div class=\"col-md-1 title\">现价</div>\n" +
            "                            <div class=\"col-md-2 data\">\n" +
            "                                <input type=\"text\" class=\"form-control\" name=\"repairMalfunctionItems[?].currentPrice\"\n" +
            "                                       placeholder=\"现价\" value=\"\">\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "\n" +
            "                    </div>"
        )
    }

    /**
     * 创建一个malfunctionItem表单
     * @param obj
     */
    function createMalfunctionItemDiv2(obj){
        var malfunctionDiv=$(obj).parent().parent()
        malfunctionDiv.children('.box-tools').before(
            "<div class=\"panel panel-group bg-gray-light malfunction-item-div\">\n" +
            "                        <div class=\"panel-heading\">\n" +
            "                            <span style=\"color: #00c0ef\">故障类型项</span>\n" +
            "                            <i class=\"glyphicon glyphicon-remove\" onclick='removeDiv(this)' style=\"float:right\"></i>\n" +
            "                        </div>\n" +
            "                        <div class=\"row data-type malfunction-item\">\n" +
            "                            <div class=\"col-md-2 title\">具体故障</div>\n" +
            "                            <div class=\"col-md-4 data\">\n" +
            "                                <input type=\"text\" class=\"form-control\" name=\"repairMalfunctionItems[?].itemName\"\n" +
            "                                       placeholder=\"具体故障\" value=\"\">\n" +
            "                            </div>\n" +
            "                            <div class=\"col-md-1 title\">原价</div>\n" +
            "                            <div class=\"col-md-2 data\">\n" +
            "                                <input type=\"text\" class=\"form-control\" name=\"repairMalfunctionItems[?].originalPrice\"\n" +
            "                                       placeholder=\"原价\" value=\"\">\n" +
            "                            </div>\n" +
            "                            <div class=\"col-md-1 title\">现价</div>\n" +
            "                            <div class=\"col-md-2 data\">\n" +
            "                                <input type=\"text\" class=\"form-control\" name=\"repairMalfunctionItems[?].currentPrice\"\n" +
            "                                       placeholder=\"现价\" value=\"\">\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "\n" +
            "                    </div>"
        )
    }

    /**
     * 创建一个malfunction表单
     * @param obj
     */
    function createMalfunctionDiv(obj){
        $(obj).parent().before(
            "<!--故障类型信息-->\n" +
            "                <div id=\"malfunction\" class=\"panel bg-gray-light panel-group newly\">\n" +
            "                    <div class=\"panel-heading\">\n" +
            "                        <span style=\"font-size:18px;color: #0044cc\">故障类型信息</span>\n" +
            "                        <span class=\"glyphicon glyphicon-remove\" onclick=\"removeDiv(this)\" style=\"float:right;\"></span>\n" +
            "                        <span class=\"fa fa-minus\" onclick=\"hide(this)\" style=\"float:right;margin-right:10px\"></span>\n" +
            "                        <span class=\"fa fa-plus\" onclick=\"show(this)\" style=\"display:none;float:right;margin-right:10px\"></span>\n" +
            "                    </div>\n" +
            "\n" +
            "                    <div class=\"row data-type malfunction\">\n" +
            "                        <div class=\"col-md-2 title\">故障名称</div>\n" +
            "                        <div class=\"col-md-10 data\">\n" +
            "                            <input type=\"text\" class=\"form-control\" name=\"name\"\n" +
            "                                   placeholder=\"商品规格名称\" value=\"\">\n" +
            "<input type=\"text\" name=\"modelId\" value=\"${model.id}\" style=\"display: none\"/>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "\n" +
            "                    <div class=\"panel panel-group  malfunction-item-div\">\n" +
            "                        <div class=\"panel-heading\">\n" +
            "                            <span style=\"color: #00c0ef\">故障类型项</span>\n" +
            "                            <i class=\"glyphicon glyphicon-remove\" style=\"float:right\"></i>\n" +
            "                        </div>\n" +
            "                        <div class=\"row data-type malfunction-item\">\n" +
            "                            <div class=\"col-md-2 title\">具体故障</div>\n" +
            "                            <div class=\"col-md-4 data\">\n" +
            "                                <input type=\"text\" class=\"form-control\" name=\"repairMalfunctionItems[?].itemName\"\n" +
            "                                       placeholder=\"具体故障\" value=\"\">\n" +
            "                            </div>\n" +
            "                            <div class=\"col-md-1 title\">原价</div>\n" +
            "                            <div class=\"col-md-2 data\">\n" +
            "                                <input type=\"text\" class=\"form-control\" name=\"repairMalfunctionItems[?].originalPrice\"\n" +
            "                                       placeholder=\"原价\" value=\"\">\n" +
            "                            </div>\n" +
            "                            <div class=\"col-md-1 title\">现价</div>\n" +
            "                            <div class=\"col-md-2 data\">\n" +
            "                                <input type=\"text\" class=\"form-control\" name=\"repairMalfunctionItems[?].currentPrice\"\n" +
            "                                       placeholder=\"现价\" value=\"\">\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "\n" +
            "                    <div class=\"box-tools text-center\" style=\"margin:10px 0\">\n" +
            "                        <button type=\"button\" class=\"btn bg-teal\" onclick=\"createMalfunctionItemDiv2(this)\">继续添加故障类型项</button>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <!--故障类型信息end-->"
        )
    }

    function deleteMalfunction(obj){
        let malfunctionId=$(obj).parent().parent().find('.malfunction-id-input')[0].value
        $.ajax({
            url:'${pageContext.request.contextPath}/repair/malfunction/delete',
            data:{
                id:malfunctionId
            },
            success:function(e){
                alert(e)
                removeDiv(obj)
            },
            error:function(e){
                alert('出现错误')
            }
        })
    }

    function deleteMalfunctionItem(obj){
        let malItemId=$(obj).parent().parent().find(".malItemId-input")[0].value
        $.ajax({
            url:"${pageContext.request.contextPath}/repair/malfunctionItem/delete",
            data:{
                id:malItemId
            },
            success:function(e){
                alert(e)
                removeDiv(obj)
            },
            error:function(){
                alert('出现错误')
            }
        })
    }

    function removeDiv(obj){
        $(obj).parent().parent().remove()
    }
    // index = 0;
    //
    // function openMyModal(i){
    //     var myModal=$("#myModal"+i);
    //     myModal.addClass("in");
    //     myModal.css("display","block");
    // }
    //
    // function closeModal(i){
    //     var myModal=$("#myModal"+i);
    //     myModal.removeClass("in");
    //     myModal.css("display","none");
    // }


    function removeSpecification(i) {
        var specificationForm = $("#specification" + i);
        // specificationForm.remove();
        specificationForm.css("display", "none");
        specificationForm.find("input").each(function () {
            $(this).val("0");
        })
    }


</script>

</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src=
                     <security:authentication property="principal.profileImg"/>
                             class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>
                    <security:authentication property="principal.username"/>
                </p>
                <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">菜单</li>
            <li id="admin-index"><a
                    href="${pageContext.request.contextPath}/pages/main.jsp"><i
                    class="fa fa-dashboard"></i> <span>首页</span></a></li>

            <li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
                <span>用户管理</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">
                    <li><a
                            href="${pageContext.request.contextPath}/staff/myProfile"> <i
                            class="fa fa-circle-o"></i> 个人信息
                    </a></li>
                    <security:authorize access="hasAnyRole('ROLE_ADMIN')">
                        <li><a
                                href="${pageContext.request.contextPath}/staff/findAll"> <i
                                class="fa fa-circle-o"></i> 员工管理
                        </a></li>
                        <li><a
                                href="${pageContext.request.contextPath}/role/findAll"> <i
                                class="fa fa-circle-o"></i> 已注册用户(正在开发)
                        </a></li>
                    </security:authorize>
                </ul>
            </li>


            <li class="treeview"><a href="#"> <i class="glyphicon glyphicon-shopping-cart"></i>
                <span>商品管理</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">
                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/pages/product-add.jsp"> <i
                            class="fa fa-circle-o"></i> 添加商品
                    </a></li>
                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/product/query/page"> <i
                            class="fa fa-circle-o"></i> 所有商品
                    </a></li>
                    <%--                    <li id="system-setting"><a--%>
                    <%--                            href="${pageContext.request.contextPath}/pages/permission-list.jsp">--%>
                    <%--                        <i class="fa fa-circle-o"></i> 权限管理--%>
                    <%--                    </a></li>--%>
                </ul>
            </li>

            <li class="treeview"><a href="#"> <i class="glyphicon glyphicon-shopping-cart"></i>
                <span>广告管理</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">
                    <%--                    <li id="system-setting"><a--%>
                    <%--                            href="${pageContext.request.contextPath}/"> <i--%>
                    <%--                            class="fa fa-circle-o"></i> 添加广告--%>
                    <%--                    </a></li>--%>
                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/homepage/picture/queryAll/page"> <i
                            class="fa fa-circle-o"></i> 所有广告
                    </a></li>
                    <%--                    <li id="system-setting"><a--%>
                    <%--                            href="${pageContext.request.contextPath}/pages/permission-list.jsp">--%>
                    <%--                        <i class="fa fa-circle-o"></i> 权限管理--%>
                    <%--                    </a></li>--%>
                </ul>
            </li>

            <li class="treeview"><a href="#"> <i class="glyphicon glyphicon-shopping-cart"></i>
                <span>手机修理区管理</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">
                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/repair/electricAppliance/query/page"> <i
                            class="fa fa-circle-o"></i> 电器
                    </a></li>
                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/repair/brand/query/page"> <i
                            class="fa fa-circle-o"></i> 品牌
                    </a></li>
                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/repair/model/query/page"> <i
                            class="fa fa-circle-o"></i> 机型
                    </a></li>
                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/repair/model/query/page"> <i
                            class="fa fa-circle-o"></i> 待联系的客户(正在开发)
                    </a></li>


                    <%--                    <li id="system-setting"><a--%>
                    <%--                            href="${pageContext.request.contextPath}/pages/permission-list.jsp">--%>
                    <%--                        <i class="fa fa-circle-o"></i> 权限管理--%>
                    <%--                    </a></li>--%>
                </ul>
            </li>

            <li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
                <span>订单管理</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">
                    <security:authorize access="hasAnyRole( 'ROLE_STAFF','ROLE_ADMIN')">
                        <li id="system-setting"><a
                                href="${pageContext.request.contextPath}/order/staff/query/page">
                            <i class="fa fa-circle-o"></i> 所有订单
                        </a></li>
                    </security:authorize>
                    <security:authorize access="hasAnyRole('ROLE_STAFF','ROLE_ADMIN')">
                        <li id="system-setting"><a
                                href="${pageContext.request.contextPath}/order/deliveryman/query/page">
                            <i class="fa fa-circle-o"></i> 查询自己需要派送的订单(正在开发)
                        </a></li>
                    </security:authorize>

                </ul>
            </li>

            <li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
                <span>文章管理</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">
                    <li><a
                            href="${pageContext.request.contextPath}/article/queryAll/page">
                        <i class="fa fa-circle-o"></i> 所有文章
                    </a></li>
                </ul>
            </li>

            <li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
                <span>商家管理</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">
                    <li><a
                            href="${pageContext.request.contextPath}/merchant/queryAll/page">
                        <i class="fa fa-circle-o"></i> 所有商家
                    </a></li>
                </ul>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-08-12 10:19:35 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class detail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(4);
    _jspx_dependants.put("/WEB-INF/views/order/../include/footer.jsp", Long.valueOf(1533571184758L));
    _jspx_dependants.put("/WEB-INF/views/order/../include/css.jsp", Long.valueOf(1533186601308L));
    _jspx_dependants.put("/WEB-INF/views/order/../include/header.jsp", Long.valueOf(1533186601317L));
    _jspx_dependants.put("/WEB-INF/views/order/../include/js.jsp", Long.valueOf(1533186601322L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fshiro_005fprincipal_0026_005fproperty_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fshiro_005fprincipal_0026_005fproperty_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fshiro_005fprincipal_0026_005fproperty_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("    <title>车管家ERP-订单详情</title>\r\n");
      out.write("\r\n");
      out.write("    <style>\r\n");
      out.write("        .td_title {\r\n");
      out.write("            font-weight: bold;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("    </style>\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("<meta content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\" name=\"viewport\">\r\n");
      out.write("<!-- Bootstrap 3.3.6 -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/static/bootstrap/css/bootstrap.min.css\">\r\n");
      out.write("<!-- Font Awesome -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/static/plugins/font-awesome/css/font-awesome.min.css\">\r\n");
      out.write("<!-- Theme style -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/static/dist/css/AdminLTE.min.css\">\r\n");
      out.write("<!-- AdminLTE Skins. Choose a skin from the css/skins\r\n");
      out.write("folder instead of downloading all of them to reduce the load. -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/static/dist/css/skins/_all-skins.min.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/static/plugins/tree/css/metroStyle/metroStyle.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/static/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/static/plugins/daterangepicker/daterangepicker.css\"/>\r\n");
      out.write("\r\n");
      out.write("<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->\r\n");
      out.write("<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\r\n");
      out.write("<!--[if lt IE 9]>\r\n");
      out.write("<script src=\"https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\r\n");
      out.write("<script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\r\n");
      out.write("<![endif]-->");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"hold-transition skin-blue sidebar-mini\">\r\n");
      out.write("<!-- Site wrapper -->\r\n");
      out.write("<div class=\"wrapper\" id=\"app\">\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- 顶部导航栏部分 -->\r\n");
      out.write("<header class=\"main-header\">\r\n");
      out.write("    <!-- Logo -->\r\n");
      out.write("    <a href=\"index2.html\" class=\"logo\">\r\n");
      out.write("        <!-- mini logo for sidebar mini 50x50 pixels -->\r\n");
      out.write("        <span class=\"logo-mini\"><b>ERP</b></span>\r\n");
      out.write("        <!-- logo for regular state and mobile devices -->\r\n");
      out.write("        <span class=\"logo-lg\"><b>车管家</b>ERP</span>\r\n");
      out.write("    </a>\r\n");
      out.write("    <!-- Header Navbar: style can be found in header.less -->\r\n");
      out.write("    <nav class=\"navbar navbar-static-top\">\r\n");
      out.write("        <!-- Sidebar toggle button-->\r\n");
      out.write("        <a href=\"#\" class=\"sidebar-toggle\" data-toggle=\"offcanvas\" role=\"button\">\r\n");
      out.write("            <span class=\"sr-only\">Toggle navigation</span>\r\n");
      out.write("            <span class=\"icon-bar\"></span>\r\n");
      out.write("            <span class=\"icon-bar\"></span>\r\n");
      out.write("            <span class=\"icon-bar\"></span>\r\n");
      out.write("        </a>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"navbar-custom-menu\">\r\n");
      out.write("            <ul class=\"nav navbar-nav\">\r\n");
      out.write("                <!-- User Account: style can be found in dropdown.less -->\r\n");
      out.write("                <li class=\"dropdown user user-menu\">\r\n");
      out.write("                    <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">\r\n");
      out.write("                        <img src=\"/static/dist/img/user2-160x160.jpg\" class=\"user-image\" alt=\"User Image\">\r\n");
      out.write("                        <span class=\"hidden-xs\">");
      if (_jspx_meth_shiro_005fprincipal_005f0(_jspx_page_context))
        return;
      out.write("</span>\r\n");
      out.write("                    </a>\r\n");
      out.write("                    <ul class=\"dropdown-menu\">\r\n");
      out.write("                        <!-- User image -->\r\n");
      out.write("                        <li class=\"user-header\">\r\n");
      out.write("                            <img src=\"/static/dist/img/user2-160x160.jpg\" class=\"img-circle\" alt=\"User Image\">\r\n");
      out.write("\r\n");
      out.write("                            <p>\r\n");
      out.write("                                ");
      if (_jspx_meth_shiro_005fprincipal_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                            </p>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        <!-- Menu Footer-->\r\n");
      out.write("                        <li class=\"user-footer\">\r\n");
      out.write("                            <div class=\"pull-left\">\r\n");
      out.write("                                <a href=\"/profile\" class=\"btn btn-default btn-flat\">个人设置</a>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"pull-right\">\r\n");
      out.write("                                <a href=\"/logout\" class=\"btn btn-default btn-flat\">安全退出</a>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </li>\r\n");
      out.write("            </ul>\r\n");
      out.write("        </div>\r\n");
      out.write("    </nav>\r\n");
      out.write("</header>\r\n");
      out.write("\r\n");
      out.write("  <!-- =============================================== -->\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../include/sider.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("menu", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("order", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <!-- 右侧内容部分 -->\r\n");
      out.write("    <div class=\"content-wrapper\">\r\n");
      out.write("        <!-- Content Header (Page header) -->\r\n");
      out.write("        <section class=\"content-header\">\r\n");
      out.write("            <div class=\"box-header with-border\">\r\n");
      out.write("                <h3 class=\"box-title\">保养维修单</h3>\r\n");
      out.write("                <div class=\"box-tools\">\r\n");
      out.write("                    <a href=\"/order/undone/list\" class=\"btn btn-primary btn-sm\"><i class=\"fa fa-arrow-left\"></i> 返回列表</a>\r\n");
      out.write("                    <button class=\"btn bg-maroon btn-sm\" id=\"printBtn\"><i class=\"fa fa-print\"></i> 打印</button>\r\n");
      out.write("                    ");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </section>\r\n");
      out.write("\r\n");
      out.write("        <!-- Main content -->\r\n");
      out.write("        <section class=\"content\">\r\n");
      out.write("\r\n");
      out.write("            <div class=\"box\">\r\n");
      out.write("                <div class=\"box-header with-border\">\r\n");
      out.write("                    <h3 class=\"box-title\">订单信息</h3>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("                <div class=\"bo-body\">\r\n");
      out.write("                    <table class=\"table\">\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td class=\"td_title\">订单号:</td>\r\n");
      out.write("                            <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${order.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("                            <td class=\"td_title\">订单日期:</td>\r\n");
      out.write("                            <td>");
      if (_jspx_meth_fmt_005fformatDate_005f0(_jspx_page_context))
        return;
      out.write(" </td>\r\n");
      out.write("\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td class=\"td_title\">订单金额:</td>\r\n");
      out.write("                            <td>￥");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${order.orderMoney}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("                            <td class=\"td_title\">订单状态:</td>\r\n");
      out.write("                            <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${order.stateName}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("\r\n");
      out.write("                    </table>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <div class=\"box\">\r\n");
      out.write("                <div class=\"box-header with-border\">\r\n");
      out.write("                    <h3 class=\"box-title\">客户车辆信息</h3>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"box-body\">\r\n");
      out.write("                    <table class=\"table\">\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td class=\"td_title\">车牌号:</td>\r\n");
      out.write("                            <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${order.car.licenceNo}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("                            <td class=\"td_title\">客户姓名:</td>\r\n");
      out.write("                            <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${order.customer.userName}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("                            <td class=\"td_title\">身份证号:</td>\r\n");
      out.write("                            <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${order.customer.idCard}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td class=\"td_title\">车主电话:</td>\r\n");
      out.write("                            <td id=\"tel\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${order.customer.tel}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("                            <td class=\"td_title\">车型:</td>\r\n");
      out.write("                            <td id=\"carType\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${order.car.carType}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("                            <td class=\"td_title\">车辆识别码:</td>\r\n");
      out.write("                            <td id=\"carNo\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${order.car.carNo}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("\r\n");
      out.write("                        </tr>\r\n");
      out.write("                    </table>\r\n");
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"box\">\r\n");
      out.write("                <div class=\"box-header\">\r\n");
      out.write("                    <h3 class=\"box-title\">项目选择</h3>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("                <div class=\"box-body\">\r\n");
      out.write("                    <table class=\"table table-bordered \" style=\"border-width: 2px;\" id=\"infoForm\">\r\n");
      out.write("                        <thead>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <th>项目代码</th>\r\n");
      out.write("                            <th>项目名称</th>\r\n");
      out.write("                            <th>工时费用</th>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        </thead>\r\n");
      out.write("                        <tbody id=\"addTr\">\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${serviceType.serviceNo}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("                            <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${serviceType.serviceName}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("                            <td>￥");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${serviceType.serviceHour * 50.0}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        </tbody>\r\n");
      out.write("                        <tfoot>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td colspan=\"4\" class=\"td_title\">小计 ：￥");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${serviceType.serviceHour * 50.0}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(" </td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        </tfoot>\r\n");
      out.write("                    </table>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"box\">\r\n");
      out.write("                <div class=\"box-header\">\r\n");
      out.write("                    <h3 class=\"box-title\">选择配件</h3>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("                <div class=\"box-body\">\r\n");
      out.write("\r\n");
      out.write("                    <table class=\"table table-bordered \" style=\"border-width: 2px;\">\r\n");
      out.write("                        <thead>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <th>编号</th>\r\n");
      out.write("                            <th>名称</th>\r\n");
      out.write("                            <th>单价</th>\r\n");
      out.write("                            <th>数量</th>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        </thead>\r\n");
      out.write("                        <tbody>\r\n");
      out.write("                        ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("                        </tbody>\r\n");
      out.write("                        <tfoot>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td colspan=\"4\" class=\"td_title\">小计 ：");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${order.orderMoney - serviceType.serviceHour * 50}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(" </td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        </tfoot>\r\n");
      out.write("                    </table>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <h4 class=\"pull-right visible-print-block\" style=\"margin-right: 200px;\">客户签字：</h4>\r\n");
      out.write("\r\n");
      out.write("        </section>\r\n");
      out.write("        <!-- /.content -->\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("    <!-- /.content-wrapper -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("<!-- 底部 -->\r\n");
      out.write("<footer class=\"main-footer hidden-print\">\r\n");
      out.write("    <div class=\"pull-right hidden-xs\">\r\n");
      out.write("        <b>Version</b> 1.0\r\n");
      out.write("    </div>\r\n");
      out.write("    <strong>Copyright &copy; 2010 -2017 <a href=\"http://almsaeedstudio.com\">车管家</a>.</strong> All rights reserved.\r\n");
      out.write("</footer>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("<!-- ./wrapper -->\r\n");
      out.write("\r\n");
      out.write("<!-- jQuery 2.2.3 -->\r\n");
      out.write("<script src=\"/static/plugins/jQuery/jquery-2.2.3.min.js\"></script>\r\n");
      out.write("<!-- Bootstrap 3.3.6 -->\r\n");
      out.write("<script src=\"/static/bootstrap/js/bootstrap.min.js\"></script>\r\n");
      out.write("<!-- SlimScroll -->\r\n");
      out.write("<script src=\"/static/plugins/slimScroll/jquery.slimscroll.min.js\"></script>\r\n");
      out.write("<!-- FastClick -->\r\n");
      out.write("<script src=\"/static/plugins/fastclick/fastclick.js\"></script>\r\n");
      out.write("<!-- AdminLTE App -->\r\n");
      out.write("<script src=\"/static/dist/js/app.min.js\"></script>\r\n");
      out.write("<script src=\"/static/dist/js/jquery.twbsPagination.min.js\"></script>\r\n");
      out.write("<script src=\"/static/plugins/datetimepicker/js/bootstrap-datetimepicker.min.js\"></script>\r\n");
      out.write("<script src=\"/static/plugins/daterangepicker/moment.js\"></script>\r\n");
      out.write("<script src=\"/static/plugins/daterangepicker/daterangepicker.js\"></script>\r\n");
      out.write("<script src=\"/static/plugins/layer/layer.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script src=\"/static/dist/js/vue.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("    $(function() {\r\n");
      out.write("        var orderId = ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${order.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(";\r\n");
      out.write("        //删除客户\r\n");
      out.write("        $(\"#delBtn\").click(function(){\r\n");
      out.write("            layer.confirm(\"确定要删除吗?\",function (index) {\r\n");
      out.write("                layer.close(index);\r\n");
      out.write("                window.location.href = \"/order/\"+ orderId +\"/del\";\r\n");
      out.write("            });\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("        // 打印\r\n");
      out.write("        $(\"#printBtn\").click(function(){\r\n");
      out.write("            window.print();\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("        // 订单下发\r\n");
      out.write("        $(\"#transBtn\").click(function(){\r\n");
      out.write("            layer.confirm(\"确定要下发订单么？如此将不能修改和删除订单！\",function (index) {\r\n");
      out.write("                layer.close(index);\r\n");
      out.write("                $.get(\"/order/\"+ orderId +\"/trans\").done(function(res){\r\n");
      out.write("                    if(res.state == \"success\") {\r\n");
      out.write("                        layer.msg(\"订单已生成并下发！等待维修中...\",{time: 2000, icon:1},function(){\r\n");
      out.write("                            history.go(0);\r\n");
      out.write("                        });\r\n");
      out.write("                    } else {\r\n");
      out.write("                        layer.msg(res.message);\r\n");
      out.write("                    }\r\n");
      out.write("\r\n");
      out.write("                }).error(function(){\r\n");
      out.write("                    layer.msg(\"系统异常\")\r\n");
      out.write("                })\r\n");
      out.write("            });\r\n");
      out.write("        })\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    })\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_shiro_005fprincipal_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  shiro:principal
    org.apache.shiro.web.tags.PrincipalTag _jspx_th_shiro_005fprincipal_005f0 = (org.apache.shiro.web.tags.PrincipalTag) _005fjspx_005ftagPool_005fshiro_005fprincipal_0026_005fproperty_005fnobody.get(org.apache.shiro.web.tags.PrincipalTag.class);
    _jspx_th_shiro_005fprincipal_005f0.setPageContext(_jspx_page_context);
    _jspx_th_shiro_005fprincipal_005f0.setParent(null);
    // /WEB-INF/views/order/../include/header.jsp(29,48) name = property type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fprincipal_005f0.setProperty("employeeName");
    int _jspx_eval_shiro_005fprincipal_005f0 = _jspx_th_shiro_005fprincipal_005f0.doStartTag();
    if (_jspx_th_shiro_005fprincipal_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fshiro_005fprincipal_0026_005fproperty_005fnobody.reuse(_jspx_th_shiro_005fprincipal_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fshiro_005fprincipal_0026_005fproperty_005fnobody.reuse(_jspx_th_shiro_005fprincipal_005f0);
    return false;
  }

  private boolean _jspx_meth_shiro_005fprincipal_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  shiro:principal
    org.apache.shiro.web.tags.PrincipalTag _jspx_th_shiro_005fprincipal_005f1 = (org.apache.shiro.web.tags.PrincipalTag) _005fjspx_005ftagPool_005fshiro_005fprincipal_0026_005fproperty_005fnobody.get(org.apache.shiro.web.tags.PrincipalTag.class);
    _jspx_th_shiro_005fprincipal_005f1.setPageContext(_jspx_page_context);
    _jspx_th_shiro_005fprincipal_005f1.setParent(null);
    // /WEB-INF/views/order/../include/header.jsp(37,32) name = property type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fprincipal_005f1.setProperty("employeeName");
    int _jspx_eval_shiro_005fprincipal_005f1 = _jspx_th_shiro_005fprincipal_005f1.doStartTag();
    if (_jspx_th_shiro_005fprincipal_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fshiro_005fprincipal_0026_005fproperty_005fnobody.reuse(_jspx_th_shiro_005fprincipal_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fshiro_005fprincipal_0026_005fproperty_005fnobody.reuse(_jspx_th_shiro_005fprincipal_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /WEB-INF/views/order/detail.jsp(36,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${order.state == '1'}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                        <a href=\"/order/");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${order.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("/edit\" class=\"btn bg-purple btn-sm\"><i class=\"fa fa-pencil\"></i> 修改订单</a>\r\n");
        out.write("                        <button class=\"btn bg-orange btn-sm\" id=\"transBtn\"><i class=\"fa fa-recycle\"></i> 订单下发</button>\r\n");
        out.write("                        <button class=\"btn btn-danger btn-sm\" id=\"delBtn\"><i class=\"fa fa-trash-o\"></i> 删除</button>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_fmt_005fformatDate_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  fmt:formatDate
    org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag _jspx_th_fmt_005fformatDate_005f0 = (org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag) _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag.class);
    _jspx_th_fmt_005fformatDate_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fformatDate_005f0.setParent(null);
    // /WEB-INF/views/order/detail.jsp(59,32) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatDate_005f0.setValue((java.util.Date) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${order.createTime}", java.util.Date.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    // /WEB-INF/views/order/detail.jsp(59,32) name = pattern type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatDate_005f0.setPattern("yyyy-MM-dd");
    int _jspx_eval_fmt_005fformatDate_005f0 = _jspx_th_fmt_005fformatDate_005f0.doStartTag();
    if (_jspx_th_fmt_005fformatDate_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody.reuse(_jspx_th_fmt_005fformatDate_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody.reuse(_jspx_th_fmt_005fformatDate_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /WEB-INF/views/order/detail.jsp(148,24) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/views/order/detail.jsp(148,24) '${partsList}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${partsList}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/views/order/detail.jsp(148,24) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("parts");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("                            <tr>\r\n");
          out.write("                                <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${parts.partsNo}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("                                <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${parts.partsName}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("                                <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${parts.salePrice}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("                                <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${parts.num}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("                            </tr>\r\n");
          out.write("                        ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }
}

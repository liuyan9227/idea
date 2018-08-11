<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>车管家ERP-质量检查</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@ include file="../include/header.jsp" %>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="parts"/>
    </jsp:include>

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                质检部任务领取
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-body">
                    <c:forEach items="${fixOrderList}" var="fixOrder">
                        <div class="panel panel-info">
                            <!-- Default panel contents -->
                            <div class="panel-heading">
                                <a href="/fix/${fixOrder.orderId}/detail">订单号：${fixOrder.orderId}</a> - ${fixOrder.carType} - ${fixOrder.orderType}
                                <c:if test="${fixOrder.state == '2'}">
                                    <button rel="${fixOrder.orderId}" class="btn btn-success btn-sm pull-right receiveBtn">任务领取</button>
                                </c:if>

                            </div>
                            <!-- List group -->
                            <ul class="list-group">
                                <c:forEach items="${fixOrder.partsList}" var="parts">
                                    <li class="list-group-item">${parts.partsName} * ${parts.partsNum}</li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:forEach>
                    <!-- /.box-body -->

                </div>
                <!-- /.box -->
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@ include file="../include/footer.jsp" %>


</div>
<!-- ./wrapper -->

<%@ include file="../include/js.jsp" %>
<script>
    $(function(){
        $(".receiveBtn").click(function() {
            var orderId = $(this).attr("rel");
            layer.confirm("确定接收该任务么？", function(){
                // 改成ajax方式：error：当前员工已有正在进行的维修任务
                $.get("/fix/" + orderId + "/receive").done(function(res){
                    if(res.state = "success"){
                        if(res){
                            window.location.href="/fix/" + orderId + "/make";
                        } else {
                            layer.msg("您已经有正在维修的订单,请完成后再接单");
                        }
                    }
                }).error(function(err){
                    layer.msg("系统异常");
                });
            })
        })
    })
</script>
</body>
</html>

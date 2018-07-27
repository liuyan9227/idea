<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>车管家ERP-类型管理</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@ include file="../include/css.jsp" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/header.jsp"%>
    <!-- =============================================== -->

    <!-- 左侧菜单栏 -->
    <%@include file="../include/sider.jsp"%>
        <!-- /.sidebar -->
    </aside>

    <!-- =============================================== -->



    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                类型管理
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="box no-border">
                <div class="box-body">
                    <form class="form-inline pull-left">
                        <input type="text" name="typeName" value="${typeName}" placeholder="类型名称" class="form-control">
                        <%--<select class="form-control" name="partsType" id="partsType">
                            <option value="">请选择配件类型</option>
                            <option value="1">机油</option>
                            <option value="2">机滤</option>
                            <option value="3">轮胎</option>
                        </select>--%>
                        <button class="btn btn-default">搜索</button>
                    </form>

                        <a href="javascript:;" id="new">新增</a>




                </div>
            </div>

            <!-- Default box -->
            <div class="box">
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>名称</th>
                            <th>#</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${page.list}" var="page">
                            <tr>
                                <td>${page.typeName}</td>
                                <td>
                                    <a href="#">更新</a>
                                    <a href="javascript:;" ref="${page.id}" class="del" >删除</a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                    <ul id="pagination" class="pagination pull-right"></ul>
                </div>
                <!-- /.box-body -->

            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- 底部 -->
   <%@include file="../include/footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script>
    $(function(){

        $(".del").click(function(){
            var id = $(this).attr("ref");
            layer.confirm("你确定要删除吗?", function(){
                window.location.href = "/type/" + id + "/del";
            })
        });

        var message = "${message}";
        if(message){
            layer.msg(message);
        }









        $("#pagination").twbsPagination({
            totalPages : ${page.pages},
            visiblePages : 3,
            first : '首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href:"?p={{number}}"
        });

        var locale = {
            "format": 'YYYY-MM-DD',
            "separator": " - ",//
            "applyLabel": "确定",
            "cancelLabel": "取消",
            "fromLabel": "起始时间",
            "toLabel": "结束时间'",
            "customRangeLabel": "自定义",
            "weekLabel": "W",
            "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
            "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            "firstDay": 1
        };

        var startDate = "";
        var endDate = "";

        if(startDate && endDate) {
            $('#time').val(startDate + " / " + endDate);
        };


        $('#time').daterangepicker({
            autoUpdateInput:false,
            "locale": locale,
            "opens":"right",
            "timePicker":false
        },function(start,end) {
            $("#startTime").val(start.format('YYYY-MM-DD'));
            $("#endTime").val(end.format('YYYY-MM-DD'));

            $('#time').val(start.format('YYYY-MM-DD') + " / " + end.format('YYYY-MM-DD'));
        })
    })
</script>
</body>
</html>

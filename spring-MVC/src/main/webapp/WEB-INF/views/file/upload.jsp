<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>文件上传</title>
</head>
<body>
    <h4>文件上传</h4>
    <form action="" method="post" enctype="multipart/form-data">
        <c:if test="${not empty error}">
            <div style="color: red">${error}</div>
        </c:if>
        <input type="text" name="username"><br>
        <input type="file" name="file"><br>
        <button>button</button>
    </form>
</body>
</html>

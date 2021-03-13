<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 13.03.2021
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="ProjectEdit" method="POST">
        <input name="btn_save" value="Zapisz" type="submit">
    </form>

    ID zapisanego projektu: ${project.projectId}
</body>
</html>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%="Hello World!"%><br/>
    Data: <%=new SimpleDateFormat("dd-MM-yyyy").format(new Date())%><br/>
    ID Studenta: <%=request.getParameter("x_student_id")%><br/>
    Informacja przekierowana z serwletu: ${requestScope.x_redirect}
</body>
</html>

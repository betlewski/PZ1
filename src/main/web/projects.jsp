<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista projektów</title>
</head>
<body>
<h2>Lista projektów</h2>
<table border="1" cellpadding="3">
    <tr>
        <th>Lp.</th>
        <th>Id</th>
        <th>Nazwa</th>
        <th>Opis</th>
        <th>Utworzony</th>
        <th>Data obrony</th>
        <th>Edycja</th>
    </tr>
    <c:forEach var="project" items="${requestScope.projects}" varStatus="info">
        <tr>
            <td>${info.count}.</td>
            <td><c:out value="${project.projectId}" /></td>
            <td><c:out value="${project.name}" /></td>
            <td><c:out value="${project.description}" /></td>
            <td><c:out value="${project.createDateTime}" /></td>
            <td><c:out value="${project.submitDateTime}" /></td>
            <c:url value="/task_page.jsp" var="tasks_of_project">
                <c:param name="x_project_id" value="${project.projectId}" />
            </c:url>
            <td><a href='<c:out value="${tasks_of_project}" />'>Zadania</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista projektów</title>
</head>
<style>
    .pagination {
        display: flex;
        flex-direction: row;
    }

    .pagination > * {
        margin: 0.5em;
    }
</style>
<link rel="stylesheet" href="css/styled-table.css">
<body>
<h2>Lista projektów</h2>
<table border="1" cellpadding="3" class="styled-table">
    <thead>
        <tr>
            <th>Lp.</th>
            <th>Id</th>
            <th>Nazwa</th>
            <th>Opis</th>
            <th>Utworzony</th>
            <th>Data obrony</th>
            <th>Edycja</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="project" items="${requestScope.projects}" varStatus="info">
        <tr>
            <td>${info.count}.</td>
            <td><c:out value="${project.projectId}" /></td>
            <td><c:out value="${project.name}" /></td>
            <td><c:out value="${project.description}" /></td>
            <javatime:format value="${project.createDateTime}"
                             var="fmtCreateDateTime" pattern="yyyy-MM-dd hh:mm:ss" />
            <td><c:out value="${fmtCreateDateTime}" /></td>
            <javatime:format value="${project.submitDateTime}" var="fmtSubmitDateTime"
                             pattern="yyyy-MM-dd" />
            <td><c:out value="${fmtSubmitDateTime}" /></td>
            <c:url value="/task_page.jsp" var="tasks_of_project">
                <c:param name="x_project_id" value="${project.projectId}" />
            </c:url>
            <td><a href='<c:out value="${tasks_of_project}" />'>Zadania</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">
    <p>Strona: <span style="font-weight: bold"> ${requestScope.page + 1} </span></p>
    <c:url value="/projects" var="previousPage">
        <c:param name="page" value="${page - 1}" />
    </c:url>
    <a href='<c:out value="${previousPage}" />'>Poprzednia strona</a>
    <c:url value="/projects" var="nextPage">
        <c:param name="page" value="${page + 1}" />
    </c:url>
    <a href='<c:out value="${nextPage}" />'>Następna strona</a>
    <p>Rozmiar strony:  <span style="font-weight: bold"> ${requestScope.sizePage} </span></p>
</div>
</body>
</html>
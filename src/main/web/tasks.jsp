<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista zadań</title>
</head>
<style>
    .container {
        width: 60%;
        margin: 0 auto;
    }

    h2, p {
        text-align: center;
    }

    p {
        padding: 5px;
    }

    .pagination {
        display: flex;
        flex-direction: row;
    }

    .pagination > * {
        margin: 0.5em;
    }
</style>
<link rel="stylesheet" href="css/styled-table.css">
<link rel="stylesheet" href="css/styled-link.css">
<body>
<div class="container">
    <h2>Lista zadań dla projektu o ID: ${param.project_id}</h2>
    <c:url value="/task-create" var="task_create">
        <c:param name="project_id" value="${param.project_id}" />
    </c:url>
    <p><a href='<c:out value="${task_create}" />' class="link">Dodaj nowe zadanie</a></p>
    <table border="1" cellpadding="3" class="styled-table">
        <thead>
        <tr>
            <th>Lp.</th>
            <th>Id</th>
            <th>Nazwa</th>
            <th>Opis</th>
            <th>Kolejność</th>
            <th>Utworzony</th>
            <th>Akcje</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="task" items="${requestScope.tasks}" varStatus="info">
            <tr>
                <td>${info.count}.</td>
                <td><c:out value="${task.taskId}"/></td>
                <td><c:out value="${task.name}"/></td>
                <td><c:out value="${task.description}"/></td>
                <td><c:out value="${task.sequence}"/></td>
                <javatime:format value="${task.createDateTime}"
                                 var="fmtCreateDateTime" pattern="yyyy-MM-dd HH:mm:ss"/>
                <td><c:out value="${fmtCreateDateTime}"/></td>
                <c:url value="/task-delete" var="delete_task">
                    <c:param name="task_id" value="${task.taskId}"/>
                    <c:param name="project_id" value="${param.project_id}"/>
                </c:url>
                <c:url value="/task-edit" var="edit_task">
                    <c:param name="task_id" value="${task.taskId}"/>
                </c:url>
                <td>
                    <a href='<c:out value="${edit_task}" />' class="link link-edit">Edycja</a>
                    <a href='<c:out value="${delete_task}" />' class="link link-delete">Usuń</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">
        <p>Strona: <span style="font-weight: bold"> ${requestScope.page + 1} </span></p>
        <c:url value="/tasks" var="previousPage">
            <c:param name="page" value="${page - 1}"/>
            <c:param name="project_id" value="${param.project_id}"/>
        </c:url>
        <a href='<c:out value="${previousPage}" />' class="link">Poprzednia strona</a>
        <c:url value="/tasks" var="nextPage">
            <c:param name="page" value="${page + 1}"/>
            <c:param name="project_id" value="${param.project_id}"/>
        </c:url>
        <a href='<c:out value="${nextPage}" />' class="link">Następna strona</a>
        <p>Rozmiar strony: <span style="font-weight: bold"> ${requestScope.sizePage} </span></p>
    </div>
</div>
</body>
</html>
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
    <h2>Lista projektów</h2>
    <p><a href="project-edit" class="link">Dodaj nowy projekt</a></p>
    <table border="1" cellpadding="3" class="styled-table">
        <thead>
            <tr>
                <th>Lp.</th>
                <th>Id</th>
                <th>Nazwa</th>
                <th>Opis</th>
                <th>Utworzony</th>
                <th>Data obrony</th>
                <th>Akcje</th>
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
                                 var="fmtCreateDateTime" pattern="yyyy-MM-dd HH:mm:ss" />
                <td><c:out value="${fmtCreateDateTime}" /></td>
                <javatime:format value="${project.submitDateTime}" var="fmtSubmitDateTime"
                                 pattern="yyyy-MM-dd" />
                <td><c:out value="${fmtSubmitDateTime}" /></td>
                <c:url value="/tasks" var="tasks_of_project">
                    <c:param name="project_id" value="${project.projectId}" />
                </c:url>
                <c:url value="/project-delete" var="delete_project">
                    <c:param name="id" value="${project.projectId}" />
                </c:url>
                <c:url value="/project-edit" var="edit_project">
                    <c:param name="project_id" value="${project.projectId}" />
                </c:url>
                <td>
                    <a href='<c:out value="${tasks_of_project}" />' class="link">Zadania</a>
                    <a href='<c:out value="${edit_project}" />' class="link link-edit">Edycja</a>
                    <a href='<c:out value="${delete_project}" />' class="link link-delete">Usuń</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">
        <p>Strona: <span style="font-weight: bold"> ${requestScope.page + 1} </span></p>
        <c:url value="/projects" var="previousPage">
            <c:param name="page" value="${page - 1}" />
        </c:url>
        <a href='<c:out value="${previousPage}" />' class="link">Poprzednia strona</a>
        <c:url value="/projects" var="nextPage">
            <c:param name="page" value="${page + 1}" />
        </c:url>
        <a href='<c:out value="${nextPage}" />' class="link">Następna strona</a>
        <p>Rozmiar strony:  <span style="font-weight: bold"> ${requestScope.sizePage} </span></p>
    </div>
</div>
</body>
</html>
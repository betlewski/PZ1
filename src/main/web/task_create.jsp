<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>

<html>
<head>
    <title>Dodawanie zadania</title>
    <style>
        h2, p {
            text-align: center;
        }

        form {
            display: flex;
            flex-direction: column;
            width: 60%;
            margin: 0 auto;
        }
    </style>
    <link rel="stylesheet" href="css/styled-form.css">
    <link rel="stylesheet" href="css/styled-link.css">
</head>
<body>
<c:url value="/tasks" var="back_to_list">
    <c:param name="project_id" value="${param.project_id}"/>
</c:url>
<p><a href='<c:out value="${back_to_list}" />' class="link">< Wróć do listy zadań</a></p>
<h2>Dodawanie nowego zadania dla projektu o ID: ${param.project_id}</h2>

<c:url value="/task-create" var="task_create">
    <c:param name="project_id" value="${param.project_id}"/>
</c:url>
<form action='<c:out value="${task_create}" />' method="POST">
    <input type="text" name="name" placeholder="Nazwa zadania">
    <textarea name="description" cols="10" rows="5" placeholder="Opis"></textarea>
    <input type="number" name="sequence" placeholder="Kolejność">
    <input name="btn_save" value="Zapisz" type="submit">
</form>

<c:if test="${not empty requestScope.newTaskId}">
    <p>ID zapisanego zadania: ${requestScope.newTaskId}</p>
</c:if>
<c:if test="${not empty requestScope.noRequiredData}">
    <p>Nie podano wymaganych pól: "Nazwa zadania"</p>
</c:if>
</body>
</html>

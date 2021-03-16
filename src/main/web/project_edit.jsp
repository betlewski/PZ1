<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>

<html>
<head>
    <title>Title</title>
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
    <p><a href="projects" class="link">< Wróć do listy projektów</a></p>
    <h2>Edycja projektu o id: ${project.projectId}</h2>
    <form action="project-edit" method="POST">
        <input type="text" hidden="hidden" name="id" value="${project.projectId}">
        <input type="text" name="name" placeholder="Nazwa projektu" value="${project.name}">
        <textarea name="description" cols="10" rows="5" placeholder="Opis">${project.description}</textarea>
        <input type="date" name="submitDate" placeholder="Data obrony" value="${project.submitDate}">
        <input name="btn_save" value="Zapisz" type="submit">

        <c:if test="${not empty successfulEdit}">
            <p>Pomyślnie edytowano wpis o projekcie.</p>
        </c:if>
    </form>
</body>
</html>

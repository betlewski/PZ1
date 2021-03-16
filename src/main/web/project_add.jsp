<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie projektu</title>
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
    <h2>Dodawanie nowego projektu</h2>
    <form action="project-add" method="POST">
        <input type="text" name="name" placeholder="Nazwa projektu">
        <textarea name="description" cols="10" rows="5" placeholder="Opis"></textarea>
        <input type="date" name="submitDate" placeholder="Data obrony">
        <input name="btn_save" value="Zapisz" type="submit">
    </form>

    <c:if test="${not empty requestScope.newProjectId}">
        <p>ID zapisanego projektu: ${requestScope.newProjectId}</p>
    </c:if>
</body>
</html>

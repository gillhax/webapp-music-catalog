<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
    Страница  <c:choose>
        <c:when test="${edit}">добавление</c:when>
        <c:otherwise>редактирование</c:otherwise>
    </c:choose> песни
    </title>

    <style>

        .error {
            color: #ff0000;
        }
    </style>

</head>

<body>

<h2><c:choose>
    <c:when test="${edit}">Добавление</c:when>
    <c:otherwise>Редактирование</c:otherwise>
</c:choose> песни</h2>

<form:form method="POST" modelAttribute="song">
    <form:input type="hidden" path="id" id="id"/>
    <table>
        <tr>
            <td><label for="name">Название: </label> </td>
            <td><form:input path="name" id="name"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="albumId">Номер альбома: </label> </td>
            <td><form:input path="albumId" id="albumId"/></td>
            <td><form:errors path="albumId" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="albumId">Номер артиста: </label> </td>
            <td><form:input path="artistId" id="artistId"/></td>
            <td><form:errors path="artistId" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="albumId">Номер пользователя: </label> </td>
            <td><form:input path="userId" id="userId"/></td>
            <td><form:errors path="userId" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="albumId">Путь к исходному файлу: </label> </td>
            <td><form:input type="file" path="source" id="source"/></td>
            <td><form:errors path="source" cssClass="error"/></td>
        </tr>

        <tr>
            <td colspan="3">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Сохранить изминения"/>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Добавить"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
</form:form>
<br/>
<br/>
Вернуться к <a href="<c:url value='/list' />">списку всех песен</a>
</body>
</html>
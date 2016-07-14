<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        Страница  <c:choose>
        <c:when test="${edit}">редактирование</c:when>
        <c:otherwise>добавление</c:otherwise>
    </c:choose> группы
    </title>

    <style>

        .error {
            color: #ff0000;
        }
    </style>

</head>

<body>

<h2><c:choose>
    <c:when test="${edit}">Редактирование</c:when>
    <c:otherwise>Добавление</c:otherwise>
</c:choose> группы</h2>

<form:form method="POST" modelAttribute="artist"  enctype="multipart/form-data" >
    <form:input type="hidden" path="id" id="id"/>
    <table>
        <tr>
            <td><label for="name">Название группы: </label> </td>
            <td><form:input path="name" id="name"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
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
Вернуться к <a href="<c:url value='/artist-list' />">списку всех групп</a>
</body>
</html>
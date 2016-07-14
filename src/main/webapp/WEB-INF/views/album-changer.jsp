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
    </c:choose> альбома
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
</c:choose> альбома</h2>

<form:form method="POST" commandName="handleAlbumForm" modelAttribute="handleAlbumForm"  enctype="multipart/form-data" >
    <form:input type="hidden" path="id" id="id"/>
    <form:input type="hidden" path="coverSource" id="coverSource"/>
    <table>
        <tr>
            <td><label for="artistId">Группа: </label> </td>
            <td><form:select path="artistId" id="artistId">
                <c:forEach items="${artists}" var="artist">
                    <form:option value="${artist.id}">${artist.name}</form:option>
                </c:forEach>
            </form:select></td>
            <td><form:errors path="artistId" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="name">Название: </label> </td>
            <td><form:input path="name" id="name"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="year">Год издания альбома: </label> </td>
            <td><form:input path="year" id="year"/></td>
            <td><form:errors path="year" cssClass="error"/></td>
        </tr>

        <tr><c:choose>
            <c:when test="${edit}"><tr><td  colspan="2"><img style=" margin-left: 70px"  src="${handleAlbumForm.coverSource}" width="200" height="200" /></td></tr></c:when>
            <c:otherwise></c:otherwise>
            </c:choose>
            <td><label for="file" >Путь к обложке альбома: </label> </td>
            <td><form:input type="file" accept="image/*" path="file" id="file" /></td>
            <td><form:errors path="file" cssClass="error"/></td>
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
Вернуться к <a href="<c:url value='/album-list' />">списку всех альбомов</a>
</body>
</html>
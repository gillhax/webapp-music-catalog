<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Все альбомы - Музыкальный каталог</title>

    <style>
        tr:first-child{
            font-weight: bold;
            background-color: #9fe278;
        }
    </style>

</head>


<body >
<h2>Список всех альбомов</h2>

<div>

    <table>
        <c:forEach items="${albums}" var="album">

        <tr>
            <td colspan="2">${album.name}</td>
        </tr>
        <tr>
            <td>
                <a href="${album.coverSource}">${album.coverSource}</a>
                <br>
                ${album.year}
            </td>
            <td>
                <audio preload="auto">
                    <c:forEach items="${SongsSourceByAlbumId}" var="sources">
                        ${sources}
                    </c:forEach>
                </audio>
                <br>
            </td>
                <%--<td><a href="<c:url value='/edit-${album.id}-song' />">[ред.]</a></td>--%>
                <%--<td><a href="<c:url value='/delete-${album.id}-song' />">[удал.]</a></td>--%>
        </tr>

    </table>
</div>


<br/>
<a href="<c:url value='/album-new' />">Добавить новую песню</a>
</body>
</html>
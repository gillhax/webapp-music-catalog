<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Музыкальный каталог</title>

    <style>
        tr:first-child{
            font-weight: bold;
            background-color: #9fe278;
        }
    </style>

</head>


<body>
<h2>Список песен</h2>
<table>
    <tr>
        <td>Id</td><td>Название</td><td>Альбом</td><td>Артист</td><td>Пользователь</td><td>Путь к файлу</td>
    </tr>
    <c:forEach items="${songs}" var="song">
        <tr>
            <td>${song.id}</td>
            <td>${song.name}</td>
            <td>${song.albumId}</td>
            <td>${song.artistId}</td>
            <td>${song.userId}</td>
            <td>${song.source}</td>
            <td><a href="<c:url value='/edit-${song.id}-song' />">[ред.]</a></td>
            <td><a href="<c:url value='/delete-${song.id}-song' />">[удал.]</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="<c:url value='/new' />">Добавить новую песню</a>
</body>
</html>
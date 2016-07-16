<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template-top.jsp" />
<jsp:include page="/WEB-INF/views/js-playlist-play-settings.jsp" />


<h4> >  <c:choose> <c:when test="${byArtist}">  Группа ${artistName}  >  Альбомы </c:when>
    <c:otherwise>Все альбомы</c:otherwise> </c:choose></h4>
<br>
    <c:forEach items="${albums}" var="album">

        <table id="album" cellspacing="0" style="border:none; width:600px;margin: 0 auto;">
        <tr>
            <th>${album.name}</th>
            <th><a style="margin-left:397px;  font-weight:100" href="<c:url value='/edit-${album.id}-album' />">[ред.]</a>
            <a style=" font-weight:100" href="<c:url value='/delete-${album.id}-album' />">[удал.]</a>
            </th>
        </tr>
        <tr >
            <td style="display: list-item">
                <img src="${album.coverSource}" width="200" height="200" alt="${album.name}"/>
                <br><p style="text-align: right; margin-left:20px">${album.year}</p>
            </td>
            <td>
                <div id="wrapper" style="margin-left:5px;">
                    <audio preload=></audio>
                    <ol>
                        <c:forEach items="${songService.findSongsByAlbum(album.id)}" var="song">
                        <li><a href="#" data-src="${song.source}">${song.name}</a></li>
                        </c:forEach>
                     </ol>
                </div>
            </td>
        </tr>
        </table>
        <br>
        <br>
    </c:forEach>
<table>

    <br>
    <a href="<c:url value='/album-new' />">Добавить новый альбом</a>
<jsp:include page="/WEB-INF/views/template-bottom.jsp" />

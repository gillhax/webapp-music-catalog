<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template-top.jsp" />
<style> .audiojs { width: 320px; !important;}  .audiojs .scrubber { width: 150px!important;} </style>

 <script>
      audiojs.events.ready(function() {audiojs.createAll();
      });
</script>
<h4> >  <c:choose> <c:when test="${byArtist}">  Группа ${artistName}  >  Песни </c:when>
    <c:otherwise>Все песни</c:otherwise> </c:choose></h4>
<br>

<table cellspacing="0" style="margin: 0 auto;">
    <tr>
        <th>Id</th><th>Название</th><th>Группа</th><th>Альбом</th><th>Воспроизвести</th>
    </tr>
    <c:forEach items="${songs}" var="song">
        <tr>
            <td>${song.id}.</td>
            <td>${song.name}</td>
            <td>${artistService.findById(song.artistId).name}</td>
            <td>${albumService.findById(song.albumId).name}</td>
            <td><audio src="${song.source}" preload="none"></audio></td>
            <td><a href="<c:url value='/edit-${song.id}-song' />">[ред.]</a></td>
            <td><a href="<c:url value='/delete-${song.id}-song' />">[удал.]</a></td>
        </tr>
    </c:forEach>

</table>

<br>
<a href="<c:url value='/song-new' />">Добавить новую песню</a>

<jsp:include page="/WEB-INF/views/template-bottom.jsp" />
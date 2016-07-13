<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template-top.jsp" />


<h4> > Все альбомы</h4>
<br>
    <c:forEach items="${albums}" var="album">
        <table id="album" cellspacing="0" style="border:none; width:600px;margin: 0 auto;">
        <tr>
            <th colspan="2">${album.name}</th>
        </tr>
        <tr>
            <td>
                <img src="/cover/1.jpg"
                     width="200" height="200" alt="${album.name}"></a>
                <%--<a href="${album.coverSource}">${album.coverSource}</a>--%>
                <br>${album.year}
            </td>
            <td>
                <%--<audio preload="auto">--%>
                    <c:forEach items="${songService.findSongsByAlbum(album.id)}" var="song">
                        ${song.source}
                    </c:forEach>
                <%--</audio>--%>
            </td>
        </tr>
        </table>
        <br>
        <br>
    </c:forEach>
<table>

<jsp:include page="/WEB-INF/views/template-bottom.jsp" />

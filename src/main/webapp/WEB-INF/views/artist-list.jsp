<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template-top.jsp" />


        <h4> > Все группы</h4>
        <br>
        <table cellspacing="0">
            <c:forEach items="${artists}" var="artist">
                <tr>
                    <td>${artist.id}.</td>
                    <td>${artist.name}</td>
                    <td><a href="<c:url value='/artist-${artist.id}/album-list' />">[Альбомы]</a></td>
                    <td><a href="<c:url value='/artist-${artist.id}/song-list' />">[Песни]</a></td>
                </tr>
            </c:forEach>
        </table>


 <jsp:include page="/WEB-INF/views/template-bottom.jsp" />

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template-top.jsp" />


        <h4> > Все группы</h4>
        <br>
        <table cellspacing="0" style="margin-left:15px">
            <c:forEach items="${artists}" var="artist">
                <tr>
                    <td>${artist.id}.</td>
                    <td>${artist.name}</td>
                    <td><a href="<c:url value='/album-list/artist-${artist.id}/' />">[Альбомы]</a></td>
                    <td><a href="<c:url value='/song-list/artist-${artist.id}/' />">[Песни]</a></td>
                    <td> -|||- </td>
                    <td><a style=" font-weight:100" href="<c:url value='/edit-${artist.id}-artist' />">[ред.]</a></td>
                    <td><a style=" font-weight:100" href="<c:url value='/delete-${artist.id}-artist' />">[удал.]</a></td>
                </tr>
            </c:forEach>
        </table>

<br>
<a href="<c:url value='/artist-new' />">Добавить новую группу</a>
 <jsp:include page="/WEB-INF/views/template-bottom.jsp" />

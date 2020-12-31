<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>

<currogas:layout pageName="repartidores">
    <h2>Repartidores</h2>

    <table id="repartidoresTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 200px;">DNI</th>
            <th>Fecha de nacimiento</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${repartidores}" var="repartidor">
            <tr>
                <td>
                    <spring:url value="/repartidores/{repartidorId}" var="repartidorUrl">
                        <spring:param name="repartidorId" value="${repartidor.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(repartidorUrl)}"><c:out value="${repartidor.nombre}"/></a>
                </td>
                <td>
                    <c:out value="${repartidor.dni}"/>
                </td>
                <td>
                    <c:out value="${repartidor.fechanacimiento}"/>
                </td> 
            </tr>
        </c:forEach>
        </tbody>
    </table>
</currogas:layout>

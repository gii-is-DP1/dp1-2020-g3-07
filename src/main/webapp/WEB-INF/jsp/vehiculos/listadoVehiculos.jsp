<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<currogas:layout pageName="vehiculos">
    <h1>Vehiculos</h1>

    <table id="vehiculosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Matricula</th>
            <th>Tipo de vehiculo</th>
            <th>Repartidor asociado</th>
            <th>Acciones</th> 
         
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vehiculos}" var="vehiculo">
            <tr>
                 <td>
                    <c:out value="${vehiculo.matricula}"/>
                </td> 
                <td>
                    <c:out value="${vehiculo.tipovehiculo}"/>
                </td>
                <td>
                    <c:out value="${vehiculo.repartidor.nombre}"/>
                </td>
                <td>
                    <%-- <spring:url value = "/vehiculos/edit/{vehiculoID}" var = "vehiculoUrl1">
                    	<spring:param name = "vehiculoID" value ="${vehiculo.id}"/>
                    </spring:url>
                    <a href = "${fn:escapeXml(vehiculoUrl1)}">Editar</a> --%>

                	<c:if test="${vehiculo.repartidor.nombre==null }">
	                    <spring:url value="vehiculos/delete/{vehiculoID}" var="vehiculoUrl">
	       				 <spring:param name="vehiculoID" value="${vehiculo.id}"/>
	    				</spring:url>
	    				<a href="${fn:escapeXml(vehiculoUrl)}" >Eliminar</a>
    				</c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a class="btn btn-default" href='<spring:url value="/vehiculos/new" htmlEscape="true"/>'>Añadir vehículo</a>
</currogas:layout>
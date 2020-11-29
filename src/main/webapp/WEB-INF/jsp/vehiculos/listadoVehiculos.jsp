<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vehiculos">
    <h2>Vehiculos</h2>

    <table id="vehiculosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Matricula</th>
            <th>Tipo de vehiculo</th>
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
                    <spring:url value="vehiculos/delete/{vehiculoID}" var="vehiculoUrl">
       				 <spring:param name="vehiculoID" value="${vehiculo.id}"/>
    				</spring:url>
    				<a href="${fn:escapeXml(vehiculoUrl)}" class="btn btn-default" >Eliminar vehiculo</a>
    				
    				<spring:url value = "/vehiculos/save/{vehiculoID}" var = "vehiculoUrl1">
                    	<spring:param name = "vehiculoID" value ="${vehiculo.id}"/>
                    </spring:url>
                    <a href = "${fn:escapeXml(vehiculoUrl1)}" class="btn btn-default">Editar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a class="btn btn-default" href='<spring:url value="/vehiculos/new" htmlEscape="true"/>'>Añadir vehículo</a>
</petclinic:layout>
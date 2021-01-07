<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>

<currogas:layout pageName="carta">
    <h2>Carta</h2>

    <table id="productoTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Precio</th>
            <th>Descripcion</th>
            <th>Alergenos</th>
<!--             <th>Acciones</th>    -->   
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${carta}" var="carta">
            <tr>
                <td>
                    <c:out value="${carta.name}"/>
                </td>
                <td>
                    <c:out value="${carta.precio}"/>
                </td>
                <td>
                    <c:out value="${carta.descripcion}"/>
                </td>
                <td>
                    <c:out value="${productos.alergenos}"/>
                </td>
               <%--  <td>
                    <spring:url value="productos/delete/{productoID}" var="productoUrl">
       				 <spring:param name="productoID" value="${carta.id}"/>
    				</spring:url>
    				<a href="${fn:escapeXml(productoUrl)}" class="btn btn-default">Eliminar producto</a>
    				
    				<spring:url value = "/productos/save/{productoID}" var = "productoUrl1">
                    	<spring:param name = "productoID" value ="${carta.id}"/>
                    </spring:url>
                    <a href = "${fn:escapeXml(productoUrl1)}" class="btn btn-default">Editar</a>
                </td> --%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    
</currogas:layout>

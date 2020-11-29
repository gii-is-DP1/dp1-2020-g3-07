<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="productos">
    <h2>Productos</h2>

    <table id="productoTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Precio</th>
            <th>Tama�o</th>
            <th>Acciones</th>      
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${productos}" var="productos">
            <tr>
                <td>
                    <c:out value="${productos.name}"/>
                </td>
                <td>
                    <c:out value="${productos.precio}"/>
                </td>
                <td>
                    <c:out value="${productos.tamanopizza}"/>
                </td>
                <td>
                    <spring:url value="productos/delete/{productoID}" var="productoUrl">
       				 <spring:param name="productoID" value="${productos.id}"/>
    				</spring:url>
    				<a href="${fn:escapeXml(productoUrl)}" class="btn btn-default">Eliminar producto</a>
    				
    				<spring:url value = "/productos/save/{productoID}" var = "productoUrl1">
                    	<spring:param name = "productoID" value ="${productos.id}"/>
                    </spring:url>
                    <a href = "${fn:escapeXml(productoUrl1)}">Editar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <a class="btn btn-default" href='<spring:url value="/productos/new" htmlEscape="true"/>'>A�adir producto</a>
</petclinic:layout>

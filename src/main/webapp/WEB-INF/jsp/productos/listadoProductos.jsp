<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<currogas:layout pageName="productos">
    <h1>Productos</h1>

    <table id="productoTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Precio</th>
            <th>Alérgenos</th>
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
                
                <c:forEach items="${productos.alergenos}" var="aler">
                    <c:out value="${aler.alergenotype}"/><span style="margin-right: 8px;"></span>
                </c:forEach>
                    
                </td>
                <td>
                    <spring:url value="productos/delete/{productoID}" var="productoUrl">
       				 <spring:param name="productoID" value="${productos.id}"/>
    				</spring:url>
    				<a href="${fn:escapeXml(productoUrl)}" >Eliminar producto</a>
    				
    				<spring:url value = "/productos/save/{productoID}" var = "productoUrl1">
                    	<spring:param name = "productoID" value ="${productos.id}"/>
                    </spring:url>
                    <a href = "${fn:escapeXml(productoUrl1)}" >Editar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <a class="btn btn-default" href='<spring:url value="/productos/new" htmlEscape="true"/>'>Anadir producto</a>
</currogas:layout>

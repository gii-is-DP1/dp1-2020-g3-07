<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="clientes">
    <h2>Clientes</h2>

    <table id="empleadosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 150px;">Apellidos</th>
            <th style="width: 200px;">Teléfono</th>
            <th>Dirección</th>
            <th>Email</th>
            <th>Fecha de nacimiento</th>
         
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${clientes}" var="clientes">
            <tr>
                 <td>
                    <c:out value="${clientes.nombre}"/>
                </td>
                <td>
                    <c:out value="${clientes.apellidos}"/>
                </td>
                <td>
                    <c:out value="${clientes.telefono}"/>
                </td>
                <td>
                    <c:out value="${clientes.direccion}"/>
                </td>
                <td>
                    <c:out value="${clientes.user.username}"/>
                </td>
                <td>
                    <c:out value="${clientes.fechanacimiento}"/>
                </td>
                
                
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
    
    <a class="btn btn-default" href='<spring:url value="/clientes/new" htmlEscape="true"/>'>Registrar nuevo cliente</a>
    
</petclinic:layout>
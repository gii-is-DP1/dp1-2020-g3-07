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
            <th style="width: 150px;">Nombre y Apellidos</th>
            <th style="width: 200px;">Teléfono</th>
            <th>Dirección</th>
            <th>Usuario</th>
            <th>Contraseña</th>
         
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${clientes}" var="cliente">
            <tr>
<%--                 <td>
                    <c:out value="${cliente.nombreApellidos}"/>
                </td> --%>
                
                <td>
                    <c:out value="${cliente.name}"/>
                </td>
                
                <td>
                    <c:out value="${cliente.telefono}"/>
                </td>
                <td>
                    <c:out value="${cliente.direccion}"/>
                </td>
                <td>
                    <c:out value="${cliente.usuario}"/>
                </td>
                <td>
                    <c:out value="${cliente.contrasena}"/>
                </td>
                
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
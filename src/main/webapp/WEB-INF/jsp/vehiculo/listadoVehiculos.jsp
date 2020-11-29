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
            <th style="width: 150px;">Matricula</th>
            <th style="width: 200px;">Tipo de Vehiculo</th>
         
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vehiculos}" var="vehiculo">
            <tr>
                <td>
                    <c:out value="${vehiculo.matricula}"/>
                </td>
                <td>
                    <c:out value="${vehiculo.tipoVehiculo}"/>
                </td>            
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
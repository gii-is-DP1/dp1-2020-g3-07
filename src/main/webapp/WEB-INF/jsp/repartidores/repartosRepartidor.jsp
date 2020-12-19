<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="repartosRepartidor">
    <h2>Repartos ${repartidor.nombre}</h2>

    <table id="repartoTable" class="table table-striped">
        <thead>
        <tr>
            <th>Fecha</th>
            <th>Hora de Inicio</th>
            <th>Hora de Fin</th>    
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${repartos}" var="reparto">
            <tr>
                <td>
                    <c:out value="${reparto.fecha}"/>
                </td>
                <td>
                    <c:out value="${reparto.horaInicio}"/>
                </td>
                <td>
                    <c:out value="${reparto.horaFin}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>

<currogas:layout pageName="pedidosAsignarRepartidor">
    <h2>Pedidos a asignar</h2>

    <form:form  class="form-horizontal">
	    <table class="table table-striped">
	        <tr>
	            <th>ID</th>
	            <th>Fecha y Hora</th>
	        </tr>
	        <c:forEach items="${pedidosList}" var="pedido">
	            <tr>
	                <td><form:checkbox path="pedidosAsignados" value="${pedido}" label="${pedido.id}" /></td>
	                <td><c:out value="${pedido.fecha}" /></td>
	            </tr>
	        </c:forEach>
		</table>
		<%-- <div class="control-group">
			<currogas:selectField name="vehiculo" label="Vehiculo " names="${vehiculos}" size="5"/>
		</div> --%>
		<div>
			<input type = "submit" value = "Confirmar Reparto"/>
		</div>
	</form:form>  
</currogas:layout>
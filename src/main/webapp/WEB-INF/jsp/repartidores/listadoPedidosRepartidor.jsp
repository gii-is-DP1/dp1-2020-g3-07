<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<currogas:layout pageName="repartidores">
    <h2>Pedidos a asignar</h2>
    
    <c:if test="${fn:length(pedidosList)!=0}">

    <form:form  class="form-horizontal">
	    <table class="table table-striped">
	        <tr>
	            <th>ID</th>
	            <th>Fecha y Hora</th>
	            <th>Nombre del cliente</th>
	        </tr>
	        <c:forEach items="${pedidosList}" var="pedido">
	            <tr>
	                <td><form:checkbox path="pedidosAsignados" value="${pedido}" label="${pedido.id}" /></td>
	                <td>
						<fmt:parseDate value="${pedido.fecha}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
						<fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${ parsedDateTime }" />
					</td>
	                <td>
						<c:choose>
							<c:when test="${pedido.cliente.id==1}">
								<c:out value="${pedido.nombreClienteGenerico}"></c:out>
							</c:when>    
							<c:otherwise>
								<c:out value="${pedido.cliente.nombre} ${pedido.cliente.apellidos}"></c:out>
							</c:otherwise>
						</c:choose>
					</td>
	            </tr>
	        </c:forEach>
		</table>
		<%-- <div class="control-group">
			<currogas:selectField name="vehiculo" label="Vehiculo " names="${vehiculos}" size="5"/>
		</div> --%>
		<div>
			<input class="btn btn-default" type = "submit" value = "Confirmar Reparto"/>
		</div>
	</form:form>
	
	</c:if>
	
	<c:if test="${fn:length(pedidosList)==0}">
	<p>No hay pedidos a domicilio pendientes</p>
	</c:if>
	
</currogas:layout>
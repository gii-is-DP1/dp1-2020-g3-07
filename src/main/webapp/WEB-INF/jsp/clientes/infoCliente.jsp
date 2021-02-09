<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<currogas:layout pageName="repartidores">

	<h2>Información del Cliente</h2>

		<table class="table table-striped">
			<tr>
				<th>Nombre</th>
				<th>Teléfono</th>
				<th>Dirección</th>
			</tr>
			<tr>
				<td>
					<c:choose>
						<c:when test="${cliente.id==1}">
							<c:out value="${pedido.nombreClienteGenerico}"></c:out>
						</c:when>    
						<c:otherwise>
							<c:out value="${cliente.nombre} ${cliente.apellidos}"></c:out>
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${cliente.id==1}">
							<c:out value="${pedido.telefonoClienteGenerico}"></c:out>
						</c:when>    
						<c:otherwise>
							<c:out value="${cliente.telefono}"></c:out>
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${cliente.id==1}">
							<c:out value="${pedido.direccionClienteGenerico}"></c:out>
						</c:when>    
						<c:otherwise>
							<c:out value="${cliente.direccion}"></c:out>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
		<form:form>
			<div>
				<spring:url value="/repartidores/{repartidorId}/repartos/{repartoId}/volver" var="volverUrl">
							<spring:param name="repartoId" value="${reparto.id}"/>
							<spring:param name="repartidorId" value="${repartidor.id}"/>
				</spring:url>
				<a href="${fn:escapeXml(volverUrl)}" class="enlacea">Volver al Reparto</a>
			</div>
		</form:form>
</currogas:layout>
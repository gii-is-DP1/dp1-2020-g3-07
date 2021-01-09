<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<currogas:layout pageName="repartidores">

	<h2>Información del Pedido</h2>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>Productos</th>
				<th>Cantidad de cada producto</th>
			</tr>
		</thead>
		<c:forEach items="${lineapedido}" var="lineapedido">
			<tr>
				<td><c:out value="${lineapedido.producto.name}"/></td>
				<td><c:out value="${lineapedido.cantidad}"/></td>
			</tr>
		</c:forEach>
	</table>

		<form:form>
		
			<div>
				<spring:url value="/repartidores/{repartidorId}/repartos/{repartoId}/volver" var="volverUrl">
							<spring:param name="repartoId" value="${reparto.id}"/>
							<spring:param name="repartidorId" value="${repartidor.id}"/>
				</spring:url>
				<a href="${fn:escapeXml(volverUrl)}" class="btn btn-outline-secondary">Volver al Reparto</a>
			</div>
		</form:form>
</currogas:layout>
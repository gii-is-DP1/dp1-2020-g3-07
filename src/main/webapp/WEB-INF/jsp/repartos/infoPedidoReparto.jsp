<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<currogas:layout pageName="repartidores">

	<h2>Información del Pedido</h2>

		<table class="table table-striped">
			<tr>
				<th>Productos</th>
				<th>Cantidad de cada producto</th>
			</tr>
			<tr>
				
			</tr>
		</table>
		<c:out value="${pedido.id}"/>
		<div>
			<c:forEach items="${lineapedido}" var="lineapedido">
				<h2><c:out value="${lineapedido.producto.name}"/>, Cantidad: <c:out value="${lineapedido.cantidad}"/>, Precio por unidad: <c:out value="${lineapedido.producto.precio}"/>$</h2>
			</c:forEach>
		</div>

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
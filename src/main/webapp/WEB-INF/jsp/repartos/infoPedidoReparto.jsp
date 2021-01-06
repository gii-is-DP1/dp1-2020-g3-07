<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>

<currogas:layout pageName="Pedidoreparto">

	<h2>Información del Pedido</h2>

		<table class="table table-striped">
			<tr>
				<th>Productos</th>
				<th>Cantidad de cada producto</th>
			</tr>
			<tr>
				
			</tr>
		</table>
		<form:form>
		<div>
			<c:forEach items="${lineapedido}" var="lineapedido">
				<c:out value="${lineapedido.producto.name}"></c:out>
				<c:out value="${lineapedido.cantidad}"></c:out>
			</c:forEach>
		</div>
			<div>
				<spring:url value="/repartidores/{repartidorId}/repartos/{repartoId}/volver" var="volverUrl">
							<spring:param name="repartoId" value="${reparto.id}"/>
							<spring:param name="repartidorId" value="${repartidor.id}"/>
				</spring:url>
				<a href="${fn:escapeXml(volverUrl)}" class="btn btn-outline-secondary">Volver al Reparto</a>
			</div>
		</form:form>
</currogas:layout>
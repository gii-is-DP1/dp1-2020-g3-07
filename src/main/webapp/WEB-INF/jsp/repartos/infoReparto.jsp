<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<currogas:layout pageName="repartidores">

	<h2>Información del Reparto</h2>

		<table class="table table-striped">
			<tr>
				<th>Nombre repartidor</th>
				<th>Fecha</th>
				<th>Hora de inicio</th>
			</tr>
			<tr>
				<td><c:out value="${reparto.repartidor.nombre}"></c:out></td>
				<td><c:out value="${reparto.fecha}"></c:out></td>
				<td>
					<fmt:parseDate value="${reparto.horaInicio}" pattern="HH:mm" var="parsedDateTime" type="both" />
					<fmt:formatDate pattern="HH:mm" value="${ parsedDateTime }" />
				</td>
			</tr>
		</table>

	<h2>Pedidos asociados</h2>

	<form:form class="form-horizontal">
		<table class="table table-striped">
			<tr>
				<th>Nombre del cliente</th>
				<th>Fecha del pedido</th>
				<th>Estado del pedido</th>
				<th>Contenido del pedido</th>
				<th>Acciones</th>
			</tr>
			<c:forEach items="${reparto.pedidos}" var="pedido">
				<tr>
					<td>
						<spring:url value="/repartidores/{repartidorId}/repartos/{repartoId}/{pedidoId}/cliente/{clienteId}" var="clienteUrl">
							<spring:param name="clienteId" value="${pedido.cliente.id}"/>
							<spring:param name="repartoId" value="${reparto.id}"/>
							<spring:param name="pedidoId" value="${pedido.id}"/>
							<spring:param name="repartidorId" value="${repartidor.id}"/>
						</spring:url>
						<a href="${fn:escapeXml(clienteUrl)}" class="enlacea">
							<c:choose>
								<c:when test="${pedido.cliente.id==1}">
									<c:out value="${pedido.nombreClienteGenerico}"></c:out>
								</c:when>    
								<c:otherwise>
									<c:out value="${pedido.cliente.nombre} ${pedido.cliente.apellidos}"></c:out>
								</c:otherwise>
							</c:choose>
						</a>
					</td>
					<td>
						<fmt:parseDate value="${pedido.fecha}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
						<fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${ parsedDateTime }" />
					</td>
					<td><c:out value="${pedido.estadopedido}"></c:out></td>
					<td>
						<spring:url value="/repartidores/{repartidorId}/repartos/{repartoId}/detallesPedido/{pedidoId}" var="detallesUrl">
							<spring:param name="pedidoId" value="${pedido.id}"/>
							<spring:param name="repartoId" value="${reparto.id}"/>
							<spring:param name="repartidorId" value="${repartidor.id}"/>
						</spring:url>
						<a href="${fn:escapeXml(detallesUrl)}" class="enlacea">Detalles del pedido</a>
					</td>
					<td>
						<spring:url value="/repartidores/{repartidorId}/repartos/{repartoId}/{pedidoId}/entregado" var="entregadoUrl">
							<spring:param name="pedidoId" value="${pedido.id}"/>
							<spring:param name="repartoId" value="${reparto.id}"/>
							<spring:param name="repartidorId" value="${repartidor.id}"/>
						</spring:url>
						<c:if test = "${pedido.estadopedido != 'Entregado'}">
							<a href="${fn:escapeXml(entregadoUrl)}" class="enlacea">Marcar como entregado</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form:form>
</currogas:layout>
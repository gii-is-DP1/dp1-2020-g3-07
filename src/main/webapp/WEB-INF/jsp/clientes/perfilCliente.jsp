<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<currogas:layout pageName="Mi Perfil">

	<h1>Mi Perfil</h1>
	<h2>Datos personales</h2>
		<table class="table table-striped">
			<tr>
				<th>Nombre y Apellidos</th>
				<th>Teléfono</th>
				<th>Dirección</th>
				<th>Email</th>
				<th>Fecha de nacimiento</th>
			</tr>
			<tr>
				<td><c:out value="${cliente.nombre} ${cliente.apellidos}"></c:out></td>
				<td><c:out value="${cliente.telefono}"></c:out></td>
				<td><c:out value="${cliente.direccion}"></c:out></td>
				<td><c:out value="${cliente.user.username}"></c:out></td>
				<td><c:out value="${cliente.fechanacimiento}"></c:out></td>
			</tr>
		</table>

		<div style="margin-bottom: 4vh;">
			<spring:url value = "/clientes/edit/{clienteId}" var = "clienteEditUrl">
				<spring:param name="clienteId" value="${cliente.id}"/>
            </spring:url>
            <a href = "${fn:escapeXml(clienteEditUrl)}" class="btn btn-default">Modificar mis datos</a>
		</div>

		<h2>Mis Pedidos</h2>
		<table class="table table-striped">
			<tr>
				<th>Fecha de realización</th>
				<th>Hora estimada</th>
				<th>Método de pago</th>
				<th>Estado</th>
				<th>Tipo de pedido</th>
				<th>Valoración</th>
				<th>Comentario</th>
				<th>Acciones</th>
			</tr>
			<c:forEach items="${pedidos}" var="pedido">
				<c:if test="${pedido.estadopedido != null || pedido.tipopedido != null}">
					<tr>
						<td><c:out value="${pedido.fecha}"/></td>
						<td><c:out value="${pedido.horaEstimada}"></c:out></td>
						<td><c:out value="${pedido.metodopago}"></c:out></td>
						<td><c:out value="${pedido.estadopedido}"></c:out></td>
						<td><c:out value="${pedido.tipopedido}"></c:out></td>
						<td><c:out value="${pedido.valoracion}"></c:out></td>
						<td><c:out value="${pedido.comentario}"></c:out></td>
						<td>
							<spring:url value = "/clientes/valorar/{pedidoId}" var = "valoracionUrl">
								<spring:param name = "pedidoId" value ="${pedido.id}"/>
							</spring:url>
							<c:if test="${empty pedido.valoracion || empty pedido.comentario}">
								<a href = "${fn:escapeXml(valoracionUrl)}">Realizar valoración</a>
							</c:if>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>	
</currogas:layout>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<currogas:layout pageName="pedidos">
	<div style="text-align: center;">
		<h1>Resumen del pedido</h1>	
		<div style="margin-top: 35px;">
			<c:forEach items="${lineapedido}" var="lineapedido">
				<h2 style="margin-top:35px;"><c:out value="${lineapedido.producto.name}"/>, Cantidad: <c:out value="${lineapedido.cantidad}"/>, Precio por unidad: <c:out value="${lineapedido.producto.precio}"/>€</h2>
				<spring:url value="edit/{pedidoID}/{lineapedidoID}/{productoID}" var="pedidoUrl">
					<spring:param name="pedidoID" value="${pedido.id}"/>
					<spring:param name="lineapedidoID" value="${lineapedido.id}"/>
					<spring:param name="productoID" value="${lineapedido.producto.id}"/>
				</spring:url>
				<a href="${fn:escapeXml(pedidoUrl)}" class="btn btn-default">Modificar cantidad</a>
				
				<spring:url value="delete/{pedidoID}/{lineapedidoID}" var="pedidoUrl">
					<spring:param name="pedidoID" value="${pedido.id}"/>
					<spring:param name="lineapedidoID" value="${lineapedido.id}"/>
				</spring:url>
				<a href="${fn:escapeXml(pedidoUrl)}" class="btn btn-default">Eliminar producto</a>
			</c:forEach>
		</div>
		<div style="margin-top:35px">
		<h2>Precio total del pedido: <c:out value="${precioTotal}"/>€</h2>
		<form:form modelAttribute="listaLineaPedidos" class="form-horizontal" id="add-lineaPedidos-form">
			<br><button class="btn btn-default" type="submit">Continuar con el pedido</button>
		</form:form>
	</div>
	</div>

</currogas:layout>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<petclinic:layout pageName="pedidos">
	<div>
		<h1 style="text-align: center;">Nuevo Pedido</h1>
		<div style="text-align: center;">
			<c:forEach items="${productos}" var="productos">
				<h2 style="margin-top: 35px;"><c:out value="${productos.name}"/>, <c:out value="${productos.precio}"/>€</h2>
					<spring:url value="{pedidoID}/{productoID}" var="lineaPedidoUrl">
						<spring:param name="pedidoID" value="${pedidos.id}"/>
						<spring:param name="productoID" value="${productos.id}"/>
					</spring:url>
					<a href="${fn:escapeXml(lineaPedidoUrl)}" class="btn btn-default">Añadir producto al carrito</a>
			</c:forEach>
		</div>
		<div style="text-align: center; margin-top:30px">
			<form:form modelAttribute="lineapedidos" class="form-horizontal" id="add-lineaPedidos-form">
				<br><button class="btn btn-default" type="submit">Continuar con el pedido</button>
			</form:form>
		</div>
	</div>
</petclinic:layout>
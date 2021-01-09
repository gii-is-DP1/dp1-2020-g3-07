<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<currogas:layout pageName="pedidos">
	<h2>Resumen del pedido</h2>	
	<c:forEach items="${lineapedido}" var="lineapedido">
		<h2><c:out value="${lineapedido.producto.name}"/>, Cantidad: <c:out value="${lineapedido.cantidad}"/>, Precio por unidad: <c:out value="${lineapedido.producto.precio}"/>$</h2>
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
       	
	<form:form modelAttribute="listaLineaPedidos" class="form-horizontal" id="add-lineaPedidos-form">
		<br><button class="btn btn-default" type="submit">Continuar con el pedido</button>
 	</form:form>
</currogas:layout>
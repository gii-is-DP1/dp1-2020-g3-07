<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>

<currogas:layout pageName="reparto">

<h2>Informacion del Reparto</h2>


	<%-- <table>
	
	
		<tr>
			<th>Fecha</th>
		</tr>
		
		<tr>
			<th>Hora de inicio</th>
		</tr>
		
		<tr>
			<c:if test="${reparto.horaFin!=null}"><th>Hora de fin</th></c:if>
		</tr>
		
		<tr>
			<th>Repartidor</th>
		</tr>
		
		<tr>
			<th>Vehiculos</th>
		</tr>
	
	</table> --%>
	
	
	<p>Fecha: <c:out value="${reparto.fecha}"></c:out></p>
	<p>Hora de inicio: <c:out value="${reparto.horaInicio}"></c:out></p>
	<c:if test="${reparto.horaFin!=null}"><p>Hora de fin: <c:out value="${reparto.horaFin}"></c:out></p></c:if>
	<p>Nombre del repartidor: <c:out value="${reparto.repartidor.nombre}"></c:out></p>
	<p>Pedidos asociados:</p>
	<c:forEach var="pedido" items="${reparto.pedidos}">
		<ul title="">
			<li>Nombre del cliente: <c:out value="${pedido.cliente.nombre} ${pedido.cliente.apellidos}"></c:out></li>
			<li>Estado del pedido: <c:out value="${pedido.estadopedido}"></c:out>
			<spring:url value="/pedidos/{pedidoId}/entregado" var="addUrl">
		        <spring:param name="pedidoId" value="${pedido.id}"/>
		    </spring:url>
		    <a href="${fn:escapeXml(addUrl)}" class="btn btn-outline-secondary">Marcar como entregado</a>
			</li>
		</ul>
		<br>
	</c:forEach>



</currogas:layout>
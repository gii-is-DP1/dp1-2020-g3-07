<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>

<currogas:layout pageName="cliente">

	<h2>Información del Cliente</h2>

		<table class="table table-striped">
			<tr>
				<th>Nombre</th>
				<th>Teléfono</th>
				<th>Dirección</th>
			</tr>
			<tr>
				<td><c:out value="${cliente.nombre} ${cliente.apellidos}"></c:out></td>
				<td><c:out value="${cliente.telefono}"></c:out></td>
				<td><c:out value="${cliente.direccion}"></c:out></td>
			</tr>
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
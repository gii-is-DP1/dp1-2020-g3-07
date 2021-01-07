<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>

<currogas:layout pageName="cliente">

	<h1>Mi Perfil</h1>
	<h3>Datos personales</h3>
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
		<div>
			<spring:url value = "/clientes/edit/{clienteId}" var = "clienteEditUrl">
				<spring:param name="clienteId" value="${cliente.id}"/>
            </spring:url>
            <a href = "${fn:escapeXml(clienteEditUrl)}">Modificar mis datos</a>
		</div>
</currogas:layout>
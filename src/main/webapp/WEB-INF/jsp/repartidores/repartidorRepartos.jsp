<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<currogas:layout pageName="repartidores">

    <h2>Información del Repartidor</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${repartidor.nombre}"/></b></td>
        </tr>
        <tr>
            <th>DNI</th>
            <td><c:out value="${repartidor.dni}"/></td>
        </tr>
        <tr>
            <th>Fecha de nacimiento</th>
            <td><c:out value="${repartidor.fechanacimiento}"/></td>
        </tr>
        <!-- <tr>
            <th>Telephone</th>
            <td><c:out value="${owner.telephone}"/></td>
        </tr> -->
    </table>
    
    
    
    <h2>Repartos del repartidor</h2>
    
    <c:if test="${fn:length(repartos)==0}">
    	<p>No hay repartos disponibles</p>
    </c:if>
    
    <c:if test="${fn:length(repartos)!=0}">
    <table class="table table-striped">
    
	    <tr>
	    	<th>Fecha</th>
	    	<th>Hora Inicio</th>
	    	<th></th>
	    </tr>
    
    	<c:forEach var="reparto" items="${repartos}">
    		<tr>
    		<td><c:out value="${reparto.fecha}"></c:out></td>
    		<td>
                <fmt:parseDate value="${reparto.horaInicio}" pattern="HH:mm" var="parsedDateTime" type="both" />
                <fmt:formatDate pattern="HH:mm" value="${ parsedDateTime }" />
            </td>
    		<td>
    		<spring:url value="/repartidores/{repartidorId}/repartos/{repartoId}" var="addUrl">
		        <spring:param name="repartoId" value="${reparto.id}"/>
		        <spring:param name="repartidorId" value="${reparto.repartidor.id}"/>
		    </spring:url>
		    <a href="${fn:escapeXml(addUrl)}" class="enlacea">Ver detalles</a>
    		</td>
    		</tr>
    	</c:forEach>
    
    </table>
    </c:if>
    

    <spring:url value="{repartidorId}/repartos/new" var="addUrl">
        <spring:param name="repartidorId" value="${repartidor.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Añadir nuevo Reparto</a>

    <!-- <br/>
    <br/>
    <br/>
    <h2>Pets and Visits</h2>

    <table class="table table-striped">
        <c:forEach var="pet" items="${owner.pets}">

            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Name</dt>
                        <dd><c:out value="${pet.name}"/></dd>
                        <dt>Birth Date</dt>
                        <dd><currogas:localDate date="${pet.birthDate}" pattern="yyyy-MM-dd"/></dd>
                        <dt>Type</dt>
                        <dd><c:out value="${pet.type.name}"/></dd>
                    </dl>
                </td>
                <td valign="top">
                    <table class="table-condensed">
                        <thead>
                        <tr>
                            <th>Visit Date</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <c:forEach var="visit" items="${pet.visits}">
                            <tr>
                                <td><currogas:localDate date="${visit.date}" pattern="yyyy-MM-dd"/></td>
                                <td><c:out value="${visit.description}"/></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/edit" var="petUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(petUrl)}">Edit Pet</a>
                            </td>
                            <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/visits/new" var="visitUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(visitUrl)}">Add Visit</a>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>

        </c:forEach>
    </table> -->

</currogas:layout>

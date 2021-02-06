<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="jquery.tablesorter.js"></script> 

<currogas:layout pageName="pedidos">
	<sec:authorize access="hasAuthority('admin') or hasAuthority('dependiente')">
    <h1>Pedidos A Domicilio</h1>
    <table id="pedidosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Fecha y hora</th>
            <th>Comentario</th>
            <th>Valoración</th>
            <th>Hora Estimada</th>
            <th>Método de pago</th>
            <th>Estado de pedido</th>
            <th>Contenido del pedido</th>
            <!-- <th>Acciones</th>      -->
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pedidosADomicilio}" var="pedidosadom">
            <tr>
                <td>
                    <fmt:parseDate value="${pedidosadom.fecha}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                    <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${ parsedDateTime }" />
                </td>
                <td>
                    <c:out value="${pedidosadom.comentario}"/>
                </td>
                <td>
                    <c:out value="${pedidosadom.valoracion}"/>
                </td>
                <td>
                    <fmt:parseDate value="${pedidosadom.horaEstimada}" pattern="HH:mm" var="parsedDateTime" type="both" />
                    <fmt:formatDate pattern="HH:mm" value="${ parsedDateTime }" />
                </td>
                <td>
                    <c:out value="${pedidosadom.metodopago}"/>
                </td>
                <td>
                    <c:out value="${pedidosadom.estadopedido}"/>
                </td>
                <td>
                	<spring:url value="pedidos/detalles/{pedidoID}" var="pedidodomUrl">
       				<spring:param name="pedidoID" value="${pedidosadom.id}"/>
    				</spring:url>
    				<a href="${fn:escapeXml(pedidodomUrl)}" >Detalles pedido</a>
                </td>       
                 <!-- <td>            
                    <spring:url value="pedidos/delete/{pedidoID}" var="delpedidodomUrl">
                        <spring:param name="pedidoID" value="${pedidosadom.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(delpedidodomUrl)}" >Eliminar pedido</a>
                </td>             -->
            </tr>
        </c:forEach>
        </tbody>
    </table>  
    
    
    
    <h1>Pedidos En Local</h1>
    <table id="pedidosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Fecha y hora</th>
            <th>Comentario</th>
            <th>Valoración</th>
            <th>Hora Estimada</th>
            <th>Método de pago</th>
            <th>Estado de pedido</th>
            <th>Contenido del pedido</th>
            <!-- <th>Acciones</th>       -->
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pedidosEnLocal}" var="pedidosenloc">
            <tr>
                <td>
                    <fmt:parseDate value="${pedidosenloc.fecha}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                    <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${ parsedDateTime }" />
                </td>
                <td>
                    <c:out value="${pedidosenloc.comentario}"/>
                </td>
                <td>
                    <c:out value="${pedidosenloc.valoracion}"/>
                </td>
                <td>
                    <fmt:parseDate value="${pedidosenloc.horaEstimada}" pattern="HH:mm" var="parsedDateTime" type="both" />
                    <fmt:formatDate pattern="HH:mm" value="${ parsedDateTime }" />
                </td>
                <td>
                    <c:out value="${pedidosenloc.metodopago}"/>
                </td>
                <td>
                    <c:out value="${pedidosenloc.estadopedido}"/>
                </td>
                <td>
                	<spring:url value="pedidos/detalles/{pedidoID}" var="pedidolocUrl">
       				<spring:param name="pedidoID" value="${pedidosenloc.id}"/>
    				</spring:url>
    				<a href="${fn:escapeXml(pedidolocUrl)}" >Detalles pedido</a>
                </td>
                <!-- <td>
                	<spring:url value="pedidos/delete/{pedidoID}" var="delpedidolocUrl">
       				<spring:param name="pedidoID" value="${pedidosenloc.id}"/>
    				</spring:url>
    				<a href="${fn:escapeXml(delpedidolocUrl)}" >Eliminar pedido</a>
                </td>             -->
            </tr>
        </c:forEach>
        </tbody>
    </table> 
    </sec:authorize>    
    
    <center><a class="btn btn-default" href='<spring:url value="/pedidos/new" htmlEscape="true"/>'>Nuevo pedido</a></center>
</currogas:layout>

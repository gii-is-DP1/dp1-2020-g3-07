<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>

<currogas:layout pageName="repartidores">
    <h2>Pedidos a asignar</h2>

    <table id="pedidosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">id</th>
            <th>Fecha y hora</th>
            <th>comentario</th>
            <th>valoracion</th>
            <th>Metodo de pago</th>
            <th>Estado de pedido</th>
            <th>Tipo de pedido</th>
            <th>Acciones</th>
            
         
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pedidos}" var="pedidos">
            <tr>
                <td>
                    <c:out value="${pedidos.id}"/>
                </td>
                <td>
                    <c:out value="${pedidos.fecha}"/>
                </td>
                <td>
                    <c:out value="${pedidos.comentario}"/>
                </td>
                <td>
                    <c:out value="${pedidos.valoracion}"/>
                </td>
                <td>
                    <c:out value="${pedidos.metodopago}"/>
                </td>
                <td>
                    <c:if test="${pedidos.estadopedido == 'pendiente'}">
                        <c:out value="${pedidos.estadopedido}"/>
                    </c:if>
                </td>
                <td>
                    <c:out value="${pedidos.tipopedido}"/>
                </td>
                <td>
                    <spring:url value="{repartidorID}/reparto/new" var="pedidoUrl">
       				 <spring:param name="pedidoID" value="${pedidos.id}"/>
    				</spring:url>
    				<a href="${fn:escapeXml(pedidoUrl)}" class="btn btn-default">Asignar</a>
                </td>       
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- <table class="table table-striped">
        <c:forEach var="reparto" items="${repartidor.reparto}">
            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Pedidos</dt>
                        <dd><c:out value="${reparto.pedidos}"/></dd>
                    </dl>
                </td>
            </tr>

        </c:forEach>
    </table> -->
</currogas:layout>
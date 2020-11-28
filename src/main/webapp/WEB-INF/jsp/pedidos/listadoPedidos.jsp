<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="pedidos">
    <h2>Pedidos</h2>

    <table id="pedidosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">id</th>
            <th>comentario</th>
            <th>valoracion</th>
            <th>Metodo de pago</th>
            <th>Estado de pedido</th>
            
         
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pedidos}" var="pedido">
            <tr>
                <td>
                    <c:out value="${pedido.id}"/>
                </td>
                <td>
                    <c:out value="${pedido.comentario}"/>
                </td>
                <td>
                    <c:out value="${pedido.valoracion}"/>
                </td>
                <td>
                    <c:out value="${pedido.metodopago}"/>
                </td>
                <td>
                    <c:out value="${pedido.estadopedido}"/>
                </td>
          


                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
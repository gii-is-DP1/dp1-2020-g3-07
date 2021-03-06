<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<petclinic:layout pageName="pedidos">
    
    <h2>Finaliza tu pedido</h2>   
    <form:form modelAttribute="pedidos" class="form-horizontal" id="add-pedidos-form">
        <div class="form-group has-feedback">
        	<form:input type="hidden" path="cliente"/>
            <form:input type="hidden" path="lineaPedidos"/>
            <petclinic:selectField label="Metodo de pago " name="metodopago" names="${metodoDePago}" size="2"/>
            <petclinic:selectField label="Tipo de pedido " name="tipopedido" names="${tipoDePedido}" size="2"/>
           	<c:choose>
    			<c:when test="${pedidos.cliente.id=='1'}">
					  <petclinic:inputField label="Nombre del cliente" name="nombreClienteGenerico"/>
					  <petclinic:inputField label="Telefono del cliente" name="telefonoClienteGenerico"/>
					  <petclinic:inputField label="Direccion de entrega del cliente" name="direccionClienteGenerico"/>
    			</c:when>    
			</c:choose>
            <p><strong>Hora de llegada: </strong><select label="horaEstimada" name ="horaEstimada">
                <option value="03:00:00">Lo antes posible</option>
                <option value="03:00:01">En 1 hora</option>
                <option value="03:00:02">En 1 hora y media</option>
                <option value="03:00:03">En 2 horas</option>
            </select></p>
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
               <button class="btn btn-default" type="submit">Finalizar pedido</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>
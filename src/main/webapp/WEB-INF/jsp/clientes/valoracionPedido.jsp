<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<currogas:layout pageName="Mi Perfil">
    <h2>Valoración</h2>
    <form:form modelAttribute="pedido" class="form-horizontal" id="add-dependiente-form">
        <!-- <form:input type="hidden" path="fecha"/>
        <form:input type="hidden" path="horaEstimada"/>
        <form:input type="hidden" path="metodopago"/>
        <form:input type="hidden" path="estadopedido"/>
        <form:input type="hidden" path="tipopedido"/>
        <form:input type="hidden" path="reparto"/>
        <form:input type="hidden" path="cliente"/>
        <form:input type="hidden" path="lineaPedidos"/> -->

        <div class="form-group has-feedback">
            <div style="display: none;">
                <currogas:inputField label="fecha" name="fecha"/>
                <currogas:inputField label="horaEstimada" name="horaEstimada"/>
                <currogas:inputField label="metodopago" name="metodopago"/>
                <currogas:inputField label="estadopedido" name="estadopedido"/>
                <currogas:inputField label="tipopedido" name="tipopedido"/>
                <currogas:inputField label="lineaPedidos" name="lineaPedidos"/>
                <currogas:inputField label="cliente" name="cliente"/> 
            </div>

            <currogas:inputField label="Valoración" name="valoracion"/>
            <currogas:inputField label="Comentario" name="comentario"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Realizar valoración</button>
            </div>
        </div>
    </form:form>
</currogas:layout>

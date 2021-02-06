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
    <form:form modelAttribute="lineapedidos" class="form-horizontal" id="add-pedidos-form">
        <div class="form-group has-feedback">
            <petclinic:selectField label="cantidad" name="cantidad" names="${cantidadProducto}" size="6"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
               <button class="btn btn-default" type="submit">Modificar la cantidad</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>
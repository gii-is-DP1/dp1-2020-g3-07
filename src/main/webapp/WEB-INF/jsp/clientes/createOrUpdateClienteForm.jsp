<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<currogas:layout pageName="Mi Perfil">
    <h2>Cliente</h2>
    <form:form modelAttribute="cliente" class="form-horizontal" id="add-dependiente-form">
        <div class="form-group has-feedback">
            <form:input type="hidden" path="user"/>
            <currogas:inputField label="Nombre" name="nombre"/>
            <currogas:inputField label="Apellidos" name="apellidos"/>
            <currogas:inputField label="Teléfono" name="telefono"/>
            <currogas:inputField label="Direccion" name="direccion"/>
            <currogas:inputField label="Fecha de nacimiento" name="fechanacimiento"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Actualizar datos cliente</button>
            </div>
        </div>
    </form:form>
</currogas:layout>

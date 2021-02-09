<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<currogas:layout pageName="productos">
    <h2>
        <c:if test="${producto['new']}">Nuevo</c:if> Producto
    </h2>
    <form:form modelAttribute="producto" class="form-horizontal" id="add-productos-form">
        <div class="form-group has-feedback">
            <currogas:inputField label="Nombre" name="name"/>
            <currogas:inputField label="Precio" name="precio"/>
            <currogas:inputField label="Descripción" name="descripcion"/>
        </div>

        <form:checkboxes items="${alergenos}" path="alergenos" name = "alergenos"  itemLabel="alergenotype"/>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${producto['new']}">
                        <button class="btn btn-default" type="submit">Anadir productos</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar producto</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</currogas:layout>

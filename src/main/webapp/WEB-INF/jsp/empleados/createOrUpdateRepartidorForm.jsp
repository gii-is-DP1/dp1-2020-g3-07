<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<currogas:layout pageName="empleados">
	<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fechanacimiento").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
    <h2>
        <c:if test="${repartidor['new']}">Nuevo</c:if> Repartidor
    </h2>
    <form:form modelAttribute="repartidor" class="form-horizontal" id="add-repartidor-form">
        <div class="form-group has-feedback">
        	<input type="hidden" name="id" value="${repartidor.id}"/>
            <currogas:inputField label="Nombre" name="nombre"/>
            <currogas:inputField label="DNI" name="dni"/>
            <currogas:inputField label="Sueldo" name="sueldo"/>
            <currogas:inputField label="Fecha de Nacimiento" name="fechanacimiento"/>
            <%-- <form:input type="date" path="fechanacimiento"/> --%>
            <div class="control-group">
                <currogas:selectField name="vehiculo" label="Vehiculo asignado " names="${vehiculos}" size="5"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${repartidor['new']}">
                        <button class="btn btn-default" type="submit">Añadir repartidor</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar datos repartidor</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
    </jsp:body>
</currogas:layout>

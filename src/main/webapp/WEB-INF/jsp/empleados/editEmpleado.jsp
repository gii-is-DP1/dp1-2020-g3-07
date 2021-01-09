<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h2>Empleados</h2>
        <form:form modelAttribute="empleados" class="form-horizontal">
            <div class="form-group has-feedback">
                <currogas:inputField label="nombre" name="nombre"/>
                <currogas:inputField label="dni" name="dni"/>
                <currogas:inputField label="sueldo" name="sueldo"/>
                <currogas:inputField label="fechanacimiento" name="fechanacimiento"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="id" value="${empleados.id}"/>
                    <button class="btn btn-default" type="submit">Edit empleado</button>
                </div>
            </div>
        </form:form>

        
    </jsp:body>

</currogas:layout>

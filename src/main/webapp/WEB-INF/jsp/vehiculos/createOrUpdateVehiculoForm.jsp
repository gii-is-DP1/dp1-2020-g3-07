<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<currogas:layout pageName="vehiculos">
    <h2>
        <c:if test="${vehiculo['new']}">Nuevo</c:if> Vehiculo
    </h2>
    <form:form modelAttribute="vehiculo" class="form-horizontal" id="add-vehiculos-form">
        <div class="form-group has-feedback">
            <currogas:inputField label="Matricula" name="matricula"/>
            <%-- <currogas:inputField label="tipovehiculo" name="tipovehiculo"/> --%>
            <div class="control-group">
                <currogas:selectField name="tipovehiculo" label="Tipo de vehiculo " names="${tiposVehiculo}" size="2"/>
            </div>
            <%-- <div class="col-sm-10">
	            <p><strong>Tipo de vehiculo </strong><select name ="tipovehiculo">
	            	<c:forEach items="${tiposVehiculo}" var="tipo">
	            		<option value="tipo">${tipo }</option>
	            	</c:forEach>
	            </select></p>
            </div> --%>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${vehiculo['new']}">
                        <button class="btn btn-default" type="submit">Añadir vehiculo</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar vehiculo</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</currogas:layout>

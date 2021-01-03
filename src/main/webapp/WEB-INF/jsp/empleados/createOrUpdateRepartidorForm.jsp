<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>

<currogas:layout pageName="repartidores">
    <h2>
        <c:if test="${repartidor['new']}">Nuevo</c:if> Repartidor
    </h2>
    <form:form modelAttribute="repartidor" class="form-horizontal" id="add-repartidor-form">
        <div class="form-group has-feedback">
            <currogas:inputField label="nombre" name="nombre"/>
            <currogas:inputField label="dni" name="dni"/>
            <currogas:inputField label="sueldo" name="sueldo"/>
            <currogas:inputField label="fechanacimiento" name="fechanacimiento"/>
            <currogas:inputField label="usuario" name="usuario"/>
            <currogas:inputField label="contrasena" name="contrasena"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${repartidor['new']}">
                        <button class="btn btn-default" type="submit">A�adir repartidor</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar datos repartidor</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</currogas:layout>

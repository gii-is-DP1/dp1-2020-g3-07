<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="dependientes">
    <h2>
        <c:if test="${dependiente['new']}">Nuevo</c:if> Dependiente
    </h2>
    <form:form modelAttribute="dependiente" class="form-horizontal" id="add-dependiente-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="nombre" name="nombre"/>
            <petclinic:inputField label="dni" name="dni"/>
            <petclinic:inputField label="sueldo" name="sueldo"/>
            <petclinic:inputField label="fechanacimiento" name="fechanacimiento"/>
            <petclinic:inputField label="usuario" name="usuario"/>
            <petclinic:inputField label="contrasena" name="contrasena"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${dependiente['new']}">
                        <button class="btn btn-default" type="submit">A�adir dependiente</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar datos dependiente</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>

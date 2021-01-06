<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>

<currogas:layout pageName="dependientes">
    <h2>
        <c:if test="${cocinero['new']}">Nuevo</c:if> Cocinero
    </h2>
    <form:form modelAttribute="cocinero" class="form-horizontal" id="add-cocinero-form">
        <div class="form-group has-feedback">
            <currogas:inputField label="nombre" name="nombre"/>
            <currogas:inputField label="dni" name="dni"/>
            <currogas:inputField label="sueldo" name="sueldo"/>
            <currogas:inputField label="fechanacimiento" name="fechanacimiento"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${cocinero['new']}">
                        <button class="btn btn-default" type="submit">A�adir cocinero</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar datos cocinero</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</currogas:layout>
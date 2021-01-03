<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>

<currogas:layout pageName="producto">
    <h2>
        <c:if test="${productos['new']}">Nuevo</c:if> Producto
    </h2>
    <form:form modelAttribute="productos" class="form-horizontal" id="add-productos-form">
        <div class="form-group has-feedback">
            <currogas:inputField label="name" name="name"/>
            <currogas:inputField label="precio" name="precio"/>
            <currogas:inputField label="tamanopizza" name="tamanopizza"/>
            <currogas:selectField label="alergenos" name="alergenos" names = "${alergenotype}" size="5"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${productos['new']}">
                        <button class="btn btn-default" type="submit">Añadir productos</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar producto</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</currogas:layout>

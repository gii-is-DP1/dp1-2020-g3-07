<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>

<currogas:layout pageName="pedido">
    <h2>
        <c:if test="${pedidos['new']}">Nuevo</c:if> Pedido
    </h2>
    <form:form modelAttribute="pedidos" class="form-horizontal" id="add-pedidos-form">
        <div class="form-group has-feedback">
            <currogas:inputField label="comentario" name="comentario"/>
            <currogas:inputField label="valoracion" name="valoracion"/>
            <currogas:inputField label="metodopago" name="metodopago"/>
            <currogas:inputField label="estadopedido" name="estadopedido"/>
            <currogas:inputField label="tipopedido" name="tipopedido"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${pedidos['new']}">
                        <button class="btn btn-default" type="submit">A�adir pedidos</button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </form:form>
</currogas:layout>

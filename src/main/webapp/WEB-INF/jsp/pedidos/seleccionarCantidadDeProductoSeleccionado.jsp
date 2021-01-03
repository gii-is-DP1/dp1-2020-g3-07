<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="lineapedido">
    <h2>Nueva linea de Pedido</h2>
    <h2><c:out value="${productos.name}, ${productos.precio}"/>$</h2>
	<form:form modelAttribute="lineapedidos" class="form-horizontal" id="add-lineaPedidos-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="cantidad" name="cantidad"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-default" type="submit">Añadir linea pedidos</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>
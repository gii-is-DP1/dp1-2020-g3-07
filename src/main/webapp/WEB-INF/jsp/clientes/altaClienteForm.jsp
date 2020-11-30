<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="clientes">
    <h2>
        <c:if test="${clientes['new']}">Nuevo</c:if> Cliente
    </h2>
     <form:form modelAttribute="clientes" class="form-horizontal" id="add-clientes-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="name" name="name"/>
        
            <petclinic:inputField label="telefono" name="telefono"/>
            <petclinic:inputField label="direccion" name="direccion"/>
            <petclinic:inputField label="usuario" name="usuario"/>
             <petclinic:inputField label="contrasena" name="contrasena"/>
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${clientes['new']}">
                        <button class="btn btn-default" type="submit">Registrar cliente</button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>

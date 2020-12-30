<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>

<currogas:layout pageName="clientes">
	<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fechanacimiento").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
    <h2>
        <c:if test="${cliente['new']}">Nuevo</c:if> Cliente
    </h2>
     <form:form modelAttribute="cliente" class="form-horizontal" id="add-cliente-form">
        <div class="form-group has-feedback">
            <currogas:inputField label="Nombre" name="nombre"/>
            <currogas:inputField label="Apellidos" name="apellidos"/>
            <currogas:inputField label="Fecha de nacimiento" name="fechanacimiento"/>
            <currogas:inputField label="Direcci�n" name="direccion"/>
            <currogas:inputField label="Tel�fono" name="telefono"/>
            <currogas:inputField label="Email" name="user.username"/>
            <currogas:inputField label="Contrase�a" name="user.password"/>
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${cliente['new']}">
                        <button class="btn btn-default" type="submit">Registrar cliente</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar datos cliente</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
    </jsp:body>
</currogas:layout>

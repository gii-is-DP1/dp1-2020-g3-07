<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="empleados">
    <%-- <h2>Empleados</h2>

    <table id="empleadosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 200px;">DNI</th>
            <th>Sueldo</th>
            <th>fechanacimiento</th>
            <th>Accion</th>
         
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${empleados}" var="empleado">
            <tr>
                <td>
                    <c:out value="${empleado.nombre}"/>
                </td>
                <td>
                    <c:out value="${empleado.dni}"/>
                </td>
                <td>
                    <c:out value="${empleado.sueldo}"/>
                </td>
                <td>
                    <c:out value="${empleado.fechanacimiento}"/>
                </td>
                <td>
                    <spring:url value = "/empleados/save/{empleadoId}" var = "empleadoUrl">
                    	<spring:param name = "empleadoId" value ="${empleado.id}"/>
                    </spring:url>
                    <a href = "${fn:escapeXml(empleadoUrl)}">Edit</a>
                </td>
                
                
                

                
            </tr>
        </c:forEach>
        </tbody>
    </table> --%>
    
    
    
    <h2>Dependientes</h2>
    
    <table id="dependientesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 200px;">DNI</th>
            <th>Sueldo</th>
            <th>Fecha de nacimiento</th>
            <th>Accion</th>
         
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dependientes}" var="dependiente">
            <tr>
                <td>
                    <c:out value="${dependiente.nombre}"/>
                </td>
                <td>
                    <c:out value="${dependiente.dni}"/>
                </td>
                <td>
                    <c:out value="${dependiente.sueldo}"/>
                </td>
                <td>
                    <c:out value="${dependiente.fechanacimiento}"/>
                </td>
                <td>
                    <spring:url value = "/dependientes/save/{dependienteId}" var = "dependienteUrl">
                    	<spring:param name = "dependienteId" value ="${dependiente.id}"/>
                    </spring:url>
                    <a href = "${fn:escapeXml(dependienteUrl)}">Edit</a>
                </td>
                
                
                

                
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    
    
    
    
    
    
    
    <h2>Repartidores</h2>
    
    <table id="dependientesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 200px;">DNI</th>
            <th>Sueldo</th>
            <th>Fecha de nacimiento</th>
            <th>Accion</th>
         
        </tr>
        </thead>
        <tbody>
        <%-- <c:forEach items="${dependientes}" var="dependiente">
            <tr>
                <td>
                    <c:out value="${dependiente.nombre}"/>
                </td>
                <td>
                    <c:out value="${dependiente.dni}"/>
                </td>
                <td>
                    <c:out value="${dependiente.sueldo}"/>
                </td>
                <td>
                    <c:out value="${dependiente.fechanacimiento}"/>
                </td>
                <td>
                    <spring:url value = "/dependientes/save/{dependienteId}" var = "dependienteUrl">
                    	<spring:param name = "dependienteId" value ="${dependiente.id}"/>
                    </spring:url>
                    <a href = "${fn:escapeXml(dependienteUrl)}">Edit</a>
                </td>
                
                
                

                
            </tr>
        </c:forEach> --%>
        </tbody>
    </table>
    
    
    
    
    
    
    
    
    <h2>Cocineros</h2>
    
    <table id="dependientesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 200px;">DNI</th>
            <th>Sueldo</th>
            <th>Fecha de nacimiento</th>
            <th>Accion</th>
         
        </tr>
        </thead>
        <tbody>
        <%-- <c:forEach items="${dependientes}" var="dependiente">
            <tr>
                <td>
                    <c:out value="${dependiente.nombre}"/>
                </td>
                <td>
                    <c:out value="${dependiente.dni}"/>
                </td>
                <td>
                    <c:out value="${dependiente.sueldo}"/>
                </td>
                <td>
                    <c:out value="${dependiente.fechanacimiento}"/>
                </td>
                <td>
                    <spring:url value = "/dependientes/save/{dependienteId}" var = "dependienteUrl">
                    	<spring:param name = "dependienteId" value ="${dependiente.id}"/>
                    </spring:url>
                    <a href = "${fn:escapeXml(dependienteUrl)}">Edit</a>
                </td>
                
                
                

                
            </tr>
        </c:forEach> --%>
        </tbody>
    </table>
    
    
    
    
    
    
    
</petclinic:layout>

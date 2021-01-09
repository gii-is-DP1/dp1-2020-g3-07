<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<currogas:layout pageName="empleados">
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
            <th>Nombre</th>
            <th>DNI</th>
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
                    <spring:url value = "/dependientes/edit/{dependienteId}" var = "dependienteUrl1">
                    	<spring:param name = "dependienteId" value ="${dependiente.id}"/>
                    </spring:url>
                    <a href = "${fn:escapeXml(dependienteUrl1)}">Editar</a>
                    
                    <spring:url value="dependientes/delete/{dependienteId}" var="dependienteUrl2">
       				 <spring:param name="dependienteId" value="${dependiente.id}"/>
    				</spring:url>
    				<a href="${fn:escapeXml(dependienteUrl2)}" >Eliminar</a>
                </td>
                
                
                

                
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <a class="btn btn-default" href='<spring:url value="/dependientes/new" htmlEscape="true"/>'>A�adir dependiente</a>
    
    
    
    
    
    
    <h2>Repartidores</h2>
    
    <table id="repartidoresTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>DNI</th>
            <th>Sueldo</th>
            <th>Fecha de nacimiento</th>
            <th>Matricula de su vehiculo</th>
            <th>Accion</th>
         
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${repartidores}" var="repartidor">
            <tr>
                <td>
                    <c:out value="${repartidor.nombre}"/>
                </td>
                <td>
                    <c:out value="${repartidor.dni}"/>
                </td>
                <td>
                    <c:out value="${repartidor.sueldo}"/>
                </td>
                <td>
                    <c:out value="${repartidor.fechanacimiento}"/>
                </td>
                <td>
                    <c:out value="${repartidor.vehiculo.matricula}"/>
                </td>
                <td>
                    <spring:url value = "/repartidores/edit/{repartidorId}" var = "repartidorUrl1">
                    	<spring:param name = "repartidorId" value ="${repartidor.id}"/>
                    </spring:url>
                    <a href = "${fn:escapeXml(repartidorUrl1)}">Editar</a>
                    
                    <spring:url value="repartidores/delete/{repartidorId}" var="repartidorUrl2">
       				 <spring:param name="repartidorId" value="${repartidor.id}"/>
    				</spring:url>
    				<a href="${fn:escapeXml(repartidorUrl2)}" >Eliminar</a>
                </td>
                
                
                

                
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <a class="btn btn-default" href='<spring:url value="/repartidores/new" htmlEscape="true"/>'>A�adir repartidor</a>
    
    
    
    
    
    
    <h2>Cocineros</h2>
    
    <table id="cocinerosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>DNI</th>
            <th>Sueldo</th>
            <th>Fecha de nacimiento</th>
            <th>Accion</th>
         
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cocineros}" var="cocinero">
            <tr>
                <td>
                    <c:out value="${cocinero.nombre}"/>
                </td>
                <td>
                    <c:out value="${cocinero.dni}"/>
                </td>
                <td>
                    <c:out value="${cocinero.sueldo}"/>
                </td>
                <td>
                    <c:out value="${cocinero.fechanacimiento}"/>
                </td>
                <td>
                    <spring:url value = "/cocineros/edit/{cocineroId}" var = "cocineroUrl1">
                    	<spring:param name = "cocineroId" value ="${cocinero.id}"/>
                    </spring:url>
                    <a href = "${fn:escapeXml(cocineroUrl1)}">Editar</a>
                    
                    <spring:url value="cocineros/delete/{cocineroId}" var="cocineroUrl2">
       				 <spring:param name="cocineroId" value="${cocinero.id}"/>
    				</spring:url>
    				<a href="${fn:escapeXml(cocineroUrl2)}" >Eliminar</a>
                </td>
                
                
                

                
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <a class="btn btn-default" href='<spring:url value="/cocineros/new" htmlEscape="true"/>'>A�adir cocinero</a>
    
    
    
    
    
</currogas:layout>

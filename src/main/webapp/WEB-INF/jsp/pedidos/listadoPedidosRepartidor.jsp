<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>

<currogas:layout pageName="pedidosAsignarRepartidor">
    <h2>Pedidos a asignar</h2>

    <%-- <table id="pedidosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">id</th>
            <th>Fecha y hora</th>
            <th>comentario</th>
            <th>valoracion</th>
            <th>Metodo de pago</th>
            <th>Estado de pedido</th>
            <th>Tipo de pedido</th>
            <th>Acciones</th>
            
         
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pedidosSinAsignar}" var="pedidos">
            <tr>
                <td>
                    <c:out value="${pedidos.id}"/>
                </td>
                <td>
                    <c:out value="${pedidos.fecha}"/>
                </td>
                <td>
                    <c:out value="${pedidos.comentario}"/>
                </td>
                <td>
                    <c:out value="${pedidos.valoracion}"/>
                </td>
                <td>
                    <c:out value="${pedidos.metodopago}"/>
                </td>
                <td>
                    <c:out value="${pedidos.estadopedido}"/>
                </td>
                <td>
                    <c:out value="${pedidos.tipopedido}"/>
                </td>
                <td>
                    <spring:url value="pedidos/delete/{pedidoID}" var="pedidoUrl">
       				 <spring:param name="pedidoID" value="${pedidos.id}"/>
    				</spring:url>
    				<a href="${fn:escapeXml(pedidoUrl)}" class="btn btn-default">Eliminar pedido</a>
                </td>
          


                
            </tr>
        </c:forEach>
        </tbody>
    </table> --%>
    
    
    
    
    <form:form class="form-horizontal">
    
    <table>
    
    <tr>
    	<td><form:label path = "pedidosAsignados">Seleccione los pedidos del reparto: </form:label></td>
    	<td><form:checkboxes items = "${pedidosList}" path = "pedidosAsignados" /></td>
    </tr>
    
    <tr>
       <td colspan = "2">
          <input type = "submit" value = "Submit"/>
       </td>
    </tr>
    
    </table>
    
    </form:form>
    
    
    
    
    
    <%-- <form:form modelAttribute="pedidosSinAsignar">
      <table>
          <tr>
              
              
              <td>
                  Property is of an array or of type java.util.Collection
                  <form:checkboxes path="conjuntoPedidos.pedidosAsignados" items="${mapaPedidos}"/>
              </td>
              
              
                  Approach 2: Property is of an array or of type java.util.Collection
                  Quidditch: <form:checkbox path="preferences.interests" value="Quidditch"/>
                  Herbology: <form:checkbox path="preferences.interests" value="Herbology"/>
                  Defence Against the Dark Arts: <form:checkbox path="preferences.interests"
                      value="Defence Against the Dark Arts"/>
                      
                  <c:forEach items="${pedidosSinAsignar}" var="pedido">
                  
                  <td>
                  
                  ${pedido.fecha} ${pedido.metodopago} <form:checkbox path="ConjuntoPedidos.pedidosAsignados" value="${pedido}"/>
                  
                  ${pedido.fecha} ${pedido.metodopago} <form:checkbox path="conjuntoPedidos.pedidosAsignados" value="${pedido.id}"/>
                  
                  </td>
                  
                  </c:forEach>
                      
              <td>
                  <input type="submit" value="Save Changes" />
              </td>
              
              
          </tr>
      </table>
  </form:form> --%>
    
    
    
    
    
    <%-- <a class="btn btn-default" href='<spring:url value="/pedidos/new" htmlEscape="true"/>'>A�adir pedido</a> --%>
    
    
</currogas:layout>
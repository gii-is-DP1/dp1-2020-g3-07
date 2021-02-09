<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<%@page pageEncoding="UTF-8"%>

<currogas:layout pageName="error">

    	<spring:url value="/resources/images/pizza.png" var="pizza" />
        <center><img width="50%" src="${pizza}" /></center>
        <br>
        <br>
        <br>

        <center><h2>¡Ups! Algo ha salido mal, pero no te vayas, tu pizza sigue ahí</h2></center>

        <p>${exception.message}</p>

</currogas:layout>
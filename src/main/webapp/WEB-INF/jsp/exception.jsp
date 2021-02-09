<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>

<currogas:layout pageName="error">

    <spring:url value="/resources/images/error.png" var="errorImage"/>
    <img src="${errorImage}"/>

    <h2>Something happened...</h2>

    <p>${exception.message}</p>

</currogas:layout>

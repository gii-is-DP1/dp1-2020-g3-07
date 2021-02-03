<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="currogas" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<currogas:layout pageName="home">
    <div class="infohome">
        <h1>Bienvenido a Curro Gas!</h1>
        <h2> Proyecto ${title}</h2>
        <p><h2> Grupo ${group}</h2></p>
        <p><ul>
        <c:forEach items="${persons}" var="person">
            <li><span>${person.firstName}</span> <span>${person.lastName}</span></li>
        </c:forEach>
        </ul></p>
        <img src="https://www.us.es/sites/default/files/logoPNG_3.png"/>
    </div>
    <div class="intro">
        <div class="intro-text">
            <h1 style="color:#91B8EB; font-size: 5rem;line-height: 50px;" class="oculto">
                <span class="text">Bienvenido</span>
            </h1>
            <h1 style="color:#91B8EB; font-size: 5rem;line-height: 50px;" class="oculto">
                <span class="text">a Curro Gas</span>
            </h1>

        </div>
    </div>
    <div class="slider"></div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.6.0/gsap.min.js" 
    integrity="sha512-1dalHDkG9EtcOmCnoCjiwQ/HEB5SDNqw8d4G2MKoNwjiwMNeBAkudsBCmSlMnXdsH8Bm0mOd3tl/6nL5y0bMaQ==" 
    crossorigin="anonymous"></script>
    <script>
        const tl = gsap.timeline({defaults: {ease: "power1.out"}});

        tl.to('.text', {y:'0%', duration: 1, stagger: 0.25});
        tl.to('.slider', {y:'-100%', duration: 1.5, delay: 0.5});
        tl.to('.intro', {y: '-100%', duration: 1}, '-=1');
        tl.fromTo('nav', {opacity: 0}, {opacity: 1, duration: 1});
        tl.fromTo('.infohome', {opacity: 0}, {opacity: 1, duration: 1}, '-=1');
    </script>
    
</currogas:layout>

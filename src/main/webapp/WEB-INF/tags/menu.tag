<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>
<section class="landing">
	<nav class="navbar navbar-default" role="navigation">
		<div class="container">
			<div class="navbar-header" style="margin-right:20px">
				<img src="/resources/images/logo_sinfondo.png" class="logo-navbar">
				<h1 id="logo-text">CURRO GAS</h1>
		<!--	<a class="navbar-brand"
					href="<spring:url value="/" htmlEscape="true" />"><span></span></a> -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#main-navbar">
					<span class="sr-only"><os-p>Toggle navigation</os-p></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="navbar-collapse collapse" style="margin-top:10px" id="main-navbar">
				<ul class="nav navbar-nav">
					
					<petclinic:menuItem active="${name eq 'home'}" url="/"
						title="home page">
						<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
						<span>Home</span>
					</petclinic:menuItem>
					
					<sec:authorize access="hasAuthority('admin')">
					<petclinic:menuItem active="${name eq 'clientes'}" url="/clientes"
						title="clientes">
						<span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span>
						<span>Clientes</span>
					</petclinic:menuItem>
					</sec:authorize>
					
					<sec:authorize access="hasAuthority('admin')">
					<petclinic:menuItem active="${name eq 'empleados'}" url="/empleados"
						title="empleados">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-person" viewBox="0 0 16 16"><path d="M12 1a1 1 0 0 1 1 1v10.755S12 11 8 11s-5 1.755-5 1.755V2a1 1 0 0 1 1-1h8zM4 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H4z"/><path d="M8 10a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/></svg>
						<span>Empleados</span>
					</petclinic:menuItem>
					</sec:authorize>
					
					<sec:authorize access="hasAuthority('admin') or hasAuthority('dependiente') or hasAuthority('cliente')">
					<petclinic:menuItem active="${name eq 'pedidos'}" url="/pedidos"
						title="pedidos">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-basket" viewBox="0 0 16 16">
  						<path d="M5.757 1.071a.5.5 0 0 1 .172.686L3.383 6h9.234L10.07 1.757a.5.5 0 1 1 .858-.514L13.783 6H15a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1v4.5a2.5 2.5 0 0 1-2.5 2.5h-9A2.5 2.5 0 0 1 1 13.5V9a1 1 0 0 1-1-1V7a1 1 0 0 1 1-1h1.217L5.07 1.243a.5.5 0 0 1 .686-.172zM2 9v4.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V9H2zM1 7v1h14V7H1zm3 3a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 4 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 6 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 8 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 1 .5-.5zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 1 .5-.5z"/>
						</svg>
						<span>Pedidos</span>
					</petclinic:menuItem>
					</sec:authorize>
					
					<sec:authorize access="hasAuthority('admin')">
					<petclinic:menuItem active="${name eq 'productos'}" url="/productos"
						title="productos">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Productos</span>
					</petclinic:menuItem>
					</sec:authorize>
					
					<sec:authorize access="hasAuthority('admin')">
					<petclinic:menuItem active="${name eq 'vehiculos'}" url="/vehiculos"
						title="vehiculos">
						<span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
						<span>Vehiculos</span>
					</petclinic:menuItem>
					</sec:authorize>
					
					<sec:authorize access="hasAuthority('admin') or hasAuthority('repartidor')">
					<petclinic:menuItem active="${name eq 'repartidores'}" url="/repartidores"
						title="repartidores">
						<span class="glyphicon glyphicon-send" aria-hidden="true"></span>
						<span>Repartos</span>
					</petclinic:menuItem>
					</sec:authorize>
					
					<petclinic:menuItem active="${name eq 'carta'}" url="/carta"
						title="carta">
						<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
						<span>Carta</span>
					</petclinic:menuItem>
					
					<sec:authorize access="hasAuthority('cliente')">
					<petclinic:menuItem active="${name eq 'Mi Perfil'}" url="/clientes/perfil"
						title="Mi Perfil">
						<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
						<span>Mi Perfil</span>
					</petclinic:menuItem>
					</sec:authorize>
	<!--				<petclinic:menuItem active="${name eq 'owners'}" url="/owners"
						title="owners">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
						<span>Owners</span>
					</petclinic:menuItem>
	
					<petclinic:menuItem active="${name eq 'owners'}" url="/owners/find"
						title="find owners">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
						<span>Find owners</span>
					</petclinic:menuItem>
	
					<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
						title="veterinarians">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Veterinarians</span>
					</petclinic:menuItem>
	
					<petclinic:menuItem active="${name eq 'error'}" url="/oups"
						title="trigger a RuntimeException to see how it is handled">
						<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
						<span>Error</span>
					</petclinic:menuItem>
	-->
				</ul>
	
	
	
	
				<ul class="nav navbar-nav navbar-right">
					<sec:authorize access="!isAuthenticated()">
						<li><a href="<c:url value="/login" />">Login</a></li>
						<li><a href="<c:url value="/users/new" />">Register</a></li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
								<strong><sec:authentication property="name" /></strong> <span
								class="glyphicon glyphicon-chevron-down"></span>
						</a>
							<ul class="dropdown-menu">
								<li>
									<div class="navbar-login">
										<div class="row">
											<div class="col-lg-4">
												<p class="text-center">
													<span class="glyphicon glyphicon-user icon-size"></span>
												</p>
											</div>
											<div class="col-lg-8">
												<p class="text-left">
													<strong><sec:authentication property="name" /></strong>
												</p>
												<p class="text-left">
													<a href="<c:url value="/logout" />"
														class="btn btn-primary btn-block btn-sm">Logout</a>
												</p>
											</div>
										</div>
									</div>
								</li>
								<li class="divider"></li>
	<!-- 							
	                            <li> 
									<div class="navbar-login navbar-login-session">
										<div class="row">
											<div class="col-lg-12">
												<p>
													<a href="#" class="btn btn-primary btn-block">My Profile</a>
													<a href="#" class="btn btn-danger btn-block">Change
														Password</a>
												</p>
											</div>
										</div>
									</div>
								</li>
	-->
							</ul></li>
					</sec:authorize>
				</ul>
			</div>
	
	
	
		</div>
	</nav>
</section>
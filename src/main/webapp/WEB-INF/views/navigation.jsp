<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<div id="navbar" class="navbar navbar-fixed-top" >
		<div class="navbar-inner">
			<div class="container-fluid">				 
				<div class="nav-collapse">
					<ul class="nav">
						<sec:authorize access="isAuthenticated()">	 
							<sec:authorize access="hasRole('ROLE_USER')">
								<li>
									<a class="navbar-link" href="./version">Version</a>
								</li>
								<li>
									<a class="navbar-link" href="./checklist_group">Checklist</a>
								</li>
								<li>
									<a class="navbar-link" href="./contact_faculty">Contact</a>
								</li>
								<li>
									<a class="navbar-link" href="./faq">FAQ</a>
								</li>
								<li>
									<a class="navbar-link" href="./googlemap">Google Map</a>
								</li>
								<li>
									<a class="navbar-link" href="./link">Link</a>
								</li>
								<li>
									<a class="navbar-link" href="./ueb_position">UEB</a>
								</li>
								<li>
									<a class="navbar-link" href="./welcometalk">Welcome Talk</a>
								</li>
							</sec:authorize>
						</sec:authorize>
						
						<sec:authorize access="isAuthenticated()">
							<form class="navbar-form pull-right"> 
								<div style="margin-top: 10px">
								   <a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
								</div>
							</form>
						</sec:authorize>					 
					</ul>									
				</div>
			</div>
		</div>
	</div>
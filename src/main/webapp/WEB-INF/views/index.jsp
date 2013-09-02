<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
	<head>
		<title>Server Side - Login Page</title>
		<link href="resources/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body onload='document.f.j_username.focus();'>
  		<sec:authorize access="isAnonymous()">
	  		<div style="padding-top: 50px;
				margin-left:auto;
				margin-right:auto;
				width:70%;">
	  			<h1 align="center">New@Shef</h1>
				<h3 align="center">Server Side</h3>
				<form name='f' action="<c:url value='j_spring_security_check' />"
					method='POST'>				
					<table style='margin:0px auto'>
						<tr>
							<td><label class="control-label">User:</label></td>
							<td><input type='text' name='j_username' value='' class="input-large">
							</td>
						</tr>
						<tr>
							<td><label class="control-label">Password:</label></td>
							<td><input type='password' name='j_password' class="input-large"/>
							</td>
						</tr>
						<tr>
							<td></td>
							<td style="text-align:right;"><input name="submit" type="submit"
								value="submit" class="btn btn-primary"/>
							</td>				  
						</tr>
					</table>				
				</form>
			</div>
 		</sec:authorize>
 		<sec:authorize access="hasRole('ROLE_USER')">
			<div>
				<%@ include file="./navigation.jsp"%> 
			</div>
			<div style="padding-top: 50px;
				 	margin-left:auto;
					margin-right:auto;
					width:70%;">
				<h3>New@Shef - Version Control:</h3>	
				<p>New@Shef server side, this app maybe need updating information later.</p> 
				<p>All the data can be saved and updated on the server side</p> 
				<p>Client side app is going to compare the local version with the server version</p> 
				<p>-If they are same, the app is going to fetch data from the internal database(sqlite3)</p> 
				<p>-Otherwise, it is going to download the latest version from the external database and update local version<p>
				<h3>New@Shef - Server implementation:</h3>	
				<p>Spring Framework: </p>
				<p>-contains service, dao classes, view controller, security and login feature </p>
				<p>Hibernate Framework:</p>
				<p>-Mapping the relation between object classes to the tables in the relational database</p>
				<p>-Retrieving data from database</p>
				<p>JSP:</p>
				<p>-form validation, assign values, dynamic input</p>
			</div>
		</sec:authorize>
	</body>
</html>
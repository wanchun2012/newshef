<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Version</title>
		<link href="resources/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<sec:authorize access="hasRole('ROLE_USER')">
			<div>
				<%@ include file="./navigation.jsp"%> 
			</div>
			<div style="padding-top: 50px">
			<h3>New@Shef - Version Control:</h3>					 		 
			<table class="table table-striped">  
		        <thead>  
		          <tr>  
		            <th>Version</th>  
		            <th>vChecklist</th>  
		            <th>vContact</th>  
		            <th>vFAQ</th>  
		            <th>vGoogleMap</th> 
		            <th>vLink</th> 
		            <th>vUEB</th> 
		            <th>vWelcomeTalk</th> 
		          </tr>  
		        </thead>  
		        <tbody>  
		          <tr>  
		            <td>v${versionForm.version}</td>  
		            <td>v${versionForm.versionChecklist}</td>  
		            <td>v${versionForm.versionContact}</td>  
		            <td>v${versionForm.versionFAQ}</td>  
		            <td>v${versionForm.versionGoogleMap}</td>  
					<td>v${versionForm.versionLink}</td>  
					<td>v${versionForm.versionUEB}</td>  
					<td>v${versionForm.versionWelcomeTalk}</td>  
		          </tr>  
		        </tbody>  
	      	</table>  
	      	</div>
		</sec:authorize>
	</body> 
</html>

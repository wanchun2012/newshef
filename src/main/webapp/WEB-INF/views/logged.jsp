<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Home</title>
		<link href="resources/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
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
	</body>
</html>
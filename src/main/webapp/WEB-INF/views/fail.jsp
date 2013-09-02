<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<head>
	<title>e-journal</title>
</head>
<body onload='document.f.j_username.focus();'>
<h3>
	Login Fail
</h3>

<P>  The time on the server is ${serverTime}. </P>

<c:if test="${not empty error}">
  <div>
   Your login attempt was not successful, try again.
 Caused :
   ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
  </div>
 </c:if>

</body>
</html>
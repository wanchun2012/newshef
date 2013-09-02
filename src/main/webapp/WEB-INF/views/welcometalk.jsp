<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Welcome Talk</title>
		<link href="resources/css/bootstrap.min.css" rel="stylesheet">
		<script type="text/javascript">
		/*
			function ifUpdate() {
				var checked=document.getElementById('checked');
				if(document.getElementById('checkUpdate').checked) {				     
				    checked.value = 1;
				}
				else {			 
					checked.value = 0;
				}			
			}	
		*/
			function formValidation()
			{
				var ok = true;
				var input = document.getElementsByTagName('textarea'); 
				 
		    	if(input[0].value==null||input[0].value=="")
		    	{
					ok =false;
				     				   
		    	}
				 
			 	if(ok)
			 	{
					var file = document.getElementsByTagName('input'); 
					for (var i=0; i<file.length; i++) 
					{
					    if (file[i].type == 'file') 
					    {
							if(file[0].value==null||file[i].value=="")
								ok = false;
					    }
					}
			 	}
			 
	 			if(ok)
					document.getElementById("myForm").submit();
	 			else
	 				alert("Empty input, please check");
			}
		</script>
	</head>
		<body>
			<sec:authorize access="hasRole('ROLE_USER')">
				<div>
  					<%@ include file="./navigation.jsp"%> 
				</div>
				<div style="padding-top: 50px;
				 	margin-left:auto;
					margin-right:auto;
					width:70%;">
					<h3>WelcomeTalk:</h3>					 
					<form:form method="post" action="welcometalk" modelAttribute="welcomeForm"  name="myForm" id="myForm" 
						class="form-horizontal" enctype="multipart/form-data">   
						<div id="dept">
							<div class="control-group">			 
								<div id="dynamicInput">
									<div id="subpos">
										<div class="control-group">
											<label class="control-label" for="welcomeText" >Welcome Text:</label>
											<div class="controls">
												<textarea id="txtValue" name="txtValue"  maxlength="2000"
													class="input-xlarge" data-validate="submit" rows=20>${welcomeForm.welcomeText}</textarea>		 						
											</div>
										</div>								 
										<div class="control-group">
											<label class="control-label" for="file" >File Upload:</label>
											<div class="controls">
												<input type="file" name="file" id="file"></input>	
											</div>
										</div>								 
										<c:if  test="${!empty welcomeForm.welcomeText}">	
											<div class="control-group">
												<label class="control-label" >View Image:</label>
												<div class="controls">
													<a href="${pageContext.request.contextPath}/welcometalk/download/1.html">download</a> 
													 
													<input name="checked" value="1" type="hidden" id="checked"/>
												</div>
											</div>		
										</c:if>
										<div class="control-group">
											<label class="control-label" > </label>
											<div class="controls">														 
												<input type="button" value="Save" class="btn btn-primary" onclick="formValidation()"/>
											</div>
										</div>
		           					</div>				 
					   			</div>							 
							</div>
						</div> 
					</form:form>
				</div>			    	
		</sec:authorize>
	</body> 
</html>

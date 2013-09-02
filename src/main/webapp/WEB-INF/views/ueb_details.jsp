<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>UEB</title>
		<link href="resources/css/bootstrap.min.css" rel="stylesheet">
		<script type="text/javascript">	 
		function formValidation() {	 
				var ok = true;
				
				var input = document.getElementsByTagName('input'); 
				for (var i=0; i<input.length; i++) 
				{
				    if (input[i].type == 'text') 
				    {
				    	if(input[i].value==null||input[i].value=="")
				    	{
							ok =false;
						    break;
				    	}
				    } 
				}
				
				var textarea = document.getElementsByTagName('textarea');
				if(ok)
				{
					for (var i=0; i<textarea.length; i++) 
					{			 
				    	if(textarea[i].value==null||textarea[i].value=="")
				    	{
							ok =false; 
						    break;
				    	}		  
					}
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
					<h3>Modify subpos:</h3>					 
					<form:form method="post" action="ueb_details" modelAttribute="uebForm" name="myForm" id="myForm"
						class="form-horizontal" enctype="multipart/form-data">   			 
				        <div id="dept">
							<div class="control-group">			 
								 <div id="dynamicInput">									     		        
							        <div id="subpos">
							        	<div class="control-group">
											<label class="control-label" for="subpositionName" >  </label>
											<div class="controls">
												<input name="uebForm.subpositionName" value="${uebForm.subpositionName}" 
													class="input-xlarge" data-validate="submit" type="text" id="txtName" disabled="disabled"/>		 						
											</div>
										</div>
										<div class="control-group">
										<label class="control-label" for="uebName" > Name: </label>
											<div class="controls">
												<input id="nameValue" name="nameValue" value="${uebForm.uebName}" 
													class="input-xlarge" data-validate="submit" type="text" maxlength="255"/>		 						
											</div>
										</div>
										<div class="control-group">
										<label class="control-label" for="uebDescription" > Description: </label>
											<div class="controls">
												<textarea id="descriptionValue" name="descriptionValue"  maxlength="2000"
													class="input-xlarge" data-validate="submit"  rows=20>${uebForm.uebDescription}</textarea>		 						
											</div>
										</div>
										<div class="control-group">
										<label class="control-label" for="file" > File Upload: </label>
											<div class="controls">
												<input type="file" name="file" id="file"></input>	
												<input type="hidden" id="indexValue" name="indexValue" value="${uebId}" />	
											</div>
										</div>		
										<c:if  test="${!empty uebForm.uebDescription}">
											<div class="control-group">
												<label class="control-label" > image view?</label>
												<div class="controls">
													<a href="${pageContext.request.contextPath}/ueb/download/${uebId}.html">download</a> 
												</div>
											</div>
										</c:if>
										<div class="control-group">
											<label class="control-label" > </label>
											<div class="controls">									 
												<input type="button" value="Save" class="btn btn-primary"  onclick="formValidation()"/>
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
				 
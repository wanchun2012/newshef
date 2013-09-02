<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Checklist</title>
		<link href="resources/css/bootstrap.min.css" rel="stylesheet">
		<script type="text/javascript">	
			function myFunction(index)
			{		 
				var selected =document.getElementById('indexValue');
				selected.value = index;
			}
			
			function formValidation()
			{
				var ok = false;
				var chx = document.getElementsByTagName('input');
				for (var i=0; i<chx.length; i++) 
				{
				    if (chx[i].type == 'radio' && chx[i].checked) 
				    {
				      ok =true;
				      break;
				    } 
				}
				
				if(ok)
					document.getElementById("myForm").submit();
	 			else
	 				alert("Please select one group");
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
				<h3>Select Group:</h3>		
				<form:form method="post" action="checklist_activity" modelAttribute="checklistForm" 
					class="form-horizontal" name="myForm" id="myForm">   
					<fieldset>
						<div id="dynamicInputChecklist"> 
							<input name="selected" value="1" type="hidden" id="selected"/>
						    <c:forEach items="${checklistForm.groupCollection}" var="group" varStatus="status">
						        <div id="group_${status.count}">
						        	<div class="control-group">
										<label class="control-label" for="groupName" >Group ${status.count}:</label>
										<div class="controls">				 
											<input type="radio" onclick="myFunction(${group.id})" value="Show alert box" name="selected"> ${group.groupName}
										</div>
									</div>								  
						        </div>
						    </c:forEach>
					   	</div>
					   	<div class="control-group">
							<label class="control-label" ></label>
							<div class="controls">
					    		<input type="hidden" id="indexValue" name="indexValue"/>
								<input type="button" value="next"  class="btn btn-primary" onclick="formValidation()"/>
							</div>
						</div>	
					</fieldset>	
				</form:form>
			</div>		 
		</sec:authorize>
	</body>
</html>
				 
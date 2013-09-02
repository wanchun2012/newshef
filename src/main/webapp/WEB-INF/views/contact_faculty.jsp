<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Contact</title>
		<link href="resources/css/bootstrap.min.css" rel="stylesheet"> 

		<script type="text/javascript">
			var counter;
		    var firstnew = true;
		    
			function addFaculty(parentdiv,length) { 
				if (firstnew) {
					counter = length;	
					counter = length + 1;
					firstnew = false;
				}
				else {
					counter++;
				}	
				var groupLength = 0;
				var newdiv = document.createElement('div');
				var divIdName = 'new_faculty_'+counter;
				newdiv.setAttribute('id',divIdName);
			
				newdiv.innerHTML = 
				' <div class="control-group">'+
					' <label class="control-label" for="facultyName">Faculty '+counter+': Name: </label>'+ 
					' <div class="controls">'+
						' <input name="facultyCollection['+(counter-1)+'].facultyName" value="${faculty.facultyName}" maxlength="255"'+
							' class="input-xlarge" data-validate="submit" type="text"/>'+
					' </div>'+
				' </div>';
	 				
				document.getElementById(parentdiv).appendChild(newdiv); 
	
				$('html, body').animate({
					scrollTop : $("#new_faculty_" + counter).offset().top -90
				}, 500);
			}	
			 
			function removeFaculty(parentdiv) {
				 
				if (counter == 0) {					
				} else {
					var removediv = document.getElementById('new_faculty_'+counter);
					document.getElementById(parentdiv).removeChild(removediv);
					counter--; 
				}
		
				// give focus to the newly added input
				$('html, body').animate({
					scrollTop : $("#new_faculty_" + counter).offset().top -90
				}, 500);
			}
		 
			function enableEdit(index) {
				var target = index;
				var editGroupName = document.getElementById('txtFacultyName_'+target);
				editGroupName.disabled=false;
						
				var edit =document.getElementById('edit_'+target);
				edit.value = "1";
				
			}
			
			function checkRemove(index) {
				var target = index;
				var remove =document.getElementById('remove_'+target);
				if(document.getElementById('btnRemove_'+target).checked) {
				     remove.value = "1";
				}
				else {
					 remove.value = "0";
				}
			}
				
			function formValidation()
			{
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
				<h3>Modify Faculty:</h3>			
				<form:form method="post" action="contact_faculty" modelAttribute="contactForm" 
					class="form-horizontal" name="myForm" id="myForm">   
					<div id="dynamicInput"> 
					    <c:forEach items="${contactForm.facultyCollection}" var="faculty" varStatus="status">
					        <div id="faculty_${status.count}">
					        	<div class="control-group">
									<label class="control-label" for="facultyName" >Faculty ${status.count}: Name:</label>
									<div class="controls">
										<input name="facultyCollection[${status.index}].facultyName" value="${faculty.facultyName}" maxlength="255"
											class="input-xlarge" data-validate="submit" type="text" id="txtFacultyName_${status.count}" disabled="disabled"/>		 									
									</div>
								</div>	
								<div class="control-group">
									<label class="control-label">Action:</label>
									<div class="controls">	
										<input type="button"  value="edit" id="btnEdit_${status.count}"  onClick="enableEdit(${status.count});"
												style="background-color: transparent;
												text-decoration: underline;
												border: none;
												color: blue;
												cursor: pointer;">
									 	<input name="facultyCollection[${status.index}].edited"  value="${faculty.edited}"  type="hidden" id="edit_${status.count}"/>
										<label style="background-color: transparent;					
												border: none;
												color: blue;
												cursor: pointer;">
												<input type="checkbox" id="btnRemove_${status.count}"  onclick="checkRemove(${status.count});" />				 
												remove
										</label>
										<input name="facultyCollection[${status.index}].removed"  value="${faculty.removed}"  type="hidden" id="remove_${status.count}"/>
										<input name="facultyCollection[${status.index}].id" value="${faculty.id}" type="hidden"/>
									</div>
								</div>							  
					        </div>
					    </c:forEach>
				   	</div>
				   	<label class="control-label"></label>
					<div class="controls">
						<input class="btn btn-primary" type="button"
							value="Add New" onClick="addFaculty('dynamicInput',${ContactFormLength});"  id="btnAddNew"/>
						<input class="btn btn-primary" type="button"
							value="Remove" onClick="removeFaculty('dynamicInput');" id="btnRemoveNew"/>
					</div>
					<br/>
					<div class="control-group">
						<label class="control-label" ></label>
						<div class="controls">	
							<input type="button" value="Save" class="btn btn-primary" onclick="formValidation()"/>
				     	</div>
				     </div>
				</form:form>
		 	</div>
		</sec:authorize>
	</body>
</html>
				 
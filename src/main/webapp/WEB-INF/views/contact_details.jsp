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
			function enableEdit(index) {
				var target = index;
				var editName = document.getElementById('txtDepartmentName_'+target);
				editName.disabled=false;
				var editEmail = document.getElementById('txtEmail_'+target);
				editEmail.disabled=false;
				var editPhone = document.getElementById('txtPhone_'+target);
				editPhone.disabled=false;
						
				var edit =document.getElementById('txtedit_'+target);
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
	
				var newdiv = document.createElement('div');
				var divIdName = 'new_department_'+counter;
				newdiv.setAttribute('id',divIdName);
			 
				newdiv.innerHTML = 
				' <div class="control-group">'+
					' <label class="control-label" for="departmentName">Department '+counter+': Name: </label>'+ 
					' <div class="controls">'+
						' <input name="departmentCollection['+(counter-1)+'].departmentName" value="${department.departmentName}"'+
							' class="input-xlarge" data-validate="submit" type="text" maxlength="255"/>'+
					' </div>'+
				' </div>'+ 
				' <div class="control-group">'+
					' <label class="control-label" for="email" >  Email:</label>' +
					' <div class="controls">'+
						' <input name="departmentCollection['+(counter-1)+'].email" value="${department.email}"'
							+ 'class="input-xlarge" data-validate="submit" type="email" maxlength="255"/>'+	
					' </div>'+
				' </div>'+
				' <div class="control-group">'+
					' <label class="control-label" for="phone" > Phone:</label>'+
						' <div class="controls">'+
							' <input name="departmentCollection['+(counter-1)+'].phone" value="${department.phone}"'+ 
								' class="input-xlarge" data-validate="submit" type="text" maxlength="50"'+
								' onkeypress="return event.charCode >= 48 && event.charCode <= 57"/>'+				 
						' </div>'+
				' </div>';
				 
				document.getElementById(parentdiv).appendChild(newdiv);
	
				$('html, body').animate({
					scrollTop : $('#new_department_'+counter).offset().top -90
				}, 500);
			}	
			
			function removeFaculty(parentdiv) { 
				if (counter == 0) {					
				} else {
					var removediv = document.getElementById('new_department_'+counter);
					document.getElementById(parentdiv).removeChild(removediv);
					counter--; 
				}
		
				// give focus to the newly added input
				$('html, body').animate({
					scrollTop : $('#new_department_'+counter).offset().top -90
				}, 500);
			}
			
			function formValidation() {	 
				var ok = true;
				var input = document.getElementsByTagName('input'); 
				var errorcode = 0;
				for (var i=0; i<input.length; i++) 
				{
				    if (input[i].type == 'text'||input[i].type == 'email') 
				    {
				    	if(input[i].value==null||input[i].value=="")
				    	{
							ok =false;
							errorcode = 1;
						    break;
				    	}
				    	else
				    	{
				    		
					    	if (input[i].type == 'email')  
					    	{
					    		 if(!input[i].value.match(/\S+@\S+\.\S+/))
					    		 {  
					    			 ok =false;
					    			 errorcode = 2;
					    			 break;
					    		 }
					    	 
					    		 if(input[i].value.indexOf(' ')!=-1 || input[i].value.indexOf('..')!=-1)
					    		 {
					    		     ok =false;
					    		     errorcode = 2;
					    		     break;
					    		 }		 
					    		 
					    	}
				    	}
				    } 
				}
				
				if(ok)
					document.getElementById("myForm").submit();
	 			else
	 			{
	 				if (errorcode == 1)
						alert("Empty input, please check");	
	 				else if (errorcode == 2)
	 					alert("Invalid email, please check");	
	 			}
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
				<h3>Modify Department:</h3>				 
				<form:form method="post" action="contact_details" modelAttribute="departmentForm" 
					class="form-horizontal" name="myForm" id="myForm">   	    
			        <div id="dept_${status.count}">
			        	<div class="control-group">
							<label class="control-label" for="facultyName" ></label>
							<div class="controls">
								<input name="departmentForm.facultyName" value="${departmentForm.facultyName}" 
									class="input-xlarge" data-validate="submit" type="text" id="txtDeptName" disabled="disabled"/>		 

								<input type="hidden" id="indexValue" name="indexValue" value="${departmentForm.id}"/>
							</div>
						</div>
					  	<div class="control-group">
							<label class="control-label"></label>
							<div class="controls">
								<input class="btn btn-primary" type="button"
									value="Add New" onClick="addFaculty('dynamicInput',${departmentForm.size});"  id="btnAddNew"/>
								<input class="btn btn-primary" type="button"
									value="Remove" onClick="removeFaculty('dynamicInput');"  id="btnRemoveNew"/>
							</div>
				 		</div> 
						<div class="control-group">
							 <div id="dynamicInput">
							    <c:forEach items="${departmentForm.departmentCollection}" var="department" varStatus="status_faculty">		        
							        <div id="faculty_${status.count}">
							        	<div class="control-group">
											<label class="control-label" for="departmentName" >Faculty ${status_faculty.count}: Name:</label>
											<div class="controls">
												<input name="departmentCollection[${status_faculty.index}].departmentName" value="${department.departmentName}" maxlength="255"
													class="input-xlarge" data-validate="submit" type="text" id="txtDepartmentName_${status_faculty.count}" disabled="disabled"/>		 						
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="email" >  Email:</label>
											<div class="controls">
												<input name="departmentCollection[${status_faculty.index}].email" value="${department.email}" maxlength="255"
														class="input-xlarge" data-validate="submit" type="email" id="txtEmail_${status_faculty.count}" disabled="disabled"/>	
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="phone" > Phone:</label>
											<div class="controls">
												<input name="departmentCollection[${status_faculty.index}].phone" value="${department.phone}" maxlength="50"
														class="input-xlarge" data-validate="submit" type="text" id="txtPhone_${status_faculty.count}" disabled="disabled"
														onkeypress="return event.charCode >= 48 && event.charCode <= 57"/>							
											</div>				 
							        	</div>
							        	<div class="control-group">
											<label class="control-label">Action:</label>
											<div class="controls">
												<input type="button"  value="edit" id="btnEdit_${status_faculty.count}"  onclick="enableEdit(${status_faculty.count});"
														style="background-color: transparent;
														text-decoration: underline;
														border: none;
														color: blue;
														cursor: pointer;">
											 	<input name="departmentCollection[${status_faculty.index}].edited"  value="${department.edited}"  type="hidden" id="txtedit_${status_faculty.count}"/>
												<label style="background-color: transparent;					
														border: none;
														color: blue;
														cursor: pointer;">
														<input type="checkbox" id="btnRemove_${status_faculty.count}"  onclick="checkRemove(${status_faculty.count});" id="btnRemove_${status_faculty.count}" />				 
														remove
												</label>
												<input name="departmentCollection[${status_faculty.index}].removed"  value="${department.removed}"  type="hidden" id="remove_${status_faculty.count}"/>
												<input name="departmentCollection[${status_faculty.index}].id" value="${department.id}" type="hidden" />
											</div>
										</div>
									</div>
							    </c:forEach>
		   					</div>							 
						</div>
					</div> 
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
				 
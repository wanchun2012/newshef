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
	 
			function enableEdit(index) {
				var target = index;
				var editName = document.getElementById('txtActivityName_'+target);
				editName.disabled=false;
				var editDetails = document.getElementById('txtDetails_'+target);
				editDetails.disabled=false;
				var editPerson = document.getElementById('txtResponsiblePerson_'+target);
				editPerson.disabled=false;							
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
			function addActivity(parentdiv,length) { 			 
				if (firstnew) {
					counter = length;	
					counter = length + 1;
					firstnew = false;
				}
				else {
					counter++;
				}	
	
				var newdiv = document.createElement('div');
				var divIdName = 'new_activity_'+counter;
				newdiv.setAttribute('id',divIdName);
			 
				newdiv.innerHTML = 
				' <div class="control-group">'+
					' <label class="control-label" for="activityName">Activity '+counter+': Name: </label>'+ 
					' <div class="controls">'+
						' <input name="activityCollection['+(counter-1)+'].activityName" value=""'+
							' class="input-xlarge" data-validate="submit" type="text" maxlength="255"/>'+
					' </div>'+
				' </div>'+ 
				' <div class="control-group">'+
					' <label class="control-label" for="details" >  Details:</label>' +
					' <div class="controls">'+
						' <textarea name="activityCollection['+(counter-1)+'].details" '
							+ ' class="input-xlarge" data-validate="submit" rows=10 maxlength="1000"/></textarea>'+	
					' </div>'+
				' </div>'+
				' <div class="control-group">'+
					' <label class="control-label" for="responsiblePerson" > ResponsiblePerson</label>'+
						' <div class="controls">'+
							' <input name="activityCollection['+(counter-1)+'].responsiblePerson" value=""'+ 
								' class="input-xlarge" data-validate="submit" type="text" maxlength="255"/>'+				 
						' </div>'+
				' </div>';
				 
				document.getElementById(parentdiv).appendChild(newdiv);
	
				$('html, body').animate({
					scrollTop : $('#new_activity_'+counter).offset().top -90
				}, 500);
			}	
			
			function removeActivity(parentdiv) { 
				if (counter== 0) {					
				} else {
					var removediv = document.getElementById('new_activity_'+counter);
					document.getElementById(parentdiv).removeChild(removediv);
					counter--; 
				}
		
				// give focus to the newly added input
				$('html, body').animate({
					scrollTop : $('#new_activity_'+counter).offset().top -90
				}, 500);
			}
			
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
				<h3>Modify Activity:</h3>			 
				<form:form method="post" action="checklist_details" modelAttribute="groupForm" 
					class="form-horizontal" name="myForm" id="myForm">       
			        <div id="group_${status.count}">
			        	<div class="control-group">
							<label class="control-label" for="groupName" >Group Name:</label>
							<div class="controls">
								<input name="groupForm.groupName" value="${groupForm.groupName}"  
									class="input-xlarge" data-validate="submit" type="text" id="txtGroupName" disabled="disabled"/>		 
								<input type="hidden" id="indexValue" name="indexValue" value="${groupForm.id}"/>
							</div>
						</div>
					  	<div class="control-group">
							<label class="control-label"></label>
							<div class="controls">
								<input class="btn btn-primary" type="button"
									value="Add New" onClick="addActivity('dynamicInput',${groupForm.size});"  id="btnAddNewActitiy"/>
								<input class="btn btn-primary" type="button"
									value="Remove" onClick="removeActivity('dynamicInput');"  id="btnRemoveNewActivity"/>
							</div>
				 		</div> 
						<div class="control-group">	 
							 <div id="dynamicInput">
							    <c:forEach items="${groupForm.activityCollection}" var="activity" varStatus="status_activity">		        
							        <div id="activity_${status.count}">
							        	<div class="control-group">
											<label class="control-label" for="activityName" >Activity ${status_activity.count}: Name:</label>
											<div class="controls">
												<input name="activityCollection[${status_activity.index}].activityName" value="${activity.activityName}" maxlength="255"
													class="input-xlarge" data-validate="submit" type="text" id="txtActivityName_${status_activity.count}" disabled="disabled"/>		 						
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="details" >  Details:</label>
											<div class="controls">
												<textarea name="activityCollection[${status_activity.index}].details" 
														class="input-xlarge" data-validate="submit" rows=10
														id="txtDetails_${status_activity.count}" disabled="disabled" maxlength="1000">${activity.details}</textarea>	
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="responsiblePerson" > ResponsiblePerson</label>
											<div class="controls">
												<input name="activityCollection[${status_activity.index}].responsiblePerson" value="${activity.responsiblePerson}" maxlength="255"
														class="input-xlarge" data-validate="submit" type="text" id="txtResponsiblePerson_${status_activity.count}" disabled="disabled"/>							
											</div>				 
							        	</div>
							        	<div class="control-group">
											<label class="control-label">Action:</label>
											<div class="controls">
												<input type="button"  value="edit" id="btnEdit_${status_activity.count}"  onclick="enableEdit(${status_activity.count});"
														style="background-color: transparent;
														text-decoration: underline;
														border: none;
														color: blue;
														cursor: pointer;">
											 	<input name="activityCollection[${status_activity.index}].edited"  value="${activity.edited}"  type="hidden" id="txtedit_${status_activity.count}"/>
												<label style="background-color: transparent;					
														border: none;
														color: blue;
														cursor: pointer;">
														<input type="checkbox" id="btnRemove_${status_activity.count}"  onclick="checkRemove(${status_activity.count});" id="btnRemove_${status_activity.count}" />				 
														remove
												</label>
												<input name="activityCollection[${status_activity.index}].removed"  value="${activity.removed}"  type="hidden" id="remove_${status_activity.count}"/>
												<input name="activityCollection[${status_activity.index}].id" value="${activity.id}" type="hidden" />
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
				 
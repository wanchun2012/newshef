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
			function enableEdit(index) {
				var target = index;
				var editName = document.getElementById('txtName_'+target);
				editName.disabled=false;
					
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
			function addSubPos(parentdiv,length) { 
				 
				if (firstnew) {
					counter = length;	
					counter = length + 1;
					firstnew = false;
				}
				else {
					counter++;
				}	
	
				var newdiv = document.createElement('div');
				var divIdName = 'new_subpos_'+counter;
				newdiv.setAttribute('id',divIdName);
			 
				newdiv.innerHTML = 
				' <div class="control-group">'+
					' <label class="control-label" for="subpositionName"> '+counter+':  </label>'+ 
					' <div class="controls">'+
						' <input name="subCollection['+(counter-1)+'].subpositionName" value="${subposition.subpositionName}"'+
							' class="input-xlarge" data-validate="submit" type="text" maxlength="255"/>'+
					' </div>'+
				' </div>';
				 
				document.getElementById(parentdiv).appendChild(newdiv);
	
				$('html, body').animate({
					scrollTop : $('#new_subpos_'+counter).offset().top -90
				}, 500);
			}	
			
			function removeSubPos(parentdiv) { 
				if (counter == 0) {					
				} else {
					var removediv = document.getElementById('new_subpos_'+counter);
					document.getElementById(parentdiv).removeChild(removediv);
					counter--; 
				}
		
				// give focus to the newly added input
				$('html, body').animate({
					scrollTop : $('#new_subpos_'+counter).offset().top -90
				}, 500);
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
	 			{	 				 
	 				alert("Empty input, please check"); 				 
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
				<h3>Modify subpos:</h3>		
		 
				<form:form method="post" action="ueb_subposition" modelAttribute="uebForm" 
					class="form-horizontal" name="myForm" id="myForm">   			    
		        	<div class="control-group">
						<label class="control-label" for="subpositionName" >${uebForm.positionName}</label>
						<div class="controls">
							<input type="hidden" id="indexValue" name="indexValue" value="${uebForm.id}"/>
						</div>
					</div>
				  	<div class="control-group">
						<label class="control-label"></label>
						<div class="controls">
							<input class="btn btn-primary" type="button"
								value="Add New" onClick="addSubPos('dynamicInput',${uebForm.size});"  id="btnAddNew"/>
							<input class="btn btn-primary" type="button"
								value="Remove" onClick="removeSubPos('dynamicInput');"  id="btnRemoveNew"/>
						</div>
			 		</div> 
						 		 
					<div id="dynamicInput">
						<c:forEach items="${uebForm.subCollection}" var="subposition" varStatus="status_subpos">		        
							<div id="subpos_${status.count}">
					        	<div class="control-group">
									<label class="control-label" for="subpositionName" > ${status_subpos.count}:  </label>
									<div class="controls">
										<input name="subCollection[${status_subpos.index}].subpositionName" value="${subposition.subpositionName}" maxlength="255"
											class="input-xlarge" data-validate="submit" type="text" id="txtName_${status_subpos.count}" disabled="disabled"/>		 						
									</div>
								</div>
											 												 
					        	<div class="control-group">
									<label class="control-label">Action:</label>
									<div class="controls">
										<input type="button"  value="edit" id="btnEdit_${status_subpos.count}"  onclick="enableEdit(${status_subpos.count});"
										style="background-color: transparent;
												text-decoration: underline;
												border: none;
												color: blue;
												cursor: pointer;">
									 	<input name="subCollection[${status_subpos.index}].edited"  value="${subposition.edited}"  type="hidden" id="txtedit_${status_subpos.count}"/>
										<label style="background-color: transparent;					
												border: none;
												color: blue;
												cursor: pointer;">
												<input type="checkbox" id="btnRemove_${status_subpos.count}"  onclick="checkRemove(${status_subpos.count});" id="btnRemove_${status_subpos.count}" />				 
												remove
										</label>
										<input name="subCollection[${status_subpos.index}].removed"  value="${subposition.removed}"  type="hidden" id="remove_${status_subpos.count}"/>
										<input name="subCollection[${status_subpos.index}].id" value="${subposition.id}" type="hidden" />
									</div>
								</div>
							</div>
						</c:forEach>
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
				 
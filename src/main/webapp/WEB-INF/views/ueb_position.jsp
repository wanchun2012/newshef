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
			var counter;
		    var firstnew = true;
		    
			function addPosition(parentdiv,length) { 
				if (firstnew) {
					counter = length;	
					counter = length + 1;
					firstnew = false;
				}
				else {
					counter++;
				}	
				 
				var newdiv = document.createElement('div');
				var divIdName = 'new_pos_'+counter;
				newdiv.setAttribute('id',divIdName);
			
				newdiv.innerHTML = 
				' <div class="control-group">'+
					' <label class="control-label" for="positionName">'+counter+':  </label>'+ 
					' <div class="controls">'+
						' <input name="uebCollection['+(counter-1)+'].positionName" value="${position.positionName}"'+
							' class="input-xlarge" data-validate="submit" type="text" maxlength="255"/>'+
					' </div>'+
				' </div>';
	 				
				document.getElementById(parentdiv).appendChild(newdiv); 
	
				$('html, body').animate({
					scrollTop : $("#new_pos_" + counter).offset().top -90
				}, 500);
			}	
			 
			function removePosition(parentdiv) {
				 
				if (counter == 0) {					
				} else {
					var removediv = document.getElementById('new_pos_'+counter);
					document.getElementById(parentdiv).removeChild(removediv);
					counter--; 
				}
		
				// give focus to the newly added input
				$('html, body').animate({
					scrollTop : $("#new_pos_" + counter).offset().top -90
				}, 500);
			}
		 
			function enableEdit(index) {
				var target = index;
				var editGroupName = document.getElementById('txtPosName_'+target);
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
				<h3>Modify Position:</h3>		
				
				<form:form method="post" action="ueb_position" modelAttribute="uebForm" 
					class="form-horizontal" name="myForm" id="myForm" >   
					<div id="dynamicInput"> 
					    <c:forEach items="${uebForm.uebCollection}" var="position" varStatus="status">
					        <div id="pos_${status.count}">
					        	<div class="control-group">
									<label class="control-label" for="positionName" >${status.count}:</label>
									<div class="controls">
										<input name="uebCollection[${status.index}].positionName" value="${position.positionName}" maxlength="255"
											class="input-xlarge" data-validate="submit" type="text" id="txtPosName_${status.count}" disabled="disabled"/>		 									
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
									 	<input name="uebCollection[${status.index}].edited"  value="${position.edited}"  type="hidden" id="edit_${status.count}"/>
										<label style="background-color: transparent;					
												border: none;
												color: blue;
												cursor: pointer;">
												<input type="checkbox" id="btnRemove_${status.count}"  onclick="checkRemove(${status.count});" />				 
												remove
										</label>
										<input name="uebCollection[${status.index}].removed"  value="${position.removed}"  type="hidden" id="remove_${status.count}"/>
										<input name="uebCollection[${status.index}].id" value="${position.id}" type="hidden"/>
									</div>
								</div>							  
					        </div>
					    </c:forEach>
				   	</div>
				   	<label class="control-label"></label>
					<div class="controls">
						<input class="btn btn-primary" type="button"
							value="Add New" onClick="addPosition('dynamicInput',${UEBFormLength});"  id="btnAddNew"/>
						<input class="btn btn-primary" type="button"
							value="Remove" onClick="removePosition('dynamicInput');" id="btnRemoveNew"/>
					</div>
					<br/>
					<div class="control-group">
						<label class="control-label" ></label>
						<div class="controls">
 		 					<input type="button" value="save" class="btn btn-primary" onclick="formValidation()"/>	
						</div>
					</div>
				</form:form>
		 	</div>
		</sec:authorize>
	</body>
</html>
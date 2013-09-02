<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Link</title>
		<link href="resources/css/bootstrap.min.css" rel="stylesheet">
		<script type="text/javascript">
			var counter;
		    var firstnew = true;
			function addLink(parentdiv,length) { 
				if (firstnew) {
					counter = length+1;	
					firstnew = false;
				}
				else {
					counter++;
				}	
				 
				var newdiv = document.createElement('div');
				var divIdName = 'new_link_'+counter;
				newdiv.setAttribute('id',divIdName);
			 
				newdiv.innerHTML = 
				' <div class="control-group">'+
					' <label class="control-label" for="description">Link '+counter+': Description: </label>'+ 
					' <div class="controls">'+
						' <input name="links['+(counter-1)+'].description" value="${link.description}" maxlength="255"'+
							' class="input-xlarge" data-validate="submit" type="text"/>'+
					' </div>'+
				' </div>'+
				' <div class="control-group">'+
					' <label class="control-label" for="url">URL: </label>'+
					' <div class="controls">'+
						' <input name="links['+(counter-1)+'].url" value="${link.url}" maxlength="255"'+
							'class="input-xlarge" data-validate="submit" type="text"/>'+
					' </div>'+
				' </div>';
				document.getElementById(parentdiv).appendChild(newdiv);
	
				$('html, body').animate({
					scrollTop : $("#link_" + counter).offset().top -90
				}, 500);
			}	
			
			function removeLink(parentdiv) {
				 
				if (counter == 0) {					
				} else {
					var removediv = document.getElementById('new_link_'+counter);
					document.getElementById(parentdiv).removeChild(removediv);
					counter--; 
				}
		
				// give focus to the newly added input
				$('html, body').animate({
					scrollTop : $("#link_" + counter).offset().top -90
				}, 500);
			}
		 
			function enableEdit(index) {
				var target = index;
				var editDescription = document.getElementById('txtDescription_'+target);
				editDescription.disabled=false;
				var editUrl = document.getElementById('txtUrl_'+target);
				editUrl.disabled=false;
				
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
				<h3>Useful Links:</h3>
				<form:form method="post" action="link" modelAttribute="linkForm" class="form-horizontal" name="myForm" id="myForm">   
					<fieldset>
						<div id="dynamicInputLink"> 
						    <c:forEach items="${linkForm.links}" var="link" varStatus="status">
						        <div id="link_${status.count}">
						        	<div class="control-group">
										<label class="control-label" for="description" >Link ${status.count}: Description:</label>
										<div class="controls">
											<input name="links[${status.index}].description" value="${link.description}" maxlength="255"
												class="input-xlarge" data-validate="submit" type="text" id="txtDescription_${status.count}" disabled="disabled"/>		 
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="url">URL:</label>
										<div class="controls">
											<input name="links[${status.index}].url" value="${link.url}" maxlength="255"
												class="input-xlarge" data-validate="submit" type="text" id="txtUrl_${status.count}" disabled="disabled"/>	
											
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">Action:</label>
										<div class="controls">
											<input type="button"  value="edit" id="btnEdit_${status.count}" onClick="enableEdit(${status.count});"
														style="background-color: transparent;
														text-decoration: underline;
														border: none;
														color: blue;
														cursor: pointer;">
										 	<input name="links[${status.index}].edited"  value="${link.edited}"  type="hidden" id="edit_${status.count}"/>
											<label style="background-color: transparent;					
													border: none;
													color: blue;
													cursor: pointer;">
													<input type="checkbox" id="btnRemove_${status.count}" onclick="checkRemove(${status.count});"/>				 
													remove
											</label>
											<input name="links[${status.index}].removed"  value="${link.removed}"  type="hidden" id="remove_${status.count}"/>
											<input name="links[${status.index}].id" value="${link.id}" type="hidden"/>
										</div>
									</div>
						        </div>
						    </c:forEach>
					   	</div>
					   	<label class="control-label"></label>
						<div class="controls">
							<input class="btn btn-primary" type="button"
								value="Add New" onclick="addLink('dynamicInputLink',${linkFormLength});" id="btnAddNew">
							<input class="btn btn-primary" type="button"
								value="Remove" onclick="removeLink('dynamicInputLink');" id="btnRemoveNew">
						</div>
						<br/>
						<label class="control-label"></label>
						<div class="controls">
							<input type="button" value="Save" class="btn btn-primary" onclick="formValidation()"/>
						</div>			
					</fieldset>     
				</form:form>
		 	</div>
		</sec:authorize>
	</body>
</html>
				 
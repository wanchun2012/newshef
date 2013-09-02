<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>GoogleMap</title>
		<link href="resources/css/bootstrap.min.css" rel="stylesheet">
		<script type="text/javascript">
			var counter;
		    var firstnew = true;
			function addMarker(parentdiv,length) { 
				if (firstnew) {
					counter = length+1;	
					firstnew = false;
				}
				else {
					counter++;
				}	
				 
				var newdiv = document.createElement('div');
				var divIdName = 'new_marker_'+counter;
				newdiv.setAttribute('id',divIdName);
			 
				newdiv.innerHTML = 
				' <div class="control-group">'+
					' <label class="control-label" for="title">Landscape '+counter+': Title: </label>'+ 
					' <div class="controls">'+
						' <input name="markers['+(counter-1)+'].title" value="${marker.title}" maxlength="50"'+
							' class="input-xlarge" data-validate="submit" type="text"/>'+
					' </div>'+
				' </div>'+
				' <div class="control-group">'+
					' <label class="control-label" for="snippet">Snippet: </label>'+
					' <div class="controls">'+
						' <input name="markers['+(counter-1)+'].snippet" value="${marker.snippet}" maxlength="127"'+
							'class="input-xlarge" data-validate="submit" type="text"/>'+
					' </div>'+
				' </div>' +
				' <div class="control-group">'+
					' <label class="control-label" for="longitude">Longitude:</label>'+
					' <div class="controls">'+
						' <input name="markers['+(counter-1)+'].longitude" value="${marker.longitude}" maxlength="20"'+
							'id="txtLongitude_'+counter+'" class="input-xlarge" data-validate="submit" type="text"/>'+
					' </div>'+
				' </div>' +
				' <div class="control-group">'+
					' <label class="control-label" for="latitude">Latitude:</label>'+
					' <div class="controls">'+
						' <input name="markers['+(counter-1)+'].latitude" value="${marker.latitude}" maxlength="20"'+
							'id="txtLatitude_'+counter+'" class="input-xlarge" data-validate="submit" type="text"/>'+
					' </div>'+
				' </div>' +
				' <div class="control-group">'+
					' <label class="control-label" for="insideView">Contains inside view?</label>'+
					' <div class="controls">'+
						' <input name="markers['+(counter-1)+'].insideView" value="${marker.insideView}"'+
						' class="input-xlarge" data-validate="submit" type="hidden" id="txtInsideView_'+counter+'"  />'+
						' <input type="checkbox" id="btnInside_'+counter+'" onclick="checkInside('+counter+');"/>'+	
					' </div>'+
				' </div>';
 
				document.getElementById(parentdiv).appendChild(newdiv);
	
				$('html, body').animate({
					scrollTop : $("#marker_" + counter).offset().top -90
				}, 500);
			}	
 
			function removeMarker(parentdiv) {
				 
				if (counter == 0) {					
				} else {
					var removediv = document.getElementById('new_marker_'+counter);
					document.getElementById(parentdiv).removeChild(removediv);
					counter--; 
				}
		
				// give focus to the newly added input
				$('html, body').animate({
					scrollTop : $("#marker_" + counter).offset().top -90
				}, 500);
			}
		 
			function enableEdit(index) {
				var target = index;
				var editTitle = document.getElementById('txtTitle_'+target);
				editTitle.disabled=false;
				var editSnippet = document.getElementById('txtSnippet_'+target);
				editSnippet.disabled=false;
				var editLongitude = document.getElementById('txtLongitude_'+target);
				editLongitude.disabled=false;
				var editLatitude = document.getElementById('txtLatitude_'+target);
				editLatitude.disabled=false;
				var editInsideView = document.getElementById('txtInsideView_'+target);
				editInsideView.disabled=false;
				var btnInsideView = document.getElementById('btnInside_'+target);
				btnInsideView.disabled=false;
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
			
			function checkInside(index) {
				var target = index;
				var inside =document.getElementById('txtInsideView_'+target);
				if(document.getElementById('btnInside_'+target).checked) {
				     inside.value = "1";
				}
				else {
					 inside.value = "0";
				}	
			}
			
			function isFloat (n) {
				  return n===+n && n!==(n|0);
			}

			function formValidation()
			{
				var ok = true;
				var input = document.getElementsByTagName('input'); 
				var errorcode = 0;
				for (var i=0; i<input.length; i++) 
				{
				    if (input[i].type == 'text') 
				    {
				    	if(input[i].value==null||input[i].value=="")
				    	{
							ok =false;
							errorcode = 1;
						    break;					   
				    	}
				     
				    } 
				}
				/*
				if(ok)
				{
					for (var i=1; i<=counter; i++) 
					{
						var lon = document.getElementById('txtLongitude_'+counter);
						var lat = document.getElementById('txtLatitude_'+counter);
					  
						if (isFloat(lon.value))
					  	{
							ok =false;
							errorcode = 1;
						    break;	
					  	}
						
						if (isFloat(lat.value))
					  	{
							ok =false;
							errorcode = 1;
						    break;	
					  	}
						  
					}
				}
				*/
				//parseFloat("10")
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
				<h3>Google Map:</h3>
				<form:form method="post" action="googlemap" modelAttribute="mapForm" class="form-horizontal" name="myForm" id="myForm">   
					<fieldset>
						<div id="dynamicInputMap"> 
						    <c:forEach items="${mapForm.markers}" var="marker" varStatus="status">
						        <div id="marker_${status.count}">
						        	<div class="control-group">
										<label class="control-label" for="title" >Landscape ${status.count}: Title:</label>
										<div class="controls">
											<input name="markers[${status.index}].title" value="${marker.title}" maxlength="50"
												class="input-xlarge" data-validate="submit" type="text" id="txtTitle_${status.count}" disabled="disabled"/>		 
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="snippet">Snippet:</label>
										<div class="controls">
											<input name="markers[${status.index}].snippet" value="${marker.snippet}" maxlength="127"
												class="input-xlarge" data-validate="submit" type="text" id="txtSnippet_${status.count}" disabled="disabled"/>			
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="longitude">Longitude:</label>
										<div class="controls">
											<input name="markers[${status.index}].longitude" value="${marker.longitude}" maxlength="20" 
												class="input-xlarge" data-validate="submit" type="text" id="txtLongitude_${status.count}" disabled="disabled"/>			
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="latitude">Latitude:</label>
										<div class="controls">
											<input name="markers[${status.index}].latitude" value="${marker.latitude}" maxlength="20"
												class="input-xlarge" data-validate="submit" type="text" id="txtLatitude_${status.count}" disabled="disabled"/>			
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="insideView">Contains inside view?</label>
										<div class="controls">								 
											<input name="markers[${status.index}].insideView" value="${marker.insideView}"
												class="input-xlarge" data-validate="submit" type="hidden" id="txtInsideView_${status.count}" disabled="disabled"/>			
											<c:if  test="${marker.insideView=='1'}">   
												<input type="checkbox" id="btnInside_${status.count}" checked = "checked" onclick="checkInside(${status.count});" disabled="disabled"/>	
											</c:if>
											<c:if  test="${marker.insideView=='0'}">   
												<input type="checkbox" id="btnInside_${status.count}" onclick="checkInside(${status.count});" disabled="disabled"/>	
											</c:if>
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
										 	<input name="markers[${status.index}].edited"  value="${marker.edited}"  type="hidden" id="edit_${status.count}"/>
											<label style="background-color: transparent;					
													border: none;
													color: blue;
													cursor: pointer;">
													<input type="checkbox" id="btnRemove_${status.count}" onclick="checkRemove(${status.count});"/>				 
													remove
											</label>
											<input name="markers[${status.index}].removed"  value="${marker.removed}"  type="hidden" id="remove_${status.count}"/>
											<input name="markers[${status.index}].id" value="${marker.id}" type="hidden"/>
										</div>
									</div>
						        </div>
						    </c:forEach>
					   	</div>
					   	<label class="control-label"></label>
						<div class="controls">
							<input class="btn btn-primary" type="button"
								value="Add New" onclick="addMarker('dynamicInputMap',${mapFormLength});" id="btnAddNew">
							<input class="btn btn-primary" type="button"
								value="Remove" onclick="removeMarker('dynamicInputMap');" id="btnRemoveNew">
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
				 
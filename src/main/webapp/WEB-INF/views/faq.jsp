<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>FAQ</title>
		<link href="resources/css/bootstrap.min.css" rel="stylesheet">
		<script type="text/javascript">
			var counter;
		    var firstnew = true;
			function addFAQ(parentdiv,length) { 
				if (firstnew) {
					counter = length+1;	
					firstnew = false;
				}
				else {
					counter++;
				}	
				 
				var newdiv = document.createElement('div');
				var divIdName = 'new_faq_'+counter;
				newdiv.setAttribute('id',divIdName);
			 
				newdiv.innerHTML = 
				' <div class="control-group">'+
					' <label class="control-label" for="question"> '+counter+': Question: </label>'+ 
					' <div class="controls">'+
						' <textarea name="questions['+(counter-1)+'].question" maxlength="300"'+
							' class="input-xlarge" data-validate="submit" rows=3>${faq.question}</textarea>'+
					' </div>'+
				' </div>'+
				' <div class="control-group">'+
					' <label class="control-label" for="answer">Answer: </label>'+
					' <div class="controls">'+
						' <textarea name="questions['+(counter-1)+'].answer" maxlength="500"'+
							' class="input-xlarge" data-validate="submit" rows=5>${faq.answer}</textarea>'+
					' </div>'+
				' </div>'+
				' <div class="control-group">'+
					' <label class="control-label" for="keyword">Keyword: </label>'+
					' <div class="controls">'+
						' <input name="questions['+(counter-1)+'].keyword" maxlength="255" id="txtKeyword_'+counter+'"'+
							' class="input-xlarge" data-validate="submit" type="text" value="" />'+
					' </div>'+
				' </div>';
				
				document.getElementById(parentdiv).appendChild(newdiv);
	
				$('html, body').animate({
					scrollTop : $("#faq_" + counter).offset().top -90
				}, 500);
			}	
			
			function removeFAQ(parentdiv) {
				 
				if (counter == 0) {					
				} else {
					var removediv = document.getElementById('new_faq_'+counter);
					document.getElementById(parentdiv).removeChild(removediv);
					counter--; 
				}
		
				// give focus to the newly added input
				$('html, body').animate({
					scrollTop : $("#faq_" + counter).offset().top -90
				}, 500);
			}
		 
			function enableEdit(index) {
				var target = index;
				var editQuestion = document.getElementById('txtQuestion_'+target);
				editQuestion.disabled=false;
				var editAnswer = document.getElementById('txtAnswer_'+target);
				editAnswer.disabled=false;
				var editKeyword = document.getElementById('txtKeyword_'+target);
				editKeyword.disabled=false;
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
				{
					var textarea = document.getElementsByTagName('textarea'); 
					
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
				<h3>FAQs:</h3>
				<form:form method="post" action="faq" modelAttribute="faqForm" class="form-horizontal" name="myForm" id="myForm">   
					<fieldset>
						<div id="dynamicInputFAQ"> 
						    <c:forEach items="${faqForm.questions}" var="faq" varStatus="status">
						        <div id="faq_${status.count}">
						        	<div class="control-group">
										<label class="control-label" for="question" >${status.count}: Question:</label>
										<div class="controls">
											<textarea name="questions[${status.index}].question"  
												class="input-xlarge" data-validate="submit" rows=3
												 id="txtQuestion_${status.count}" disabled="disabled" maxlength="300">${faq.question}</textarea>		 
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="answer">Answer:</label>
										<div class="controls">
											<textarea name="questions[${status.index}].answer" rows=5
												class="input-xlarge" data-validate="submit" 
												id="txtAnswer_${status.count}" disabled="disabled" maxlength="500">${faq.answer}</textarea>											
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="answer">Keyword:</label>
										<div class="controls">
											<input name="questions[${status.index}].keyword" value="${faq.keyword}" maxlength="255"
														class="input-xlarge" data-validate="submit" type="text" id="txtKeyword_${status.count}" disabled="disabled"/>											
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
										 	<input name="questions[${status.index}].edited"  value="${faq.edited}"  type="hidden" id="edit_${status.count}"/>
											<label style="background-color: transparent;					
													border: none;
													color: blue;
													cursor: pointer;">
													<input type="checkbox" id="btnRemove_${status.count}" onclick="checkRemove(${status.count});"/>				 
													remove
											</label>
											<input name="questions[${status.index}].removed"  value="${faq.removed}"  type="hidden" id="remove_${status.count}"/>
											<input name="questions[${status.index}].id" value="${faq.id}" type="hidden"/>
										</div>
									</div>
						        </div>
						    </c:forEach>
					   	</div>
					   	<label class="control-label"></label>
						<div class="controls">
							<input class="btn btn-primary" type="button"
								value="Add New" onclick="addFAQ('dynamicInputFAQ',${faqFormLength});" id="btnAddNew">
							<input class="btn btn-primary" type="button"
								value="Remove" onclick="removeFAQ('dynamicInputFAQ');" id="btnRemoveNew">
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
				 
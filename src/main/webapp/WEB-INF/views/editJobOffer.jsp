<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/views/template/header.jsp" %>

<br/>
<br/>

<div class="container-wrapper" style="background-color:white;">
    <div class="container">
        <div class="page-header">
            <h1>Edit Job Offer</h1>

            <p class="lead">Please update the job offer information here:</p>
        </div>

        <form:form id="jobForm" action="${pageContext.request.contextPath}/employer/jobOfferInventory/editJobOffer" method="post"
                   commandName="jobOffer" enctype="multipart/form-data">
        <form:hidden path="jobId" value="${jobOffer.jobId}" />
        <form:hidden path="location.locationId" value="${jobOffer.location.locationId}" />

<div class="form-group">
            <label for="category">Category</label>
            <label class="checkbox-inline"><form:radiobutton path="category" id="category"
                                                             value="IT" required="required"/>IT</label>
            <label class="checkbox-inline"><form:radiobutton path="category" id="category"
                                                             value="Financial" required="required"/>Financial</label>
            <label class="checkbox-inline"><form:radiobutton path="category" id="category"
                                                            value="Logistic" required="required"/>Logistic</label>
        <div class="help-block with-errors"></div>
        </div>

        <div class="form-group">
            <label class="control-label" for="companyLogo">Upload Company Logo</label>
            <form:input id="companyLogo" path="companyLogo" type="file" class="form:input-large" required="required"/>
        </div>

        <div class="form-group">
            <label for="position">Position</label>
             <form:errors path="position" cssStyle="color: red;" />
            <form:input path="position" id="position" class="form-Control" value="${jobOffer.position}" required="required" data-error="Please enter your Position."/>
        </div>

        <div class="form-group">
            <label for="expirationDate">Expiration Date:</label>
            <form:input type="date" class="form-control" path="expirationDate" id="expirationDate" required="required" data-error="Please enter your Expiration Date."/>
        </div>

        <div class="form-group">
            <label for="location">Location</label>
            <form:input path="location.Country" id="country" class="form-Control" value="${jobOffer.location.country}" required="required" data-error="Please enter your Country"/>
            <form:input path="location.City" id="city" class="form-Control" value="${jobOffer.location.city}" required="required" data-error="Please enter your City."/>
        </div>

        <div class="form-group">
            <label for="companyName">Company Name</label>
            <form:input path="companyName" id="companyName" class="form-Control" value="${jobOffer.companyName}" required="required" data-error="Please enter your Company Name."/>
        </div>

        <div class="form-group green-border-focus">
          <label for="shortDescription">Short Description (on the Main Page)</label>
          <form:textarea path="shortDescription" id="shortDescription" class="form-control" value="${jobOffer.shortDescription}"  rows="3" required="required" data-error="Please enter your Short Description"/>
        </div>

       <div class="form-group">
        <label for="description">Description</label>
       <textarea id="txtEditor" name="txtEditor" value="${jobOffer.description}"/></textarea>
       <form:textarea path="description" id="txtEditorContent" name="txtEditorContent" value="${jobOffer.description}" hidden="hidden"/>
       </div>

       <div class="form-group">
        <label for="responsibilities">Responsibilities</label>
       <textarea id="txtEditor2" name="txtEditor2"/></textarea>
       <form:textarea path="responsibilities" id="txtEditorContent2" name="txtEditorContent2" value="${jobOffer.responsibilities}" hidden="hidden"/>
       </div>

       <div class="form-group">
        <label for="requirements">Job Requirements</label>
       <textarea id="txtEditor3" name="txtEditor3"/></textarea>
       <form:textarea path="requirements" id="txtEditorContent3" name="txtEditorContent3" value="${jobOffer.requirements}" hidden="hidden"/>
       </div>

       <div class="form-group">
        <label for="preferredSkills">Preferred Skills</label>
       <textarea id="txtEditor4" name="txtEditor4"/></textarea>
       <form:textarea path="preferredSkills" id="txtEditorContent4" name="txtEditorContent4" value="${jobOffer.preferredSkills}" hidden="hidden"/>
       </div>

       <div class="form-group">
        <label for="benefits">Benefits</label>
       <textarea id="txtEditor5" name="txtEditor5"/></textarea>
       <form:textarea path="benefits" id="txtEditorContent5" name="txtEditorContent5" value="${jobOffer.benefits}" hidden="hidden"/>
       </div>

        <div class="form-group green-border-focus">
          <label for="Additional Informations">Additional information</label>
          <form:textarea path="additionalInfo" id="additionalInfo" class="form-control" value="${jobOffer.additionalInfo}" rows="4"/>
        </div>

        <br><br>
        <input type="submit" value="Next" class="btn btn-default" id="sendButton">
        <a href="<c:url value="/employer/jobOfferInventory" />" class="btn btn-default">Cancel</a>
        </form:form>

        <br/>
        <br/>
        <br/>


        <%@include file="/WEB-INF/views/template/footer.jsp" %>
        <script src="https://code.jquery.com/jquery-2.1.0.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script>
        <script src="<c:url value="/resources/js/editor.js" />"></script>
        <script src="<c:url value="/resources/js/editJobOffer.js" />"></script>

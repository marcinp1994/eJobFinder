<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/views/template/header.jsp" %>

<br/>
<br/>
<div class="container-wrapper" style="background-color:white;">
    <div class="container">
        <div class="page-header">
            <h1>Add job offer</h1>

            <p class="lead">Fill the below information to add a job offer:</p>
        </div>

        <form:form action="${pageContext.request.contextPath}/employer/${jobOffer.employer.employerId}/jobOfferInventory/addJobOffer" method="post"
                   commandName="jobOffer" enctype="multipart/form-data">

        <div class="form-group">
            <label for="category">Category</label>
            <form:errors path="category" cssStyle="color: red;" />
            <label class="checkbox-inline"><form:radiobutton path="category" id="category"
                                                             value="IT" required="required"/>IT</label>
            <label class="checkbox-inline"><form:radiobutton path="category" id="category"
                                                             value="financial" required="required"/>Financial</label>
            <label class="checkbox-inline"><form:radiobutton path="category" id="category"
                                                            value="logistic" required="required"/>Logistic</label>
        <div class="help-block with-errors"></div>

        </div>

        <div class="form-group">
            <label class="control-label" for="companyLogo">Upload Company Logo</label>
            <form:errors path="companyLogo" cssStyle="color: red;" />
            <form:input id="companyLogo" path="companyLogo" type="file" class="form:input-large" required="required"/>
        </div>

        <div class="form-group">
            <label for="position">Position</label>
            <form:errors path="position" cssStyle="color: red;" />
            <form:input path="position" id="position" class="form-Control" required="required" data-error="Please enter your Position."/>
        </div>

        <div class="form-group">
            <label for="expirationDate">Expiration Date:</label>
             <form:errors path="expirationDate" cssStyle="color: red;" />
            <form:input type="date" class="form-control" path="expirationDate" id="expirationDate" required="required" data-error="Please enter your Expiration Date."/>
        </div>

        <div class="form-group">
            <label for="location">Location</label>
            <form:errors path="location" cssStyle="color: red;" />
            <form:input path="location.Country" id="country" class="form-Control" placeholder="Country" required="required" data-error="Please enter your Country"/>
            <form:input path="location.City" id="city" class="form-Control" placeholder="City" required="required" data-error="Please enter your City."/>
        </div>

        <div class="form-group">
            <label for="companyName">Company Name</label>
             <form:errors path="companyName" cssStyle="color: red;" />
            <form:input path="companyName" id="companyName" class="form-Control" required="required" data-error="Please enter your Company Name."/>
        </div>

        <div class="form-group green-border-focus">
          <label for="shortDescription">Short Description (on the Main Page)</label>
           <form:errors path="shortDescription" cssStyle="color: red;" />
          <form:textarea path="shortDescription" id="shortDescription" class="form-control"  rows="3" required="required" data-error="Please enter your Short Description"/>
        </div>

       <div class="form-group">
        <label for="description">Description</label>
         <form:errors path="description" cssStyle="color: red;" />
       <textarea id="txtEditor" name="txtEditor"/></textarea>
       <form:textarea path="description" id="txtEditorContent" name="txtEditorContent"  hidden="hidden"/>
       </div>

       <div class="form-group">
        <label for="responsibilities">Responsibilities</label>
         <form:errors path="responsibilities" cssStyle="color: red;" />
       <textarea id="txtEditor2" name="txtEditor2"/></textarea>
       <form:textarea path="responsibilities" id="txtEditorContent2" name="txtEditorContent2" hidden="hidden"/>
       </div>

       <div class="form-group">
        <label for="requirements">Job Requirements</label>
         <form:errors path="requirements" cssStyle="color: red;" />
       <textarea id="txtEditor3" name="txtEditor3"/></textarea>
       <form:textarea path="requirements" id="txtEditorContent3" name="txtEditorContent3" hidden="hidden"/>
       </div>

       <div class="form-group">
        <label for="preferredSkills">Preferred Skills</label>
        <form:errors path="preferredSkills" cssStyle="color: red;" />
       <textarea id="txtEditor4" name="txtEditor4"/></textarea>
       <form:textarea path="preferredSkills" id="txtEditorContent4" name="txtEditorContent4" hidden="hidden"/>
       </div>

       <div class="form-group">
        <label for="benefits">Benefits</label>
        <form:errors path="benefits" cssStyle="color: red;" />
       <textarea id="txtEditor5" name="txtEditor5"/></textarea>
       <form:textarea path="benefits" id="txtEditorContent5" name="txtEditorContent5" hidden="hidden"/>
       </div>

        <div class="form-group green-border-focus">
          <label for="Additional Informations">Additional information</label>
          <form:textarea path="additionalInfo" id="additionalInfo" class="form-control"  rows="4"/>
        </div>

        <br><br>
        <input type="submit" value="submit" class="btn btn-default">
        <a href="<c:url value="/employer/${jobOffer.employer.employerId}/jobOfferInventory/addJobOffer" />" class="btn btn-default">Cancel</a>
    </form:form>

<br/>
<br/>
<br/>

<%@include file="/WEB-INF/views/template/footer.jsp" %>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>
<script src="<c:url value="/resources/js/editor.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/addJobOffer.js" />"></script>


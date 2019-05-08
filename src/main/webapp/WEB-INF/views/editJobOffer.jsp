<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/views/template/header.jsp" %>


<div class="container-wrapper" style="background-color:white;">
    <div class="container">
        <div class="page-header">
            <h1>Edit Job Offer</h1>

            <p class="lead">Please update the job offer information here:</p>
        </div>

        <form:form action="${pageContext.request.contextPath}/employer/${employerId}/jobOfferInventory/editJobOffer" method="post"
                   commandName="jobOffer" enctype="multipart/form-data">
        <form:hidden path="jobId" value="${jobOffer.jobId}" />
        <form:hidden path="location.locationId" value="${jobOffer.location.locationId}" />

<div class="form-group">
            <label for="category">Category</label>
            <label class="checkbox-inline"><form:radiobutton path="category" id="category"
                                                             value="IT" />IT</label>
            <label class="checkbox-inline"><form:radiobutton path="category" id="category"
                                                             value="financial" />Financial</label>
            <label class="checkbox-inline"><form:radiobutton path="category" id="category"
                                                            value="logistic" />Logistic</label>
        </div>

        <div class="form-group">
            <label class="control-label" for="companyLogo">Upload Company Logo</label>
            <form:input id="companyLogo" path="companyLogo" type="file" class="form:input-large" />
        </div>

        <div class="form-group">
            <label for="position">Position</label>
             <form:errors path="position" cssStyle="color: red;" />
            <form:input path="position" id="position" class="form-Control" value="${jobOffer.position}"/>
        </div>

        <div class="form-group">
            <label for="expirationDate">Expiration Date:</label>
            <form:input type="date" path="expirationDate" id="expirationDate"/>
        </div>

        <div class="form-group">
            <label for="location">Location</label>
            <form:input path="location.Country" id="country" class="form-Control" value="${jobOffer.location.country}"/>
            <form:input path="location.City" id="city" class="form-Control" value="${jobOffer.location.city}"/>
        </div>

        <div class="form-group">
            <label for="companyName">Company Name</label>
            <form:input path="companyName" id="companyName" class="form-Control" value="${jobOffer.companyName}"/>
        </div>

        <div class="form-group green-border-focus">
          <label for="shortDescription">Short Description (on the Main Page)</label>
          <form:textarea path="shortDescription" id="shortDescription" class="form-control" value="${jobOffer.shortDescription}"  rows="3"/>
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
        <input type="submit" value="submit" class="btn btn-default">
        <a href="<c:url value="/employer/${jobOffer.employer.employerId}/jobOfferInventory" />" class="btn btn-default">Cancel</a>
        </form:form>

        <br/>
        <br/>
        <br/>


        <%@include file="/WEB-INF/views/template/footer.jsp" %>
        <script src="https://code.jquery.com/jquery-2.1.0.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <script src="<c:url value="/resources/js/editor.js" />"></script>
        <script>
        $(document).ready(function() {

        $("#txtEditor").Editor();
        $("#txtEditor").Editor("setText", '${jobOffer.responsibilities}');
        $("input:submit").click(function(){
        $('#txtEditorContent').text($('#txtEditor').Editor("getText"));});

        $("#txtEditor2").Editor();
        $("#txtEditor2").Editor("setText", '${jobOffer.responsibilities}');
        $("input:submit").click(function(){
        $('#txtEditorContent2').text($('#txtEditor2').Editor("getText"));});

        $("#txtEditor3").Editor();
        $("#txtEditor3").Editor("setText", '${jobOffer.requirements}');
        $("input:submit").click(function(){
        $('#txtEditorContent3').text($('#txtEditor3').Editor("getText"));});

        $("#txtEditor4").Editor();
        $("#txtEditor4").Editor("setText", '${jobOffer.preferredSkills}');
        $("input:submit").click(function(){
        $('#txtEditorContent4').text($('#txtEditor4').Editor("getText"));});

        $("#txtEditor5").Editor();
        $("#txtEditor5").Editor("setText", '${jobOffer.benefits}');
        $("input:submit").click(function(){
        $('#txtEditorContent5').text($('#txtEditor5').Editor("getText"));});

        });
        </script>

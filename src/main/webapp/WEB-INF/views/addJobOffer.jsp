<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/views/template/header.jsp" %>


<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Add job offer</h1>

            <p class="lead">Fill the below information to add a job offer:</p>
        </div>

        <form:form action="${pageContext.request.contextPath}/employer/${jobOffer.employer.employerId}/jobOfferInventory/addJobOffer" method="post"
                   commandName="jobOffer" enctype="multipart/form-data">

        <div class="form-group">
            <label for="category">Category</label>
            <label class="checkbox-inline"><form:radiobutton path="category" id="category"
                                                             value="IT" />IT</label>
            <label class="checkbox-inline"><form:radiobutton path="category" id="category"
                                                             value="financial" />Financial</label>
            <label class="checkbox-inline"><form:radiobutton path="category" id="category"                                                            value="logistic" />Logistic</label>
        </div>

        <div class="form-group">
            <label class="control-label" for="companyLogo">Upload Company Logo</label>
            <form:input id="companyLogo" path="companyLogo" type="file" class="form:input-large" />
        </div>

        <div class="form-group">
            <label for="position">Position</label>
            <form:input path="position" id="position" class="form-Control"/>
        </div>

        <div class="form-group">
            <label for="companyName">Company Name</label>
            <form:input path="companyName" id="companyName" class="form-Control"/>
        </div>

        <div class="form-group green-border-focus">
          <label for="shortDescription">Short Description (on the Main Page)</label>
          <form:textarea path="shortDescription" id="shortDescription" class="form-control"  rows="3"/>
        </div>

       <div class="form-group">
        <label for="description">Description</label>
       <textarea id="txtEditor" name="txtEditor"/></textarea>
       <form:textarea path="description" id="txtEditorContent" name="txtEditorContent" hidden="hidden"/>
       </div>

       <div class="form-group">
        <label for="responsibilities">Responsibilities</label>
       <textarea id="txtEditor2" name="txtEditor2"/></textarea>
       <form:textarea path="responsibilities" id="txtEditorContent2" name="txtEditorContent2" hidden="hidden"/>
       </div>

       <div class="form-group">
        <label for="requirements">Job Requirements</label>
       <textarea id="txtEditor3" name="txtEditor3"/></textarea>
       <form:textarea path="requirements" id="txtEditorContent3" name="txtEditorContent3" hidden="hidden"/>
       </div>

       <div class="form-group">
        <label for="preferredSkills">Preferred Skills</label>
       <textarea id="txtEditor4" name="txtEditor4"/></textarea>
       <form:textarea path="preferredSkills" id="txtEditorContent4" name="txtEditorContent4" hidden="hidden"/>
       </div>

       <div class="form-group">
        <label for="benefits">Benefits</label>
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


<%@include file="/WEB-INF/views/template/footer.jsp" %>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/js/editor.js" />"></script>
<script type="text/javascript">
$(document).ready( function() {
$("#txtEditor").Editor();
$("input:submit").click(function(){
$('#txtEditorContent').text($('#txtEditor').Editor("getText"));});});
</script>

<script type="text/javascript">
$(document).ready( function() {
$("#txtEditor2").Editor();
$("input:submit").click(function(){
$('#txtEditorContent2').text($('#txtEditor2').Editor("getText"));});});
</script>

<script type="text/javascript">
$(document).ready( function() {
$("#txtEditor3").Editor();
$("input:submit").click(function(){
$('#txtEditorContent3').text($('#txtEditor3').Editor("getText"));});});
</script>

<script type="text/javascript">
$(document).ready( function() {
$("#txtEditor4").Editor();
$("input:submit").click(function(){
$('#txtEditorContent4').text($('#txtEditor4').Editor("getText"));});});
</script>

<script type="text/javascript">
$(document).ready( function() {
$("#txtEditor5").Editor();
$("input:submit").click(function(){
$('#txtEditorContent5').text($('#txtEditor5').Editor("getText"));});});
</script>

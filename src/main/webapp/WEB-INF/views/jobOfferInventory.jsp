<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="/WEB-INF/views/template/header.jsp"%>


<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Job offer Inventory Page</h1>

            <p class="lead">This is the job offer inventory page!</p>
        </div>

        <table class="table table-striped table-hover">
            <thead>
            <tr class="bg-success">
                  <th>Status</th>
                  <th>Position</th>
                  <th>Company Name</th>
                  <th>Description</th>
                  <th></th>
            </tr>
            </thead>
            <c:forEach items="${jobOffers}" var="jobOffer">
            <tr>
                <td><a href="<spring:url value="/jobOfferList/viewJobOffer/${jobOffer.jobId}" />">${jobOffer.position}</td>
                <td>${jobOffer.companyName}</td>
                <td>${jobOffer.shortDescription}</td>
                <td>
                    <a href="<spring:url value="/jobOfferList/viewJobOffer/${jobOffer.jobId}" />">
                    <span class="glyphicon glyphicon-info-sign"></span></a>
                    <a href="<spring:url value="/employer/${jobOffer.employer.employerId}/jobOfferInventory/deleteJobOffer/${jobOffer.jobId}" />">
                    <span class="glyphicon glyphicon-trash"></span></a>
                    <a href="<spring:url value="/employer/${jobOffer.employer.employerId}/jobOfferInventory/editJobOffer/${jobOffer.jobId}" />">
                    <span class="glyphicon glyphicon-edit"></span></a>
                </td>
            </tr>
            </c:forEach>
        </table>

        <a href="<spring:url value="/employer/${employerId}/jobOfferInventory/addJobOffer" />" class="btn btn-primary">Add Job Offer</a>

        <br/>
        <br/>
        <br/>
<%@include file="/WEB-INF/views/template/footer.jsp" %>


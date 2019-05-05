<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="/WEB-INF/views/template/header.jsp"%>

<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Job Offers</h1>

            <p class="lead">Checkout all the awesome list of job offers now! </p>
        </div>

        <table class="table table-striped table-hover">
            <thead>
                <tr class="bg-success">
                    <th>Photo Thumb</th>
                    <th>Position</th>
                    <th>Company Name</th>
                    <th>Description</th>
                    <th>Salary</th>
                    <th></th>
                </tr>
            </thead>
            <c:forEach items="${jobOffers}" var="jobOffer">
            <tr>
                <td><a href="<spring:url value="/jobOfferList/viewJobOffer/${jobOffer.jobId}" />"><img src="<c:url value="/resources/images/${jobOffer.jobId}.png" /> " style="width:100%" alt="image" /></td>
                <td><a href="<spring:url value="/jobOfferList/viewJobOffer/${jobOffer.jobId}" />">${jobOffer.position}</td>
                <td>${jobOffer.companyName}</td>
                <td>${jobOffer.shortDescription}</td>
                <td>${jobOffer.salary}</td>
                <td><a href="<spring:url value="/jobOfferList/viewJobOffer/${jobOffer.jobId}" />">
                <span class="glyphicon glyphicon-info-sign"></span></td>
            </tr>
            </c:forEach>
        </table>

<%@include file="/WEB-INF/views/template/footer.jsp"%>


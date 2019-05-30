<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="/WEB-INF/views/template/header.jsp"%>
<br/>
<br/>

<security:authorize access="isAuthenticated()">
                <security:authentication property="principal.username" var="username"/>
</security:authorize>

<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Job offer Inventory Page</h1>

            <p class="lead">This is the job offer inventory page!</p>
        </div>

        <table class="table">
            <thead>
            <tr class="bg-success">
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
                    <span class="fas fa-info"></span></a>
                    <a href="<spring:url value="/employer/jobOfferInventory/deleteJobOffer/${jobOffer.jobId}" />">
                    <span class="fas fa-trash-alt"></span></a>
                    <a href="<spring:url value="/employer/jobOfferInventory/editJobOffer/${jobOffer.jobId}" />">
                    <span class="fas fa-edit"></span></a>
                    <a href="<spring:url value="/employer/jobOfferInventory/perfectEmployee/${jobOffer.jobId}" />">
                    <span class="fas fa-user"></span></a>
                               <c:choose>
                                  <c:when test="${jobOffer.containsRules}">
<a><span class="fas fa-user-check" style=""></span></a>
                                 <a href="<spring:url value="/jobOfferList/viewJobOffer/${jobOffer.jobId}" /> >
                                        <span class="fas fa-user-check"></span></a>
                                  </c:when>

                                </c:choose>

                </td>
            </tr>
            </c:forEach>
        </table>

        <a href="<spring:url value="/employer/jobOfferInventory/addJobOffer" />" class="btn btn-primary">Add Job Offer</a>

        <br/>
        <br/>
        <br/>
<%@include file="/WEB-INF/views/template/footer.jsp" %>



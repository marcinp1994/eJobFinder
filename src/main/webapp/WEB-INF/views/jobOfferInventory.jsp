<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@include file="/WEB-INF/views/template/header.jsp"%>

            <br/>
            <br/>
            <security:authorize access="isAuthenticated()">
                <security:authentication property="principal.username" var="username" />
            </security:authorize>

            <div class="modal fade" id="modalA" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Success</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Candidate will be informed about your decision.</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-wrapper">
                <div class="container">
                    <div class="page-header">
                        <h1>Job offer Inventory Page</h1>
                        <c:if test="${isPremium}">
                            <h3><span style="float:right">  <i class="fas fa-1x fa-star"></i> Premium member</span></h3>
                        </c:if>
                        <p class="lead">This is the job offer inventory page!</p>
                    </div>

                    <table class="table">
                        <thead>
                            <tr class="bg-success">
                                <th class="text-center">Position</th>
                                <th class="text-center">Company Name</th>
                                <th class="text-center">Description</th>
                                <th class="text-center"></th>
                            </tr>
                        </thead>
                        <c:forEach items="${jobOffers}" var="jobOffer">

                            <tr>
                                <td class="text-center"><a href="<spring:url value=" /jobOfferList/viewJobOffer/${jobOffer.jobId} " />">${jobOffer.position}</td>
                <td class="text-center">${jobOffer.companyName}</td>
                <td class="text-center" >${jobOffer.shortDescription}</td>
                <td class="text-center">
                    <a href="<spring:url value="/jobOfferList/viewJobOffer/${jobOffer.jobId}" />">
                    <span class="fas fa-info"></span></a>
                                    <a href="<spring:url value=" /eJobFinder/employer/jobOfferInventory/deleteJobOffer/${jobOffer.jobId} " />">
                                        <span class="fas fa-trash-alt"></span></a>
                                    <a href="<spring:url value=" /eJobFinder/employer/jobOfferInventory/editJobOffer/${jobOffer.jobId} " />">
                                        <span class="fas fa-edit"></span></a>
                                    <a href="<spring:url value=" /eJobFinder/employer/jobOfferInventory/perfectEmployee/${jobOffer.jobId} " />">
                                        <c:choose>
                                            <c:when test="${jobOffer.containsRules}">
                                                <span class="fas fa-user-check" style=""></span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="fas fa-user"></span>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${not empty jobOffer.jobOfferApplications}">
                                                <a href="#" data-toggle="modal" data-target="#modal${jobOffer.jobId}">
                                                    <span class="far fa-address-book" style=""></span>
                                                </a>
                                            </c:when>

                                        </c:choose>

                                    </a>
                                    <c:if test="${not empty jobOffer.potentialJobOfferApplicationsWithMin && isPremium}">
                                        <a href="#" data-toggle="modal" data-target="#modalPremium${jobOffer.jobId}">
                                            <span class="fas fa-search-plus"></span></a>
                                    </c:if>
                                </td>
                            </tr>

                        </c:forEach>
                    </table>
                    <c:forEach items="${jobOffers}" var="jobOffer">

                        <c:if test="${not empty jobOffer.jobOfferApplications}">
                            <div class="modal fade" id="modal${jobOffer.jobId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document" style="width:50%;">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="modalLabel${jobOffer.jobId}">Applications for ${jobOffer.position} in ${jobOffer.companyName}</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body" style="width:100%;">

                                            <table class="table">
                                                <thead class="thead-dark">
                                                    <tr>
                                                        <th scope="col">#</th>
                                                        <th scope="col">Name</th>
                                                        <th scope="col">Lastname</th>
                                                        <th scope="col">Score</th>
                                                        <th scope="col">Percent</th>
                                                         <th class="text-center" scope="col">CV</th>
                                                        <th scope="col">Candidate acceptance</th>
                                                        <th scope="col">My acceptance</th>
                                                        <th scope="col"></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${jobOffer.jobOfferApplications}" var="jobOfferApplication" varStatus="loop">
                                                        <tr>
                                                            <th scope="row">${loop.count}</th>
                                                            <td>${jobOfferApplication.candidate.name}</td>
                                                            <td>${jobOfferApplication.candidate.lastName}</td>
                                                            <td>${jobOfferApplication.calculatedScore}</td>
                                                            <td>${jobOfferApplication.percentOfMaxScore}</td>
                                                             <td>   <button type="button" class="btn btn-info btn-sm" >View CV</button></td>
                                                            <td class="text-center">
                                                                <c:choose>
                                                                    <c:when test="${jobOfferApplication.candidateAcceptancee}">
                                                                        <p>Yes</p>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <p>No</p>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                            </td>
                                                            <td class="text-center">
                                                                <c:choose>
                                                                    <c:when test="${jobOfferApplication.employerAcceptancee}">
                                                                        <p>Yes</p>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <p>No</p>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>
                                                                           <c:if test="${!jobOfferApplication.employerAcceptancee}">
                                                                <button type="button" class="btn btn-success" onclick="acceptByEmployer('${jobOffer.jobId}','${jobOfferApplication.candidate.candidateId}','true','modal${jobOffer.jobId}')">Accept</button>
                                                                </c:if>
                                                                <button type="button" class="btn btn-danger" onclick="acceptByEmployer('${jobOffer.jobId}','${jobOfferApplication.candidate.candidateId}','','modal${jobOffer.jobId}')">Decline</button>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${not empty jobOffer.potentialJobOfferApplicationsWithMin && isPremium}">
                            <div class="modal fade" id="modalPremium${jobOffer.jobId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document" style="width:50%;">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="modalLabel${jobOffer.jobId}">Poroposition from our database for ${jobOffer.position} in ${jobOffer.companyName}</h5>
                                               <h5 class="modal-title" id="modalLabel${jobOffer.jobId}">You can see here candidates which have more than threshold (${jobOffer.thresholdPercentagePoints}%) from maximal score: <b>${jobOffer.maximalPoints}</b>  </h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body" style="width:100%;">

                                            <table class="table">
                                                <thead class="thead-dark">
                                                    <tr>
                                                        <th scope="col">#</th>
                                                        <th scope="col">Name</th>
                                                        <th scope="col">Lastname</th>
                                                        <th scope="col">Score</th>
                                                        <th scope="col">Percent</th>
                                                        <th class="text-center" scope="col">CV</th>
                                                        <th scope="col">Candidate acceptance</th>
                                                        <th scope="col">My acceptance</th>
                                                        <th scope="col"></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${jobOffer.potentialJobOfferApplicationsWithMin}" var="jobOfferApplication" varStatus="loop">
                                                        <tr>
                                                            <th scope="row">${loop.count}</th>
                                                            <td>${jobOfferApplication.candidate.name}</td>
                                                            <td>${jobOfferApplication.candidate.lastName}</td>
                                                            <td>${jobOfferApplication.calculatedScore}</td>
                                                            <td>${jobOfferApplication.percentOfMaxScore}</td>
                                                                 <td>   <button type="button" class="btn btn-info btn-sm" >View CV</button></td>
                                                            <td class="text-center">
                                                                <c:choose>
                                                                    <c:when test="${jobOfferApplication.candidateAcceptancee}">
                                                                        <p>Yes</p>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <p>No</p>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                            </td>
                                                            <td class="text-center">
                                                                <c:choose>
                                                                    <c:when test="${jobOfferApplication.employerAcceptancee}">
                                                                        <p>Yes</p>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <p>No</p>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>
                                                                <button type="button" class="btn btn-success" onclick="acceptByEmployer('${jobOffer.jobId}','${jobOfferApplication.candidate.candidateId}','true','modalPremium${jobOffer.jobId}')">Invite</button>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>

                    </c:forEach>
                    <a href="<spring:url value=" /eJobFinder/employer/jobOfferInventory/addJobOffer " />" class="btn btn-primary">Add Job Offer</a>

                    <br/>
                    <br/>
                    <br/>
                    <script type="text/javascript" src="<c:url value="/resources/js/jobInventory.js " />"></script>
                    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
                    <%@include file="/WEB-INF/views/template/footer.jsp" %>
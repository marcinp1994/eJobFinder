<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@include file="/WEB-INF/views/template/header.jsp"%>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
                <br/>
                <br/>

                <div class="container-wrapper">
                    <div class="container">
                        <div class="page-header">
                            <h1>Job Offers report page</h1>
                            <p class="lead">You can check yours job offers. </p>
                        </div>
                        <div class="well well-sm">
                            <div class="btn-group">
                                <a href="#" id="list" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-th-list">
      </span>List</a> <a href="#" id="grid" class="btn btn-default btn-sm"><span
         class="glyphicon glyphicon-th"></span>Grid</a>
                            </div>
                        </div>
                        <div id="products" class="row list-group">
                            <c:forEach items="${offers}" var="jobOffer">
                                <div class="item  col-xs-4 col-lg-4">
                                    <div class="thumbnail">

                                        <div class="caption">
                                            <h3><a class="group inner list-group-item-heading" href="<spring:url value="/jobOfferList/viewJobOffer/${jobOffer.jobId}" />">
                  ${jobOffer.position}
                   </a>
               </h3>

                                            <p class="group inner list-group-item-text">
                                                ${jobOffer.companyName}
                                            </p>
                                            <p class="lead" style="color:#808080;font-size:15px;font-style:italic;">
                                                ${jobOffer.shortDescription}
                                            </p>
                                            <p class="lead" style="color:#808080;font-size:15px;font-style:italic;">
                                                All applications: ${fn:length(jobOffer.jobOfferApplications)}
                                            </p>

                                            <div class="row">
                                                <div class="col-xs-12 col-md-5">
                                                    <a class="btn btn-success" href="#" data-toggle="modal" data-target="#modal${jobOffer.jobId}" <c:if test="${empty jobOffer.jobOfferApplications}">
                        disabled
                         </c:if>
                      >View applications</a>
                                                </div>
                                                <div class="col-xs-12 col-md-6">

                                                    <c:if test="${not empty jobOffer.potentialJobOfferApplicationsWithMin && isPremium}">
                                                        <a class="btn btn-danger" href="#" data-toggle="modal" data-target="#modalPremium${jobOffer.jobId}">See potential candidates</a>
                                                    </c:if>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
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
                                                    <c:if test="${not empty jobOffer.jobOfferApplications}">
                                                        <h3>Number of applications for this offer: ${fn:length(jobOffer.jobOfferApplications)} </h3>
                                                        <div class="container">
                                                            <div class="row">
                                                                <div class="col-md-4 align-middle" style="margin-left:100px;">
                                                                    <canvas id="chart${jobOffer.jobId}" width="200" height="200" style="max-height:200px;max-width:200px;"></canvas>
                                                                </div>
                                                                <div class="col-md-4 align-middle">
                                                                    <canvas id="chart2${jobOffer.jobId}" width="200" height="200" style="max-height:200px;max-width:200px;"></canvas>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <script>
                                                            var chart$ {jobOffer.jobId} = new Chart(document.getElementById("chart${jobOffer.jobId}"), {
                                                                type: 'pie',
                                                                data: {
                                                                    labels: ["Valid", "Not valid"],
                                                                    datasets: [{
                                                                        label: "Population (millions)",
                                                                        backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f", "#e8c3b9", "#c45850"],
                                                                        data: [$ {
                                                                            fn: length(jobOffer.validJobOfferApplications)
                                                                        }, $ {
                                                                            jobOffer.invalidJobOfferApplications
                                                                        }]
                                                                    }]
                                                                },
                                                                options: {
                                                                    title: {
                                                                        display: true,
                                                                        text: 'Applications'
                                                                    }
                                                                }
                                                            });
                                                            var chart2$ {jobOffer.jobId} = new Chart(document.getElementById("chart2${jobOffer.jobId}"), {
                                                                type: 'pie',
                                                                data: {
                                                                    labels: ["Yes", "No"],
                                                                    datasets: [{
                                                                        label: "Population (millions)",
                                                                        backgroundColor: ["#3cba9f", "#e8c3b9", "#c45850"],
                                                                        data: [$ {
                                                                            jobOffer.numberOfJobOfferApplicationsWithAcceptance
                                                                        }, $ {
                                                                            jobOffer.numberOfJobOfferApplicationsWithoutAcceptance
                                                                        }]
                                                                    }]
                                                                },
                                                                options: {
                                                                    title: {
                                                                        display: true,
                                                                        text: 'Applications acceptance'
                                                                    }
                                                                }
                                                            });
                                                        </script>
                                                    </c:if>
                                                    <table class="table">
                                                        <thead class="thead-dark">
                                                            <tr>
                                                                <th class="text-center" scope="col">#</th>
                                                                <th class="text-center" scope="col">Name</th>
                                                                <th class="text-center" scope="col">Lastname</th>
                                                                <th class="text-center" scope="col">Score</th>
                                                                <th class="text-center" scope="col">Percent</th>
                                                                <th class="text-center" scope="col">Acceptance</th>
                                                                <th class="text-center" scope="col">Contact</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>

                                                            <c:forEach items="${jobOffer.jobOfferApplications}" var="jobOfferApplication" varStatus="loop">
                                                                <tr>
                                                                    <th scope="row">${loop.count}</th>
                                                                    <td class="text-center">${jobOfferApplication.candidate.name}</td>
                                                                    <td class="text-center">${jobOfferApplication.candidate.lastName}</td>
                                                                    <td class="text-center">${jobOfferApplication.calculatedScore}</td>
                                                                    <td class="text-center">${jobOfferApplication.percentOfMaxScore}</td>
                                                                    <td class="text-center">
                                                                        <c:choose>
                                                                            <c:when test="${jobOfferApplication.candidateAcceptancee}">
                                                                                <p>Yes</p>
                                                                    </td>
                                                                    <td>
                                                                        <a href="mailto:${jobOfferApplication.candidate.candidateEmail}" class="btn btn-success btn-sm">
                                                                            <span class="glyphicon glyphicon-envelope"></span> Mail</a>
                                                                        <span>&nbsp&nbsp</span>
                                                                        <a href="#" class="btn btn-info btn-sm">
                                                                            <span class="glyphicon glyphicon-phone"></span>${jobOfferApplication.candidate.candidatePhone}
                                                                        </a>
                                                                        </a>
                                                                    </td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <p>No</p>
                                                                        </td>
                                                                        <td> </td>
                                                                    </c:otherwise>
                                                                    </c:choose>

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
                                                    <h5 class="modal-title" id="modalLabel${jobOffer.jobId}">LIst of potential candidates which can meet yours codidtions for  ${jobOffer.position} in ${jobOffer.companyName}</h5>
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
                                                                        <button type="button" class="btn btn-success" onclick="acceptByEmployer('${jobOffer.jobId}','${jobOfferApplication.candidate.candidateId}','true','modal${jobOffer.jobId}')">Invite for apply</button>
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
                        </div>

                        <br/>
                        <br/>
                        <br/>
                        <%@include file="/WEB-INF/views/template/footer.jsp"%>
                            <script type="text/javascript" src="<c:url value="/resources/js/jobInventory.js " />"></script>
                            <script>
                                $(document).ready(function() {
                                    $('#list').click(function(event) {
                                        event.preventDefault();
                                        $('#products .item').addClass('list-group-item');
                                    });
                                    $('#grid').click(function(event) {
                                        event.preventDefault();
                                        $('#products .item').removeClass('list-group-item');
                                        $('#products .item').addClass('grid-group-item');
                                    });
                                });
                            </script>
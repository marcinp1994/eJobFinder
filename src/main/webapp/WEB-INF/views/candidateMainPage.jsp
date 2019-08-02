<%@include file="/WEB-INF/views/template/header.jsp"%>
    <br/>
    <br/>

    <div class="container-wrapper">
        <div class="container">
            <div class="page-header">
                <br>
                <h1>Candidate control panel</h1>

            </div>
            <c:set var="notify2" value="false" />
            <h1 class="display-4">Welcome, ${name} ${lastName}</h1>
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
                            <p>Employer will be informed about your decision.</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <div style="width:76%;margin-left:15%;height:25%; margin-top:5%;">
                <c:if test="${not empty applications}">
                    <div class="modal fade" id="modalList" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document" style="width:50%;">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modalLabel">Your apllications</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body" style="width:100%;">
                                    <table class="table" align="center" style="width:100%;">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Postion</th>
                                                <th scope="col">Company</th>
                                                <th scope="col">Acceptance</th>
                                                <th scope="col">My acceptance</th>
                                                <th scope="col"></th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach items="${applications}" var="jobOfferApplication" varStatus="loop">
                                                <c:set var="buttonsEnabled" value="true" />
                                                <tr>
                                                    <th scope="row">${loop.count}</th>
                                                    <td class="text-center">${jobOfferApplication.jobOffer.position}</td>
                                                    <td class="text-center">${jobOfferApplication.jobOffer.companyName}</td>
                                                    <td class="text-center">
                                                        <c:choose>
                                                            <c:when test="${jobOfferApplication.employerAcceptanceeAsInt == -1}">
                                                                <p>Rejected</p>
                                                                <c:set var="buttonsEnabled" value="false" />
                                                            </c:when>
                                                            <c:when test="${jobOfferApplication.employerAcceptanceeAsInt == 1}">
                                                                <p>Yes</p>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <p>No decision yet</p>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </td>
                                                    <td class="text-center">
                                                        <c:choose>
                                                            <c:when test="${jobOfferApplication.candidateAcceptancee}">
                                                                <p>Yes</p>
                                                                <c:set var="buttonsEnabled" value="false" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <p>No</p>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </td>
                                                    <td>       <c:choose>
                                                           <c:when test="${-1 	!=  jobOfferApplication.employerAcceptanceeAsInt}">
                                                              <button type="button" class="btn btn-success" onclick="acceptByCandidate('${jobOfferApplication.jobOffer.jobId}','${candidateId}','true','modalList')" <c:if test="${ not buttonsEnabled}">disabled</c:if>>Accept</button>
                                                              <button type="button" class="btn btn-danger" onclick="acceptByCandidate('${jobOfferApplication.jobOffer.jobId}','${candidateId}','','modalList')">Decline</button>
                                                           </c:when>
                                                                            <c:otherwise>
                                                                            <button type="button" class="btn btn-danger" onclick="acceptByCandidate('${jobOfferApplication.jobOffer.jobId}','${candidateId}','','modalList')">Remove</button>
                                                                                  </c:otherwise>                </c:choose>
                <a type="button" class="btn btn-info" href="<c:url value=" /eJobFinder/jobOfferList/viewJobOffer/${jobOfferApplication.jobOffer.jobId} " />">Details</a>
                </td>
                </tr>

                <c:if test="${jobOfferApplication.employerAcceptanceeAsInt == 1}">
                    <c:if test="${not jobOfferApplication.candidateAcceptancee}">
                        <c:set var="notify" value="true" />
                    </c:if>
                </c:if>

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
                <c:if test="${not empty propositions}">
                    <div class="modal fade" id="modalListProp" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document" style="width:50%;">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modalLabel">Job propositions for you</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body" style="width:100%;">
                                    <table class="table" align="center" style="width:100%;">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Postion</th>
                                                <th scope="col">Company</th>
                                                <th scope="col">Acceptance</th>
                                                <th scope="col">My acceptance</th>
                                                <th scope="col"></th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach items="${propositions}" var="jobOfferPropositions" varStatus="loop">
                                                <c:set var="buttonsEnabled" value="true" />
                                                <tr>
                                                    <th scope="row">${loop.count}</th>
                                                    <td class="text-center">${jobOfferPropositions.jobOffer.position}</td>
                                                    <td class="text-center">${jobOfferPropositions.jobOffer.companyName}</td>
                                                    <td class="text-center">
                                                        <c:choose>
                                                            <c:when test="${jobOfferPropositions.employerAcceptanceeAsInt == -1}">
                                                                <p>Rejected</p>
                                                                <c:set var="buttonsEnabled" value="false" />
                                                            </c:when>
                                                            <c:when test="${jobOfferPropositions.employerAcceptanceeAsInt == 1}">
                                                                <p>Yes</p>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <p>No decision yet</p>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </td>
                                                    <td class="text-center">
                                                        <c:choose>
                                                            <c:when test="${jobOfferPropositions.candidateAcceptancee}">
                                                                <p>Yes</p>
                                                                <c:set var="buttonsEnabled" value="false" />
                                                            </c:when>

                                                            <c:otherwise>
                                                                <p>No</p>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-success" onclick="acceptByCandidate('${jobOfferPropositions.jobOffer.jobId}','${candidateId}','true','modalListProp')" <c:if test="${ not buttonsEnabled}">disabled</c:if>>Accept</button>
                <button type="button" class="btn btn-danger" onclick="acceptByCandidate('${jobOfferPropositions.jobOffer.jobId}','${candidateId}','','modalListProp')">Decline</button>
                <a type="button" class="btn btn-info" href="<c:url value=" /eJobFinder/jobOfferList/viewJobOffer/${jobOfferPropositions.jobOffer.jobId} " />">Details</a>
                </td>
                </tr>

                <c:if test="${jobOfferPropositions.employerAcceptanceeAsInt == 1}">
                    <c:if test="${not jobOfferPropositions.candidateAcceptancee}">
                        <c:set var="notify2" value="true" />
                    </c:if>
                </c:if>

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
                <c:if test="${notify}">

                    <div class="alert alert-success" role="alert">
                        You have new acceptance for yours applications!
                    </div>

                </c:if>
                <c:if test="${notify2}">

                    <div class="alert alert-success" role="alert">
                        You have new job propositions!
                    </div>

                </c:if>
                <div>
                    <span>  <a href="<c:url value="/candidate/" />"  class="btn "><i class="fas fa-10x fa-id-card"></i>
                              <br />
                                  <b>Fill up your profile<b>
                              </a></span>
                    <span style="margin-left:30px;">  <a href="#" data-toggle="modal" data-target="#modalList" class="btn "><i class="far fa-10x fa-list-alt"></i>
                                                       <br />
                                                           <b>Your applications<b>
                                                        </a></span>

                                                  <span style="margin-left:30px;">  <a href="#" data-toggle="modal" data-target="#modalFileUpload" class="btn "><i class="fas fa-10x fa-file-upload""></i>
                                                                                                         <br />
                                                                                                            <b>Upload your CV<b>
                                                                                                          </a></span>
                    <c:if test="${not empty propositions}">
                        <span style="margin-left:30px;">  <a href="#" data-toggle="modal" data-target="#modalListProp" class="btn "><i class="fas fa-10x fa-hand-holding-usd"></i>
                                                                                    <br />
                                                                                        <b>Propositions for you<b>
                                                                                     </a></span>
                    </c:if>
                </div>
            </div>

                 <div class="modal fade" id="modalFileUpload" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document" style="width:50%;">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="modalLabel">CV upload</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body" style="width:100%;">
  <form id="cvUpload" action="${pageContext.request.contextPath}/candidate/uploadCV" method="post"
                   commandName="jobOffer" enctype="multipart/form-data">
  <div class="form-group">
    <label for="file">Choose your CV file</label>
    <input type="file" class="form-control-file" id="file" name="file">
     <input type="submit" value="Submit" />
  </div>
</form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>
                            </div>
                            </div>
                            </div>
            <br/>
            <br/>
            <br/>

            <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
            <script type="text/javascript" src="<c:url value=" /eJobFinder/resources/js/candidateMainPage.js " />"></script>
            <%@include file="/WEB-INF/views/template/footer.jsp" %>
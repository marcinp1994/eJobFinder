<%@include file="/WEB-INF/views/template/header.jsp"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <br/>
        <br/>
        <div class="container-wrapper">
            <div class="container">
                <div class="page-header">
                    <br>
                    <h1>Employer Control panel</h1>
                    <c:if test="${isPremium}">
                        <h3><span style="float:right">  <i class="fas fa-1x fa-star"></i> Premium member</span></h3>
                    </c:if>
                </div>
                <h1 class="display-4">Welcome, ${name} ${lastName}
    </h1>

                <c:if test="${notify}">
                    <div class="alert alert-success" role="alert">
                        You have new applications for your offers! Please check yours report
                    </div>
                </c:if>

                <div style="width:95%;margin-left:5%;height:25%; margin-top:5%;">

                    <div>

                        <span style="">  <a href=" #"  class="btn"><i class="fas fa-10x fa-user-edit"></i>
                                                   <br />
                                                       <b>Edit your profile<b>
                                                    </a></span>
                        <span style="margin-left:30px;">  <a href="<c:url value="/employer/jobOfferInventory/" />"  class="btn"><i class="far fa-10x fa-list-alt"></i>
                                                                              <br />
                                                                                  <b>Your job offers<b>
                                                                              </a></span>
                        <span style="margin-left:30px;">  <a href="<c:url value="/employer/jobOfferInventory/addJobOffer" />"  class="btn"><i class="far fa-10x fa-plus-square"></i>
                                                                                                                         <br />
                                                                                                               <b>Add job offer<b>
                                                                                           </a></span>
                        <span style="margin-left:30px;">  <a href="<c:url value="/employer/report/" />"  class="btn"><i class="fas fa-10x fa-file-invoice"></i>
                                              <br />
                                                  <b>Report<b>
                                              </a></span>

                        <c:if test="${isPremium}">
                            <span style="margin-left:30px;">  <a href="<c:url value="/employer/cancelPremium/" />"  class="btn"><i class="fas fa-10x fa-star"></i>
                                                                                              <br />
                                                                                                  <b>Cancel Premium Membership<b>
                                                                                              </a></span>
                        </c:if>

                        <c:if test="${!isPremium}">
                            <span style="margin-left:30px;">  <a href="<c:url value="/employer/buyPremium/" />"  class="btn"><i class="fas fa-10x fa-star"></i>
                                                                                              <br />
                                                                                                  <b>Buy Premium Membership<b>
                                                                                              </a></span>
                        </c:if>
                    </div>
                </div>

                <br/>
                <br/>
                <br/>
                <div class="modal fade" id="modalList" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document" style="width:50%;">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="modalLabel">Your offers</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body" style="width:100%;">
                                <table class="table" align="center" style="width:100%;">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th scope="col">#</th>
                                            <th class="text-center" scope="col">Postion</th>
                                            <th class="text-center" scope="col">Company</th>
                                            <th class="text-center" scope="col">Description</th>
                                            <th class="text-center" scope="col">Valid Applications</th>
                                            <th class="text-center" scope="col">Applications</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${offers}" var="jobOffer" varStatus="loop">
                                            <tr>
                                                <th scope="row">${loop.count}</th>
                                                <td class="text-center">${jobOffer.position}</td>
                                                <td class="text-center">${jobOffer.companyName}</td>
                                                <td class="text-center">${jobOffer.shortDescription}</td>
                                                <td class="text-center">${jobOffer.numberOfValidJobOfferApplications}
                                                </td>
                                                <td class="text-center">${fn:length(jobOffer.jobOfferApplications)}</td>
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
            </div>

        </div>
<%@include file="/WEB-INF/views/template/footer.jsp" %>
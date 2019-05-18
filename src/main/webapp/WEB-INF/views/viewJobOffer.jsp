<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="/WEB-INF/views/template/header.jsp"%>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
	<div class="container-wrapper">
		<div class="container">
			<div class="page-header">
				<h1>Job deatils</h1>
				<p class="lead">Here is a detail informations about the job!</p>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-sm-3">
						<img src="
							<c:url value="/resources/images/${jobOffer.jobId}.png" /> " alt="image"
                             style="width:100%"/>
						</div>
						<div class="col-md-5">
							<h3>
								<b>Position:</b> ${jobOffer.position}
							</h3>
							<p>
								<b>Company Name:</b> ${jobOffer.companyName}
							</p>
							<p>
								<b>Expiration Date:</b> ${expirationDate}
							</p>
							<p>
								<b>Location:</b> ${location}
							</p>
							<p>
								<b>Job ID:</b> ${jobOffer.jobId}
							</p>
						</div>
					</div>
					<br/>
					<br/>
					<h4>
						<b>Job Description</b>
					</h4>
					<p style="font-size:15px;">${jobOffer.description}</p>
					<br/>
					<br/>
					<h4>
						<b>Responsibilities:</b>
					</h4>
					<p style="font-size:15px;">${jobOffer.responsibilities}</p>
					<br/>
					<br/>
					<h4>
						<b>Job Requirements:</b>
					</h4>
					<p style="font-size:15px;">${jobOffer.requirements}</p>
					<br/>
					<br/>
					<h4>
						<b>Preferred Skills:</b>
					</h4>
					<p style="font-size:15px;">${jobOffer.preferredSkills}</p>
					<br/>
					<br/>
					<h4>
						<b>Benefits</b>
					</h4>
					<p style="font-size:15px;">${jobOffer.benefits}</p>
					<c:if test="${not empty jobOffer.additionalInfo}">
					<h4>
                    	<b>More info</b>
                    </h4>
                    <p style="font-size:15px;">${jobOffer.additionalInfo}</p>
					</c:if>

                       <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <c:if test="${pageContext.request.isUserInRole('ROLE_USER')}">
                           <div class="dropdown">
                                 <button class="btn btn-success dropdown-toggle" type="button" data-toggle="dropdown">Apply
                                 <span class="caret"></span></button>
                                 <ul class="dropdown-menu">
                                 <li><a href="<spring:url value="/candidate/${jobOffer.jobId}/apply" />">Use my profile</a></li>
                                 <li role="separator" class="divider"></li>
                                 <li><a href="<spring:url value="/candidate/${jobOffer.jobId}/apply" />">Fill up candidate profile</a></li>
                                 </ul>
                               </div>
                             </c:if>
                        </c:if>
                       <c:if test="${pageContext.request.userPrincipal.name == null}">
                       <div class="alert alert-danger" role="alert">
                         <strong>If you want to apply for this job offer you have to </strong> <a class="btn btn-primary" href="<spring:url value="/candidate/${jobOffer.jobId}" />">Log in</a>
                       </div>
                        </c:if>
    </div>

<br/>
<br/>
<br/>
<%@include file="/WEB-INF/views/template/footer.jsp"%>


<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
                    <img src="<c:url value="/resources/images/${jobOffer.jobId}.png" /> " alt="image"
                             style="width:100%"/>
                </div>

                <div class="col-md-5">
                    <h3>${jobOffer.position}</h3>
                    <p>${jobOffer.companyName}</p>
                </div>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <h3>${jobOffer.description}</h3>
                </div>
            </div>
       </div>

<%@include file="/WEB-INF/views/template/footer.jsp"%>


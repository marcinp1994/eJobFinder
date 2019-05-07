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
                    <h3><b>Position:</b> ${jobOffer.position}</h3>
                    <p><b>Company Name:</b> ${jobOffer.companyName}</p>
                    <p><b>Expiration Date:</b> ${expirationDate}</p>
                    <p><b>Location:</b> ${location}</p>
                    <p><b>Job ID:</b> ${jobOffer.jobId}</p>
                </div>
            </div>

            <br/>
            <br/>

                    <h4><b>Job Description</b></h4>
                    <p style="font-size:15px;">${jobOffer.description}</p>
                    <br/>
                    <br/>
                    <h4><b>Responsibilities:</b></h4>
                    <p style="font-size:15px;">${jobOffer.responsibilities}</p>
                    <br/>
                    <br/>
                    <h4><b>Job Requirements:</b></h4>
                    <p style="font-size:15px;">${jobOffer.requirements}</p>
                    <br/>
                    <br/>
                    <h4><b>Preferred Skills:</b></h4>
                    <p style="font-size:15px;">${jobOffer.preferredSkills}</p>
                    <br/>
                    <br/>
                    <h4><b>Benefits</b></h4>
                    <p style="font-size:15px;">${jobOffer.benefits}</p>
       </div>
        <br/>
        <br/>
        <br/>
<%@include file="/WEB-INF/views/template/footer.jsp"%>


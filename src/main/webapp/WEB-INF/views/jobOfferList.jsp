<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="/WEB-INF/views/template/header.jsp"%>

<br/>
<br/>

<div class="container-wrapper">
<div class="container">
<div class="page-header">
   <h1>Job Offers</h1>
   <p class="lead">Checkout all the awesome list of job offers now! </p>
</div>
<div class="well well-sm">
   <div class="btn-group">
      <a href="#" id="list" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-th-list">
      </span>List</a> <a href="#" id="grid" class="btn btn-default btn-sm"><span
         class="glyphicon glyphicon-th"></span>Grid</a>
   </div>
</div>
<div id="products" class="row list-group">
   <c:forEach items="${jobOffers}" var="jobOffer">
      <div class="item  col-xs-4 col-lg-4">
         <div class="thumbnail">
         <a href="<spring:url value="/jobOfferList/viewJobOffer/${jobOffer.jobId}" />">
            <img class="group list-group-image" src="<c:url value="/resources/images/${jobOffer.jobId}.png" /> " width="400" height = "250" alt="image" /> </a>
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
               <div class="row">
                  <div class="col-xs-12 col-md-6">
                     <a class="btn btn-success" href="<spring:url value="/jobOfferList/viewJobOffer/${jobOffer.jobId}" />">Details</a>
                  </div>
               </div>
            </div>
         </div>
      </div>

   </c:forEach>
</div>

        <br/>
        <br/>
        <br/>
<%@include file="/WEB-INF/views/template/footer.jsp"%>
<script>
   $(document).ready(function() {
        $('#list').click(function(event){event.preventDefault();$('#products .item').addClass('list-group-item');});
        $('#grid').click(function(event){event.preventDefault();$('#products .item').removeClass('list-group-item');$('#products .item').addClass('grid-group-item');});
   });
</script>
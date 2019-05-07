<%@include file="/WEB-INF/views/template/header.jsp"%>


<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Administrator page</h1>

            <p class="lead">This is the employer page!</p>
        </div>

        <h3>
            <a href="<c:url value="/employer/jobOfferInventory" />" >Job Offers Inventory</a>
        </h3>

        <p>Here you can view, check and modify all your job offers!</p>

        <br/>
        <br/>
        <br/>

 <%@include file="/WEB-INF/views/template/footer.jsp" %>


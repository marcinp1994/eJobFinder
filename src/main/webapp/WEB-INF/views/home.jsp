<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@include file="/WEB-INF/views/template/header.jsp" %>

<!-- Carousel
================================================== -->


<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
        <div class="item active">
                    <img class="first-slide" src="<c:url value="/resources/images/jobs3.jpg" />" alt="First slide">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1 style="color:red;"><b><font size="30">Job opportunities</font></b></h1>
                            <p style="color:black;font-size:30px"><b>Looking for a job? We can do better, how about a career defining opportunity?  Just choose one of the category below.</b></p>
                            <p><a class="btn btn-lg btn-warning"  href="<spring:url value="/jobOfferList" />" role="button">See hot jobs</a></p>
                        </div>
                    </div>
                </div>
        <div class="item">
            <img class="second-slide" src="<c:url value="/resources/images/jobOffers2.jpg" />" alt="Second slide">
            <div class="container">
                    <div class="carousel-caption">
                        <p><a class="btn btn-lg btn-warning" href="#" role="button">See hot jobs</a></p>
                    </div>
            </div>
        </div>
        <div class="item">
            <img class="third-slide" src="<c:url value="/resources/images/jobs5.jpg" />" alt="Third slide">
            <div class="container">
                <div class="carousel-caption">
                    <div class="carousel-caption">
                        <p><a class="btn btn-lg btn-warning" href="#" role="button">See hot jobs</a></p>
                    </div>
            </div>
        </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div><!-- /.carousel -->


<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->

<div class="container marketing">

    <!-- Three columns of text below the carousel -->
    <div class="row">
        <div class="col-lg-4">
        <a href="<spring:url value="/jobOfferList/IT" />">
            <img class="img-circle" src="<c:url value="/resources/images/IT.png" />" alt="Generic placeholder image" width="140" height="140"></a>
            <h2>IT</h2>
            <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Praesent commodo cursus magna.</p>
            <p><a class="btn btn-default" href="<spring:url value="/jobOfferList/IT" />" role="button">View IT offers &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
        <a href="<spring:url value="/jobOfferList/Financial" />">
            <img class="img-circle" src="<c:url value="/resources/images/financial.jpg" />" alt="Generic placeholder image" width="140" height="140"></a>
            <h2>Financial</h2>
            <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh.</p>
            <p><a class="btn btn-default" href="<spring:url value="/jobOfferList/Financial" />" role="button">View financial offers &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
        <a href="<spring:url value="/jobOfferList/Logistic" />">
            <img class="img-circle" src="<c:url value="/resources/images/logistics.jpg" />" alt="Generic placeholder image" width="140" height="140"></a>
            <h2>Logistic</h2>
            <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
            <p><a class="btn btn-default" href="<spring:url value="/jobOfferList/Logistic" />" role="button">View logistic offers &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
    </div><!-- /.row -->
</div>


    <br/>
        <br/>
        <br/>
    <!-- Footer -->

<%@include file="/WEB-INF/views/template/footer.jsp" %>
</div>
</body>
</html>

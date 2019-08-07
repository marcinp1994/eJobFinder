<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/carousel.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/listGrid.css" />" rel="stylesheet">
       <link href="<c:url value="/resources/css/bootstrap-tagsinput.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/editor.css" />" type="text/css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
</head>
<!-- NAVBAR================================================== -->
<body style="background-color:#F5F5F5;">
<div class="navbar-wrapper">
    <div class="container">

        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">

                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                            aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<c:url value="/" /> ">Job Offer</a>
                </div>

                <div id="navbar" class="navbar-collapse collapse">

                    <ul class="nav navbar-nav">
                        <li><a href="<c:url value="/" /> ">Home</a></li>
                        <li><a href="<c:url value="/jobOfferList" />">Job Offers</a></li>
                        <li><a href="#contact">Contact</a></li>
                       <li class="dropdown">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Category<span class="caret"></span></a>
                       <ul class="dropdown-menu">
                          <li><a href="<c:url value="/jobOfferList/IT/" />">IT</a></li>
                          <li role="separator" class="divider"></li>
                          <li><a href="<c:url value="/jobOfferList/Financial" />">Financial</a></li>
                          <li role="separator" class="divider"></li>
                          <li><a href="<c:url value="/jobOfferList/Logistic" />">Logistic</a></li>
                       </ul>
                    </li>
                    </ul>    <div class="col-sm-3 col-md-3">
                                      <form  role="search" action="/eJobFinder/search">
                                       <div class="input-group" style="margin-left:20px; margin-top:8px;">
                                      <input type="text" style="border-radius: 30px; width:300px;" class="form-control" placeholder="Search..." name="searchString" id="searchString"   >
                                     <div class="input-group-btn">   <button class="btn" style=" background: none;float: left; type="submit">
                                     <span style="color:white; " class="glyphicon glyphicon-search">
                                     </span></button>       </div>
                                                             </div>
                                                             </form>
                                           </div>
                    <ul class="nav navbar-nav pull-right">
                        <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <li><a>Welcome: ${pageContext.request.userPrincipal.name}</a></li>
                            <li><a href="<c:url value="/j_spring_security_logout" />">Logout</a></li>
                            <c:if test="${pageContext.request.isUserInRole('ROLE_EMPLOYER')}">
                                <li><a href="<c:url value="/employer" />">My Account</a></li>
                            </c:if>
                            <c:if test="${pageContext.request.isUserInRole('ROLE_USER')}">
                                <li><a href="<c:url value="/candidate/candidateMainPage" />">MyAccount</a></li>
                            </c:if>
                        </c:if>

                        <c:if test="${pageContext.request.userPrincipal.name  == null}">
                            <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Login<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                            <li><a href="<c:url value="/employer/" />">Employer</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="<c:url value="/candidate/candidateMainPage" />">Candidate</a></li>
                            </ul>
                            </li>
                            <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Register<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                            <li><a href="<c:url value="/registerEmployer/" />">Employer</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="<c:url value="/registerCandidate/" />">Candidate</a></li>
                            </ul>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>

        </nav>

    </div>


    </div>
</div>
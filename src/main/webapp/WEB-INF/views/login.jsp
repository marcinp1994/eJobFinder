<%@include file="/WEB-INF/views/template/header.jsp" %>

<!------ Include the above in your HEAD tag ---------->
<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">


<div class="wrapper fadeInDown" id="wrapper">
  <div id="formContent">

    <!-- Login Form -->
    <form name="loginForm" action="<c:url value="/j_spring_security_check" />" method="post">

      <input class="login" type="text" id="username" class="fadeIn second" name="username" placeholder="username" required="required" data-error="Please enter your Username.">
      <input class="login" type="text" id="password" class="fadeIn third" name="password" placeholder="password" required="required" data-error="Please enter your Password.">
      <input class="login" type="submit" class="fadeIn fourth" value="Log In">
      <input class="login" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>

    <!-- Remind Passowrd -->
    <div id="formFooter">
      <a class="underlineHover" href="#">Forgot Password?</a>
    </div>

  </div>
</div>
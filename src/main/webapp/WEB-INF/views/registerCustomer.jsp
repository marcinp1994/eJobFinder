<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/views/template/header.jsp" %>

<br/>
<br/>
<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Register ${customerType}</h1>

            <p class="lead">Please fill in your information below:</p>
        </div>

        <form:form action="${pageContext.request.contextPath}/register" method="post"
                   commandName="customer" >

        <h3>Basic Info</h3>
        <form:hidden path="customerType" value="${customerType}" />

        <div class="form-group">
            <label for="name">Name</label>
            <form:input path="name" id="name" class="form-Control" required="required" data-error="Please enter your Name."/>
        </div>

        <div class="form-group">
            <label for="name">Last Name</label>
            <form:input path="lastName" id="name" class="form-Control" required="required" data-error="Please enter your Last Name."/>
        </div>

        <c:if test="${customerType == 'Employer'}">
        <div class="form-group">
            <label for="name">Company Name</label>
            <form:input path="companyName" id="name" class="form-Control" required="required" data-error="Please enter your Company Name."/>
        </div>
        </c:if>

        <div class="form-group">
            <label for="email">Email</label>
            <form:input path="customerEmail" id="email" class="form-Control" required="required" data-error="Please enter your Email."/>
        </div>

        <div class="form-group">
            <label for="phone">Phone</label>
            <form:input path="customerPhone" id="phone" class="form-Control" required="required" data-error="Please enter your Phone."/>
        </div>

        <div class="form-group">
            <label for="username">Username</label>
            <form:input path="Username" id="username" class="form-Control" required="required" data-error="Please enter your Username."/>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <form:password path="password" id="password" class="form-Control" required="required" data-error="Please enter your Password."/>
        </div>


        <br><br>
        <input type="submit" value="submit" class="btn btn-default">
        <a href="<c:url value="/" />" class="btn btn-default">Cancel</a>
        </form:form>


        <%@include file="/WEB-INF/views/template/footer.jsp" %>

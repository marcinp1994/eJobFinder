<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/views/template/header.jsp" %>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/css/bootstrap-select.min.css" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">


<br/>
<br/>
<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Add a perfect employee profile</h1>

            <p class="lead">Fill the below information:</p>
        </div>

        <form:form action="${pageContext.request.contextPath}/employer/jobOfferInventory/perfectEmployee" method="post"
                   commandName="jobOffer" enctype="multipart/form-data">

         <div class="form-group">
                   <select  class="selectpicker" data-style="btn-light"  data-live-search="true">
                     <option hidden >Technology</option>
                     <c:forEach items="${technologies}" var="technology">
                     <option>${technology}</option>
                    </c:forEach>
                   </select>
                   <select  class="selectpicker" data-style="btn-light">
                     <option hidden >Years of work</option>
                     <option>0.5</option>
                     <option>1</option>
                     <option>1.5</option>
                     <option>2</option>
                     <option>2.5</option>
                     <option>3</option>
                     <option>3.5</option>
                     <option>4</option>
                     <option>4.5</option>
                     <option>5</option>
                     <option>5.5</option>
                     <option>6</option>
                     <option>6.5</option>
                     <option>7</option>
                     <option>7.5</option>
                     <option>8</option>
                     <option>8.5</option>
                     <option>9</option>
                     <option>9.5</option>
                     <option>10</option>
                     <option>10+</option>
                   </select>
                   <select  class="selectpicker" data-style="btn-light">
                     <option hidden >Level</option>
                     <option>1</option>
                     <option>2</option>
                     <option>3</option>
                     <option>4</option>
                     <option>5</option>
                     <option>6</option>
                   </select>
                   <button type="button" onclick="myFunction()" class="btn btn-success">ADD</button>
         </div>

          <div class="form-group" id="tableId" style="display:none">
              <table class="table table-sm">
                <thead>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Technology</th>
                    <th scope="col">Years of Work</th>
                    <th scope="col">Level</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <th scope="row">1</th>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                  </tr>
                </tbody>
              </table>
          </div>

        <input type="submit" value="submit" class="btn btn-default">
        <a href="<c:url value="/employer/jobOfferInventory/addJobOffer" />" class="btn btn-default">Cancel</a>
    </form:form>

<br/>
<br/>
<br/>

<%@include file="/WEB-INF/views/template/footer.jsp" %>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/js/bootstrap-select.min.js"></script>
<script src="<c:url value="/resources/js/editor.js" />"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script>
function myFunction() {
  var x = document.getElementById("tableId");
  var a = $('select').val();
  alert(a);
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}
</script>



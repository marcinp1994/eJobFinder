<%@include file="/WEB-INF/views/template/header.jsp"%>

<br/>
<br/>


<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Candidate control panel</h1>

        </div>
<h1 class="display-4">Welcome, ${name} ${lastName}</h1>
       <div style="width:56%;margin-left:23%;height:25%; margin-top:5%;">
                           <div >
                              <span >  <a href="<c:url value="/candidate/" />"  class="btn "><i class="fas fa-10x fa-id-card"></i>
                              <br />
                                  <b>Fill up your profile<b>
                              </a></span>
                                   <span style="margin-left:40px;">  <a href="#"  class="btn "><i class="far fa-10x fa-list-alt"></i>
                                                       <br />
                                                           <b>Your applications<b>
                                                        </a></span>
                              <span  style="margin-left:40px;">  <a href="#"  class="btn"><i class="fas fa-10x fa-file-upload"></i>
                                                  <br />
                                                      <b>Upload your CV<b>
                                                  </a></span>
                            </div>
                        </div>

        <br/>
 <br/>
   <br/>


 <%@include file="/WEB-INF/views/template/footer.jsp" %>


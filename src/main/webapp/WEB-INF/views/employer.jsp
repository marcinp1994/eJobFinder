<%@include file="/WEB-INF/views/template/header.jsp"%>

<br/>
<br/>

<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Administrator Control panel</h1>

        </div>
<h1 class="display-4">Welcome, ${name} ${lastName}</h1>
        <div style="width:56%;margin-left:20%;height:25%; margin-top:5%;">
                       <div >

                               <span style="margin-left:40px;">  <a href="#"  class="btn"><i class="fas fa-10x fa-user-edit"></i>
                                                   <br />
                                                       <b>Edit your profile<b>
                                                    </a></span>
                               <span  style="margin-left:40px;">  <a href="#"  class="btn"><i class="far fa-10x fa-list-alt"></i>
                                                                              <br />
                                                                                  <b>Your job offers<b>
                                                                              </a></span>
                          <span style="margin-left:40px;">  <a href="#"  class="btn"><i class="fas fa-10x fa-file-invoice"></i>
                                              <br />
                                                  <b>Reports<b>
                                              </a></span>
                        </div>
                    </div>

        <br/>
        <br/>
        <br/>

 <%@include file="/WEB-INF/views/template/footer.jsp" %>


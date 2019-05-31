<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@include file="/WEB-INF/views/template/header.jsp" %>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/css/bootstrap-select.min.css" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
<br/>
<br/>
<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            </br>
            <h1>Please fill up your profile</h1>

            <p class="lead">Fill the below information:</p>
        </div>

        <security:csrfMetaTags />

        <form:form action="" method="post" commandName="jobOffer" enctype="multipart/form-data" class="form-inline">
            </br>
            <!--TECHNICAL RULES-->
            <div class="container" style="width: 98%;">

                <div class="panel panel-primary">
                    <div class="panel-heading">Technologies - please add technologies which do you know</div>
                    <div class="panel-body" style="background-color:#EFEFEF;">

                        <div class="input-group mb-2 mr-sm-2">
                            <select id="technologySelector" class="selectpicker" data-style="btn-light" data-live-search="true">
                                <option value="" hidden>Technology</option>
                                <c:forEach items="${technologies}" var="technology">
                                    <option>${technology}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group mb-2 mr-sm-2">

                            <h4>&nbsp; at level&nbsp</h4>

                            <select id="experienceSelector" class="selectpicker" data-style="btn-light">
                                <option value="" hidden>Level</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                                <option>6</option>
                            </select>
                        </div>

                        <div class="input-group mb-2 mr-sm-2">
                            <h4> with years of experience&nbsp</h4>

                            <select id="yearSelector" class="selectpicker" data-style="btn-light">
                                <option value="" hidden>Years of work</option>
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
                        </div>

                        <button type="button" id="add_row" class="btn btn-primary" style="float: right;"> Add</button>

                        <table class="table table-hover" id="tableOfFacts" style="display: none;">
                            <thead>
                                <tr>
                                    <th style="width: 5%;" scope="col" class="text-center">#</th>
                                    <th style="width: 60%;" scope="col">Genearted description</th>
                                    <th style="width: 10%;" scope="col">Technology</th>
                                    <th style="width: 10%;" scope="col">Level</th>
                                    <th style="width: 10%;" scope="col">Years of experience</th>
                                    <th style="width: 5%;" scope="col">#</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!--SKILLS RULES-->
            <div class="container" style="width: 98%;">
                <div class="panel panel-info">
                    <div class="panel-heading">Skills - which skills do you have</div>
                    <div class="panel-body" style="background-color:EFEFEF;">

                        <div class="input-group mb-2 mr-sm-2">

                            <select id="skillSelector" class="selectpicker" data-style="btn-light" data-live-search="true">
                                <option value="" hidden>Skill</option>
                                <c:forEach items="${skills}" var="skill">
                                    <option>${skill}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group mb-2 mr-sm-2">

                            <h4> at level&nbsp</h4>

                            <select id="skill_experienceSelector" class="selectpicker" data-style="btn-light">
                                <option value="" hidden>Level</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                                <option>6</option>
                            </select>
                        </div>

                        <button type="button" id="skill_add_row" class="btn btn-info" style="float: right;"> Add</button>

                        <table class="table table-hover" id="skill_tableOfFacts" style="display: none;">
                            <thead>
                                <tr>
                                    <th style="width: 5%;" scope="col" class="text-center">#</th>
                                    <th style="width: 70%;" scope="col">Genearted description</th>
                                    <th style="width: 10%;" scope="col">Skill</th>
                                    <th style="width: 10%;" scope="col">Level</th>
                                    <th style="width: 5%;" scope="col">#</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!--TOOL RULES-->
            <div class="container" style="width: 98%;">
                <div class="panel panel-success">
                    <div class="panel-heading">Tools - which tools do you know?</div>
                    <div class="panel-body" style="background-color:EFEFEF;">

                        <div class="input-group mb-2 mr-sm-2">

                            <select id="tool_nameSelector" class="selectpicker" data-style="btn-light" data-live-search="true">
                                <option value="" hidden>Tool</option>
                                <c:forEach items="${tools}" var="tool">
                                    <option>${tool}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group mb-2 mr-sm-2">

                            <h4> at level&nbsp</h4>

                            <select id="tool_experienceSelector" class="selectpicker" data-style="btn-light">
                                <option value="" hidden>Level</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                                <option>6</option>
                            </select>
                        </div>

                        <div class="input-group mb-2 mr-sm-2">
                            <h4> with years of experience&nbsp</h4>

                            <select id="tool_yearSelector" class="selectpicker" data-style="btn-light">
                                <option value="" hidden>Years of work</option>
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
                        </div>

                        <button type="button" id="tool_add_row" class="btn btn-success" style="float: right;"> Add</button>

                        <table class="table table-hover" id="tool_tableOfFacts" style="display: none;">
                            <thead>
                                <tr>
                                    <th style="width: 5%;" scope="col" class="text-center">#</th>
                                    <th style="width: 60%;" scope="col">Genearted description</th>
                                    <th style="width: 10%;" scope="col">Tool</th>
                                    <th style="width: 10%;" scope="col">Level</th>
                                    <th style="width: 10%;" scope="col">Years of experience</th>
                                    <th style="width: 5%;" scope="col">#</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!--LANGUAGE RULES-->
            <div class="container" style="width: 98%;">
                <div class="panel panel-warning">
                    <div class="panel-heading">Languages- which languages do you know?</div>
                    <div class="panel-body" style="background-color:EFEFEF;">

                        <div class="input-group mb-2 mr-sm-2">

                            <select id="langSelector" class="selectpicker" data-style="btn-light" data-live-search="true">
                                <option value="" hidden>language</option>
                                <c:forEach items="${languages}" var="language">
                                    <option>${language}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group mb-2 mr-sm-2">

                            <h4> at level&nbsp</h4>

                            <select id="lang_experienceSelector" class="selectpicker" data-style="btn-light">
                                <option value="" hidden>level</option>
                                <c:forEach items="${languages_levels}" var="language_lvl">
                                    <option>${language_lvl}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <button type="button" id="lang_add_row" class="btn btn-warning" style="float: right;"> Add</button>

                        <table class="table table-hover" id="lang_tableOfFacts" style="display: none;">
                            <thead>
                                <tr>
                                    <th style="width: 5%;" scope="col" class="text-center">#</th>
                                    <th style="width: 70%;" scope="col">Genearted description</th>
                                    <th style="width: 10%;" scope="col">Language</th>
                                    <th style="width: 10%;" scope="col">Level</th>
                                    <th style="width: 5%;" scope="col">#</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        <security:csrfMetaTags />

        <form:form action="" method="post" commandName="jobOffer" enctype="multipart/form-data" class="form-inline">
            </br>
            <!--TECHNICAL RULES-->
            <div class="container" style="width: 98%;">

                <div class="panel panel-primary">
                    <div class="panel-heading">Technologies - please add technologies which do you know</div>
                    <div class="panel-body" style="background-color:#EFEFEF;">

                        <div class="input-group mb-2 mr-sm-2">
                            <select id="technologySelector" class="selectpicker" data-style="btn-light" data-live-search="true">
                                <option value="" hidden>Technology</option>
                                <c:forEach items="${technologies}" var="technology">
                                    <option>${technology}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group mb-2 mr-sm-2">

                            <h4>&nbsp; at level&nbsp</h4>

                            <select id="experienceSelector" class="selectpicker" data-style="btn-light">
                                <option value="" hidden>Level</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                                <option>6</option>
                            </select>
                        </div>

                        <div class="input-group mb-2 mr-sm-2">
                            <h4> with years of experience&nbsp</h4>

                            <select id="yearSelector" class="selectpicker" data-style="btn-light">
                                <option value="" hidden>Years of work</option>
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
                        </div>

                        <button type="button" id="add_row" class="btn btn-primary" style="float: right;"> Add</button>

                        <table class="table table-hover" id="tableOfFacts" style="display: none;">
                            <thead>
                                <tr>
                                    <th style="width: 5%;" scope="col" class="text-center">#</th>
                                    <th style="width: 60%;" scope="col">Genearted description</th>
                                    <th style="width: 10%;" scope="col">Technology</th>
                                    <th style="width: 10%;" scope="col">Level</th>
                                    <th style="width: 10%;" scope="col">Years of experience</th>
                                    <th style="width: 5%;" scope="col">#</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!--SKILLS RULES-->
            <div class="container" style="width: 98%;">
                <div class="panel panel-info">
                    <div class="panel-heading">Skills - which skills do you have</div>
                    <div class="panel-body" style="background-color:EFEFEF;">

                        <div class="input-group mb-2 mr-sm-2">

                            <select id="skillSelector" class="selectpicker" data-style="btn-light" data-live-search="true">
                                <option value="" hidden>Skill</option>
                                <c:forEach items="${skills}" var="skill">
                                    <option>${skill}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group mb-2 mr-sm-2">

                            <h4> at level&nbsp</h4>

                            <select id="skill_experienceSelector" class="selectpicker" data-style="btn-light">
                                <option value="" hidden>Level</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                                <option>6</option>
                            </select>
                        </div>

                        <button type="button" id="skill_add_row" class="btn btn-info" style="float: right;"> Add</button>

                        <table class="table table-hover" id="skill_tableOfFacts" style="display: none;">
                            <thead>
                                <tr>
                                    <th style="width: 5%;" scope="col" class="text-center">#</th>
                                    <th style="width: 70%;" scope="col">Genearted description</th>
                                    <th style="width: 10%;" scope="col">Skill</th>
                                    <th style="width: 10%;" scope="col">Level</th>
                                    <th style="width: 5%;" scope="col">#</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!--TOOL RULES-->
            <div class="container" style="width: 98%;">
                <div class="panel panel-success">
                    <div class="panel-heading">Tools - which tools do you know?</div>
                    <div class="panel-body" style="background-color:EFEFEF;">

                        <div class="input-group mb-2 mr-sm-2">

                            <select id="tool_nameSelector" class="selectpicker" data-style="btn-light" data-live-search="true">
                                <option value="" hidden>Tool</option>
                                <c:forEach items="${tools}" var="tool">
                                    <option>${tool}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group mb-2 mr-sm-2">

                            <h4> at level&nbsp</h4>

                            <select id="tool_experienceSelector" class="selectpicker" data-style="btn-light">
                                <option value="" hidden>Level</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                                <option>6</option>
                            </select>
                        </div>

                        <div class="input-group mb-2 mr-sm-2">
                            <h4> with years of experience&nbsp</h4>

                            <select id="tool_yearSelector" class="selectpicker" data-style="btn-light">
                                <option value="" hidden>Years of work</option>
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
                        </div>

                        <button type="button" id="tool_add_row" class="btn btn-success" style="float: right;"> Add</button>

                        <table class="table table-hover" id="tool_tableOfFacts" style="display: none;">
                            <thead>
                                <tr>
                                    <th style="width: 5%;" scope="col" class="text-center">#</th>
                                    <th style="width: 60%;" scope="col">Genearted description</th>
                                    <th style="width: 10%;" scope="col">Tool</th>
                                    <th style="width: 10%;" scope="col">Level</th>
                                    <th style="width: 10%;" scope="col">Years of experience</th>
                                    <th style="width: 5%;" scope="col">#</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!--LANGUAGE RULES-->
            <div class="container" style="width: 98%;">
                <div class="panel panel-warning">
                    <div class="panel-heading">Languages- which languages do you know?</div>
                    <div class="panel-body" style="background-color:EFEFEF;">

                        <div class="input-group mb-2 mr-sm-2">

                            <select id="langSelector" class="selectpicker" data-style="btn-light" data-live-search="true">
                                <option value="" hidden>language</option>
                                <c:forEach items="${languages}" var="language">
                                    <option>${language}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group mb-2 mr-sm-2">

                            <h4> at level&nbsp</h4>

                            <select id="lang_experienceSelector" class="selectpicker" data-style="btn-light">
                                <option value="" hidden>level</option>
                                <c:forEach items="${languages_levels}" var="language_lvl">
                                    <option>${language_lvl}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <button type="button" id="lang_add_row" class="btn btn-warning" style="float: right;"> Add</button>

                        <table class="table table-hover" id="lang_tableOfFacts" style="display: none;">
                            <thead>
                                <tr>
                                    <th style="width: 5%;" scope="col" class="text-center">#</th>
                                    <th style="width: 70%;" scope="col">Genearted description</th>
                                    <th style="width: 10%;" scope="col">Language</th>
                                    <th style="width: 10%;" scope="col">Level</th>
                                    <th style="width: 5%;" scope="col">#</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!--LOCATION RULES-->
            <div class="container" style="width: 98%;">
                <div class="panel panel-danger">
                    <div class="panel-heading">Location - where do you live or want to live</div>
                    <div class="panel-body" style="background-color:EFEFEF">

                        <div class="input-group mb-2 mr-sm-2">

                            <select id="locSelector" class="selectpicker" data-style="btn-light" data-live-search="true">
                                <option value="" hidden>location</option>
                                <c:forEach items="${locations}" var="loc">
                                    <option>${loc}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <button type="button" id="loc_add_row" class="btn btn-danger" style="float: right;"> Add</button>

                        <table class="table table-hover" id="loc_tableOfFacts" style="display: none;">
                            <thead>
                                <tr>
                                    <th style="width: 5%;" scope="col" class="text-center">#</th>
                                    <th style="width: 90%;" scope="col">Location</th>
                                    <th style="width: 5%;" scope="col">#</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!--WORKING HOURS RULES-->
            <div class="container" style="width: 98%;">
                <div class="panel panel-primary">
                    <div class="panel-heading">Working hours - which working hours do you preffer?</div>
                    <div class="panel-body" style="background-color:EFEFEF;">

                        <div class="input-group mb-2 mr-sm-2">

                            <h4> Working hours fine for me is &nbsp</h4>

                            <select id="workHSelector" class="selectpicker" data-style="btn-light" data-live-search="true">
                                <option value="" hidden>work hours</option>
                                <c:forEach items="${workingHours}" var="wH">
                                    <option>${wH}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <button type="button" id="workH_add_row" class="btn btn-primary" style="float: right;"> Add</button>

                        <table class="table table-hover" id="workH_tableOfFacts" style="display: none;">
                            <thead>
                                <tr>
                                    <th style="width: 5%;" scope="col" class="text-center">#</th>
                                    <th style="width: 80%;" scope="col">Genearted description</th>
                                    <th style="width: 10%;" scope="col">Working Hours</th>
                                    <th style="width: 5%;" scope="col">#</th>
                                </tr>
                            </thead>
                            <tbody> </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!--CONTRACT RULES-->
            <div class="container" style="width: 98%;">
                <div class="panel panel-info">
                    <div class="panel-heading">Contract - which type of contract do you preffer?</div>
                    <div class="panel-body" style="background-color:EFEFEF;">

                        <div class="input-group mb-2 mr-sm-2">

                            <h4>  Acceptable contracts types &nbsp</h4>

                            <select id="contrSelector" class="selectpicker" data-style="btn-light" data-live-search="true">
                                <option value="" hidden>type of contract</option>
                                <c:forEach items="${typeOfContracts}" var="val">
                                    <option>${val}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <button type="button" id="contr_add_row" class="btn btn-info" style="float: right;"> Add</button>

                        <table class="table table-hover" id="contr_tableOfFacts" style="display: none;">
                            <thead>
                                <tr>
                                    <th style="width: 5%;" scope="col" class="text-center">#</th>
                                    <th style="width: 80%;" scope="col">Genearted description</th>
                                    <th style="width: 10%;" scope="col">Type of Contract</th>
                                    <th style="width: 5%;" scope="col">#</th>
                                </tr>
                            </thead>
                            <tbody> </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!--PERIODS RULES-->
            <div class="container" style="width: 98%;">
                <div class="panel panel-success">
                    <div class="panel-heading">Periods of notice - which period of notice do you have?</div>
                    <div class="panel-body" style="background-color:EFEFEF;">

                        <div class="input-group mb-2 mr-sm-2">

                            <h4> Periods of notices is&nbsp</h4>

                            <select id="periodSelector" class="selectpicker" data-style="btn-light" data-live-search="true">
                                <option value="" hidden>period</option>
                                <c:forEach items="${periods}" var="val">
                                    <option>${val}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <button type="button" id="period_add_row" class="btn btn-success" style="float: right;"> Add</button>

                        <table class="table table-hover" id="period_tableOfFacts" style="display: none;">
                            <thead>
                                <tr>
                                    <th style="width: 5%;" scope="col" class="text-center">#</th>
                                    <th style="width: 80%;" scope="col">Genearted description</th>
                                    <th style="width: 10%;" scope="col">Period of Notice</th>
                                    <th style="width: 5%;" scope="col">#</th>
                                </tr>
                            </thead>
                            <tbody> </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!--EDUCATION RULES-->
            <div class="container" style="width: 98%;">
                <div class="panel panel-warning">
                    <div class="panel-heading">Education - please describe your education</div>
                    <div class="panel-body" style="background-color:EFEFEF;">
                        <table class="table">
                            <tr>
                                <td class="col-md-2">
                                    <h4>Which professiona title do you have?</h4> </td>
                                <td class="col-md-3">
                                    <select id="edu_titleSelector" class="selectpicker" data-style="btn-light" data-live-search="true">
                                        <option value="" hidden>professional title</option>
                                        <c:forEach items="${eduTitles}" var="val">
                                            <option>${val}</option>
                                        </c:forEach>
                                    </select>

                                </td>
                            </tr>
                            <tr>
                                <td class="col-md-2">
                                    <h4>In which field of study? &nbsp</h4> </td>
                                <td class="col-md-3">

                                    <select id="edu_fieldSelector" class="selectpicker" data-style="btn-light" data-live-search="true">
                                        <option value="" hidden>field of study</option>
                                        <c:forEach items="${eduFields}" var="val">
                                            <option>${val}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="col-md-2">
                                    <h4>In which mode of study? &nbsp</h4> </td>
                                <td class="col-md-3">

                                    <select id="edu_modeSelector" class="selectpicker" data-style="btn-light" data-live-search="true">
                                        <option value="" hidden>mode of study</option>
                                        <c:forEach items="${eduModes}" var="val">
                                            <option>${val}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="col-md-2">
                                    <h4> Do you have studied abroad? &nbsp</h4>
                                </td>
                                <td class="col-md-3">

                                    <select id="edu_abroadSelector" class="selectpicker" data-style="btn-light">
                                        <option>NO</option>
                                        <option>YES</option>

                                    </select>

                                </td>
                            </tr>
                            <tr>
                                <td class="col-md-2">
                                    <h4> Are you still a student? &nbsp</h4>
                                </td>
                                <td class="col-md-3">

                                    <select id="edu_isStudentSelector" class="selectpicker" data-style="btn-light">
                                        <option>NO</option>
                                        <option>YES</option>

                                    </select>

                                </td>
                            </tr>
                        </table>
                        <button type="button" id="edu_add_row" class="btn btn-warning" style="float: right;"> Add</button>

                        <table class="table table-hover" id="edu_tableOfFacts" style="display: none;">
                            <thead>
                                <tr>
                                    <th style="width: 5%;" scope="col" class="text-center">#</th>
                                    <th style="width: 30%;" scope="col">Title</th>
                                    <th style="width: 25%;" scope="col">Field</th>
                                    <th style="width: 25%;" scope="col">Mode</th>
                                    <th style="width: 5%;" scope="col">Abroad</th>
                                    <th style="width: 5;" scope="col">Student</th>
                                    <th style="width: 5%;" scope="col">#</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!--PREVIOUS RULES-->
            <div class="container" style="width: 98%;">
                <div class="panel panel-danger">
                    <div class="panel-heading">Previous employer - please describe your job experience</div>
                    <div class="panel-body" style="background-color:EFEFEF;">
                        <table class="table">
                            <tr>
                                <td class="col-md-2">
                                    <h4>Which job title you have previous?</h4> </td>
                                <td class="col-md-3">
                                    <select id="prev_titleSelector" class="selectpicker" data-style="btn-light" data-live-search="true">
                                        <option value="" hidden>job title</option>
                                        <c:forEach items="${jobTitles}" var="val">
                                            <option>${val}</option>
                                        </c:forEach>
                                    </select>

                                </td>
                            </tr>
                            <tr>
                                <td class="col-md-2">
                                    <h4> How many years you worked there? &nbsp</h4>
                                </td>
                                <td class="col-md-3">
                                    <select id="prev_yearSelector" class="selectpicker" data-style="btn-light">
                                        <option value="" hidden>Years of work</option>
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
                    </div>

                    </td>
                    </tr>
                    <tr>
                        <td class="col-md-2">
                            <h4>Have you gain any professional experience? &nbsp</h4>
                        </td>
                        <td class="col-md-3">
                            <select id="prev_proExp" class="selectpicker" data-style="btn-light">

                                <option>YES</option>
                                <option>NO</option>
                            </select>

                        </td>
                    </tr>
                    <tr>
                        <td class="col-md-2">
                            <h4> Are you still working here? &nbsp</h4>
                        </td>
                        <td class="col-md-3">
                            <select id="prev_stillWorking" class="selectpicker" data-style="btn-light">
                                <option>YES</option>
                                <option>NO</option>

                            </select>

                        </td>
                    </tr>
                    </table>

                    <button type="button" id="prev_add_row" class="btn btn-danger" style="float: right;"> Add</button>

                    <table class="table table-hover" id="prev_tableOfFacts" style="display: none;">
                        <thead>
                            <tr>
                                <th style="width: 5%;" scope="col" class="text-center">#</th>
                                <th style="width: 65%;" scope="col">Genearted description</th>
                                <th style="width: 10%;" scope="col">Title</th>
                                <th style="width: 5%;" scope="col">Years</th>
                                <th style="width: 5%;" scope="col">Experience</th>
                                <th style="width: 5%;" scope="col">Still working</th>
                                <th style="width: 5%;" scope="col">#</th>
                            </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>
    </div>

    <!--SALARY RULES-->
    <div class="container" style="width: 98%;">
        <div class="panel panel-info">
            <div class="panel-heading">Salary - select your salary expectations range</div>
            <div class="panel-body" style="background-color:EFEFEF;">
                <div class="input-group mb-2 mr-sm-2">
                    <h4> Minimum  &nbsp</h4>
                    <select id="salary_amountDownSelector" class="selectpicker" data-style="btn-light">
                        <option value="" hidden>Amount</option>
                        <option>3000</option>
                        <option>3500</option>
                        <option>4000</option>
                        <option>4500</option>
                        <option>5000</option>
                        <option>5500</option>
                        <option>6000</option>
                        <option>6500</option>
                        <option>7000</option>
                        <option>7500</option>
                        <option>8000</option>
                        <option>8500</option>
                        <option>9000</option>
                        <option>9500</option>
                        <option>10000</option>
                        <option>11000</option>
                        <option>12000</option>
                        <option>13000</option>
                        <option>14000</option>
                        <option>15000</option>
                        <option>16000</option>
                        <option>17000</option>
                        <option>18000</option>
                        <option>19000</option>
                        <option>20000</option>
                        <option>21000</option>
                        <option>22000</option>
                        <option>23000</option>
                        <option>25000+</option>
                    </select>
                </div>

                <div class="input-group mb-2 mr-sm-2">
                    <h4> Maxiumum  &nbsp</h4>
                    <select id="salary_amountUpSelector" class="selectpicker" data-style="btn-light">
                        <option value="" hidden>Amount</option>
                        <option>3000</option>
                        <option>3500</option>
                        <option>4000</option>
                        <option>4500</option>
                        <option>5000</option>
                        <option>5500</option>
                        <option>6000</option>
                        <option>6500</option>
                        <option>7000</option>
                        <option>7500</option>
                        <option>8000</option>
                        <option>8500</option>
                        <option>9000</option>
                        <option>9500</option>
                        <option>10000</option>
                        <option>11000</option>
                        <option>12000</option>
                        <option>13000</option>
                        <option>14000</option>
                        <option>15000</option>
                        <option>16000</option>
                        <option>17000</option>
                        <option>18000</option>
                        <option>19000</option>
                        <option>20000</option>
                        <option>21000</option>
                        <option>22000</option>
                        <option>23000</option>
                        <option>25000+</option>
                    </select>
                </div>

                <button type="button" id="salary_add_row" class="btn btn-info" style="float: right;"> Add</button>

                <table class="table table-hover" id="salary_tableOfFacts" style="display: none;">
                    <thead>
                        <tr>
                            <th style="width: 5%;" scope="col" class="text-center">#</th>
                            <th style="width: 70%;" scope="col">Genearted description</th>
                            <th style="width: 10%;" scope="col">Minimum</th>
                            <th style="width: 10%;" scope="col">Maxiumum</th>
                            <th style="width: 5%;" scope="col">#</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div style=" height:50px; width:98%;">
        <button type="button" id="sendFormButton" class="btn btn-primary" style="position:relative; width:50%; height:50px; left:25%">Update my profile</button>
    </div>
</form:form>
<%@include file="/WEB-INF/views/template/footer.jsp" %>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js" integrity="sha256-T0Vest3yCU7pafRw9r+settMBX6JkKN06dqBnpQ8d30=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/js/bootstrap-select.min.js"></script>
<script src="<c:url value="/resources/js/editor.js" />"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/js/candidate.js" />"></script>



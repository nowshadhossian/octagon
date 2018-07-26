<#-- @ftlvariable name="upcomingSubjects" type="java.util.List<com.kids.crm.model.Subject>" -->
<#-- @ftlvariable name="upcomingSessions" type="java.util.List<com.kids.crm.model.Session>" -->
<#assign title="Register | Octagon">
<#include "layout/no-nav/top.ftl">
<div class="card card-register mx-auto mt-5">
        <div class="card-header">Register an Account</div>
        <div class="card-body">
            <form action="/register" method="post">
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <@spring.bind "signup.firstName" />
                            <label for="firstName">First name</label>
                            <input class="form-control" id="firstName" name="${spring.status.expression}" type="text" value="${spring.status.value?default("")}" aria-describedby="nameHelp" placeholder="Enter first name">
                            <@spring.showErrors "<br>"/>
                        </div>
                        <div class="col-md-6">
                            <@spring.bind "signup.lastName" />
                            <label for="lastName">Last name</label>
                            <input class="form-control" id="lastName" name="${spring.status.expression}" value="${spring.status.value?default("")}" type="text" aria-describedby="nameHelp" placeholder="Enter last name">
                           <@spring.showErrors "<br>"/>
                        </div>
                    </div>
                </div>

                <div class="form-group date_picker">
                     <@spring.bind "signup.dateOfBirth" />
                    <label>Date Of Birth</label>
                    <input class="form-control" id="date" name="${spring.status.expression}" autocomplete="off" value="${spring.status.value?default("")}" placeholder="MM/DD/YYYY enter date" type="text"/>
                     <@spring.showErrors "<br>"/>
                </div>

                 <@spring.bind "signup.gender" />
                <div class="form-group">
                    <label for="exampleFormControlSelect1">Gender</label>
                    <select class="form-control" id="gender" name="${spring.status.expression}">
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>
                     <@spring.showErrors "<br>"/>
                </div>

                <div class="form-group">
                     <@spring.bind "signup.school" />
                    <label for="phoneNo">School</label>
                    <input class="form-control" id="school" name="${spring.status.expression}" value="${spring.status.value?default("")}" type="text" aria-describedby="school" placeholder="Enter School Name">
                     <@spring.showErrors "<br>"/>
                </div>

                <div class="form-group">
                     <@spring.bind "signup.phoneNo" />
                    <label for="phoneNo">Phone No</label>
                    <input class="form-control" id="phoneNo" name="${spring.status.expression}" value="${spring.status.value?default("")}" type="number" aria-describedby="phoneNo" placeholder="Enter Phone Number">
                     <@spring.showErrors "<br>"/>
                </div>

                <div class="form-group">
                     <@spring.bind "signup.address" />
                    <label for="comment">Address:</label>
                    <textarea class="form-control" rows="5" id="address"  name="${spring.status.expression}" value="${spring.status.value?default("")}"></textarea>
                     <@spring.showErrors "<br>"/>
                </div>

                <div class="form-group  enrolling-input">
                    <h5><label class="form-check-label" for="defaultCheck2">Enrolling</label></h5>
                     <@spring.bind "signup.enrollingIds" />
                    <#list upcomingSubjects as upcomingSubject>
                        <div class="form-check">
                            <input class="form-check-input"  name="${spring.status.expression}" type="checkbox" value="${upcomingSubject.getId()}" id="subjects">
                            <label class="form-check-label" for="subjects">${upcomingSubject.getName()}</label>
                        </div>
                    </#list>
                     <@spring.showErrors "<br>"/>
                </div>

                <div class="form-group">
                     <@spring.bind "signup.interestSessionId" />
                    <label for="exampleFormControlSelect1">Session</label>
                    <select class="form-control" id="session" name="${spring.status.expression}">
                        <#list upcomingSessions as upcomingSession>
                             <option value="${upcomingSession.getId()}">${upcomingSession.getName()} ${upcomingSession.getYear()}</option>
                        </#list>
                    </select>
                </div>

                <div class="form-group">
                     <@spring.bind "signup.examsCurriculum" />
                    <label for="session">Curriculum</label>
                    <select class="form-control" id="session" name="${spring.status.expression}" onchange="showfield(this.options[this.selectedIndex].value, 'examsCurriculumOther')">
                        <option value="SSC, HSC Bangla version">SSC, HSC Bangla version</option>
                        <option value="SSC, HSC English version">SSC, HSC English version</option>
                        <option value="O &amp; A level Edexcel">O &amp; A level Edexcel</option>
                        <option value="O &amp; A level CIE">O &amp; A level CIE</option>
                        <option value="Other">Other</option>
                    </select>
                    <div id="examsCurriculumOther" style="display: none"> <label for="examsCurriculumOtherInput">If Other:</label><input id="examsCurriculumOtherInput" type="text" class="form-control" name="${spring.status.expression}" /></div>
                </div>



                <div class="form-group referee-col">
                    <h5><label class="form-check-label">Referees</label></h5>
                    <div class="referee-rows">
                         <@spring.bind "signup.refereesSubjectId" />
                        <div class="form-row referee-row">
                            <div class="col-md-6">
                                <label for="exampleFormControlSelect1">Subject</label>
                                <select class="form-control"  name="${spring.status.expression}">
                                    <#list upcomingSubjects as upcomingSubject>
                                        <option value="${upcomingSubject.getId()}">${upcomingSubject.getName()}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="col-md-6">
                                 <@spring.bind "signup.refereesName" />
                                <label for="lastName">Last name</label>
                                <input class="form-control"  name="${spring.status.expression}" type="text" aria-describedby="nameHelp" placeholder="Enter referees name">
                            </div>
                        </div>
                    </div>
                    <a class="small mt-3 add-referee-link">
                        <button type="button" class="btn btn-info btn-sm">
                            <span class="oi oi-plus"></span> Add Referee
                        </button>
                    </a>
                    <a class="small mt-3 remove-referee-link">
                        <button type="button" class="btn btn-info btn-sm">
                            <span class="oi oi-minus"></span> Remove Referee
                        </button>
                    </a>
                </div>

                <div class="form-group guardian-col">
                    <label class="form-check-label">Guardian Information</label>
                    <div class="guardian-rows">
                        <div class="form-row guardian-row">
                             <@spring.bind "signup.guardianName" />
                            <div class="col-md-3">
                                <label for="guardianName">Name</label>
                                <input class="form-control guardian-name" name="${spring.status.expression}" type="text"
                                       aria-describedby="nameHelp" placeholder="Enter name">
                            </div>
                            <@spring.bind "signup.guardianContactNo" />
                            <div class="col-md-3">
                                <label for="contactNo">Contact No</label>
                                <input class="form-control guardian-contactNo"  name="${spring.status.expression}" type="text"
                                       aria-describedby="nameHelp" placeholder="Enter No">
                            </div>

                            <@spring.bind "signup.guardianRelation" />
                            <div class="col-md-3">
                                <label for="relation">Relation</label>
                                <input class="form-control guardian-relation" name="${spring.status.expression}" type="text"
                                       aria-describedby="nameHelp" placeholder="Enter Relation">
                            </div>

                            <@spring.bind "signup.guardianEmail" />
                            <div class="col-md-3">
                                <label for="email">Email</label>
                                <input class="form-control guardian-email" name="${spring.status.expression}" type="email"
                                       aria-describedby="nameHelp"
                                       placeholder="Enter Email">
                            </div>
                        </div>
                    </div>
                    <a class="small mt-3 add-guardian-link">
                        <button type="button" class="btn btn-info btn-sm">
                            <span class="oi oi-plus"></span> Add Guardian
                        </button>
                    </a>
                    <a class="small mt-3 remove-guardian-link">
                        <button type="button" class="btn btn-info btn-sm">
                            <span class="oi oi-minus"></span> Remove Guardian
                        </button>
                    </a>
                </div>

                <script id="template-guardian-list-item" type="text/template">
                    <div class="form-row guardian-row">
                        <div class="col-md-3">
                            <input class="form-control  guardian-name" name="guardianName" type="text" aria-describedby="nameHelp" placeholder="Enter name">
                        </div>
                        <div class="col-md-3">
                            <input class="form-control guardian-contactNo" name="guardianContactNo" type="text" aria-describedby="nameHelp" placeholder="Enter No">
                        </div>

                        <div class="col-md-3">
                            <input class="form-control guardian-relation" name="guardianRelation" type="text" aria-describedby="nameHelp" placeholder="Enter Relation">
                        </div>

                        <div class="col-md-3">
                            <input class="form-control guardian-email" name="guardianEmail" type="email" aria-describedby="nameHelp" placeholder="Enter Email">
                        </div>
                    </div>
                </script>

                <script id="template-referee-list-item" type="text/template">
                    <div class="form-row referee-row">
                        <@spring.bind "signup.refereesSubjectId" />
                        <div class="col-md-6">
                            <select class="form-control"  name="${spring.status.expression}">
                               <#list upcomingSubjects as upcomingSubject>
                                   <option value="${upcomingSubject.getId()}">${upcomingSubject.getName()}</option>
                               </#list>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <@spring.bind "signup.refereesName" />
                            <input class="form-control" name="${spring.status.expression}" type="text" aria-describedby="nameHelp"
                                   placeholder="Enter referees name">
                        </div>
                    </div>
                </script>

                <div class="form-group">
                     <@spring.bind "signup.email" />
                    <label for="email">Email address</label>
                    <input class="form-control" id="email"  name="${spring.status.expression}" value="${spring.status.value?default("")}" type="email" aria-describedby="emailHelp" placeholder="Enter email">
                    <@spring.showErrors "<br>"/>
                </div>

                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <@spring.bind "signup.password" />
                            <label for="password">Password</label>
                            <input class="form-control" id="password" name="${spring.status.expression}" value="${spring.status.value?default("")}" type="password" placeholder="Password">
                            <@spring.showErrors "<br>"/>
                        </div>
                        <div class="col-md-6">
                            <@spring.bind "signup.confirmPassword" />
                            <label for="confirmPassword">Confirm password</label>
                            <input class="form-control" id="confirmPassword" name="${spring.status.expression}" value="${spring.status.value?default("")}" type="password" placeholder="Confirm password">
                            <@spring.showErrors "<br>"/>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button id="register" type="submit" class="btn btn-primary btn-block" name="register" value="register">Register</button>
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="/login">Login Page</a>
                <#--<a class="d-block small" href="/forgot-password">Forgot Password?</a>-->
            </div>
        </div>

</div>
<#include "layout/no-nav/bottom.ftl">
<script>
    $( function() {
        $( "#date" ).datepicker();
    } );

    function showfield(name, otherDiv){
        if(name=='Other')document.getElementById(otherDiv).style.display="block";
        else document.getElementById(otherDiv).style.display="none";
    }

</script>
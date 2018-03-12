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
                    <input class="form-control" id="date" name="${spring.status.expression}" value="${spring.status.value?default("")}" placeholder="MM/DD/YYYY enter date" type="text"/>
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
                    <label class="form-check-label" for="defaultCheck2">Enrolling..</label>
                     <@spring.bind "signup.enrollingIds" />
                    <div class="form-check">
                        <input class="form-check-input"  name="enrollingIds" type="checkbox" value="1" id="defaultCheck1">
                        <label class="form-check-label" for="defaultCheck1">Physics</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" name="enrollingIds" type="checkbox" value="2" id="defaultCheck2">
                        <label class="form-check-label" for="defaultCheck2">Chamistry</label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" name="enrollingIds" type="checkbox" value="3" id="defaultCheck3">
                        <label class="form-check-label" for="defaultCheck3">Biology</label>
                    </div>
                     <@spring.showErrors "<br>"/>
                </div>

                <div class="form-group">
                    <label for="exampleFormControlSelect1">Session</label>
                    <select class="form-control" id="session" name="session">
                        <option value="Summer 2018">Summer 2018</option>
                        <option value="Spring 2018">Spring 2018</option>
                    </select>
                </div>

                <div class="form-group referee-col">
                    <label class="form-check-label">Referees...</label>
                    <div class="referee-rows">
                        <div class="form-row referee-row">
                            <div class="col-md-6">
                                <label for="exampleFormControlSelect1">Subject</label>
                                <select class="form-control"  name="subject">
                                    <option value="1">Physics</option>
                                    <option value="2">Chemistry</option>
                                    <option value="3">Bio</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label for="lastName">Last name</label>
                                <input class="form-control"  name="refereesName" type="text" aria-describedby="nameHelp" placeholder="Enter referees name">
                            </div>
                        </div>
                    </div>
                    <a class="d-block small mt-3 add-referee-link">Add Referee</a>
                    <a class="d-block small mt-3 remove-referee-link">Remove Referee</a>
                </div>

                <div class="form-group guardian-col">
                    <label class="form-check-label">Guardian Information</label>
                    <div class="guardian-rows">
                        <div class="form-row guardian-row">
                            <div class="col-md-3">
                                <label for="guardianName">Name</label>
                                <input class="form-control guardian-name" name="guardianName" type="text"
                                       aria-describedby="nameHelp" placeholder="Enter name">
                            </div>
                            <div class="col-md-3">
                                <label for="contactNo">Contact No</label>
                                <input class="form-control guardian-contactNo"  name="contactNo" type="text"
                                       aria-describedby="nameHelp" placeholder="Enter No">
                            </div>

                            <div class="col-md-3">
                                <label for="relation">Relation</label>
                                <input class="form-control guardian-relation" name="relation" type="text"
                                       aria-describedby="nameHelp" placeholder="Enter Relation">
                            </div>

                            <div class="col-md-3">
                                <label for="email">Email</label>
                                <input class="form-control guardian-email" name="guardianEmail" type="text"
                                       aria-describedby="nameHelp"
                                       placeholder="Enter Email">
                            </div>
                        </div>
                    </div>
                    <a class="d-block small mt-3 add-guardian-link">Add Guardian</a>
                    <a class="d-block small mt-3 remove-guardian-link">Remove Guardian</a>
                </div>

                <script id="template-guardian-list-item" type="text/template">
                    <div class="form-row guardian-row">
                        <div class="col-md-3">
                            <input class="form-control  guardian-name" name="guardianName" type="text" aria-describedby="nameHelp" placeholder="Enter name">
                        </div>
                        <div class="col-md-3">
                            <input class="form-control guardian-contactNo" name="contactNo" type="text" aria-describedby="nameHelp" placeholder="Enter No">
                        </div>

                        <div class="col-md-3">
                            <input class="form-control guardian-relation" name="relation" type="text" aria-describedby="nameHelp" placeholder="Enter Relation">
                        </div>

                        <div class="col-md-3">
                            <input class="form-control guardian-email" name="guardianEmail" type="text" aria-describedby="nameHelp" placeholder="Enter Email">
                        </div>
                    </div>
                </script>

                <script id="template-referee-list-item" type="text/template">
                    <div class="form-row referee-row">
                        <div class="col-md-6">
                            <select class="form-control" name="subject">
                                <option value="1">Physics</option>
                                <option value="2">Chemistry</option>
                                <option value="3">Bio</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <input class="form-control" name="refereesName" type="text" aria-describedby="nameHelp"
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
                <a class="d-block small" href="/forgot-password">Forgot Password?</a>
            </div>
        </div>

</div>
<#include "layout/no-nav/bottom.ftl">
<script>
    $( function() {
        $( "#date" ).datepicker();
    } );
</script>
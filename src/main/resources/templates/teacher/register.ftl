<#import "/spring.ftl" as spring/>
<#assign title="Register | Octagon">
<#include "/layout/no-nav/top.ftl">
<div class="card card-register mx-auto mt-5">
    <div class="card-header">Register Teacher Account</div>
    <div class="card-body">
        <form action="/register/teacher" method="post">
            <div class="form-group">
                <div class="form-row">
                    <div class="col-md-6">
                            <@spring.bind "signup.firstName" />
                        <label for="firstName">First name</label>
                        <input class="form-control" id="firstName" name="${spring.status.expression}" type="text"
                               value="${spring.status.value!""}" aria-describedby="nameHelp"
                               placeholder="Enter first name">
                            <@spring.showErrors "<br>"/>
                    </div>
                     <@spring.bind "signup.lastName" />
                    <div class="col-md-6">
                        <label for="lastName">Last name</label>
                        <input class="form-control" id="lastName" value="${spring.status.value!""}" name="${spring.status.expression}" type="text"
                               aria-describedby="nameHelp" placeholder="Enter last name">
                         <@spring.showErrors "<br>"/>
                    </div>
                </div>
            </div>

             <@spring.bind "signup.phoneNo" />
            <div class="form-group">
                <label for="phoneNo">Phone No</label>
                <input class="form-control" id="phoneNo" name="${spring.status.expression}" type="text" aria-describedby="phoneNo"
                       value="${spring.status.value!""}" placeholder="Enter Phone Number">
                 <@spring.showErrors "<br>"/>
            </div>

            <@spring.bind "signup.address" />
            <div class="form-group">
                <label for="comment">Address:</label>
                <textarea class="form-control" rows="5" id="address" name="${spring.status.expression}">${spring.status.value!""}</textarea>
                 <@spring.showErrors "<br>"/>
            </div>

             <@spring.bind "signup.degree" />
            <div class="form-group">
                <label for="degree">Degree Completed</label>
                <input class="form-control" id="degree" value="${spring.status.value!""}" name="${spring.status.expression}" type="text" aria-describedby="degree"
                       placeholder="Degree Completed">
                <@spring.showErrors "<br>"/>
            </div>


             <@spring.bind "signup.email" />
            <div class="form-group">
                <label for="email">Email address</label>
                <input class="form-control" id="email" value="${spring.status.value!""}" name="${spring.status.expression}" type="email" aria-describedby="emailHelp"
                       placeholder="Enter email">
                <@spring.showErrors "<br>"/>
            </div>

             <@spring.bind "signup.password" />
            <div class="form-group">
                <div class="form-row">
                    <div class="col-md-6">
                        <label for="password">Password</label>
                        <input class="form-control" id="password" name="${spring.status.expression}" type="password"
                               placeholder="Password">
                         <@spring.showErrors "<br>"/>
                    </div>
                    <div class="col-md-6">
                        <label for="confirmPassword">Confirm password</label>
                        <input class="form-control" id="confirmPassword" name="confirmPassword" type="password"
                               placeholder="Confirm password">
                    </div>
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button id="register" type="submit" class="btn btn-primary btn-block" name="register" value="register">
                Register
            </button>
        </form>
        <div class="text-center">
            <a class="d-block small mt-3" href="/login">Login Page</a>
            <a class="d-block small" href="/forgot-password">Forgot Password?</a>
        </div>
    </div>

</div>
<#include "/layout/no-nav/bottom.ftl">

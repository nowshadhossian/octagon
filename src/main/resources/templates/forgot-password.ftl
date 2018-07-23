<#assign title="Forgot Password | Octagon">
<#include "layout/no-nav/top.ftl">
<div class="card card-login mx-auto mt-5">
        <div class="card-header">Reset Password</div>
        <div class="card-body">
            <#if success?? && success==true>
                <div class="text-center mt-4 mb-5">
                    <p>Your request has been sent. You'll get an email soon.</p>
                    <h4>Please check in your inbox</h4>
                </div>
                <div>
                    <a class="d-block small" href="/login">Login Page</a>
                </div>
            <#else>

            <div class="text-center mt-4 mb-5">
                <h4>Forgot your password?</h4>
                <p>Enter your email address and we will send you instructions on how to reset your password.</p>
            </div>
            <form action="/forgot-password1" method="post">
                <div class="form-group">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="text" name="email" class="form-control" id="email" placeholder="Enter email address" required>
                </div>
                <div>
                    <button type="submit" id="submitForm" class="btn btn-primary btn-block">Reset Password</button>
                </div>
                <#--<a class="btn btn-primary btn-block" href="login.html">Reset Password</a>-->
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="/register">Register an Account</a>
                <a class="d-block small" href="/login">Login Page</a>
            </div>
            </#if>
        </div>
</div>
<#include "layout/no-nav/bottom.ftl">

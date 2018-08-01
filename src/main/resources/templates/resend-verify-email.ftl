<#assign title="Forgot Password | Octagon">
<#include "layout/no-nav/top.ftl">
<div class="card card-login mx-auto mt-5">
    <div class="card-header">Resend Verify Email</div>
    <div class="card-body">
            <#--<div class="text-center mt-4 mb-5">
                <h4>Forgot your password?</h4>
                <p>Enter your email address and we will send you instructions on how to reset your password.</p>
            </div>-->
            <form action="/verify/email/resend" method="post">
                <div class="form-group">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="email" name="email" class="form-control" id="email" placeholder="Enter email address" required>
                </div>
                <div>
                    <button type="submit" id="submitForm" class="btn btn-primary btn-block">Resend</button>
                </div>
            <#--<a class="btn btn-primary btn-block" href="login.html">Reset Password</a>-->
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="/register">Register an Account</a>
                <a class="d-block small" href="/login">Login Page</a>
            </div>
    </div>
</div>
<#include "layout/no-nav/bottom.ftl">

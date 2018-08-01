<#assign title="Forgot Password | Octagon">
<#include "layout/no-nav/top.ftl">
<div class="card card-login mx-auto mt-5">
    <div class="card-header">Resend Verify Email</div>
    <div class="card-body">

            <form action="/verify/email/resend" method="post">
                <div class="form-group">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="email" name="email" class="form-control" id="email" placeholder="Enter email address" required>
                </div>
                <div>
                    <button type="submit" id="submitForm" class="btn btn-primary btn-block">Resend</button>
                </div>
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="/register">Register an Account</a>
                <a class="d-block small" href="/login">Login Page</a>
            </div>
    </div>
</div>
<#include "layout/no-nav/bottom.ftl">

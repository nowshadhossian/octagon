<#assign title="Forgot Password | Octagon">
<#include "layout/no-nav/top.ftl">
<div class="card card-login mx-auto mt-5">
    <div class="card-header">Reset your password</div>
    <div class="card-body">
        <#if requestMessage??>
           <div class="text-center mt-4 mb-5">
                <p>${requestMessage}</p>
           </div>
        <#else>
            <form id="password-reset-form" action="/reset-password" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="userId" value="${user.id!""}"/>
                <div class="form-group">
                    <label for="password">New Password</label>
                    <input class="form-control" name="password" id="password" type="password" placeholder="New Password" minlength="2" title="Enter minimum 4 character" required/>
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Re-enter Password</label>
                    <input class="form-control" name="confirmPassword" id="confirmPassword" type="password" placeholder="Re-enter Password" required/>
                    <div id="message" class="ui-helper-hidden">
                        <small id="passwordHelp" class="">

                        </small>
                    </div>
                </div>

                <div>
                    <button type="submit" id="submitForm" class="btn btn-primary btn-block">Submit</button>
                </div>
            <#--<a class="btn btn-primary btn-block" href="login.html">Reset Password</a>-->
            </form>
        </#if>

        <div class="text-center">
            <a class="d-block small mt-3" href="/register">Register an Account</a>
            <a class="d-block small" href="/login">Login Page</a>
        </div>
    </div>
</div>
<#include "layout/no-nav/bottom.ftl">

<script>
    $( function() {
        $("#confirmPassword").change(function () {
            validateInput();
        });

        $("#password-reset-form").submit(function( event ) {
            if(validateInput()){
                return;
            } else {
                event.preventDefault();
            }
        });

        function validateInput() {
            var currentPass = $("#password").val();
            var reEnterPass = $("#confirmPassword").val();
            if (currentPass==reEnterPass){
                $("#message").removeClass("ui-helper-hidden");

                $("#message #passwordHelp").removeClass("text-danger");
                $("#message #passwordHelp").addClass("text-success");
                $("#message #passwordHelp").html("Password matched");

                $("#confirmPassword").removeClass("is-invalid");
                $("#confirmPassword").addClass("is-valid");

                return true;
            } else {
                $("#message").removeClass("ui-helper-hidden");

                $("#message #passwordHelp").removeClass("text-success");
                $("#message #passwordHelp").addClass("text-danger");
                $("#message #passwordHelp").html("Password doesn't matched");

                $("#confirmPassword").removeClass("is-valid");
                $("#confirmPassword").addClass("is-invalid");
                return false;
            }

        }
    } );

</script>

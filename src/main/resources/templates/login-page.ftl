<#--<img src="/images/goru.png" alt="b"/>
<img src="/ui-lib/2.png" alt="e"/>
 <form action="/login" method="post">
     <fieldset>
         <legend>Please Login</legend>
         <!-- use param.error assuming FormLoginConfigurer#failureUrl contains the query parameter error &ndash;&gt;

         <!-- the configured LogoutConfigurer#logoutSuccessUrl is /login?logout and contains the query param logout &ndash;&gt;

here
         <#if (param.error)??>
         <#else>
          <div>
              Invalid credential. &lt;#&ndash;${SPRING_SECURITY_LAST_EXCEPTION.message}&ndash;&gt;
          </div>
         </#if>

        <#if (param??) && (param.logout??)>
        <#else>
             <div>
                 You have been logged out.
             </div>
        </#if>
         <p>
             <label for="username">Username</label>
             <input type="text" id="username" name="username"/>
         </p>
         <p>
             <label for="password">Password</label>
             <input type="password" id="password" name="password"/>
         </p>
         <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

         <div>
             <button type="submit" class="btn">Log in</button>
         </div>
     </fieldset>
 </form>-->

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>SB Admin - Start Bootstrap Template</title>
    <!-- Bootstrap core CSS-->
    <link href="/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="/css/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Custom styles for this template-->
    <link href="/css/sb-admin.css" rel="stylesheet">
</head>

<body class="bg-dark">
<div class="container">
    <div class="card card-login mx-auto mt-5">
        <div class="card-header">Login</div>
        <div class="card-body">
            <form>
                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input class="form-control" id="exampleInputEmail1" type="email" aria-describedby="emailHelp" placeholder="Enter email"/>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Password</label>
                    <input class="form-control" id="exampleInputPassword1" type="password" placeholder="Password"/>
                </div>
                <div class="form-group">
                    <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox"> Remember Password</label>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button class="btn btn-primary btn-block" href="index.html">Login</button>
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="register.html">Register an Account</a>
                <a class="d-block small" href="forgot-password.html">Forgot Password?</a>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript-->
<script src="/jq/jquery.min.js"></script>
<script src="/jq/bootstrap/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="/jq/jquery-easing/jquery.easing.min.js"></script>
</body>
</body>

</html>


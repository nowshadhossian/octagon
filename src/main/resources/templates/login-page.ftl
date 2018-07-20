<#assign title="Login Page | Octagon">
<#include "layout/no-nav/top.ftl">
<#if successMsg??>
  <div class="alert alert-success alert-dismissible fade show" role="alert">
      ${successMsg}
      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
          <span aria-hidden="true">&times;</span>
      </button>
  </div>
</#if>

<#if (SPRING_SECURITY_LAST_EXCEPTION)??>
  <div class="alert alert-danger alert-dismissible fade show" role="alert">
      Invalid Username or Password
      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
          <span aria-hidden="true">&times;</span>
      </button>
  </div>
</#if>

 <div class="card card-login mx-auto mt-5">
        <div class="card-header">Login</div>
        <div class="card-body">
            <form action="/login" method="post">
                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input class="form-control" name="username" id="exampleInputEmail1" type="email" aria-describedby="emailHelp" placeholder="Enter email"/>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Password</label>
                    <input class="form-control" name="password" id="exampleInputPassword1" type="password" placeholder="Password"/>
                </div>
                <div class="form-group">
                    <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox"> Remember Password</label>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button class="btn btn-primary btn-block" type="submit">Login</button>
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="/register">Register an Account</a>
                <a class="d-block small" href="/forgot-password1">Forgot Password?</a>
            </div>
        </div>
 </div>
<#include "layout/no-nav/bottom.ftl">

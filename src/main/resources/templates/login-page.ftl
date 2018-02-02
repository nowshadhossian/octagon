<img src="/images/goru.png" alt="b"/>
<img src="/ui-lib/2.png" alt="e"/>
 <form action="/login" method="post">
     <fieldset>
         <legend>Please Login</legend>
         <!-- use param.error assuming FormLoginConfigurer#failureUrl contains the query parameter error -->

         <!-- the configured LogoutConfigurer#logoutSuccessUrl is /login?logout and contains the query param logout -->

here
         <#if (param.error)??>
         <#else>
          <div>
              Invalid credential. <#--${SPRING_SECURITY_LAST_EXCEPTION.message}-->
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
         <input type="hidden"
    		name="${_csrf.parameterName}"
    		value="${_csrf.token}"/>

         <div>
             <button type="submit" class="btn">Log in</button>
         </div>
     </fieldset>
 </form>

<h1>File Upload</h1>
<div>
    <a href="/superadmin/question/upload">Question Upload Start Now</a>
</div>

<form action="/logout" method="post">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <input class="btn btn-primary" type="submit" value="Logout">
</form>
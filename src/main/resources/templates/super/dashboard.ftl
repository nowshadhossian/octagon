<h1>File Upload</h1>
<div>

</div>

<form action="/logout" method="post">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <input class="btn btn-primary" type="submit" value="Logout">
</form>
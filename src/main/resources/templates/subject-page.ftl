<#assign title="Subject Home | Octagon">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1>Hello ${name}!!!</h1>
        <p>This is an example of a blank page that you can use as a starting point for creating new ones.</p>
    </div>
</div>
<#include "/layout/nav/bottom.ftl">
<#--

<!DOCTYPE html>
<html>
<head>
    <title>I am success</title>
</head>
<body>
<h2>Success here</h2>

His name is ${name}



<form action="/logout" method="post">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <input type="submit" value="Logout">
</form>

<a href="/daily/exam">Daily Exam</a>
</body>
</html>-->

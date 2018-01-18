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
</body>
</html>
<!DOCTYPE html>
<html>
<head>
    <title>Students {msg}</title>
</head>
<body>
<h2>List of Students</h2>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Age</th>
    </tr>

            <#list studentList as student>
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.age}</td>
                </tr>
            </#list>
</table>
</body>
</html>
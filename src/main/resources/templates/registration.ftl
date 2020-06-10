<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SocialNetwork</title>
    <link rel="stylesheet" href="/static/style.css"/>
</head>
<body>
<form action="/" method="get">
    <div><input type="submit" value="Back"/></div>
</form>
<#if error??>
    <div style="color: red">
        <h1> ${error} </h1>
    </div>
</#if>

<form class="form-2" action="/registration" method="post">
    <p class="float1">
        <input type="text" name="username" placeholder="Username">
    </p>
    <p class="float1">
        <input type="password" name="password" placeholder="Password">
    </p>
    <p class="float1">
        <input type="text" name="firstName" placeholder="First name">
    </p>
    <p class="float1">
        <input type="text" name="lastName" placeholder="Last name">
    </p>
    <p class="float1">
        <input type="email" name="email" placeholder="Email">
    </p>
    <p class="clearfix">
        <input type="submit" name="submit" value="Add user">
    </p>
</form>
</body>
</html>

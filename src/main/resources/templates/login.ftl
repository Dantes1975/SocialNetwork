<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SocialNetwork</title>
    <link rel="stylesheet" href="/static/style.css" />
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


<form class="form-2" action="/login" method="post">
    <p class="float">
        <label for="login"><i class="icon-user"></i>Username</label>
        <input type="text" name="username" placeholder="Username">
    </p>
    <p class="float">
        <label for="password"><i class="icon-lock"></i>Password</label>
        <input type="password" name="password" placeholder="Password" class="showpassword">
    </p>
    <p class="clearfix">
        <input type="submit" name="submit" value="SignIn">
    </p>
</form>
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SocialNetwork</title>
    <link rel="stylesheet" href="/static/style.css"/>
</head>
<body>
<div class="header">
    <form action="/users/back" method="get">
        <input type="hidden" name="userId" value="${user.id}"/>
        <div><input type="submit" value="Back"/></div>
    </form>
</div>

<div class="sidebar">
    <b> Username: ${user.username}</b>
    <br>
    <b> First name: ${user.firstName}</b>
    <br>
    <b> Last name: ${user.lastName}</b>
    <br>
    <b> Email: ${user.email}</b>
    <br>
</div>
<div class="content">
    <h1 style="text-align: center">Enter new values</h1>
    <form class="form-2" action="/users/update" method="post">
        <p class="float1">
            <input type="hidden" name="id" value="${user.id}"/>
        </p>
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
            <input type="submit" name="submit" value="Update user">
        </p>
    </form>
</div>
</body>
</html>

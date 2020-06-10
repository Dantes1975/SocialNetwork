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
        <input type="hidden" name="userId" value="${userId}"/>
        <div><input type="submit" value="Back"/></div>
    </form>
</div>

<div class="sidebar">
    <b> Birth Day: ${information.birthDate}</b>
    <br>
    <b> Company: ${information.company}</b>
    <br>
    <b> Education: ${information.education}</b>
    <br>
    <b> About me: ${information.aboutMe}</b>
</div>
<div class="content">
    <h1 style="text-align: center">Enter new values</h1>
    <form class="form-2" action="/information/update" method="post">
        <p class="float1">
            <input type="hidden" name="userId" value="${userId}"/>
        </p>
        <p class="float1">
            <input type="date" name="birthDate" placeholder="Birth Date">
        </p>
        <p class="float1">
            <input type="text" name="education" placeholder="Education">
        </p>
        <p class="float1">
            <input type="text" name="company" placeholder="company">
        </p>
        <p class="float1">
            <textarea name="aboutMe" , rows="5" cols="40" placeholder="About me"></textarea>
        </p>
        <p class="clearfix">
            <input type="submit" name="submit" value="Update information">
        </p>
    </form>
</div>
</body>
</html>

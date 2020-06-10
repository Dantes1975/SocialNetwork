<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SocialNetwork</title>
    <link rel="stylesheet" href="/static/style.css"/>
</head>
<body>
<div class="header">
    <form action="/friends" method="get">
        <input type="hidden" name="userId" value="${userId}"/>
        <div><input type="submit" value="Back"/></div>
    </form>
</div>

<div class="sidebar">
    <#if user.mainPhoto??>
        <img src="/img/${user.mainPhoto.fileName}" width="120" height="200" border="2" decoding="auto">
    <#else >
        <div>
            <img src="/static/startphoto.jpg" width="150" height="200" border="2" decoding="auto">
        </div>
    </#if>
    <br>
    <form action="/conversation/create" method="post">
        <input type="hidden" name="authorId" value="${userId}"/>
        <input type="hidden" name="opponentId" value="${friendId}"/>
        <button style="color: green" type="submit">Open conversation</button>
    </form>
</div>
<div class="content">
    <b> First name: ${user.firstName}</b>
    <br>
    <b> Last name: ${user.lastName}</b>
    <br>
    <b> Email: ${user.email}</b>
    <br>
    <b> Birth Day: ${information.birthDate}</b>
    <br>
    <b> Company: ${information.company}</b>
    <br>
    <b> Education: ${information.education}</b>
    <br>
    <b> About me: ${information.aboutMe}</b>
</div>
</body>
</html>

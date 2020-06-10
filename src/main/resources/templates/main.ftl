<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SocialNetwork</title>
    <link rel="stylesheet" href="/static/style.css"/>
</head>
<body>
<div class="header">
    <form action="/logout" method="post">
        <div><input type="submit" value="Logout"/></div>
    </form>
</div>

<div class="sidebar">
    <#if user.mainPhoto??>
        <img src="/img/${user.mainPhoto.fileName}" width="120" height="200" border="2" decoding="auto">
        <form action="/photo/add" method="post" enctype="multipart/form-data">
            <input type="hidden" name="userId" value="${user.id}"/>
            <div><label> <input type="file" name="file"/> </label></div>
            <div><input type="submit" value="Update main photo"/></div>
        </form>
    <#else >
        <div>
            <img src="/static/startphoto.jpg" width="150" height="200" border="2" decoding="auto">
            <form action="/photo/add" method="post" enctype="multipart/form-data">
                <input type="hidden" name="userId" value="${user.id}"/>
                <div><label> <input type="file" name="file"/> </label></div>
                <div><input type="submit" value="Add main photo"/></div>
            </form>
        </div>
    </#if>
    <br>
    <div>
        <form action="/users/update/" method="get">
            <input type="hidden" name="userId" value="${user.id}"/>
            <button type="submit">Settings</button>
        </form>
    </div>
    <br>
    <div>
        <form action="/friends/" method="get">
            <input type="hidden" name="userId" value="${user.id}"/>
            <button type="submit">Friends</button>
        </form>
    </div>
    <br>
    <form action="/photos/get/" method="get">
        <input type="hidden" name="userId" value="${user.id}"/>
        <button type="submit">Photos</button>
    </form>
    <br>
    <div>
        <form action="/messages/mymessages" method="get">
            <input type="hidden" name="userId" value="${user.id}"/>
            <button type="submit">Messages</button>
        </form>
    </div>
    <br>
    <div>
        <form action="/information/get" method="get">
            <input type="hidden" name="userId" value="${user.id}"/>
            <button type="submit">Update information</button>
        </form>
    </div>
</div>
<div class="content">
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

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SocialNetwork</title>
    <link rel="stylesheet" href="/static/style.css" />
</head>
<body>
<div class="header">
    <form action="/users/back" method="get">
        <input type="hidden" name="userId" value="${userId}"/>
        <div><input type="submit" value="Back"/></div>
    </form>
</div>

<div class="sidebar">
    <form action="/photos/add" method="post" enctype="multipart/form-data">
        <input type="hidden" name="userId" value="${userId}"/>
        <div><label> <input type="file" name="file"/> </label></div>
        <div><input type="submit" value="Add photo"/></div>
    </form>
</div>
<div class="content">
    <#list photos as p>
        <div class="photo">
            <img src="/img/${p.fileName}" width="280" height="400" border="2" decoding="auto">
            <form action="/photos/delete" method="post">
                <input type="hidden" name="photoId" value="${p.id}"/>
                <input type="hidden" name="userId" value="${userId}"/>
                <button type="submit">Delete</button>
            </form>
        </div>
    </#list>
</div>
</body>
</html>


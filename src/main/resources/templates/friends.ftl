<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SocialNetwork</title>
    <link rel="stylesheet" href="/static/style.css"/>
    <style>
        h1 {
            display: inline-block;
            padding: 6px;
        }
    </style>

</head>
<body>
<div class="header">
    <form action="/users/back" method="get">
        <input type="hidden" name="userId" value="${userId}"/>
        <div><input type="submit" value="Back"/></div>
    </form>
</div>

<div class="sidebar">
    <h1>Users</h1>
    <#list users as u>
        <#if u.id!=1>
            <div>
                <h1> ${u.username} </h1>
                <h1>
                    <form action="/friends/add" method="post">
                        <input type="hidden" name="userId" value="${userId}"/>
                        <input type="hidden" name="friendId" value="${u.id}"/>
                        <button type="submit">ADD</button>
                    </form>
                </h1>
            </div>
        </#if>
    </#list>
</div>
<div class="content">
    <#if friends?has_content>
        <h1>Your friends</h1>
        <#list friends as friend>
            <div>
                <h1> ${friend.username}</h1>
                <h1>
                    <form action="/conversation/create" method="post">
                        <input type="hidden" name="authorId" value="${userId}"/>
                        <input type="hidden" name="opponentId" value="${friend.id}"/>
                        <button style="color: green" type="submit">Open conversation</button>
                    </form>
                </h1>
                <h1>
                    <form action="/friends/information" method="get">
                        <input type="hidden" name="userId" value="${userId}"/>
                        <input type="hidden" name="friendId" value="${friend.id}"/>
                        <button style="color: gray" type="submit">Information</button>
                    </form>
                </h1>
                <h1>
                    <form action="/friends/delete" method="post">
                        <input type="hidden" name="userId" value="${userId}"/>
                        <input type="hidden" name="friendId" value="${friend.id}"/>
                        <button style="color: red" type="submit">Delete</button>
                    </form>
                </h1>
            </div>
        </#list>
    <#else >
        <h1> You have no friends </h1>
    </#if>
</div>
</body>
</html>
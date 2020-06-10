<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SocialNetwork</title>
    <link rel="stylesheet" href="/static/style.css"/>
    <style>
        body {
            background: url("/static/bg-03.jpg");
            background-size: 100%;
        }

        h2 {
            display: inline-block;
            padding: 6px;
        }
    </style>
</head>
<body>
<div>
    <form action="/users/back" method="get">
        <input type="hidden" name="userId" value="${userId}"/>
        <div><input type="submit" value="Back"/></div>
    </form>
    <br>
    <#list messages as message>
        <div>
            <h2> ${message.author.username} ${message.text} ${message.departureDate}</h2>
            <h2>
                <form action="/conversation/get" method="post">
                    <input type="hidden" name="userId" value="${userId}"/>
                    <input type="hidden" name="conversationId" value="${message.conversationId}"/>
                    <div><input style="color: green" type="submit" value="To conversation"/></div>
                </form>
            </h2>
        </div>
    </#list>
</div>
</body>
</html>


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

        * {
            margin: 0;
            padding: 0;
        }

        p {
            padding: 10px;
        }

        .header1 {
            background: darkred;
            width: 715px;
            height: 100px;
        }

        .left {
            position: absolute;
            left: 0;
            top: 82px;
            bottom: 122px;
            width: 50%;
            border: 2px solid #fff;
        }

        .right {
            position: absolute;
            right: 0;
            top: 82px;
            bottom: 122px;
            width: 50%;
            border: 2px solid #fff;
        }

        .footer {
            bottom: 0px;
            background: #FEDFC0;
            width: 100%;
            height: 120px;
            border-top: 2px solid #7B5427;
            position: absolute;
        }
    </style>
</head>
<body>
<div class="header">
    <form action="/users/back" method="get">
        <input type="hidden" name="userId" value="${author.id}"/>
        <div><input type="submit" value="Back"/></div>
    </form>
    <h1>Hello, this is conversation between ${author.username} and ${opponent.username} </h1>
</div>
<div class="left">
    <h1 align="center"> Messages from ${author.username} </h1>
    <#list outgoing as o>
        <div>
            <h2>${o.departureDate}</h2>
            <h2> ${o.text} </h2>
            <h2><#if isAuthor>
                    <form action="/conversation/delete" method="post">
                        <input type="hidden" name="messageId" value="${o.id}"/>
                        <input type="hidden" name="conversationId" value="${conversationId}"/>
                        <input type="hidden" name="userId" value="${userId}"/>
                        <button style="color: red" type="submit">Delete</button>
                    </form>
                </#if>
            </h2>
        </div>
    </#list>

</div>
<div class="right">
    <h1 align="center"> Messages from ${opponent.username} </h1>
    <#list inbox as i>
        <div>
            <h2>${i.departureDate}</h2>
            <h2> ${i.text} </h2>
            <h2>
                <#if !isAuthor>
                    <form action="/conversation/delete" method="post">
                        <input type="hidden" name="messageId" value="${i.id}"/>
                        <input type="hidden" name="conversationId" value="${conversationId}"/>
                        <input type="hidden" name="userId" value="${userId}"/>
                        <button style="color: red" type="submit">Delete</button>
                    </form>
                </#if>
            </h2>
        </div>
    </#list>
</div>
<div class="footer">
    <h1> Create message</h1>
    <br>
    <#if isAuthor>
        <form action="/conversation/send/author" method="post">
            <input type="hidden" name="conversationId" value="${conversationId}"/>
            <input type="hidden" name="from" value="${author.id}"/>
            <input type="hidden" name="userId" value="${opponent.id}"/>
            <div><label> Text: <input type="text" name="text"/> </label></div>
            <div><input type="submit" value="Send message"/></div>
        </form>
    <#else >
        <form action="/conversation/send/opponent" method="post">
            <input type="hidden" name="conversationId" value="${conversationId}"/>
            <input type="hidden" name="from" value="${opponent.id}"/>
            <input type="hidden" name="userId" value="${author.id}"/>
            <div><label> Text: <input type="text" name="text"/> </label></div>
            <div><input type="submit" value="Send message"/></div>
        </form>
    </#if>
</div>
</body>
</html>


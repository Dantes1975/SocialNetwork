<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div>
        <@l.logout />
    </div>

    <div>
        <b>User Name : ${user.username}</b>
        <br>
        <b>First name: ${user.firstName}</b>
        <br>
        <b>Last name: ${user.lastName}</b>
        <br>
        <b>Email: ${user.email}</b>

        <br>
        <a href="/users">Users</a>


    </div>
    <br>
    <div>
        <form action="/users/" method="delete">
            <input type="hidden" name="userId"/>
            <button type="submit">Delete</button>
        </form>
    </div>
    <br>
    <div>
        <form action="/messages/mymessages" method="get">
            <input type="hidden" name="userId" value="${user.id}"/>
            <button type="submit">Messages</button>
        </form>
    </div>
</@c.page>
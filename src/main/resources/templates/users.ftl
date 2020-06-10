<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div>
        <@l.logout />
    </div>

    <div>
        <#list users as user>
            <h2> ${user.id} ${user.username} ${user.firstName} ${user.lastName} ${user.email} </h2>
            <form action="/users/delete" method="post">
                <input type="hidden" name="userId" value="${user.id}"/>
                <button type="submit">Delete</button>
            </form>
        </#list>
    </div>
</@c.page>
<!DOCTYPE html>
<html>
<header>
    <meta charset="UTF-8">
    <title>Latest</title>
    <link rel="stylesheet" href="/css/styles.css">
</header>
<body>
    <#if snippets?has_content>
        <#list snippets as snippet>
            <div>
                <span>${snippet.timestamp}</span>
                <pre>${snippet.code}</pre>
            </div>
        </#list>
    <#else>
        <h1> No snippets found</h1>
    </#if>
</body>
</html>
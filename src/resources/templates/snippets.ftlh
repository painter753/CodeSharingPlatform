<!DOCTYPE html>
<html>
<header>
    <meta charset="UTF-8">
    <title>Latest</title>
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">

    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</header>
<body>
    <#if snippets?has_content>
        <#list snippets as snippet>
            <div>
                <span>${snippet.timestamp}</span>
                <pre><code>${snippet.code}</code></pre>
            </div>
        </#list>
    <#else>
        <h1> No snippets found</h1>
    </#if>
</body>
</html>
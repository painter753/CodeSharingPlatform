<!DOCTYPE html>
<html>
<header>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">

    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</header>
<body>
    <#if snippet??>
        <div>
            <span id="load_date">${snippet.timestampCreated}</span>
            <pre id="code_snippet"><code>${snippet.code}</code></pre>
            <#if snippet.restricted>
                <#if snippet.hasViewsRestriction() && snippet.views gte 0>
                    <span id="views_restriction">${snippet.views} more views allowed</span>
                </#if>
                <#if snippet.hasTimeRestriction() && snippet.seconds gt 0>
                    <span id="time_restriction">The code will available for ${snippet.seconds} seconds</span>
                </#if>
            </#if>
        </div>
    </#if>
</body>
</html>
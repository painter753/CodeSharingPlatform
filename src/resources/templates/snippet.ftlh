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
    <div>
        <span id="load_date">${snippet.timestamp}</span>
        <pre id="code_snippet"><code>${snippet.code}</code></pre>
    </div>

</body>
</html>
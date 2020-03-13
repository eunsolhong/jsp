<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" indent="yes" encoding="utf-8"/>
<xsl:template match="list">
<html>
<head>
<title>책 목록</title>
</head>
<body>
now register book list
<ul>
<xsl:for-each select="book">
<li>
<b>
<xsl:value-of select="title"/>
</b>
(
<xsl:value-of select="price"/>
원)
<br/>
<i>
<xsl:value-of select="author"/>
</i>
</li>
</xsl:for-each>
</ul>
</body>
</html>
</xsl:template>
</xsl:stylesheet>
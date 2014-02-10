<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
<xsl:output method="xml"/>
<xsl:apply-templates/>
<xsl:template match="records">
<records>
<xsl:apply-templates/>
</records>
</xsl:template>

<xsl:template match="record">
  <record><xsl:apply-templates/></record>
 </xsl:template>

<xsl:template match="value">
  <xsl:variable name="attributeName" select="@name" />
  <xsl:variable name="myVar">
    <xsl:call-template name="string-replace-all">
      <xsl:with-param name="text" select="$attributeName" />
      <xsl:with-param name="replace" select="':'" />
      <xsl:with-param name="by" select="'_'" />
    </xsl:call-template>
  </xsl:variable>
  
  <xsl:element name="{$myVar}"><xsl:value-of select="text()"/></xsl:element>
 </xsl:template> 
 
 <xsl:template name="string-replace-all">
    <xsl:param name="text" />
    <xsl:param name="replace" />
    <xsl:param name="by" />
    <xsl:choose>
      <xsl:when test="contains($text, $replace)">
        <xsl:value-of select="substring-before($text,$replace)" />
        <xsl:value-of select="$by" />
        <xsl:call-template name="string-replace-all">
          <xsl:with-param name="text"
          select="substring-after($text,$replace)" />
          <xsl:with-param name="replace" select="$replace" />
          <xsl:with-param name="by" select="$by" />
        </xsl:call-template>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$text" />
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  
</xsl:stylesheet>
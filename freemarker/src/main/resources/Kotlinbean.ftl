import java.util.Date;

/**
* @author Generate By Freemarker .
* ${tableName}<#if tableComment?? && tableComment!="">: ${tableComment}</#if>
*/
data class  ${javaClassName} (

        <#list javaFieldMetaDataList as javaField>
        <#if javaField.dbComment?? && javaField.dbComment!="">
        /** ${javaField.dbComment} */
        </#if>
        var  ${javaField.javaName}: ${javaField.javaType} = ${javaField.defaultValue}<#if javaField_index!=(javaFieldMetaDataList?size-1)>, </#if>

        </#list>

)

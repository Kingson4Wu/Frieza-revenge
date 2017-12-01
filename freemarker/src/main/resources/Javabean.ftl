

/**
* @author Generate By Freemarker .
* ${tableName}<#if tableComment?? && tableComment!="">: ${tableComment}</#if>
*/
public class ${javaClassName} {

        <#list javaFieldMetaDataList as javaField>
        <#if javaField.dbComment?? && javaField.dbComment!="">
        /** ${javaField.dbComment} */
        </#if>
        private ${javaField.javaType} ${javaField.javaName};

        </#list>


}

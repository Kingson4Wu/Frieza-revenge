import java.util.Date;

/**
* @author Generate By Freemarker .
* ${tableName}<#if tableComment?? && tableComment!="">: ${tableComment}</#if>
*/
public class ${javaClassName}Mapper {

        INSERT INTO `${tableName}` (
        <#list javaFieldMetaDataList as javaField>`${javaField.dbName}`<#if javaField_index!=(javaFieldMetaDataList?size-1)>, </#if><#if (javaField_index+1)%5=0><br/>        </#if></#list>
        ) VALUES (
        <#list javaFieldMetaDataList as javaField>#\{${javaField.javaName}\}<#if javaField_index!=(javaFieldMetaDataList?size-1)>, </#if><#if (javaField_index+1)%5=0><br/>        </#if></#list>
        )

        UPDATE `${tableName}` SET
        <#list javaFieldMetaDataList as javaField>`${javaField.dbName}` = #\{${javaField.javaName}\}<#if javaField_index!=(javaFieldMetaDataList?size-1)>, </#if><#if (javaField_index+1)%5=0><br/>        </#if></#list>
        WHERE 1=1 AND

        SELECT  <#list javaFieldMetaDataList as javaField>t.`${javaField.dbName}`<#if javaField_index!=(javaFieldMetaDataList?size-1)>, </#if><#if (javaField_index+1)%5=0><br/>        </#if></#list>
        FROM  `${tableName}` t
        WHERE 1=1 AND

}

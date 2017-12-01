package com.kxw.freemarker.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wukunxin@bigdoctor.com
 * @date: 2017/12/2 上午12:02
 */
public class JavaCodeMetaDataHelper {

    public static Map<String, JavaClassMetaData> obtainJavaClassMap(List<String> tableList) throws Exception {
        Map<String, TableMetaData> tableMetaDataMap = TableMetadataHelper.obtainTableInfo(tableList);
        Map<String, JavaClassMetaData> javaClassMetaDataMap = new HashMap<>(tableMetaDataMap.size());

        for (Map.Entry<String, TableMetaData> tableMetaDataEntry : tableMetaDataMap.entrySet()) {
            JavaClassMetaData javaClassMetaData = new JavaClassMetaData();
            TableMetaData tableMetaData = tableMetaDataEntry.getValue();
            javaClassMetaData.setTableName(tableMetaData.getTableName());
            javaClassMetaData.setTableComment(tableMetaData.getTableComment());
            javaClassMetaData.setJavaClassName(getBigCamelCase(tableMetaData.getTableName()));

            List<TableMetaData.ColumnMetaData> columnMetaDataList = tableMetaData.getColumnMetaDataList();
            List<JavaClassMetaData.JavaFieldMetaData> javaFieldMetaDataList = new ArrayList<>(columnMetaDataList.size());
            for (TableMetaData.ColumnMetaData columnMetaData : columnMetaDataList) {
                JavaClassMetaData.JavaFieldMetaData javaFieldMetaData = new JavaClassMetaData.JavaFieldMetaData();
                javaFieldMetaData.setDbName(columnMetaData.getColumn());
                javaFieldMetaData.setDbComment(columnMetaData.getComment());
                javaFieldMetaData.setDbType(columnMetaData.getType());
                javaFieldMetaData.setJavaName(getCamelCase(columnMetaData.getColumn()));
                javaFieldMetaData.setJavaType(getJavaType(columnMetaData.getType()));
                javaFieldMetaDataList.add(javaFieldMetaData);
            }
            javaClassMetaData.setJavaFieldMetaDataList(javaFieldMetaDataList);

            javaClassMetaDataMap.put(tableMetaData.getTableName(), javaClassMetaData);

        }
        return javaClassMetaDataMap;
    }


    private static String getJavaType(final String sqlType) {

        if (sqlType.startsWith("bigint") || sqlType.startsWith("long")) {
            return "Long";
        } else if (sqlType.startsWith("integer") || sqlType.startsWith("int")) {
            return "Integer";
        } else if (sqlType.startsWith("float") || sqlType.startsWith("double")) {
            return "BigDecimal";
        } else if (sqlType.startsWith("varchar") || sqlType.startsWith("char")) {
            return "String";
        } else if (sqlType.startsWith("datetime") || sqlType.startsWith("timestamp") || sqlType.startsWith("date")) {
            return "Date";
        } else if (sqlType.startsWith("bigint")) {
            return "Long";
        } else if (sqlType.startsWith("tinyint")) {
            if ("tinyint(1)".equals(sqlType)) {
                return "Boolean";
            } else {
                return "Integer";
            }
        }
        return null;
    }


    private static String getCamelCase(final String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            //ch[0] = (char) (ch[0] - 32);
        }
        char c;
        for (int j = 1; j < ch.length; j++) {
            c = ch[j];
            if (c == '_') {
                if (ch[j + 1] >= 'a' && ch[j + 1] <= 'z') {
                    ch[j + 1] = (char) (ch[j + 1] - 32);
                }
            }
        }

        String camelCase = new String(ch).replaceAll("_", "");
        return getBoolField(camelCase);
    }

    private static String getBigCamelCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        char c;
        for (int j = 1; j < ch.length; j++) {
            c = ch[j];
            if (c == '_') {
                if (ch[j + 1] >= 'a' && ch[j + 1] <= 'z') {
                    ch[j + 1] = (char) (ch[j + 1] - 32);
                }
            }
        }
        return new String(ch).replaceAll("_", "");
    }

    private static String getBoolField(final String str) {

        String str2 = str;
        if (str2.startsWith("is")) {
            str2 = str2.substring(2, str.length());
        }
        char[] ch = str2.toCharArray();
        if (ch[0] >= 'A' && ch[0] <= 'Z') {
            ch[0] = (char) (ch[0] + 32);
        }
        str2 = new String(ch);
        /** java 关键字*/
        if ("package".equals(str2)) {
            return str;
        }
        return str2;
    }

}
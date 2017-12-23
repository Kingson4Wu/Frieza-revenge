package com.kxw.freemarker.demo;

import java.util.List;

/**
 * @author: wukunxin@bigdoctor.com
 * @date: 2017/12/2 上午12:41
 */
public class JavaClassMetaData {

    private String javaClassName;

    private String tableName;

    private String tableComment;

    private List<JavaFieldMetaData> javaFieldMetaDataList;

    public String getJavaClassName() {
        return javaClassName;
    }

    public void setJavaClassName(String javaClassName) {
        this.javaClassName = javaClassName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<JavaFieldMetaData> getJavaFieldMetaDataList() {
        return javaFieldMetaDataList;
    }

    public void setJavaFieldMetaDataList(List<JavaFieldMetaData> javaFieldMetaDataList) {
        this.javaFieldMetaDataList = javaFieldMetaDataList;
    }

    public static class JavaFieldMetaData {

        private String javaType;

        private String javaName;

        private String defaultValue;

        private String dbName;

        private String dbComment;

        private String dbType;

        public String getJavaType() {
            return javaType;
        }

        public void setJavaType(String javaType) {
            this.javaType = javaType;
        }

        public String getJavaName() {
            return javaName;
        }

        public void setJavaName(String javaName) {
            this.javaName = javaName;
        }

        public String getDbName() {
            return dbName;
        }

        public void setDbName(String dbName) {
            this.dbName = dbName;
        }

        public String getDbComment() {
            return dbComment;
        }

        public void setDbComment(String dbComment) {
            this.dbComment = dbComment;
        }

        public String getDbType() {
            return dbType;
        }

        public void setDbType(String dbType) {
            this.dbType = dbType;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }
    }
}
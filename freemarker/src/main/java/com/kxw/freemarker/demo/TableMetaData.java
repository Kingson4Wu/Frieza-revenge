package com.kxw.freemarker.demo;

import java.util.List;

/**
 * @author: wukunxin@bigdoctor.com
 * @date: 2017/12/1 下午11:34
 */
public class TableMetaData {
    private String tableName;

    private String tableComment;

    private List<ColumnMetaData> columnMetaDataList;


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

    public List<ColumnMetaData> getColumnMetaDataList() {
        return columnMetaDataList;
    }

    public void setColumnMetaDataList(List<ColumnMetaData> columnMetaDataList) {
        this.columnMetaDataList = columnMetaDataList;
    }

    public static class ColumnMetaData {

        private String column;

        private String type;

        private String comment;

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }
}
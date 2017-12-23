package com.kxw.freemarker.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wukunxin@bigdoctor.com
 * @date: 2017/12/1 下午11:16
 */
public class TableMetadataHelper {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/bigdoctor_treatment";
    private static final String user = "root";
    private static final String password = "123456";

    private static Connection getMySQLConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public static Map<String, TableMetaData> obtainTableInfo(List<String> tableName) throws Exception {
        Connection conn = getMySQLConnection();
        Statement stmt = conn.createStatement();

        Map<String, TableMetaData> tableMetaDataMap = new HashMap<>(tableName.size());
        for (int i = 0; i < tableName.size(); i++) {

            TableMetaData tableMetaData = new TableMetaData();

            String table = tableName.get(i);

            /** 获取表信息 */
            ResultSet tableRs = stmt.executeQuery("SHOW CREATE TABLE " + table);
            if (tableRs != null && tableRs.next()) {
                String createDDL = tableRs.getString(2);
                String comment = getTableComment(createDDL);
                tableMetaData.setTableName(table);
                tableMetaData.setTableComment(comment);
            }
            tableRs.close();

            /** 获取列信息 */
            List<TableMetaData.ColumnMetaData> columnMetaDataList = new ArrayList<>();
            ResultSet columnRs = stmt.executeQuery("show full columns from " + table);
            while (columnRs.next()) {
                TableMetaData.ColumnMetaData columnMetaData = new TableMetaData.ColumnMetaData();
                columnMetaData.setColumn(columnRs.getString("Field"));
                columnMetaData.setComment(columnRs.getString("Comment"));
                columnMetaData.setType(columnRs.getString("Type"));
                columnMetaDataList.add(columnMetaData);
            }
            columnRs.close();
            tableMetaData.setColumnMetaDataList(columnMetaDataList);
            tableMetaDataMap.put(table, tableMetaData);
        }
        stmt.close();
        conn.close();
        return tableMetaDataMap;
    }

    /**
     * 返回注释信息
     */
    private static String getTableComment(String table) {
        int index = table.indexOf("COMMENT='");
        if (index < 0) {
            return "";
        }
        String comment = table.substring(index + 9);
        comment = comment.substring(0, comment.length() - 1);
        return comment;
    }
}
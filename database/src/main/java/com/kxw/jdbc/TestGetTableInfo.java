package com.kxw.jdbc;


import java.io.*;
import java.sql.*;

public class TestGetTableInfo {

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        String tableName;
        StringBuffer buf = new StringBuffer();
        try {
            // load driver and create connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");

            // get the first table
            DatabaseMetaData dbMeta = conn.getMetaData();
            ResultSet dbRS = dbMeta.getTables(null, null, null,
                    new String[]{"TABLE"});
            dbRS.next();
            tableName = dbRS.getString(3);
            buf.append("public class " + tableName + " {\n\n");

            // create statement
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from " + tableName);

            // get each field of result set
            ResultSetMetaData rsMeta = rs.getMetaData();
            String fieldType;
            String fieldName;

            for (int i = 1; i < rsMeta.getColumnCount() + 1; i++) {
                // convert type name to lower case
                // MySQL 中表的字段类型是大写的（如 INT, VARCHAR），因此需要转换为 java 所能接受的小写
                // BTW: java 中没有 varchar 类型，因此需要判断一下然后将其转换为 String 类型
                fieldType = rsMeta.getColumnTypeName(i).toLowerCase();
                fieldName = rsMeta.getColumnName(i);

                // 将 varchar 类型转换成 String 类型
                if (fieldType.equals("varchar"))
                    fieldType = "String";

                // declare field
                buf.append(fieldType + " " + fieldName + ";\n\n");
                // create getter
                buf.append("public " + fieldType + " get" + fieldName
                        + "() {\n");
                buf.append("return this." + fieldName + ";\n}\n\n");
                // create setter
                buf.append("public void set" + fieldName + "(" + fieldType
                        + " " + fieldName + ") {\n");
                buf.append("this." + fieldName + " = " + fieldName + ";\n}\n\n");
            }
            buf.append("}");

            // write buf to file
            File file = new File("e:\\" + tableName + ".java");
            FileOutputStream fos = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(buf.toString());

            // close some objects
            bw.close();
            dbRS.close();
            rs.close();
            conn.close();

            System.out.println("File " + tableName + ".java successfully created.");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

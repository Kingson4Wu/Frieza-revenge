package com.kxw.freemarker.demo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.File;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

/**
 * @author: wukunxin@bigdoctor.com
 * @date: 2017/12/1 下午10:33
 */
public class CodeGenerateUtils {

    public static void main(String[] args) throws Exception {

        String tableName = "to";
        TableMetadataHelper.setUrl("jdbc.6.min");
        TableMetadataHelper.setUser("fn");
        TableMetadataHelper.setPassword("k14");


        Map<String, JavaClassMetaData> javaClassMetaDataMap = JavaCodeMetaDataHelper.obtainJavaClassMap(Arrays.asList(tableName));

        JavaClassMetaData javaClassMetaData = javaClassMetaDataMap.get(tableName);

        Configuration cfg = new Configuration(new Version("2.3.20"));
        String tplDir = CodeGenerateUtils.class.getClassLoader().getResource("").getPath();
        File tplFile = new File(tplDir);
        if (!tplFile.exists() || tplFile.isFile()) {
            System.out.println("模版目录不存在 ...");
            return;
        }
        cfg.setDirectoryForTemplateLoading(tplFile);

        // 建立数据模型
        Map<String, Object> root = new HashMap();
        root.put("javaClassName", javaClassMetaData.getJavaClassName());
        root.put("tableName", javaClassMetaData.getTableName());
        root.put("tableComment", javaClassMetaData.getTableComment());

        root.put("javaFieldMetaDataList", javaClassMetaData.getJavaFieldMetaDataList());

        //Template template = cfg.getTemplate("Kotlinbean.ftl");
        Template template = cfg.getTemplate("Javabean.ftl");
        //Template template = cfg.getTemplate("MybatisDao.ftl");

        //Writer writer = new PrintWriter(System.out);
        Writer writer=new StringWriter();

        template.process(root, writer);
        String content = writer.toString();
        String replaceContent = content
                .replaceAll("<br/>","\n")
                .replaceAll("\\\\[{]","{")
                .replaceAll("\\\\[}]","}");

        System.out.println(replaceContent);


    }
}
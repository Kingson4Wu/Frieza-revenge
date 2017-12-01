package com.kxw.freemarker.demo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.File;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.*;

/**
 * @author: wukunxin@bigdoctor.com
 * @date: 2017/12/1 下午10:33
 */
public class CodeGenerateUtils {

    public static void main(String[] args) throws Exception {


        Map<String, JavaClassMetaData> javaClassMetaDataMap = JavaCodeMetaDataHelper.obtainJavaClassMap(Arrays.asList("treatment_record"));

        JavaClassMetaData javaClassMetaData = javaClassMetaDataMap.get("treatment_record");

        //System.out.println(CodeGenerateUtils.class.getClassLoader().getResource("").getPath());

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

        Template template = cfg.getTemplate("Javabean.ftl");

        Writer writer = new PrintWriter(System.out);

        template.process(root, writer);


    }
}
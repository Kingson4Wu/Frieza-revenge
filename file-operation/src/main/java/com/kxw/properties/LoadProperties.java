package com.kxw.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by kxw on 2016/1/7.
 */
public class LoadProperties {
    public static InputStream in = null;
    public static Map<String, String> propertiesInfo = new HashMap<String, String>();
    public static void loadProperties() {
        try {
            in = LoadProperties.class.getClassLoader().getResourceAsStream("sysconf.properties");
            Properties props = new Properties();
            props.load(in);
            propertiesInfo.put("mail_address", props.getProperty("mail_address", "kingson_wu@163.com"));
            propertiesInfo.put("mail_user", props.getProperty("mail_user", "kingson_wu"));
            propertiesInfo.put("mail_password", props.getProperty("mail_password", "xxxxx"));
        } catch (Exception e) {
            throw new RuntimeException("加载配置文件失败,请确认class根目录下config.properties文件", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static String getPropertiesInfo(String key) {
        if (propertiesInfo.size() == 0) {
            loadProperties();
        }
        return propertiesInfo.get(key);
    }
    public static Map<String, String> getPropertiesInfo() {
        if (propertiesInfo.size() == 0) {
            loadProperties();
        }
        return propertiesInfo;
    }
}

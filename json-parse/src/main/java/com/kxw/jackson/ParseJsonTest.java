package com.kxw.jackson;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParseJsonTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String data = "{\"type\":2,\"range\":1,\"start\":1368417600,\"end\":1368547140,"
                + "\"cityName\":\"天津\",\"companyIds\":[\"12000001\"],\"companyNames\":[\"天津\"],"
                + "\"12000001\":{\"data\":[47947,48328,48573,48520],"
                + "\"timestamps\":[1368417600,1368417900,1368418200,1368418500]}}";
        String data2 = parseJson(data);
        System.out.println(data2);
    }

    public static String parseJson(String data) {
        // 用来展现解析Json得到的值
        StringBuffer buf = new StringBuffer();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(data); // 读取Json
            // rootNode.path("xx")返回的还是一个JsonNode对象，调用该JsonNode的相应方法，得到键对应的值
            int type = rootNode.path("type").asInt();
            int range = rootNode.path("range").asInt();
            long start = rootNode.path("start").asLong();
            long end = rootNode.path("end").asLong();
            String cityName = rootNode.path("cityName").asText();

            // 转换时间格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));

            String str = "类型(type):" + type + "\r\n" + "范围(range):" + range
                    + "\r\n" + "开始时间(start):"
                    + sdf.format(new Date(start * 1000)) + "\r\n"
                    + "结束时间(end):" + sdf.format(new Date(end * 1000)) + "\r\n"
                    + "城市名称(cityName):" + cityName;
            buf.append(str);
            // 得到companyIds的JsonNode对象
            JsonNode companyIds = rootNode.path("companyIds");
            JsonNode companyNames = rootNode.path("companyNames");

            // 遍历companyIds中的内容
            for (int i = 0; i < companyIds.size(); i++) {
                String companyId = companyIds.get(i).asText();
                // 本例解析的Json字符串中companyIds与companyNames的长度是相同的，所有直接遍历companyNames
                String companyName = companyNames.get(i).asText();
                // companyId的值：12000001，对应Json串中的
                // "12000001":{"data":[...],"timestamps":[....]}
                JsonNode infoNode = rootNode.path(companyId);
                // 得到"12000001":{"data":[...],"timestamps":[....]}中的data和timestamps的JsonNode对象
                JsonNode dataNode = infoNode.path("data");
                JsonNode timestampsNode = infoNode.path("timestamps");
                // 遍历data和timestamps 本例中data.size与timestamps.size是相等的

                buf.append("\r\n{\r\n  公司ID(companyId):" + companyId
                        + "\r\n  公司名称(companyName):" + companyName + "\r\n"
                        + " data:");
                for (int j = 0; j < dataNode.size(); j++) {
                    long dataValue = dataNode.get(j).asLong();
                    buf.append(dataValue + ",");
                }
                buf.append("\r\n time:");
                for (int k = 0; k < timestampsNode.size(); k++) {
                    long timeValue = timestampsNode.get(k).asLong();
                    buf.append(sdf.format(new Date(timeValue * 1000)) + ",");
                }
                buf.append("\r\n}\r\n");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return buf.toString();
    }

}
/**
 * result:
 类型(type):2
 范围(range):1
 开始时间(start):201305131200
 结束时间(end):201305142359
 城市名称(cityName):天津
 {
 公司ID(companyId):12000001
 公司名称(companyName):天津
 data:47947,48328,48573,48520,
 time:201305131200,201305131205,201305131210,201305131215
 }
 */
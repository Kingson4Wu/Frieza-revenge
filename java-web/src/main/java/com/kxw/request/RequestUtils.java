package com.kxw.request;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kxw on 2015/10/4.
 */
public class RequestUtils {

    private static final Pattern IP_PATTERN = Pattern.compile("^[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}$");

    private static final Pattern PRIVATE_IP_PATTERN = Pattern.compile("127\\.0\\.0\\.1");

    public static String getRequestUserIp(HttpServletRequest request) {
        String ip = parseRequestIp(request.getHeader("HTTP_CDN_SRC_IP"));

        if (StringUtils.isBlank(ip)) {
            ip = parseRequestIp(request.getHeader("X-Forwarded-For"));
        }
        if (StringUtils.isBlank(ip)) {
            ip = parseRequestIp(request.getHeader("HTTP_X_FORWARDED_FOR"));
        }
        if (StringUtils.isBlank(ip)) {
            ip = parseRequestIp(request.getHeader("HTTP_CLIENT_IP"));
        }
        if (StringUtils.isBlank(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    private static String parseRequestIp(String s) {
        if (StringUtils.isBlank(s) || "unknown".equalsIgnoreCase(s)) {
            return null;
        }

        String[] splits = s.split(",");
        if (splits == null || splits.length == 0) {
            return null;
        }

        for (int i = 0; i < splits.length; i++) {
            String split = splits[i];
            if (StringUtils.isNotBlank(split)) {
                String ip = split.trim();
                Matcher matcher = IP_PATTERN.matcher(ip);
                if (matcher.matches() && !isPrivateIp(ip)) {
                    return ip;
                }
            }
        }
        return null;
    }

    private static boolean isPrivateIp(String s) {
        if (StringUtils.isBlank(s)) {
            return false;
        }
        Matcher matcher = PRIVATE_IP_PATTERN.matcher(s);
        return matcher.matches();
    }
}

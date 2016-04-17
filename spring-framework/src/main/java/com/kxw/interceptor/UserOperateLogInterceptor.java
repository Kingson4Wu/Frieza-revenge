package com.kxw.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Created by kingsonwu on 15/12/25.
 * {<a href='http://blog.csdn.net/kingson_wu/article/details/44674317'>@link</a>}
 */
public class UserOperateLogInterceptor implements HandlerInterceptor {
    private final Logger userLogger = LoggerFactory.getLogger("user");

    private List<String> list = new ArrayList<String>();

    {
        list.add("/static/");
        list.add("/virtual/indexImage");
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        String requestUrl = request.getRequestURI();

        if (!list.contains(requestUrl)) {
            StringBuilder requestParams = new StringBuilder();
            //String userName = StringUtils.split((String) SecurityUtils.getSubject().getPrincipal(), "\\|")[0];
            String userName = "";

            String queryString = getQueryString(request);
            requestParams.append("  ");
            requestParams.append(queryString);
            requestParams.append("  ");
            if (request instanceof AbstractMultipartHttpServletRequest) {

                AbstractMultipartHttpServletRequest multipartRequest = (AbstractMultipartHttpServletRequest) request;
                MultiValueMap<String, MultipartFile> multiValueMap = multipartRequest
                        .getMultiFileMap();
                if (multiValueMap != null) {
                    requestParams.append("file=");
                    for (Map.Entry<String, List<MultipartFile>> entry : multiValueMap
                            .entrySet()) {
                        for (MultipartFile file : entry.getValue()) {
                            requestParams.append(file.getOriginalFilename());
                        }
                    }
                }
            }
            userLogger.info("username:{},request url:{},request parameter:{}",
                    userName, requestUrl, requestParams.toString());
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public String getQueryString(HttpServletRequest request) {
        boolean first = true;
        StringBuffer strbuf = new StringBuffer("");

        Enumeration<String> emParams = request.getParameterNames();
        // do-while
        do {
            if (!emParams.hasMoreElements()) {
                break;
            }
            String sParam = (String) emParams.nextElement();
            String[] sValues = request.getParameterValues(sParam);

            String sValue = "";
            for (int i = 0; i < sValues.length; i++) {
                sValue = sValues[i];
                if (sValue != null && sValue.trim().length() != 0
                        && first == true) {
                    first = false;
                    strbuf.append(sParam).append("=").append(sValue);
                } else if (sValue != null && sValue.trim().length() != 0
                        && first == false) {
                    strbuf.append("&").append(sParam).append("=")
                            .append(sValue);
                }
            }
        } while (true);

        return strbuf.toString();
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
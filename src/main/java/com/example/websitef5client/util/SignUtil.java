package com.example.websitef5client.util;

import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class SignUtil {

    private static final String SIGN_KEY = "sign";

    private static final String ACCESS_SECRET = "accessSecret";

    public static boolean verificationSign(HttpServletRequest request, String accessSecret) {
        Enumeration<?> pNames = request.getParameterNames();
        Map<String, Object> params = new HashMap<>();
        while (pNames.hasMoreElements()) {
            String pName = (String) pNames.nextElement();
            if (SIGN_KEY.equals(pName)) continue;
            Object pValue = request.getParameter(pName);
            params.put(pName, pValue);
        }
        String originSign = request.getParameter(SIGN_KEY);
        String sign = createSign(params, accessSecret);
        return sign.equals(originSign);
    }

    public static String createSign(Map<String, Object> params, String accessSecret) {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuilder temp = new StringBuilder();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = String.valueOf(value);
            }
            temp.append(valueString);
        }
        temp.append("&").append(ACCESS_SECRET).append("=").append(accessSecret);
        return DigestUtils.md5Hex(temp.toString()).toUpperCase();
    }
}
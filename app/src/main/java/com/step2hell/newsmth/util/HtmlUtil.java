package com.step2hell.newsmth.util;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HtmlUtil {

    public static String getHTML(String url) throws Exception {
        URL uri = new URL(url);
        URLConnection connection = uri.openConnection();
        InputStream in = connection.getInputStream();
        byte[] buf = new byte[1024];
        StringBuffer sb = new StringBuffer();
        while ((in.read(buf, 0, buf.length)) > 0) {
            sb.append(new String(buf, "GBK"));
        }
        in.close();
        return sb.toString();
    }

    public static String getSubSimple(String soap, String rgex) {
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            return m.group(1);
        }
        return "";
    }
}

package com.tong.http;

import com.tong.kit.Httpkit;
import com.tong.kit.StringKit;

import java.net.HttpCookie;
import java.net.URI;
import java.util.*;

/**
 * @Author stonewong
 * @Description TODO
 * @Date 2019/6/1 17:01
 * @Param
 * @return
 **/
public class HttpRequest {

    private String raw;
    private String uri;
    private HttpMethod method;
    private HttpSession session;

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getUri() {
        return uri;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public String getQueryString() {
        return queryString;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Map<String, Object> getGetData() {
        return getData;
    }

    public Map<String, Object> getPostData() {
        return postData;
    }

    public void setPostData(Map<String, Object> postData) {
        this.postData = postData;
    }

    public Map<String, HttpCookie> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, HttpCookie> cookies) {
        this.cookies = cookies;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    private String queryString;
    private String data;
    private Map<String, Object> getData;
    private Map<String, Object> postData;
    private Map<String, HttpCookie> cookies;

    private Map<String, String> headers = new HashMap<String, String>();

    public HttpRequest(HttpSession session) {
        this.session = session;
        parse();
    }

    private void parse() {
        this.raw = session.line();
        //根据自定义字符为分界符进行拆分，并将结果进行封装提供对应方法进行遍历取值，
        StringTokenizer tokenizer = new StringTokenizer(raw);
        String method_name = tokenizer.nextToken().toUpperCase();
        method = HttpMethod.valueOf(method_name);

        String uri = tokenizer.nextToken();
        this.uri = uri;

        int questionIdx = uri.indexOf('?');
        if (questionIdx != -1) {
            String queryString = uri.substring(questionIdx + 1);
            this.setQueryString(queryString);
            this.setGetData(Httpkit.parseData(queryString));
            uri = uri.substring(0, questionIdx);
            this.setUri(uri);
        }

        String[] lines = raw.split("\r\n");
        for (int i = 1; i < lines.length; i++) {
            String[] keyVal = lines[i].split(":", 2);
            headers.put(keyVal[0], keyVal[1]);
        }

        if (headers.containsKey(HttpHeader.COOKIE)) {
            List<HttpCookie> cookies = new LinkedList<HttpCookie>();
            StringTokenizer tok = new StringTokenizer(headers.get(HttpHeader.COOKIE), ";");
            while (tok.hasMoreElements()) {
                String token = tok.nextToken();
                int eqIdx = token.indexOf('=');
                if (eqIdx == -1) {
                    continue;
                }
                String key = token.substring(0, eqIdx);
                String value = token.substring(eqIdx + 1);

                cookies.add(new HttpCookie(key, value));
            }
            setCookies(cookies);
        }

    }

    private void setQueryString(String queryString) {
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setGetData(Map<String, Object> getData) {
        this.getData = getData;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setCookies(List<HttpCookie> cookies) {
        Map<String, HttpCookie> map = new HashMap<String, HttpCookie>();
        for (HttpCookie cookie : cookies) {
            map.put(cookie.getName(), cookie);
        }
        this.cookies = map;
    }

    @Override
    public void finalize() {
        if (postData != null) {
            for (Object value : postData.values()) {
                if (value instanceof HttpFileUpload) {
                    HttpFileUpload u = (HttpFileUpload) value;
                    if (!u.getTempFile().delete()) {
                        u.getTempFile().deleteOnExit();
                    }
                }
            }
        }
    }
}

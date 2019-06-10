package com.tong.http;

import com.tong.kit.IOKit;
import com.tong.kit.StringKit;
import sun.misc.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author stonewong
 * @Description TODO
 * @Date 2019/6/1 17:03
 * @Param
 * @return
 **/
public class HttpResponse {

    private String version = "HTTP/1.1";
    private HttpStatus status = HttpStatus.OK;
    private String reason = "";
    private Map<String, String> headers = new LinkedHashMap<String, String>();
    private InputStream response;
    private String body;
    private long length = 0;

    public HttpResponse(HttpStatus status, InputStream inputStream) {
        this.status = status;
        this.response = inputStream;
        try {
            this.length = inputStream.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HttpResponse() {

    }

    public void addDefaultHeaders() {
        headers.put(HttpHeader.DATE, new Date().toString());
        headers.put(HttpHeader.SERVER, "tong");
        headers.put(HttpHeader.CONTENT_LENGTH, length + "");
        headers.put(HttpHeader.CONNECTION, "close");
    }


    public String getVersion() {
        return null;
    }

    public String getStatusCode() {
        return null;
    }

    public String getReason() {
        return null;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public byte[] bytes() {
        try{
            if(StringKit.isNotBlank(body)){
                return body.getBytes("UTF-8");

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (null != response){
            try{
                return  IOKit.getBytesFromInputStream(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public String body(){
        if(StringKit.isNotBlank(body)) {
            return body;
        }
        if (null != response){
            return new BufferedReader(new InputStreamReader(response))
                    .lines().collect(Collectors.joining("\n"));
        }
        return null;
    }

    public HttpResponse reason(String reason) {
        this.reason = reason;
        return this;
    }

    public HttpResponse reason(HttpStatus httpStatus, String reason) {
        this.status = httpStatus;
        this.reason = reason;
        return this;
    }

    public void setLength(long length) {
        this.length = length;
    }
}

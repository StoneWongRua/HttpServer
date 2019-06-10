package com.tong.handler;

import com.tong.http.HttpRequest;
import com.tong.http.HttpResponse;

public interface RequestHandler {

    public HttpResponse handle(HttpRequest request);
}

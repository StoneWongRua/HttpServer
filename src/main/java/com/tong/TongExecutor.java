package com.tong;

import com.tong.handler.RequestHandler;
import com.tong.http.HttpRequest;
import com.tong.http.HttpResponse;
import com.tong.http.HttpSession;

import java.util.List;

/**
 * @Author stonewong
 * @Description TODO
 * @Date 2019/6/1 17:04
 * @Param
 * @return
 **/
public class TongExecutor implements Runnable {

    private HttpRequest httpRequest;
    private List<RequestHandler> handlers;
    public TongExecutor(HttpRequest httpRequest, List<RequestHandler> handlers) {
        this.httpRequest = httpRequest;
        this.handlers = handlers;
    }

    @Override
    public void run() {
        for (RequestHandler requestHandler : handlers) {
            HttpResponse resp = requestHandler.handle(httpRequest);
            if (resp != null) {
                HttpSession httpSession = httpRequest.getSession();
                httpSession.sendResponse(resp);
                httpSession.close();
                return;
            }
        }
    }
}

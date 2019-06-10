package com.tong.http;

/**
 * @Author stonewong
 * @Description TODO
 * @Date 2019/6/10 16:31
 * @Param
 * @return
 **/
public class HttpHeader {

    //用来指定在这次的请求/响应链中的所有缓存机制 都必须 遵守的指令
    public static final String CACHE_CONTROL = "Cache-Control";

    //浏览器想要优先使用的连接类型
    public static final String CONNECTION = "Connection";

    //指示回复的内容该以何种形式展示
    public static final String CONTENT_DISPOSITION = "Content-Disposition";

    //以 八位字节数组 （8位的字节）表示的请求体的长度
    public static final String CONTENT_LENGTH = "Content-Length";

    //请求体的 多媒体类型 （用于POST和PUT请求中）
    public static final String CONTENT_TYPE = "Content-Type";

    //发送该消息的日期和时间(按照 RFC 7231 中定义的"超文本传输协议日期"格式来发送)
    public static final String DATE = "Date";

    //表明客户端要求服务器做出特定的行为
    public static final String EXPECT = "Expect";

    //服务器的名字
    public static final String SERVER = "Server";

    //发起一个针对 跨来源资源共享 的请求
    public static final String ORIGIN = "Origin";


    //表示浏览器所访问的前一个页面
    // 正是那个页面上的某个链接将浏览器带到了当前所请求的这个页面。
    public static final String REFERER = "Referer";

    //用来将实体安全地传输给用户的编码形式。
    public static final String TRANSFER_ENCODING = "Transfer-Encoding";

    //浏览器的浏览器身份标识字符串
    public static final String USER_AGENT = "User-Agent";


    //用于超文本传输协议的认证的认证信息
    public static final String AUTHORIZATION = "Authorization";

    //之前由服务器通过 Set- Cookie （下文详述）发送的一个 超文本传输协议Cookie
    public static final String COOKIE = "Cookie";

    //HTTP cookie
    public static final String SET_COOKIE = "Set-Cookie";
}
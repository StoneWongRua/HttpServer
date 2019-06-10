package com.tong.http;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author stonewong
 * @Description TODO
 * @Date 2019/6/10 12:16
 * @Param
 * @return
 **/
public enum  HttpStatus {

    /**
     * 1xx消息
     * 这一类型的状态码，代表请求已被接受，需要继续处理。
     * 这类响应是临时响应，只包含状态行和某些可选的响应头信息，并以空行结束。
     * 由于HTTP/1.0协议中没有定义任何1xx状态码，所以除非在某些试验条件下，服务器禁止向此类客户端发送1xx响应。
     * 这些状态码代表的响应都是信息性的，标示客户应该采取的其他行动。
     * */

    // 服务器已经接收到请求头，并且客户端应继续发送请求主体
    // 或者如果请求已经完成，忽略这个响应
    CONTINUE(100, "Continue"),

    //服务器已经理解了客户端的请求，并将通过Upgrade消息头通知客户端采用不同的协议来完成这个请求
    SWITCHING_PROTOCOLS(101, "SWITCHING PROTOCOLS"),

    // 表示服务器已经收到并且正在处理请求，但无响应可用
    PROCESSING(102, "PROCESSING"),

    /**
     * 2xx成功
     * 这一类型的状态码，表示请求已经被服务器成功接收，理解，并且接受
     * */

    // 请求已经成功，请求所希望的响应头或者数据体将随此响应返回
    // GET请求中，响应将包含与请求的资源相对应的实体
    // POST请求中，响应将包含描述或者操作结果的实体
    OK(200, "OK"),

    // 请求已经被时间，而且有一个新的资源已经依据请求的需要而建立
    CREATED(201, "CREATED"),


    // 服务器是一个转换代理服务器
    NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),

    // 服务器成功处理了请求，但是没有返回内容
    NO_CONTENT(204, "NO CONTENT"),

    // 服务器成功处理了请求，没有返回任何内容，但是此响应要求请求者重置文档视图
    RESET_CONTENT(205, "RESET CONTENT"),

    // 服务器已经成功处理了部分GET请求
    // 此响应一般用来实现断点续传或者将一个大文档分解为多个下载段同时下载
    PARTIAL_CONTENT(206, "PARTIAL CONTENT"),

    //之后的消息体将是一个XML消息，并且依据之前子请求数量的不同，会包含一系列独立的响应代码
    MULTI_STATUS(207, "MULTI STATUS"),

    // DAV绑定的成员已经在多状态响应之前的部分被列举，且未被再次包含
    ALREADY_REPORTE(208, "ALREADY REPORTED"),

    //服务器已经满足了对资源的请求，对实体请求的一个或者多个实体操作的结果表示
    IM_USED(209, "IM USED"),

    /**
     * * 3xx重定向
     * 这类状态码代表需要客户端采取进一步的操作才能完成请求。
     * 通常，这些状态码用来重定向，后续的请求地址（重定向目标）在本次响应的Location域中指明。
     */

    //被请求的资源有一系列可供选择的回馈信息
    // 每个都有自己特定的地址和浏览器驱动的商议信息。
    // 用户或浏览器能够自行选择一个首选的地址进行重定向。
    // 除非这是一个HEAD请求，否则该响应应当包括一个资源特性及地址的列表的实体，以便用户或浏览器从中选择最合适的重定向地址。
    MULTIPLE_CHOICES(300, "Multiple Choices"),

    //被请求的资源已永久移动到新位置，并且将来任何对此资源的引用都应该使用本响应返回的若干个URI之一。
    // 如果可能，拥有链接编辑功能的客户端应当自动把请求的地址修改为从服务器反馈回来的地址。
    // 除非额外指定，否则这个响应也是可缓存的
    MOVED_PERMANENTLY(301, "Moved Permanently"),

    //要求客户端执行临时重定向
    FOUND(302, "Found"),

    //对应当前请求的响应可以在另一个URI上被找到。
    // 当响应于POST（或PUT / DELETE）接收到响应时，客户端应该假定服务器已经收到数据
    // 并且应该使用单独的GET消息发出重定向。
    SEE_OTHER(303, "See Other"),

    //表示资源在由请求头中的If-Modified-Since或If-None-Match参数指定的这一版本之后，未曾被修改。
    //在这种情况下，由于客户端仍然具有以前下载的副本，因此不需要重新传输资源
    NOT_MODIFIED(304, "Not Modified"),

    //被请求的资源必须通过指定的代理才能被访问。
    //Location域中将给出指定的代理所在的URI信息，接收者需要重复发送一个单独的请求，通过这个代理才能访问相应资源。
    USE_PROXY(305, "Use Proxy"),

    //在这种情况下，请求应该与另一个URI重复，但后续的请求应仍使用原始的URI。
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),



    /**
     * 4xx客户端错误
     * 这类的状态码代表了客户端看起来可能发生了错误，妨碍了服务器的处理。
     * 除非响应的是一个HEAD请求，否则服务器就应该返回一个解释当前错误状况的实体，以及这是临时的还是永久性的状况。
     * 这些状态码适用于任何请求方法。浏览器应当向用户显示任何包含在此类错误响应中的实体内容。
     * */

    //由于明显的客户端错误
    // 例如，格式错误的请求语法，太大的大小，无效的请求消息或欺骗性路由请求
    // 服务器不能或不会处理该请求。
    BAD_REQUEST(400, "Bad Request"),

    //401语义即“未认证”，即用户没有必要的凭据。
    UNAUTHORIZED(401, "Unauthorized"),

    //该状态码是为了将来可能的需求而预留的。
    PAYMENT_REQUIRED(402, "Payment Required"),

    //服务器已经理解请求，但是拒绝执行它。
    // 与401响应不同的是，身份验证并不能提供任何帮助，而且这个请求也不应该被重复提交。
    // 如果这不是一个HEAD请求，而且服务器希望能够讲清楚为何请求不能被执行，那么就应该在实体内描述拒绝的原因。
    // 当然服务器也可以返回一个404响应，假如它不希望让客户端获得任何信息。
    FORBIDDEN(403, "Forbidden"),

    //请求失败，请求所希望得到的资源未被在服务器上发现，但允许用户的后续请求。
    NOT_FOUND(404, "Not Found"),

    //请求行中指定的请求方法不能被用于请求相应的资源。
    // 该响应必须返回一个Allow头信息用以表示出当前资源能够接受的请求方法的列表。
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

    //请求的资源的内容特性无法满足请求头中的条件，因而无法生成响应实体，该请求不可接受。
    NOT_ACCEPTABLE(406, "Not Acceptable"),

    //与401响应类似，只不过客户端必须在代理服务器上进行身份验证。
    PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),

    // 请求超时。
    REQUEST_TIMEOUT(408, "Request Timeout"),

    //表示因为请求存在冲突无法处理该请求，例如多个同步更新之间的编辑冲突
    CONFLICT(409, "Conflict"),

    //表示所请求的资源不再可用，将不再可用。
    // 当资源被有意地删除并且资源应被清除时，应该使用这个。
    GONE(410, "Gone"),

    //服务器拒绝在没有定义Content-Length头的情况下接受请求。
    LENGTH_REQUIRED(411, "Length Required"),

    //服务器在验证在请求的头字段中给出先决条件时，没能满足其中的一个或多个。
    PRECONDITION_FAILED(412, "Precondition Failed"),

    //表示服务器拒绝处理当前请求，因为该请求提交的实体数据大小超过了服务器愿意或者能够处理的范围。
    REQUEST_ENTITY_TOO_LARGE(413, "Request Entity Too Large"),

    //表示请求的URI长度超过了服务器能够解释的长度，因此服务器拒绝对该请求提供服务
    //通常的情况包括：
    //本应使用POST方法的表单提交变成了GET方法，导致查询字符串（英语：Query string）过长。
    //重定向URI“黑洞”，例如每次重定向把旧的URI作为新的URI的一部分，导致在若干次重定向后URI超长。
    //客户端正在尝试利用某些服务器中存在的安全漏洞攻击服务器。
    // 这类服务器使用固定长度的缓冲读取或操作请求的URI，当GET后的参数超过某个数值后，可能会产生缓冲区溢出，导致任意代码被执行。
    // 没有此类漏洞的服务器，应当返回414状态码。
    REQUEST_URI_TOO_LONG(414, "Request-URI Too Long"),

    //对于当前请求的方法和所请求的资源，请求中提交的互联网媒体类型并不是服务器中所支持的格式，因此请求被拒绝。
    // 例如，客户端将图像上传格式为svg，但服务器要求图像使用上传格式为jpg。
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),

    //客户端已经要求文件的一部分（Byte serving（英语：Byte serving）），但服务器不能提供该部分。
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested Range Not Satisfiable"),

    //在请求头Expect中指定的预期内容无法被服务器满足，
    // 或者这个服务器是一个代理服显的证据证明在当前路由的下一个节点上，Expect的内容无法被满足。
    EXPECTATION_FAILED(417, "Expectation Failed"),

    /**
     * 5xx服务器错误
     * 表示服务器无法完成明显有效的请求。
     * 这类状态码代表了服务器在处理请求的过程中有错误或者异常状态发生.
     * 也有可能是服务器意识到以当前的软硬件资源无法完成对请求的处理。
     */

    //通用错误消息，服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理。没有给出具体错误信息。
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    //服务器不支持当前请求所需要的某个功能。
    NOT_IMPLEMENTED(501, "Not Implemented"),

    //作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应。
    BAD_GATEWAY(502, "Bad Gateway"),

    //由于临时的服务器维护或者过载，服务器当前无法处理请求。
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),

    //作为网关或者代理工作的服务器尝试执行请求时，未能及时从上游服务器收到响应
    GATEWAY_TIMEOUT(504, "Gateway Timeout"),

    //服务器不支持，或者拒绝支持在请求中使用的HTTP版本。
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported");


    private static Map<Integer, HttpStatus> codes = new HashMap<Integer, HttpStatus>();

    private int code;

    private String text;

    static {
        for (HttpStatus status : HttpStatus.values()) {
            codes.put(status.getCode(), status);
        }
    }


    private HttpStatus(int code, String phrase) {
        this.code = code;
        this.text = phrase;
    }


    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return text;
    }

}

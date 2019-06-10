package com.tong;

import com.tong.http.HttpResponse;
import com.tong.http.HttpRequest;
import com.tong.handler.RequestHandler;
import com.tong.http.HttpSession;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 * @Author stonewong
 * @Description TODO
 * @Date 2019/6/1 14:47
 * @Param
 * @return
 **/
public class Tong {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Tong.class));

    //普通socket编程时，accept方法会一直阻塞，直到有客户端请求的到来，并返回socket进行相应的处理
    //NIO采用选择器返回已经准备好的socket，并且按照顺序处理，基于通道(Channel)和缓冲区(Buffer)来进行数据的传输
    private Selector selector = Selector.open();

    /**
     * Socket服务
     */
    private ServerSocketChannel server = ServerSocketChannel.open();//可以监听新进来的 TCP 连接的通道

    /**
     * 是否已经在运行
     * @throws IOException
     */
    private boolean isRunning = false;

    /**
     * debug模式
     * @throws IOException
     */
    private boolean debug = true;

    /**
     * 处理器
     * @throws IOException
     */
    private List<RequestHandler> handlers = new LinkedList<RequestHandler>();

    /**
     * HttpWeb上下文路径
     * @throws IOException
     */
    private String contextPath = "/";

    /**
     * 创建一个Socket
     * 捆绑一个端口
     * “如果一个TCP客户或者服务器未曾调用bind捆绑一个端口，当调用connect或listen时，内核就要为相应的套接字选择一个临时接口。”
     * @throws IOException
     */
    public Tong bind(InetSocketAddress address)throws IOException{//InetSocketAddress是端口地址类型，基类是SocketAddress类
        server.socket().bind(address);
        server.configureBlocking(false);
        //将serverSocketChannel注册到Selector
        //并指定该信道key值的属性为OP_ACCEPT
        server.register(selector, SelectionKey.OP_ACCEPT);
        return this;
    }

    public Tong bind(int port) throws IOException{
        return bind(new InetSocketAddress(port));
    }

    /**
     * 设置一个端口
     * @throws IOException
     */
    public Tong(int port)throws IOException{
        this.bind(port);
    }

    /**
     * 添加一个请求处理器
     * @throws IOException
     */
    public Tong addHandler(RequestHandler requestHandler){
        handlers.add(requestHandler);
        return this;
    }

    /**
     * 移除一个请求处理器
     * @throws IOException
     */
    public void removeRequestHandler(RequestHandler requestHandler){
        handlers.remove(requestHandler);
    }

    /**
     * 启动服务
     * @throws IOException
     */
    public void start(){
        isRunning  = true;
        LOGGER.info("HST Server Listen on 0.0.0.0:" + server.socket().getLocalPort());
        while(isRunning){
            try{
                selector.selectNow();
                Iterator<SelectionKey> i = selector.selectedKeys().iterator();
                while(i.hasNext()){
                    SelectionKey key = i.next();
                    i.remove();
                    if(!key.isValid()){
                        continue;
                    }
                    try{
                        if(key.isAcceptable()){
                            //接受socket
                            SocketChannel client = server.accept();
                            //非阻塞模式
                            client.configureBlocking(false);
                            //注册选择器到socket
                            client.register(selector, SelectionKey.OP_READ);
                        }
                        else if(key.isReadable()){
                            //从连接上读取
                            //获取socket通道
                            SocketChannel client = (SocketChannel) key.channel();
                            //获取会话
                            HttpSession session = (HttpSession)key.attachment();

                            //如果没有则创建一个会话
                            if(session == null){
                                session = new HttpSession(client);
                                key.attach(session);
                            }

                            //读取会话数据
                            session.readData();

                            String line;
                            while((line = session.read()) != null){
                                if(line.isEmpty()){
                                    this.execute(new TongExecutor(new HttpRequest(session) , handlers));
                                }
                            }
                        }
                    }catch (Exception ex){
                        System.err.println("Error handling client: " + key.channel());
                        if(isDebug()){
                            ex.printStackTrace();
                        }
                        else {
                            System.err.println(ex);
                            System.err.println("\tat" + ex.getStackTrace()[0]);
                        }
                        if(key.attachment() instanceof HttpSession){
                            ((HttpSession) key.attachment()).close();
                        }
                    }
                }
            } catch (IOException ex) {
                //停止服务
                this.shutdown();
                throw new RuntimeException(ex);
            }
        }
    }

    private ExecutorService executor;
    private Future<?> execute(Runnable runnable){
        if(this.executor == null){
            this.executor = Executors.newCachedThreadPool();
        }
        return executor.submit(runnable);
    }

    /**
     * 处理请求
     */
    protected void handle(HttpRequest request)throws IOException{
        for(RequestHandler requestHandler: handlers){
            HttpResponse resp = requestHandler.handle(request);
            if (resp != null){
                request.getSession().sendResponse(resp);
                return;
            }
        }
    }

    /**
     * 停止服务
     */
    private void shutdown() {
        isRunning = false;
        try{
            selector.close();
            server.close();
        }catch (IOException ex){

        }
    }


    public Selector getSelector() {

        return selector;
    }

    public ServerSocketChannel getServer() {

        return server;
    }

    public boolean isRunning() {

        return isRunning;
    }

    public boolean isDebug() {

        return debug;
    }

    public List<RequestHandler> getHandlers() {

        return handlers;
    }

}

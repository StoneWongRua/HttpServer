package com.tong.http;

import com.tong.Const;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharacterCodingException;
import java.util.Map;

/**
 * @Author stonewong
 * @Description TODO
 * @Date 2019/6/1 16:25
 * @Param
 * @return
 **/
public class  HttpSession {

    private SocketChannel channel;

    //通过传入一个capacity用来指定buffer的容量
    //返回了一个HeapByteBuffer的对象
    //https://blog.csdn.net/liuguangqiang/article/details/52128076
    private ByteBuffer buffer = ByteBuffer.allocate(2048);

    private StringBuffer readLines = new StringBuffer();
    private int mark = 0;

    public String line(){
        return readLines.toString();
    }

    public String read() {
        StringBuffer sb = new StringBuffer();
        int l = -1;
        while(buffer.hasRemaining()){
            char c = (char)buffer.get();
            sb.append(c);
            if (c == '\n' && l == '\r'){
                //做标记
                mark = buffer.position();
                // 扩展
                readLines.append(sb);
                return sb.substring(0, sb.length() - 2);


            }
            l = c;
        }
        return null;
    }


    /**
     * get more data from the stream
     *
     * */
    public void readData() throws IOException {
        buffer.limit(buffer.capacity());//Limit在读模式下表示最多能读多少数据，此时和缓存中的实际数据大小相同
        int read = channel.read(buffer);
        if(read != -1){
            buffer.flip();//把buffer的当前位置更改为buffer缓冲区的第一个位置
            buffer.position(mark);
        }
    }

    public void writeLine(String line) throws IOException {
        channel.write(Const.ENCODER.encode(CharBuffer.wrap(line + "\r\n")));
    }


    public void sendResponse(HttpResponse response) {
        response.addDefaultHeaders();
        try{
            writeLine(response.getVersion() + " " + response.getStatusCode() + " " + response.getReason());
            for(Map.Entry<String, String> header: response.getHeaders().entrySet()){
                writeLine(header.getKey() + ": " + header.getValue());

            }
            writeLine("");
            byte[] content = response.bytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public HttpSession(SocketChannel channel) {
        this.channel = channel;
    }





    public void close() {
    }

}

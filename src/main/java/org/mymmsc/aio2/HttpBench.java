package org.mymmsc.aio2;

import org.mymmsc.api.assembly.Api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * HTTP 批量请求
 *
 * @author wangfeng
 * @date 2016年1月10日 上午7:57:42
 */
public class HttpBench extends Asio<HttpContext> {
    // http://100.73.17.2/dsmp/api/v2/collection/collectRecord.cgi
    //private String host = "100.100.1.1";
    //private String host = "www.baidu.com";
    private final static String UTF8 = "utf-8";
    private String host = "100.73.17.2";

    private int port = 80;
    private String path = "/dsmp/api/v2/collection/collectRecord.cgi";
    @SuppressWarnings("unused")
    private String postdata = "memberId=12345678901";
    private int postlen = 0;
    private int connectTimeout = 30 * 1000;
    private int readTimeout = 30 * 1000;

    /**
     * 并发数
     */
    private int concurrency = 100;
    /**
     * 总请求数, -1为无限制
     */
    private int number = 1000;

    private int good = 0;
    private int bad = 0;
    private int requests = 0;

    /**
     * @throws IOException
     */
    public HttpBench() throws IOException {
        super();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        HttpBench hb = null;
        try {
            long tm = System.currentTimeMillis();
            hb = new HttpBench();
            hb.start();
            System.out.println("\nuse " + (System.currentTimeMillis() - tm) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAccepted(HttpContext context) {
        //
    }

    @Override
    public void onConnected(HttpContext context) {
        int posting = 0;
        String request = null;
        boolean keepalive = true;
        boolean isproxy = false;
        String fullurl = "";
        String cookie = "";
        String auth = "";

        String hdrs = String.format("Host: %s\r\n", host);
        hdrs += "User-Agent: HttpBench/1.0.1\r\n";
        hdrs += "Accept: */*\r\n";
        // 已连接server端, 超时改用读写超时参数
        context.setTimeout(readTimeout);

        if (!Api.isEmpty(postdata)) {
            postdata = postdata.trim();
            postlen = postdata.length();
        }

        if (postlen <= 0) {
            posting = 0;
        } else {
            posting = 1;
        }
        /* setup request */
        if (posting <= 0) {
            request = String.format("%s %s HTTP/1.0\r\n%s%s%s%s\r\n",
                    (posting == 0) ? "GET" : "HEAD",
                    (isproxy) ? fullurl : path,
                    keepalive ? "Connection: Keep-Alive\r\n" : "",
                    cookie, auth, hdrs);
        } else {
            request = String.format("POST %s HTTP/1.0\r\n%s%s%sContent-length: %d\r\nContent-type: %s\r\n%s\r\n",
                    (isproxy) ? fullurl : path,
                    keepalive ? "Connection: Keep-Alive\r\n" : "",
                    cookie, auth,
                    postlen,
                    hdrs, postdata);
        }
        try {
            SocketChannel sc = null;
            sc = context.getChannel();
            sc.write(ByteBuffer.wrap(request.getBytes(UTF8)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClosed(HttpContext context) {
        requests--;
        good++;
    }

    @Override
    public void onError(HttpContext context) {
        bad++;
        //good --;
    }

    @Override
    public void onWrite(HttpContext context) {
        //
        System.out.print(">");
    }

    @Override
    public void onCompact(HttpContext context) {
        System.out.println(String.format("number=%d,request=%d,good=%d,bad=%d.", number, requests, good, bad));
        if ((number < 0 || number > good + bad + requests) && concurrency > requests) {
            // 如果未达到并发限制数量, 新增加一个请求
            try {
                SocketChannel sc = SocketChannel.open();
                sc.configureBlocking(false);
                sc.setOption(StandardSocketOptions.SO_RCVBUF, 128 * 1024);
                sc.setOption(
                        StandardSocketOptions.SO_SNDBUF, 128 * 1024);
                //sc.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
                sc.setOption(StandardSocketOptions.SO_REUSEADDR, true);
                //sc.socket().setSoTimeout(10 * 1000);
                sc.setOption(StandardSocketOptions.TCP_NODELAY, true);
                // SO_LINGGER参数在java不能使用,
                //sc.setOption(StandardSocketOptions.SO_LINGER, 10 * 1000);
                //socket.setSoTimeout(connectTimeout);
                InetSocketAddress sa = new InetSocketAddress(host, port);
                @SuppressWarnings("unused")
                boolean ret = sc.connect(sa);
                HttpContext ctx = new HttpContext(sc, connectTimeout);
                sc.register(selector,
                        SelectionKey.OP_READ | SelectionKey.OP_WRITE | SelectionKey.OP_CONNECT,
                        ctx);
                requests++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (number <= good + bad) {
            done = false;
        }
    }

    @Override
    public void onTimeout(HttpContext context) {
        // 超时后, 失败请求数+1
        bad++;
        //good --;
    }

    @Override
    public void onRead(HttpContext context) {
        CharBuffer buffer = context.getBuffer();
        buffer.flip();
        int pos = context.getReadpos();
        StringBuffer line = new StringBuffer();
        int start = pos;
        int stop = start;
        //int dupCRLF = 0;
        while (!context.hasHeader && buffer.hasRemaining()) {
            char ch = buffer.get();
            switch (ch) {
                case '\r':
                    // 跳过
                    //dupCRLF ++;
                    break;
                case '\n':
                    //dupCRLF ++;
                    // 处理内容
                    if (start == stop) {
                        // header域结束, 下面是body
                        System.out.println("Body start...");
                        context.hasHeader = true;
                        String cl = context.getHeader("Content-Length");
                        int len = Api.valueOf(int.class, cl);
                        context.setContentLength(len);
                        break;
                    } else {
                        // header域
                        //dupCRLF = 0;
                        context.addHeader(line.toString());
                        line.setLength(0);
                        stop = start = 0;
                    }
                    break;
                default:
                    // 默认追加内容
                    stop++;
                    line.append(ch);
                    break;
            }
            pos++;
        }

        buffer.compact();

        char[] ac = Arrays.copyOfRange(buffer.array(), 0, buffer.position());
        String response = new String(ac);
        System.out.println(response);

        int cl = buffer.position();
        try {
            cl = response.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        if (cl >= context.getContentLength()) {
            // 数据处理完毕
            onClosed(context);
        }
    }

}

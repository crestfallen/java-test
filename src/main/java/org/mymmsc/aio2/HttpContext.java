package org.mymmsc.aio2;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * HTTP协议上下文
 * 
 * @author wangfeng
 * @date 2016年2月15日 上午10:43:32
 */
public class HttpContext extends AioContext {
	/** 状态码 */
	private int status = 900;
	/** 接收到字节数 */
	private int recviced = 0;
	private int length = 0;
	private int readpos = 0;
	
	/** 是否已经完整的获取了HTTP Header域 */
	public boolean hasHeader = false;
	private int headerCount;
    private String[] headers;
    @SuppressWarnings("unused")
    /** HTTP body域, 暂时未用到 */
	private byte[] body;
    
	public HttpContext(SocketChannel channel, int timeout) throws IOException {
		super(channel, timeout);
	}

	public void addHeader(String header) {
		if(headers == null) {
			headers = new String[1];
		} else if (headerCount >= headers.length) {
            headers = Arrays.copyOf(headers, headers.length + 1);
        }
        headers[headerCount++] = header;
    }
	
	public String getHeader(String key) {
        int keyLength = key.length();
        for (int i = 1; i < headerCount; i++) {
            if (headers[i].regionMatches(true, 0, key, 0, keyLength)) {
                return headers[i].substring(keyLength);
            }
        }
        return null;
    }
	
	public int getStatus() {
		return status;
	}

	public int getRecviced() {
		return recviced;
	}

	public int getLength() {
		return length;
	}

	public int getReadpos() {
		return readpos;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setRecviced(int recviced) {
		this.recviced = recviced;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setReadpos(int readpos) {
		this.readpos = readpos;
	}

}

/**
 * 
 */
package org.mymmsc.aio2;

import java.nio.channels.SocketChannel;

/**
 * AIO事件接口
 * 
 * @author wangfeng
 * @date 2016年2月7日 上午1:23:23
 */
public abstract interface AioHandler {
	//public abstract void onCompact(Selector selector);
	//public abstract void onAccepted(SocketChannel sc);
	public abstract void handleConnected(SocketChannel sc);
	public abstract void handleClosed(SocketChannel sc);
	//public abstract void onError(SocketChannel sc);
	public abstract void handleRead(SocketChannel sc);
	//public abstract void onWrite(SocketChannel sc);
	public abstract void handleTimeout(SocketChannel sc);
}

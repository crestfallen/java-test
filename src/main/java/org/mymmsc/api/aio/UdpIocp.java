/**
 *
 */
package org.mymmsc.api.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * UDP协议的完成端口
 *
 * @author wangfeng
 */
public class UdpIocp {
    /**
     * 超时3秒
     */
    private static final int TIMEOUT = 3000; // Wait timeout (milliseconds)
    /**
     * 最大接收
     */
    private static final int ECHOMAX = 255; // Maximum size of echo datagram

    public static void start(int servPort) throws IOException {
        // Create a selector to multiplex client connections.
        Selector selector = Selector.open();
        DatagramChannel channel = DatagramChannel.open();
        // 非阻塞
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(servPort));
        channel.register(selector, SelectionKey.OP_READ, new ClientRecord());

        while (true) { // Run forever, receiving and echoing datagrams
            // Wait for task or until timeout expires
            if (selector.select(TIMEOUT) == 0) {
                System.out.print(".");
                continue;
            }

            // Get iterator on set of keys with I/O to process
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            while (keyIter.hasNext()) {
                SelectionKey key = keyIter.next(); // Key is bit mask

                // Client socket channel has pending data?
                if (key.isReadable())
                    handleRead(key);

                // Client socket channel is available for writing and
                // key is valid (i.e., channel not closed).
                if (key.isValid() && key.isWritable())
                    handleWrite(key);

                keyIter.remove();
            }
        }
    }

    public static void handleRead(SelectionKey key) throws IOException {
        DatagramChannel channel = (DatagramChannel) key.channel();
        ClientRecord clntRec = (ClientRecord) key.attachment();
        clntRec.buffer.clear(); // Prepare buffer for receiving
        clntRec.clientAddress = channel.receive(clntRec.buffer);
        if (clntRec.clientAddress != null) { // Did we receive something?
            // Register write with the selector
            key.interestOps(SelectionKey.OP_WRITE);
        }
    }

    public static void handleWrite(SelectionKey key) throws IOException {
        DatagramChannel channel = (DatagramChannel) key.channel();
        ClientRecord clntRec = (ClientRecord) key.attachment();
        clntRec.buffer.flip(); // Prepare buffer for sending
        int bytesSent = channel.send(clntRec.buffer, clntRec.clientAddress);
        if (bytesSent != 0) { // Buffer completely written?
            // No longer interested in writes
            key.interestOps(SelectionKey.OP_READ);
        }
    }

    public static void main(String[] argv) {
        try {
            start(17080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientRecord {
        public SocketAddress clientAddress;
        public ByteBuffer buffer = ByteBuffer.allocate(ECHOMAX);
    }
}
/*
 * @(#)AioContext.java	6.3.9 09/09/25
 *
 * Copyright 2009 MyMMSC Software Foundation (MSF), Inc. All rights reserved.
 * MSF PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.mymmsc.aio2;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;

/**
 * 异步IO关联上下文
 *
 * @author WangFeng(wangfeng@yeah.net)
 * @version 6.3.9 09/10/02
 * @since mymmsc-api 6.3.9
 */
public abstract class AioContext {
    public int length = 0;
    private SocketChannel m_channel = null;
    private int m_timeout = 0;
    private long m_startTime = 0;
    private CharBuffer m_buffer = null;

    public AioContext(SocketChannel channel, int timeout) throws IOException {
        this.m_channel = channel;
        this.m_timeout = timeout;
        this.m_startTime = System.currentTimeMillis();
        this.m_channel.configureBlocking(false);
        m_buffer = CharBuffer.allocate(128 * 1024);
        m_buffer.clear();
    }

    public void close() {
        try {
            m_buffer.clear();
            m_channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CharBuffer getBuffer() {
        return m_buffer;
    }

    public int add(CharBuffer buf) {
        if (buf != null) {
            m_buffer.put(buf);
        }
        return m_buffer.position();
    }

    public int add(CharBuffer buf, int len) {
        if (buf != null) {
            m_buffer.put(buf.array(), 0, len);
            length += len;
        }
        return m_buffer.position();
    }

    /**
     * AioContext is time out ?
     *
     * @return
     */
    public boolean isTimeout() {
        return isTimeout(m_timeout);
    }

    public boolean isTimeout(long timeout) {
        return (System.currentTimeMillis() - m_startTime) >= timeout;
    }

    /**
     * @return the channel
     */
    public SocketChannel getChannel() {
        return m_channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(SocketChannel channel) {
        this.m_channel = channel;
    }

    /**
     * @return the timeout
     */
    public int getTimeout() {
        return m_timeout;
    }

    /**
     * @param timeout the timeout to set
     */
    public void setTimeout(int timeout) {
        this.m_timeout = timeout;
    }

    /**
     * @return the startTime
     */
    public long getStartTime() {
        return m_startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(long startTime) {
        this.m_startTime = startTime;
    }
}

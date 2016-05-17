/*
 * @(#)CompleteEvent.java	6.3.9 09/09/25
 *
 * Copyright 2009 MyMMSC Software Foundation (MSF), Inc. All rights reserved.
 * MSF PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.mymmsc.aio2.test;

import java.nio.channels.SelectionKey;

public abstract class CompleteEvent extends SelectionKey {
	public final static int IOCP_UNKNOWN = 0x0000;
	public final static int IOCP_ACCEPT = 0x0001;
	public final static int IOCP_CONNECT = 0x0002;
	public final static int IOCP_CLOSED = 0x0004;
	public final static int IOCP_READ = 0x0008;
	public final static int IOCP_TIMEOUT = 0x0010;
	public final static int IOCP_WRITE = 0x0020;
	private int event = IOCP_UNKNOWN;

	public CompleteEvent(SelectionKey key) {
		event = IOCP_UNKNOWN;
		if (key == null) {

		} else if (key.isAcceptable()) {
			event = IOCP_ACCEPT;
		} else if (key.isConnectable()) {
			event = IOCP_CONNECT;
		} else if (key.isReadable()) {
			event = IOCP_READ;
		} else if (key.isWritable()) {
			event = IOCP_WRITE;
		} else if (key.isValid()) {
			//
		} else {
			//
		}
	}

	public int getEvent() {
		return event;
	}
}

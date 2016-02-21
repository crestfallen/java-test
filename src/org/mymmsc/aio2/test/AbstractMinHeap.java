/**
 * @(#)AbstractMinHeap.java	6.3.9 09/11/02
 *
 * Copyright 2000-2010 MyMMSC Software Foundation (MSF), Inc. All rights reserved.
 * MyMMSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.mymmsc.aio2.test;

/**
 * @author WangFeng(wangfeng@yeah.net)
 * @version 6.3.9 09/10/02
 * @since mymmsc-api 6.3.9
 * @param <T>
 */
public abstract class AbstractMinHeap<T> {

	private int m_index = -1;

	public void setIndex(int index) {
		this.m_index = index;
	}

	public int getIndex() {
		return m_index;
	}

}

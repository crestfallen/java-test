/**
 * @(#)InterfaceMinHeap.java 6.3.9 09/11/02
 * <p>
 * Copyright 2000-2010 MyMMSC Software Foundation (MSF), Inc. All rights reserved.
 * MyMMSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.mymmsc.aio2.test;

public interface InterfaceMinHeap<T> {

    public long compareTo(T o);

    public int getIndex();

    public void setIndex(int i);

}

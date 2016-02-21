/**
 * @(#)MinHeap2.java	6.3.9 09/11/02
 *
 * Copyright 2000-2010 MyMMSC Software Foundation (MSF), Inc. All rights reserved.
 * MyMMSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.mymmsc.aio2.test;

import java.lang.reflect.Array;

/**
 * 
 * 
 * @author WangFeng(wangfeng@yeah.net)
 * @version 6.3.9 09/10/02
 * @since mymmsc-api 6.3.9
 * @param <E>
 */
public class MinHeap2<E extends InterfaceMinHeap<E>> {
	private Class<E> m_class;
	private E[] m_elements;
	private int m_size;
	private int m_reclaimPos;

	/**
	 * MinHeap构造函数
	 * 
	 * @param num
	 *            初始化栈大小
	 */
	@SuppressWarnings("unchecked")
	public MinHeap2(int num) {
		m_class = (Class<E>) InterfaceMinHeap.class;
		m_elements = (E[]) Array.newInstance(m_class, num);
		m_size = 0;
		m_reclaimPos = num;
	}

	/**
	 * MinHeap构造函数
	 * 
	 * @remark 默认开128个元素
	 */
	public MinHeap2() {
		this(128);
	}

	@SuppressWarnings("unchecked")
	private void reverse() {
		if (m_size >= m_reclaimPos) {
			m_reclaimPos = m_elements.length * 2;
			E[] tmp = (E[]) Array.newInstance(m_class, m_reclaimPos);
			System.arraycopy(m_elements, 0, tmp, 0, m_size);
			m_elements = tmp;
		}
	}

	public synchronized void reset() {
		m_size = m_elements.length;
		int pos = (m_size - 1) / 2;
		for (int i = pos; i >= 0; i--) {
			shift_down(i);
		}
	}

	/**
	 * is empty?
	 * 
	 * @return
	 */
	public synchronized boolean isEmpty() {
		return m_size == 0;
	}

	/**
	 * When insert element we need shiftUp
	 * 
	 * @param array
	 * @param pos
	 */
	private void shift_up(int pos) {
		E tmp = m_elements[pos];
		int index = (pos - 1) / 2;
		while (index >= 0) {
			if (tmp.compareTo(m_elements[index]) < 0) {
				m_elements[pos] = m_elements[index];
				m_elements[pos].setIndex(pos);
				pos = index;
				if (pos == 0) {
					break;
				}
				index = (pos - 1) / 2;
			} else {
				break;
			}
		}
		m_elements[pos] = tmp;
		m_elements[pos].setIndex(pos);
	}

	private void shift_down(int pos) {

		E tmp = m_elements[pos];
		tmp.setIndex(pos);
		int index = pos * 2 + 1;// use left child
		while (index < m_size) {
			if (index + 1 < m_size
					&& m_elements[index + 1].compareTo(m_elements[index]) < 0) {
				index += 1;// switch to right child
			}
			if (tmp.compareTo(m_elements[index]) > 0) {
				m_elements[pos] = m_elements[index];
				m_elements[pos].setIndex(pos);
				pos = index;
				index = pos * 2 + 1;
			} else {
				break;
			}
		}
		m_elements[pos] = tmp;
		m_elements[pos].setIndex(pos);
	}

	/**
	 * 返回当前元素的数量
	 * 
	 * @return
	 */
	public synchronized int length() {
		return m_size;
	}

	/**
	 * 取出最小的元素
	 * 
	 * @return E
	 */
	public synchronized E top() {
		return m_elements[0];
	}

	/**
	 * @return E
	 */
	public synchronized E removeMin() {
		E ret = m_elements[0];
		m_elements[0] = m_elements[--m_size];
		m_elements[0].setIndex(0);
		shift_down(0);
		return ret;
	}

	/**
	 * @return
	 */
	public synchronized boolean isFull() {
		return m_elements.length == m_size;
	}

	/**
	 * insert to tail
	 * 
	 * @param val
	 */
	public synchronized void add(E val) {
		reverse();
		val.setIndex(m_size);
		m_elements[m_size++] = val;
		shift_up(m_size - 1);
	}

	/**
	 * @param e
	 * @return
	 */
	public synchronized boolean remove(E e) {
		int index = e.getIndex();
		if (index <= 0) {
			return false;
		}
		for (int i = index; i < m_size - 1; i++) {
			m_elements[i] = m_elements[i + 1];
		}
		m_elements[--m_size] = null;
		return true;
	}
}
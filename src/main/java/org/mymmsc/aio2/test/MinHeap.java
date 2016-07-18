package org.mymmsc.aio2.test;

import java.util.ArrayList;

/**
 * Min Heap
 *
 * @author wangfeng
 * @date 2016年2月10日 下午7:19:53
 */
public class MinHeap<E extends InterfaceMinHeap<E>> {
    private ArrayList<E> heap = null;

    public MinHeap() {
        heap = new ArrayList<E>();
    }

    public void insert(E data) {
        heap.add(data);
        bubbleUp(heap.size() - 1);
    }

    public E getMin() {
        E result = null;
        if (heap.size() > 0) {
            result = heap.get(0);
            int lastIndex = heap.size() - 1;
            heap.set(0, heap.get(lastIndex));
            heap.remove(lastIndex);
            sinkDown(0);
        }
        return result;
    }

    // Check max heap for recursive version
    private void sinkDown(int index) {
        int smallest = index;
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;

            if (left < heap.size() && heap.get(index).compareTo(heap.get(left)) > 0) {
                smallest = left;
            }
            if (right < heap.size() && heap.get(smallest).compareTo(heap.get(right)) > 0) {
                smallest = right;
            }
            if (smallest != index) {
                E swap = heap.get(index);
                heap.set(index, heap.get(smallest));
                heap.set(smallest, swap);
                index = smallest;
            } else {
                break;
            }
        }
    }

    // Check max heap for recursive version
    private void bubbleUp(int index) {
        int parent = (int) index / 2;
        while (heap.get(parent).compareTo(heap.get(index)) > 0) {
            E swap = heap.get(parent);
            heap.set(parent, heap.get(index));
            heap.set(index, swap);
            index = parent;
            parent = (int) index / 2;
        }
    }
}
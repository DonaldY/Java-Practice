package cn.donaldy.threadExample;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by DonaldY on 2018/5/25.
 */
public class ConcurrentSortedList {
    
    private class Node {
        int value;
        Node prev;
        Node next;
        ReentrantLock lock = new ReentrantLock();
        
        Node() {}
        
        Node(int value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
        
    }

    private final Node head;
    private final Node tail;
    
    public ConcurrentSortedList() {
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /**
     * 从前往后找，找到插入
     * @param value
     */
    public void insert(int value) {
        Node current = head;
        current.lock.lock();
        Node next = current.next;
        try {
            while (true) {
                next.lock.lock();
                try {
                    if (next == tail || next.value < value) {
                        Node node = new Node(value, current, next);
                        next.prev = node;
                        current.next = node;
                        return;
                    }
                } finally {
                    current.lock.lock();
                }
                current = next;
                next = current.next;
            }
        } finally {
            next.lock.unlock();
        }
    }

    /**
     * 从后向前遍历
     * @return
     */
    public int size() {
        Node currrent = tail;
        int count = 0;
        while (currrent.prev != head) {
            ReentrantLock lock = currrent.lock;
            lock.lock();
            try {
                ++ count;
                currrent =  currrent.prev;
            } finally {
                lock.unlock();
            }
        }
        return count;
    }
}

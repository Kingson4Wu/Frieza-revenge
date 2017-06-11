package com.kxw.cache.lru.impl;

import java.util.Hashtable;

/**
 * {<a href = 'http://gogole.iteye.com/blog/692103'>@link</a>}
 */
public class LRUCache2 {

    private int cacheSize;
    private Hashtable<Object, Entry> nodes;//缓存容器
    private int currentSize;
    private Entry first;//链表头
    private Entry last;//链表尾

    public LRUCache2(int i) {
        currentSize = 0;
        cacheSize = i;
        nodes = new Hashtable<Object, Entry>(i);//缓存容器
    }

    /**
     * 获取缓存中对象,并把它放在最前面
     */
    public Entry get(Object key) {
        Entry node = nodes.get(key);
        if (node != null) {
            moveToHead(node);
            return node;
        } else {
            return null;
        }
    }

    /**
     * 添加 entry到hashtable, 并把entry
     */
    public void put(Object key, Object value) {
        //先查看hashtable是否存在该entry, 如果存在，则只更新其value
        Entry node = nodes.get(key);

        if (node == null) {
            //缓存容器是否已经超过大小.
            if (currentSize >= cacheSize) {
                nodes.remove(last.key);
                removeLast();
            } else {
                currentSize++;
            }
            node = new Entry();
        }
        node.value = value;
        //将最新使用的节点放到链表头，表示最新使用的.
        moveToHead(node);
        nodes.put(key, node);
    }

    /**
     * 将entry删除, 注意：删除操作只有在cache满了才会被执行
     */
    public void remove(Object key) {
        Entry node = nodes.get(key);
        //在链表中删除
        if (node != null) {
            if (node.prev != null) {
                node.prev.next = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            if (last == node) {
                last = node.prev;
            }
            if (first == node) {
                first = node.next;
            }
        }
        //在hashtable中删除
        nodes.remove(key);
    }

    /**
     * 删除链表尾部节点，即使用最后 使用的entry
     */
    private void removeLast() {
        //链表尾不为空,则将链表尾指向null. 删除连表尾（删除最少使用的缓存对象）
        if (last != null) {
            if (last.prev != null) {
                last.prev.next = null;
            } else {
                first = null;
            }
            last = last.prev;
        }
    }

    /**
     * 移动到链表头，表示这个节点是最新使用过的
     */
    private void moveToHead(Entry node) {
        if (node == first) {
            return;
        }
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (last == node) {
            last = node.prev;
        }
        if (first != null) {
            node.next = first;
            first.prev = node;
        }
        first = node;
        node.prev = null;
        if (last == null) {
            last = first;
        }
    }

    /*
     * 清空缓存
     */
    public void clear() {
        first = null;
        last = null;
        currentSize = 0;
    }

}

class Entry {
    Entry prev;//前一节点
    Entry next;//后一节点
    Object value;//值
    Object key;//键
}

/**
 * 作者使用的是双链表 + hashtable 的方式实现的。如果在面试题里考到如何实现LRU，考官一般会要求使用双链表 + hashtable 的方式。
 * 所以，我把原文的部分内容摘抄如下：

 双链表 + hashtable实现原理：
 将Cache的所有位置都用双连表连接起来，当一个位置被命中之后，就将通过调整链表的指向，将该位置调整到链表头的位置，
 新加入的Cache直接加到链表头中。这样，在多次进行Cache操作后，最近被命中的，就会被向链表头方向移动，而没有命中的，而想链表后面移动，
 链表尾则表示最近最少使用的Cache。当需要替换内容时候，链表的最后位置就是最少被命中的位置，我们只需要淘汰链表最后的部分即可。
 */
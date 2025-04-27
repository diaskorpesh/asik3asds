package org.example;

import java.util.Iterator;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Entry<K, V>> {

    private class Node {
        K k; V v; Node l, r;
        Node(K k, V v) { this.k = k; this.v = v; }
    }
    public static final class Entry<K, V> {
        private final K k; private final V v;
        Entry(K k, V v) { this.k = k; this.v = v; }
        public K getKey()   { return k; }
        public V getValue() { return v; }
    }
    private Node root;
    private int  size;

    public int size() { return size; }
    public void put(K k, V v) { root = put(root, k, v); }
    private Node put(Node x, K k, V v) {
        if (x == null) { size++; return new Node(k, v); }
        int c = k.compareTo(x.k);
        if      (c < 0) x.l = put(x.l, k, v);
        else if (c > 0) x.r = put(x.r, k, v);
        else            x.v = v;
        return x;
    }

    public V get(K k) {
        Node x = root;
        while (x != null) {
            int c = k.compareTo(x.k);
            if      (c < 0) x = x.l;
            else if (c > 0) x = x.r;
            else            return x.v;
        }
        return null;
    }

    public void delete(K k) { root = delete(root, k); }

    private Node delete(Node x, K k) {
        if (x == null) return null;
        int c = k.compareTo(x.k);
        if      (c < 0) x.l = delete(x.l, k);
        else if (c > 0) x.r = delete(x.r, k);
        else {
            size--;
            if (x.r == null) return x.l;
            if (x.l == null) return x.r;
            Node t = x;
            x = min(t.r);
            x.r = deleteMin(t.r);
            x.l = t.l;
        }
        return x;
    }

    private Node deleteMin(Node x) { return x.l == null ? x.r : (x.l = deleteMin(x.l)); }

    private Node min(Node x) { return x.l == null ? x : min(x.l); }


    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {

            private Node cur = root;
            private Stack stack;

            private class Stack {
                Node n; Stack prev;
                Stack(Node n, Stack p) { this.n = n; this.prev = p; }
            }
            private void push(Node n) { stack = new Stack(n, stack); }
            private Node pop()        { Node n = stack.n; stack = stack.prev; return n; }

            @Override
            public boolean hasNext() {
                return cur != null || stack != null;
            }

            @Override
            public Entry<K, V> next() {
                while (cur != null) {
                    push(cur);
                    cur = cur.l;
                }
                Node n = pop();
                cur = n.r;
                return new Entry<>(n.k, n.v);
            }
        };
    }
}

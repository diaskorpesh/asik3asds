package org.example;
import java.util.Iterator;

public class MyHashTable<K, V> implements Iterable<MyHashTable.HashNode<K, V>> {
    static final class HashNode<K, V> {
        K key;
        V value;
        HashNode<K, V> next;
        HashNode(K k, V v) { key = k; value = v; }
    }

    private final HashNode<K, V>[] table;
    private final int m = 997;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashTable() { table = (HashNode<K, V>[]) new HashNode[m]; }

    private int hash(K k) {
        int h = k.hashCode();
        return (h < 0 ? -h : h) % m;
    }

    public void put(K k, V v) {
        int i = hash(k);
        for (HashNode<K, V> x = table[i]; x != null; x = x.next)
            if (x.key.equals(k)) { x.value = v; return; }
        HashNode<K, V> n = new HashNode<>(k, v);
        n.next = table[i];
        table[i] = n;
        size++;
    }

    public V get(K k) {
        int i = hash(k);
        for (HashNode<K, V> x = table[i]; x != null; x = x.next)
            if (x.key.equals(k)) return x.value;
        return null;
    }

    public V remove(K k) {
        int i = hash(k);
        HashNode<K, V> p = null, x = table[i];
        while (x != null) {
            if (x.key.equals(k)) {
                if (p == null) table[i] = x.next; else p.next = x.next;
                size--;
                return x.value;
            }
            p = x; x = x.next;
        }
        return null;
    }

    public boolean contains(V v) {
        for (HashNode<K, V> b : table)
            for (HashNode<K, V> x = b; x != null; x = x.next)
                if (x.value.equals(v)) return true;
        return false;
    }

    public K getKey(V v) {
        for (HashNode<K, V> b : table)
            for (HashNode<K, V> x = b; x != null; x = x.next)
                if (x.value.equals(v)) return x.key;
        return null;
    }

    public int size() { return size; }

    public int[] bucketSizes() {
        int[] a = new int[m];
        for (int i = 0; i < m; i++) {
            int c = 0;
            for (HashNode<K, V> x = table[i]; x != null; x = x.next) c++;
            a[i] = c;
        }
        return a;
    }

    public Iterator<HashNode<K, V>> iterator() {
        return new Iterator<>() {
            int b;
            HashNode<K, V> cur = table[0];
            private void advance() {
                while (cur == null && ++b < m) cur = table[b];
            }
            { advance(); }
            public boolean hasNext() { return cur != null; }
            public HashNode<K, V> next() {
                HashNode<K, V> r = cur;
                cur = cur.next;
                if (cur == null) advance();
                return r;
            }
        };
    }
}

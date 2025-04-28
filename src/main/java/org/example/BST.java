package org.example;
import java.util.Iterator;
import java.util.Stack;
public class BinarySearchTree<K extends Comparable<K>, V> implements Iterable<BinarySearchTree.Entry<K, V>> {
    private class Node {
        K key;
        V value;
        Node left, right;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private Node root;
    private int size;
    public void put(K key, V value) {
        root = put(root, key, value);
    }
    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }
    public int size() {
        return size;
    }
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new InOrderIterator();
    }
    private class InOrderIterator implements Iterator<Entry<K, V>> {
        private Stack<Node> stack = new Stack<>();
        private Node current = root;
        InOrderIterator() {
            pushLeft(current);
        }
        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }
        @Override
        public Entry<K, V> next() {
            Node node = stack.pop();
            Entry<K, V> entry = new Entry<>(node.key, node.value);
            pushLeft(node.right);
            return entry;
        }
    }
    public static class Entry<K, V> {
        private K key;
        private V value;
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
    }
}

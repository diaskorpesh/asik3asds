package org.example;
public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> h = new MyHashTable<>();
        for (int i = 0; i < 10000; i++)
            h.put(new MyTestingClass(i), new Student(i, "s" + i));
        int[] b = h.bucketSizes();
        for (int i = 0; i < b.length; i++)
            System.out.println(i + " " + b[i]);

        BST<Integer, String> t = new BST<>();
        for (int i = 0; i < 20; i++) t.put(i, "v" + i);
        for (BST.Entry<Integer, String> e : t)
            System.out.println("key is " + e.getKey() + " and value is " + e.getValue());
    }
}

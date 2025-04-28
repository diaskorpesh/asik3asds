package org.example;
public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();
        for (int i = 0; i < 10000; i++) {
            int randomId = (int) (Math.random() * 1000000);
            table.put(new MyTestingClass(randomId), new Student("Student" + i));
        }
        int[] bucketSizes = table.bucketSizes();
        System.out.println("Hash Table Bucket Sizes:");
        for (int i = 0; i < bucketSizes.length; i++) {
            System.out.println("Bucket " + i + ": " + bucketSizes[i]);
        }
        BST<Integer, String> tree = new BST<>();
        for (int i = 0; i < 20; i++) {
            tree.put(i, "v" + i);
        }
        System.out.println("\nTree size: " + tree.size());
        for (BST.Entry<Integer, String> elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}

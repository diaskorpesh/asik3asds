package org.example;
public class MyTestingClass {
    private final int id;
    public MyTestingClass(int id) { this.id = id; }
    public int hashCode() {
        int h = id;
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
    public boolean equals(Object o) {
        return o instanceof MyTestingClass t && t.id == id;
    }
}

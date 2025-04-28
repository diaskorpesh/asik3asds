package org.example;
public class MyTestingClass {
    private int id;
    public MyTestingClass(int id) {
        this.id = id;
    }
    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + id;
        hash = hash ^ (hash >>> 16);
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyTestingClass other = (MyTestingClass) obj;
        return id == other.id;
    }
}

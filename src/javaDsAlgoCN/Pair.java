package javaDsAlgoCN;

public class Pair<T> {
    T first;
    T second;

    public Pair(T f, T s) {
        this.first = f;
        this.second = s;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}
class a {
    public void m1(){
        System.out.println("a:m1");
    }
}

class b extends a{
    public void m2(){
        System.out.println("b:m1");
    }
}
class Main{
    public static void main(String[] args) {
        a obj = new b();
        obj.m1();
    }
}
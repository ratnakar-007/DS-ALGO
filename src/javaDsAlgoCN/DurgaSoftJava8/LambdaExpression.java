package javaDsAlgoCN.DurgaSoftJava8;

@FunctionalInterface
interface PrintHello {
    void print();
}

public class LambdaExpression {
    public static void main(String[] args) {
        PrintHello ph = ()->{
            System.out.println("Hello");
        };
        ph.print();
    }
}

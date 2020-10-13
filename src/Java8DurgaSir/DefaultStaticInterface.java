package Java8DurgaSir;

interface I1 {
    static String m2() {
        return "Static of I1;";
    }
    default void m1() {
        System.out.println("Default of I1;");
    }
}

public class DefaultStaticInterface implements I1{
    public static void main(String[] args) {
        System.out.println(I1.m2());
        DefaultStaticInterface dsi = new DefaultStaticInterface();
        dsi.methodOfDSI();
    }

    private void methodOfDSI() {
        m1();
    }
}

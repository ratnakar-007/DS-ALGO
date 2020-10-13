package javaDsAlgoCN;

public class SirionLabsInt {
    static int check = 10;
    public final int myInt;
    String name;
    int rNum;
//    public static void main(String[] args) {
//        SirionLabsInt sli = new SirionLabsInt();
//        String x = null;
//        System.out.println(print(x));
//    }
    public SirionLabsInt(){
        System.out.println("Constructor Of SirionLabsInt..");
        myInt = 20;
    }
    public SirionLabsInt(int check, int name, int rNum){
        myInt = 100;
        check = check;
    }
    static{
        System.out.println("Static Block.");
        //check = 10;
    }
    {
        System.out.println("Instance Block." + check);
    }
    public static void  print(){
        String y = "SirionLabsInt.";
        System.out.println("This Parent's Static.");
    }
}

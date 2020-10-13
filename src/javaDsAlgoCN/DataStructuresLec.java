package javaDsAlgoCN;

import java.util.ArrayList;

public class DataStructuresLec {

    public DataStructuresLec(){
        System.out.println("");
    }

    public DataStructuresLec(int a){
        System.out.println(a);
    }

    public static void main(String[] args) {
        ArrayList<Integer> al1 = new ArrayList<>();
        DataStructuresLec dsl = new DataStructuresLec();
        al1.add(23);
        al1.add(45);
        int rem = al1.remove(1);
        System.out.println(rem);
        String str = "Ratnakar";
    }
}

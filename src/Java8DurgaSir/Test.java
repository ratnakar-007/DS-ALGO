package Java8DurgaSir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test
{

    public static void main(String arg[])
    {
        String s1 = "xyz";
        String s2 = s1;
        s1 += "d";
        System.out.println(s1 + " " + s2 + " " + (s1==s2));
        StringBuffer sb1 = new StringBuffer("abc");
        StringBuffer sb2 = sb1;
        sb1.append("d");
        System.out.println(sb1 + " " + sb2 + " " + (sb1==sb2));
    }


}
package javaDsAlgoCN.DurgaSoftJava8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
interface Square {
    List<Integer> square();
}

public class LambdaExpression {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        List<Integer> ansL = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i+10);
        }
        Square s = ()-> list
               .stream()
               .filter(ele -> ele%2 ==0)
               .collect(Collectors.toList());
        ansL = s.square();
        System.out.println(ansL);
    }
}

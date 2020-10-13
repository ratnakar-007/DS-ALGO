package javaDsAlgoCN;
import java.util.Scanner;
import java.util.Stack;

public class StackClass {

    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        /*
        int size = s.nextInt();
        Stack<Integer> input = new Stack<Integer>();
        for(int i = 0; i < size; i++) {
            input.push(s.nextInt());
        }
        Stack<Integer> extra = new Stack<Integer>();
        StackSolution.reverseStack(input, extra);
        while(!input.isEmpty()) {
            System.out.print(input.pop() + " ");
        }*/
        //String input = "(a + (b*c)) * (d * ( f * j)) + (a + (b*c)) * (d * ( f * j)) + (a + (b*c)) * (d * ( f * j)) + (a + (b*c)) * (d * ( f * j())) + (a + (b*c)) * (d * ( f * j)) + (a + (b*c)) * (d * ( f * j)) + (a + (b*c)) * (d * ( (f * j))) + (a + (b*c)) * (d * ( f * j)) + (a + (b*c)) * (d * ( f * j)) + (a + (b*c)) * (d * ( f * j)) + (a + (b*c)) * (d * ( f * j)) + (a + (b*c)) * (d * ( f * j)) + (a + (b*c)) + (d * ( f * j)) + ()";
        //System.out.println(StackSolution.checkRedundantBrackets(input));
        String input = "";
        System.out.println(StackSolution.countBracketReversals(input));
    }
}

class StackSolution {

    public static void reverseStack(Stack<Integer> input, Stack<Integer> extra) {
        int data;
        while (!input.isEmpty()) {
            data = input.pop();
            extra.push(data);
        }
        while (!extra.isEmpty()) {
            input.push(extra.pop());
        }
    }
    public static boolean checkRedundantBrackets(String input) {
        Stack<Character> st = new Stack<>();
        for (int i = 0 ; i < input.length() ; i++) {
            char currChar = input.charAt(i);
            int emptyPrnthssFound = 0;
            if (currChar != ')') {
                st.push(currChar);
            } else {
                while (st.peek() != '(') {
                    emptyPrnthssFound++;
                    st.pop();
                }
                if (emptyPrnthssFound<=1)
                    return false;
                st.pop();
                if (!st.isEmpty())
                    if (st.peek() == '(')
                        return true;
            }
        }
        return false;
    }
    public static int[] stockSpan(int[] price) {
        int[] spanArr = new int[price.length];
        Stack<Integer> indxStack = new Stack<>();
        int maxValue;
        int maxIndex;
        indxStack.push(0);
        spanArr[0] = 1;

        for (int i = 1 ; i < price.length ; i++) {
            if (price[i] > price[indxStack.peek()]) {
                while (!indxStack.isEmpty())
                    if(price[i] > price[indxStack.peek()])
                        spanArr[i] += spanArr[indxStack.pop()];
                    else
                        break;
                indxStack.push(i);
                spanArr[i] += 1;
            } else {
                indxStack.push(i);
                spanArr[i] += 1;
            }
        }
        return spanArr;
    }
    public static int countBracketReversals(String input){
        Stack<Character> opnBraces = new Stack<>();
        Stack<Character> clsBraces = new Stack<>();

        if ((input.length() % 2) != 0) return -1;

        for (int i = 0 ; i<input.length() ; i++) {
            if (input.charAt(i) == '{')
                opnBraces.push('{');
            else
                clsBraces.push('}');
        }
        return opnBraces.size() > clsBraces.size() ?
                (opnBraces.size()-clsBraces.size())/2 :
                (clsBraces.size() - opnBraces.size())/2 ;
    }

}
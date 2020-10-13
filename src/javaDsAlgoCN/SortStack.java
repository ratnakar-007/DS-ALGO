package javaDsAlgoCN;

import java.util.Stack;

public class SortStack
{
    // This function return the sorted stack
    public static Stack<Integer> sortStack(Stack<Integer> input)
    {
        Stack<Integer> tmpStack = new Stack<Integer>();
        while(!input.isEmpty())
        {
            // pop out the first element
            int tmp = input.pop();
            // while temporary stack is not empty and
            // top of stack is greater than temp
            while(!tmpStack.isEmpty() && tmpStack.peek()
                    < tmp)
            {
                // pop from temporary stack and
                // push it to the input stack
                input.push(tmpStack.pop());
            }
            // push temp in tempory of stack
            tmpStack.push(tmp);
        }
        return tmpStack;
    }

    // Driver Code
    public static void main(String args[])
    {
        //Input-1
        int[] input1 = {34, 37, 6, 8, 99, 1, 40, 10, 27, 21};
        Stack<Integer> stack1 = new Stack<Integer>();

        //Input-2
        int[] input2 = {90, 91, 92, 93,94, 95, 96, 97, 98, 99};
        Stack<Integer> stack2 = new Stack<Integer>();

        for (int i : input1)
            stack1.push(i);

        for (int i : input2)
            stack2.push(i);

        // This is the temporary stack
        stack1 = sortStack(stack1);
        System.out.println("Sorted STACK-1:");

        while (!stack1.empty())
        {
            System.out.print(stack1.pop()+" ");
        }
        System.out.println("");
        stack2 = sortStack(stack2);
        System.out.println("Sorted STACK-2:");

        while (!stack2.empty())
        {
            System.out.print(stack2.pop()+" ");
        }
    }
}
package javaDsAlgoCN;

import java.util.Scanner;

class Node<T> {
    T data;
    Node next;

    public Node(T data) {
        this.data = data;
    }
}

public class JustPracticingException {

    /*public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        Stack<Integer> st = new Stack<Integer>();

        int choice = s.nextInt();
        int input;

        while (choice !=-1) {
            if(choice == 1) {
                input = s.nextInt();
                st.push(input);
            }
            else if(choice == 2) {
                try {
                    System.out.println(st.pop());
                } catch (StackEmptyException e) {
                    System.out.println(-1);
                }
            }
            else if(choice == 3) {
                try {
                    System.out.println(st.top());
                } catch (StackEmptyException e) {
                    System.out.println(-1);
                }
            }
            else if(choice == 4) {
                System.out.println(st.size());
            }
            else if(choice == 5) {
                System.out.println(st.isEmpty());
            }
            choice = s.nextInt();
        }
    }*/
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        String input = s.nextLine();
        System.out.println(BalancedParenthesis.checkBalanced(input));
    }
}

class Stack<T> {

    private Node<T> head;
    private int length = 0;

    public Stack() {
        head = null;
    }

    public int size() {
        return length;
    }

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        length++;
    }

    public boolean isEmpty() {
        return (length == 0);
    }

    public T pop() throws StackEmptyException {
        if (length == 0) {
            StackEmptyException see = new StackEmptyException();
            throw see;
        } else {
            T data = head.data;
            head = head.next;
            length--;
            return data;
        }
    }

    public T top() throws StackEmptyException {
        if (length == 0) {
            StackEmptyException see = new StackEmptyException();
            throw see;
        } else {
            return head.data;
        }
    }
}

class BalancedParenthesis {
    public static boolean checkBalanced(String exp) {
        Stack<Character> stack = new Stack<>();
        char currCh;
        char poppedCh;
        for (int i = 0 ; i<exp.length() ; i++) {
            currCh = exp.charAt(i);
            if (currCh == '{' || currCh == '[' || currCh == '('){
                stack.push(currCh);
            } else if (currCh == '}' || currCh == ']' || currCh == ')') {
                try {
                    poppedCh = stack.pop();
                } catch (StackEmptyException e) {
                    return false;
                }

                if (!isMatchingPair(poppedCh, currCh))
                    return false;
            }
        }
        return true;
    }

    public static boolean isMatchingPair(char ch1, char ch2) {
        if (ch1 == '(' && ch2 == ')')
            return true;
        else if (ch1 == '{' && ch2 == '}')
            return true;
        else if (ch1 == '[' && ch2 == ']')
            return true;
        else
            return false;
    }
}

class Queue<T> {
    private Node<T> head;
    private Node<T> tail;
    private int length = 0;

    public Queue() {
        head = null;
        tail = null;
    }

    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }
        length++;
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return (length == 0);
    }

    public T dequeue() {
        if (length == 0){
            return null;
        } else {
            T data = head.data;
            head = head.next;
            length--;
            return data;
        }
    }

    public T front() {
        if (length == 0) {
            return null;
        } else {
            return head.data;
        }
    }
}

class QueueEmptyException extends Exception {
    private static final long versionUID = 1L;
}


class StackEmptyException extends Exception {
    private static final long versionUID = 1L;
}
package expression_calculator;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Expression_Calculator {

    public static double Calculate_Plus_And_Minus(Stack<String> stack) {
        Stack<String> temp = new Stack();
        Stack<String> final_stack = new Stack();
        ArrayList<String> list = new ArrayList();
        char op;
        double counter = 0;
        double num1 = 0;
        double num2 = 0;
        double result = 0;

        while (!stack.empty()) {
            temp.push(stack.pop());
        }
        while (!temp.empty()) {
            list.add(temp.pop());
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("+") || list.get(i).equals("-")) {
                op = list.get(i).charAt(0);
                num1 = Double.parseDouble((String) final_stack.peek());
                num2 = Double.parseDouble((String) list.get(i + 1));

                if (op == '+') {
                    result = num1 + num2;
                } else if (op == '-') {
                    result = num1 - num2;
                }
                final_stack.pop();
                i++;
                final_stack.push("" + result);
            } else {
                final_stack.push(list.get(i));
            }
        }
        return result;
    }

    public static double Calculate_Multiply_And_Division(ArrayList<String> list) {
        Stack<String> stack = new Stack();
        char op;
        double num1 = 0;
        double num2 = 0;
        double result = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("*") || list.get(i).equals("/")) {
                op = list.get(i).charAt(0);
                num1 = Double.parseDouble((String) stack.peek());
                num2 = Double.parseDouble((String) list.get(i + 1));
                result = 0;
                if (op == '*') {
                    result = num1 * num2;
                } else if (op == '/') {
                    result = num1 / num2;
                }
                stack.pop();
                i++;
                stack.push("" + result);
            } else {
                stack.push(list.get(i));
            }

        }

        if (stack.size() > 1) {
            result = Calculate_Plus_And_Minus(stack);
        }

        return result;
    }

    public static void Separate_Parentheses(Stack<String> stack) {
        ArrayList<String> list = new ArrayList();
        Stack<String> temp1 = new Stack();
        double final_result = 0;

        while (!stack.empty()) {
            temp1.push(stack.pop());
            if (temp1.peek().equals("(")) {
                while (!temp1.empty()) {
                    list.add(temp1.pop());
                    if (list.get(list.size() - 1).equals(")")) {
                        if (list.get(list.size() - 1).equals(")")) {
                            list.remove(list.size() - 1);
                        }
                        if (list.get(0).equals("(")) {
                            list.remove(0);
                        }
                        final_result = Calculate_Multiply_And_Division(list);
                        temp1.push("" + final_result);
                        list.clear();
                        break;
                    }
                }
            }
        }
        if (temp1.size() > 1) {
            while (!temp1.empty()) {
                list.add(temp1.pop());
            }
            final_result = Calculate_Multiply_And_Division(list);
            System.out.println();
            System.out.println("Finally  = " + final_result);
        } else {
            System.out.println("Finally  = " + final_result);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter The Expression To Calculate! ");
        String Expression = in.next();
        Stack<String> stack = new Stack();
        String num = "";

        for (int i = 0; i < Expression.length(); i++) {
            if (Expression.charAt(i) == '(' || Expression.charAt(i) == ')'
                    || Expression.charAt(i) == '+' || Expression.charAt(i) == '-'
                    || Expression.charAt(i) == '*' || Expression.charAt(i) == '/') {
                if (num.length() > 0) {
                    stack.push(num);
                }
                stack.push("" + Expression.charAt(i));
                num = "";
                continue;
            }
            num += Expression.charAt(i);
        }
        if (num.length() > 0) {
            stack.push(num);
        }
        Separate_Parentheses(stack);

    }
}

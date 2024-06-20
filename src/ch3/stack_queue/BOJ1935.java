package ch3.stack_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 후위 표기식2(S3)
public class BOJ1935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] chars = br.readLine().toCharArray();
        double[] charMap = new double[n];
        for (int i = 0; i < n; i++) {
            charMap[i] = Integer.parseInt(br.readLine());
        }

        Stack<Double> stack = new Stack<>();
        for (char c : chars) {
            if (c >= 65 && c <= 90) {
                stack.push(charMap[c - 65]);
            } else {
                double n2 = stack.pop();
                double n1 = stack.pop();
                stack.push(operate(c, n1, n2));
            }
        }

        System.out.printf("%.2f", stack.pop());
    }

    static double operate(char operator, double num1, double num2) {
        double result = 0;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = (double) num1 / num2;
                break;
        }
        return result;
    }
}

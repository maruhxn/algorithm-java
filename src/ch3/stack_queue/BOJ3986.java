package ch3.stack_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 좋은 단어(S4)
public class BOJ3986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int result = 0;
        while (n-- > 0) {
            char[] chars = br.readLine().toCharArray();
            Stack<Character> stack = new Stack<>();

            for (char aChar : chars) {
                if (!stack.isEmpty() && stack.peek().equals(aChar)) {
                    stack.pop();
                } else {
                    stack.push(aChar);
                }
            }

            if(stack.isEmpty()) result++;

        }

        System.out.println(result);
    }
}

package algospot.ch19;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class BRACKETS2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        while (c-- > 0) {
            String str = sc.next();
            Stack<Character> stack = new Stack<>();
            boolean flag = true;

            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(') {
                    stack.push(')');
                } else if (str.charAt(i) == '{') {
                    stack.push('}');
                } else if (str.charAt(i) == '[') {
                    stack.push(']');
                } else {
                    if (stack.isEmpty() || stack.pop() != str.charAt(i)) {
                        flag = false;
                        break;
                    }
                }
            }

            if (!flag || !stack.isEmpty()) System.out.println("NO");
            else System.out.println("YES");
        }
    }
}

package ch3.stack_queue;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

// 탑(G5)
public class BOJ2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Point> stack = new Stack<>();

        stack.push(new Point(Integer.parseInt(st.nextToken()), 1));
        bw.append("0 ");

        for (int i = 2; i <= n; i++) {
            Point point = new Point(Integer.parseInt(st.nextToken()), i);
            while (true) {
                if (stack.isEmpty()) {
                    bw.append("0 ");
                    stack.push(point);
                    break;
                }

                if (stack.peek().value < point.value) {
                    stack.pop();
                } else {
                    bw.append(stack.peek().index + " ");
                    stack.push(point);
                    break;
                }

            }
        }

        bw.flush();
        bw.close();
    }

    static class Point {
        int value;
        int index;

        public Point(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
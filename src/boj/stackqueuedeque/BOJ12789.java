package boj.stackqueuedeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ12789 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int next = 1;
        Stack<Integer> side = new Stack<>();

        for (int i = 0; i < N; i++) {
            int person = Integer.parseInt(st.nextToken());

            while (!side.isEmpty() && side.peek().equals(next)) {
                side.pop();
                next++;
            }

            if (next == person) {
                next++;
            } else {
                side.push(person);
            }

        }

        while (!side.isEmpty()) {
            if (!side.pop().equals(next)) {
                System.out.println("Sad");
                return;
            } else {
                next++;
            }
        }

        System.out.println("Nice");
    }
}
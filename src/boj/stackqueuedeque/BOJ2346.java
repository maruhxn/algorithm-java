package boj.stackqueuedeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ2346 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        Deque<Pair> q = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            q.add(new Pair(i + 1, Integer.parseInt(st.nextToken())));
        }

        Pair next = q.poll();
        sb.append(next.index + " ");

        while (!q.isEmpty()) {
            // 양수인 경우
            if (next.value > 0) {
                // 순서 뒤로 돌리기
                for (int i = 0; i < next.value - 1; i++) {
                    q.add(q.poll());
                }

                next = q.poll();
                sb.append(next.index + " ");
            }
            // 음수인 경우
            else {
                for (int i = 0; i < -next.value - 1; i++) {
                    q.addFirst(q.pollLast());
                }

                next = q.pollLast();
                sb.append(next.index + " ");
            }
        }

        System.out.println(sb);

    }

    static class Pair {
        int index;
        int value;

        public Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}
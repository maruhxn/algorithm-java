package boj.stackqueuedeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ28279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            switch (op) {
                case 1 -> deque.addFirst(Integer.parseInt(st.nextToken()));
                case 2 -> deque.addLast(Integer.parseInt(st.nextToken()));
                case 3 -> {
                    if (deque.isEmpty()) sb.append(-1).append("\n");
                    else sb.append(deque.removeFirst()).append("\n");
                }
                case 4 -> {
                    if (deque.isEmpty()) sb.append(-1).append("\n");
                    else sb.append(deque.removeLast()).append("\n");
                }
                case 5 -> sb.append(deque.size()).append("\n");
                case 6 -> {
                    if (deque.isEmpty()) sb.append(1).append("\n");
                    else sb.append(0).append("\n");
                }
                case 7 -> {
                    if (deque.isEmpty()) sb.append(-1).append("\n");
                    else sb.append(deque.getFirst()).append("\n");
                }
                case 8 -> {
                    if (deque.isEmpty()) sb.append(-1).append("\n");
                    else sb.append(deque.getLast()).append("\n");
                }
            }
        }

        System.out.println(sb);
        br.close();
    }
}

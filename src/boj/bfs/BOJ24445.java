package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ24445 {

    static ArrayList<Integer>[] graph;
    static int[] answer;
    static int depth;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        answer = new int[N];
        queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a - 1].add(b - 1);
            graph[b - 1].add(a - 1);
        }

        for (int i = 0; i < N; i++) {
            graph[i].sort((o1, o2) -> o2 - o1);
        }

        bfs(R - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append("\n");
        }

        System.out.println(sb);
    }

    static void bfs(int start) {
        answer[start] = ++depth;
        queue.add(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < graph[cur].size(); i++) {
                int next = graph[cur].get(i);
                if (answer[next] == 0) {
                    answer[next] = ++depth;
                    queue.add(next);
                }
            }
        }
    }
}

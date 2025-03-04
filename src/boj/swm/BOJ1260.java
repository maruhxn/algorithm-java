package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260 {

    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 1; i <= N; i++) graph[i].sort(((o1, o2) -> o1 - o2));

        visited = new boolean[N + 1];
        dfs(V);
        sb.append("\n");
        visited = new boolean[N + 1];
        bfs(V);

        System.out.println(sb);
    }

    static void dfs(int v) {
        visited[v] = true;
        sb.append(v).append(" ");

        for (int i = 0; i < graph[v].size(); i++) {
            int next = graph[v].get(i);
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            sb.append(curr).append(" ");
            for (int i = 0; i < graph[curr].size(); i++) {
                int next = graph[curr].get(i);
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
    }
}

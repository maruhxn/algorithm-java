package boj.dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260 {

    static StringBuilder sb;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static boolean[] discovered;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        discovered = new boolean[N + 1];

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

        for (int i = 1; i <= N; i++) {
            graph[i].sort((o1, o2) -> o1 - o2);
        }

        sb = new StringBuilder();

        dfs(V);
        sb.append("\n");
        bfs(V);

        System.out.println(sb);
    }

    static void dfs(int node) {
        visited[node] = true;
        sb.append(node).append(" ");
        for (int i = 0; i < graph[node].size(); i++) {
            int next = graph[node].get(i);
            if (!visited[next]) {
                dfs(next);

            }
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        discovered[start] = true;
        queue.add(start);
        sb.append(start).append(" ");

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int i = 0; i < graph[node].size(); i++) {
                int next = graph[node].get(i);
                if (!discovered[next]) {
                    discovered[next] = true;
                    queue.add(next);
                    sb.append(next).append(" ");
                }
            }
        }

    }
}

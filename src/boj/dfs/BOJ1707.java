package boj.dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1707 {

    static ArrayList<Integer>[] graph;
    static int[] visited;
    static boolean flag;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            graph = new ArrayList[V];
            visited = new int[V];
            flag = false;

            for (int i = 0; i < V; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                graph[u].add(v);
                graph[v].add(u);
            }

            for (int i = 0; i < V; i++) {
                if (visited[i] == 0) {
                    visited[i] = 1;
                    dfs(i);
                }
            }

            sb.append(flag ? "NO" : "YES").append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int node) {
        for (int i = 0; i < graph[node].size(); i++) {
            int next = graph[node].get(i);
            if (visited[next] == 0) {
                visited[next] = -visited[node];
                dfs(next);
            } else if (visited[next] == visited[node]) {
                flag = true;
                return;
            }
        }
    }
}
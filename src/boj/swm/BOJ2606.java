package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2606 {

    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int ret = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        graph = new ArrayList[V + 1];
        visited = new boolean[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        bfs(1);

        System.out.println(ret);
    }

    static void bfs(int node) {
        visited[node] = true;

        for (int i = 0; i < graph[node].size(); i++) {
            int next = graph[node].get(i);
            if (!visited[next]) {
                bfs(next);
                ++ret;
            }
        }
    }
}

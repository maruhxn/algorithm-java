package boj.floyd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1956 {

    static int[][] graph;
    static int INF = 987654321;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = INF;
            }
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            graph[u][v] = Math.min(graph[u][v], w);
        }

        for (int k = 0; k < V; k++) { // 경유점
            for (int i = 0; i < V; i++) { // 시점
                if (graph[i][k] == INF) continue;
                for (int j = 0; j < V; j++) { // 종점
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int ret = INF;
        for (int i = 0; i < V; i++) {
            ret = Math.min(ret, graph[i][i]);
        }

        System.out.println(ret == INF ? -1 : ret);
    }
}

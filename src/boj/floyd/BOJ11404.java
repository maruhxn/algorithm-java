package boj.floyd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11404 {

    static int INF = 987654321;
    static int N, M;
    static long[][] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new long[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) graph[i][j] = 0;
                else graph[i][j] = INF;
            }
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            graph[u][v] = Math.min(graph[u][v], w);
        }

        for (int k = 0; k < N; k++) { // 경유점
            for (int i = 0; i < N; i++) { // 시점
                for (int j = 0; j < N; j++) { // 종점
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(graph[i][j] == INF ? 0 : graph[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}

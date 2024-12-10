package algospot.ch30;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PROMISES {

    static int V, M, N;
    static final int INF = 987654321;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) { // 최대 50번
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken()); // 도시 수
            M = Integer.parseInt(st.nextToken()); // 고속도로 수
            N = Integer.parseInt(st.nextToken()); // 앞으로 건설될 고속도로

            graph = new int[V][V];

            for (int i = 0; i < V; i++) {
                Arrays.fill(graph[i], INF);
                graph[i][i] = 0;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                graph[a][b] = graph[b][a] = Math.min(graph[a][b], c);
            }

            floyd();

            int ret = 0;

            for (int k = 0; k < N; k++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                if (!update(a, b, c)) ret++;
            }

            bw.append(ret + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    // i - j 최단 경로가 (a, b)를 이용하려면
    // i - a - b - j 또는 i - b - a - j 형태가 되어야 함
    // 이 중 최소 거리로 갱신하자!
    private static boolean update(int a, int b, int c) {
        if (graph[a][b] <= c) return false; // 추가되는 간선이 기존 최소 거리보다 크거나 같다면 갱신 불가
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = Math.min(graph[i][j], graph[i][a] + c + graph[b][j]);
                graph[i][j] = Math.min(graph[i][j], graph[i][b] + c + graph[a][j]);
            }
        }

        return true;
    }

    private static void floyd() {
        for (int k = 0; k < V; k++) { // 경유점
            for (int i = 0; i < V; i++) { // 시점
                if (graph[i][k] == INF) continue;
                for (int j = 0; j < V; j++) { // 종점
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }
}

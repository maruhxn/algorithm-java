package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11657 {

    static int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] graph = new ArrayList[N + 1];
        long[] upper = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            upper[i] = INF;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, c));
        }

        upper[1] = 0;
        boolean updated = false;
        for (int iter = 0; iter < N; iter++) {
            updated = false;
            for (int curr = 1; curr <= N; curr++) {
                if(upper[curr] == INF) continue; // 지금 조사하고 있는 점이 1번 정점에서 도달할 수 없는 경우 조사 중단
                for (Edge next : graph[curr]) {
                    if (upper[next.v] > upper[curr] + next.weight) {
                        upper[next.v] = upper[curr] + next.weight;
                        updated = true;
                    }
                }
            }
            if(!updated) break;
        }

        if(updated) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(upper[i] == INF ? -1 : upper[i]).append("\n");
        }

        System.out.println(sb);
    }

    static class Edge {
        int v;
        int weight;

        public Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }
}

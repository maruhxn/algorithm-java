package ch8.bellman_ford_moore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 웜홀(G3)

/**
 * 양방향 고려 안하면 틀림..
 * (정점의 개수 - 1)번 동안 최단거리 초기화 작업을 할 때, 더 이상 최단거리 초기화가 일어나지 않으면 반복문 종료
 * + (정점의 개수 - 1)먼까지 계속 업데이트가 발생했을 경우에만 음수 사이클 생기는지 확인
 * => 위 과정을 통해 시간 복잡도를 줄여야 시간 초과 안 뜸...
 */
public class BOJ1865 {
    static int N, M, W;
    static ArrayList<Edge> edges;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            edges = new ArrayList<>();
            dist = new int[N + 1];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges.add(new Edge(s, e, w));
                edges.add(new Edge(e, s, w));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges.add(new Edge(s, e, -w));
            }

            boolean hasMCycle = false;
            for (int i = 1; i <= N; i++) {
                if (bellman_ford(i)) {
                    hasMCycle = true;
                    break;
                }
            }

            System.out.println(hasMCycle ? "YES" : "NO");
        }
    }

    static boolean bellman_ford(int start) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        boolean update = false;

        for (int i = 0; i < N - 1; i++) {
            update = false;

            for (int j = 0; j < 2 * M + W; j++) {
                Edge nowEdge = edges.get(j);
                int s = nowEdge.s;
                int e = nowEdge.e;
                int w = nowEdge.w;
                if (dist[s] != Integer.MAX_VALUE && dist[e] > dist[s] + w) {
                    dist[e] = dist[s] + w;
                    update = true;
                }
            }

            if (!update) {
                break;
            }
        }


        if (update) {
            for (int i = 0; i < 2 * M + W; i++) {
                Edge nowEdge = edges.get(i);
                int s = nowEdge.s;
                int e = nowEdge.e;
                int w = nowEdge.w;
                if (dist[s] != Integer.MAX_VALUE && dist[e] > dist[s] + w) {
                    return true;
                }
            }
        }

        return false;
    }

    static class Edge {
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
}

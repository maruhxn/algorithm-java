package ch8.bellman_ford_moore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 타임머신으로 빨리 가기((G4)
public class p059 {
    static ArrayList<Edge> edges;
    static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();
        dist = new long[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(s, e, w));
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                Edge nowEdge = edges.get(j);
                int s = nowEdge.start;
                int e = nowEdge.end;
                int w = nowEdge.weight;
                if (dist[s] != Integer.MAX_VALUE && dist[e] > dist[s] + w) {
                    dist[e] = dist[s] + w;
                }
            }
        }

        boolean isMCylce = false;

        for (int i = 0; i < m; i++) {
            Edge nowEdge = edges.get(i);
            int s = nowEdge.start;
            int e = nowEdge.end;
            int w = nowEdge.weight;
            if (dist[s] != Integer.MAX_VALUE && dist[e] > dist[s] + w) {
                isMCylce = true;
            }
        }

        if (isMCylce) {
            System.out.println(-1);
        } else {
            for (int i = 2; i <= n; i++) {
                System.out.println(dist[i] == Integer.MAX_VALUE ? -1 : dist[i]);
            }
        }
    }

    static class Edge {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}

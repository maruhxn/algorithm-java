package boj.swm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1504 {

    static int N, E, INF = 87654321;
    static ArrayList<Edge>[] graph;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        dist = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 1 -> v1 -> v2 -> N
        // 1 -> v2 -> v1 -> N
        dijkstra(1);
        int a1 = dist[v1]; // 1 -> v1
        int a2 = dist[v2]; // 1 -> v2

        dijkstra(v1);
        int b = dist[v2]; // v1 -> v2 (= v2 -> v1)
        int c2 = dist[N]; // v1 -> N

        dijkstra(v2);
        int c1 = dist[N]; // v2 -> N

        int l1 = a1 + b + c1;
        int l2 = a2 + b + c2;
        int ret = Math.min(l1, l2);

        System.out.println(ret >= INF ? -1 : ret);
    }

    private static void dijkstra(int src) {
        Arrays.fill(dist, INF);
        PriorityQueue<PqForm> pq = new PriorityQueue<>((p1, p2) -> p1.dist - p2.dist);
        pq.add(new PqForm(src, 0));
        dist[src] = 0;

        while (!pq.isEmpty()) {
            PqForm curr = pq.poll();
            int here = curr.v;
            int currDist = curr.dist;

            if (dist[here] < currDist) continue;

            for (Edge next : graph[here]) {
                int nextDist = currDist + next.weight;
                if (dist[next.v] > nextDist) {
                    dist[next.v] = nextDist;
                    pq.add(new PqForm(next.v, nextDist));
                }
            }
        }
    }

    static class PqForm {
        int v;
        int dist;

        public PqForm(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }
    }

    static class Edge {
        int v;
        int weight;

        Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }
}

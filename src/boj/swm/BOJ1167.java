package boj.swm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1167 {

    static ArrayList<Edge>[] graph;
    static int[] dist;
    static int INF = 987654321;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());

        graph = new ArrayList[V + 1];
        dist = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());

            int v;
            while ((v = Integer.parseInt(st.nextToken())) != -1) {
                int w = Integer.parseInt(st.nextToken());
                graph[u].add(new Edge(v, w));
                graph[v].add(new Edge(u, w));
            }
        }

        dijkstra(1);
        int max = -1;
        int maxIdx = 0;
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] != INF && dist[i] > max) {
                max = dist[i];
                maxIdx = i;
            }
        }

        dijkstra(maxIdx);
        max = -1;
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] != INF && dist[i] > max) {
                max = dist[i];
            }
        }
        System.out.println(max);

    }

    static void dijkstra(int src) {
        Arrays.fill(dist, INF);

        PriorityQueue<PqForm> pq = new PriorityQueue<>((p1, p2) -> p1.dist - p2.dist);
        dist[src] = 0;
        pq.add(new PqForm(src, 0));

        while (!pq.isEmpty()) {
            PqForm curr = pq.poll();
            int here = curr.v;
            int currDist = curr.dist;

            if (dist[here] < currDist) continue;

            for (Edge next : graph[here]) {
                int nextDist = currDist + next.w;
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
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}

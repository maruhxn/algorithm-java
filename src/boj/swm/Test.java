package boj.swm;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Test {

    static final int INF = 987654321;
    static ArrayList<Edge>[] graph;
    static int[] dist, upper;
    static int[][] fDist;
    static int V;

    public static void main(String[] args) {
        graph = new ArrayList[V];
        dist = new int[V];
        upper = new int[V];
        fDist = new int[V][V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
            upper[i] = INF;
        }


        dijkstra(0);
        hasMinusCycle(0);
        floyd();
    }

    private static void floyd() {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) fDist[i][j] = 0;
                else fDist[i][j] = INF;
            }
        }

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                if (fDist[i][k] == INF) continue;
                for (int j = 0; j < V; j++) {
                    fDist[i][j] = Math.min(fDist[i][j], fDist[i][k] + fDist[k][j]);
                }
            }
        }
    }

    private static boolean hasMinusCycle(int src) {
        upper[src] = 0;

        for (int iter = 0; iter < V - 1; iter++) {
            for (int curr = 0; curr < V; curr++) {
                for (Edge next : graph[curr]) {
                    if (upper[next.v] > upper[curr] + next.w) {
                        upper[next.v] = upper[curr] + next.w;
                    }
                }
            }
        }

        for (int curr = 0; curr < V; curr++) {
            for (Edge next : graph[curr]) {
                if (upper[next.v] > upper[curr] + next.w) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void dijkstra(int src) {
        PriorityQueue<PqForm> pq = new PriorityQueue<>((p1, p2) -> p1.w - p2.w);
        dist[src] = 0;
        pq.add(new PqForm(src, 0));

        while (!pq.isEmpty()) {
            PqForm curr = pq.poll();
            int here = curr.x;
            int currDist = curr.w;

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
        int x;
        int w;

        public PqForm(int x, int w) {
            this.x = x;
            this.w = w;
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

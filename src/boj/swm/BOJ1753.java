package boj.swm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1753 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int INF = 987654321;
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] graph = new ArrayList[V + 1];
        int[] dist = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }

        int K = Integer.parseInt(br.readLine()); // 시작 정점

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Edge(v, w));
        }

        PriorityQueue<PqForm> pq = new PriorityQueue<>((p1, p2) -> p1.dist - p2.dist);
        pq.add(new PqForm(K, 0));
        dist[K] = 0;

        while (!pq.isEmpty()) {
            PqForm curr = pq.poll();
            int here = curr.x;
            int cost = curr.dist;

            if (dist[here] < cost) continue;

            for (Edge next : graph[here]) {
                int nextDist = cost + next.weight;
                if (dist[next.v] > nextDist) {
                    dist[next.v] = nextDist;
                    pq.add(new PqForm(next.v, nextDist));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(dist[i] == INF ? "INF" : dist[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static class Edge {
        int v;
        int weight;

        Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }

    private static class PqForm {
        int x;
        int dist;

        public PqForm(int x, int dist) {
            this.x = x;
            this.dist = dist;
        }
    }
}

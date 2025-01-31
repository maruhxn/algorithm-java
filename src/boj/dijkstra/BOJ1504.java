package boj.dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1504 {

    static int INF = 87654321;
    static int V, E;
    static ArrayList<Node>[] graph;
    static int[] dist;
    static PriorityQueue<PqForm> pq = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V];

        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken()) - 1;
        int v2 = Integer.parseInt(st.nextToken()) - 1;

        // 1 -> v1 -> v2 -> N
        // 1 -> v2 -> v1 -> N
        // 1->v1, 1-> v2, v1->v2, v1->N, v2->N (5개)

        int a = dijkstra(0, v1);
        int b = dijkstra(0, v2);
        int c = dijkstra(v1, v2);
        int d = dijkstra(v1, V - 1);
        int e = dijkstra(v2, V - 1);

        int ret = Math.min(a + c + e, b + c + d);
        System.out.println(ret >= INF ? -1 : ret);
    }

    private static int dijkstra(int st, int ed) {
        dist = new int[V];
        Arrays.fill(dist, INF);

        dist[st] = 0;
        pq.add(new PqForm(st, 0));

        while (!pq.isEmpty()) {
            PqForm p = pq.poll();
            int curr = p.index;
            int cost = p.dist;

            if (dist[curr] < cost) continue;

            for (int i = 0; i < graph[curr].size(); i++) {
                Node next = graph[curr].get(i);
                int nextDist = cost + next.weight;
                if (dist[next.node] <= nextDist) continue;
                dist[next.node] = nextDist;
                pq.add(new PqForm(next.node, nextDist));
            }
        }

        return dist[ed];
    }

    static class Node {
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static class PqForm {
        int index, dist;

        public PqForm(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }
    }
}

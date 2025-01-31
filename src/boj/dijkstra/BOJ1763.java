package boj.dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1763 {

    static ArrayList<Node>[] graph;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V];
        dist = new int[V];

        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        int start = Integer.parseInt(br.readLine()) - 1;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
        }


        PriorityQueue<PqForm> pq = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        dist[start] = 0;
        pq.add(new PqForm(start, 0));

        while (!pq.isEmpty()) {
            PqForm polled = pq.poll();
            int curr = polled.index;
            int cost = polled.dist; // 현재까지의 최단 거리

            if (dist[curr] < cost) continue;

            for (int i = 0; i < graph[curr].size(); i++) {
                Node next = graph[curr].get(i);
                int nextDist = cost + next.weight;
                if (dist[next.node] <= nextDist) continue;
                dist[next.node] = nextDist;
                pq.add(new PqForm(next.node, nextDist));
            }
        }

        for (int i = 0; i < V; i++) {
            sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
        }

        System.out.println(sb);
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

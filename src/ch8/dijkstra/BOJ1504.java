package ch8.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 특정한 최단 경로(G4)
public class BOJ1504 {
    static int N, E, v1, v2;

    static ArrayList<Node>[] graph;
    static int[] dist;
    static boolean[] visited;
    static PriorityQueue<Node> q;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int[] result1 = dijkstra(1);
        int[] result2 = dijkstra(v1);
        int[] result3 = dijkstra(v2);
        int v1_first_result = isConnected(result1[1], result2[2], result3[3])
                ? result1[1] + result2[2] + result3[3]
                : Integer.MAX_VALUE; // 1 -> v1 -> v2 -> N
        int v2_first_result = isConnected(result1[2], result3[1], result2[3])
                ? result1[2] + result3[1] + result2[3]
                : Integer.MAX_VALUE; // 1 -> v2 -> v1 -> N
        if (v1_first_result == Integer.MAX_VALUE && v2_first_result == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(v1_first_result, v2_first_result));
        }
    }

    private static boolean isConnected(int a, int b, int c) {
        return a != Integer.MAX_VALUE
                && b != Integer.MAX_VALUE
                && c != Integer.MAX_VALUE;
    }

    private static int[] dijkstra(int k) {
        dist = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        visited = new boolean[N + 1];
        q = new PriorityQueue<>();

        q.add(new Node(k, 0));
        dist[k] = 0;
        while (!q.isEmpty()) {
            Node curr = q.poll();
            int c_v = curr.v;
            if (visited[c_v]) continue;
            visited[c_v] = true;

            graph[c_v].forEach(node -> {
                int value = node.v;
                int weight = node.w;
                if (dist[value] > dist[c_v] + weight) {
                    dist[value] = dist[c_v] + weight;
                    q.add(new Node(value, dist[value]));
                }
            });
        }
        return new int[]{dist[1], dist[v1], dist[v2], dist[N]};
    }

    private static class Node implements Comparable<Node> {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}
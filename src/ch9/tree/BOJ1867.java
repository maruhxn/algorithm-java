package ch9.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 트리의 지름(G4)

/**
 * 항상 1번이 루트 노드.
 * 1. 루트 노드에서 가장 먼 리프 노드 찾기
 * 2. 해당 리프 노드에서 가장 먼 노드까지의 거리 구하기
 * => 다익스트라 2번
 */
public class BOJ1867 {

    static ArrayList<Node>[] graph;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        dist = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, w));
            graph[e].add(new Node(s, w));
        }

        dist = new int[n + 1];
        visited = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dijkstra(1);

        int max = 0;
        int maxIdx = 0;
        for (int i = 1; i <= n; i++) {
            if (max <= dist[i]) {
                max = dist[i];
                maxIdx = i;
            }
        }

        dist = new int[n + 1];
        visited = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dijkstra(maxIdx);

        int result = 0;
        for (int v : dist) {
            if (v == Integer.MAX_VALUE) continue;
            result = Math.max(result, v);
        }

        System.out.println(result);

    }

    private static void dijkstra(int u) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(u, 0));
        dist[u] = 0;

        while (!q.isEmpty()) {
            Node curr = q.poll();
            int c_v = curr.value;
            if (visited[c_v]) continue;
            visited[c_v] = true;

            graph[c_v].forEach(node -> {
                int value = node.value;
                int weight = node.weight;
                if (dist[value] > dist[c_v] + weight) {
                    dist[value] = dist[c_v] + weight;
                    q.add(new Node(value, dist[value]));
                }
            });
        }

    }

    static class Node implements Comparable<Node> {
        int value;
        int weight;

        public Node(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return this.weight - n.weight;
        }
    }

}


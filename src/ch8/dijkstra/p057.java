package ch8.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

// 최소 비용 구하기(G5)
public class p057 {
    static ArrayList<Node>[] graph;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList[n + 1];
        dist = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph[u].add(new Node(v, w));
        }

        int u = sc.nextInt();
        int v = sc.nextInt();

        System.out.println(dijkstra(u, v));
    }

    private static int dijkstra(int u, int v) {
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

        return dist[v];
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

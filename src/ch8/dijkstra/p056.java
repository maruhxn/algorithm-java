package ch8.dijkstra;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

// 최단 경로 구하기(G4)
public class p056 {
    static ArrayList<Node>[] graph;
    static int[] dist;
    static boolean[] visited;
    static PriorityQueue<Node> q = new PriorityQueue<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        int K = sc.nextInt(); // 시작 정점

        graph = new ArrayList[V + 1];
        dist = new int[V + 1];
        visited = new boolean[V + 1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i <= V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph[u].add(new Node(v, w));
        }

        q.add(new Node(K, 0));
        dist[K] = 0;
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

        for (int i = 1; i <= V; i++) {
            System.out.println(!visited[i] ? "INF" : dist[i]);
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
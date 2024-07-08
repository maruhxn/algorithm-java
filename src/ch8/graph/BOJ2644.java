package ch8.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 촌수계산(S2)
public class BOJ2644 {
    static ArrayList<Integer>[] graph;
    static int[] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int k = sc.nextInt();

        graph = new ArrayList[n + 1];
        dist = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = -1;
        }

        for (int i = 0; i < k; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            graph[s].add(e);
            graph[e].add(s);
        }

        BFS(a);

        System.out.println(dist[b]);
    }

    private static void BFS(int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        dist[i]++;
        while (!queue.isEmpty()) {
            Integer now = queue.poll();
            graph[now].forEach(v -> {
                if (dist[v] == -1) {
                    queue.add(v);
                    dist[v] = dist[now] + 1;
                }
            });
        }

    }
}
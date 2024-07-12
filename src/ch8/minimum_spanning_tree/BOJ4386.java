package ch8.minimum_spanning_tree;

// 별자리 만들기(G3)

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 연결 그래프 형태
 * 최소 스패닝 트리
 */
public class BOJ4386 {
    static PriorityQueue<Edge> queue;
    static Point[] points;
    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        queue = new PriorityQueue<>();
        points = new Point[n + 1];
        for (int i = 1; i <= n; i++) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            points[i] = new Point(x, y);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                Point s = points[i];
                Point e = points[j];
                double w = Math.sqrt(Math.pow(e.x - s.x, 2) + Math.pow(e.y - s.y, 2));
                queue.add(new Edge(i, j, w));
            }
        }

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int useEdge = 0;
        double result = 0;
        while (useEdge < n - 1) {
            Edge now = queue.poll();
            if (find(now.s) != find(now.e)) {
                union(now.s, now.e);
                useEdge++;
                result += now.w;
            }
        }
        System.out.printf("%.2f", result);
    }

    static int find(int v) {
        if (parent[v] == v) return v;
        return parent[v] = find(parent[v]);
    }

    static void union(int a, int b) {
        if (a == b) return;
        int p_a = find(a);
        int p_b = find(b);
        parent[p_b] = p_a;
    }

    static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int s, e;
        double w;

        public Edge(int s, int e, double w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return (int) (this.w - o.w);
        }
    }
}

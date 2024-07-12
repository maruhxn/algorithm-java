package ch8.minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 전력난(G4)
public class BOJ6497 {
    static PriorityQueue<Edge> queue;
    static int[] parent;
    static long total;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            total = 0;
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()); // 집의 수
            int n = Integer.parseInt(st.nextToken()); // 길의 수
            if (m == 0 && n == 0) break;

            parent = new int[m + 1];
            for (int i = 1; i <= m; i++) {
                parent[i] = i;
            }

            queue = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                total += w;
                queue.add(new Edge(s, e, w));
            }

            int useEdge = 0;
            long result = 0;
            while (useEdge < m - 1) {
                Edge now = queue.poll();
                if (find(now.s) != find(now.e)) {
                    union(now.s, now.e);
                    useEdge++;
                    result += now.w;
                }
            }

            System.out.println(total - result);
        }

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

    static class Edge implements Comparable<Edge> {
        int s, e;
        long w;

        public Edge(int s, int e, long w) {
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

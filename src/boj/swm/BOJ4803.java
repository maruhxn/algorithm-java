package boj.swm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ4803 {

    static int[] parent, rank;
    static boolean[] isCycle;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int c = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;

            parent = new int[n + 1];
            rank = new int[n + 1];
            isCycle = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            // 트리 개수 파악
            Set<Integer> set = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                int root = find(i);
                if (!isCycle[root]) set.add(root);
            }
            int cnt = set.size();

            sb.append(String.format("Case %d: ", c++));
            if (cnt == 0) {
                sb.append("No trees.\n");
            } else if (cnt == 1) {
                sb.append("There is one tree.\n");
            } else {
                sb.append(String.format("A forest of %d trees.\n", cnt));
            }
        }

        System.out.println(sb);
    }

    private static int find(int x) {
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            isCycle[x] = true;
            return;
        }

        // 사이클 전파: 둘 중 하나가 사이클이면 합쳐진 전체가 사이클
        if (isCycle[x] || isCycle[y]) {
            isCycle[x] = isCycle[y] = true;
        }

        if (rank[x] > rank[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        parent[x] = y;

        if (rank[x] == rank[y]) ++rank[y];
    }
}
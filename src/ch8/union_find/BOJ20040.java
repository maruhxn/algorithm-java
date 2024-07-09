package ch8.union_find;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 사이클 게임(G4)
public class BOJ20040 {
    static int[] parent;
    static boolean hasCycle = false;
    static int k = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 3 <= n <= 500,000
        int m = Integer.parseInt(st.nextToken()); // 3 <= n <= 1,000,000
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            union(s, e);
            if (hasCycle && k == 0) k = i;
        }

        System.out.println(k);
    }

    private static int find(int a) {
        if (parent[a] != a) {
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }

    private static void union(int a, int b) {
        if (a == b) return;
        int parentA = find(a);
        int parentB = find(b);
        if (!hasCycle && parentA == parentB) hasCycle = true;
        parent[parentB] = parentA;
    }
}

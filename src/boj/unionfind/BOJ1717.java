package boj.unionfind;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ1717 {

    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 0) union(b, c);
            else {
                if (find(b) == find(c)) bw.append("YES\n");
                else bw.append("NO\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;

        if (rank[x] > rank[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        parent[x] = y;

        if (rank[x] == rank[y]) ++rank[y];
    }
}

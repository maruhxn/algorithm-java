package boj.unionfind;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1043 {

    static int[] parent, rank;
    static ArrayList<Integer>[] party;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        rank = new int[n + 1];

        party = new ArrayList[m];

        // 초기화
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < k; i++) {
            int a = Integer.parseInt(st.nextToken());
            union(a, 0);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            party[i] = new ArrayList<>();

            int prev = Integer.parseInt(st.nextToken());
            party[i].add(prev);

            for (int j = 0; j < cnt - 1; j++) {
                int next = Integer.parseInt(st.nextToken());
                party[i].add(next);
                union(prev, next);
            }
        }

        int ret = 0;

        for (int i = 0; i < party.length; i++) {
            boolean flag = true;
            for (int j = 0; j < party[i].size(); j++) {
                if (find(party[i].get(j)) == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) ++ret;
        }

        bw.write(String.valueOf(ret));
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

        if (x == 0) {
            int temp = x;
            x = y;
            y = temp;
        }

        parent[x] = y;

        if (rank[x] == rank[y]) ++rank[y];
    }

}

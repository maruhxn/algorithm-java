package boj.unionfind;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1976 {

    static int[] parent, rank;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N];
        rank = new int[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int a = Integer.parseInt(st.nextToken());
                if(a == 1) union(i, j);
            }
        }

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[M];
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int j : arr) {
            if (find(arr[0]) != find(j)) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    static int find(int x) {
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    static void union(int x, int y) {
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

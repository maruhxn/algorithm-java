package boj.swm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11725 {

    static ArrayList<Integer>[] tree;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N + 1];
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < parent.length; i++) {
            sb.append(parent[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int node) {
        for (int child : tree[node]) {
            if (child != 1 && parent[child] == 0) {
                parent[child] = node;
                dfs(child);
            }
        }
    }
}

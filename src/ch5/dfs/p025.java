package ch5.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// ABCDE(G5)
public class p025 {

    static ArrayList<Integer>[] A;
    static boolean visited[];
    static boolean ok;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        ok = false;
        visited = new boolean[n];
        A = new ArrayList[n];
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            A[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            A[s].add(e);
            A[e].add(s);
        }

        for (int i = 0; i < n; i++) {
            DFS(i, 1);
            if(ok) break;
        }

        if (ok) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static void DFS(int now, int depth) {
        if (depth == 5 || ok) {
            ok = true;
            return;
        }

        visited[now] = true;
        for (int i : A[now]) {
            if (!visited[i]) {
                DFS(i, depth + 1);
            }
        }
        visited[now] = false;
    }
}

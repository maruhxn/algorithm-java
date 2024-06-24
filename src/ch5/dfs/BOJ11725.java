package ch5.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 트리의 부모 찾기(S2)
public class BOJ11725 {
    static ArrayList<Integer>[] A;
    static int[] result;
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        A = new ArrayList[n + 1];
        result = new int[n - 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            A[s].add(e);
            A[e].add(s);
        }

        DFS(1);

        for (int i : result) {
            System.out.println(i);
        }
    }

    private static void DFS(int v) {
        if (visited[v]) return;

        visited[v] = true;
        for (int i : A[v]) {
            if (!visited[i]) {
                result[i - 2] = v;
                DFS(i);
            }
        }
    }
}



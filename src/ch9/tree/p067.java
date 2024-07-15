package ch9.tree;

import java.util.ArrayList;
import java.util.Scanner;

// 트리의 부모 찾기(S2)
public class p067 {

    static ArrayList<Integer>[] A;
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        A = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            A[s].add(e);
            A[e].add(s);
        }

        visited = new boolean[n + 1];
        parent = new int[n + 1];

        DFS(1);

        for (int i = 2; i <= n; i++) {
            System.out.println(parent[i]);
        }

    }

    private static void DFS(int i) {
        visited[i] = true;
        A[i].forEach(node -> {
            if (!visited[node]) {
                parent[node] = i;
                DFS(node);
            }
        });
    }
}

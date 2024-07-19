package ch9.lowest_common_ancestor;

import java.util.ArrayList;
import java.util.Scanner;

// LCA(G3)
public class p074 {
    static ArrayList<Integer>[] tree;
    static int[][] P;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        tree = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        P = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            tree[s].add(e);
            tree[e].add(s);
        }

        DFS(1, 0);
        int M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            LCA(a, b);
        }
    }

    private static void LCA(int a, int b) {
//        if(a == b) {
//            System.out.println(a);
//            return;
//        }
//        if (P[a][1] == P[b][1]) { // 깊이가 같을 경우
//            int parent_A = P[a][0];
//            int parent_B = P[b][0];
//            while (parent_A != parent_B) {
//                parent_A = P[parent_A][0];
//                parent_B = P[parent_B][0];
//            }
//            System.out.println(parent_A);
//        } else {
//            if (P[a][1] >= P[b][1]) {
//                LCA(P[a][0], b);
//            } else {
//                LCA(a, P[b][0]);
//            }
//        }
        if (P[a][1] < P[b][1]) {
            int temp = a;
            a = b;
            b = temp;
        }

        while (P[a][1] != P[b][1]) { // 두 노드의 depth 맞추기
            a = P[a][0];
        }

        while (a != b) { // 같은 조상이 나올 때까지 한 칸씩 올리기
            a = P[a][0];
            b = P[b][0];
        }

        System.out.println(a);
    }

    private static void DFS(int i, int depth) {
        visited[i] = true;
        for (int node : tree[i]) {
            if (!visited[node]) {
                P[node][0] = i;
                P[node][1] = depth + 1;
                DFS(node, depth + 1);
            }
        }
    }
}
